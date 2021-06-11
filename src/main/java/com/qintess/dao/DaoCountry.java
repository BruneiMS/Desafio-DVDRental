package com.qintess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PSQLException;

import com.qintess.modelos.Country;

public class DaoCountry implements DaoBase<Country> {

	private Connection conn;
	/**
	 * Metodo construtor
	 * @param conn Conexao com o banco de dados
	 */
	public DaoCountry(Connection conn) {
		super();
		this.conn = conn;
	}

	/**
	 * Medoto que lista os paises presentes no banco de dados
	 * @return countryLista ArrayList dos paises
	 */
	@Override
	public List<Country> listaTodos() {
		String sql = "SELECT country_id, country FROM COUNTRY;";
		List<Country> countryLista = new ArrayList<Country>();
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Country c = new Country(rs.getInt("country_id"), rs.getString("country"));
				countryLista.add(c);
			}
			return countryLista;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Metodo que retorna um pais dado seu id
	 * @param id Numero de ID a ser buscado
	 * @return c Objeto Country do pais encontrado
	 */
	@Override
	public Country buscaPorId(int id) {
		String sql = "SELECT country_id, country FROM COUNTRY WHERE country_id = ?;";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			try {
				rs.next();
				Country c = new Country(rs.getInt("country_id"), rs.getString("country"));
				return c;
			} catch (PSQLException e) {
				System.out.println("Country nao encontrado");
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

	/**
	 * Metodo que altera pais no banco
	 * @param country Objeto Country do pais a ser alterado
	 * @return retorna true para alteracao realizada e false para falha na alteracao
	 */
	@Override
	public boolean altera(Country country) {
		String sql = "UPDATE COUNTRY SET "
				+ "country = ? "
				+ "WHERE country_id = ?;";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, country.getCountry());
			pstmt.setInt(2, country.getId());
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

	/**
	 * Metodo para inserir novo pais no banco
	 * @param country Objeto Country do pais a ser adicionado
	 * @return boolean True para insercao realizada e false para falha na insercao
	 */
	@Override
	public boolean insere(Country country) {
		String sql = "INSERT INTO COUNTRY "
				+ "(country) VALUES (?);";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, country.getCountry());
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
	
	
	/**
	 * Metodo para verificar se pais ja esta presente no banco
	 * @param pais Nome do pais a ser verificado
	 * @return boolean Retorna true se pais ja esta presente e false se nao
	 */
	public boolean verificaPais(String pais) {
		String sql = "SELECT country_id FROM COUNTRY "
				+ "WHERE country = ?;";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, pais);
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
	
	public boolean verificaIdPorPais(String nome) {
		String sql = "SELECT country_id"
				+ "	FROM country"
				+ "	WHERE country = ?";
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
	
	public int buscaIdPorPais(String nome) {
		String sql = "SELECT country_id"
				+ "	FROM country"
				+ "	WHERE country = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, nome);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("country_id");
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}

