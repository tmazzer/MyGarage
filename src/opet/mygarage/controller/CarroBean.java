/**
 * 
 */
package opet.mygarage.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import opet.mygarage.model.CarroModel;
import opet.mygarage.util.MensagemRetorno;
import opet.mygarage.vo.Carro;


/**
 * Backing Bean para dar suporte às páginas Carro/JSF.
 * 
 * @author Juliano Zapelini Batista / Tiago Mazzer
 * 
 * @since 19/04/2016
 * 
 * @version 1.0
 * 
 */
@Named("carroBean")
@RequestScoped

public class CarroBean implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3616982032386029450L;
	/*
	 * Variáveis de instância
	 */
	private String msgRetorno;

	private Carro carro;
	
	private CarroModel carroModel;
	
	/*
	 * Função construtora da classe
	 */
	/**
	 * Construtor CarroBean()
	 */
	public CarroBean() {
		carro = new Carro();
		carroModel = new CarroModel();
		
		msgRetorno = "";
		System.out.println("LOG::CarroBean:CONSTRUTOR");
	}
	/*
	 * Métodos de acesso
	 */
	
	public String salvarCarroController(){
		System.out.println("LOG::CarroBean:salvarCarroController");


		// Declaração de variáveis
		msgRetorno = "";
		FacesContext context = FacesContext.getCurrentInstance();

		// Processamento dos dados
		
		if (this.carro.getIdCarro() == null){		
			
			this.carro = carroModel.cadastrarCarroModel(carro);
	
			if (MensagemRetorno.getCodigodMensagem() == 0){
				carro = new Carro();
				msgRetorno = "Carro cadastrado com sucesso";
				return "/paginas/carros/carroView";
			}else {
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, " : Não foi possível salvar os dados: ", MensagemRetorno.getDescMensagem()));
	
				return msgRetorno = MensagemRetorno.getDescMensagem();
			}
		}else
		{
			this.carro = carroModel.alteraCarroModel(carro);
			
			if (MensagemRetorno.getCodigodMensagem() == 0){
				carro = new Carro();
				return msgRetorno = "Carro alterado com sucesso";
			}else {
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, " : Não foi possível salvar os dados: ", MensagemRetorno.getDescMensagem()));
	
				msgRetorno = MensagemRetorno.getDescMensagem();
				MensagemRetorno.setCodigodMensagem(0);
				MensagemRetorno.setDescMensagem("");
				return msgRetorno;
			}
		}
		
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

	/**
	 * @return the carro
	 */
	public Carro getCarro() {
		return carro;
	}

	/**
	 * @param carro the carro to set
	 */
	public void setCarro(Carro carro) {
		this.carro = carro;
	}


}
