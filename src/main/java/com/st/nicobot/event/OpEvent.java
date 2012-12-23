package com.st.nicobot.event;

public interface OpEvent extends AbstractEvent {

	/**
     * Called when a user (possibly us) gets granted operator status for a channel.
     *  <p>
     * This is a type of mode change and is also passed to the onMode
     * method in the PircBot class.
     *  <p>
     * The implementation of this method in the PircBot abstract class
     * performs no actions and may be overridden as required.
     * 
     * @since PircBot 0.9.5
     *
     * @param channel The channel in which the mode change took place.
     * @param sourceNick The nick of the user that performed the mode change.
     * @param sourceLogin The login of the user that performed the mode change.
     * @param sourceHostname The hostname of the user that performed the mode change.
     * @param recipient The nick of the user that got 'opped'.
     */
    void onOp(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient);
}
