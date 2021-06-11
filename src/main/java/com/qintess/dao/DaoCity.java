package com.qintess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.postgresql.util.PSQLException;
import com.qintess.modelos.City;


public class DaoCity implements DaoBase<City>{

	private Connection conn;
	public DaoCity(Connection conn) {
		super();
		this.conn = conn;
	}

	/**
	 * Metodo para listar as cidades no banco
	 * @return Retorna ArrayList com os objetos City das cidades presentes no banco
	 */
	@Override
	public List<City> listaTodos() {
		String sql = "SELECT city_id, city, country_id FROM CITY;";
		List<City> cityLista = new ArrayList<City>();
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				City c = new City(	rs.getInt("city_id"), 
									rs.getString("city"),
									rs.getInt("country_id"));
				cityLista.add(c);
			}
			return cityLista;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Metodo para buscar cidade por id no banco
	 * @param id Numero de id a ser buscado
	 * @return c Retorna o objeto da cidade encontrada
	 */
	@Override
	public City buscaPorId(int id) {
		String sql = "SELECT city_id, city, country_id FROM CITY WHERE city_id = ?;";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			try {
				rs.next();
				City c = new City(  rs.getInt("city_id"), 
									rs.getString("city"),
									rs.getInt("country_id"));
				return c;
			} catch (PSQLException e) {
				System.out.println("City nao encontrado!");
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
	 * Metodo para realizar alteracoes em cidade no banco
	 * @param city Objeto da cidade a ser alterada
	 * @return boolean Retorna true caso alteracao seja realizada com sucesso
	 */
	@Override
	public boolean altera(City city) {
		String sql 	= " UPDATE CITY SET "
					+ " city = ? "
					+ " WHERE city_id = ?;";

		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, city.getCity());
			pstmt.setInt(2, city.getCityId());
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

	/**
	 * Metodo para inserir cidade no banco
	 * @param city Objeto City da cidade a ser inserida
	 * @return boolean Retorna true se insercao for realizada com successo
	 */
	@Override
	public boolean insere(City city) {
		String sql 	= " INSERT INTO CITY "
					+ " (city, country_id) VALUES (?, ?);";

		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, city.getCity());
			pstmt.setInt(2, city.getCountryId());
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
	
	/**
	 * @param cidade Nome da cidade a ser verificada
	 * @param pais Nome do pais em que a cidade esta localizada
	 * @return boolean Retorna true caso a cidade esteja presente no banco
	 */
	public boolean verificaCidade(String cidade, String pais) {
		String sql = "SELECT CITY.city_id FROM CITY "
				+ "INNER JOIN COUNTRY ON CITY.country_id = COUNTRY.country_id "
				+ "WHERE CITY.city = ? AND COUNTRY.country = ?;";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, cidade);
			pstmt.setString(2, pais);
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
	
	
	/**
	 * Metodo que retorna id de determinada cidade
	 * @param cidade
	 * @param pais
	 * @return int Numero de id da cidade. Retorna 0 se cidade nao esta presente
	 */
	public int buscaIdPorCidade(String cidade, String pais) {
		String sql = "SELECT CITY.city_id FROM CITY "
				+ "INNER JOIN COUNTRY ON CITY.country_id = COUNTRY.country_id "
				+ "WHERE CITY.city = ? AND COUNTRY.country = ?;";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, cidade);
			pstmt.setString(2, pais);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("city_id");
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}