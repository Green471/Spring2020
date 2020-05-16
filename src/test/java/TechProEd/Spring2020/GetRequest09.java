package TechProEd.Spring2020;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class GetRequest09 extends BaseTest01 {
	
    //JsonPath ==> It is used to navigate in Json Format Data
	
	/*
	 * When I send a GET request to REST API URL
	 * https://restful-booker.herokuapp.com/booking/5 
	 * Then HTTP Status Code should be 200
	 * And response content type is “application/JSON” 
	 * And response body should be like; 
	 * {"firstname": "Sally", "lastname": "Ericsson", "totalprice": 111,
	 * "depositpaid": false, "bookingdates": { "checkin": "2017-05-23", "checkout":"2019-07-02" }
	 * }
	*/
	@Test
	public void getTest() {
		
		Response response = given().
                                spec(spec01).
                            when().
                                get("/booking/5");
		response.prettyPrint();
		
		JsonPath json = response.jsonPath();
		
		System.out.println(json.getString("firstname"));//return the value of first name
		//You can use 3 parameters; 1)Message for non-technicals when the test fails 
		//                          2)Expected result 3)Actual result
		assertEquals("First name did not match", "Eric", json.getString("firstname"));
				
		System.out.println(json.getString("totalprice")); //return 390
		assertEquals("Total price is not as expected","776",json.getString("totalprice"));
			
		System.out.println(json.getString("bookingdates.checkin"));//2018-04-08
		assertEquals("Checkin date is not as expected","2017-07-06",json.getString("bookingdates.checkin"));	
	}	
}
