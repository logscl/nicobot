package com.st.nicobot.bot;

import java.util.Arrays;
import java.util.List;

import com.st.nicobot.bot.utils.Random;
import org.jibble.pircbot.PircBot;
import org.jibble.pircbot.User;
import org.joda.time.DateTime;
import org.picocontainer.annotations.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.st.nicobot.api.domain.model.Message;
import com.st.nicobot.api.services.APIMessageService;
import com.st.nicobot.bot.event.DeopEvent;
import com.st.nicobot.bot.event.InviteEvent;
import com.st.nicobot.bot.event.JoinEvent;
import com.st.nicobot.bot.event.KickEvent;
import com.st.nicobot.bot.event.MessageEvent;
import com.st.nicobot.bot.event.NickChangeEvent;
import com.st.nicobot.bot.event.OpEvent;
import com.st.nicobot.bot.event.PartEvent;
import com.st.nicobot.bot.event.PrivateMessageEvent;
import com.st.nicobot.bot.event.UserListEvent;
import com.st.nicobot.bot.utils.NicobotProperty;
import com.st.nicobot.bot.utils.Option;
import com.st.nicobot.context.annotations.Component;
import com.st.nicobot.services.BehaviorsService;
import com.st.nicobot.services.HandlingService;
import com.st.nicobot.services.PropertiesService;
import com.st.nicobot.services.SchedulerService;

/**
 * Je me suis basé la dessus !
 * http://www.jibble.org/pircbot.php
 * @author Logan
 *
 */
@Component
public class NicoBot extends PircBot {

	private static Logger logger = LoggerFactory.getLogger(NicoBot.class);

	@Inject
	private BehaviorsService behaviors;

	@Inject
	private HandlingService handling;

	@Inject
	private SchedulerService schedulerService;

	@Inject
	private PropertiesService props;

	@Inject
	private APIMessageService apiMessageService;

	public NicoBot() {	}

	public void start() {
		// Random login généré au démarrage du bot
		this.setLogin("NB-" + Random.nextInt());

		this.setName(props.get(NicobotProperty.BOT_NAME));
		this.setAutoNickChange(props.getBoolean(NicobotProperty.BOT_AUTO_NICK_CHANGE));
		this.setMessageDelay(props.getLong(NicobotProperty.BOT_MESSAGE_DELAY));
		this.setVersion("Comme le vrai, mais en plus bot.");
	}

	@Override
	protected void onMessage(String channel, String sender, String login, String hostname, String message) {
		super.onMessage(channel, sender, login, hostname, message);

		List<MessageEvent> events = handling.getEvents(MessageEvent.class);

		for(MessageEvent event : events) {
			event.onMessage(channel, sender, login, hostname, message);
		}

		behaviors.randomBehave(this, new Option(channel, sender, message, false));
	}

	@Override
	protected void onKick(String channel, String kickerNick, String kickerLogin, String kickerHostname,
			String recipientNick, String reason) {

		List<KickEvent> events = handling.getEvents(KickEvent.class);

		for(KickEvent event : events) {
			event.onKick(channel, kickerNick, kickerLogin, kickerHostname, recipientNick, reason);
		}
	}

	@Override
	protected void onInvite(String targetNick, String sourceNick, String sourceLogin, String sourceHostname, String channel) {
		List<InviteEvent> events = handling.getEvents(InviteEvent.class);

		for(InviteEvent event : events) {
			event.onInvite(targetNick, sourceNick, sourceLogin, sourceHostname, channel);
		}
	}

	@Override
	protected void onJoin(String channel, String sender, String login, String hostname) {
		List<JoinEvent> events = handling.getEvents(JoinEvent.class);

		for(JoinEvent event : events) {
			event.onJoin(channel, sender, login, hostname);
		}
	}

	/**
	 * Format une chaine de caractere en remplacant les "%p" par {@code sender} et les "%c" par {@code channel}.
	 * @param message
	 * @param sender
	 * @param channel
	 * @return
	 */
	public String formatMessage(String message, String sender, String channel) {
		message = message.replaceAll("%p", sender);
		message = message.replaceAll("%c", channel);

		return message;
	}

	@Override
	protected void onPrivateMessage(String sender, String login, String hostname, String message) {
		super.onPrivateMessage(sender, login, hostname, message);

		List<PrivateMessageEvent> events = handling.getEvents(PrivateMessageEvent.class);

		for(PrivateMessageEvent event : events) {
			event.onPrivateMessage(sender, login, hostname, message);
		}
	}

	@Override
	protected void onPart(String channel, String sender, String login, String hostname) {
		super.onPart(channel, sender, login, hostname);

		List<PartEvent> events = handling.getEvents(PartEvent.class);

		for(PartEvent event : events) {
			event.onPart(channel, sender, login, hostname);
		}
	}

	@Override
	protected void onNickChange(String oldNick, String login, String hostname, String newNick) {
		super.onNickChange(oldNick, login, hostname, newNick);

		List<NickChangeEvent> events = handling.getEvents(NickChangeEvent.class);

		for(NickChangeEvent event : events) {
			event.onNickChange(oldNick, login, hostname, newNick);
		}
	}

	@Override
	protected void onOp(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient) {
		super.onOp(channel, sourceNick, sourceLogin, sourceHostname, recipient);

		List<OpEvent> events = handling.getEvents(OpEvent.class);

		for(OpEvent event : events) {
			event.onOp(channel, sourceNick, sourceLogin, sourceHostname, recipient);
		}
	}

	@Override
	protected void onUserList(String channel, User[] users) {
		super.onUserList(channel, users);

		List<UserListEvent> events = handling.getEvents(UserListEvent.class);

		for(UserListEvent event : events) {
			event.onUserList(channel, users);
		}
	}

	@Override
	protected void onDeop(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient) {
		super.onDeop(channel, sourceNick, sourceLogin, sourceHostname, recipient);

		List<DeopEvent> events = handling.getEvents(DeopEvent.class);

		for(DeopEvent event : events) {
			event.onDeop(channel, sourceNick, sourceLogin, sourceHostname, recipient);
		}
	}

	@Override
	protected void onConnect() {
		super.onConnect();

		this.joinChannel(props.get(NicobotProperty.BOT_CHAN));

		schedulerService.startScheduler();

		if(!props.getBoolean(NicobotProperty.BOT_DEV_MODE)) {
			this.sendRawLine("AUTH "+props.get(NicobotProperty.BOT_Q_AUTHNAME)+" "+props.get(NicobotProperty.BOT_Q_PASSWORD));
			this.sendRawLine("MODE "+props.get(NicobotProperty.BOT_NAME)+ " +x");
		}
		props.set(NicobotProperty.BOT_NAME, this.getName());
	}

	@Override
	public void log(String line) {
		logger.trace(line);
	}

	public void sendChannelMessage(String target, String msg) {
		Message message = new Message(new DateTime(), this.getNick(), msg);
		apiMessageService.saveMessages(Arrays.asList(message));

		sendMessage(target, msg);
	}

}
