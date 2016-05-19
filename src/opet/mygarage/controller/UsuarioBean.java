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
		
	
	/*
	 * Função construtora da classe
	 */

	/**
	 * Construtor CadastraUsuarioBean()
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
		
		System.out.println("UsuarioBean::salvarUsuarioController");
		
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
		
		System.out.println("UsuarioBean::consultaUsuarioController");
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
    public void uploadFoto(){
        try {
            Upload upload = Upload.getInstance();
            upload.write(uploadedPhoto);
            
            usuario.setFoto(upload.extractFileName(uploadedPhoto));

            System.out.println("Foto carregada: " + usuario.getNome());

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

}
