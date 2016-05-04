/**
 * 
 */
package opet.mygarage.model.persistencia;

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

}
