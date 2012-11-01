/**
 * 
 */
package com.st.nicobot.cmd;

/**
 * @author Julien
 *
 */
public class Option {

	protected String channel;
	protected String sender;
	
	public Option() {	}
	
	public Option(String channel, String sender) {
		this.channel = channel;
		this.sender = sender;
	}
}
