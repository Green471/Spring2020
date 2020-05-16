package TechProEd.Spring2020;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.junit.Before;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseTest01 {

	protected RequestSpecification spec01;
	protected RequestSpecification spec02;
	
	@Before
	public void setUp01() {
		
		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
		
		spec01 = requestSpecBuilder.
				             setBaseUri("https://restful-booker.herokuapp.com").
				             build();
	}
	
	@Before
	public void setUp02() {
		
		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
		
		spec02 = requestSpecBuilder.
				            setBaseUri("http://dummy.restapiexample.com/api/v1/employees")
				            .build();
	}
}
