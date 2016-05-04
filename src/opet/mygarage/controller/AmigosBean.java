/**
 * 
 */
package opet.mygarage.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import opet.mygarage.model.AmigosModel;
import opet.mygarage.model.UsuarioModel;
import opet.mygarage.util.SessaoSistema;
import opet.mygarage.vo.Usuario;

@Named("amigosBean")
@SessionScoped
/**
 * Backing Bean para dar suporte às páginas Amigos/JSF
 * 
 * @author Juliano Zapelini Batista / Tiago Mazzer
 * 
 * @since 28/04/2016
 * 
 * @version 1.0
 * 
 */
public class AmigosBean  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8448556849541299799L;
	/*
	 * Variáveis de instância
	 */
	
	private String msgRetorno;
	
	private List<Usuario> usuarioList;
	
	private List<Usuario> amigosList;
	
	private AmigosModel amigosModel;
	
	private Usuario usuario;
	
	private String isFriend;
	


	/*
	 * Função construtora da classe
	 */
	/**
	 * Construtor TimelineBean()
	 */
	public AmigosBean() {
		System.out.println("AmigosBean::Controller");
		amigosModel = new AmigosModel();
		usuario = new Usuario();
		isFriend = "";
	}
	
	
	/*
	 * Métodos de acesso
	 */
	
	/**
	 * Monta a tela inicial amigosView.xhtml
	 */
	public void inicializaPagina()
	{
		amigosList = amigosModel.listaAmigosModel();	
		isFriend = "";
		if (usuarioList == null){
			msgRetorno = "Não há amigos para listar";			
		}else{
			msgRetorno = "";	
		}			
	}
	
	/**
	 * Exclui a amizade entre os Usuarios
	 */
	public String excluirAmigoController()
	{
		System.out.println("AmigosBean::excluirAmigo");
		
		if(amigosModel.excluiAmigoModel(usuario)){
			isFriend = "amigo";
			return msgRetorno = "Amigo excluido com sucesso";			
		} else{
			isFriend = "";
			return msgRetorno = "Problema ao Excluir amigo. Tente mais tarde";		
		}		
	}
	
	/**
	 * Adiciona amizade entre os Usuarios
	 */
	public String adicionaAmigoController()
	{
		System.out.println("AmigosBean::adicionaAmigo");
		if(amigosModel.adicionaAmigoModel(usuario)){
			isFriend = "amigo";
			return msgRetorno = "Amigo Adicionado com sucesso";			
		} else{
			isFriend = "";
			return msgRetorno = "Problema ao Adicionar amigo. Tente mais tarde";		
		}
	}
	
	/**
	 * Consulta os dados do Usuario logado. Acessa usuarioModel.consultaUsuarioModel()
	 * 
	 */
	public String consultaUsuarioAmigoController(Usuario usuario){

		System.out.println("AmigosBean::consultaUsuarioAmigoController");

		UsuarioModel usuarioModel = new UsuarioModel();
		
		this.usuario = usuarioModel.consultaUsuarioModel(usuario);
		
		if (SessaoSistema.getCodigodMensagem() != 0){
			msgRetorno = "Usuário não encontrado. Favor verificar mais tarde.";
			SessaoSistema.setCodigodMensagem(0);
			SessaoSistema.setDescMensagem("");
			return msgRetorno;
		} else
		{
			return "/paginas/amigos/usuarioAmigoView";
		}

	}
	
	public List<Usuario> consultaUsuariosController(Usuario usuario){
		return usuarioList = amigosModel.consultaUsuariosModel(usuario);			
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
	 * @return the usuarioList
	 */
	public List<Usuario> getUsuarioList() {
		return usuarioList;
	}


	/**
	 * @param usuarioList the usuarioList to set
	 */
	public void setUsuarioList(List<Usuario> usuarioList) {
		this.usuarioList = usuarioList;
	}


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
	 * @return the amigosList
	 */
	public List<Usuario> getAmigosList() {
		return amigosList;
	}


	/**
	 * @param amigosList the amigosList to set
	 */
	public void setAmigosList(List<Usuario> amigosList) {
		this.amigosList = amigosList;
	}


	/**
	 * @return the isFriend
	 */
	public String getIsFriend() {
		return isFriend;
	}


	/**
	 * @param isFriend the isFriend to set
	 */
	public void setIsFriend(String isFriend) {
		this.isFriend = isFriend;
	}


}
