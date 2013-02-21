package com.st.nicobot.bot.event;

import com.st.nicobot.bot.NicoBot;

public interface KickEvent extends AbstractEvent {

	/**
     * This method is called whenever someone (possibly us) is kicked from
     * any of the channels that we are in.
     *  <p>
     * The implementation of this method in the PircBot abstract class
     * performs no actions and may be overridden as required.
     * 
     * @param channel The channel from which the recipient was kicked.
     * @param kickerNick The nick of the user who performed the kick.
     * @param kickerLogin The login of the user who performed the kick.
     * @param kickerHostname The hostname of the user who performed the kick.
     * @param recipientNick The unfortunate recipient of the kick.
     * @param reason The reason given by the user who performed the kick.
     * @param nicobot the famous bot.
     */
    void onKick(String channel, String kickerNick, String kickerLogin, String kickerHostname, String recipientNick, String reason, NicoBot nicobot); 
}
