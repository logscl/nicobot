package com.st.nicobot.behavior;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Julien
 *
 */
public class BackwardsSpeakingTest {

	private BackwardsSpeaking backwardsSpeaking = new BackwardsSpeaking();
	
	@Test
	public void testReverseMessage() throws Exception {
		String message = "hello World";
		String resp = backwardsSpeaking.reverseString(message);
		assertEquals("dlroW olleh", resp);

		message = "Hello World";
		resp = backwardsSpeaking.reverseString(message);
		assertEquals("DlroW olleh", resp);
		
		message = "Hello World !";
		resp = backwardsSpeaking.reverseString(message);
		assertEquals("! dlroW olleh", resp);
	}
	
}
