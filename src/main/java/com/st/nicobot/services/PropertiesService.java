package com.st.nicobot.services;

import com.st.nicobot.property.NicobotProperty;

public interface PropertiesService {
	
	/**
	 * Récupère une valeur dans le fichier de properties
	 * @param key la clé à chercher
	 * @return la valeur liée à la clé
	 */
	public String get(NicobotProperty key);
	
	public void set(NicobotProperty key, String value);
	
	/**
	 * Retourne un booléen suivant la valeur d'une clé du fichier de properties
	 * @param key la valeur à chercher
	 * @return <code>true</code> si la valeur de clé (ou defaultValue) = "<code>true</code>", false sinon
	 */
	public Boolean getBoolean(NicobotProperty key);
	
	public Long getLong(NicobotProperty key);

}
