package com.qintess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.qintess.modelos.Actor;

public class DaoActor implements DaoBase<Actor>{
	
	private Connection conn;
	
	public DaoActor(Connection conn) {
		this.conn = conn;
	}

	/**
	 * Metodo para listar todos os atores presentes no banco
	 * @return actorLista ArrayList com os Objetos Actor dos atores
	 */
	@Override
	public List<Actor> listaTodos() {
		String sql = "SELECT actor_id, first_name, last_name FROM ACTOR;";
		List<Actor> actorLista = new ArrayList<Actor>();
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Actor a = new Actor(rs.getInt("actor_id"), rs.getString("first_name"), rs.getString("last_name"));
				actorLista.add(a);
			}
			return actorLista;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Metodo para encontrar ator pelo id
	 * @param id Numero da id a ser buscada
	 * @return a Objeto Actor do ator encontrado
	 */
	@Override
	public Actor buscaPorId(int id) {
		String sql = "SELECT actor_id, first_name, last_name FROM ACTOR "
				+ "WHERE actor_id = ?;";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Actor a = new Actor(rs.getInt("actor_id"), rs.getString("first_name"), rs.getString("last_name"));
				return a;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 *Metodo para deletar filme do banco
	 *@param id Numero de id do ator a ser deletado
	 *@return boolean True se filme for apagado com sucesso
	 */
	@Override
	public boolean deleta(int id) {
		String sql = "DELETE FROM ACTOR WHERE actor_id = ?;";
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

	/**
	 * Metodo para alterar ator no banco
	 * @param actor Objeto Actor do ator atualizado
	 * @return boolean Retorna true para alteracao realizada com sucesso e false para falha
	 */
	@Override
	public boolean altera(Actor actor) {
		String sql = "UPDATE ACTOR SET "
				+ "first_name = ?,"
				+ "last_name = ?"
				+ "WHERE actor_id = ?;";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, actor.getFirstName());
			pstmt.setString(2, actor.getLastName());
			pstmt.setInt(3, actor.getActorId());
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
	 * Metodo para inserir ator
	 * @param actor Classe Actor do ator a ser inserido
	 * @return boolean Retorna true caso insercao seja realizada e false para falha
	 */
	@Override
	public boolean insere(Actor actor) {
		String sql = "INSERT INTO ACTOR "
				+ "(first_name, last_name) "
				+ "VALUES (?, ?);";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, actor.getFirstName());
			pstmt.setString(2, actor.getLastName());
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
	 * Metodo para verificar se ator ja esta presente no banco
	 * @param nome Primeiro nome do ator
	 * @param sobrenome Sobrenome do ator a ser verificado
	 * @return Retorna true caso ator ja esteja presente no banco
	 */
	public boolean verificaNome(String nome, String sobrenome) {
		String sql  = " SELECT actor_id FROM ACTOR "
					+ " WHERE first_name = ? "
					+ " AND last_name = ?;";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, nome);
			pstmt.setString(2, sobrenome);
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
	
	public int buscaIdPorNome(String nome, String sobrenome) {
		String sql = "SELECT actor_id"
				+ "	FROM ACTOR"
				+ "	WHERE first_name = ? AND last_name = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, nome);
			pstmt.setString(2, sobrenome);
			ResultSet rs = pstmt.executeQuery();
			try {
				rs.next();
				return rs.getInt("actor_id");
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
