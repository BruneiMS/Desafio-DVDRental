package com.qintess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.qintess.modelos.FilmCategory;

public class DaoFilmCategory implements DaoBase<FilmCategory> {

	private Connection conn;
	public  DaoFilmCategory(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public List<FilmCategory> listaTodos() {

		String sql = "SELECT film_id, category_id FROM FILM_CATEGORY;";

		List<FilmCategory> filmCategoryLista = new ArrayList<FilmCategory>();

		try (PreparedStatement pstmt = conn.prepareStatement(sql)){

			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				FilmCategory a = new FilmCategory(rs.getInt("film_id"), rs.getInt("category_id"));
				filmCategoryLista.add(a);
			}
			return filmCategoryLista;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public FilmCategory buscaPorId(int id) {

		return null;
	}

	@Override
	public boolean deleta(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean altera(FilmCategory filmCategory) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insere(FilmCategory filmCategory) {
		String sql  = "INSERT INTO FILM_CATEGORY "
				+ "(film_id, category_id) VALUES (?, ?);";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, filmCategory.getFilmId());
			pstmt.setInt(2, filmCategory.getCategoryId());
			int exec = pstmt.executeUpdate();
			if(exec == 1) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();		}
		return false;
	}

}
