package com.cs442.hparik11.places_near_me;

public class Location_Details {
	private double latitude;
	private double longitude;
	private String title;
	public Location_Details(double latitude, double longitude, String title)
	{
		this.latitude = latitude;
		this.longitude = longitude;
		this.title = title;
	}
	public double getLatitude()
	{
		return this.latitude;
	}
	
	public double getLongitude()
	{
		return this.longitude;
	}
	
	public String getTitle()
	{
		return this.title;
	}
}
