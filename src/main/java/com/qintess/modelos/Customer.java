package com.qintess.modelos;

public class Customer {

	private int id;
	private int storeId;
	private String firstName;
	private String lastName;
	private String email;
	private int addressId;
	private boolean activebool;
	private int active;
	
	
	public Customer(int id, int storeId, String firstName, String lastName, String email, int addressId,
			boolean activebool, int active) {
		this.id = id;
		this.storeId = storeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.addressId = addressId;
		this.activebool = activebool;
		this.active = active;
	}
	
	
	public Customer(int storeId, String firstName, String lastName, String email, int addressId, boolean activebool,
			int active) {
		super();
		this.storeId = storeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.addressId = addressId;
		this.activebool = activebool;
		this.active = active;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public boolean getActivebool() {
		return activebool;
	}
	public void setActivebool(boolean activebool) {
		this.activebool = activebool;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", storeId=" + storeId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", addressId=" + addressId + ", activebool=" + activebool + ", active=" + active
				+ "]";
	}

}
