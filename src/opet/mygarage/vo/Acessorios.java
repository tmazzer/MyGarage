/**
 * 
 */
package opet.mygarage.vo;

import java.io.Serializable;

/**
 * Classe para representar um Usuario do sistema.
 * 
 * @author Juliano Zapelini Batista / Tiago Mazzer
 * 
 * @since 19/04/2016
 * 
 * @version 1.0
 * 
 */
public class Acessorios implements Serializable, Cloneable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8450231205266738277L;
	/*
	 * Variáveis de instância
	 */
	private Integer carro_idCarro;
	
	private String nome;
	
	private String descricao;
	
	private String Marca;
	
	private String Modelo;
	
	private Integer idAcessorios;
	
	private String foto;
	
	/*
	 * Função construtora
	 */
	public Acessorios() {
	}
	
	/**
	 * @return the idAcessorios
	 */
	public Integer getIdAcessorios() {
		return idAcessorios;
	}
	/**
	 * @param idAcessorios the idAcessorios to set
	 */
	public void setIdAcessorios(Integer idAcessorios) {
		this.idAcessorios = idAcessorios;
	}
	/**
	 * @return the carro_idCarro
	 */
	public Integer getCarro_idCarro() {
		return carro_idCarro;
	}
	/**
	 * @param carro_idCarro the carro_idCarro to set
	 */
	public void setCarro_idCarro(Integer carro_idCarro) {
		this.carro_idCarro = carro_idCarro;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
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
	 * @return the marca
	 */
	public String getMarca() {
		return Marca;
	}
	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		Marca = marca;
	}
	/**
	 * @return the modelo
	 */
	public String getModelo() {
		return Modelo;
	}
	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(String modelo) {
		Modelo = modelo;
	}

	/**
	 * @return the foto
	 */
	public String getFoto() {
		return foto;
	}

	/**
	 * @param foto the foto to set
	 */
	public void setFoto(String foto) {
		this.foto = foto;
	}
}
