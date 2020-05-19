package TechProEd.Spring2020;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingId {

	@SerializedName("bookingid")
	@Expose
	private Integer bookingid;
	@SerializedName("booking")
	@Expose
	private Booking booking;

	public Integer getBookingid() {
	return bookingid;
	}

	public void setBookingid(Integer bookingid) {
	this.bookingid = bookingid;
	}

	public Booking getBooking() {
	return booking;
	}

	public void setBooking(Booking booking) {
	this.booking = booking;
	}

	public BookingId(Integer bookingid, Booking booking) {
		this.bookingid = bookingid;
		this.booking = booking;
	}
	
	public BookingId() {
	}

	@Override
	public String toString() {
		return "BookingId [bookingid=" + bookingid + ", booking=" + booking + "]";
	}
}
