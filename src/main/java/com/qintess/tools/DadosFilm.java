package com.qintess.tools;

public class DadosFilm {

	private String titulo;
	private String descricao;
	private  int anoLancamento;
	private String idioma;
	private int tempoAluguel;
	private double precoAluguel;
	private int duracao;
	private double valorReposicao;
	private String classificacao;
	private String extras;
	private String[] categorias;
	
	
	public DadosFilm(String titulo, String descricao, int anoLancamento, String idioma, int tempoAluguel,
			double precoAluguel, int duracao, double valorReposicao, String classificacao, String extras,
			 String... categorias) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		this.anoLancamento = anoLancamento;
		this.idioma = idioma;
		this.tempoAluguel = tempoAluguel;
		this.precoAluguel = precoAluguel;
		this.duracao = duracao;
		this.valorReposicao = valorReposicao;
		this.classificacao = classificacao;
		this.extras = extras;
		this.categorias = categorias;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getAnoLancamento() {
		return anoLancamento;
	}

	public String getIdioma() {
		return idioma;
	}

	public int getTempoAluguel() {
		return tempoAluguel;
	}

	public double getPrecoAluguel() {
		return precoAluguel;
	}

	public int getDuracao() {
		return duracao;
	}

	public double getValorReposicao() {
		return valorReposicao;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public String getExtras() {
		return extras;
	}

	public String[] getCategorias() {
		return categorias;
	}
	
}
