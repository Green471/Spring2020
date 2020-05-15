package TechProEd.Spring2020;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;


public class RestApiGetRequest10 extends BaseTest {
		
	//How to convert Json to Java object
	//1) Add GSON dependency from Maven Repository
	//   GSON: GSON is a converter.GSON is used to convert Json to a Java Object and 
	//         to convert an Java Object to Json. 
	//Note 1: The process of converting from JSON to JAVA OBJECT is called "DE-SERIALIZATION"
	//Note 2: The process of converting from JAVA OBJECT to JSON is called "SERIALIZATION"
	
	@Test
	public void convertJsonFileToHashMap() {

	Response response = given().
			                spec(spec03).	
                        when().
                            get("/2");
	response.prettyPrint();
	
	//To convert JSON to a HASHMAP JAVA OBJECT use as() method.(DE-SERIALIZATION)
	//You will need to import HashMap class
	HashMap<String, String> map = response.as(HashMap.class);
	System.out.println(map);
	System.out.println(map.keySet());
	System.out.println(map.values());
	
	assertEquals(1.0, map.get("userId"));
	assertEquals(false,map.get("completed"));
	assertTrue("quis ut nam facilis et officia qui".equals(map.get("title")));
	assertFalse("TechPro Education".equals(map.get("title")));
	
	}
	
	@Test
	public void convertJsonFileToListOfMaps() {

	Response response = given().
			                spec(spec03).	
                        when().
                            get();
	response.prettyPrint();
	
	List<Map<String,String>> listOfMaps = response.as(ArrayList.class);
	System.out.println(listOfMaps);
	System.out.println(listOfMaps.get(1));
	System.out.println(listOfMaps.get(1).keySet());
	System.out.println(listOfMaps.get(1).values());
	
	assertEquals(1.0,listOfMaps.get(1).get("userId"));
	assertEquals("quis ut nam facilis et officia qui",listOfMaps.get(1).get("title"));
	assertEquals(2.0,listOfMaps.get(1).get("id"));
	assertEquals(false,listOfMaps.get(1).get("completed"));
			
	}
}
