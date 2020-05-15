package TechProEd.Spring2020;

import org.junit.Test;

public class GetRequest06 extends BaseTest01 {
	/*
	 When I send a GET request to REST API URL 
	 https://restful-booker.herokuapp.com/5   
     Then HTTP Status Code should be 200
     And response content type is “application/JSON”
     And response body should be like; 
     {
	    "firstname": "Sally",
	    "lastname": "Ericsson",
	    "totalprice": 111,
	    "depositpaid": false,
	    "bookingdates": {
	        "checkin": "2017-05-23",
	        "checkout": "2019-07-02"
	  }
	*/
	@Test
	public void getTest01() {
		
		spec01.pathParam("bookingid", 5);
	}
	
	
	
	
	
	

}
