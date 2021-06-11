package com.qintess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PSQLException;

import com.qintess.modelos.Film;

public class DaoFilm implements DaoBase<Film> {
	
	private Connection conn;

	public DaoFilm(Connection conn) {
		this.conn = conn;
	}

	/**
	 * Metodo para listar os filmes presentes no banco de dados
	 * @return listaFilm Lista com os Objetos Film dos filmes
	 */
	@Override
	public List<Film> listaTodos() {
		String sql = "SELECT film_id, title, description, release_year, language_id, "
				+ "rental_duration, rental_rate, length, replacement_cost, "
				+ "rating, special_features FROM FILM;";
		List<Film> listaFilm = new ArrayList<Film>();
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Film f = new Film(rs.getInt("film_id"), rs.getString("title"), rs.getString("description"), 
							rs.getInt("release_year"), rs.getInt("language_id"), rs.getInt("rental_duration"), 
							rs.getDouble("rental_rate"), rs.getInt("length"), rs.getDouble("replacement_cost"), 
							rs.getString("rating"), rs.getString("special_features"));
				listaFilm.add(f);
			}
			return listaFilm;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Metodo para buscar filme utilizando sua id
	 * @param id Contem a id do filme a ser buscado
	 * @return 
	 */
	@Override
	public Film buscaPorId(int id) {
		String sql = "SELECT film_id, title, description, release_year, language_id, "
				+ "rental_duration, rental_rate, length, replacement_cost, "
				+ "rating, special_features FROM FILM WHERE film_id = ?;";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			try {
				rs.next();
				Film f = new Film(rs.getInt("film_id"), rs.getString("title"), rs.getString("description"), 
						rs.getInt("release_year"), rs.getInt("language_id"), rs.getInt("rental_duration"), 
						rs.getDouble("rental_rate"), rs.getInt("length"), rs.getDouble("replacement_cost"), 
						rs.getString("rating"), rs.getString("special_features"));
				return f;
			} catch (PSQLException e) {
				System.out.println("Film nao encontrado");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleta(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean altera(Film film) {
		String sql = "UPDATE FILM SET "
				+ "(title = ?, description = ?, release_year = ?, language_id = ?, "
				+ "rental_duration = ?, rental_rate = ?, length = ?, replacement_cost = ?, "
				+ "rating = ?, special_features = ?, fulltext = ?);";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, film.getTitle());
			pstmt.setString(2, film.getDescription());
			pstmt.setInt(3, film.getReleaseDate());
			pstmt.setInt(4, film.getLanguageId());
			pstmt.setInt(5, film.getRentalDuration());
			pstmt.setDouble(6, film.getRentalRate());
			pstmt.setInt(7, film.getLength());
			pstmt.setDouble(8, film.getReplacementCost());
			pstmt.setString(9, film.getRating());
			pstmt.setString(10, film.getSpecialFeatures());
			pstmt.setString(11, film.getDescription());
			int exec = pstmt.executeUpdate();
			if(exec == 1) {
				return true;
			}
			else {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public boolean insere(Film film) {
		String sql	= " INSERT INTO FILM "
					+ " (title, description, release_year, language_id, rental_duration, "
					+ " rental_rate, length, replacement_cost, rating, special_features) "
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?::mpaa_rating, ?::text[]); ";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, film.getTitle());
			pstmt.setString(2, film.getDescription());
			pstmt.setInt(3, film.getReleaseDate());
			pstmt.setInt(4, film.getLanguageId());
			pstmt.setInt(5, film.getRentalDuration());
			pstmt.setDouble(6, film.getRentalRate());
			pstmt.setInt(7, film.getLength());
			pstmt.setDouble(8, film.getReplacementCost());
			pstmt.setString(9, film.getRating());
			pstmt.setString(10, film.getSpecialFeatures());
			int exec = pstmt.executeUpdate();
			if(exec == 1) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean verificaTitulo(String titulo) {
		String sql = "SELECT film_id FROM FILM WHERE title = ?;";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, titulo);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int buscaPorTitulo(String titulo) {
		String sql = "SELECT film_id FROM FILM WHERE title = ?;";
		try (PreparedStatement pstmt = conn.prepareStatement(sql))  {
			pstmt.setString(1, titulo);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("film_id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}