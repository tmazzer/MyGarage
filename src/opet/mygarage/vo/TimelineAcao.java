/**
 * 
 */
package opet.mygarage.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe para representar um item da Timeline_acao.
 * 
 * @author Juliano Zapelini Batista / Tiago Mazzer
 * 
 * @since 13/05/2016
 * 
 * @version 1.0
 * 
 */
public class TimelineAcao implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3101616954801016529L;

	/*
	 * Variáveis de instância
	 */
	
	//	IDTIMELINE_ACAO - NUMBER(38,0)
	//	USUARIO_IDUSUARIO - NUMBER(38,0)
	//	TIMELINE_IDTIMELINE - NUMBER(38,0)
	//	DATAACAO - DATE
	//	CURTIR - VARCHAR2(1 BYTE)
	//	COMENTARIO - VARCHAR2(500 BYTE)
	
	private Integer idTimelineAcao;
	
	private Integer idUsuario;
	
	private String nomeUsuario;
	
	private Integer idTimeline;
	
	private Date dataAcao;
	
	private String like;
	
	private String comentario;
	
	
	
	
	/*
	 * Função construtora
	 */
	public TimelineAcao() {
	}



	
	/*
	 * Métodos de acesso
	 */

	/**
	 * @return the idTimelineAcao
	 */
	public Integer getIdTimelineAcao() {
		return idTimelineAcao;
	}




	/**
	 * @param idTimelineAcao the idTimelineAcao to set
	 */
	public void setIdTimelineAcao(Integer idTimelineAcao) {
		this.idTimelineAcao = idTimelineAcao;
	}




	/**
	 * @return the idUsuario
	 */
	public Integer getIdUsuario() {
		return idUsuario;
	}




	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}




	/**
	 * @return the idTimeline
	 */
	public Integer getIdTimeline() {
		return idTimeline;
	}




	/**
	 * @param idTimeline the idTimeline to set
	 */
	public void setIdTimeline(Integer idTimeline) {
		this.idTimeline = idTimeline;
	}




	/**
	 * @return the dataAcao
	 */
	public Date getDataAcao() {
		return dataAcao;
	}




	/**
	 * @param dataAcao the dataAcao to set
	 */
	public void setDataAcao(Date dataAcao) {
		this.dataAcao = dataAcao;
	}




	/**
	 * @return the like
	 */
	public String getLike() {
		return like;
	}




	/**
	 * @param curtir the curtir to set
	 */
	public void setLike(String like) {
		this.like = like;
	}





	/**
	 * @return the nomeUsuario
	 */
	public String getNomeUsuario() {
		return nomeUsuario;
	}




	/**
	 * @param nomeUsuario the nomeUsuario to set
	 */
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}




	/**
	 * @return the comentario
	 */
	public String getComentario() {
		return comentario;
	}




	/**
	 * @param comentario the comentario to set
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}


	
	
}
