package com.st.nicobot;

public class BotMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		NicoBot bot = new NicoBot("N1C0-B0T");
		
		//moar logs
		bot.setVerbose(true);
		
		bot.connect("nl.quakenet.org");
		bot.joinChannel("#zqsd");
	}


}
