package TechProEd.Spring2020;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingDates {
	
	//Create Getters and Setters by using http://www.jsonschema2pojo.org/
	//Create constructor with all parameters
	//Create constructor with no parameters
	//Create toString()

	@SerializedName("checkin")
	@Expose
	private String checkin;
	@SerializedName("checkout")
	@Expose
	private String checkout;

	public String getCheckin() {
	return checkin;
	}

	public void setCheckin(String checkin) {
	this.checkin = checkin;
	}

	public String getCheckout() {
	return checkout;
	}

	public void setCheckout(String checkout) {
	this.checkout = checkout;
	}

	//If there is super() inside the constructors delete it
	public BookingDates(String checkin, String checkout) {
		this.checkin = checkin;
		this.checkout = checkout;
	}
	//If there is super() inside the constructors delete it
	public BookingDates() {
	}

	@Override
	public String toString() {
		return "BookingDates [checkin=" + checkin + ", checkout=" + checkout + "]";
	}
}
