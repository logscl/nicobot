package com.st.nicobot.event;

import org.jibble.pircbot.User;

public interface UserListEvent extends AbstractEvent {

	/**
     * This method is called when we receive a user list from the server
     * after joining a channel.
     *  <p>
     * Shortly after joining a channel, the IRC server sends a list of all
     * users in that channel. The PircBot collects this information and
     * calls this method as soon as it has the full list.
     *  <p>
     * To obtain the nick of each user in the channel, call the getNick()
     * method on each User object in the array.
     *  <p>
     * At a later time, you may call the getUsers method to obtain an
     * up to date list of the users in the channel.
     *  <p>
     * The implementation of this method in the PircBot abstract class
     * performs no actions and may be overridden as required.
     * 
     * @since PircBot 1.0.0
     * 
     * @param channel The name of the channel.
     * @param users An array of User objects belonging to this channel.
     * 
     * @see User
     */
    void onUserList(String channel, User[] users);
}
