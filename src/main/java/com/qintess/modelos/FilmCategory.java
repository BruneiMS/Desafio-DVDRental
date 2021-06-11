package com.qintess.modelos;

public class FilmCategory {

	private int filmId;
	private int categoryId;
	
	public FilmCategory(int filmId, int categoryId) {
		this.filmId = filmId;
		this.categoryId = categoryId;
	}

	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "FilmCategory [filmId=" + filmId + ", categoryId=" + categoryId + "]";
	}
}
