package TechProEd.Spring2020;

import org.junit.Test;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class DeleteRequest01 extends BaseTest01 {

	@Test
	public void deleteTest01() {
	//Get the data before deleting
	Response response = given().
			                spec(spec03).
                        when().
                            get("/198");	
	response.prettyPrint();
	
	//Delete the data by using delete()
	Response responseAfterDelete = given().
	                                   spec(spec03).
	                               when().
	                                   delete("/198"); 
	responseAfterDelete.prettyPrint();
	
	responseAfterDelete.
	                then().
	                assertThat().
	                statusCode(200);	
	}		
}
