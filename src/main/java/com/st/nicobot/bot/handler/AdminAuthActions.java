package com.st.nicobot.bot.handler;

import org.jibble.pircbot.User;
import org.picocontainer.annotations.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.st.nicobot.bot.NicoBot;
import com.st.nicobot.bot.event.DeopEvent;
import com.st.nicobot.bot.event.NickChangeEvent;
import com.st.nicobot.bot.event.OpEvent;
import com.st.nicobot.bot.event.UserListEvent;
import com.st.nicobot.bot.utils.NicobotProperty;
import com.st.nicobot.services.PropertiesService;
import com.st.nicobot.services.UsersService;

public class AdminAuthActions implements NickChangeEvent, OpEvent, DeopEvent, UserListEvent {
	
	private static Logger logger = LoggerFactory.getLogger(AdminAuthActions.class);
	
	@Inject
	private NicoBot nicobot;
	
	@Inject
	private UsersService adminUsers;

	@Inject
	private PropertiesService properties;
	
	
	@Override
	public void onUserList(String channel, User[] users) {
		if(channel.equals(properties.get(NicobotProperty.BOT_CHAN))) {
			for(User user : users) {
				adminUsers.addUser(user); // check sur op fait dans le service
			}
		}
	}

	@Override
	public void onOp(String channel, String sourceNick, String sourceLogin,	String sourceHostname, String recipient) {
		logger.debug("User {} on chan {} has been oped by {} ({} - {}). Will add him to adminList !", new Object[]{recipient, channel, sourceNick, sourceLogin, sourceHostname});
		if(channel.equals(properties.get(NicobotProperty.BOT_CHAN))) {
			adminUsers.addUser(recipient);
		}
	}
	
	@Override
	public void onDeop(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient) {
		logger.debug("User {} on chan {} has been DEoped by {} ({} - {}). Will remove him from adminList !", new Object[]{recipient, channel, sourceNick, sourceLogin, sourceHostname});
		if(channel.equals(properties.get(NicobotProperty.BOT_CHAN))) {
			adminUsers.removeUser(recipient);
		}
	}

	@Override
	public void onNickChange(String oldNick, String login, String hostname,	String newNick) {
		logger.debug("User {} changed nick to {}. ({},{})",new Object[]{oldNick, newNick, login, hostname});
		adminUsers.updateUser(oldNick, newNick);
	}

}
