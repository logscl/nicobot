package com.st.nicobot;

import java.util.List;

import org.jibble.pircbot.User;
import org.picocontainer.annotations.Inject;

import com.st.nicobot.bot.AbstractPircBot;
import com.st.nicobot.context.annotations.Component;
import com.st.nicobot.event.DeopEvent;
import com.st.nicobot.event.InviteEvent;
import com.st.nicobot.event.JoinEvent;
import com.st.nicobot.event.KickEvent;
import com.st.nicobot.event.MessageEvent;
import com.st.nicobot.event.NickChangeEvent;
import com.st.nicobot.event.OpEvent;
import com.st.nicobot.event.PartEvent;
import com.st.nicobot.event.PrivateMessageEvent;
import com.st.nicobot.event.UserListEvent;
import com.st.nicobot.property.NicobotProperty;
import com.st.nicobot.services.BehaviorsService;
import com.st.nicobot.services.HandlingService;
import com.st.nicobot.services.PropertiesService;
import com.st.nicobot.services.SchedulerService;
import com.st.nicobot.utils.Option;

/**
 * Je me suis basé la dessus !
 * http://www.jibble.org/pircbot.php
 * @author Logan
 *
 */
@Component
public class NicoBot extends AbstractPircBot {
	
	@Inject
	private BehaviorsService behaviors;
	
	@Inject
	private HandlingService handling;
	
	@Inject
	private SchedulerService schedulerService;
	
	@Inject
	private PropertiesService props;
	
	public NicoBot() {	}
	
	public void start() {
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
			event.onMessage(channel, sender, login, hostname, message, this);
		}
		
		behaviors.randomBehave(this, new Option(channel, sender, message, false));
	}
	
	@Override
	protected void onKick(String channel, String kickerNick, String kickerLogin, String kickerHostname, 
			String recipientNick, String reason) {
		
		List<KickEvent> events = handling.getEvents(KickEvent.class);
		
		for(KickEvent event : events) {
			event.onKick(channel, kickerNick, kickerLogin, kickerHostname, recipientNick, reason, this);
		}
	}
	
	@Override
	protected void onInvite(String targetNick, String sourceNick, String sourceLogin, String sourceHostname, String channel) {
		List<InviteEvent> events = handling.getEvents(InviteEvent.class);
		
		for(InviteEvent event : events) {
			event.onInvite(targetNick, sourceNick, sourceLogin, sourceHostname, channel, this);
		}
	}
	
	@Override
	protected void onJoin(String channel, String sender, String login, String hostname) {
		List<JoinEvent> events = handling.getEvents(JoinEvent.class);
		
		for(JoinEvent event : events) {
			event.onJoin(channel, sender, login, hostname, this);
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
			event.onPrivateMessage(sender, login, hostname, message, this);
		}
	}
	
	@Override
	protected void onPart(String channel, String sender, String login, String hostname) {
		super.onPart(channel, sender, login, hostname);
		
		List<PartEvent> events = handling.getEvents(PartEvent.class);
		
		for(PartEvent event : events) {
			event.onPart(channel, sender, login, hostname, this);
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
		
		schedulerService.startScheduler();
		
		//TODO : Trouver un moyen de simplifier ca en dev (params auth/pass remplacés au save - ou alors désactiver en dev
		this.sendRawLine("AUTH "+props.get(NicobotProperty.BOT_Q_AUTHNAME)+" "+props.get(NicobotProperty.BOT_Q_PASSWORD));
		this.sendRawLine("MODE "+props.get(NicobotProperty.BOT_NAME)+ " +x");
	}

}
