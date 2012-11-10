/**
 * 
 */
package com.st.nicobot.utils;

/**
 * @author Julien
 *
 */
public class HeartBeat {

	/** L'url */
	private String url;
	
	/** L'intervalle de temps entre 2 battements */
	private int delay;
	
	public HeartBeat() {	}
	
	public HeartBeat(String url, int delay) {
		this.delay = delay;
		this.url = url;
	}

	public int getDelay() {
		return delay;
	}
	
	public String getUrl() {
		return url;
	}
}
