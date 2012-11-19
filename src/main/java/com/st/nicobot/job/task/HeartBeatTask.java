/**
 * 
 */
package com.st.nicobot.job.task;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Julien
 *
 */
public class HeartBeatTask extends Task {

	private static final String URL = "http://nicobot.jlamby.cloudbees.net/";
	
	@Override
	public void run() {
		System.out.println("HeartBeatTask running ...");
		try {
			URL url = new URL(URL);
			URLConnection connection = url.openConnection();
			InputStream is = connection.getInputStream();
			
			while(is.read() != -1) {
				//do nothing
			}
			System.out.println("End of stream.");
		}
		catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

}