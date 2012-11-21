/**
 * 
 */
package com.st.nicobot.job.task;

import com.st.nicobot.BotMain;
import com.st.nicobot.NicoBot;
import com.st.nicobot.internal.services.MessagesImpl;

/**
 * @author Julien
 *
 */
public class HappyGeekTimeTask extends Task {

	@Override
	public void run() {
		System.out.println("HappyGeekTimeTask running ...");
		
		
		NicoBot bot = BotMain.getBot();
		
		for(String channel : bot.getChannels()) {
			System.out.println("Sending message to " + channel);
			bot.sendMessage(channel, MessagesImpl.getInstance().getOtherMessage("hgt"));
		}
	}
}
