package TechProEd.Spring2020;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GetRequest12 extends BaseTest01 {

	/*
	 * When I send GET Request to URL
	 * http://dummy.restapiexample.com/api/v1/employees
	 * Then
	 * Status code is 200
	 *  1)Print all ids greater than 10 on the console
	 *  Assert that there are 14 ids greater than 10
	 *  2)Print all ages less than 30 on the console
	 *  Assert that maximum age is 23
	 *  3)Print all employee names whose salaries are greater than 350000 
	 *  Assert that Charde Marshall is one of the employees whose salary is greater than 350,000
	 */
	@Test
	public void getTest() {
		
		Response response = given().
				               spec(spec02). 
				            when().  
				               get();
		response.prettyPrint();
		
		//Status code is 200 ==> Hard Assertion
		response.
              then().
              assertThat().	
              statusCode(200);
		
		JsonPath json = response.jsonPath();
		SoftAssert softAssert = new SoftAssert();
		
		//1)Print all ids greater than 10 on the console
		List<String> idList = json.getList("data.findAll{Integer.valueOf(it.id)>10}.id");
		System.out.println(idList); //[11, 12, 13, ... , 24]
		//Assert that there are 14 ids greater than 10
		softAssert.assertEquals(idList.size(), 14);
		
		//2)Print all ages less than 30 on the console
		List<String> ageList = json.getList("data.findAll{Integer.valueOf(it.employee_age)<30}.employee_age");
		System.out.println(ageList);
		//Assert that maximum age less than 30 is 23
		// First Step: Convert String ages to integer and put them into an integer list
		List<Integer> ageListInt = new ArrayList<>();
		for(int i=0; i<ageList.size(); i++) {
			ageListInt.add(Integer.valueOf(ageList.get(i)));
		}
		System.out.println(ageListInt);
		//Second Step: Sort elements in ascending order
		Collections.sort(ageListInt);
		System.out.println(ageListInt);
		//Assert that the last(max) element is 23
		softAssert.assertTrue(ageListInt.get(ageListInt.size()-1)==23);
		
		//3)Print all employee names whose salaries are greater than 350,000 
		List<String> nameList = json.getList("data.findAll{Integer.valueOf(it.employee_salary)>350000}.employee_name");
		System.out.println(nameList);
		//Assert that Charde Marshall is one of the employees whose salary is greater than 350,000
		softAssert.assertTrue(nameList.contains("Charde Marshall"));

		softAssert.assertAll();
        
	}
}
