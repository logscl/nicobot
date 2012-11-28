package com.st.nicobot;

import com.st.nicobot.internal.services.PropertiesServiceImpl;
import com.st.nicobot.property.NicobotProperty;
import com.st.nicobot.services.PropertiesService;

public class BotMain {

	//LOL dirty hackz
	private static NicoBot bot;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		PropertiesService props = PropertiesServiceImpl.getInstance();
		
		bot = new NicoBot(props.get(NicobotProperty.BOT_NAME));
		
		bot.connect(props.get(NicobotProperty.BOT_SERVER));
		bot.joinChannel(props.get(NicobotProperty.BOT_CHAN));
	}
	
	public static NicoBot getBot() {
		return bot;
	}


}
