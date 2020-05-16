package TechProEd.Spring2020;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetRequest14 extends BaseTest01 {

	//GSON is converter. 
	//GSON is used to convert Json to Java Object. ==> DE-SERIALIZATION
	//GSON can be used to convert Java Objects to Json ==> SERIALIZATION
	
	@Test
	public void convertJsonToMap() {
		
		Response response = given().
                               spec(spec03).
                            when(). 
                               get("/2");
		response.prettyPrint();
		
		HashMap<String, String> map = response.as(HashMap.class); //DE-SERIALIZATION
		
		System.out.println(map);
		System.out.println(map.keySet());
		System.out.println(map.values());
		
		SoftAssert softAssert = new SoftAssert();
		
		//Assert that completed is false (Use soft assertion)
		softAssert.assertEquals(map.get("completed"), false);
		
		//Assert that user id is 1 (Use soft assertion)
		softAssert.assertEquals(map.get("userId"), 1.0);
		
		//Assert that title is "quis ut nam facilis et officia qui"
		softAssert.assertEquals(map.get("title"), "quis ut nam facilis et officia qui");
		
		softAssert.assertAll();
	
	}
	@Test
	public void convertJsonToListOfMaps() {
		
		Response response = given().
				                spec(spec03).
				            when(). 
				                get();
		response.prettyPrint();
		
		List<Map<String, String>> listOfMaps = response.as(ArrayList.class);//DE-SERIALIZATION
		System.out.println(listOfMaps);
		//Get the second map and print it on the console
		System.out.println(listOfMaps.get(1));
		System.out.println(listOfMaps.get(1).keySet());
		System.out.println(listOfMaps.get(1).values());	
		
		SoftAssert softAssert = new SoftAssert();
		//Assert that there are 200 ids
		softAssert.assertTrue(listOfMaps.size()==200);
		
		//Assert that 121st element's completed is true.
		softAssert.assertEquals(listOfMaps.get(120).get("completed"), true);
		
		//Assert that the last element's title is "ipsam aperiam voluptates qui"
		softAssert.assertEquals(listOfMaps.get(listOfMaps.size()-1).get("title"),"ipsam aperiam voluptates qui");	
	}

}
