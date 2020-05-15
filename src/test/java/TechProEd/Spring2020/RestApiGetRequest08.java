package TechProEd.Spring2020;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RestApiGetRequest08 extends BaseTest {

	//How to navigate in Json data
	
	@Test
	public void getTest03(){ 
		
	Response response = given().
					       spec(spec02).
				        when().
					       get();
	
	response.prettyPrint();
	
	JsonPath json = response.jsonPath();
	
	//Get all ids into a list
	List<String> ids01 = json.getList("data.id");
	System.out.println("All ids: " + ids01);
	//Assert the number of the ids
	assertEquals(ids01.size(),24);
	//Get all ids greater than 10
	List<String> ids02 = json.getList("data.findAll{Integer.valueOf(it.id)>10}.id");
	//"data.findAll{Integer.valueOf(it.id)>10}.id" that syntax comes from Java Groovy
	System.out.println("All ids gretaer than 10: " + ids02);
	
	//Get all employee ages into a list
	List<String> ages01 = json.getList("data.employee_age");
	System.out.println("All employee ages: " + ages01);
	//Assert the number of the employee ages
	assertEquals(ages01.size(),24);
	//Get all employee ages less than 30
	List<String> ages02 = json.getList("data.findAll{Integer.valueOf(it.employee_age)<30}.employee_age");
	System.out.println("All employee ages less than 30: " + ages02);
	//Assert max age less than 30 is 23
	Collections.sort(ages02);
	Collections.reverse(ages02);
	assertEquals("23",ages02.get(0));
	
	//Get all employee names into a list
	List<Integer> names = json.getList("data.employee_name");
	System.out.println("All employee names: " + names);
	//Get all employee names whose age is less than 30
	List<String> names01 = json.getList("data.findAll{Integer.valueOf(it.employee_age)<30}.employee_name");
	System.out.println("All employee names whose age is less than 30: " + names01);
	//Assert the number of the names
	assertEquals(names.size(),24);
	//Assert "Cedric Kelly" is among the names
	assertTrue(names.contains("Cedric Kelly"));
	}
}
