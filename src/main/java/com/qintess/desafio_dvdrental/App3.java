package com.qintess.desafio_dvdrental;

import java.sql.Connection;

import com.qintess.conexao.Conexao;
import com.qintess.tools.DadosAddress;
import com.qintess.tools.DadosStaff;
import com.qintess.tools.StaffTools;

public class App3 {
	public static void main(String[] args) {
		try (Connection conn = Conexao.conecta()){
			DadosStaff gerente = new DadosStaff("Joao", "das Couves", "joao@bol.com", "joaozinho", "123456");
			DadosAddress enderecoGerente = new DadosAddress("456 Rua das Laranjeiras", "", "Xavante", "7788", "112235455", "Limeira", "Brunei");
			DadosAddress enderecoLoja = new DadosAddress("26 Rua das Laranjeiras", "", "Xavante", "7788", "112235455", "Limeira", "Brunei");
			StaffTools operador = new StaffTools(conn);
			
			
			//Adiciona loja
			//operador.adicionaLoja(gerente, enderecoGerente, enderecoLoja);
			
			//Lista as lojas
			//operador.listaLojas();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
