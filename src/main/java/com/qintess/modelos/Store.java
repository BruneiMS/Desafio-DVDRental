package com.qintess.modelos;

public class Store {

	private int storeId;
	private int managerId;
	private int addressId;
	

	public Store(int storeId, int managerId, int addressId) {
		super();
		this.storeId = storeId;
		this.managerId = managerId;
		this.addressId = addressId;
	}


	public Store(int managerId, int addressId) {
		super();
		this.managerId = managerId;
		this.addressId = addressId;
	}




	public int getStoreId() {
		return storeId;
	}


	public int getManagerId() {
		return managerId;
	}


	public int getAddressId() {
		return addressId;
	}


	@Override
	public String toString() {
		return "Store [storeId=" + storeId + ", managerId=" + managerId + ", addressId=" + addressId + "]";
	}
	
	
	
}
