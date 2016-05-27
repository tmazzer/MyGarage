/**
 * 
 */
package opet.mygarage.controller;

import java.io.IOException;
import java.io.Serializable;
import javax.servlet.http.Part;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import opet.mygarage.model.UsuarioModel;
import opet.mygarage.util.SessaoSistema;
import opet.mygarage.util.Upload;
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
	
	private Part uploadedPhoto;
	
	private Upload upload;
	
	private String emailAnterior;
		
	
	/*
	 * Função construtora da classe
	 */

	/**
	 * Construtor UsuarioBean()
	 */
	public UsuarioBean() {
		
		System.out.println("UsuarioBean:CONSTRUTOR");
		
		usuario = new Usuario();
		usuarioModel = new UsuarioModel();

		//Inicializa Mensagens
		SessaoSistema.setCodigodMensagem(0);
		msgRetorno = "";
	}
	

	
	/**
	 * Limpa tela para Cadastrar Usuario 
	 * 
	 */
	public String telaCadastrarUsuarioController(){
		
		System.out.println("UsuarioBean::telaCadastrarUsuarioController");
		msgRetorno = "";
		usuario = new Usuario();
		return "/paginas/usuario/manterUsuarioView";
	}
	
	/**
	 * Cadastra um novo Usuario. Acessa usuarioModel.confirmaCadastroModel()
	 * 
	 */
	public String salvarUsuarioController(){
		
		msgRetorno = "";
		FacesContext context = FacesContext.getCurrentInstance();
		
        
        if (uploadedPhoto != null){
        	usuario.setFoto("S");
        }        

		// Processamento dos dados
		
		if (this.usuario.getIdUsuario() == null){		
			/////Cadastra Usuario
			this.usuario = usuarioModel.cadastrarUsuarioModel(usuario);
	
			if (SessaoSistema.getCodigodMensagem() == 0){
				usuario = new Usuario();
				msgRetorno = "Usuário cadastrado com sucesso";
				
				//Salva foto
				if (uploadedPhoto != null){
			        String diretorio = "USUARIO\\" + usuario.getIdUsuario();        
			        String fileName = "user_" + usuario.getIdUsuario() + ".jpg";
			        uploadFoto(diretorio, fileName);
				}
				uploadedPhoto = null;
				
				return "/paginas/timeline/timelineView";	
			}else {
				return msgRetorno = SessaoSistema.getDescMensagem();
			}
		}else
		{
			///////Altera Usuario
			this.usuario = usuarioModel.alteraUsuarioModel(usuario, emailAnterior);
			
			if (SessaoSistema.getCodigodMensagem() == 0){
				usuario.setIdUsuario(SessaoSistema.getIdUsuarioLogado());
				this.usuario = usuarioModel.consultaUsuarioModel(usuario);
				msgRetorno = "Usuário Alterado com sucesso";
				
				//Salva foto
				if (uploadedPhoto != null){
			        String diretorio = "USUARIO\\" + usuario.getIdUsuario();        
			        String fileName = "user_" + usuario.getIdUsuario() + ".jpg";
			        uploadFoto(diretorio, fileName);
				}
				uploadedPhoto = null;
				emailAnterior = null;
				
				return "/paginas/usuario/usuarioView";
			}else {
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
		
		SessaoSistema.setCodigodMensagem(0);
		
		msgRetorno = "";
		FacesContext context = FacesContext.getCurrentInstance();
		
		usuario.setIdUsuario(SessaoSistema.getIdUsuarioLogado());

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

		System.out.println("UsuarioBean::excluiUsuarioController");
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
	
	/**
	 * Prepara a tela para Alterar o Usuario
	 * 
	 */
	public String telaAlteraUsuarioController(){
		
		msgRetorno = "";
		
		usuario.setIdUsuario(SessaoSistema.getIdUsuarioLogado());
		
		emailAnterior = usuario.getEmail();		

		this.usuario = usuarioModel.consultaUsuarioModel(usuario);
		
		return "/paginas/usuario/manterUsuarioView";
		
	}

	/**
	 * Valida se o Login informado é valido. Acessa usuarioModel.validaLoginModel()
	 * 
	 */
	public String validaLogin(){
		
		System.out.println("UsuarioBean::validaLogin");

		if (usuarioModel.validaLoginModel(usuario)){
			msgRetorno = "Validado com sucesso";
			return "/paginas/timeline/timelineView";
		}else{
			msgRetorno = "Email ou Senha invalidos!";
		}
			
		return msgRetorno;
		
	}
	
	/**
	 * Metodo responsavel por fazer Upload da foto do usuario.
	 * 
	 */
    public void uploadFoto(String diretorio, String fileName){
        try {
            upload = Upload.getInstance();
            upload.write(uploadedPhoto, diretorio, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
	 * @return the uploadedPhoto
	 */
	public Part getUploadedPhoto() {
		return uploadedPhoto;
	}



	/**
	 * @param uploadedPhoto the uploadedPhoto to set
	 */
	public void setUploadedPhoto(Part uploadedPhoto) {
		this.uploadedPhoto = uploadedPhoto;
	}



	/**
	 * @return the emailAnterior
	 */
	public String getEmailAnterior() {
		return emailAnterior;
	}



	/**
	 * @param emailAnterior the emailAnterior to set
	 */
	public void setEmailAnterior(String emailAnterior) {
		this.emailAnterior = emailAnterior;
	}

}
