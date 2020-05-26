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
			
			//0. Step: Get the data before updating
			Response responseBeforeUpdated = given().
					                           spec(spec03).
								             when(). 
								               get("/" + 198);
			responseBeforeUpdated.prettyPrint();
			 
			//1. Step: Create updated JSON body
			JSONObject jsonReqBody = new JSONObject();
			jsonReqBody.put("userId",13);
			jsonReqBody.put("title","TechProEd");
			jsonReqBody.put("completed",false);
			
			//2. Step: Update the booking
			Response responseForUpdated = given(). 
								               contentType(ContentType.JSON). 
								               spec(spec03).
								               body(jsonReqBody.toString()).
								            when(). 
								               put("/" + 198);
			
			responseForUpdated.prettyPrint();
			
			// Create JsonPath object to navigate in updated Json Format
			JsonPath jsonForUpdated = responseForUpdated.jsonPath();
	         
		     //Soft Assertion
			 SoftAssert softAssert = new SoftAssert();
			 
			//Soft assert the status code is 200
			 softAssert.assertEquals(responseForUpdated.getStatusCode(), 200,  "Status code is not 200");
			
			 //Soft assert the completed is false
			 softAssert.assertEquals(jsonForUpdated.getBoolean("completed"), false, "completed did not match");
			 
			 //Soft assert the title is TechProEd
			 softAssert.assertEquals(jsonForUpdated.getString("title"), "TechProEd", "Title did not match");
			
			 //Soft assert the User Id is 13
			 softAssert.assertEquals(jsonForUpdated.getInt("userId"), 13, "User Id did not match");
			
			 softAssert.assertAll();
		}
}


