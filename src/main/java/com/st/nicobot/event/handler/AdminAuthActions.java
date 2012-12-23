package com.st.nicobot.event.handler;

import org.jibble.pircbot.User;
import org.picocontainer.annotations.Inject;

import com.st.nicobot.NicoBot;
import com.st.nicobot.event.DeopEvent;
import com.st.nicobot.event.NickChangeEvent;
import com.st.nicobot.event.OpEvent;
import com.st.nicobot.event.UserListEvent;
import com.st.nicobot.property.NicobotProperty;
import com.st.nicobot.services.PropertiesService;
import com.st.nicobot.services.UsersService;

public class AdminAuthActions implements NickChangeEvent, OpEvent, DeopEvent, UserListEvent {
	
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
		if(channel.equals(properties.get(NicobotProperty.BOT_CHAN))) {
			adminUsers.addUser(sourceNick);
		}
	}
	
	@Override
	public void onDeop(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient) {
		if(channel.equals(properties.get(NicobotProperty.BOT_CHAN))) {
			adminUsers.removeUser(sourceNick);
		}
	}

	@Override
	public void onNickChange(String oldNick, String login, String hostname,	String newNick) {
		adminUsers.updateUser(oldNick, newNick);
	}

}
