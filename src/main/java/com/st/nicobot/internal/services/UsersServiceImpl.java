package com.st.nicobot.internal.services;

import java.util.HashSet;
import java.util.Set;

import org.jibble.pircbot.User;

import com.st.nicobot.services.UsersService;

public class UsersServiceImpl implements UsersService {
	
	private Set<String> administrators = new HashSet<String>();

	@Override
	public void addUser(User user) {
		if(user.isOp()) {
			administrators.add(user.getNick());
		}
	}
	
	@Override
	public void addUser(String username) {
		administrators.add(username);
	}

	@Override
	public void updateUser(String oldUsername, String newUsername) {
		String u = findUser(oldUsername);
		if(u != null) {
			administrators.remove(u);
			administrators.add(newUsername);
		}
	}

	@Override
	public void removeUser(String username) {
		String u = findUser(username);
		if(u != null) {
			administrators.remove(u);
		}
	}

	@Override
	public boolean isAdmin(String username) {
		return findUser(username) != null;
	}

	@Override
	public Set<String> getUsers() {
		return administrators;
	}
	
	private String findUser(String username) {
		for(String user : administrators) {
			if(user.equalsIgnoreCase(username)) {
				return user;
			}
		}
		return null;
	}

}
