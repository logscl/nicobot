package com.st.nicobot.utils;

/**
 * @author Julien
 *
 */
public class Option {

	public final String channel;
	public final String sender;
	public final String message;
	public final boolean senderIsAdmin;
	
	/**
	 * Use {@link Option#Option(String, String, String, boolean)} instead.
	 * @param channel
	 * @param sender
	 * @param message
	 */
	@Deprecated
	public Option(String channel, String sender, String message) {
		this.channel = channel;
		this.sender = sender;
		this.message = message;
		this.senderIsAdmin = false;
	}
	
	public Option(String channel, String sender, String message, boolean senderIsAdmin) {
		this.channel = channel;
		this.sender = sender;
		this.message = message;
		this.senderIsAdmin = senderIsAdmin;
	}
	
}
