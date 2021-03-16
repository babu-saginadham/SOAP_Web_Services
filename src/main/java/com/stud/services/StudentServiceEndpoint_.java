package com.stud.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.addressing.server.annotation.Action;
import org.springframework.ws.soap.server.endpoint.annotation.SoapAction;

import com.auto.generated.model.StudentLookupRequest;
import com.auto.generated.model.StudentLookupResponse;


//@Endpoint
public class StudentServiceEndpoint_ {

	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceEndpoint_.class);
	
	private static final String NAMESPACE = "http://com/pmsgate/schemas";

	@PayloadRoot(localPart = "StudentLookupRequest", namespace = NAMESPACE)
	@SoapAction("http://com/pmsgate/StudentLookup")
	@Action("http://com/pmsgate/StudentLookup")
	@ResponsePayload
	public StudentLookupResponse lookup(
			@RequestPayload final StudentLookupRequest request) {

		LOGGER.debug("updatePayee");
		
		StudentLookupResponse response = null;
		try
		{
			response = new StudentLookupResponse();
			response.setResult("SUCCESS");
			response.setStudName("Babu");
			response.setStudNo("121");
		}
		catch(Exception e)
		{
			LOGGER.error("General Exception in updatePayee: "+e);
			response = new StudentLookupResponse();
			response.setResult("Error");
		}

		return response;	
	}
	
}
