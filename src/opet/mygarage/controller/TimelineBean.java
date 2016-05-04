/**
 * 
 */
package opet.mygarage.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import opet.mygarage.util.SessaoSistema;

@Named("timelineBean")
@SessionScoped
/**
 * Backing Bean para dar suporte às páginas Timeline/JSF
 * 
 * @author Juliano Zapelini Batista / Tiago Mazzer
 * 
 * @since 26/04/2016
 * 
 * @version 1.0
 * 
 */
public class TimelineBean implements Serializable {

	/*
	 * Variáveis de instância
	 */
	
	private String msgRetorno;
	
	private static final long serialVersionUID = -911596375118728823L;

	/*
	 * Função construtora da classe
	 */
	/**
	 * Construtor TimelineBean()
	 */
	public TimelineBean() {
		System.out.println("TimelineBean::Construtor()");
		msgRetorno = "Seja bem vindo  " + SessaoSistema.getNomeUsuarioLogado();
	}
	
	/*
	 * Métodos de acesso
	 */
	/**
	 * Monta a tela inicial timelineView.xhtml
	 */
	public void inicializaPagina()
	{
		System.out.println("TimelineBean::inicializaPagina()");
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
