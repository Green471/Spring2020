package TechProEd.Spring2020;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RestApiPatchRequest01 extends BaseTest01 {

	@Test
	public void patchTest01(){
		
		//0. Step: Get the data before updating
		Response responseBeforeUpdated = given().
				                           spec(spec03).
							             when(). 
							               get("/" + 198);
		responseBeforeUpdated.prettyPrint();
		
		//1. Step: Create partially updated JSON body
		JSONObject jsonReqBody = new JSONObject();
		jsonReqBody.put("title","SDET or QA");
		
		//2. Step: Update the booking
		Response responseForPartialUpdate = given(). 
								               contentType(ContentType.JSON). 
								               spec(spec03).
								               body(jsonReqBody.toString()).
								            when(). 
								               patch("/" + 198);
		
		responseForPartialUpdate.prettyPrint();
		
		// Create JsonPath object to navigate in updated Json Format
		JsonPath jsonForUpdated = responseForPartialUpdate.jsonPath();
         
	     //Soft Assertion
		 SoftAssert softAssert = new SoftAssert();
		 
		//Soft assert the status code is 200
		 softAssert.assertEquals(responseForPartialUpdate.getStatusCode(), 200,  "Status code is not 200");
		
		 //Soft assert the completed is false
		 softAssert.assertEquals(jsonForUpdated.get("completed"), true, "completed did not match");
		 
		 //Soft assert the title is TechProEd
		 softAssert.assertEquals(jsonForUpdated.get("title"), "SDET or QA", "Title did not match");
		
		 //Soft assert the User Id is 13
		 softAssert.assertEquals(jsonForUpdated.get("userId"), 10, "User Id did not match");
		
		 softAssert.assertAll();
	}
}
