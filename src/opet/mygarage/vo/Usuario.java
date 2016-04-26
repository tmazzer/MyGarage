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
public class Usuario implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9146207191603604677L;
	/*
	 * Variáveis de instância
	 */
	
	private Integer idUsuario;
	
	private String nome;
	
	private String sobrenome;
	
	private String telefone;
	
	private String email;
	
	private String senha;
	
	/*
	 * Função construtora
	 */
	public Usuario() {

	}
	/*
	 * Métodos de acesso
	 */

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
	 * @return the sobrenome
	 */
	public String getSobrenome() {
		return sobrenome;
	}

	/**
	 * @param sobrenome the sobrenome to set
	 */
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	/**
	 * @return the telefone
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * @param telefone the telefone to set
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

}
