package com.qintess.dao;

import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PSQLException;

import com.qintess.modelos.Language;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DaoLanguage implements DaoBase<Language> {

	private Connection conn;
	
	public DaoLanguage(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public List<Language> listaTodos() {
		String sql = "SELECT language_id, name FROM LANGUAGE;";
		List<Language> languageLista = new ArrayList<Language>();
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Language l = new Language(	rs.getInt("language_id"),
						rs.getString("name"));
				languageLista.add(l);
			}
			return languageLista;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Language buscaPorId(int id) {
		String sql = "SELECT language_id, name FROM LANGUAGE WHERE language_id = ?;";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			try {
				rs.next();
				Language l = new Language(	rs.getInt("language_id"), 
											rs.getString("name"));
				return l;
			} catch (PSQLException e) {
				System.out.println("Language nao encontrado");
			}
			return null;
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
	public boolean altera(Language language) {
		String sql 	= "UPDATE LANGUAGE SET "
				+ "name = ?"
				+ "WHERE language_id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, language.getName());
			pstmt.setInt(2, language.getLanguageId());
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

	@Override
	public boolean insere(Language language) {
		String sql  = "INSERT INTO LANGUAGE "
				+ "(name) VALUES (?);";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, language.getName());
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
	
	public boolean verificaIdioma(String idioma) {
		String sql = "SELECT name FROM LANGUAGE WHERE name = ?;";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, idioma);
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

	public int buscaPorNome(String idioma) {
		String sql = "SELECT language_id FROM LANGUAGE WHERE name = ?;";
		try (PreparedStatement pstmt = conn.prepareStatement(sql))  {
			pstmt.setString(1, idioma);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("language_id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
