/**
 * 
 */
package opet.mygarage.model.persistencia;

import java.util.List;

import opet.mygarage.vo.TimelineAcao;

/**
 * Classe Interface do TimelineAcaoDAO
 * 
 * @author Juliano Zapelini Batista / Tiago Mazzer
 * 
 * @since 13/05/2016
 * 
 * @version 1.0
 * 
 */
public interface ITimelineAcaoDAO {
	public List<TimelineAcao> listaTimelineAcaoDAO(Integer idTimeline);
	public Boolean cadastraTimelineAcaoDAO(TimelineAcao timelineAcao);
	public Boolean excluiLikeTimelineAcaoDAO(TimelineAcao timelineAcao);
	public Boolean validaLikePostDAO(TimelineAcao timelineAcao);
	public Boolean excluiTimelineAcaoDAO(Integer idTimelineAcao);
}
