package TechProEd.Spring2020;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RestApiPutRequest01 extends BaseTest01  {

		@Test
		public void putTest01(){
			
			//1. Step: Create a booking	and see it on the console
			Response responseCreated = createResponseForPost();
			responseCreated.prettyPrint();
			 
			// Create JsonPath object to navigate in created Json Format
			JsonPath jsonForCreated = responseCreated.jsonPath();
			 
			//2. Step: Get booking id
			int bookingId = jsonForCreated.getInt("bookingid");
			System.out.println(bookingId);
			 
			//3. Step: Create updated JSON body
			JSONObject jsonReqBody = new JSONObject();
			jsonReqBody.put("firstname","Selim");
			jsonReqBody.put("lastname","Alptekin");
			jsonReqBody.put("totalprice",888);
			jsonReqBody.put("depositpaid",false);
			
			JSONObject jsonBookingDatesBody = new JSONObject();
			jsonBookingDatesBody.put("checkin", "2020-05-02");
			jsonBookingDatesBody.put("checkout", "2020-05-05");
			
			jsonReqBody.put("bookingdates",jsonBookingDatesBody);
			jsonReqBody.put("additionalneeds","Wifi");
			
			//4. Step: Update the booking
			Response responseForUpdated = given(). 
		               auth().
		               preemptive().
		               basic("admin","password123").
		               contentType(ContentType.JSON). // "application/json"
		               spec(spec01).
		               body(jsonReqBody.toString()).
		            when(). 
		               put("/booking/" + bookingId);
			
			responseForUpdated.prettyPrint();
			
			// Create JsonPath object to navigate in updated Json Format
			JsonPath jsonForUpdated = responseForUpdated.jsonPath();
	         
		     //Soft Assertion
			 SoftAssert softAssert = new SoftAssert();
			 
			//Soft assert the status code is 200
			 softAssert.assertEquals(responseForUpdated.getStatusCode(), 200,  "Status code is not 200");
			
			 //Soft assert the first name is Selim
			 softAssert.assertEquals(jsonForUpdated.getString("firstname"), "Selim", "First name did not match");
			 
			 //Soft assert the last name is Alptekin
			 softAssert.assertEquals(jsonForUpdated.getString("lastname"), "Alptekin", "Last name did not match");
			
			 //Soft assert total price is 888
			 softAssert.assertEquals(jsonForUpdated.getInt("totalprice"), 888, "total price did not match");
			
			 //Soft assert deposit paid is false
			 softAssert.assertEquals(jsonForUpdated.getBoolean("depositpaid"), false, "Deposit paid did not match");
			 
			 //Soft assert checkin date is 2020-05-02
			 softAssert.assertEquals(jsonForUpdated.getString("bookingdates.checkin"), "2020-05-02", "checkin date did not match");
			 
			 //Soft assert checkout date is 2020-05-05
			 softAssert.assertEquals(jsonForUpdated.getString("bookingdates.checkout"), "2020-05-05", "checkout date did not match");
			 
			 //Soft assert additional needs are Wifi
			 softAssert.assertEquals(jsonForUpdated.getString("additionalneeds"), "Wifi", "Additional needs did not match");
			 
			 softAssert.assertAll();
		}
}


