/**
 * 
 */
package opet.mygarage.model.persistencia;

import java.util.List;

import opet.mygarage.vo.TimelineAcao;

/**
 * Classe Persistencia do TimelineAcaoDAO
 * 
 * @author Juliano Zapelini Batista / Tiago Mazzer
 * 
 * @since 13/05/2016
 * 
 * @version 1.0
 * 
 */
public class PersistenciaTimelineAcao implements ITimelineAcaoDAO {

	/*
	 * Variáveis de instância
	 */
	TimelineAcaoDAO timelineAcaoDAO;
	
	/*
	 * Função construtora
	 */
	public PersistenciaTimelineAcao(){
		timelineAcaoDAO = new TimelineAcaoDAO();
	}
	
	/*
	 * Operações da classe
	 */
	
	
	/**
	 * Acessa TimelineAcaoDAO para listar os Comentarios/Curtir da base Timeline_Acao
	 * 
	 * @see opet.mygarage.model.persistencia.ITimelineAcaoDAO#listaTimelineAcaoDAO()
	 */
	@Override
	public List<TimelineAcao> listaTimelineAcaoDAO(Integer idTimeline) {
		return timelineAcaoDAO.listaTimelineAcaoDAO(idTimeline);
	}

	/**
	 * Acessa TimelineAcaoDAO para cadastrar comentario na base Timeline_Acao
	 * 
	 * @see opet.mygarage.model.persistencia.ITimelineAcaoDAO#cadastraComentarioPostDAO(opet.mygarage.vo.TimelineAcao)
	 */
	@Override
	public Boolean cadastraTimelineAcaoDAO(TimelineAcao timelineAcao) {
		return timelineAcaoDAO.cadastraTimelineAcaoDAO(timelineAcao);
	}


	/**
	 * Acessa TimelineAcaoDAO para excluir o curtir na base Timeline_Acao
	 * 
	 * @see opet.mygarage.model.persistencia.ITimelineAcaoDAO#excluiLikeTimelineAcaoDAO(opet.mygarage.vo.TimelineAcao)
	 */
	@Override
	public Boolean excluiLikeTimelineAcaoDAO(TimelineAcao timelineAcao) {
		return timelineAcaoDAO.excluiLikeTimelineAcaoDAO(timelineAcao);
	}
	/**
	 * Acessa TimelineAcaoDAO para excluir o comentario na base Timeline_Acao
	 * 
	 * @see opet.mygarage.model.persistencia.ITimelineAcaoDAO#excluiTimelineAcaoDAO(opet.mygarage.vo.TimelineAcao)
	 */
	@Override
	public Boolean excluiTimelineAcaoDAO(Integer idTimelineAcao) {
		return timelineAcaoDAO.excluiTimelineAcaoDAO(idTimelineAcao);
	}
	
	/**
	 * Acessa TimelineAcaoDAO para excluir o comentario na base Timeline_Acao
	 * 
	 * @see opet.mygarage.model.persistencia.ITimelineAcaoDAO#excluiTimelineAcaoDAO(opet.mygarage.vo.TimelineAcao)
	 */
	@Override
	public Boolean excluiTimelineAcaoByTimelineDAO(Integer idTimeline) {
		return timelineAcaoDAO.excluiTimelineAcaoByTimelineDAO(idTimeline);
	}
}
