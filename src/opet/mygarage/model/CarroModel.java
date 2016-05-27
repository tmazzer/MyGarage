/**
 * 
 */
package opet.mygarage.model;

import java.util.List;

import opet.mygarage.model.persistencia.PersistenciaCarro;
import opet.mygarage.model.persistencia.PersistenciaTimeline;
import opet.mygarage.util.SessaoSistema;
import opet.mygarage.vo.Acessorios;
import opet.mygarage.vo.Carro;

/**
 * Classe Model do Carro
 * 
 * @author Juliano Zapelini Batista / Tiago Mazzer
 * 
 * @since 19/04/2016
 * 
 * @version 1.0
 * 
 */
public class CarroModel {
	/*
	 * Variáveis de instância
	 */
	private PersistenciaCarro persistenciaCarro;
	
	/*
	 * Função construtora
	 */

	public CarroModel() {
		persistenciaCarro = new PersistenciaCarro();
		SessaoSistema.setCodigodMensagem(0);
		SessaoSistema.setDescMensagem("");
	}
	/*
	 * Operações da classe
	 */

	public Carro cadastrarCarroModel(Carro carro) {
		
		if (carro.getApelido() == null || (carro.getApelido().equalsIgnoreCase(""))){
			SessaoSistema.setCodigodMensagem(1);
			SessaoSistema.setDescMensagem("Campo Apelido não informado");
			return carro = null;
		}
		
		if (persistenciaCarro.validaApelidoCarroDAO(carro.getUsuarioIdUsuario(), carro.getApelido())){
			SessaoSistema.setCodigodMensagem(2);
			SessaoSistema.setDescMensagem("Apelido já cadastrado");
			return carro = null;
		}
		
		if (carro.getMarca() == null || (carro.getMarca().equalsIgnoreCase(""))){
			SessaoSistema.setCodigodMensagem(1);
			SessaoSistema.setDescMensagem("Campo Marca não informado");
			return carro = null;
		}
		
		if (carro.getModelo() == null || (carro.getModelo().equalsIgnoreCase(""))){
			SessaoSistema.setCodigodMensagem(1);
			SessaoSistema.setDescMensagem("Campo Modelo não informado");
			return carro = null;
		}
		
		if (carro.getAnoFabricacao() == null || (carro.getAnoFabricacao().equalsIgnoreCase(""))){
			SessaoSistema.setCodigodMensagem(1);
			SessaoSistema.setDescMensagem("Campo Ano Fabricação não informado");
			return carro = null;
		}
		
		if (carro.getAnoModelo() == null || (carro.getAnoModelo().equalsIgnoreCase(""))){
			SessaoSistema.setCodigodMensagem(1);
			SessaoSistema.setDescMensagem("Campo Ano Modelo não informado");
			return carro = null;
		}
		
		if (carro.getCor() == null || (carro.getCor().equalsIgnoreCase(""))){
			SessaoSistema.setCodigodMensagem(1);
			SessaoSistema.setDescMensagem("Campo Cor não informado");
			return carro = null;
		}
		
		if (carro.getPlaca() == null || (carro.getPlaca().equalsIgnoreCase(""))){
			SessaoSistema.setCodigodMensagem(1);
			SessaoSistema.setDescMensagem("Campo Placa não informado");
			return carro = null;
		}
		
		if (carro.getQuilometragem() == null){
			SessaoSistema.setCodigodMensagem(1);
			SessaoSistema.setDescMensagem("Campo Quilometragem não informado");
			return carro = null;
		}
		
		if (carro != null){	
		 	PersistenciaTimeline persistenciaTimeline = new PersistenciaTimeline();
			String postDescricao = SessaoSistema.getNomeUsuarioLogado() + " adicionou um novo carro: " + carro.getMarca() + " " + carro.getModelo();
		 	persistenciaTimeline.cadastraPostDAO(postDescricao);	
		}
		return persistenciaCarro.cadastraCarroDAO(carro);
	}

	public Carro alteraCarroModel(Carro carro) {
		
		if (carro.getApelido() == null || (carro.getApelido().equalsIgnoreCase(""))){
			SessaoSistema.setCodigodMensagem(1);
			SessaoSistema.setDescMensagem("Campo Apelido não informado");
			return carro = null;
		}
		
		if (persistenciaCarro.validaApelidoCarroDAO(carro.getUsuarioIdUsuario(), carro.getApelido())){
			SessaoSistema.setCodigodMensagem(2);
			SessaoSistema.setDescMensagem("Apelido já cadastrado");
			return carro = null;
		}
		
		if (carro.getMarca() == null || (carro.getMarca().equalsIgnoreCase(""))){
			SessaoSistema.setCodigodMensagem(1);
			SessaoSistema.setDescMensagem("Campo Marca não informado");
			return carro = null;
		}
		
		if (carro.getModelo() == null || (carro.getModelo().equalsIgnoreCase(""))){
			SessaoSistema.setCodigodMensagem(1);
			SessaoSistema.setDescMensagem("Campo Modelo não informado");
			return carro = null;
		}
		
		if (carro.getAnoFabricacao() == null || (carro.getAnoFabricacao().equalsIgnoreCase(""))){
			SessaoSistema.setCodigodMensagem(1);
			SessaoSistema.setDescMensagem("Campo Ano Fabricação não informado");
			return carro = null;
		}
		
		if (carro.getAnoModelo() == null || (carro.getAnoModelo().equalsIgnoreCase(""))){
			SessaoSistema.setCodigodMensagem(1);
			SessaoSistema.setDescMensagem("Campo Ano Modelo não informado");
			return carro = null;
		}
		
		if (carro.getCor() == null || (carro.getCor().equalsIgnoreCase(""))){
			SessaoSistema.setCodigodMensagem(1);
			SessaoSistema.setDescMensagem("Campo Cor não informado");
			return carro = null;
		}
		
		if (carro.getPlaca() == null || (carro.getPlaca().equalsIgnoreCase(""))){
			SessaoSistema.setCodigodMensagem(1);
			SessaoSistema.setDescMensagem("Campo Placa não informado");
			return carro = null;
		}
		
		if (carro.getQuilometragem() == null){
			SessaoSistema.setCodigodMensagem(1);
			SessaoSistema.setDescMensagem("Campo Quilometragem não informado");
			return carro = null;
		}
		
		if (carro != null){	
		 	PersistenciaTimeline persistenciaTimeline = new PersistenciaTimeline();
			String postDescricao = SessaoSistema.getNomeUsuarioLogado() + " adicionou um novo carro: " + carro.getMarca() + " " + carro.getModelo();
		 	persistenciaTimeline.cadastraPostDAO(postDescricao);	
		}
		return persistenciaCarro.alteraCarroDAO(carro);
	}

	public Carro consultaCarroModel(Carro carro) {
		return persistenciaCarro.consultaCarroDAO(carro);
	}

	public boolean excluiCarroModel(Carro carro) {
		return persistenciaCarro.excluiCarroDAO(carro);
	}

	public List<Carro> listaCarrosModel(Integer idUsuario) {
		return persistenciaCarro.listaCarrosDAO(idUsuario);
		
	}
	public Acessorios cadastrarAcessoriosModel(Carro carro, Acessorios acessorios) {
		
		if (acessorios.getNome() == null || (acessorios.getNome().equalsIgnoreCase(""))){
			SessaoSistema.setCodigodMensagem(1);
			SessaoSistema.setDescMensagem("Campo Nome não informado");
			return acessorios = null;
		}
		
		if (persistenciaCarro.validaNomeAcessorioDAO(carro.getIdCarro(), acessorios.getNome())){
			SessaoSistema.setCodigodMensagem(2);
			SessaoSistema.setDescMensagem("Nome já cadastrado");
			return acessorios = null;
		}
		
		if (acessorios != null){	
		 	PersistenciaTimeline persistenciaTimeline = new PersistenciaTimeline();
			String postDescricao = SessaoSistema.getNomeUsuarioLogado() + " adicionou um novo acessório: " + acessorios.getMarca() + " " + acessorios.getModelo();
		 	persistenciaTimeline.cadastraPostDAO(postDescricao);	
		}
		
		return persistenciaCarro.cadastraAcessoriosDAO(carro, acessorios);
	}
	
	public Acessorios alteraAcessoriosModel(Acessorios acessorios) {		
		if (acessorios.getNome() == null || (acessorios.getNome().equalsIgnoreCase(""))){
			SessaoSistema.setCodigodMensagem(1);
			SessaoSistema.setDescMensagem("Campo Nome não informado");
			return acessorios = null;
		}
		
		if (persistenciaCarro.validaNomeAcessorioDAO(acessorios.getCarro_idCarro(), acessorios.getNome())){
			SessaoSistema.setCodigodMensagem(2);
			SessaoSistema.setDescMensagem("Nome já cadastrado");
			return acessorios = null;
		}
		
		if (acessorios != null){	
		 	PersistenciaTimeline persistenciaTimeline = new PersistenciaTimeline();
			String postDescricao = SessaoSistema.getNomeUsuarioLogado() + " adicionou um novo acessório: " + acessorios.getMarca() + " " + acessorios.getModelo();
		 	persistenciaTimeline.cadastraPostDAO(postDescricao);	
		}
		
		return persistenciaCarro.alteraAcessoriosDAO(acessorios);
	}

	public Acessorios consultaAcessoriosModel(Acessorios acessorios) {
		return persistenciaCarro.consultaAcessoriosDAO(acessorios);
	}

	public boolean excluiAcessoriosModel(Acessorios acessorios) {
		return persistenciaCarro.excluiAcessoriosDAO(acessorios);
	}

	public List<Acessorios> listaAcessoriosModel(Carro carro) {
		return persistenciaCarro.listaAcessoriosDAO(carro);
	}

}
