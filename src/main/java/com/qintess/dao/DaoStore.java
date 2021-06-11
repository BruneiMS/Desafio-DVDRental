package com.qintess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PSQLException;

import com.qintess.modelos.Store;

public class DaoStore implements DaoBase<Store>{

	private Connection conn;
	public DaoStore(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public List<Store> listaTodos() {
		String sql = "SELECT store_id, manager_staff_id, address_id FROM store;";
		List<Store> storeLista = new ArrayList<Store>();
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Store c = new Store(	rs.getInt("store_id"),
						rs.getInt("manager_staff_id"),
						rs.getInt("address_id"));
				storeLista.add(c);
			}
			return storeLista;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Store buscaPorId(int id) {
		String sql = "SELECT store_id, manager_staff_id, address_id FROM STORE WHERE store_id = ?;";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			try {
				rs.next();
				Store c = new Store (	rs.getInt("store_id"),
										rs.getInt("manager_staff_id"),
										rs.getInt("address_id"));
				return c;
			} catch (PSQLException e) {
				System.out.println("Store não encontrado!");
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
	public boolean altera(Store store) {
		String sql 	= " UPDATE STORE SET "
				+ " manager_staFf_id = ?,"
				+ " address_id = ?"
				+ " WHERE store_id = ?";

		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, store.getManagerId());
			pstmt.setInt(2, store.getAddressId());
			pstmt.setInt(3, store.getStoreId());
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
	public boolean insere(Store store) {
		String sql  = " INSERT INTO STORE "
					+ " (manager_staff_id, address_id) VALUES (?, ?);";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, store.getManagerId());
			pstmt.setInt(2, store.getAddressId());
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
	
	public boolean verificaStore(int id) {
		String sql  = " SELECT store_id FROM STORE "
					+ " WHERE store_id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, id);
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
	
	public int buscaPorEndereco(int addressId) {
		String sql = "SELECT store_id FROM STORE WHERE address_id = ?;";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, addressId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("store_id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	

}