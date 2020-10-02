package TestCase;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import junit.framework.Assert;
import utilities.RestUtils;

public class TC001_Post_Request extends TestBase {
	
	String name = RestUtils.empName();
	String salary = RestUtils.empSalary();
	String age = RestUtils.empAge();

	@BeforeClass
	void Register_Employee() throws InterruptedException {

		// Specify the Base URI (Uniform Resourse Identifier)
		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";

		// Request Object
		httpRequest = RestAssured.given();

		JSONObject requestPrams = new JSONObject();

		requestPrams.put("name", name);
		requestPrams.put("salary", salary);
		requestPrams.put("age", age);
	
		httpRequest.header("Content-Type", "application/json"); // Specify Content Type

		httpRequest.body(requestPrams.toJSONString()); // attach above data to the request

		// Sending Request
		response = httpRequest.request(Method.POST, "/create");
		
		Thread.sleep(300);

	}

	@Test
	void Verify_Response_Body() {
		String responseBody = response.getBody().asString(); 				// JSON to String
		log.debug("Response Body is: " + responseBody); 
		Assert.assertEquals(responseBody.contains(name), true);
		Assert.assertEquals(responseBody.contains(salary), true);
		Assert.assertEquals(responseBody.contains(age), true);
	}

	@Test
	void verify_Status_Code() {
		int statusCode = response.getStatusCode();
		log.debug("Status Code is: " + statusCode);
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	void verify_Status_Line() {
		String statusLine = response.getStatusLine();
		log.debug("Staus Line is: " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

	@Test
	void verify_Content_Type() {
		String contentType = response.header("Content-Type");
		log.debug("Content-Type is: " + contentType);
		Assert.assertEquals(contentType, "application/json");
	}

}// class
