package com.st.nicobot.utils;

/**
 * @author Julien
 *
 */
public class Option {

	public final String channel;
	public final String sender;
	public final String message;
	
	public Option(String channel, String sender, String message) {
		this.channel = channel;
		this.sender = sender;
		this.message = message;
	}
	
}
