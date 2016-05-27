/**
 * 
 */
package opet.mygarage.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import opet.mygarage.model.CarroModel;
import opet.mygarage.util.SessaoSistema;
import opet.mygarage.util.Upload;
import opet.mygarage.vo.Acessorios;
import opet.mygarage.vo.Carro;
import javax.servlet.http.Part;

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
@SessionScoped

public class CarroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3616982032386029450L;
	/*
	 * Variáveis de instância
	 */
	private String msgRetorno;

	private List<Carro> carrosList;
	private List<Acessorios> acessoriosList;

	private Carro carro;
	private Acessorios acessorios;

	private CarroModel carroModel;

	private Part uploadedPhoto;
	
	private Upload upload;
	
	private String apelidoCarroAnterior;
	
	private String nomeAcessorioAnterior;



	/*
	 * Função construtora da classe
	 */
	/**
	 * Construtor CarroBean()
	 */
	public CarroBean() {
		carro = new Carro();
		acessorios = new Acessorios();
		carroModel = new CarroModel();

		msgRetorno = "";
	}

	/*
	 * Métodos de acesso
	 */
	/**
	 * Monta a tela inicial carros ListaView.xhtml
	 */

	public void inicializaPagina() {
		carrosList = carroModel.listaCarrosModel(SessaoSistema.getIdUsuarioLogado());
		if (carrosList == null) {
			msgRetorno = "Não há carros para listar";
		} else {
			msgRetorno = "";
		}

		// acessoriosList = carroModel.listaAcessoriosModel();
	}

	public void inicializaPaginaAcessorios() {
		System.out.println("LOG::CarroBean::inicializaPaginaAcessorios");
		acessoriosList = carroModel.listaAcessoriosModel(carro);
	}

	public String consultaCarroController(Carro carro) {
		this.carro = carroModel.consultaCarroModel(carro);
		return "/paginas/carros/carroView";
	}

	public String telaAdicionar() {
		carro = new Carro();
		return "/paginas/carros/manterCarroView";
	}

	public String excluiCarroController() {
		if (carroModel.excluiCarroModel(carro)) {
			msgRetorno = "Carro excluido com sucesso";
			return "/paginas/carros/carroListaView";
		} else {
			return msgRetorno = "Problema ao Excluir Carro. Tente mais tarde";
		}
	}

	public String salvarCarroController() {

		// Declaração de variáveis
		msgRetorno = "";
		FacesContext context = FacesContext.getCurrentInstance();
		
        if (uploadedPhoto != null){
        	carro.setFoto("S");
        }    

		// Processamento dos dados

		if (this.carro.getIdCarro() == null) {

			if ((this.carro.getDescCombut()).equals("FLEX")) {
				this.carro.setTpCombust(1);
			} else if ((this.carro.getDescCombut()).equals("GASOLINA")) {
				this.carro.setTpCombust(2);
			} else {
				this.carro.setTpCombust(3);
			}

			this.carro = carroModel.cadastrarCarroModel(carro);

			if (SessaoSistema.getCodigodMensagem() == 0) {
							
				//Upload foto
				if (uploadedPhoto != null){
			        String diretorio = "USUARIO\\" + SessaoSistema.getIdUsuarioLogado();
			        String fileName = "car_" + carro.getIdCarro() + ".jpg";
			        uploadFoto(diretorio, fileName);
				}
				
				uploadedPhoto = null;
				carro = new Carro();
				msgRetorno = "Carro cadastrado com sucesso";
				return "/paginas/carros/carroListaView";
			} else {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						" : Não foi possível salvar os dados: ", SessaoSistema.getDescMensagem()));

				return msgRetorno = SessaoSistema.getDescMensagem();
			}
		} else {
			if ((this.carro.getDescCombut()).equals("FLEX")) {
				this.carro.setTpCombust(1);
			} else if ((this.carro.getDescCombut()).equals("GASOLINA")) {
				this.carro.setTpCombust(2);
			} else {
				this.carro.setTpCombust(3);
			}

			this.carro = carroModel.alteraCarroModel(carro);

			if (SessaoSistema.getCodigodMensagem() == 0) {
				
				//Upload foto
				if (uploadedPhoto != null){
			        String diretorio = "USUARIO\\" + SessaoSistema.getIdUsuarioLogado();
			        String fileName = "car_" + carro.getIdCarro() + ".jpg";
			        uploadFoto(diretorio, fileName);
				}
				
				uploadedPhoto = null;
				carro = new Carro();
				msgRetorno = "Carro alterado com sucesso";
				
				return "/paginas/carros/carroListaView";
			} else {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						" : Não foi possível salvar os dados: ", SessaoSistema.getDescMensagem()));

				msgRetorno = SessaoSistema.getDescMensagem();
				SessaoSistema.setCodigodMensagem(0);
				SessaoSistema.setDescMensagem("");
				return msgRetorno;
			}
		}

	}
	
	public String consultaAcessoriosController(Acessorios acessorios) {
		this.acessorios = carroModel.consultaAcessoriosModel(acessorios);
		return "/paginas/carros/acessoriosView";
	}

	public String telaAdicionarAcessorios() {
		acessorios = new Acessorios();
		return "/paginas/carros/manterAcessoriosView";
	}

	public String excluiAcessoriosController() {
		if (carroModel.excluiAcessoriosModel(acessorios)) {
			msgRetorno = "Acessorio excluido com sucesso";
			return "/paginas/carros/acessoriosListaView";
		} else {
			return msgRetorno = "Problema ao Excluir Acessorio. Tente mais tarde";
		}
	}

	public String salvarAcessoriosController() {
		System.out.println("LOG::AcessoriosBean::salvarACessorioController");

		// Declaração de variáveis
		msgRetorno = "";
		FacesContext context = FacesContext.getCurrentInstance();
		
		
        if (uploadedPhoto != null){
        	acessorios.setFoto("S");
        }  

		// Processamento dos dados

		if (this.acessorios.getIdAcessorios() == null) {

			this.acessorios = carroModel.cadastrarAcessoriosModel(carro, acessorios);

			if (SessaoSistema.getCodigodMensagem() == 0) {
				
				//Upload foto
				if (uploadedPhoto != null){
					String diretorio = "USUARIO\\" + SessaoSistema.getIdUsuarioLogado();     
					String fileName = "ace_" + acessorios.getIdAcessorios() + ".jpg";
			        uploadFoto(diretorio, fileName);
				}
				uploadedPhoto = null;
				acessorios = new Acessorios();
				msgRetorno = "Acessorio cadastrado com sucesso";
				
				return "/paginas/carros/carroView";
			} else {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						" : Não foi possível salvar os dados: ", SessaoSistema.getDescMensagem()));

				return msgRetorno = SessaoSistema.getDescMensagem();
			}
		} else {
			this.acessorios = carroModel.alteraAcessoriosModel(acessorios);

			if (SessaoSistema.getCodigodMensagem() == 0) {
				
				//Upload foto
				if (uploadedPhoto != null){
					String diretorio = "USUARIO\\" + SessaoSistema.getIdUsuarioLogado();     
					String fileName = "ace_" + acessorios.getIdAcessorios() + ".jpg";
			        uploadFoto(diretorio, fileName);
				}
				
				acessorios = new Acessorios();
				msgRetorno = "Acessorio alterado com sucesso";
				uploadedPhoto = null;
				
				return "/paginas/carros/carroView";
			} else {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						" : Não foi possível salvar os dados: ", SessaoSistema.getDescMensagem()));

				msgRetorno = SessaoSistema.getDescMensagem();
				SessaoSistema.setCodigodMensagem(0);
				SessaoSistema.setDescMensagem("");
				return msgRetorno;
			}
		}

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
	

	/**
	 * @return the msgRetorno
	 */
	public String getMsgRetorno() {
		return msgRetorno;
	}

	/**
	 * @param msgRetorno
	 *            the msgRetorno to set
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
	 * @param carro
	 *            the carro to set
	 */
	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public List<Carro> getCarrosList() {
		return carrosList;
	}

	public void setCarrosList(List<Carro> carrosList) {
		this.carrosList = carrosList;
	}

	public CarroModel getCarroModel() {
		return carroModel;
	}

	public void setCarroModel(CarroModel carroModel) {
		this.carroModel = carroModel;
	}
	// acessorios

	/**
	 * @return the acessoriosList
	 */
	public List<Acessorios> getAcessoriosList() {
		return acessoriosList;
	}

	/**
	 * @param acessoriosList
	 *            the acessoriosList to set
	 */
	public void setAcessoriosList(List<Acessorios> acessoriosList) {
		this.acessoriosList = acessoriosList;
	}

	/**
	 * @return the acessorios
	 */
	public Acessorios getAcessorios() {
		return acessorios;
	}

	/**
	 * @param acessorios
	 *            the acessorios to set
	 */
	public void setAcessorios(Acessorios acessorios) {
		this.acessorios = acessorios;
	}

	/**
	 * @return the uploadedPhoto
	 */
	public Part getUploadedPhoto() {
		return uploadedPhoto;
	}

	/**
	 * @param uploadedPhoto
	 *            the uploadedPhoto to set
	 */
	public void setUploadedPhoto(Part uploadedPhoto) {
		this.uploadedPhoto = uploadedPhoto;
	}
	
	/**
	 * @return the apelidoCarroAnterior
	 */
	public String getApelidoCarroAnterior() {
		return apelidoCarroAnterior;
	}

	/**
	 * @param apelidoCarroAnterior the apelidoCarroAnterior to set
	 */
	public void setApelidoCarroAnterior(String apelidoCarroAnterior) {
		this.apelidoCarroAnterior = apelidoCarroAnterior;
	}

	/**
	 * @return the nomeAcessorioAnterior
	 */
	public String getNomeAcessorioAnterior() {
		return nomeAcessorioAnterior;
	}

	/**
	 * @param nomeAcessorioAnterior the nomeAcessorioAnterior to set
	 */
	public void setNomeAcessorioAnterior(String nomeAcessorioAnterior) {
		this.nomeAcessorioAnterior = nomeAcessorioAnterior;
	}



}
