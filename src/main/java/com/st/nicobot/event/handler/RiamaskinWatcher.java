package com.st.nicobot.event.handler;

import org.picocontainer.annotations.Inject;

import com.st.nicobot.NicoBot;
import com.st.nicobot.services.Messages;
import com.st.nicobot.utils.Option;

/**
 * @author jlamby
 *
 */
public class RiamaskinWatcher extends ConditionalMessageEvent {

    private static final String RIAMASKIN_STRING = "riamaskin";

    @Inject
    private Messages messages;
    
    @Override
    public int getChance() {
        return 250;
    }

    @Override
    public boolean testImpl(Option option) {
    	return option.message.equalsIgnoreCase(RIAMASKIN_STRING);
    }
    
    @Override
    public void onMessage(String channel, String sender, String login, String hostname, String message, NicoBot nicobot) {
    	Option o = new Option(channel, sender, message, false);
    	
    	if (testCondition(o)) {
    		nicobot.sendMessage(channel, messages.getOtherMessage(RIAMASKIN_STRING));
    	}
    }

}
