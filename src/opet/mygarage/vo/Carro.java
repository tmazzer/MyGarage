/**
 * 
 */
package opet.mygarage.vo;

import java.io.Serializable;

/**
 * Classe para representar um Carro do sistema.
 * 
 * @author Juliano Zapelini Batista / Tiago Mazzer
 * 
 * @since 19/04/2016
 * 
 * @version 1.0
 * 
 */
public class Carro implements Serializable, Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1536485388832568076L;

	/*
	 * Variáveis de instância
	 */
	
	private String apelido;
	
	private String marca;
	
	private String modelo;
	
	private String anoFabricacao;
	
	private String anoModelo;
	
	private String cor;
	
	private String placa;
	
	private String quilometragem;
	
	private Integer idCarro;
	
	/*
	 * Função construtora
	 */
	public Carro() {
		System.out.println("LOG::Carro.VO:Construtor");
	}
	
	/**
	 * @return the apelido
	 */
	public String getApelido() {
		return apelido;
	}
	/**
	 * @param apelido the apelido to set
	 */
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	/**
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}
	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}
	/**
	 * @return the modelo
	 */
	public String getModelo() {
		return modelo;
	}
	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	/**
	 * @return the anoFabricacao
	 */
	public String getAnoFabricacao() {
		return anoFabricacao;
	}
	/**
	 * @param anoFabricacao the anoFabricacao to set
	 */
	public void setAnoFabricacao(String anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}
	/**
	 * @return the anoModelo
	 */
	public String getAnoModelo() {
		return anoModelo;
	}
	/**
	 * @param anoModelo the anoModelo to set
	 */
	public void setAnoModelo(String anoModelo) {
		this.anoModelo = anoModelo;
	}
	/**
	 * @return the cor
	 */
	public String getCor() {
		return cor;
	}
	/**
	 * @param cor the cor to set
	 */
	public void setCor(String cor) {
		this.cor = cor;
	}
	/**
	 * @return the placa
	 */
	public String getPlaca() {
		return placa;
	}
	/**
	 * @param placa the placa to set
	 */
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	/**
	 * @return the quilometragem
	 */
	public String getQuilometragem() {
		return quilometragem;
	}
	/**
	 * @param quilometragem the quilometragem to set
	 */
	public void setQuilometragem(String quilometragem) {
		this.quilometragem = quilometragem;
	}
	/**
	 * @return the idCarro
	 */
	public Integer getIdCarro() {
		return idCarro;
	}
	/**
	 * @param idCarro the idCarro to set
	 */
	public void setIdCarro(Integer idCarro) {
		this.idCarro = idCarro;
	}
	
}
