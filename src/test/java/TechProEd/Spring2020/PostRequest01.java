package TechProEd.Spring2020;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class PostRequest01 extends BaseTest01 {
	
	//To create a Post Request we need to do the followings
	//1) Headers ==> Content Type (Optional) ==> About request data
	//           ==> Authorization(Mandatory if it is not arranged)
	//           ==> Accept Type ==> (Optional) ==> About response data
	//2) Request Body ==> Mandatory
	
	// What is the difference between GET and POST requests?
	//  ==> GET Request does not need body but POST request must have body
	
	// Some keys can be mandatory for request body, if you do not enter data for the mandatory
	// keys you will get "400 Bad Request" status code.
	
	// API developers can make some values unique.For the unique values if you create one more
	// data by sending POST Request again you will get "400 Bad Request"
	// ==> If there is unique values in data base check if your data is duplicated or not 
	//     by using postman
	// ==> If there is no any problem to delete data from data base delete the duplicated data then
	//     create your POST Request. To delete data you can use SQL Delete Command. To delet data 
	//     you can use HTTP Delete Method
	
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
	@Test
	public void postTest01() {
		
		//1st Way to add request body into our script
		String jsonReqBody = "{\n" + 
				"			    \"firstname\": \"Suleyman\",\n" + 
				"			    \"lastname\": \"Alptekin\",\n" + 
				"			    \"totalprice\": 123,\n" + 
				"			    \"depositpaid\": true,\n" + 
				"			    \"bookingdates\": {\n" + 
				"			        \"checkin\": \"2020-05-02\",\n" + 
				"			        \"checkout\": \"2020-05-05\"\n" + 
				"			    },\n" + 
				"			    \"additionalneeds\": \"Wifi\"\n" + 
				"		    }";
		
		Response response = given(). 
				               contentType(ContentType.JSON). // "application/json"
				               spec(spec01).
				               auth().
                               basic("admin","password123").
                               body(jsonReqBody).
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
}
