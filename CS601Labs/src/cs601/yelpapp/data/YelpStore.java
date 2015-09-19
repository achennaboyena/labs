package cs601.yelpapp.data;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

import org.json.simple.JSONArray;

/**
 * Data structure to store information about businesses, users, and reviews.
 * 
 * @param <businessId>
 * 
 */
public class YelpStore<businessId> {
	//Declaring TreeSet for Business class
	TreeSet<Business> businessTree;
	//Declaring TreeMap for Review class 
	TreeMap<String , TreeSet<Review>> revTree;
	//Declaration of Hash map data Structure for User class.
	HashMap<String , User> userMap;
	//creating Business class global object
	Business businessAdd;
	//creating Review class object
	Review reviewAdd;

	/**
	* Default constructor.
	*/
	public YelpStore() {
		//created TreeSet for Business class
		businessTree = new TreeSet<Business>();
		//created TreeMap for Review class and created TreeSet inside because every business has many reviews 
		
		revTree = new TreeMap<String, TreeSet<Review>>();
		//created Hash map for User class.
		userMap = new HashMap<String, User>();
	}

	/**
	* } Add a new review.
	* 
	* @param <businessId>
	* @param <businessId>
	* @param businessId
	*            - ID of the business reviewed.
	* @param rating
	*            - integer rating 1-5.
	* @param review
	*            - text of the review.
	* @param date
	*            - date of the review in the format yyyy-MM-dd, e.g.,
	*            2015-05-25.
	* @param userId
	*            - ID of the user writing the review.
	* @return true if successful, false if unsuccessful because of invalid date
	*         or rating.
	*/

	public boolean addReview( String businessId , int rating , String review , String date , String userId ) {
    //added a new review
		reviewAdd = new Review(businessId, rating, review, date, userId);
	//if the test method in the review class fails then it returns false

		if ( reviewAdd.getTest() == false ) {
			return false ;
		}
    //if there is no key for reviewTree Data Structure
		if ( !revTree.containsKey( businessId )) {
	//declares and initializes the TreeSet for the list of reviews for each business		
			TreeSet<Review> busRevs = new TreeSet<>();
	//it adds review class object		
			busRevs.add( reviewAdd );
	//it adds the business ids and business reviews  		
			revTree.put( businessId , busRevs );
			
			return true ;
		    
		    }
	//if review tree  does contain a key the businness Id 
		else if ( revTree.containsKey( businessId )) {
    //put the new businessId and create the set for review
			TreeSet<Review> busRevs = revTree.get( businessId );
	//add the review to the set		
			busRevs.add(reviewAdd);
			
			return true;
		}
		

		return false;
	}

	/**
	* Add a new business. Assumes the business has no neighborhood information.
	* 
	* @param businessId
	*            - ID of the business.
	* @param name
	*            - name of the business.
	* @param city
	*            - city where the business is located.
	* @param state
	*            - state where the business is located.
	* @param lat
	*            - latitude of business location.
	* @param lon
	*            - longitude of business location.
	* @return true if successful.
	*/
	public boolean addBusiness(String businessId, String name, String city,String state, double lat, double lon) {
     //creates a new object and allocates memory 
		businessAdd = new Business(businessId, name, city, state, lat, lon);
		businessTree.add(businessAdd);
		return false;
	}

	/**
	* Add a new business.
	* 
	* @param businessId
	*            - ID of the business.
	* @param name
	*            - name of the business.
	* @param city
	*            - city where the business is located.
	* @param state
	*            - state where the business is located.
	* @param lat
	*            - latitude of business location.
	* @param lon
	*            - longitude of business location.
	* @param neighborhoods
	*            - JSONArray containing a list of neighborhoods where the
	*            business is located.
	* @return true if successful.
	*/

	public boolean addBusiness(String businessId, String name, String city, String state, double lat, double lon, JSONArray neighborhoods) {
	//creates new business object
		businessAdd = new Business(businessId, name, city, state, lat, lon,	neighborhoods);
	//adding value to the data structure
		businessTree.add(businessAdd);

		return false;
	}

	/**
	* Add a new business.
	* 
	* @param businessId
	*            - ID of the business.
	* @param name
	*            - name of the business.
	* @param city
	*            - city where the business is located.
	* @param state
	*            - state where the business is located.
	* @param lat
	*            - latitude of business location.
	* @param lon
	*            - longitude of business location.
	* @param neighborhoods
	*            - comma separated String containing a list of neighborhoods
	* @return true if successful.s
	*/
	public boolean addBusiness(String businessId, String name, String city , String state, double lat, double lon, String neighborhoods) {
        //this is creation of object for business class and calls constructor of business class
		businessAdd = new Business(businessId, name, city, state, lat, lon, neighborhoods);
		// this is adding of new value to data structure
		businessTree.add(businessAdd);

		return false;
	}

	/**
	* Add a new user.
	* 
	* @param userId
	*            - ID of the user.
	* @param name
	*            - name of the user (e.g., Sami R.)
	* @return true if successful.
	*/
	public boolean addUser(String userId, String name) {
		//if user map does not contain userId create a new user id 
		if (!userMap.containsKey(userId)) {
			
			User userAdd = new User(userId, name);
		//adding value to the data structure
			userMap.put(userId, userAdd);
			return true;
		}

		return false;
	}

	/**
	* Return a string representation of the data store. Format must be as follows:
	*  Business1 Name - City, State (lat, lon) (neighborhood1 , neighborhood2)
	*  Rating - User: Review 
	*  Rating - User: review
	*  Business2 Name - City, State (lat, lon) (neighborhood1, neighborhood2)
	*  Rating - User: Review 
	*  Rating - User: review 
	*  Adhere to the following rules in generating the output: 
	*  1. Business Names must be sorted alphabetically. 
	*  2. Ratings for a particular business must be sorted by date. 
	*  3. Only reviews for businesses that have been added will be displayed. 
	*  4. If a review is written by user U and no user U has been added to the store the review will appear with no name. 
	*  5. Neighborhoods are sorted alphabetically.
	* 
	* @return string representation of the data store
	* 
	*/
	
	public String toString() {
		//creates a new StringBuilder
		StringBuilder sbuild = new StringBuilder();
        //for loop for each business stored in corresponding data structure
		for (Business business : businessTree) {
		//appending name to output as for the format of representation given
			sbuild.append(business.getName());
			sbuild.append(" - " + business.getCity() + ", "
				+ business.getState() + " (" + business.getLat() + ", "
				+ business.getLon() + ") (");
			List<String> test=business.getNeighbours();
			int counter=0;
			if(test!=null)
			{
				for(String a: test)
				{
					counter++;
					sbuild.append(a);
	                if(business.getNeighbours().size()==counter)
	                {
	                    break;
	                }
	                sbuild.append(", ");
				
				}
			}
			sbuild.append(")\n");
			//sbuild.append(business);
        //for loop for every business review 
			TreeSet<Review> businessReviews = revTree.get(business.getBusinessId());
		//if there are business reviews , then for  each review of business add rating
			if (businessReviews != null) {
		 
				for (Review review : businessReviews)
		
				{
				
					sbuild.append(review.getRating()+" - ");

					if (!userMap.containsKey(review.getUserId())) {
					
						sbuild.append("");
					
					}
					
					if (userMap.containsKey(review.getUserId())) {
					
						sbuild.append(userMap.get(review.getUserId()).getName());
					
					}

					sbuild.append(": " + review.getReview());
					
					sbuild.append("\n");
			
				}
	
			}
			
			sbuild.append("\n");
		}
		
		return sbuild.toString();
	
	}

	/**
	* Save the string representation of the data store to the file specified by fname.
	*  This method must use the YelpStore toString method.
	* 
	* @param fname
	*            - path specifying where to save the output.
	*/
	public void printToFile(Path fname) {

		
		
		StringWriter sw = new StringWriter();
		try(BufferedWriter bOut = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fname.toString()), "UTF-8")))
        {
	      
	     
	         
	         //create buffered writer
	        
	         
	         // writing string to writer
			bOut.write(this.toString());
	         
	         // forces out the characters to string writer
			bOut.flush();
	         
	         // string buffer is created
	         StringBuffer sb = sw.getBuffer();
	         
	         //prints the string
	         System.out.println(sb);
	            
	      }catch(IOException e){
	         // if I/O error occurs
	         e.printStackTrace();
	      }
	   
		
		
	}
		
   
		
		
	

	/**
	* Return an alphabetized list of the ids of all businesses in the data
	* store.
	* 
	* @return
	*/
	public List<String> getBusinesses() {
    
    
		return null;
    
		
	}

	/**
	* Return the average rating for the given business name.
	* 
	* @param business
	*            - name of the business (not ID).
	* @return average rating or 0 if no ratings for the business.
	*/
	public double getRating(String busId) {
		return 0.0;
	}

	/**
	* Return the list of names of all users reviewing the business. List will
	* be sorted by the date the user submitted his/her review.
	* 
	* @param busId
	*            - ID of the business.
	* @return List of user names or empty list if no reviews.
	*/
	


	public List<String> getUsers(String busId) {

		return null;

	}
}

