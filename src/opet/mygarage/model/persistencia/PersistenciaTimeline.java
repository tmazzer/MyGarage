/**
 * 
 */
package opet.mygarage.model.persistencia;

import java.util.List;

import opet.mygarage.vo.Timeline;

/**
 * Classe Persistencia da tabela Timeline
 * 
 * @author Juliano Zapelini Batista / Tiago Mazzer
 * 
 * @since 09/05/2016
 * 
 * @version 1.0
 * 
 */
public class PersistenciaTimeline implements ITimelineDAO {

	/*
	 * Variáveis de instância
	 */
	TimelineDAO timelineDAO;
	
	/*
	 * Função construtora
	 */
	public PersistenciaTimeline(){
		timelineDAO = new TimelineDAO();
	}
	
	/*
	 * Operações da classe
	 */
	
	/**
	 * Acessa TimelineDAO para listar a Timeline da base Timeline
	 * 
	 * @see opet.mygarage.model.persistencia.ITimelineDAO#listaTimelineDAO()
	 */
	@Override
	public List<Timeline> listaTimelineDAO() {
		return timelineDAO.listaTimelineDAO();
	}
	
	/**
	 * Acessa TimelineDAO para listar a Timeline de um Usuario Selecionado
	 */
	@Override
	public List<Timeline> listaTimelineUsuarioDAO(Integer idUsuario) {
		return timelineDAO.listaTimelineUsuarioDAO(idUsuario);
	}
	/**
	 * Acessa TimelineDAO para cadastrar um Post na base TImeline
	 */
	@Override
	public Boolean cadastraPostDAO(String postDescricao) {
		return timelineDAO.cadastraPostDAO(postDescricao);
	}
	/**
	 * Acessa TimelineDAO para excluir um Post na base TImeline
	 */
	@Override
	public Boolean excluiPostDAO(Integer idTimeline) {
		return timelineDAO.excluiPostDAO(idTimeline);
	}

}
