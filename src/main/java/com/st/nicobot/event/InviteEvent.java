package com.st.nicobot.event;

import com.st.nicobot.NicoBot;

public interface InviteEvent extends AbstractEvent {

	/**
     * Called when we are invited to a channel by a user.
     *  <p>
     * The implementation of this method in the PircBot abstract class
     * performs no actions and may be overridden as required.
     * 
     * @since PircBot 0.9.5
     * 
     * @param targetNick The nick of the user being invited - should be us!
     * @param sourceNick The nick of the user that sent the invitation.
     * @param sourceLogin The login of the user that sent the invitation.
     * @param sourceHostname The hostname of the user that sent the invitation.
     * @param channel The channel that we're being invited to.
     * @param nicobot the famous nicobot
     */
    void onInvite(String targetNick, String sourceNick, String sourceLogin, String sourceHostname, String channel, NicoBot nicobot); 
}
