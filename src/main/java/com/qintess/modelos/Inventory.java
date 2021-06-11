package com.qintess.modelos;

public class Inventory {

	private int inventoryId;
	private int filmId;
	private int storeId;
	
	public Inventory(int inventoryId, int filmId, int storeId) {
		this.inventoryId = inventoryId;
		this.filmId = filmId;
		this.storeId = storeId;
	}
	

	public Inventory(int filmId, int storeId) {
		super();
		this.filmId = filmId;
		this.storeId = storeId;
	}



	public int getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}

	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	@Override
	public String toString() {
		return "Inventory [inventoryId=" + inventoryId + ", filmId=" + filmId + ", storeId=" + storeId
				+ ", getInventoryId()=" + getInventoryId() + ", getFilmId()=" + getFilmId() + ", getStoreId()="
				+ getStoreId() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
}