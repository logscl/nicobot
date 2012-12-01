package com.st.nicobot.utils;


/**
 * @author Julien
 *
 */
public class Random {

	private static java.util.Random instanceRandom;
	
	private Random() {	}
	
	public static java.util.Random getInstance() {
		if (instanceRandom == null) {
			instanceRandom = new java.util.Random(1337);
		}
		
		return instanceRandom;
	}
	
}
