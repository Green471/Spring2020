package TechProEd.Spring2020;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PutRequest02 extends BaseTest01 {

	@Test
	public void putTestWithPojo() {
		
		        //1. Step: Get the data before updating
				Response response = given().
						               spec(spec03).
						            when().
						               get("/198");
				response.prettyPrint();
				
				//2. Step: Create a JSONObject object
				Todo pojoRequestBody = new Todo(33, 198, "TechPro Education", false);
				
				//3. Step: Create put request to update the data whose id is 198
				Response responseForPut = given().
						                     contentType(ContentType.JSON).
		                                     spec(spec03).
		                                     body(pojoRequestBody).	
		                                  when().
		                                     put("/198");
				
				//Response after updating
				responseForPut.prettyPrint();
				
				responseForPut.
				           then().
				           assertThat().
				           statusCode(200);
				
				JsonPath json = responseForPut.jsonPath();
				
				SoftAssert softAssert = new SoftAssert();
				
				softAssert.assertEquals(json.getBoolean("completed"), false, "Completed is not updated");
				softAssert.assertEquals(json.getInt("userId"), 33, "User Id is not updated");
				softAssert.assertEquals(json.getString("title"), "TechPro Education", "Title is not updated");
				
				softAssert.assertAll();				               
	}
}
