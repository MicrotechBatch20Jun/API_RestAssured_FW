package TestCase;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class TC005_Delete_Employee_Record extends TestBase {

	@BeforeClass
	void delete_Employee() throws InterruptedException {

		// Specify the Base URI
		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";

		// Request Object
		httpRequest = RestAssured.given();

		// Sending Request
		response = httpRequest.request(Method.GET, "/employees");
		
		//first get the Jsonpath object instance from the response interface
		JsonPath json = response.jsonPath();
		
		String id = json.get("[0].id");
	
		response = httpRequest.request(Method.GET, "/delete/" + id);
				
		
		Thread.sleep(3000);
	}
	 
	
	@Test
	void verify_Status_Code() {
		int statusCode = response.getStatusCode();
		log.debug("Status Code is: " + statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}//class
