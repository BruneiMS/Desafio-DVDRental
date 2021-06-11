package com.qintess.tools;

public class DadosCustomer {

	private String nome;
	private String sobrenome;
	private String email;
	
	public DadosCustomer(String nome, String sobrenome, String email) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public String getEmail() {
		return email;
	}
	
	
}
