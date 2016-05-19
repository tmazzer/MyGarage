/**
 * 
 */
package opet.mygarage.model.persistencia;

import java.util.List;

import opet.mygarage.vo.Timeline;

/**
 * Classe Interface do TimelineDAO
 * 
 * @author Juliano Zapelini Batista / Tiago Mazzer
 * 
 * @since 09/05/2016
 * 
 * @version 1.0
 * 
 */
public interface ITimelineDAO {
	public List<Timeline> listaTimelineDAO();
	public List<Timeline> listaTimelineUsuarioDAO(Integer idUsuario);
	public Boolean cadastraPostDAO(String postDescricao);
	public Boolean excluiPostDAO(Integer idTimeline);
}
