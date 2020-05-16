package TechProEd.Spring2020;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetRequest15 extends BaseTest01 {
     
	//Assert that the names of first 5 employees are Tiger Nixon, Garrett Winters,
	//Ashton Cox, Cedric Kelly, Airi Satou
	@Test
	public void assertDataWithLoop() {
		
		Response response = given().
                               spec(spec02).
                            when(). 
                               get();
		response.prettyPrint();
		
		JsonPath json = response.jsonPath();
		SoftAssert softAssert = new SoftAssert();
		
		//1st Way for assertion of first five employee names
		softAssert.assertEquals(json.get("data[0].employee_name"), "Tiger Nixon");
		softAssert.assertEquals(json.get("data[1].employee_name"), "Garrett Winters");
		softAssert.assertEquals(json.get("data[2].employee_name"), "Ashton Cox");
		softAssert.assertEquals(json.get("data[3].employee_name"), "Cedric Kelly");
		softAssert.assertEquals(json.get("data[4].employee_name"), "Airi Satou");
		
		//2nd Way for assertion of first five employee names
		List<String> names = new ArrayList<>();
		      names.add("Tiger Nixon");
		      names.add("Garrett Winters");
		      names.add("Ashton Cox");
		      names.add("Cedric Kelly");
		      names.add("Airi Satou");
		System.out.println(names);   
		
		for(int i=0; i<5; i++) {
			softAssert.assertEquals(json.get("data[" + i + "].employee_name"), names.get(i));
		}
				
		//3rd Way for assertion of first five employee names
		
		List<Map> actualList = json.getList("data");
		System.out.println(actualList);
		
		Map<Integer, String> expectedMap = new HashMap<>();
		      expectedMap.put(0, "Tiger Nixon");
		      expectedMap.put(1, "Garrett Winters");
		      expectedMap.put(2, "Ashton Cox");
		      expectedMap.put(3, "Cedric Kelly");
		      expectedMap.put(4, "Airi Satou");
		System.out.println(expectedMap);  
		
		for(int i=0; i<5; i++) {
			softAssert.assertEquals(actualList.get(i).get("employee_name"), expectedMap.get(i));
		}
		
		softAssert.assertAll();

	}

}
