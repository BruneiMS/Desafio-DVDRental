package com.qintess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.postgresql.util.PSQLException;
import com.qintess.modelos.Staff;

public class DaoStaff implements DaoBase<Staff> {
	
	private Connection conn;
	

	public DaoStaff(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public List listaTodos() {
		String sql = "SELECT staff_id, first_name, last_name, address_id, email, store_id, "
				+ "active, username, password FROM STAFF;";
		List<Staff> staffLista = new ArrayList<Staff>();
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Staff s = new Staff(rs.getInt("staff_id"), rs.getString("first_name"), rs.getString("last_name"), 
						rs.getInt("address_id"), rs.getString("email"), rs.getInt("store_id"), rs.getBoolean("active"), 
						rs.getString("username"), rs.getString("password"));
				staffLista.add(s);
			}
			return staffLista;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Staff buscaPorId(int id) {
		String sql = "SELECT staff_id, first_name, last_name, address_id, email, store_id, "
				+ "active, username, password FROM STAFF WHERE staff_id = ?;";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			try {
				rs.next();
				Staff s = new Staff(rs.getInt("staff_id"), rs.getString("first_name"), rs.getString("last_name"), 
						rs.getInt("address_id"), rs.getString("email"), rs.getInt("store_id"), rs.getBoolean("active"), 
						rs.getString("username"), rs.getString("password"));
				return s;
			} catch (PSQLException e) {
				e.printStackTrace();
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
	public boolean altera(Staff staff) {
		String sql = "UPDATE STAFF SET "
				+ "store_id = ? WHERE username = ?;";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, staff.getStoreId());
			pstmt.setString(2, staff.getUsername());
			int exec = pstmt.executeUpdate();
			if(exec == 1) {
				return true;
			}
			else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean insere(Staff staff) {
		String sql = "INSERT INTO STAFF (first_name, last_name, address_id, email, store_id, "
				+ "active, username, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, staff.getFirst_name());
			pstmt.setString(2, staff.getLast_name());
			pstmt.setInt(3, staff.getAddress_id());
			pstmt.setString(4, staff.getEmail());
			pstmt.setInt(5, staff.getStoreId());
			pstmt.setBoolean(6, staff.isActive());
			pstmt.setString(7, staff.getUsername());
			pstmt.setString(8, staff.getPassword());
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
	
	public int buscaPorUsername(String username) {
		String sql = "SELECT staff_id FROM STAFF WHERE username = ?;";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("staff_id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}



}
