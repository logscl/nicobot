package com.st.nicobot.property;

public enum NicobotProperty {

	BOT_NAME("bot.name", "nicobot"),
	BOT_SERVER("bot.server","nl.quakenet.org"),
	BOT_CHAN("bot.chan","#zqsd"),
	BOT_AUTO_NICK_CHANGE("bot.autoNickChange","true"),
	BOT_MESSAGE_DELAY("bot.messageDelay","1000"),
	BOT_VERBOSE("bot.verbose","false");
	
	private String key;
	private String defaultValue;
	
	private NicobotProperty(String key, String defaultValue) {
		this.key = key;
		this.defaultValue = defaultValue;
	}
	
	public String getKey() {
		return key;
	}
	
	public String getDefaultValue() {
		return defaultValue;
	}
}