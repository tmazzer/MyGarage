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
	 * Vari�veis de inst�ncia
	 */
	private PersistenciaCarro persistenciaCarro;
	
	/*
	 * Fun��o construtora
	 */

	public CarroModel() {
		persistenciaCarro = new PersistenciaCarro();
		MensagemRetorno.setCodigodMensagem(0);
		MensagemRetorno.setDescMensagem("");
	}
	/*
	 * Opera��es da classe
	 */

	public Carro cadastrarCarroModel(Carro carro) {
		
		if (carro.getApelido() == null || (carro.getApelido().equalsIgnoreCase(""))){
			MensagemRetorno.setCodigodMensagem(1);
			MensagemRetorno.setDescMensagem("Campo Apelido n�o informado");
			return carro = null;
		}
		
		if (carro.getMarca() == null || (carro.getMarca().equalsIgnoreCase(""))){
			MensagemRetorno.setCodigodMensagem(1);
			MensagemRetorno.setDescMensagem("Campo Marca n�o informado");
			return carro = null;
		}
		
		if (carro.getModelo() == null || (carro.getModelo().equalsIgnoreCase(""))){
			MensagemRetorno.setCodigodMensagem(1);
			MensagemRetorno.setDescMensagem("Campo Modelo n�o informado");
			return carro = null;
		}
		
		if (carro.getAnoFabricacao() == null || (carro.getAnoFabricacao().equalsIgnoreCase(""))){
			MensagemRetorno.setCodigodMensagem(1);
			MensagemRetorno.setDescMensagem("Campo Ano Fabrica��o n�o informado");
			return carro = null;
		}
		
		if (carro.getAnoModelo() == null || (carro.getAnoModelo().equalsIgnoreCase(""))){
			MensagemRetorno.setCodigodMensagem(1);
			MensagemRetorno.setDescMensagem("Campo Ano Modelo n�o informado");
			return carro = null;
		}
		
		if (carro.getCor() == null || (carro.getCor().equalsIgnoreCase(""))){
			MensagemRetorno.setCodigodMensagem(1);
			MensagemRetorno.setDescMensagem("Campo Cor n�o informado");
			return carro = null;
		}
		
		if (carro.getPlaca() == null || (carro.getPlaca().equalsIgnoreCase(""))){
			MensagemRetorno.setCodigodMensagem(1);
			MensagemRetorno.setDescMensagem("Campo Placa n�o informado");
			return carro = null;
		}
		
		if (carro.getQuilometragem() == null){
			MensagemRetorno.setCodigodMensagem(1);
			MensagemRetorno.setDescMensagem("Campo Quilometragem n�o informado");
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
