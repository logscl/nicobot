package com.st.nicobot;

import com.st.nicobot.internal.services.PropertiesServiceImpl;
import com.st.nicobot.property.NicobotProperty;
import com.st.nicobot.services.PropertiesService;

public class BotMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		PropertiesService props = PropertiesServiceImpl.getInstance();
		
		NicoBot bot = new NicoBot(props.get(NicobotProperty.BOT_NAME));
		
		//moar logs
		bot.setVerbose(props.getBoolean(NicobotProperty.BOT_VERBOSE));
		
		bot.connect(props.get(NicobotProperty.BOT_SERVER));
		bot.joinChannel(props.get(NicobotProperty.BOT_CHAN));
	}


}
