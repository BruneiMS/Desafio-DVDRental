package com.qintess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qintess.modelos.FilmActor;

public class DaoFilmActor implements DaoBase<FilmActor> {

	private Connection conn;
	public DaoFilmActor(Connection conn) {
		super();
		this.conn = conn;
	}
	
	@Override
	public List<FilmActor> listaTodos() {
		String sql = "SELECT actor_id, film_id\r\n"
				+ "	FROM film_actor;";
		List<FilmActor> filmActorLista = new ArrayList<FilmActor>();
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				FilmActor fa = new FilmActor(
						rs.getInt("actor_id"), rs.getInt("film_id"));
				filmActorLista.add(fa);
			}
			return filmActorLista;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public FilmActor buscaPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleta(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean altera(FilmActor entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insere(FilmActor filmActor) {
		String sql = "INSERT INTO film_actor "
				+ "	(actor_id, film_id) "
				+ "	VALUES (?, ?);";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, filmActor.getActorId());
			pstmt.setInt(2, filmActor.getFilmId());
			int exec = pstmt.executeUpdate();
			if(exec == 1) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Integer> listaFilmesDoAtor(int id){
		String sql = "SELECT film_id FROM FILM_ACTOR WHERE actor_id = ?;";
		List<Integer> lista = new ArrayList<Integer>();
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
					pstmt.setInt(1, id);
					ResultSet rs = pstmt.executeQuery();
					while(rs.next()) {
						lista.add(rs.getInt("film_id"));
					}
					return lista;
				} catch (Exception e) {
					e.printStackTrace();
				}
		return null;
	}
	
	public List<Integer> listaAtoresdoFilme(int id){
		String sql = "SELECT actor_id FROM FILM_ACTOR WHERE film_id = ?;";
		List<Integer> lista = new ArrayList<Integer>();
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
					pstmt.setInt(1, id);
					ResultSet rs = pstmt.executeQuery();
					while(rs.next()) {
						lista.add(rs.getInt("actor_id"));
					}
					return lista;
				} catch (Exception e) {
					e.printStackTrace();
				}
		return null;
	}

}
