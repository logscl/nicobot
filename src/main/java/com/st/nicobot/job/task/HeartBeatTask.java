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
		try {
			URL url = new URL(URL);
			URLConnection connection = url.openConnection();
			InputStream is = connection.getInputStream();
			
			while(is.read() != -1) {
				System.out.println("Reading ...");
			}
		}
		catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

}