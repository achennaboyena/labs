package cs601.yelpapp.data;

public class User {
//data members of user class
	private String userId;
	private String name;
//constructor for User method
	public User(String userId, String name){
		this.userId = userId;
		this.name = name;
	}
//getter method for userId
	public String getUserId(){
		return this.userId;
		
	}
//getter method name
	public String getName(){
		return this.name;
	}
//setter method for name
	public String setName(String name){
		return this.name=name;
	}
//toString method for User class
	public String toString()
	{
		String user = "-" + getUserId() + ", " + getName();
		return user;
	}
	
}
