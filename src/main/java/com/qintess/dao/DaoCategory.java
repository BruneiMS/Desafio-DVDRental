package com.qintess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PSQLException;

import com.qintess.modelos.Category;

public class DaoCategory implements DaoBase<Category>{

	private Connection conn;
	public DaoCategory(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public List<Category> listaTodos() {
		String sql = "SELECT category_id, name FROM CATEGORY;";
		List<Category> categoryLista = new ArrayList<Category>();
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Category c = new Category(	rs.getInt("category_id"),
						rs.getString("name"));
				categoryLista.add(c);
			}
			return categoryLista;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Category buscaPorId(int id) {
		String sql = "SELECT category_id, name FROM CATEGORY WHERE category_id = ?;";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			try {
				rs.next();
				Category c = new Category(	rs.getInt("category_id"), 
											rs.getString("name"));
				return c;
			} catch (PSQLException e) {
				e.printStackTrace();
			}
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	public boolean altera(Category category) {
		String sql 	= "UPDATE CATEGORY SET "
				+ "name = ?"
				+ "WHERE category_id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, category.getName());
			pstmt.setInt(2, category.getCategoryId());
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
	public boolean insere(Category category) {
		String sql  = "INSERT INTO CATEGORY "
				+ "(name) VALUES (?);";

		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, category.getName());
			int exec = pstmt.executeUpdate();
			if(exec == 1) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	public boolean verificaNome(String nome) {
		String sql = "SELECT category_id, name\r\n"
				+ "	FROM category\r\n"
				+ "	WHERE name = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, nome);
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
	
	public int buscaIdPorNome(String categoria) {
		String sql = "SELECT category_id "
				+ "FROM CATEGORY "
				+ "WHERE name = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, categoria);
			ResultSet rs = pstmt.executeQuery();
			try {
				rs.next();
				return rs.getInt("category_id");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
