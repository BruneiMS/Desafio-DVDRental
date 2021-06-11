package com.qintess.tools;

public class DadosStaff {
	
	private String nome;
	private String sobrenome;
	private String email;
	private String usuario;
	private String senha;
	
	public DadosStaff(String nome, String sobrenome, String email, String usuario, String senha) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.usuario = usuario;
		this.senha = senha;
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

	public String getUsuario() {
		return usuario;
	}

	public String getSenha() {
		return senha;
	}
	
	

}
