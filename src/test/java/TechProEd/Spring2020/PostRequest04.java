package TechProEd.Spring2020;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class PostRequest04 extends BaseTest01 {
	
	//POJO(Beans) ==> Plain Old Java Object
	@Test
	public void postTest01() {
	BookingDates bookingDates = new BookingDates("2020-05-21", "2020-05-25");
	Booking booking = new Booking("Selim", "Can", 888, true, bookingDates, "Breakfast");
	
	Response response = given().
                            auth().
                            preemptive().
                            basic("admin","password123").
                            contentType(ContentType.JSON).
                            spec(spec01).
                            body(booking).
                        when().
                            post("/booking");
    response.prettyPrint();  
    
    BookingId bookingId = response.as(BookingId.class);
    
    //Use JsonPath to create an object and use the object to navigate inside json data
    JsonPath json = response.jsonPath();
    
    //Create soft assert object for soft assertion
    SoftAssert softAssert = new SoftAssert();
    
    //Assert that status code is 200
    softAssert.assertEquals(response.getStatusCode(), 200);
    
    //Assert that first name is Selim
    softAssert.assertEquals(json.get("booking.firstname"),booking.getFirstname());
    
    //Assert that last name is Can
    softAssert.assertEquals(json.get("booking.lastname"),booking.getLastname());
    
    //Assert that total price is 888
    softAssert.assertEquals(json.get("booking.totalprice"),booking.getTotalprice()); 
    
    //Assert that deposit paid is true
    softAssert.assertEquals(json.get("booking.depositpaid"),booking.getDepositpaid()); 
    
    //Assert that chekcin is 2020-05-21
    softAssert.assertEquals(json.get("booking.bookingdates.checkin"),booking.getBookingdates().getCheckin());
    
    //Assert that checkout is 2020-05-21
    softAssert.assertEquals(json.get("booking.bookingdates.checkout"),booking.getBookingdates().getCheckout());
    
    //Assert that additional needs Breakfast
    softAssert.assertEquals(json.get("booking.additionalneeds"),booking.getAdditionalneeds());
    
    //To get data from response 
    System.out.println(bookingId.getBooking().toString());
    
    //To get data from request
    System.out.println(booking.toString());
    
    //To assert all data in one line code
    softAssert.assertEquals(bookingId.getBooking().toString(),booking.toString());
    
    softAssert.assertAll();
    
	}                        
}
