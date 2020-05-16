package TechProEd.Spring2020;

import org.hamcrest.Matchers;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class GetRequest07 extends BaseTest01 {

	/*
		When I send a GET request to REST API URL 
		https://restful-booker.herokuapp.com/booking/5   
	    Then HTTP Status Code should be 200
	    And response content type is “application/JSON”
	    And response body should be like; 
	    {
		    "firstname": "Sally",
		    "lastname": "Ericsson",
		    "totalprice": 111,
		    "depositpaid": false,
		    "bookingdates": {
		        "checkin": "2017-05-23",
		        "checkout": "2019-07-02"
		     }
	    }
	*/
	@Test
	public void getTest01() {
		spec01.pathParam("bookingid", 5);
		
		//For response use given() and when(); for the assertion use then()
		Response response = given().
				               spec(spec01).
				            when().
				               get("/booking/{bookingid}");
		
		response.prettyPrint();
		
		response.
		     then(). 
		     assertThat(). 
		     statusCode(200).
		     contentType(ContentType.JSON).//application/Json
		     body("firstname",Matchers.equalTo("Sally")).
		     body("bookingdates.checkout",Matchers.equalTo("2019-03-21") );
		     
	}
	
	
	
	
	
	
}
