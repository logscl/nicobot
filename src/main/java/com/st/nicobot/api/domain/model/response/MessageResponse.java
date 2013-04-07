package com.st.nicobot.api.domain.model.response;

import java.util.List;

import com.st.nicobot.api.domain.model.Message;

/**
 * @author Julien
 *
 */
public class MessageResponse implements UnmarshalledResponse {

	private List<Message> messages;
	
	public MessageResponse() {	}

	public List<Message> getMessages() {
		return messages;
	}
	
}
