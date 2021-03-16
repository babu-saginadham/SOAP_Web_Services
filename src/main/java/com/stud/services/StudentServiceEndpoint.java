package com.stud.services;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.server.endpoint.annotation.SoapAction;

import com.auto.generated.model.StudentCreationRequest;
import com.auto.generated.model.StudentCreationResponse;
import com.auto.generated.model.StudentDetailsRequest;
import com.auto.generated.model.StudentDetailsResponse;
import com.auto.generated.model.StudentLookupRequest;
import com.auto.generated.model.StudentLookupResponse;
import com.stud.model.Student;


@Endpoint //@RestController("value="/api/")
public class StudentServiceEndpoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceEndpoint_.class);
	
	private static final String NAMESPACE = "http://com/pmsgate/schemas";
	
	Map<String, Student> studentDetails = null;
	int sno = 1;

	@PayloadRoot(localPart = "StudentLookupRequest", namespace = NAMESPACE)
	@SoapAction("http://com/pmsgate/StudentLookup")
	@org.springframework.ws.soap.addressing.server.annotation.Action("http://com/pmsgate/StudentLookup")
	@ResponsePayload
	public StudentLookupResponse studentLookup(
			@RequestPayload final StudentLookupRequest request) {

		LOGGER.debug("studentLookup");
		
		StudentLookupResponse response = null;
		try
		{
			response = new StudentLookupResponse();
			if(studentDetails == null || studentDetails.isEmpty()) {
				response.setResult("No students available");
				return response;
			}
			
			if(studentDetails.get(String.valueOf(request.getStudNo())) == null) {
				response.setResult("Student Data not found");
				return response;
			}
			
			response.setResult("Student Found");
			response.setStudName(studentDetails.get(request.getStudNo()).getsName());
			response.setStudNo(studentDetails.get(request.getStudNo()).getSno());
			
			return response;
			
		}
		catch(Exception e)
		{
			LOGGER.error("General Exception in updatePayee: "+e);
			response = new StudentLookupResponse();
			response.setResult("Error");
		}

		return response;	
	}
	@PayloadRoot(localPart = "StudentDetailsRequest", namespace = NAMESPACE)
	@SoapAction("http://com/pmsgate/StudentDetails")
	@org.springframework.ws.soap.addressing.server.annotation.Action("http://com/pmsgate/StudentDetails")
	@ResponsePayload
	public StudentDetailsResponse studentDetails(@RequestPayload final StudentDetailsRequest request){
		LOGGER.debug("studentDetails");
		StudentDetailsResponse response1 = null;
		try{
			response1 = new StudentDetailsResponse();
			response1.setStudNo("111");
			response1.setStudName("Venki");
			response1.setContactNo("9988776655");
			response1.setStudAddress("Kothapeta");
			}
		catch(Exception e)
		{
			LOGGER.error("Exception"+e);
			response1 = new StudentDetailsResponse();
		}
		return response1;
	}
	
	@PayloadRoot(localPart = "StudentCreationRequest", namespace = NAMESPACE)
	@SoapAction("http://com/pmsgate/StudentCreation")
	@org.springframework.ws.soap.addressing.server.annotation.Action("http://com/pmsgate/StudentCreation")
	@ResponsePayload
	public StudentCreationResponse studentCreation(@RequestPayload final StudentCreationRequest request){
		LOGGER.debug("studentCreation");
		StudentCreationResponse response1 = null;
		
		
		try{
			
			if(studentDetails == null) {
				studentDetails = new HashMap<String, Student>();
			}
			
			response1 = new StudentCreationResponse();
			response1.setStudNo(String.valueOf(sno++));
			response1.setStudName(request.getStudName());
			response1.setStudAddress(request.getStudAdd());
			
			Student stud = new Student();
			stud.setSno(response1.getStudNo());
			stud.setsName(response1.getStudName());
			stud.setsAddr(request.getStudAdd());
			
			studentDetails.put(response1.getStudNo(), stud);
			
			}
		catch(Exception e)
		{
			LOGGER.error("Exception"+e);
			response1 = new StudentCreationResponse();
		}
		return response1;
	}
	
}
