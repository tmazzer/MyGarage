/**
 * 
 */
package opet.mygarage.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import opet.mygarage.model.AmigosModel;
import opet.mygarage.model.CarroModel;
import opet.mygarage.model.TimelineModel;
import opet.mygarage.model.UsuarioModel;
import opet.mygarage.util.SessaoSistema;
import opet.mygarage.vo.Carro;
import opet.mygarage.vo.Relacionamento;
import opet.mygarage.vo.Timeline;
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
	
	private List<Carro> carrosList;
	
	private AmigosModel amigosModel;
	
	private Usuario usuario;
	
	private String isFriend;
	
	private Relacionamento relacionamento;
	
	private String nomeBusca;
	
	private List<Timeline> timelineList;
	



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
		relacionamento = new Relacionamento();
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
		System.out.println("AmigosBean::inicializaPagina");
		this.amigosList = amigosModel.listaAmigosModel();	
		isFriend = "";
		msgRetorno = "";	
		nomeBusca = "";
	}
	
	/**
	 * Executa a ação do botão Busca Usuarios da tela AmigosView
	 */	
	public List<Usuario> consultaUsuariosController(){
		
		//Se pesquisar vier vazia, limpa tela de consulta.
		
		System.out.println("AmigosBean::consultaUsuariosController");
		
		if (nomeBusca.equalsIgnoreCase("")){
			return usuarioList = null;
		}else{
			usuario.setNome(nomeBusca);
			nomeBusca = "";
			return usuarioList = amigosModel.consultaUsuariosModel(usuario);	
		}		
	}
	
	
	/**
	 * Consulta os detalhes do Usuario selecionado na tela AmigosView
	 * 
	 */
	public String consultaUsuarioAmigoController(Integer idUsuario){

		
		if (idUsuario == SessaoSistema.getIdUsuarioLogado()){
			isFriend = null;
		}
		
		usuarioList = null;
		
		UsuarioModel usuarioModel = new UsuarioModel();
		
		usuario.setIdUsuario(idUsuario);
		
		this.usuario = usuarioModel.consultaUsuarioModel(usuario);
		
		if (SessaoSistema.getCodigodMensagem() == 0){
		
			//Consulta se é amigo
			
			relacionamento = null;
			
			relacionamento = amigosModel.validaAmigo(usuario.getIdUsuario());
			
			if(relacionamento != null){
				isFriend = "S";
			} else{				
				isFriend = "N";
			}
			
			//Lista os carros do Usuario
			
			setCarrosList(new ArrayList<>());
			CarroModel carroModel = new CarroModel();			
			setCarrosList(carroModel.listaCarrosModel(idUsuario));	
			
			// Recupera Timeline/Feed
			TimelineModel timelineModel = new TimelineModel();
			timelineList = timelineModel.listaTimelineUsuarioModel(idUsuario);

			
			return "/paginas/amigos/usuarioAmigoView";

		} else
		{
			msgRetorno = "Usuário não encontrado. Favor verificar mais tarde.";
			SessaoSistema.setCodigodMensagem(0);
			SessaoSistema.setDescMensagem("");
			return msgRetorno;
		}

	}
	
	/**
	 * Exclui a amizade entre os Usuarios
	 */
	public String excluirAmigoController()
	{
		
		if(amigosModel.excluiAmigoModel(usuario)){
			isFriend = "";
			return msgRetorno = "Amigo excluido com sucesso";			
		} else{
			isFriend = "amigo";
			return msgRetorno = "Problema ao Excluir amigo. Tente mais tarde";		
		}		
	}
	
	/**
	 * Adiciona amizade entre os Usuarios
	 */
	public String adicionaAmigoController()
	{
		
		System.out.println("AmigosBean::adicionaAmigoController");
		
		if(amigosModel.adicionaAmigoModel(usuario)){
			isFriend = "amigo";
			return msgRetorno = "Amigo Adicionado com sucesso";			
		} else{
			isFriend = "";
			return msgRetorno = "Problema ao Adicionar amigo. Tente mais tarde";		
		}
	}	

	
	/////////////////////////////////////////////////////////////////////////////////////////////
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


	/**
	 * @return the relacionamento
	 */
	public Relacionamento getRelacionamento() {
		return relacionamento;
	}


	/**
	 * @param relacionamento the relacionamento to set
	 */
	public void setRelacionamento(Relacionamento relacionamento) {
		this.relacionamento = relacionamento;
	}


	/**
	 * @return the nomeBusca
	 */
	public String getNomeBusca() {
		return nomeBusca;
	}


	/**
	 * @param nomeBusca the nomeBusca to set
	 */
	public void setNomeBusca(String nomeBusca) {
		this.nomeBusca = nomeBusca;
	}


	/**
	 * @return the carrosList
	 */
	public List<Carro> getCarrosList() {
		return carrosList;
	}


	/**
	 * @param carrosList the carrosList to set
	 */
	public void setCarrosList(List<Carro> carrosList) {
		this.carrosList = carrosList;
	}
	


	/**
	 * @return the timelineList
	 */
	public List<Timeline> getTimelineList() {
		return timelineList;
	}


	/**
	 * @param timelineList the timelineList to set
	 */
	public void setTimelineList(List<Timeline> timelineList) {
		this.timelineList = timelineList;
	}


}
