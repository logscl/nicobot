package com.st.nicobot.internal.services;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.st.nicobot.property.NicobotProperty;
import com.st.nicobot.services.PropertiesService;

public class PropertiesServiceImpl implements PropertiesService {

	private static Logger logger = LoggerFactory.getLogger(PropertiesServiceImpl.class);
	
	private Properties properties;
	
	public PropertiesServiceImpl(){
	}
	
	public void start() {
		properties = new Properties();
		try {
			properties.load(getClass().getResourceAsStream("/nicobot.properties"));
		} catch (IOException e) {
			logger.error("Impossible de charger le fichier de properties :( ");
			e.printStackTrace();
		}
	}
	
	@Override
	public String get(NicobotProperty key) {
		return properties.getProperty(key.getKey(), key.getDefaultValue());
	}

	@Override
	public Boolean getBoolean(NicobotProperty key) {
		//TODO si la clé est différente de "true" ou "false", il faudrait tester la default value au lieu de retourner false par défaut.
		return Boolean.valueOf(this.get(key));
	}
	
	@Override
	public Long getLong(NicobotProperty key) {
		//TODO check NumberFormatException pour la clé et la defaultValue
		return Long.valueOf(this.get(key));
	}
}
