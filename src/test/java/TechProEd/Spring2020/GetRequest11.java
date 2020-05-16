package TechProEd.Spring2020;

import static org.hamcrest.Matchers.*;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class GetRequest11 extends BaseTest01 {

	/*         Warm Up (15 Minutes)
	 * When I send a GET Request to URL
	 * http://dummy.restapiexample.com/api/v1/employees
	 * Then status code is 200
	 * And the name of 5th employee is "Airi Satou"
	 * And the salary of 6th employee is "372000"
	 * And there are "24" employees
	 * And "Rhona Davidson" is one of the employees
	 */
	@Test
	public void getTest() {
		
		Response response = given().
				               spec(spec02). 
				            when(). 
				               get();
                                		
		response.prettyPrint();
		
		//There are "24" employees and status code is 200 and Rhona Davidson is one of the employees
		//That part is hard assertion
		response.
             then(). 
                assertThat(). 
                statusCode(200).
                body("data.id", hasSize(24)).
                body("data.employee_name", hasItem("Rhona Davidson"));
                
		SoftAssert softAssert = new SoftAssert();
		
		JsonPath json = response.jsonPath();
		
		//The name of 5th employee is "Airi Satou"
		softAssert.assertEquals(json.getString("data[4].employee_name"), "Airi Satou", "Name did not match");
		
		//The salary of 6th employee is "372000"
		softAssert.assertEquals(json.getString("data[5].employee_salary"), "372000", "Salary did not match");
		
		//There are "24" employees with soft assertion(verification) 
		softAssert.assertEquals(json.getList("data.id").size(), 24);
		
		//Rhona Davidson is one of the employees
		softAssert.assertTrue(json.getList("data.employee_name").contains("Rhona Davidson"));
		
		softAssert.assertAll();

	}
}
