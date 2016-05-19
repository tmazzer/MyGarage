package opet.mygarage.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe para representar um item da Timeline.
 * 
 * @author Juliano Zapelini Batista / Tiago Mazzer
 * 
 * @since 09/05/2016
 * 
 * @version 1.0
 * 
 */
public class Timeline implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1815238274297825467L;
	
	/*
	 * Variáveis de instância
	 */
	
	private Integer idTimeline;
	
	private Integer idUSuario;
	
	private String nomeUSuario;
	
	private Date dataCadastro;
	
	private String descricao;
	
	private String like;
	
	
	
	/*
	 * Função construtora
	 */
	public Timeline() {
	}


	
	/*
	 * Métodos de acesso
	 */

	/**
	 * @return the timeline
	 */
	public Integer getIdTimeline() {
		return idTimeline;
	}



	/**
	 * @param timeline the timeline to set
	 */
	public void setIdTimeline(Integer idTimeline) {
		this.idTimeline = idTimeline;
	}



	/**
	 * @return the idUSuario
	 */
	public Integer getIdUSuario() {
		return idUSuario;
	}



	/**
	 * @param idUSuario the idUSuario to set
	 */
	public void setIdUSuario(Integer idUSuario) {
		this.idUSuario = idUSuario;
	}



	/**
	 * @return the dataCadastro
	 */
	public Date getDataCadastro() {
		return dataCadastro;
	}



	/**
	 * @param dataCadastro the dataCadastro to set
	 */
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}



	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}



	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}



	/**
	 * @return the nomeUSuario
	 */
	public String getNomeUSuario() {
		return nomeUSuario;
	}



	/**
	 * @param nomeUSuario the nomeUSuario to set
	 */
	public void setNomeUSuario(String nomeUSuario) {
		this.nomeUSuario = nomeUSuario;
	}



	/**
	 * @return the like
	 */
	public String getLike() {
		return like;
	}



	/**
	 * @param like the like to set
	 */
	public void setLike(String like) {
		this.like = like;
	}


}
