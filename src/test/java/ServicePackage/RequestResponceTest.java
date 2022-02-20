package ServicePackage;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import MainPackage.ExcelUtility;
import MainPackage.Helper;
import MainPackage.baseClass;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import okhttp3.ResponseBody;

public class RequestResponceTest {
	Helper hp;

	/**
	 * Launch the Application
	 **/
	@BeforeMethod
	public void setup() {
		baseClass.initilize();
	}

	/**
	 * Add a New ToDo from WebUI and Newly added ToDo Item validate with API JSon
	 * response, Means Search the ToDo from existing ToDo List using ResAsured.
	 **/
	@Test
	public void ValidateAPIResponce() {

		hp = new Helper(baseClass.driver);
		String todo = baseClass.prop.getProperty("ToDoApi");
		Reporter.log("Add New Todo to existing list " + todo);
		hp.AddTodos(todo);
		// Specify the base URL to the RESTful web service
		String baseURL = "https://mysterious-thicket-31854.herokuapp.com/#/active";

		Reporter.log(baseURL);
		RestAssured.baseURI = baseURL;
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.header("Content-Type", "application/json");
		// specify the method type (GET)
		Response response = httpRequest.request(Method.GET, "");
		// ResponseBody body = (ResponseBody) response.getBody();
		String getResponce = response.getBody().asString();
		Reporter.log(getResponce);

		Assert.assertEquals(getResponce.toLowerCase().contains("title") /* Expected value */, true /* Actual Value */,
				"Response body contains Test New");
		boolean flag = JsonResponceCheck(response, todo);

		Assert.assertEquals(flag, true);

	}

	/**
	 * JSON Resonse comparision
	 * 
	 * @param response
	 * @param todo
	 * @return
	 */
	public boolean JsonResponceCheck(Response response, String todo) {
		JsonPath jsonPathEvaluator = response.jsonPath();
		boolean flag = false;
		List<String> ToDoName = jsonPathEvaluator.get("title");

		for (String lst : ToDoName) {

			Reporter.log(lst);

			if (lst.equalsIgnoreCase(todo)) {
				flag = true;

				break;
			} else
				flag = false;

		}
		return flag;
	}
/**
 * Close all browser Instances
 */
	@AfterTest
	public void Cleartest() {
		baseClass.driver.quit();

	}
}
