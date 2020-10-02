package TestCase;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import junit.framework.Assert;
import utilities.RestUtils;

public class TC004_Put_Employee_Record extends TestBase {

	String name = RestUtils.empName();
	String salary = RestUtils.empSalary();
	String age = RestUtils.empAge();

	@BeforeClass
	void update_Employee_Data() throws InterruptedException {

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
		response = httpRequest.request(Method.GET, "/update/" + id);

		Thread.sleep(3000);

	}

	@Test
	void Verify_Response_Body() {
		String responseBody = response.getBody().asString(); // JSON to String
		log.debug("Response Body is: " + responseBody);
		Assert.assertEquals(responseBody.contains("Successfully! Record has been updated."), true);
	}

	@Test
	void verify_Status_Code() {
		int statusCode = response.getStatusCode();
		log.debug("Status Code is: " + statusCode);
		Assert.assertEquals(statusCode, 200);
	}

}// class
