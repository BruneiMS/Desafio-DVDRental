package com.qintess.modelos;

public class Language {
	
	private int languageId;
	private String name;
	
	
	
	public int getLanguageId() {
		return languageId;
		
	}
	
	public Language(int languageId, String name) {
		super();
		this.languageId = languageId;
		this.name = name;
	}
	
	public Language(String name) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	
}
