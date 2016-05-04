/**
 * 
 */
package opet.mygarage.model;

import opet.mygarage.model.persistencia.PersistenciaCarro;
import opet.mygarage.util.SessaoSistema;
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
		
		return persistenciaCarro.cadastraCarroDAO(carro);
	}

	public Carro alteraCarroModel(Carro carro) {
		// TODO Auto-generated method stub
		return null;
	}

	public Carro consultaCarroModel(Carro carro) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean excluiCarroModel(Carro carro) {
		// TODO Auto-generated method stub
		return false;
	}
}
