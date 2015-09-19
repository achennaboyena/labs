package cs601.yelpapp.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.Date;

// implementing comparable interface
public class Review implements Comparable<Review> {

	private String BusinessId;
	private int rating;
	private String review;
	private boolean test = true;
	private String userId;
	Date date1 = Calendar.getInstance().getTime();
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private String date = df.format(date1);
	String strRating = Integer.toString(rating);

	public Review() {

	}
//constructor for review method
	public Review(String BusinessId, int rating, String review, String date,
			String userId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		try {
			 sdf.parse(date);
		} catch (Exception e) {
			test = false;
		}
		if (rating < 1 || rating > 5) {
			test = false;
		}

		this.BusinessId = BusinessId;
		this.rating = rating;
		this.review = review;
		this.date = date;
		this.userId = userId;
	}
//getter method for businessId
	public String getBusinessId() {
		return this.BusinessId;

	}
//getter method for test 
	public boolean getTest() {
		return this.test;
	}
//getter method for rating
	public int getRating() {
		return this.rating;
	}
//setter method for rating
	public int setRating(int rating) {
		return this.rating = rating;
	}
//Getter method for review 
	public String getReview() {
		return this.review;

	}
//setter method for review
	public String setReview(String review) {
		return this.review = review;
	}
//get method for date 
	public String getDate() {

		return this.date;

	}
//getter method for userId
	public String getUserId() {
		return this.userId;
	}
//toString method for review class
	public String toString() {
		String review = getBusinessId() + ", " + getRating() + ", "
				+ getReview() + ", " + getDate() + ", " + getUserId();
		return review;
	}

	@Override
	public int compareTo(Review ob2) {
		if (this.getDate().equals(ob2.getDate()) == true
				&& this.getBusinessId().equals(ob2.getBusinessId()) == true
				&& this.getUserId().equals(ob2.getUserId()) == true) {
			return 0;
		}
		String s1 = this.date;
		String s2 = ob2.date;
		int check = s1.compareTo(s2);
		if (check == 0) {
			return this.getUserId().compareTo(ob2.getUserId());
		} else {
			return check;
		}
	}

}

