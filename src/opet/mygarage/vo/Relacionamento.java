/**
 * 
 */
package opet.mygarage.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe para representar um Relacionamento.
 * 
 * @author Juliano Zapelini Batista / Tiago Mazzer
 * 
 * @since 04/05/2016
 * 
 * @version 1.0
 * 
 */
public class Relacionamento implements Serializable, Cloneable {/**
	 * 
	 */
	private static final long serialVersionUID = 3458382953387606214L;
	/*
	 * Variáveis de instância
	 */
	private Integer idRelacionamento;
	private Integer codigoAmigo; 
	private Integer idUsuario;
	private Integer tipoGrupo;
	private Date dataIniRelacionamento;
	private Date dataFimRelacionamento;
	private String statusRelacionamento;
	
	
	/*
	 * Função construtora
	 */
	public Relacionamento() {
	}

	
	/*
	 * Métodos de acesso
	 */
	/**
	 * @return the idRelacionamento
	 */
	public Integer getIdRelacionamento() {
		return idRelacionamento;
	}


	/**
	 * @param idRelacionamento the idRelacionamento to set
	 */
	public void setIdRelacionamento(Integer idRelacionamento) {
		this.idRelacionamento = idRelacionamento;
	}


	/**
	 * @return the codigoAmigo
	 */
	public Integer getCodigoAmigo() {
		return codigoAmigo;
	}


	/**
	 * @param codigoAmigo the codigoAmigo to set
	 */
	public void setCodigoAmigo(Integer codigoAmigo) {
		this.codigoAmigo = codigoAmigo;
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
	 * @return the tipoGrupo
	 */
	public Integer getTipoGrupo() {
		return tipoGrupo;
	}


	/**
	 * @param tipoGrupo the tipoGrupo to set
	 */
	public void setTipoGrupo(Integer tipoGrupo) {
		this.tipoGrupo = tipoGrupo;
	}


	/**
	 * @return the dataIniRelacionamento
	 */
	public Date getDataIniRelacionamento() {
		return dataIniRelacionamento;
	}


	/**
	 * @param dataIniRelacionamento the dataIniRelacionamento to set
	 */
	public void setDataIniRelacionamento(Date dataIniRelacionamento) {
		this.dataIniRelacionamento = dataIniRelacionamento;
	}


	/**
	 * @return the dataFimRelacionamento
	 */
	public Date getDataFimRelacionamento() {
		return dataFimRelacionamento;
	}


	/**
	 * @param dataFimRelacionamento the dataFimRelacionamento to set
	 */
	public void setDataFimRelacionamento(Date dataFimRelacionamento) {
		this.dataFimRelacionamento = dataFimRelacionamento;
	}


	/**
	 * @return the statusRelacionamento
	 */
	public String getStatusRelacionamento() {
		return statusRelacionamento;
	}


	/**
	 * @param statusRelacionamento the statusRelacionamento to set
	 */
	public void setStatusRelacionamento(String statusRelacionamento) {
		this.statusRelacionamento = statusRelacionamento;
	}


	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
