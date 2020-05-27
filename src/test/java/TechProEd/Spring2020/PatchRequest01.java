package TechProEd.Spring2020;

import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class PatchRequest01 extends BaseTest01 {

	@Test
	public void patchTest() {
		
		//Get the data before updating partially
		Response response = given().
								spec(spec03).
							when().
								get("/200");
        response.prettyPrint();
        
        JSONObject jsonObject = new JSONObject(); 
        jsonObject.put("title", "Suleyman Alptekin");
        
        Response responseAfterPatch = given().
                                         contentType(ContentType.JSON).
                                         spec(spec03).
                                         body(jsonObject.toString()).
                                      when().
                                         patch("/200");
        
        responseAfterPatch.prettyPrint(); 
        
        responseAfterPatch.
                       then().
                       assertThat().
                       statusCode(200);
        
        JsonPath json = responseAfterPatch.jsonPath();
        
        SoftAssert softAssert = new SoftAssert();
        
        softAssert.assertEquals(json.getInt("userId"), 10,"User Id did not match");
        softAssert.assertEquals(json.getBoolean("completed"), false,"Completed did not match");
        softAssert.assertEquals(json.getString("title"), "Suleyman Alptekin","Title did not match");
        
        softAssert.assertAll();
   
	}
}
