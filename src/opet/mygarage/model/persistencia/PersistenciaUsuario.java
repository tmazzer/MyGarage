/**
 * 
 */
package opet.mygarage.model.persistencia;

import java.util.List;

import opet.mygarage.vo.Usuario;

/**
 * Classe Persistencia do Usuario
 * 
 * @author Juliano Zapelini Batista / Tiago Mazzer
 * 
 * @since 19/04/2016
 * 
 * @version 1.0
 * 
 */
public class PersistenciaUsuario implements IUsuarioDAO {
	/*
	 * Variáveis de instância
	 */
	
	public UsuarioDAO usuarioDAO;
	
	/*
	 * Função construtora
	 */
	
	public PersistenciaUsuario(){
		usuarioDAO = new UsuarioDAO();
	}
	
	/*
	 * Operações da classe
	 */
	
	/**
	 * Cadastra um novo Usuario na tabela Usuario
	 * 
	 * @see
	 * cadastro.pessoa.persistencia.IPessoaDAO#salvar(cadastro.pessoa.vo.Pessoa)
	 */
	@Override
	public Usuario cadastraUsuarioDAO(Usuario usuario) {
		return usuarioDAO.cadastraUsuarioDAO(usuario);
	}
	
	/**
	 * Consulta Usuario existente na tabela Usuario
	 * 
	 * @see
	 * cadastro.pessoa.persistencia.IPessoaDAO#salvar(cadastro.pessoa.vo.Pessoa)
	 */
	@Override
	public Usuario consultaUsuarioDAO(Usuario usuario) {
		return usuarioDAO.consultaUsuarioDAO(usuario);
	}
	/**
	 * Exclui Usuario existente na tabela Usuario
	 * Retorna TRUE para Excluido com sucesso
	 * Retorna FALSE se ERRO ao Excluir
	 * @see
	 * cadastro.pessoa.persistencia.IPessoaDAO#salvar(cadastro.pessoa.vo.Pessoa)
	 */
	@Override
	public Boolean excluiUsuarioDAO(Usuario usuario) {
		
		if(usuarioDAO.excluiUsuarioDAO(usuario)){
			return true;
		}else{
			return false;
		}		
	}
	
	/**
	 * Altera Usuario existente na tabela Usuario
	 * 
	 * @see
	 * cadastro.pessoa.persistencia.IPessoaDAO#salvar(cadastro.pessoa.vo.Pessoa)
	 */
	@Override
	public Usuario alteraUsuarioDAO(Usuario usuario) {
		return usuarioDAO.alteraUsuarioDAO(usuario);
	}
	/**
	 * Consulta Usuario, procurando por Email
	 * 
	 * @see
	 * cadastro.pessoa.persistencia.IPessoaDAO#salvar(cadastro.pessoa.vo.Pessoa)
	 */
	@Override
	public Usuario consultaPorEmailUsuarioDAO(Usuario usuario) {
		return usuarioDAO.consultaPorEmailUsuarioDAO(usuario);
	}

	@Override
	public List<Usuario> buscaUsuarioDAO(Usuario usuario) {
		return usuarioDAO.buscaUsuarioDAO(usuario);		
	}
}
