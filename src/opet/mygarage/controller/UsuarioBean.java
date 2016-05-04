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
import opet.mygarage.util.SessaoSistema;
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
		SessaoSistema.setCodigodMensagem(0);
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
			/////Cadastra Usuario
			this.usuario = usuarioModel.cadastrarUsuarioModel(usuario);
	
			if (SessaoSistema.getCodigodMensagem() == 0){
				usuario = new Usuario();
				msgRetorno = "Usuário cadastrado com sucesso";
				return "/paginas/timeline/timelineView";	
			}else {
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, " : Não foi possível salvar os dados: ", SessaoSistema.getDescMensagem()));
	
				msgRetorno = SessaoSistema.getDescMensagem();
				return "/paginas/loginView";	
			}
		}else
		{
			///////Altera Usuario
			this.usuario = usuarioModel.alteraUsuarioModel(usuario);
			
			if (SessaoSistema.getCodigodMensagem() == 0){
				usuario.setIdUsuario(SessaoSistema.getIdUsuarioLogado());
				this.usuario = usuarioModel.consultaUsuarioModel(usuario);
				msgRetorno = "Usuário Alterado com sucesso";
				return "/paginas/usuario/usuarioView";
			}else {
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, " : Não foi possível salvar os dados: ", SessaoSistema.getDescMensagem()));
	
				msgRetorno = SessaoSistema.getDescMensagem();
				SessaoSistema.setCodigodMensagem(0);
				SessaoSistema.setDescMensagem("");				
				return "/paginas/usuario/manterUsuarioView";
			}
		}		
		
	}
	/**
	 * Consulta os dados do Usuario logado. Acessa usuarioModel.consultaUsuarioModel()
	 * 
	 */
	public String consultaUsuarioController(){
		// Declaração de variáveis
		msgRetorno = "";
		FacesContext context = FacesContext.getCurrentInstance();
		

		
		usuario.setIdUsuario(SessaoSistema.getIdUsuarioLogado());
		System.out.println("LOG::BEAN::USUARIO: " + usuario.getIdUsuario());



		this.usuario = usuarioModel.consultaUsuarioModel(usuario);
		
		if (SessaoSistema.getCodigodMensagem() != 0){
			msgRetorno = "Usuário não encontrado. Favor verificar mais tarde.";
			SessaoSistema.setCodigodMensagem(0);
			SessaoSistema.setDescMensagem("");
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, " : Erro ao consultar usuario logado: ", SessaoSistema.getDescMensagem()));

		}
		
		return "/paginas/usuario/usuarioView";
	}
	/**
	 * Exclui os dados do Usuario logado. Acessa usuarioModel.excluiUsuarioModel()
	 * 
	 */
	public String excluiUsuarioController(){

		
		msgRetorno = "";
		
		usuario.setIdUsuario(SessaoSistema.getIdUsuarioLogado());

		if(usuarioModel.excluiUsuarioModel(usuario)){
			msgRetorno = "Usuario excluído com sucesso!";
			SessaoSistema.limpaSessao();
			return "/paginas/loginView";
		} else{
			msgRetorno = "Erro ao excluir Usuario. Tente mais tarde!";
			return "/paginas/usuario/usuarioView";
		}
	}
	
	public String telaAlteraUsuarioController(){
		
		msgRetorno = "";
		
		usuario.setIdUsuario(SessaoSistema.getIdUsuarioLogado());

		this.usuario = usuarioModel.consultaUsuarioModel(usuario);
		
		return "/paginas/usuario/manterUsuarioView";
		
	}

	
	public String validaLogin(){

		if (usuarioModel.validaLoginModel(usuario)){
			msgRetorno = "Validado com sucesso";
			return "/paginas/timeline/timelineView";
		}else{
			msgRetorno = "Email ou Senha invalidos!";
		}
			
		return msgRetorno;
		
	}

}
