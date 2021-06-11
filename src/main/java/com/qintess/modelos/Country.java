package com.qintess.modelos;

public class Country {

	private int countryId;
	private String country;
	
	public Country(int id, String country) {
		this.countryId = id;
		this.country = country;
	}
	public Country(String country) {
		this.country = country;
	}
	public int getId() {
		return countryId;
	}
	public void setId(int id) {
		this.countryId = id;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public String toString() {
		return "Country [id=" + countryId + ", country=" + country + "]";
	}

}
