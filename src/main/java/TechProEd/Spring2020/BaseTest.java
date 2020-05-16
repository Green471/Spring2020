package TechProEd.Spring2020;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Before;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseTest {

	protected RequestSpecification spec01;
	protected RequestSpecification spec02;
	protected RequestSpecification spec03;
	
	@Before
	public void setUp01() {
		spec01 = new RequestSpecBuilder().
                setBaseUri("https://restful-booker.herokuapp.com").
                build();
	}	
		
	@Before
	public void setUp02() {
		spec02 = new RequestSpecBuilder().
                setBaseUri("http://dummy.restapiexample.com/api/v1/employees").
                build();
	}
	
	@Before
	public void setUp03() {
		spec03 = new RequestSpecBuilder().
                setBaseUri("https://jsonplaceholder.typicode.com/todos").
                build();
	}	
	
	
	protected Response createBookingJSONObject() {
		
		JSONObject jsonReqBody = new JSONObject();
		jsonReqBody.put("firstname", "Suleyman");
		jsonReqBody.put("lastname", "Alptekin");
		jsonReqBody.put("totalprice", 123);
		jsonReqBody.put("depositpaid", true);
		
		JSONObject jsonBookingdatesBody = new JSONObject();
		jsonBookingdatesBody.put("checkin", "2020-05-02");
		jsonBookingdatesBody.put("checkout", "2020-05-05");
		
		jsonReqBody.put("bookingdates", jsonBookingdatesBody);
		jsonReqBody.put("additionalneeds", "Wifi");
				
		Response response = given().
				                auth().
				                basic("admin", "password123").
                                contentType(ContentType.JSON).
                                spec(spec01).
                                body(jsonReqBody.toString()).
                             when(). 
                                post("/booking");
		return response;
	}
	
	protected  Map createRequestBookingMap() {
		
		 Map jsonReqBodyMap = new HashMap<>();

			jsonReqBodyMap.put("firstname", "Suleyman");
			jsonReqBodyMap.put("lastname", "Alptekin");
			jsonReqBodyMap.put("totalprice", 123);
			jsonReqBodyMap.put("depositpaid", true);
			
			Map jsonBookingDatesMap = new HashMap<>();
			jsonBookingDatesMap.put("checkin", "2020-05-02");
			jsonBookingDatesMap.put("checkout", "2020-05-05");
			
			jsonReqBodyMap.put("bookingdates", jsonBookingDatesMap);
			jsonReqBodyMap.put("additionalneeds", "Wifi");
			
			return jsonReqBodyMap;
	}
	
	protected Response createResponse() {
		
		Response response = given().
                auth().
                basic("admin", "password123").
                contentType(ContentType.JSON).
                spec(spec01).
                body(createRequestBookingMap()).
             when(). 
                post("/booking");
		
		return response;
	}
}
