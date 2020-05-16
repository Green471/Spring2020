package TechProEd.Spring2020;

import static org.hamcrest.Matchers.*;
import org.junit.Test;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class GetRequest06{
	
	/* Warm Up (13 Minutes)
	  
	   1)Create a class whose name is "GetRequest06" 
	   under src/test/java and in TechProEd.Spring2020 
	   2)When I send a GET request to REST API URL 
		 https://restful-booker.herokuapp.com/booking/5   
	     Then HTTP Status Code should be 200
	     And response content type is “application/JSON”
	     And "firstname" should be "Jim",
	     And "totalprice" should be 602
		 And "checkin" should be "2015-06-12"		 
	*/
	
	@Test
	public void getTest01() {
		
		Response response = given(). 
				            when().
				               get("https://restful-booker.herokuapp.com/booking/5");
		response.prettyPrint();
		
		response.
		     then().
		         assertThat().
		         statusCode(200).
		         contentType("application/JSON").//ContentType.JSON
		         body("firstname", equalTo("Mary")).
		         body("totalprice", equalTo(182)).
		         body("bookingdates.checkin", equalTo("2016-06-15"));
	}
}
