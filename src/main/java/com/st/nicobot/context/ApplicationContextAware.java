package com.st.nicobot.context;

import org.picocontainer.MutablePicoContainer;

/**
 * @author Julien
 *
 */
public interface ApplicationContextAware {

	/**
	 * Défini le contexte d'application
	 * @param appCtx
	 */
	public void setApplicationContext(MutablePicoContainer appCtx);
	
}
