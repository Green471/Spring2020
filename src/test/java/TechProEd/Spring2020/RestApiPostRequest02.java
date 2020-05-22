package TechProEd.Spring2020;

import static io.restassured.RestAssured.given;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RestApiPostRequest02 extends BaseTest {
        
		// Custom Java Classes to Match Our Request and Response
		
		//POJO(Beans) => Plain Old Java Object 
		
		//1st Way
		//Create a class name it as PojoClass01 
		//Create the class by using getters and setters under the Source tab
		
		//2nd Way
		//Go to Google type "Json to Pojo Generator" select 1st one
		//Copy paste your Json data, enter package and class names it will generate 
		//Select Target language as Java, Source type as Json, Annotation Style as Gson
		//a class for you automatically
		//Create a Class under src/main/java in TechProEd.Spring2020 name it as Booking,
	    //Create another Class under src/main/java in TechProEd.Spring2020 name it as BookingDates,
		//Copy the class script from "Json to Pojo Generator" then paste them int the class
	    //Create Constructor with all parameters by using Source
	    //Create toString method by using Source
		
	    @Test
        public void pojoUsage() {
	    	//Create body using Pojo (After typing constructor name ctrl+space to see the parameters)
	    	BookingDates bookingDates = new BookingDates("2020-05-21", "2020-05-28");
	    	Booking booking = new Booking("Suleyman", "Alptekin", 8888, true, bookingDates, "Newspaper");
	    	
	    	Response response = given().
					                auth().
					                preemptive().
					                basic("admin", "password123").
					                contentType(ContentType.JSON).
					                spec(spec01).
					                body(booking).
					            when(). 
					                post("/booking");
	    	response.prettyPrint();
	    	
	    	BookingId bookingid = response.as(BookingId.class); 
	    	
	    	//Use JsonPath to navigate in Json Data
	    	JsonPath json = response.jsonPath();
	    	
	    	 //Soft Assertion
			 SoftAssert softAssert = new SoftAssert();
			 
			//Soft assert the status code is 200
			 softAssert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
			 
			//Soft assert the first name is Suleyman
			 softAssert.assertEquals(json.get("booking.firstname"), booking.getFirstname(), "First name did not match");
			 
			 //Soft assert the last name is Alptekin
			 softAssert.assertEquals(json.get("booking.lastname"), booking.getLastname(), "Last name did not match");
			
			 //Soft assert total price is 8888
			 softAssert.assertEquals(json.get("booking.totalprice"), booking.getTotalprice(), "total price did not match");
			
			 //Soft assert deposit paid is true
			 softAssert.assertEquals(json.get("booking.depositpaid"), booking.getDepositpaid(), "Deposit paid did not match");
			 
			 //Soft assert checkin date is 2020-05-21
			 softAssert.assertEquals(json.get("booking.bookingdates.checkin"), bookingDates.getCheckin(), "checkin date did not match");
			 
			 //Soft assert checkout date is 2020-05-28
			 softAssert.assertEquals(json.get("booking.bookingdates.checkout"), bookingDates.getCheckout(), "checkout date did not match");
			 
			 //Soft assert additional needs are Newspaper
			 softAssert.assertEquals(json.get("booking.additionalneeds"), booking.getAdditionalneeds(), "Additional needs did not match");
			 
			 //Verify All fields in one step
			 System.out.println(bookingid.toString());
			 System.out.println(booking.toString());
			 softAssert.assertEquals(bookingid.getBooking().toString(), booking.toString());
			 softAssert.assertAll();
        }

}
