package TechProEd.Spring2020;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RestApiGetRequest11 extends BaseTest {

	@Test
	public void assertDataWithLoop() {
		Response response = given().
				                 spec(spec02).
				            when().
				                 get();

		response.prettyPrint();

		//Assert that first five employee names are Tiger Nixon, Garrett Winters,
		//Ashton Cox, Cedric Kelly, Airi Satou
		//Assert status code is 200
		
		JsonPath json = response.jsonPath();
		
		//1st Way:
		assertEquals(200,response.statusCode());
		assertEquals("Tiger Nixon", json.get("data[0].employee_name"));
		assertEquals("Garrett Winters", json.get("data[1].employee_name"));
		assertEquals("Ashton Cox", json.get("data[2].employee_name"));
		assertEquals("Cedric Kelly", json.get("data[3].employee_name"));
		assertEquals("Airi Satou", json.get("data[4].employee_name"));
		
		
		//2nd Way:
		assertEquals(200,response.statusCode());
		List<String> expectedNamesInList = new ArrayList<>();
		expectedNamesInList.add("Tiger Nixon");
		expectedNamesInList.add("Garrett Winters");
		expectedNamesInList.add("Ashton Cox");
		expectedNamesInList.add("Cedric Kelly");
		expectedNamesInList.add("Airi Satou");
		
		for(int i=0; i<5; i++) {
			assertEquals(expectedNamesInList.get(i),json.getList("data.employee_name").get(i));
		}
		
		//3rd Way
		assertEquals(200,response.statusCode());
		List<Map> dataInMap = json.getList("data", Map.class);
		System.out.println(dataInMap);
		
		Map<Integer, String> expectedNamesInMap = new HashMap<>();
		expectedNamesInMap.put(0, "Tiger Nixon");
		expectedNamesInMap.put(1, "Garrett Winters");
		expectedNamesInMap.put(2, "Ashton Cox");
		expectedNamesInMap.put(3, "Cedric Kelly");
		expectedNamesInMap.put(4, "Airi Satou");
		
		System.out.println(expectedNamesInMap);
		for(int i=0; i<5; i++) {
			//System.out.println(expectedNamesInMap.get(i) + " <=> " + dataInMap.get(i).get("employee_name"));
			assertEquals(expectedNamesInMap.get(i),dataInMap.get(i).get("employee_name"));
		}
	
	}
}
