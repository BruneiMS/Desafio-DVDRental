package com.qintess.modelos;

public class City {

	private int cityId;
	private String city;
	private int countryId;

	public City(int cityId, String city, int countryId) {
		this.cityId = cityId;
		this.city = city;
		this.countryId = countryId;
	}
	
	public City(String city, int countryId) {
		super();
		this.city = city;
		this.countryId = countryId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	@Override
	public String toString() {
		return "city [cityId=" + cityId + ", city=" + city + ", countryId=" + countryId + ", lastUpdate="
				+ "]";
	}

}
