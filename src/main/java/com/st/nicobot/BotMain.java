package com.st.nicobot;

import com.st.nicobot.context.ApplicationContext;
import com.st.nicobot.property.NicobotProperty;
import com.st.nicobot.services.PropertiesService;

public class BotMain {

	private static ApplicationContext container;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		initContainer();
		
		PropertiesService props = container.getPicoContainer().getComponent(PropertiesService.class);
		
		NicoBot bot = container.getPicoContainer().getComponent(NicoBot.class);

		bot.connect(props.get(NicobotProperty.BOT_SERVER));
		bot.joinChannel(props.get(NicobotProperty.BOT_CHAN));
	}

	private static void initContainer() {
		container = new ApplicationContext();
		container.init();
	}
}
