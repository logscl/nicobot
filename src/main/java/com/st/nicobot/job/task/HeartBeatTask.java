/**
 * 
 */
package com.st.nicobot.job.task;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Julien
 *
 */
public class HeartBeatTask extends Task {

	private static Logger logger = LoggerFactory.getLogger(HeartBeatTask.class);
	
	private static final String URL = "http://nicobot.jlamby.cloudbees.net/";
	
	@Override
	public void run() {
		logger.info("HeartBeatTask running ...");
		try {
			URL url = new URL(URL);
			URLConnection connection = url.openConnection();
			InputStream is = connection.getInputStream();
			
			while(is.read() != -1) {
				//do nothing
			}
			
			logger.debug("End of stream.");
		}
		catch(Exception ex) {
			logger.error(ex.getMessage());
		}
	}

}