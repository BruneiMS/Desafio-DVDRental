package com.qintess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.qintess.modelos.Inventory;

public class DaoInventory implements DaoBase<Inventory>{

	private Connection conn;
	
	public DaoInventory(Connection conn) {
		this.conn = conn;
	}
	
	/**
	 * Metodo para listar todos os dados do Inventario no banco
	 * @return actorLista ArrayList com os Objetos Actor dos atores
	 */
	
	@Override
	public List<Inventory> listaTodos() {
		String sql = "SELECT	inventory_id, film_id, store_id FROM INVENTORY;";
		List<Inventory> inventoryLista = new ArrayList<Inventory>();
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Inventory i = new Inventory(rs.getInt("inventory_id"), rs.getInt("film_id"), rs.getInt("store_id"));
				inventoryLista.add(i);
			}
			return inventoryLista;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Metodo para encontrar dado no Inventario pelo id
	 * @param id Numero da id a ser buscada
	 * @return a Objeto Inventario do dado do inventario encontrado
	 */
	@Override
	public Inventory buscaPorId(int id) {
		String sql 	= "SELECT 	inventory_id, film_id, store_id FROM INVENTORY"
					+ " WHERE inventory_id = 2;";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Inventory i = new Inventory(rs.getInt("inventory_id"), rs.getInt("film_id"), rs.getInt("store_id"));
				return i;
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
	public boolean altera(Inventory inventory) {
		String sql	 = "UPDATE INVENTORY SET store_id = ? "
					+ " WHERE inventory_id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, inventory.getInventoryId());
			pstmt.setInt(3, inventory.getStoreId());
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
	public boolean insere(Inventory inventory) {
		String sql 	= "INSERT INTO INVENTORY "
					+ " (film_id, store_id)"
					+ " VALUES (?, ?);";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, inventory.getFilmId());
			pstmt.setInt(2, inventory.getStoreId());
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

}