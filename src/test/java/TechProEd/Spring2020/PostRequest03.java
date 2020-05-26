package TechProEd.Spring2020;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PostRequest03 extends BaseTest01 {
	
	/*
	 *                POST Scenario:
	 * Given accept type is Json
	 * When I send a POST request to the URL
	 * https://restful-booker.herokuapp.com/booking
	 * with the request body 
			{
			    "firstname": "Suleyman",
			    "lastname": "Alptekin",
			    "totalprice": 123,
			    "depositpaid": true,
			    "bookingdates": {
			        "checkin": "2020-05-02",
			        "checkout": "2020-05-05"
			    },
			    "additionalneeds": "Wifi"
		    }
	 * Then status code should be 200
	 * And response body should match with the request body.
	*/

	//4th Way: To insert request body into our script
	//         We will create a map and will use the map for the request body
	
	@Test
	public void postTest04() {
		
		Map requestMap = new HashMap<>();
		
		requestMap.put("firstname", "Suleyman");
		requestMap.put("lastname", "Alptekin");
		requestMap.put("totalprice", 123);
		requestMap.put("depositpaid", true);
		
		Map bookingDatesMap = new HashMap<>();
		bookingDatesMap.put("checkin", "2020-05-02");
		bookingDatesMap.put("checkout", "2020-05-05");
		
		requestMap.put("bookingdates",bookingDatesMap);
		requestMap.put("additionalneeds", "Wifi");
		
		Response response = given(). 
	               contentType(ContentType.JSON). // or "application/json"
	               spec(spec01).
	               auth().
	               basic("admin","password123").
	               body(requestMap).
	            when(). 
	               post("/booking");
		
		response.prettyPrint(); 
		
		//For status code use hard assertion
		response.
		then().
		assertThat(). 
		statusCode(200);

        //I need JsonPath object to navigate in Response which is in Json Format
        JsonPath json = response.jsonPath();
        
        //I need soft assert object for soft Assertion
        SoftAssert softAssert = new SoftAssert();
        
        //Assert the first name is Suleyman
        softAssert.assertEquals(json.get("booking.firstname"), "Suleyman", "Firstname did not match");
        
        //Assert the last name is Alptekin
        softAssert.assertEquals(json.get("booking.lastname"), "Alptekin", "Lastname did not match");
        
        //Assert the total price is 123
        softAssert.assertEquals(json.get("booking.totalprice"), 123, "Total price did not match");
        
        //Assert the deposit paid is true
        softAssert.assertEquals(json.get("booking.depositpaid"), true, "Deposit paid did not match");
        
        //Assert the checkin date is 2020-05-02
        softAssert.assertEquals(json.get("booking.bookingdates.checkin"), "2020-05-02", "Checkin date did not match");
        
        //Assert the checkout date is 2020-05-05
        softAssert.assertEquals(json.get("booking.bookingdates.checkout"), "2020-05-05", "Checkout date did not match");
        
        //Assert the additional needs are Wifi
        softAssert.assertEquals(json.get("booking.additionalneeds"), "Wifi", "additional needs did not match");
        
        softAssert.assertAll();
	
	}
	@Test
	public void postTest05() {
		
		Response response = createMapForRequestBody();
		
		response.prettyPrint(); 
		
		//For status code use hard assertion
		response.
		then().
		assertThat(). 
		statusCode(200);

        //I need JsonPath object to navigate in Response which is in Json Format
        JsonPath json = response.jsonPath();
        
        //I need soft assert object for soft Assertion
        SoftAssert softAssert = new SoftAssert();
        
        //Assert the first name is Suleyman
        softAssert.assertEquals(json.get("booking.firstname"), "Suleyman", "Firstname did not match");
        
        //Assert the last name is Alptekin
        softAssert.assertEquals(json.get("booking.lastname"), "Alptekin", "Lastname did not match");
        
        //Assert the total price is 123
        softAssert.assertEquals(json.get("booking.totalprice"), 123, "Total price did not match");
        
        //Assert the deposit paid is true
        softAssert.assertEquals(json.get("booking.depositpaid"), true, "Deposit paid did not match");
        
        //Assert the checkin date is 2020-05-02
        softAssert.assertEquals(json.get("booking.bookingdates.checkin"), "2020-05-02", "Checkin date did not match");
        
        //Assert the checkout date is 2020-05-05
        softAssert.assertEquals(json.get("booking.bookingdates.checkout"), "2020-05-05", "Checkout date did not match");
        
        //Assert the additional needs are Wifi
        softAssert.assertEquals(json.get("booking.additionalneeds"), "Wifi", "additional needs did not match");
        
        softAssert.assertAll();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
