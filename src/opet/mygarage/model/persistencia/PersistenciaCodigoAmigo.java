/**
 * 
 */
package opet.mygarage.model.persistencia;

import opet.mygarage.vo.Usuario;

/**
 * Persistencia da Classe ICodigoAmigo
 * 
 * @author Juliano Zapelini Batista / Tiago Mazzer
 * 
 * @since 03/05/2016
 * 
 * @version 1.0
 * 
 */
public class PersistenciaCodigoAmigo implements ICodigoAmigoDAO {

	/*
	 * Variáveis de instância
	 */
	
	public CodigoAmigoDAO codigoAmigoDAO;
	
	/*
	 * Função construtora
	 */
	
	public PersistenciaCodigoAmigo(){
		codigoAmigoDAO = new CodigoAmigoDAO();
	}
	
	/*
	 * Operações da classe
	 */
	
	/**
	 * Cadastra um codigo de relacionamento
	 * 
	 * @see
	 * cadastro.pessoa.persistencia.IPessoaDAO#salvar(cadastro.pessoa.vo.Pessoa)
	 */
	@Override
	public Boolean cadastraCodigoAmigoDAO(Usuario usuario) {
		return codigoAmigoDAO.cadastraCodigoAmigoDAO(usuario);
	}

	/**
	 * Consulta o codigo de relacionamento do Usuario informado
	 * 
	 * @see
	 * cadastro.pessoa.persistencia.IPessoaDAO#salvar(cadastro.pessoa.vo.Pessoa)
	 */
	@Override
	public Integer consultaCodigoAmigoDAO(Integer idUsuario) {
		return codigoAmigoDAO.consultaCodigoAmigoDAO(idUsuario);
	}

}
