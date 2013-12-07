package com.st.nicobot.bot.event;

public interface QuitEvent extends AbstractEvent {
	
	/**
     * This method is called whenever someone (possibly us) quits from the
     * server.  We will only observe this if the user was in one of the
     * channels to which we are connected.
     *  <p>
     * The implementation of this method in the PircBot abstract class
     * performs no actions and may be overridden as required.
     * 
     * @param sourceNick The nick of the user that quit from the server.
     * @param sourceLogin The login of the user that quit from the server.
     * @param sourceHostname The hostname of the user that quit from the server.
     * @param reason The reason given for quitting the server.
     */
    void onQuit(String sourceNick, String sourceLogin, String sourceHostname, String reason);

}
