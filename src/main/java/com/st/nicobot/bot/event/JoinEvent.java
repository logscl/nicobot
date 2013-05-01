package com.st.nicobot.bot.event;


public interface JoinEvent extends AbstractEvent {

	/**
     * This method is called whenever someone (possibly us) joins a channel
     * which we are on.
     *  <p>
     * The implementation of this method in the PircBot abstract class
     * performs no actions and may be overridden as required.
     *
     * @param channel The channel which somebody joined.
     * @param sender The nick of the user who joined the channel.
     * @param login The login of the user who joined the channel.
     * @param hostname The hostname of the user who joined the channel.
     */
    void onJoin(String channel, String sender, String login, String hostname);
}
