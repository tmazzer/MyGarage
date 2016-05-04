/**
 * 
 */
package opet.mygarage.model;

import opet.mygarage.model.persistencia.PersistenciaCarro;
import opet.mygarage.util.MensagemRetorno;
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
		MensagemRetorno.setCodigodMensagem(0);
		MensagemRetorno.setDescMensagem("");
	}
	/*
	 * Operações da classe
	 */

	public Carro cadastrarCarroModel(Carro carro) {
		
		if (carro.getApelido() == null || (carro.getApelido().equalsIgnoreCase(""))){
			MensagemRetorno.setCodigodMensagem(1);
			MensagemRetorno.setDescMensagem("Campo Apelido não informado");
			return carro = null;
		}
		
		if (carro.getMarca() == null || (carro.getMarca().equalsIgnoreCase(""))){
			MensagemRetorno.setCodigodMensagem(1);
			MensagemRetorno.setDescMensagem("Campo Marca não informado");
			return carro = null;
		}
		
		if (carro.getModelo() == null || (carro.getModelo().equalsIgnoreCase(""))){
			MensagemRetorno.setCodigodMensagem(1);
			MensagemRetorno.setDescMensagem("Campo Modelo não informado");
			return carro = null;
		}
		
		if (carro.getAnoFabricacao() == null || (carro.getAnoFabricacao().equalsIgnoreCase(""))){
			MensagemRetorno.setCodigodMensagem(1);
			MensagemRetorno.setDescMensagem("Campo Ano Fabricação não informado");
			return carro = null;
		}
		
		if (carro.getAnoModelo() == null || (carro.getAnoModelo().equalsIgnoreCase(""))){
			MensagemRetorno.setCodigodMensagem(1);
			MensagemRetorno.setDescMensagem("Campo Ano Modelo não informado");
			return carro = null;
		}
		
		if (carro.getCor() == null || (carro.getCor().equalsIgnoreCase(""))){
			MensagemRetorno.setCodigodMensagem(1);
			MensagemRetorno.setDescMensagem("Campo Cor não informado");
			return carro = null;
		}
		
		if (carro.getPlaca() == null || (carro.getPlaca().equalsIgnoreCase(""))){
			MensagemRetorno.setCodigodMensagem(1);
			MensagemRetorno.setDescMensagem("Campo Placa não informado");
			return carro = null;
		}
		
		if (carro.getQuilometragem() == null){
			MensagemRetorno.setCodigodMensagem(1);
			MensagemRetorno.setDescMensagem("Campo Quilometragem não informado");
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
