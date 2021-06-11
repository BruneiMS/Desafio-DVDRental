package com.qintess.tools;

import java.sql.Connection;
import java.util.List;
import com.qintess.dao.DaoActor;
import com.qintess.dao.DaoCategory;
import com.qintess.dao.DaoFilm;
import com.qintess.dao.DaoFilmActor;
import com.qintess.dao.DaoFilmCategory;
import com.qintess.dao.DaoInventory;
import com.qintess.dao.DaoLanguage;
import com.qintess.dao.DaoStore;
import com.qintess.exceptions.DuplicatedItemException;
import com.qintess.exceptions.FailedInsertException;
import com.qintess.exceptions.NoSuchItemException;
import com.qintess.modelos.Actor;
import com.qintess.modelos.Category;
import com.qintess.modelos.Film;
import com.qintess.modelos.FilmActor;
import com.qintess.modelos.FilmCategory;
import com.qintess.modelos.Inventory;
import com.qintess.modelos.Language;

public class FilmTools {
	
	private Connection conn;
	

	public FilmTools(Connection conn) {
		super();
		this.conn = conn;
	}


	/**
	 * Metodo para adicionar novos filmes no banco de dados
	 * @param filme Dados do filme a ser inserido
	 * @param actors Dados dos atores
	 */
	public void adicionaFilme(DadosFilm filme, DadosActor... actors){
		DaoFilm daoF = new DaoFilm(this.conn);
		DaoActor daoA = new DaoActor(this.conn);
		int filmId = 0;
		try {
			if(daoF.verificaTitulo(filme.getTitulo())){
				throw new DuplicatedItemException("Filme " + filme.getTitulo() + " ja esta no banco");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		DaoLanguage daoL = new DaoLanguage(conn);
		if(!daoL.verificaIdioma(filme.getIdioma())) {
			Language idioma = new Language(filme.getIdioma());
			try {
				if (!daoL.insere(idioma)) {
					throw new FailedInsertException("Falha ao inserir idioma " + filme.getIdioma());
				}
				System.out.println("Idioma " + filme.getIdioma() + " inserido");
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}
		int idIdioma = daoL.buscaPorNome(filme.getIdioma());
		
		Film inFilm = new Film(filme.getTitulo(), filme.getDescricao(), filme.getAnoLancamento(), idIdioma,
				filme.getTempoAluguel(), filme.getPrecoAluguel(), filme.getDuracao(), filme.getValorReposicao(), 
				filme.getClassificacao(), filme.getExtras());
		
		try {
			if(!daoF.insere(inFilm)) {
				throw new FailedInsertException("Falha ao inserir filme");
			}
			System.out.println("Filme " + filme.getTitulo() + " inserido");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		filmId = daoF.buscaPorTitulo(filme.getTitulo());
			
		DaoCategory daoC = new DaoCategory(conn);
		for (String categoria : filme.getCategorias()) {
			if(!daoC.verificaNome(categoria)) {
				Category addCat = new Category(categoria);
				daoC.insere(addCat);
				System.out.println("Categoria " + categoria + " inserida");
			}
			if (filmId != 0) {
				int catId = daoC.buscaIdPorNome(categoria);
				linkaFilmCategory(filmId, catId);
			}
		}
		
		for (DadosActor actor : actors) {
			if (!daoA.verificaNome(actor.getNome(), actor.getSobrenome())) {
				Actor at = new Actor(actor.getNome(), actor.getSobrenome());
				daoA.insere(at);
				System.out.println("Ator/Atriz " + actor.getNome() + " " + actor.getSobrenome() + " inserido(a)");
				}
			if(filmId != 0) {
				int actorId = daoA.buscaIdPorNome(actor.getNome(), actor.getSobrenome());
				linkaFilmActor(filmId, actorId);
			}
		}
		
	}
	
	/**
	 * Metodo para criar ligacao entre filmes e atores
	 * @param filmId Numero de id do filme
	 * @param actorId Numero de id do ator
	 * @return boolean True para insercao feita com sucesso
	 */
	private boolean linkaFilmActor(int filmId, int actorId) {
		FilmActor fa = new FilmActor(actorId, filmId);
		DaoFilmActor daoF = new DaoFilmActor(this.conn);
		boolean in = daoF.insere(fa);
		return in;
	}
	
	/**
	 * Metodo para criar ligacao entre filme e categoria
	 * @param filmId Numero de id do filme
	 * @param categoryId Numero de id da categoria
	 * @return boolean True para insercao feita com sucesso
	 */
	private boolean linkaFilmCategory(int filmId, int categoryId) {
		FilmCategory fc = new FilmCategory(filmId, categoryId);
		DaoFilmCategory daoF = new DaoFilmCategory(this.conn);
		boolean in = daoF.insere(fc);
		return in;
	}
	
	/**
	 * Metodo para listar filmes presentes no banco de dados
	 */
	public void listaFilmes() {
		DaoFilm daoF = new DaoFilm(this.conn);
		List<Film> lista = daoF.listaTodos();
		for (Film f : lista) {
			System.out.println("ID: " + f.getFilmId() + " | " + f.getTitle() + " | " + f.getRentalRate() + " | " + 
					f.getLength() + " min | " + f.getLanguageId());
		}
	}
	
	/**
	 * Metodo para listar atores presentes no banco
	 */
	public void listaAtores() {
		DaoActor daoA = new DaoActor(this.conn);
		List<Actor> lista = daoA.listaTodos();
		for (Actor a : lista) {
			System.out.println("ID: " + a.getActorId() + " | " + a.getFirstName() + " | " + a.getLastName());
		}
	}
	
	/**
	 * Metodo para listar filmes de um ator especifico
	 * @param id Numero de id do ator
	 */
	public void listaFilmesDoAtor(int id) {
		DaoFilmActor daoFa = new DaoFilmActor(conn);
		DaoFilm daoF = new DaoFilm(conn);
		DaoActor daoA = new DaoActor(conn);
		Actor ator = daoA.buscaPorId(id);
		System.out.println("Lista de filmes de " + ator.getFirstName() + " " + ator.getLastName());
		List<Integer> lista = daoFa.listaFilmesDoAtor(id);
		for (Integer filmId : lista) {
			Film filme = daoF.buscaPorId(filmId);
			System.out.println(filme.getTitle());
		}
	}
	
	/**
	 * Metodo para listar atores presentes em um determinado filme
	 * @param id Numero de id do filme
	 */
	public void listaAtoresDoFilme(int id) {
		DaoFilmActor daoFa = new DaoFilmActor(conn);
		DaoFilm daoF = new DaoFilm(conn);
		DaoActor daoA = new DaoActor(conn);
		Film filme = daoF.buscaPorId(id);
		System.out.println("Lista de atores do filme " + filme.getTitle());
		List<Integer> lista = daoFa.listaAtoresdoFilme(id);
		for (Integer actorId : lista) {
			 Actor ator = daoA.buscaPorId(actorId);
			System.out.println(ator.getFirstName() + " " + ator.getLastName());
		}
	}
	
	/**
	 * Metodo para adicionar filmes ao inventario da loja
	 * @param filmId Numero de id do filme
	 * @param storeId Numero de id da loja
	 * @param qtd Quantidade de copias
	 */
	public void adicionaInventario(int filmId, int storeId, int qtd) {
		
		DaoStore daoS = new DaoStore(conn);
		try {
			if(!daoS.verificaStore(storeId)) {
				throw new NoSuchItemException("Loja com id: " + storeId +" nao encontrada");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		DaoFilm daoF = new DaoFilm(this.conn);
		try {
			if(daoF.buscaPorId(filmId) == null){
				throw new NoSuchItemException("Filme com id: " + filmId +" nao encontrado");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		Film filme = daoF.buscaPorId(filmId);
		
		DaoInventory daoI = new DaoInventory(this.conn);
		for(int i = 0; i < qtd; i++) {
			Inventory inv = new Inventory(filmId, storeId);
			daoI.insere(inv);
		}
		
		System.out.println(qtd + " do filme " + filme.getTitle() + " adicionados a loja " + storeId);
	}
}


