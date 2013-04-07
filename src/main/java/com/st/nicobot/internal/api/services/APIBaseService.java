package com.st.nicobot.internal.api.services;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.picocontainer.annotations.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.st.nicobot.api.domain.model.response.UnmarshalledResponse;
import com.st.nicobot.bot.utils.NicobotProperty;
import com.st.nicobot.services.PropertiesService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * @author Julien
 *
 */
public abstract class APIBaseService<T extends UnmarshalledResponse> {

	private static Logger logger = LoggerFactory.getLogger(APIBaseService.class);

	@Inject
	private PropertiesService propertiesService;
	
	private static ObjectMapper objectMapper;

	public abstract T getResponseInstance();
	
	public abstract String getServiceName(); 
	
	ObjectMapper getObjectMapper() {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
		}
		
		return objectMapper;
	}
	
	public String getServiceURI() {
		return propertiesService.get(NicobotProperty.API_URI) + "/" + getServiceName();
	}
	
	/**
	 * Retourne un client pour effectuer une requete sur une url
	 * @param url
	 * @return
	 */
	WebResource getClient(String url){
		Client client = Client.create();
		WebResource webResource = client.resource(url);
		
		return webResource;
	}
	
	T sendGetRequest(MultivaluedMap<String, String> queryParams) {
		WebResource resource = getClient(getServiceURI());
		resource = resource.queryParams(queryParams);

		ClientResponse response = resource.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		
		T msgResp = null;
		
		if (response.getStatus() == 200) {
			msgResp = parseResponse(response);
		}
		else {
			logger.error("Erreur lors de la sauvegarde à distance. Reponse de l'API : {}", getRemoteAPIError(response));
		}
		
		return msgResp;
	}
	
	T sendPostRequest(Object payload) {
		try {
			String json = getObjectMapper().writeValueAsString(payload);
		
			System.out.println(json);
		
			ClientResponse response = getClient(getServiceURI()).type(MediaType.APPLICATION_JSON).post(ClientResponse.class, json);
	
			T msgResp = null;
			
			if (response.getStatus() == 201) {
				//msgResp = parseResponse(response); //faire evol l'api pour ameliorer le retour des POST
			}
			else {
				logger.error("Erreur lors de la sauvegarde à distance. Reponse de l'API : {}", getRemoteAPIError(response));
			}
			
			return msgResp;
		}
		catch(Exception ex) {
			System.err.println(ex);
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	T parseResponse(ClientResponse clientResponse) {
		try {
			String content = clientResponse.getEntity(String.class);
		
			return (T) getObjectMapper().readValue(content, getResponseInstance().getClass());
		}
		catch(Exception ex) {
			System.err.println(ex);
		}
		
		return null;
	}
	
	String getRemoteAPIError(ClientResponse clientResponse) {
		try {
			return clientResponse.getEntity(String.class);
		} 
		catch(Exception ex) {
			logger.error("{}", ex);
		}
		
		return null;
	}
}
