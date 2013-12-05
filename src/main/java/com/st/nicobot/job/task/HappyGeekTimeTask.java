package com.st.nicobot.job.task;

import com.st.nicobot.services.LeetGreetingService;
import org.apache.commons.lang3.StringUtils;
import org.picocontainer.annotations.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.st.nicobot.bot.NicoBot;
import com.st.nicobot.context.annotations.Component;
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
				String message;
				if(greetingService.getGreeters().get(chan) != null) {
					String names = StringUtils.join(greetingService.getGreeters().get(chan), ",");
					if(names.contains(",")) {
						message = String.format(messages.getOtherMessage("congratHGT"), names);
					} else {
						message = String.format(messages.getOtherMessage("congratSoloHGT"), names);
					}
				} else {
					message = messages.getOtherMessage("noHGT");
				}
				bot.sendChannelMessage(chan, message);
			}
			logger.info("Happy Geek Thread finished");
		} catch (InterruptedException e) {
			logger.error("Error in waiting task", e);
		} finally {
			greetingService.finish();
		}
	}
}
