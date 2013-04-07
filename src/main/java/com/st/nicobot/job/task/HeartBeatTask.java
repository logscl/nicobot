package com.st.nicobot.job.task;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.st.nicobot.context.annotations.Component;

/**
 * @author Julien
 *
 */
@Component
public class HeartBeatTask extends Task {

	private static Logger logger = LoggerFactory.getLogger(HeartBeatTask.class);
	
	private static final String WEB_URL = "http://nicobot.jlamby.cloudbees.net/";
	private static final String API_URL = "http://api.nicobot.jlamby.cloudbees.net/messages";
	
	@Override
	public void run() {
		logger.info("HeartBeatTask running ...");
		requestUrl(WEB_URL);
		requestUrl(API_URL);
	}
	
	void requestUrl(String requestUrl) {
		try {
			URL url = new URL(requestUrl);
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