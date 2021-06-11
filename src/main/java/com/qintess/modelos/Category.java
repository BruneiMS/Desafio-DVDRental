package com.qintess.modelos;

public class Category {

	private int categoryId;
	private String name;
	
	public Category(String name) {
		super();
		this.name = name;
	}
	
	public Category() {}

	public Category(int categoryId, String name) {
		this.categoryId = categoryId;
		this.name = name;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", name=" + name + "]";
	}
	
	
}
