package TechProEd.Spring2020;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RestApiDeleteRequest01 extends BaseTest01 {

	@Test
	public void putTest01(){
		
		//0. Step: Get the data before deleting
		Response responseBeforeDeleted = given().
				                           spec(spec03).
							             when(). 
							               get("/" + 198);
		responseBeforeDeleted.prettyPrint();
		
		//2. Step: Delete the data
		Response responseForDelete = given(). 
							             spec(spec03).
							         when(). 
							             delete("/" + 198);
		
		responseForDelete.prettyPrint();
		
		//Hard Assertion
		responseForDelete.
		           then().
		           assertThat().
		           statusCode(200);
         
	    //Soft Assertion
		SoftAssert softAssert = new SoftAssert();
		 
		//Soft assert the status code is 200
		softAssert.assertEquals(responseForDelete.getStatusCode(), 200,  "Status code is not 200");
		
		softAssert.assertAll();
	}
}
