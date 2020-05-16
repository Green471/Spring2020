package TechProEd.Spring2020;

import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

public class RestApiPostRequest01 extends BaseTest {
	
	/*
	 * POST Scenario:
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
	
	// For POST Request we should use 
	//  1) Headers (Optional)
	//  2) Request body (Mandatory)

	// Note that; we do not send request body in GET Request.
	
	// Some keys are mandatory to send for POST Request, some are not. It depends on API
	// For that API "additionalneeds" are not mandatory but others mandatory.
	// If you do not send mandatory things you will get "400 Bad Request" message.
	
	@Test
	public void postTest01() {
		
		//1st Way: Using Json Format as String
		String jsonReqBody = "			{\n" + 
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
		
//		String jsonReqBody = "{\"firstname\":\"Suleyman\",\"lastname\": \"Alptekin\",\"totalprice\": 123,\"depositpaid\": true,\"bookingdates\": {\"checkin\": \"2020-05-02\",\"checkout\": \"2020-05-05\"},\"additionalneeds\": \"Wifi\"}";
		
		Response response = given().
				                auth().
				                basic("admin", "password123").
                                contentType(ContentType.JSON).
                                spec(spec01).
                                body(jsonReqBody).
                             when(). 
                                post("/booking");
		 response.prettyPrint();
		 
		 JsonPath json = response.jsonPath();
		 
	     //Soft Assertion
		 SoftAssert softAssert = new SoftAssert();
		 
		//Soft assert the status code is 200
		 softAssert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
		
		 //Soft assert the first name is Suleyman
		 softAssert.assertEquals(json.getString("booking.firstname"), "Suleyman", "First name did not match");
		 
		 //Soft assert the last name is Alptekin
		 softAssert.assertEquals(json.getString("booking.lastname"), "Alptekin", "Last name did not match");
		
		 //Soft assert total price is 123
		 softAssert.assertEquals(json.getString("booking.totalprice"), "123", "total price did not match");
		
		 //Soft assert deposit paid is true
		 softAssert.assertEquals(json.getString("booking.depositpaid"), "true", "Deposit paid did not match");
		 
		 //Soft assert checkin date is 2020-05-02
		 softAssert.assertEquals(json.getString("booking.bookingdates.checkin"), "2020-05-02", "checkin date did not match");
		 
		 //Soft assert checkout date is 2020-05-05
		 softAssert.assertEquals(json.getString("booking.bookingdates.checkout"), "2020-05-05", "checkout date did not match");
		 
		 //Soft assert additional needs are Wifi
		 softAssert.assertEquals(json.getString("booking.additionalneeds"), "Wifi", "Additional needs did not match");
		 
		 softAssert.assertAll();
	
	}
	
	@Test
	public void postTest02() {
		
		//2nd Way: Creating Json Object by using JSONObject Class		
		//         Add JSON Maven dependency ==> Update Maven Project ==> Save
		
		JSONObject jsonReqBody = new JSONObject();
		jsonReqBody.put("firstname", "Suleyman");
		jsonReqBody.put("lastname", "Alptekin");
		jsonReqBody.put("totalprice", 123);
		jsonReqBody.put("depositpaid", true);
		
		JSONObject jsonBookingdatesBody = new JSONObject();
		jsonBookingdatesBody.put("checkin", "2020-05-02");
		jsonBookingdatesBody.put("checkout", "2020-05-05");
		
		jsonReqBody.put("bookingdates", jsonBookingdatesBody);
		jsonReqBody.put("additionalneeds", "Wifi");
				
		Response response = given().
				                auth().
				                basic("admin", "password123").
                                contentType(ContentType.JSON).
                                spec(spec01).
                                body(jsonReqBody.toString()).
                             when(). 
                                post("/booking");
		 
		 response.prettyPrint();
		 
		 JsonPath json = response.jsonPath();
		 
	     //Soft Assertion
		 SoftAssert softAssert = new SoftAssert();
		 
		//Soft assert the status code is 200
		 softAssert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
		
		 //Soft assert the first name is Suleyman
		 softAssert.assertEquals(json.getString("booking.firstname"), "Suleyman", "First name did not match");
		 
		 //Soft assert the last name is Alptekin
		 softAssert.assertEquals(json.getString("booking.lastname"), "Alptekin", "Last name did not match");
		
		 //Soft assert total price is 123
		 softAssert.assertEquals(json.getString("booking.totalprice"), "123", "total price did not match");
		
		 //Soft assert deposit paid is true
		 softAssert.assertEquals(json.getString("booking.depositpaid"), "true", "Deposit paid did not match");
		 
		 //Soft assert checkin date is 2020-05-02
		 softAssert.assertEquals(json.getString("booking.bookingdates.checkin"), "2020-05-02", "checkin date did not match");
		 
		 //Soft assert checkout date is 2020-05-05
		 softAssert.assertEquals(json.getString("booking.bookingdates.checkout"), "2020-05-05", "checkout date did not match");
		 
		 //Soft assert additional needs are Wifi
		 softAssert.assertEquals(json.getString("booking.additionalneeds"), "Wifi", "Additional needs did not match");
		 
		 softAssert.assertAll();

	
	}
	
	@Test
	public void postTest03() {
		
		 //3rd Way: Put creating JSON Object codes into BaseTest Class		

		 Response response = createBookingJSONObject();
		 
		 response.prettyPrint();
		 
		 JsonPath json = response.jsonPath();
		 
	     //Soft Assertion
		 SoftAssert softAssert = new SoftAssert();
		 
		//Soft assert the status code is 200
		 softAssert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
		
		 //Soft assert the first name is Suleyman
		 softAssert.assertEquals(json.getString("booking.firstname"), "Suleyman", "First name did not match");
		 
		 //Soft assert the last name is Alptekin
		 softAssert.assertEquals(json.getString("booking.lastname"), "Alptekin", "Last name did not match");
		
		 //Soft assert total price is 123
		 softAssert.assertEquals(json.getString("booking.totalprice"), "123", "total price did not match");
		
		 //Soft assert deposit paid is true
		 softAssert.assertEquals(json.getString("booking.depositpaid"), "true", "Deposit paid did not match");
		 
		 //Soft assert checkin date is 2020-05-02
		 softAssert.assertEquals(json.getString("booking.bookingdates.checkin"), "2020-05-02", "checkin date did not match");
		 
		 //Soft assert checkout date is 2020-05-05
		 softAssert.assertEquals(json.getString("booking.bookingdates.checkout"), "2020-05-05", "checkout date did not match");
		 
		 //Soft assert additional needs are Wifi
		 softAssert.assertEquals(json.getString("booking.additionalneeds"), "Wifi", "Additional needs did not match");
		 
		 softAssert.assertAll();
	}
	
	@Test
	public void postTest04() {
		
		 //4th Way: Create Map for Request Body	
		
	    Map requestMap = new HashMap<>();

		requestMap.put("firstname", "Suleyman");
		requestMap.put("lastname", "Alptekin");
		requestMap.put("totalprice", 123);
		requestMap.put("depositpaid", true);
		
		Map bookingDatesMap = new HashMap<>();
		bookingDatesMap.put("checkin", "2020-05-02");
		bookingDatesMap.put("checkout", "2020-05-05");
		
		requestMap.put("bookingdates", bookingDatesMap);
		requestMap.put("additionalneeds", "Wifi");
		
		Response response = given().
				                auth().
				                basic("admin", "password123").
				                contentType(ContentType.JSON).
				                spec(spec01).
				                body(requestMap).
				            when(). 
				                post("/booking");	
		 
		 response.prettyPrint();
		 
		 Map responseMap = response.body().as(Map.class);
		 
	     //Soft Assertion
		 SoftAssert softAssert = new SoftAssert();
		 
		//Soft assert the status code is 200
		 softAssert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
		
		 //Soft assert the first name is Suleyman
		 softAssert.assertEquals(responseMap.get("booking.firstname"), requestMap.get("booking.firstname"), "First name did not match");
		 
		 //Soft assert the last name is Alptekin
		 softAssert.assertEquals(responseMap.get("booking.lastname"), requestMap.get("booking.lastname"), "Last name did not match");
		
		 //Soft assert total price is 123
		 softAssert.assertEquals(responseMap.get("booking.totalprice"), requestMap.get("booking.totalprice"), "total price did not match");
		
		 //Soft assert deposit paid is true
		 softAssert.assertEquals(responseMap.get("booking.depositpaid"), requestMap.get("booking.depositpaid"), "Deposit paid did not match");
		 
		 //Soft assert checkin date is 2020-05-02
		 softAssert.assertEquals(responseMap.get("booking.bookingdates.checkin"), requestMap.get("booking.bookingdates.checkin"), "checkin date did not match");
		 
		 //Soft assert checkout date is 2020-05-05
		 softAssert.assertEquals(responseMap.get("booking.bookingdates.checkout"), requestMap.get("booking.bookingdates.checkout"), "checkout date did not match");
		 
		 //Soft assert additional needs are Wifi
		 softAssert.assertEquals(responseMap.get("booking.additionalneeds"), requestMap.get("booking.additionalneeds"), "Additional needs did not match");
		 
		 softAssert.assertAll();
	}
	
	@Test
	public void postTest05() {
		
		 //5th Way: Creating Map for Request Body code will be stored in base Test Class	
	
		 Map requestMap = createRequestBookingMap();
		 Response response = createResponse();
		 
		 response.prettyPrint();
		 
		 Map responseMap = response.body().as(Map.class);

		 System.out.println(requestMap);
         System.out.println(responseMap);
         
	     //Soft Assertion
		 SoftAssert softAssert = new SoftAssert();
		 
		//Soft assert the status code is 200
		 softAssert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
		
		 //Soft assert the first name is Suleyman
		 softAssert.assertEquals(responseMap.get("booking.firstname"), requestMap.get("booking.firstname"), "First name did not match");
		 
		 //Soft assert the last name is Alptekin
		 softAssert.assertEquals(responseMap.get("booking.lastname"), requestMap.get("booking.lastname"), "Last name did not match");
		
		 //Soft assert total price is 123
		 softAssert.assertEquals(responseMap.get("booking.totalprice"), requestMap.get("booking.totalprice"), "total price did not match");
		
		 //Soft assert deposit paid is true
		 softAssert.assertEquals(responseMap.get("booking.depositpaid"), requestMap.get("booking.depositpaid"), "Deposit paid did not match");
		 
		 //Soft assert checkin date is 2020-05-02
		 softAssert.assertEquals(responseMap.get("booking.bookingdates.checkin"), requestMap.get("booking.bookingdates.checkin"), "checkin date did not match");
		 
		 //Soft assert checkout date is 2020-05-05
		 softAssert.assertEquals(responseMap.get("booking.bookingdates.checkout"), requestMap.get("booking.bookingdates.checkout"), "checkout date did not match");
		 
		 //Soft assert additional needs are Wifi
		 softAssert.assertEquals(responseMap.get("booking.additionalneeds"), requestMap.get("booking.additionalneeds"), "Additional needs did not match");
		 
		 softAssert.assertAll();
	}
}
