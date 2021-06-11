package com.qintess.tools;

import java.sql.Connection;
import java.util.List;

import com.qintess.dao.DaoAddress;
import com.qintess.dao.DaoCity;
import com.qintess.dao.DaoCountry;
import com.qintess.dao.DaoStaff;
import com.qintess.dao.DaoStore;
import com.qintess.exceptions.FailedInsertException;
import com.qintess.modelos.Address;
import com.qintess.modelos.City;
import com.qintess.modelos.Country;
import com.qintess.modelos.Staff;
import com.qintess.modelos.Store;

public class StaffTools {

	private Connection conn;

	public StaffTools(Connection conn) {
		super();
		this.conn = conn;
	}
	
	/**
	 * Metodo para adicionar nova loja ao banco
	 * @param staff Dados do gerente da nova loja
	 * @param enderecoStaff Endereco do gerente
	 * @param enderecoLoja Endereco da loja
	 */
	public void adicionaLoja(DadosStaff staff, DadosAddress enderecoStaff, DadosAddress enderecoLoja) {
		DaoStaff daoStaff = new DaoStaff(this.conn);
		DaoStore daoStore = new DaoStore(this.conn);
		
		int idEnderecoStaff = registraEndereco(enderecoStaff);
		if(idEnderecoStaff == 0) {
			return;
		}
		
		int idEnderecoLoja = registraEndereco(enderecoLoja);
		if(idEnderecoLoja == 0) {
			return;
		}
		
		Staff inStaff = new Staff(staff.getNome(), staff.getSobrenome(), idEnderecoStaff, 
				staff.getEmail(), true, staff.getUsuario(), staff.getSenha());
		daoStaff.insere(inStaff);
		int idStaff = daoStaff.buscaPorUsername(staff.getUsuario());
		
		Store inStore = new Store(idStaff, idEnderecoLoja);
		try {
			if(!daoStore.insere(inStore)){
				throw new FailedInsertException("Falha ao inserir loja");
			}
			int idStore = daoStore.buscaPorEndereco(idEnderecoLoja);
			inStaff.setStoreId(idStore);
			daoStaff.altera(inStaff);
			System.out.println("Loja " + idStore + " adicionada");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	
	/**
	 * Metodo para inserir endereco no banco
	 * @param endereco Dados do endereco
	 * @return boolean Retorna True para insercao feita com sucesso
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
	 * Metodo para listar lojas
	 */
	public void listaLojas() {
		DaoStore daoStore = new DaoStore(this.conn);
		DaoAddress daoA = new DaoAddress(this.conn);
		DaoStaff daoStaff = new DaoStaff(this.conn);
		List<Store> lista = daoStore.listaTodos();
		for (Store store : lista) {
			Address a = daoA.buscaPorId(store.getAddressId());
			Staff s = daoStaff.buscaPorId(store.getManagerId());
			System.out.println("ID da loja: " + store.getStoreId() + " |  Gerente " + s.getFirst_name() + " " + s.getLast_name() + " | " + a.getAddress());
		}
	}
	
}
