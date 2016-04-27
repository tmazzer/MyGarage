/**
 * 
 */
package opet.mygarage.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("timelineBean")
@SessionScoped
/**
 * Backing Bean para dar suporte às páginas Timeline/JSF
 * 
 * @author Juliano Zapelini Batista / Tiago Mazzer
 * 
 * @since 2604/2016
 * 
 * @version 1.0
 * 
 */
public class TimelineBean implements Serializable {

	/*
	 * Variáveis de instância
	 */
	private static final long serialVersionUID = -911596375118728823L;

	/*
	 * Função construtora da classe
	 */
	/**
	 * Construtor TimelineBean()
	 */
	public TimelineBean() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Métodos de acesso
	 */
	
	public String telaPrincipalController(){
		return "/paginas/timeline/timelineView";
	}

}
