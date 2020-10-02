package base;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.ExcelReader;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

public class TestBase {

	public static RequestSpecification httpRequest;
	public static Response response;	
	public static String projectPath;
	public static ExcelReader excel;
	public Logger log;
	public String id = "5";

	@BeforeClass
	public void setup() {
		
		projectPath = System.getProperty("user.dir");

		log = Logger.getLogger("EmployeeRestAPI");
		
		PropertyConfigurator.configure("Log4j.properties");
		
		log.setLevel(Level.DEBUG);

		excel = new ExcelReader(System.getProperty("user.dir") + "\\src\\test\\resources\\data\\API.xlsx");

	}// setup

}// class