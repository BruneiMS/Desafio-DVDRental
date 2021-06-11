package com.qintess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PSQLException;

import com.qintess.modelos.Customer;

public class DaoCustomer implements DaoBase<Customer>{

	private Connection conn;
	public DaoCustomer(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public List<Customer> listaTodos() {
		String sql = "SELECT customer_id, store_id, first_name, last_name, email, address_id, activebool, active "
				+ "FROM CUSTOMER;";
		List<Customer> customerLista = new ArrayList<Customer>();
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Customer c = new Customer(rs.getInt("customer_id"), rs.getInt("store_id"), rs.getString("first_name"), 
						rs.getString("last_name"), rs.getString("email"), rs.getInt("address_id"), 
						rs.getBoolean("activebool"), rs.getInt("active"));
				customerLista.add(c);
			}
			return customerLista;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Customer buscaPorId(int id) {
		String sql = "SELECT customer_id, store_id, first_name, last_name, email, address_id, activebool, active"
				+ " FROM CUSTOMER WHERE customer_id = ?;";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			try {
				rs.next();
				Customer c = new Customer(rs.getInt("customer_id"), rs.getInt("store_id"), 
						rs.getString("first_name"), rs.getString("last_name"), 
						rs.getString("email"), rs.getInt("address_id"), rs.getBoolean("activebool"), 
						rs.getInt("active"));
				return c;
			} catch (PSQLException e) {
				System.out.println("Customer nao encontrado");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleta(int id) {
		String sql = "DELETE FROM PAYMENT WHERE customer_id = ?; "
				+ " DELETE FROM CUSTOMER WHERE customer_id = ?; ";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, id);
			pstmt.setInt(2, id);
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
	public boolean altera(Customer customer) {
		String sql = "UPDATE CUSTOMER SET "
				+ "store_id = ?, first_name = ?, last_name = ?, email = ?, address_id = ?,"
				+ "activebool = ?, active = ? "
				+ "WHERE customer_id = ?;";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, customer.getStoreId());
			pstmt.setString(2, customer.getFirstName());
			pstmt.setString(3, customer.getLastName());
			pstmt.setString(4, customer.getEmail());
			pstmt.setInt(5, customer.getAddressId());
			pstmt.setBoolean(6, customer.getActivebool());
			pstmt.setInt(7, customer.getActive());
			pstmt.setInt(8, customer.getId());
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
	public boolean insere(Customer customer) {
		String sql = "INSERT INTO CUSTOMER "
				+ "(store_id, first_name, last_name, email, address_id, "
				+ "activebool, active) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?);";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, customer.getStoreId());
			pstmt.setString(2, customer.getFirstName());
			pstmt.setString(3, customer.getLastName());
			pstmt.setString(4, customer.getEmail());
			pstmt.setInt(5, customer.getAddressId());
			pstmt.setBoolean(6, customer.getActivebool());
			pstmt.setInt(7, customer.getActive());
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

	public boolean verificaCliente(String firstName, String lastName, String email) {
		String sql  = " SELECT customer_id FROM CUSTOMER "
					+ " WHERE first_name = ? "
					+ " AND last_name = ?"
					+ " AND email = ?;";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setString(3, email);
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

	
	public int retornaIdCliente(String firstName, String lastName, String email) {
		String sql  = " SELECT customer_id FROM CUSTOMER "
					+ " WHERE first_name = ? "
					+ " AND last_name = ?"
					+ " AND email = ?;";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setString(3, email);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("customer_id");
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public boolean verificaStatus (int id) {
		String sql  = " SELECT active FROM CUSTOMER "
					+ " WHERE customer_id = ?;";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			try {
				rs.next();
				if(rs.getInt("active") == 1) {
					return true;
				}
				return false;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}