/**
 * 
 */
package opet.mygarage.model.persistencia;
import java.util.List;

import opet.mygarage.vo.Relacionamento;

/**
 * Classe Interface do RelacionamentoDAO
 * 
 * @author Juliano Zapelini Batista / Tiago Mazzer
 * 
 * @since 04/05/2016
 * 
 * @version 1.0
 * 
 */
public interface IRelacionamentoDAO {
	public Boolean cadastraRelacionamentoDAO(Integer codigoAmigo, Integer idUsuario);
	public Boolean desativaRelacionamentoDAO(Integer codigoAmigo, Integer idUsuario);
	public List<Relacionamento> listaAmigosRelacionamentoDAO(Integer codigoAmigo);
	public Boolean consultaRelacionamentoDAO(Integer codigoAmigo, Integer idUsuario);
}
