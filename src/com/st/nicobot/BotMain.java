package com.st.nicobot;

public class BotMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		NicoBot bot = new NicoBot();
		
		//moar logs
		bot.setVerbose(true);
		
		bot.connect("irc.quakenet.org");
		bot.joinChannel("#zqsd");
	}


}
