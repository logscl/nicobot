package com.st.nicobot.bot.handler;

import org.picocontainer.annotations.Inject;

import com.st.nicobot.bot.NicoBot;
import com.st.nicobot.bot.utils.Option;
import com.st.nicobot.services.Messages;

/**
 * @author jlamby
 *
 */
public class RiamaskinWatcher extends ConditionalMessageEvent {

    private static final String RIAMASKIN_STRING = "riamaskin";

    @Inject
    private Messages messages;
    
    @Inject
    private NicoBot nicobot;
    
    @Override
    public int getChance() {
        return 250;
    }

    @Override
    public boolean testImpl(Option option) {
    	return option.message.equalsIgnoreCase(RIAMASKIN_STRING);
    }
    
    @Override
    public void onMessage(String channel, String sender, String login, String hostname, String message) {
    	Option o = new Option(channel, sender, message, false);
    	
    	if (testCondition(o)) {
    		nicobot.sendChannelMessage(channel, messages.getOtherMessage(RIAMASKIN_STRING));
    	}
    }

}
