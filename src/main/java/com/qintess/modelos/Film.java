package com.qintess.modelos;

public class Film {

	private int filmId;
	private String title;
	private String description;
	private int releaseDate;
	private int languageId;
	private int rentalDuration;
	private double rentalRate;
	private int length;
	private double replacementCost;
	private String rating;
	private String specialFeatures;
	
	
	public Film(int filmId, String title, String description, int releaseDate, int languageId, int rentalDuration,
			double rentalRate, int length, double replacementCost, String rating, String specialFeatures) {
		super();
		this.filmId = filmId;
		this.title = title;
		this.description = description;
		this.releaseDate = releaseDate;
		this.languageId = languageId;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
	}


	public Film(String title, String description, int releaseDate, int languageId, int rentalDuration,
			double rentalRate, int lenght, double replacementCost, String rating, String specialFeatures) {
		super();
		this.title = title;
		this.description = description;
		this.releaseDate = releaseDate;
		this.languageId = languageId;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.length = lenght;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
	}


	public int getFilmId() {
		return filmId;
	}


	public String getTitle() {
		return title;
	}


	public String getDescription() {
		return description;
	}


	public int getReleaseDate() {
		return releaseDate;
	}


	public int getLanguageId() {
		return languageId;
	}


	public int getRentalDuration() {
		return rentalDuration;
	}


	public double getRentalRate() {
		return rentalRate;
	}


	public int getLength() {
		return length;
	}


	public double getReplacementCost() {
		return replacementCost;
	}


	public String getRating() {
		return rating;
	}


	public String getSpecialFeatures() {
		return specialFeatures;
	}


	@Override
	public String toString() {
		return "Film [filmId=" + filmId + ", title=" + title + ", description=" + description + ", releaseDate="
				+ releaseDate + ", languageId=" + languageId + ", rentalDuration=" + rentalDuration + ", rentalRate="
				+ rentalRate + ", lenght=" + length + ", replacementCost=" + replacementCost + ", rating=" + rating
				+ ", specialFeatures=" + specialFeatures + "]";
	}
	
}
