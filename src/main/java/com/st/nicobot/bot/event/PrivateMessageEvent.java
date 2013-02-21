package com.st.nicobot.bot.event;

import com.st.nicobot.bot.NicoBot;

public interface PrivateMessageEvent extends AbstractEvent {

	/**
     * This method is called whenever a private message is sent to the PircBot.
     *  <p>
     * The implementation of this method in the PircBot abstract class
     * performs no actions and may be overridden as required.
     *
     * @param sender The nick of the person who sent the private message.
     * @param login The login of the person who sent the private message.
     * @param hostname The hostname of the person who sent the private message.
     * @param message The actual message.
     * @param nicobot The famous bot.
     */
    void onPrivateMessage(String sender, String login, String hostname, String message, NicoBot nicobot);
}
