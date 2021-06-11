package com.qintess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PSQLException;

import com.qintess.modelos.Address;

public class DaoAddress implements DaoBase<Address> {

	private Connection conn;
	public DaoAddress(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public List<Address> listaTodos() {
		String sql = "SELECT address_id, address, address2, district, city_id, postal_code, phone FROM ADDRESS;";
		List<Address> addressLista = new ArrayList<Address>();
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Address a = new Address(rs.getInt("address_id"), rs.getString("address"), rs.getString("address2"), 
						rs.getString("district"), rs.getInt("city_id"), rs.getString("postal_code"), 
						rs.getString("phone"));
				addressLista.add(a);
			}
			return addressLista;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	

	@Override
	public Address buscaPorId(int id) {
		String sql = "SELECT address_id, address, address2, district, city_id, postal_code, phone"
				+ " FROM ADDRESS WHERE address_id = ?;";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			try {
				rs.next();
				Address a = new Address(rs.getInt("address_id"), rs.getString("address"), rs.getString("address2"), 
						rs.getString("district"), rs.getInt("city_id"), rs.getString("postal_code"), 
						rs.getString("phone"));
				return a;
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
		String sql = "DELETE FROM ADDRESS WHERE address_id = ?;";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, id);
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
	public boolean altera(Address address) {
		String sql = "UPDATE ADDRESS SET "
				+ "address = ? "
				+ "WHERE address_id = ?;";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, address.getAddress());
			pstmt.setInt(2, address.getCity_id());
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
	public boolean insere(Address address) {
		String sql = "INSERT INTO ADDRESS "
				+ "(address, address2, district, city_id, postal_code, phone) "
				+ "VALUES (?, ?, ?, ?, ?, ?);";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, address.getAddress());
			pstmt.setString(2, address.getAddress2());
			pstmt.setString(3, address.getDistrict());
			pstmt.setInt(4, address.getCity_id());
			pstmt.setString(5, address.getPostal_code());
			pstmt.setString(6, address.getPhone());
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

	public boolean verificaEndereco(String endereco, String complemento, String telefone) {
		String sql  = " SELECT address FROM ADDRESS "
					+ " WHERE address = ? "
					+ " AND address2 = ? "
					+ " AND phone = ?; ";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, endereco);
			pstmt.setString(2, complemento);
			pstmt.setString(3, telefone);
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
	
	public int buscaIdPorNome(String endereco, String complemento, String telefone){
		String sql  = " SELECT address_id FROM ADDRESS "
				+ " WHERE address = ? "
				+ " AND address2 = ? "
				+ " AND phone = ?; ";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, endereco);
			pstmt.setString(2, complemento);
			pstmt.setString(3, telefone);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("address_id");
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}