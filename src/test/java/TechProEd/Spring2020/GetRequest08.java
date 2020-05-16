package TechProEd.Spring2020;

import org.junit.Test;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class GetRequest08 extends BaseTest01 {
	
	//Among the data there are someones whose first name is "Susan"
	
	//1st Way without using Base Test class (It is not recommended)
	@Test
	public void getTest01() {
		Response response = given().
				            when().
                               get("https://restful-booker.herokuapp.com/booking?firstname=Susan");  
		response.prettyPrint();
	}
	
	//2nd Way with Base Test Class
	@Test
	public void getTest02() {
		
		spec01.queryParam("firstname", "Mary");
		spec01.queryParam("lastname", "Smith");
		
		Response response = given().
                               spec(spec01).
                            when(). 
                               get("/booking");
		
		response.prettyPrint();	
	}
}
