package com.qintess.tools;

public class DadosAddress {
	
	private String endereco;
	private String complemento;
	private String distrito;
	private String cep;
	private String telefone;
	private String cidade;
	private String pais;
	
	
	public DadosAddress(String endereco, String complemento, String distrito, String cep, String telefone, String cidade,
			String pais) {
		super();
		this.endereco = endereco;
		this.complemento = complemento;
		this.distrito = distrito;
		this.cep = cep;
		this.telefone = telefone;
		this.cidade = cidade;
		this.pais = pais;
	}


	public String getEndereco() {
		return endereco;
	}


	public String getComplemento() {
		return complemento;
	}


	public String getDistrito() {
		return distrito;
	}


	public String getCep() {
		return cep;
	}


	public String getTelefone() {
		return telefone;
	}


	public String getCidade() {
		return cidade;
	}


	public String getPais() {
		return pais;
	}
	
	
}
