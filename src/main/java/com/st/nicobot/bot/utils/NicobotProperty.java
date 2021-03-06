package com.st.nicobot.bot.utils;

public enum NicobotProperty {

	BOT_NAME("bot.name", "nicobot"),
	BOT_SERVER("bot.server","nl.quakenet.org"),
	BOT_CHAN("bot.chan","#zqsd"),
	BOT_AUTO_NICK_CHANGE("bot.autoNickChange","true"),
	BOT_MESSAGE_DELAY("bot.messageDelay","1000"),

	BOT_Q_AUTHNAME("bot.qAuth", ""),
	BOT_Q_PASSWORD("bot.qPassword", ""),
	BOT_DEV_MODE("bot.devMode", "true"),

	API_URI("api.uri", "http://api.nicobot.cloudbees.net"),
	API_PERSISTENCE_STRATEGY("api.persistence.stragegy", "com.st.nicobot.internal.api.services.NoPersistenceStrategy");

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
