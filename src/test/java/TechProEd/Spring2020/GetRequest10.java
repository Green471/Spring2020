package TechProEd.Spring2020;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class GetRequest10 extends BaseTest01 {

	@Test
	public void getTest() {
		
		Response response = given().
				               spec(spec02).
                            when().
                               get();
		response.prettyPrint();
		
		JsonPath json = response.jsonPath();
		//To get all employee names
		System.out.println(json.getString("data.employee_name"));
		
		//To get 3rd employee name
		System.out.println(json.getString("data[2].employee_name"));
		
		//Soft Assertion
		SoftAssert softAssert = new SoftAssert();
		
		//Soft assert the name of first employee is Tiger Nixon
		softAssert.assertEquals(json.getString("data[0].employee_name"), "Tiger Nixon", "Name did not match");
		
		softAssert.assertAll();
			
	}
}
