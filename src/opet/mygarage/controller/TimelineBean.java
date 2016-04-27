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
 * Backing Bean para dar suporte �s p�ginas Timeline/JSF
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
	 * Vari�veis de inst�ncia
	 */
	private static final long serialVersionUID = -911596375118728823L;

	/*
	 * Fun��o construtora da classe
	 */
	/**
	 * Construtor TimelineBean()
	 */
	public TimelineBean() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * M�todos de acesso
	 */
	
	public String telaPrincipalController(){
		return "/paginas/timeline/timelineView";
	}

}
