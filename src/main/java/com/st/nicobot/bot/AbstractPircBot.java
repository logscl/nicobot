/**
 * 
 */
package com.st.nicobot.bot;

import org.jibble.pircbot.PircBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Julien
 *
 */
public abstract class AbstractPircBot extends PircBot {

	private static Logger logger = LoggerFactory.getLogger(AbstractPircBot.class);
	
	@Override
	public void log(String line) {
		logger.trace(line);
	}
	
}
