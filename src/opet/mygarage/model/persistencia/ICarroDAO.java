/**
 * 
 */
package opet.mygarage.model.persistencia;

import java.util.List;

import opet.mygarage.vo.Acessorios;
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
	
	public Acessorios cadastraAcessoriosDAO(Carro carro, Acessorios acessorios);
	public Acessorios consultaAcessoriosDAO(Acessorios acessorios);
	public Boolean excluiAcessoriosDAO(Acessorios acessorios);
	public Acessorios alteraAcessoriosDAO(Acessorios acessorios);
	public List<Carro> listaCarrosDAO(Integer idUsuario);
	
}
