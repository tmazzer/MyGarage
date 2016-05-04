/**
 * 
 */
package opet.mygarage.model.persistencia;

import opet.mygarage.vo.Carro;

/**
 * Classe Interface do CarroDAO
 * 
 * @author Juliano Zapelini Batista / Tiago Mazzer
 * 
 * @since 19/04/2016
 * 
 * @version 1.0
 * 
 */
public interface ICarroDAO {
	public Carro cadastraCarroDAO(Carro carro);
	public Carro consultaCarroDAO(Carro carro);
	public Boolean excluiCarroDAO(Carro carro);
	public Carro alteraCarroDAO(Carro carro);
}
