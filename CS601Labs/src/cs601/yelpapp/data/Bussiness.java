package cs601.yelpapp.data;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import org.json.simple.JSONArray;

//implementing compatable interface
public class Business implements Comparable<Business>{
//declaring all data members	
	private String businessId;
	private String name;
	private String city;
	private String state;
	private Double lat;
	private Double lon;
    private List <String> neighborhoods;
 //comparing of businessNames and then comparing of businessId 
    public int compareTo(Business newBusiness)
    {
    	if(this.getBusinessId().equals(newBusiness.getBusinessId()))
    	{
    		return 0;
    	}
    	else
    	{
    		int test=this.getName().compareTo(newBusiness.getName());
    		if(test == 0)
    		{
    			return this.getBusinessId().compareTo(newBusiness.getBusinessId());
    		}
    		else
    		{
    			return test;
    		}
    		
    	}
    }
  //constructor of business Class
    public Business(String businessId, String name,String city,String state,Double lat,Double lon)
	{
    	this.businessId = businessId;
		this.name = name;
		this.city = city;
		this.state = state;
		this.lat = lat;
		this.lon = lon;
		 
        
	}
    // constructor for another add business method
	public Business(String businessId ,String name ,String city ,String state,Double lat ,Double lon ,JSONArray neighborhoods)
	{
		this.businessId = businessId;
		this.name = name;
		this.city = city;
		this.state = state;
		this.lat = lat;
		this.lon = lon;
		this.neighborhoods= parseJSONArray(neighborhoods);
		
	}
	//constructor for another addBusiness method
	private List<String> parseJSONArray(JSONArray neighborhoods)
	{
		List<String> neighbors = new ArrayList<>();
		
		for(int i = 0 ; i< neighborhoods.size();i++)
		{
			String s= neighborhoods.get(i).toString();
			neighbors.add(s);
		}
		
		Collections.sort(neighbors);
		
		return neighbors;
		
	}
	// another constructor for another business method
	public Business(String businessId ,String name ,String city ,String state,Double lat ,Double lon ,String neighborhoods)
	{
		this.businessId = businessId;
		this.name = name;
		this.city = city;
		this.state = state;
		this.lat = lat;
		this.lon = lon;
		this.neighborhoods = split(neighborhoods);
        		
	}
	// list for iterating neighbors
	public List<String> split(String neighborhoods)
	{
		List<String> neighbors =  new ArrayList<>();
		for(String s : neighborhoods.split(","))
		{
			
		neighbors.add(s);	
		}
			
		Collections.sort(neighbors);
		return neighbors;
	}
	//get method to getBusinessId
	public  String getBusinessId(){
	return	this.businessId;
    	
    	
    }
	//get method for name
    public String getName(){
    return	this.name;
    	
    }
    //setter method for name
    public String setName(String name){
    	return this.name=name;
    }
    //getter method for city
    public String getCity(){
    	return this.city;
    }
    //setter method for city
    public String setCity(String city){
    	return this.city=city;
    }
    //getter method for State
    public String getState(){
        return this.state;	
    }
    //setter method for State
    public String setState(String state){
        return this.state= state;	
    }
    //getter method for Neighbours
    public List<String> getNeighbours(){
        return neighborhoods;	
    }
    //getter method for latitude
    public Double getLat()
    {
    	return this.lat;
    }
    //setter method for latitude
    public Double setLat(Double lat)
    {
    	return this.lat = lat;
    }
    //getter method for longitude
    public Double getLon()
    {
    	return this.lon;
    }
    //setter method for longitude
    public Double setLon(Double lon)
    {
    	return this.lon = lon;
    }
   //toString method
    public String toString()
    {
    	StringBuilder sb = new StringBuilder();
    	String business=getName()+" - "+getCity()+", "+getState()+" ("+getLat()+", "+getLon()+") (";
    	for(int i = 0 ; i< neighborhoods.size();i++)
		{
			String s= neighborhoods.get(i).toString();
			if(i==neighborhoods.size()-1)
			{
				business=business+s;
			}
			else
			{
				business=business+s+", ";
			}
		
		}
    	return business;
    }
    
}

	
	
	


	




	

