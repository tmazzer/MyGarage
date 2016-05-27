/**
 * 
 */
package opet.mygarage.model.persistencia;

import java.util.List;

import opet.mygarage.vo.Acessorios;
import opet.mygarage.vo.Carro;

/**
 * Classe Persistencia do Carro
 * 
 * @author Juliano Zapelini Batista / Tiago Mazzer
 * 
 * @since 19/04/2016
 * 
 * @version 1.0
 * 	
 */
public class PersistenciaCarro implements ICarroDAO {
	
	/*
	 * Variáveis de instância
	 */
	
	public CarroDAO carroDAO;
	
	/*
	 * Função construtora
	 */
	
	public PersistenciaCarro(){
		carroDAO = new CarroDAO();
	}
	/*
	 * Operações da classe
	 */
	
	/**
	 * Cadastra um novo Carro na tabela Carro
	 * 
	 */
	@Override
	public Carro cadastraCarroDAO(Carro carro) {
		return carroDAO.cadastraCarroDAO(carro);
	}
	/**
	 * Consulta Carro existente na tabela Carro
	 */
	@Override
	public Carro consultaCarroDAO(Carro carro) {
		return carroDAO.consultaCarroDAO(carro);
	}
	/**
	 * Exclui CArro existente na tabela CArro
	 * Retorna TRUE para Excluido com sucesso
	 * Retorna FALSE se ERRO ao Excluir
	 */
	@Override
	public Boolean excluiCarroDAO(Carro carro) {
		if(carroDAO.excluiCarroDAO(carro)){
			return true;
		}else{
			return false;
		}		
	}
	/**
	 * Altera Carro existente na tabela Carro
	 */
	@Override
	public Carro alteraCarroDAO(Carro carro) {
		return carroDAO.alteraCarroDAO(carro);
	}
	
	/**
	 * Lista todos os carros do Usuario informado
	 */
	@Override
	public List<Carro> listaCarrosDAO(Integer idUsuario){
		return carroDAO.listaCarrosDAO(idUsuario);
	}

	/**
	 * Cadastra um novo Acessorio na tabela Acessorios
	 * 
	 */
	@Override
	public Acessorios cadastraAcessoriosDAO(Carro carro, Acessorios acessorios) {
		return carroDAO.cadastraAcessoriosDAO(carro, acessorios);
	}

	/**
	 * Consulta Acessorio existente na tabela Acessorios
	 */
	@Override
	public Acessorios consultaAcessoriosDAO(Acessorios acessorios) {
		return carroDAO.consultaAcessoriosDAO(acessorios);
	}
	
	/**
	 * Exclui Acessorio existente na tabela Acessorios
	 * Retorna TRUE para Excluido com sucesso
	 * Retorna FALSE se ERRO ao Excluir
	 */
	@Override
	public Boolean excluiAcessoriosDAO(Acessorios acessorios) {
		if(carroDAO.excluiAcessoriosDAO(acessorios)){
			return true;
		}else{
			return false;
		}	
	}
	/**
	 * Altera Acessorio existente na tabela Acessorios
	 */
	@Override
	public Acessorios alteraAcessoriosDAO(Acessorios acessorios) {
		return carroDAO.alteraAcessoriosDAO(acessorios);
	}

	public List<Acessorios> listaAcessoriosDAO(Carro carro) {
		return carroDAO.listaAcessoriosDAO(carro);
	}

	@Override
	public Boolean validaApelidoCarroDAO(Integer idUsuario, String apelidoCarro) {
		return carroDAO.validaApelidoCarroDAO(idUsuario, apelidoCarro);
	}

	@Override
	public Boolean validaNomeAcessorioDAO(Integer idCarro, String nomeAcessorio) {
		return carroDAO.validaNomeAcessorioDAO(idCarro, nomeAcessorio);
	}

}
