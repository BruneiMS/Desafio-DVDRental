package com.qintess.desafio_dvdrental;

import java.sql.Connection;

import com.qintess.conexao.Conexao;
import com.qintess.tools.CustomerTools;
import com.qintess.tools.DadosAddress;
import com.qintess.tools.DadosCustomer;
import com.qintess.tools.FilmTools;

public class App1 {
	
	public static void main(String[] args) {

		try (Connection conn = Conexao.conecta()){
			
			//Inserir dados do cliente e o endereco
			
			CustomerTools operador = new CustomerTools(conn);
			DadosCustomer cliente = new DadosCustomer("Brunei", "Mourao", "bru@gmail.com");
			DadosAddress endereco = new DadosAddress("0 Rua dos Bobos", "", "Penha", "8777343", "420666171", "Santa Branca", "Brunelandia");
			
			//Cadastra o cliente
			//operador.acicionaCliente(cliente, endereco, 1);
			
			
			//Busca o cliente pelo nome
			//operador.buscaCliente("Brunei", "Mourao", "bru@gmail.com");
			
			
			//Desativa o cadastro do cliente
			//operador.desativaCliente(600);
			
			
			//Lista todos os clientes
			operador.listaClientes();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}
