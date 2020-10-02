package TestCase;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import io.restassured.RestAssured;
//import io.restassured.authentication.PreemptiveBasicAuthScheme;
//import io.restassured.http.Header;
//import io.restassured.http.Headers;
import io.restassured.http.Method;
import junit.framework.Assert;

public class TC002_Get_All_Employees extends TestBase {

	@BeforeClass
	void get_All_Employees() throws InterruptedException {

		// Specify the Base URI
		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";

		// Request Object
		httpRequest = RestAssured.given();

		// Sending Request
		response = httpRequest.request(Method.GET, "/employees");
		
		Thread.sleep(3000);
	}

	@Test
	void Verify_Response_Body() {
		String responseBody = response.getBody().asString();			 // JSON to String
		log.debug("Response Body is: " + responseBody); 
		Assert.assertTrue(responseBody.contains("success"));
	}

	@Test
	void verify_Status_Code() {
		int statusCode = response.getStatusCode();
		log.debug("Status Code is: " + statusCode);
		Assert.assertEquals(statusCode, 200);
	}

	

}// class

// AuthorizationTest

// PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();

// authScheme.setUserName("java2020");
// authScheme.setPassword("12345");

// RestAssured.authentication = authScheme;

//@Test
//void verify_All_Headers() {
//	Headers allheaders = response.headers();
//
//	for (Header header : allheaders) {
//		log.debug(header.getName() + "  -  " + header.getValue());
//	}

//}
