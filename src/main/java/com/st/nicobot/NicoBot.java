package com.st.nicobot;

import java.util.List;

import com.st.nicobot.bot.AbstractPircBot;
import com.st.nicobot.event.AbstractEvent;
import com.st.nicobot.event.InviteEvent;
import com.st.nicobot.event.JoinEvent;
import com.st.nicobot.event.KickEvent;
import com.st.nicobot.event.MessageEvent;
import com.st.nicobot.event.PartEvent;
import com.st.nicobot.event.PrivateMessageEvent;
import com.st.nicobot.internal.services.BehaviorsServiceImpl;
import com.st.nicobot.internal.services.HandlingServiceImpl;
import com.st.nicobot.internal.services.PropertiesServiceImpl;
import com.st.nicobot.internal.services.SchedulerServiceImpl;
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
public class NicoBot extends AbstractPircBot {
	
	//bon je triche un peu, il faut dans l'ideal          
	//un service/whatever qui gere tt les autres services 
	private BehaviorsService behaviors = BehaviorsServiceImpl.getInstance();
	
	private SchedulerService schedulerService = SchedulerServiceImpl.getInstance();
	
	private PropertiesService props = PropertiesServiceImpl.getInstance();
	
	private HandlingService handling = HandlingServiceImpl.getInstance();
	
	public NicoBot() {
		this("nicobot");
	}
	
	public NicoBot(String nick) {
		this.setName(nick);
		this.setAutoNickChange(props.getBoolean(NicobotProperty.BOT_AUTO_NICK_CHANGE));
		this.setMessageDelay(props.getLong(NicobotProperty.BOT_MESSAGE_DELAY));
	}
	
	@Override
	protected void onMessage(String channel, String sender, String login, String hostname, String message) {
		super.onMessage(channel, sender, login, hostname, message);
		
		List<AbstractEvent> events = handling.getEvents(MessageEvent.class);
		
		for(AbstractEvent event : events) {
			((MessageEvent)event).onMessage(channel, sender, login, hostname, message, this);
		}
		
		behaviors.randomBehave(this, new Option(channel, sender, message));
	}
	
	@Override
	protected void onKick(String channel, String kickerNick, String kickerLogin, String kickerHostname, 
			String recipientNick, String reason) {
		
		List<AbstractEvent> events = handling.getEvents(KickEvent.class);
		
		for(AbstractEvent event : events) {
			((KickEvent)event).onKick(channel, kickerNick, kickerLogin, kickerHostname, recipientNick, reason, this);
		}
	}
	
	@Override
	protected void onInvite(String targetNick, String sourceNick, String sourceLogin, String sourceHostname, String channel) {
		List<AbstractEvent> events = handling.getEvents(InviteEvent.class);
		
		for(AbstractEvent event : events) {
			((InviteEvent)event).onInvite(targetNick, sourceNick, sourceLogin, sourceHostname, channel, this);
		}
	}
	
	@Override
	protected void onJoin(String channel, String sender, String login, String hostname) {
		List<AbstractEvent> events = handling.getEvents(JoinEvent.class);
		
		for(AbstractEvent event : events) {
			((JoinEvent)event).onJoin(channel, sender, login, hostname, this);
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
		
		List<AbstractEvent> events = handling.getEvents(PrivateMessageEvent.class);
		
		for(AbstractEvent event : events) {
			((PrivateMessageEvent)event).onPrivateMessage(sender, login, hostname, message, this);
		}
	}
	
	@Override
	protected void onPart(String channel, String sender, String login, String hostname) {
		super.onPart(channel, sender, login, hostname);
		
		List<AbstractEvent> events = handling.getEvents(PartEvent.class);
		
		for(AbstractEvent event : events) {
			((PartEvent)event).onPart(channel, sender, login, hostname, this);
		}
	}
	
	@Override
	protected void onConnect() {
		super.onConnect();
		
		schedulerService.startScheduler();
	}
	
}
