package com.qintess.modelos;

public class Staff {
	
	private int staffId;
	private String firstName;
	private String lastName;
	private int addressId;
	private String email;
	private int storeId;
	private boolean active;
	private String username;
	private String password;
	
	
	public Staff(int staffId, String first_name, String last_name, int address_id, String email, int storeId,
			boolean active, String username, String password) {
		super();
		this.staffId = staffId;
		this.firstName = first_name;
		this.lastName = last_name;
		this.addressId = address_id;
		this.email = email;
		this.storeId = storeId;
		this.active = active;
		this.username = username;
		this.password = password;
	}


	public Staff(String first_name, String last_name, int address_id, String email, boolean active,
			String username, String password) {
		super();
		this.firstName = first_name;
		this.lastName = last_name;
		this.addressId = address_id;
		this.email = email;
		this.active = active;
		this.username = username;
		this.password = password;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public int getStaffId() {
		return staffId;
	}


	public String getFirst_name() {
		return firstName;
	}


	public String getLast_name() {
		return lastName;
	}


	public int getAddress_id() {
		return addressId;
	}


	public String getEmail() {
		return email;
	}


	public int getStoreId() {
		return storeId;
	}


	public String getUsername() {
		return username;
	}


	public String getPassword() {
		return password;
	}


	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	
	
	
	
}
