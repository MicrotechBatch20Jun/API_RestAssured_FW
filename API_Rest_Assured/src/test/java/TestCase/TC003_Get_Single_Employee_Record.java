package TestCase;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC003_Get_Single_Employee_Record extends TestBase {

	@BeforeClass
	void get_Employee_Data() throws InterruptedException {

		//Specify the Base URI
		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";

		//Request Object
		httpRequest = RestAssured.given();

		//Sending Request
		response = httpRequest.request(Method.GET, "/employee/" + id);	
		
		Thread.sleep(3000);

	}

	@Test
	void Verify_Response_Body() {
		String responseBody = response.getBody().asString();			 // JSON to String
		log.debug("Response Body is: " + responseBody); 
		Assert.assertEquals(responseBody.contains(id), true);
	}

	@Test
	void verify_Status_Code() {
		int statusCode = response.getStatusCode();
		log.debug("Status Code is: " + statusCode);
		Assert.assertEquals(statusCode, 200);
	}



}//class
