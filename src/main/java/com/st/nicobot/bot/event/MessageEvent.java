package com.st.nicobot.bot.event;



public interface MessageEvent extends AbstractEvent {

	/**
	 * This method is called whenever a message is sent to a channel.
	 *  <p>
	 * The implementation of this method in the PircBot abstract class
	 * performs no actions and may be overridden as required.
	 *
	 * @param channel The channel to which the message was sent.
	 * @param sender The nick of the person who sent the message.
	 * @param login The login of the person who sent the message.
	 * @param hostname The hostname of the person who sent the message.
	 * @param message The actual message sent to the channel.
	 */
	void onMessage(String channel, String sender, String login, String hostname, String message);
}
