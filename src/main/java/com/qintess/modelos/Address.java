package com.qintess.modelos;

public class Address {

	private Integer AddressId;
	private String address;
	private String address2;
	private String district;
	private int cityId;
	private String postalCode;
	private String phone;


	public Address(Integer addressId, String address, String address2, String district, int city_id, String postal_code, String phone) {
		super();
		this.AddressId = addressId;
		this.address = address;
		this.address2 = address2;
		this.district = district;
		this.cityId = city_id;
		this.postalCode = postal_code;
		this.phone = phone;
	}
	

	public Address(String address, String address2, String district, int cityId, String postalCode, String phone) {
		super();
		this.address = address;
		this.address2 = address2;
		this.district = district;
		this.postalCode = postalCode;
		this.cityId = cityId;
		this.phone = phone;
	}



	public Integer getAddress_id() {
		return AddressId;
	}

	public void setAddress_id(Integer address_id) {
		this.AddressId = address_id;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public int getCity_id() {
		return cityId;
	}

	public void setCity_id(int city_id) {
		this.cityId = city_id;
	}

	public String getPostal_code() {
		return postalCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Address [AddressId=" + AddressId + ", address=" + address + ", address2=" + address2 + ", district="
				+ district + ", cityId=" + cityId + ", postalCode=" + postalCode + ", phone=" + phone + "]";
	}
	
	

}
