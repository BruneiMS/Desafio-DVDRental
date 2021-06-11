package com.qintess.tools;

import java.sql.Connection;
import java.util.List;

import com.qintess.dao.DaoAddress;
import com.qintess.dao.DaoCity;
import com.qintess.dao.DaoCountry;
import com.qintess.dao.DaoCustomer;
import com.qintess.dao.DaoStore;
import com.qintess.exceptions.DuplicatedItemException;
import com.qintess.exceptions.FailedInsertException;
import com.qintess.exceptions.NoSuchItemException;
import com.qintess.modelos.Address;
import com.qintess.modelos.City;
import com.qintess.modelos.Country;
import com.qintess.modelos.Customer;

public class CustomerTools {

	private Connection conn;

	public CustomerTools(Connection conn) {
		super();
		this.conn = conn;
	}

	/**
	 * Metodo para cadastrar cliente
	 * @param cliente Dados do cliente
	 * @param endereco Dados do endereco
	 * @param loja Numero da loja
	 */
	public void acicionaCliente(DadosCustomer cliente, DadosAddress endereco, int loja) {
		DaoCustomer daoC = new DaoCustomer(this.conn);
		try {
			if(daoC.verificaCliente(cliente.getNome(), cliente.getSobrenome(), cliente.getEmail())){
				throw new DuplicatedItemException(cliente.getNome() + " " + cliente.getSobrenome() + 
						" ja esta presente no banco");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		DaoStore daoS = new DaoStore(conn);
		try {
			if(!daoS.verificaStore(loja)) {
				throw new NoSuchItemException("Loja com id: " + loja +" nao encontrada");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		int idEndereco = registraEndereco(endereco);
		if (idEndereco == 0) {
			return;
		}
		
		Customer inCustomer = new Customer(loja, cliente.getNome(), cliente.getSobrenome(), cliente.getEmail(), 
				idEndereco, true, 1);
		try {
			if (!daoC.insere(inCustomer)) {
				throw new FailedInsertException("Falha ao inserir cliente: " + cliente.getEmail());
			}
			System.out.println("Cliente inserido: " + cliente.getNome() + " " + cliente.getSobrenome());
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	
	/**
	 * Metodo para buscar cliente no banco
	 * @param nome Nome do cliente
	 * @param sobrenome Sobrenome do cliente
	 * @param email Email do cliente
	 */
	public void buscaCliente(String nome, String sobrenome, String email) {
		DaoCustomer daoC = new DaoCustomer(this.conn);
		DaoAddress daoA = new DaoAddress(this.conn);
		DaoCity daoCi = new DaoCity(this.conn);
		DaoCountry daoCo = new DaoCountry(this.conn);
		try {
			if(!daoC.verificaCliente(nome, sobrenome, email)) {
				throw new NoSuchItemException("Cliente nao encontrado");
			}
			int idCliente = daoC.retornaIdCliente(nome, sobrenome, email);
			Customer c = daoC.buscaPorId(idCliente);
			Address a = daoA.buscaPorId(c.getAddressId());
			City ci = daoCi.buscaPorId(a.getCity_id());
			Country co = daoCo.buscaPorId(ci.getCountryId());
			System.out.println("Cliente id: " + c.getId() + " | " + c.getFirstName() + " | " + c.getLastName() + " | " + 
					c.getEmail() + " | " + a.getAddress() + " | " + ci.getCity() + " | " + co.getCountry() + " | " + c.getActive());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo para registrar endereco no banco
	 * @param endereco Dados do endereco
	 * @return boolean True para insercao feita com sucesso
	 */
	private int registraEndereco(DadosAddress endereco) {
		int idEndereco = 0;
		DaoAddress daoA = new DaoAddress(this.conn);
		if(daoA.verificaEndereco(endereco.getEndereco(), endereco.getComplemento(), endereco.getTelefone())) {
			idEndereco = daoA.buscaIdPorNome(endereco.getEndereco(), endereco.getComplemento(), endereco.getTelefone());
		} else {
			DaoCountry daoCo = new DaoCountry(this.conn);
			if(!daoCo.verificaIdPorPais(endereco.getPais())) {
				Country coun = new Country(endereco.getPais());
				try {
					if (!daoCo.insere(coun)) {
						throw new FailedInsertException("Falha ao inserir pais: " + endereco.getPais());
					}
					System.out.println("Pais inserido: " + endereco.getPais());
				} catch (Exception e) {
					e.printStackTrace();
					return 0;
				}
			}
			int idPais = daoCo.buscaIdPorPais(endereco.getPais());

			
			DaoCity daoCi = new DaoCity(conn);
			if(!daoCi.verificaCidade(endereco.getCidade(), endereco.getPais())) {
				City cid = new City(endereco.getCidade(), idPais);
				try {
					if (!daoCi.insere(cid)) {
						throw new FailedInsertException("Falha ao inserir cidade: " + endereco.getCidade());
					}
					System.out.println("Cidade inserida: " + endereco.getCidade());
				} catch (Exception e) {
					e.printStackTrace();
					return 0;
				}
			}
			int idCid = daoCi.buscaIdPorCidade(endereco.getCidade(), endereco.getPais());

			
			Address end = new Address(endereco.getEndereco(), endereco.getComplemento(), endereco.getDistrito(), 
					idCid, endereco.getCep(), endereco.getTelefone());
			try {
				if (!daoA.insere(end)) {
					throw new FailedInsertException("Falha ao inserir endereco: " + endereco.getEndereco());
				}
				System.out.println("Endereco inserido: " + endereco.getEndereco());
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
			idEndereco = daoA.buscaIdPorNome(endereco.getEndereco(), endereco.getComplemento(), endereco.getTelefone());
		}
		return idEndereco;
	}
	
	/**
	 * Metodo para buscar clientes pelo id
	 * @param id Numero de id do cliente
	 */
	public void buscaClienteId(int id) {
		DaoCustomer daoC = new DaoCustomer(this.conn);
		DaoAddress daoA = new DaoAddress(this.conn);
		DaoCity daoCi = new DaoCity(this.conn);
		DaoCountry daoCo = new DaoCountry(this.conn);
		try {
			if(daoC.buscaPorId(id) == null) {
				throw new NoSuchItemException("Cliente nao encontrado");
			}
			Customer c = daoC.buscaPorId(id);
			Address a = daoA.buscaPorId(c.getAddressId());
			City ci = daoCi.buscaPorId(a.getCity_id());
			Country co = daoCo.buscaPorId(ci.getCountryId());
			System.out.println("Cliente id: " + c.getId() + " | " + c.getFirstName() + " | " + c.getLastName() + " | " + 
					c.getEmail() + " | " + a.getAddress() + " | " + ci.getCity() + " | " + co.getCountry() + " | " + c.getActive());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Metodo para listar clientes presentes no banco
	 */
	public void listaClientes() {
		DaoCustomer daoC = new DaoCustomer(this.conn);
		DaoAddress daoA = new DaoAddress(this.conn);
		DaoCity daoCi = new DaoCity(this.conn);
		DaoCountry daoCo = new DaoCountry(this.conn);
		List<Customer> lista = daoC.listaTodos();
		for (Customer c : lista) {
			Address a = daoA.buscaPorId(c.getAddressId());
			City ci = daoCi.buscaPorId(a.getCity_id());
			Country co = daoCo.buscaPorId(ci.getCountryId());
			System.out.println("Id: " + c.getId() + " | " + c.getFirstName() + " | " + c.getLastName() + " | " + 
					c.getEmail() + " | " + a.getAddress() + " | " + ci.getCity() + " | " + co.getCountry() + " | " + c.getActive());
		}

	}
	
	
	/** Metodo para desativar cadastro de cliente
	 * @param id Numero de id do cliente
	 */
	public void desativaCliente(int id) {
		DaoCustomer daoC = new DaoCustomer(this.conn);
		Customer c = daoC.buscaPorId(id);
		c.setActive(0);
		c.setActivebool(false);
		daoC.altera(c);
		System.out.println("Cliente " + c.getId() + " desativado");
	}
	
	/**
	 * Metodo para ativar cadastro de cliente
	 * @param id Numero de id do cliente
	 */
	public void ativaCliente(int id) {
		DaoCustomer daoC = new DaoCustomer(this.conn);
		Customer c = daoC.buscaPorId(id);
		c.setActive(1);
		c.setActivebool(true);
		daoC.altera(c);
		System.out.println("Cliente " + c.getId() + " ativado");
	}

}
