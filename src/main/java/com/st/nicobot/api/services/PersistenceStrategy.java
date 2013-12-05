package com.st.nicobot.api.services;

import javax.ws.rs.core.MultivaluedMap;

public interface PersistenceStrategy {

	public String sendGetRequest(String serviceURI, MultivaluedMap<String, String> queryParams);

	public String sendPostRequest(String serviceURI, Object payload);

}
