/**
 * 
 */
package opet.mygarage.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("amigosBean")
@RequestScoped
/**
 * Backing Bean para dar suporte �s p�ginas Amigos/JSF
 * 
 * @author Juliano Zapelini Batista / Tiago Mazzer
 * 
 * @since 28/04/2016
 * 
 * @version 1.0
 * 
 */
public class AmigosBean {
	/*
	 * Vari�veis de inst�ncia
	 */
	
	private String msgRetorno;
	
	/*
	 * Fun��o construtora da classe
	 */
	/**
	 * Construtor TimelineBean()
	 */
	public AmigosBean() {
		System.out.println("AmigosBean::Controller");
	}
	/*
	 * M�todos de acesso
	 */
	/**
	 * Monta a tela inicial amigosView.xhtml
	 */
	public void inicializaPagina()
	{
		System.out.println("AmigoBean::inicializaPagina()");
		msgRetorno = "Entrou na AmigosView";
	}
	/**
	 * @return the msgRetorno
	 */
	public String getMsgRetorno() {
		return msgRetorno;
	}

	/**
	 * @param msgRetorno the msgRetorno to set
	 */
	public void setMsgRetorno(String msgRetorno) {
		this.msgRetorno = msgRetorno;
	}

}
