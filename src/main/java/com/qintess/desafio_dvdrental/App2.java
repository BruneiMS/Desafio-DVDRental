package com.qintess.desafio_dvdrental;

import java.sql.Connection;

import com.qintess.conexao.Conexao;
import com.qintess.tools.DadosActor;
import com.qintess.tools.DadosFilm;
import com.qintess.tools.FilmTools;

public class App2 {
	
	public static void main(String[] args) {
		
		try (Connection conn = Conexao.conecta()){
			//Dados dos filmes e atores
			DadosFilm filme = new DadosFilm("Carros", "Vruuuum", 2002, "Carreano", 5, 4.88, 120, 15.99, "PG", "{Sennas fortes, Making of}", "Animation, Race");
			DadosActor ator1 = new DadosActor("Relampago", "Marquinhos");
			DadosActor ator2 = new DadosActor("To", "Mate");
			DadosActor ator3 = new DadosActor("Rubens", "Barrichello");
			FilmTools operador = new FilmTools(conn);
			
			//Insere filme no banco
			//operador.adicionaFilme(filme, ator1, ator2, ator3);
			
			//Lista filmes do banco
			//operador.listaFilmes();
			
			//Adiciona filme no inventario
			//operador.adicionaInventario(1001, 1, 4);
			
			//Lista atores de um filme
			//operador.listaAtoresDoFilme(1001);
			
			//Lista filmes de um ator
			//operador.listaFilmesDoAtor(203);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
