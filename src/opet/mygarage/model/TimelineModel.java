package opet.mygarage.model;

import java.util.List;

import opet.mygarage.model.persistencia.PersistenciaTimeline;
import opet.mygarage.model.persistencia.PersistenciaTimelineAcao;
import opet.mygarage.vo.Timeline;
import opet.mygarage.vo.TimelineAcao;

/**
 * Classe Model para tratar a lista de Timeline
 * 
 * @author Juliano Zapelini Batista / Tiago Mazzer
 * 
 * @since 09/05/2016
 * 
 * @version 1.0
 * 
 */
public class TimelineModel {
	/*
	 * Variáveis de instância
	 */
	
	private PersistenciaTimeline persistenciaTimeline;
	
	private PersistenciaTimelineAcao persistenciaTimelineAcao;
	
	/*
	 * Função construtora
	 */
	public TimelineModel() {
		persistenciaTimeline = new PersistenciaTimeline();
		persistenciaTimelineAcao = new PersistenciaTimelineAcao();
	}

	
	/*
	 * Operações da classe
	 */

	/**
	 * Lista a Timeline 
	 */

	public List<Timeline> listaTimelineModel() {
		return persistenciaTimeline.listaTimelineDAO();
	}
	
	/**
	 * Lista a Timeline APENAS do usuario selecionado
	 */
	public List<Timeline> listaTimelineUsuarioModel(Integer idUsuario) {
		return persistenciaTimeline.listaTimelineUsuarioDAO(idUsuario);
	}

	/**
	 * Cadastra um Post informado pelo Usuario. Acessa camada de Persistencia pra execuçao
	 */
	public boolean cadastraPostModel(String postDescricao) {
		return persistenciaTimeline.cadastraPostDAO(postDescricao);
	}
	
	
	// TIMELINE ACAO //
	

	/**
	 * Acessa PersistenciaTimelineAcao para listar os Comentarios/Curtir da base Timeline_Acao
	 */
	public List<TimelineAcao> listaTimeLineAcao(Integer idTimeline) {
		return persistenciaTimelineAcao.listaTimelineAcaoDAO(idTimeline);
	}

	/**
	 * Acessa TimeliPersistenciaTimelineAcaoneDAO para cadastrar comentario na base Timeline_Acao
	 */
	public Boolean cadastraTimelineAcaoModel(TimelineAcao timelineAcao) {
		return persistenciaTimelineAcao.cadastraTimelineAcaoDAO(timelineAcao);
	}

	/**
	 * Acessa PersistenciaTimelineAcao para excluir  o Like na base Timeline_Acao
	 */
	public boolean excluiLikeTimelineAcaoModel(TimelineAcao timelineAcao) {
		return persistenciaTimelineAcao.excluiLikeTimelineAcaoDAO(timelineAcao);
	}

	/**
	 * Acessa PersistenciaTimeline para excluir o Post que o Usuario logado informou
	 * 
	 */
	public boolean excluiPostTimelineModel(Integer idTimeline) {
		persistenciaTimelineAcao.excluiTimelineAcaoByTimelineDAO(idTimeline);
		persistenciaTimeline.excluiPostDAO(idTimeline);		
		return true;
	}
	
	/**
	 * Acessa PersistenciaTimelineAcao para excluir o Comentario que o Usuario logado informou
	 * 
	 */
	public boolean excluiTimelineAcaoModel(Integer idTimelineAcao) {
		return persistenciaTimelineAcao.excluiTimelineAcaoDAO(idTimelineAcao);
	}


}
