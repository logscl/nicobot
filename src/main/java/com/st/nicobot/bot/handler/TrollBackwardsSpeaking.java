package com.st.nicobot.bot.handler;

import org.apache.commons.lang3.StringUtils;

import com.st.nicobot.bot.NicoBot;
import com.st.nicobot.bot.behavior.BackwardsSpeaking;
import com.st.nicobot.bot.utils.Option;

/**
 * Lorsque le message recu est un palindrome, il y a une chance que nicobot troll et le renvoi sans modif (simulation de 
 * {@link BackwardsSpeaking}.
 * 
 * @author Julien
 *
 */
public class TrollBackwardsSpeaking extends ConditionalMessageEvent {

    @Override
    public int getChance() {
        return 250;
    }

    @Override
    public void onMessage(String channel, String sender, String login, String hostname, String message, NicoBot nicobot) {
    	Option opt = new Option(channel, sender, message, false);
    	
    	if (testCondition(opt)) {
        	nicobot.sendMessage(channel, message);
        }
    }
    
    @Override
    public boolean testImpl(Option o) {
    	return StringUtils.reverse(o.message).equals(o.message);
    }
}
