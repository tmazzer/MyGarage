/**
 * 
 */
package opet.mygarage.model.persistencia;

import java.util.List;

import opet.mygarage.vo.Relacionamento;

/**
 * Classe Persistencia da tabela Relacionamento
 * 
 * @author Juliano Zapelini Batista / Tiago Mazzer
 * 
 * @since 04/05/2016
 * 
 * @version 1.0
 * 
 */
public class PersistenciaRelacionamento implements IRelacionamentoDAO {
	/*
	 * Variáveis de instância
	 */
	RelacionamentoDAO relacionamentoDAO;
	
	/*
	 * Função construtora
	 */
	public PersistenciaRelacionamento(){
		relacionamentoDAO = new RelacionamentoDAO();
	}
	
	/*
	 * Operações da classe
	 */
	
	/**
	 * Acessa RelacionamentoDAO para cadastrar relacionamento na base Relacionamento
	 * 
	 * @see opet.mygarage.model.persistencia.IRelacionamentoDAO#cadastraRelacionamentoDAO(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Boolean cadastraRelacionamentoDAO(Integer codigoAmigo, Integer idUsuario) {
		return relacionamentoDAO.cadastraRelacionamentoDAO(codigoAmigo, idUsuario);
	}

	/**
	 * Acessa RelacionamentoDAO para desativar relacionamento na base Relacionamento
	 * 
	 * @see opet.mygarage.model.persistencia.IRelacionamentoDAO#desativaRelacionamentoDAO(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Boolean desativaRelacionamentoDAO(Integer codigoAmigo, Integer idUsuario) {
		return relacionamentoDAO.desativaRelacionamentoDAO(codigoAmigo, idUsuario);
	}

	/**
	 * Acessa RelacionamentoDAO para listar relacionamento na base Relacionamento
	 * 
	 * @see opet.mygarage.model.persistencia.IRelacionamentoDAO#listaAmigosRelacionamentoDAO(java.lang.Integer)
	 */
	@Override
	public List<Relacionamento> listaAmigosRelacionamentoDAO(Integer codigoAmigo) {
		return relacionamentoDAO.listaAmigosRelacionamentoDAO(codigoAmigo);
	}
	
	/**
	 * Acessa RelacionamentoDAO para consultar Relacionamento
	 * 
	 * @see opet.mygarage.model.persistencia.IRelacionamentoDAO#listaAmigosRelacionamentoDAO(java.lang.Integer)
	 */
	@Override
	public Boolean consultaRelacionamentoDAO(Integer codigoAmigo, Integer idUsuario){
		return relacionamentoDAO.consultaRelacionamentoDAO(codigoAmigo, idUsuario);
	}

}
