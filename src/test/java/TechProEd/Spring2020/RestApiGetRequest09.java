package TechProEd.Spring2020;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.Test;

import io.restassured.path.json.JsonPath;

public class RestApiGetRequest09 {

	    //How to read local Json File
		@Test
		public void readLocalJsonFile() {
			
		JsonPath jsonFromLocalFile = new JsonPath(new File("/Users/apple/Desktop/EmployeesFile.json"));
		//Get all ids into a list
		List<String> ids = jsonFromLocalFile.getList("data.id");
		System.out.println("All ids: " + ids);
		List<String> names = jsonFromLocalFile.getList("data.employee_name");
		System.out.println("All names: " + names);
		List<String> salaries = jsonFromLocalFile.getList("data.employee_salary");
		System.out.println("All salaries: " + salaries);
		
		assertEquals("1",ids.get(0));
		assertEquals("Tiger Nixon",names.get(0));
		assertEquals("320800",salaries.get(0));
		assertTrue(ids.contains("24"));
		assertFalse(names.contains("TechPro Education"));
		
		}
}
