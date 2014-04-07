package com.st.nicobot.job.task;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.picocontainer.annotations.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.st.nicobot.bot.NicoBot;
import com.st.nicobot.context.annotations.Component;
import com.st.nicobot.services.LeetGreetingService;
import com.st.nicobot.services.Messages;

/**
 * @author Julien
 *
 */
@Component
public class HappyGeekTimeTask extends Task {

	private static Logger logger = LoggerFactory.getLogger(HappyGeekTimeTask.class);

	@Inject
	private Messages messages;

	@Inject
	private NicoBot bot;

	@Inject
	private LeetGreetingService greetingService;

	@Override
	public void run() {
		logger.info("HappyGeekTimeTask running ...");

		for(String channel : bot.getChannels()) {
			logger.debug("Sending message to {}" , channel);

			bot.sendChannelMessage(channel, messages.getOtherMessage("hgt"));
		}

		try {
			logger.info("Bot will now wait for 1 min to read mesages");
			greetingService.init();
			synchronized (this) {
				this.wait(60000);
			}

			for(String chan : bot.getChannels()) {
				Set<String> names = greetingService.getGreeters().get(chan);
				String message = buildMessageWithNames(names);

				bot.sendChannelMessage(chan, message);
			}

			logger.info("Happy Geek Thread finished");
		} catch (InterruptedException e) {
			logger.error("Error in waiting task", e);
		} finally {
			greetingService.finish();
		}
	}

	/**
	 * Construit un message suivant si {@code names} est null ou non.
	 * 
	 * @param names
	 *            Une collection de {@link String} pouvant etre vide
	 * @return
	 */
	String buildMessageWithNames(Set<String> names) {
		String message = messages.getOtherMessage("noHGT");

		if (names != null) {
			message = createCongratulationMessageWithNames(names);
		}

		return message;
	}

	/**
	 * Retourne un message formaté diffirement si {@code names} contient 1 ou
	 * plusieurs éléments
	 * 
	 * @param names
	 *            Un collection de {@link String} contenant 1 ou plusieurs
	 *            éléments
	 * @return
	 */
	String createCongratulationMessageWithNames(Set<String> names) {
		String congratulationMessage = retrieveCongratulationMessage(names.size() > 1);

		return String.format(congratulationMessage, StringUtils.join(names, ", "));
	}

	/**
	 * Retourne un template de message suivant {@code isMoreThanOneGreeters}
	 * 
	 * @param isMoreThanOneGreeters
	 * @return
	 */
	String retrieveCongratulationMessage(boolean isMoreThanOneGreeters) {
		String congratMessage = messages.getOtherMessage("congratSoloHGT");

		if (isMoreThanOneGreeters) {
			congratMessage = messages.getOtherMessage("congratHGT");
		}

		return congratMessage;
	}
}
