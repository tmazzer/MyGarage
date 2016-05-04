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
	 * Vari�veis de inst�ncia
	 */
	private PersistenciaCarro persistenciaCarro;
	
	/*
	 * Fun��o construtora
	 */

	public CarroModel() {
		persistenciaCarro = new PersistenciaCarro();
		SessaoSistema.setCodigodMensagem(0);
		SessaoSistema.setDescMensagem("");
	}
	/*
	 * Opera��es da classe
	 */

	public Carro cadastrarCarroModel(Carro carro) {
		
		if (carro.getApelido() == null || (carro.getApelido().equalsIgnoreCase(""))){
			SessaoSistema.setCodigodMensagem(1);
			SessaoSistema.setDescMensagem("Campo Apelido n�o informado");
			return carro = null;
		}
		
		if (carro.getMarca() == null || (carro.getMarca().equalsIgnoreCase(""))){
			SessaoSistema.setCodigodMensagem(1);
			SessaoSistema.setDescMensagem("Campo Marca n�o informado");
			return carro = null;
		}
		
		if (carro.getModelo() == null || (carro.getModelo().equalsIgnoreCase(""))){
			SessaoSistema.setCodigodMensagem(1);
			SessaoSistema.setDescMensagem("Campo Modelo n�o informado");
			return carro = null;
		}
		
		if (carro.getAnoFabricacao() == null || (carro.getAnoFabricacao().equalsIgnoreCase(""))){
			SessaoSistema.setCodigodMensagem(1);
			SessaoSistema.setDescMensagem("Campo Ano Fabrica��o n�o informado");
			return carro = null;
		}
		
		if (carro.getAnoModelo() == null || (carro.getAnoModelo().equalsIgnoreCase(""))){
			SessaoSistema.setCodigodMensagem(1);
			SessaoSistema.setDescMensagem("Campo Ano Modelo n�o informado");
			return carro = null;
		}
		
		if (carro.getCor() == null || (carro.getCor().equalsIgnoreCase(""))){
			SessaoSistema.setCodigodMensagem(1);
			SessaoSistema.setDescMensagem("Campo Cor n�o informado");
			return carro = null;
		}
		
		if (carro.getPlaca() == null || (carro.getPlaca().equalsIgnoreCase(""))){
			SessaoSistema.setCodigodMensagem(1);
			SessaoSistema.setDescMensagem("Campo Placa n�o informado");
			return carro = null;
		}
		
		if (carro.getQuilometragem() == null){
			SessaoSistema.setCodigodMensagem(1);
			SessaoSistema.setDescMensagem("Campo Quilometragem n�o informado");
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
