/**
 * 
 */
package opet.mygarage.model.persistencia;

import opet.mygarage.vo.Usuario;

/**
 * Classe Interface do CodigoAmigoDAO
 * 
 * @author Juliano Zapelini Batista / Tiago Mazzer
 * 
 * @since 03/05/2016
 * 
 * @version 1.0
 * 
 */
public interface ICodigoAmigoDAO {
	public Boolean cadastraCodigoAmigoDAO(Usuario usuario);
	public Integer consultaCodigoAmigoDAO(Integer idUsuario);
	
}
