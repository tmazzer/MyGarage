/**
 * 
 */
package opet.mygarage.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import opet.mygarage.model.UsuarioModel;
import opet.mygarage.util.MensagemRetorno;
import opet.mygarage.vo.Usuario;


/**
 * Backing Bean para dar suporte às páginas Usuario/JSF.
 * 
 * @author Juliano Zapelini Batista / Tiago Mazzer
 * 
 * @since 19/04/2016
 * 
 * @version 1.0
 * 
 */
@Named("usuarioBean")
@SessionScoped

public class UsuarioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3638285027357727602L;



	

	/*
	 * Variáveis de instância
	 */
	private Usuario usuario;
	
	private UsuarioModel usuarioModel;
	
	private String msgRetorno;
		
	
	/*
	 * Função construtora da classe
	 */



	/**
	 * Construtor CadastraUsuarioBean()
	 */
	public UsuarioBean() {
		System.out.println("LOG::UsuarioBean:CONSTRUTOR");
		usuario = new Usuario();
		usuarioModel = new UsuarioModel();

		//Inicializa Mensagens
		MensagemRetorno.setCodigodMensagem(0);
		msgRetorno = "";
	}
	
	/*
	 * Métodos de acesso
	 */
	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}


	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
	 * Limpa tela para Cadastrar Usuario 
	 * 
	 */
	public String telaCadastrarUsuarioController(){
		msgRetorno = "";
		usuario = new Usuario();
		return "/paginas/usuario/manterUsuarioView";
	}
	
	/**
	 * Cadastra um novo Usuario. Acessa usuarioModel.confirmaCadastroModel()
	 * 
	 */
	public String salvarUsuarioController(){
		
		// Declaração de variáveis
		msgRetorno = "";
		FacesContext context = FacesContext.getCurrentInstance();

		// Processamento dos dados
		
		if (this.usuario.getIdUsuario() == null){		
			
			this.usuario = usuarioModel.cadastrarUsuarioModel(usuario);
	
			if (MensagemRetorno.getCodigodMensagem() == 0){
				usuario = new Usuario();
				msgRetorno = "Usuário cadastrado com sucesso";
			}else {
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, " : Não foi possível salvar os dados: ", MensagemRetorno.getDescMensagem()));
	
				msgRetorno = MensagemRetorno.getDescMensagem();
			}
		}else
		{
			this.usuario = usuarioModel.alteraUsuarioModel(usuario);
			
			if (MensagemRetorno.getCodigodMensagem() == 0){
				usuario = new Usuario();
				msgRetorno = "Usuário alterado com sucesso";
			}else {
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, " : Não foi possível salvar os dados: ", MensagemRetorno.getDescMensagem()));
	
				msgRetorno = MensagemRetorno.getDescMensagem();
				MensagemRetorno.setCodigodMensagem(0);
				MensagemRetorno.setDescMensagem("");
			}
		}
		return "/paginas/loginView";
	}
	/**
	 * Consulta os dados do Usuario logado. Acessa usuarioModel.consultaUsuarioModel()
	 * 
	 */
	public String consultaUsuarioController(){
		
		msgRetorno = "";
		
		//////////////////////////
		/////////APAGAR//////////FORÇANDO CODIGO PARA TESTE - CORRETO PEGAR DA SECAO
		usuario.setIdUsuario(2);
		System.out.println("LOG::BEAN::USUARIO: " + usuario.getIdUsuario());
		//////////////////////////
		/////////APAGAR//////////FORÇANDO CODIGO PARA TESTE - CORRETO PEGAR DA SECAO

		this.usuario = usuarioModel.consultaUsuarioModel(usuario);
		
		if (MensagemRetorno.getCodigodMensagem() != 0){
			msgRetorno = "Usuário não encontrado. Favor verificar mais tarde.";
			MensagemRetorno.setCodigodMensagem(0);
			MensagemRetorno.setDescMensagem("");
		}
		
		return "/paginas/usuario/usuarioView";
	}
	/**
	 * Exclui os dados do Usuario logado. Acessa usuarioModel.excluiUsuarioModel()
	 * 
	 */
	public String excluiUsuarioController(){

		
		msgRetorno = "";
		
		//////////////////////////
		/////////APAGAR//////////FORÇANDO CODIGO PARA TESTE - CORRETO PEGAR DA SECAO
		usuario.setIdUsuario(3);
		//////////////////////////
		/////////APAGAR//////////FORÇANDO CODIGO PARA TESTE - CORRETO PEGAR DA SECAO

		if(usuarioModel.excluiUsuarioModel(usuario)){
			msgRetorno = "Usuario excluído com sucesso!";
			return "/paginas/loginView";
		} else{
			msgRetorno = "Erro ao excluir Usuario. Tente mais tarde!";
			return "/paginas/usuario/usuarioView";
		}
	}
	
	public String telaAlteraUsuarioController(){
		
		msgRetorno = "";
		
		//////////////////////////
		/////////APAGAR//////////FORÇANDO CODIGO PARA TESTE - CORRETO PEGAR DA SECAO
		usuario.setIdUsuario(2);
		//////////////////////////
		/////////APAGAR//////////FORÇANDO CODIGO PARA TESTE - CORRETO PEGAR DA SECAO

		this.usuario = usuarioModel.consultaUsuarioModel(usuario);
		
		return "/paginas/usuario/manterUsuarioView";
		
	}
	
	public String alteraUsuarioController(){
		
		msgRetorno = "";
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		this.usuario = usuarioModel.alteraUsuarioModel(usuario);
		
		if (MensagemRetorno.getCodigodMensagem() == 0){
			return this.msgRetorno = "Usuário Alterado com sucesso";
		}else {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, " : Não foi possível salvar os dados: ", MensagemRetorno.getDescMensagem()));

			return this.msgRetorno = MensagemRetorno.getDescMensagem();
		}
		
	}
	
	public String validaLogin(){

		
		
		//TimelineController timeline = new TimelineController();
		
		if (usuarioModel.validaLoginModel(usuario)){
			msgRetorno = "Validado com sucesso";
			//timeline.telaPrincipalController();
			return "/paginas/timeline/timelineView";
		}else{
			msgRetorno = "Email ou Senha invalidos!";
		}
			
		return msgRetorno;
		
	}

}
