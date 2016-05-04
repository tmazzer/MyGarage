/**
 * 
 */
package opet.mygarage.model.persistencia;

import java.util.List;

import opet.mygarage.vo.Usuario;

/**
 * Classe Interface do UsuarioDAO
 * 
 * @author Juliano Zapelini Batista / Tiago Mazzer
 * 
 * @since 19/04/2016
 * 
 * @version 1.0
 * 
 */
public interface IUsuarioDAO {
	
	public Usuario cadastraUsuarioDAO(Usuario usuario);
	public Usuario consultaUsuarioDAO(Usuario usuario);
	public Boolean excluiUsuarioDAO(Usuario usuario);
	public Usuario alteraUsuarioDAO(Usuario usuario);
	public Usuario consultaPorEmailUsuarioDAO(Usuario usuario);
	//Amigos
	public List<Usuario> listaAmigosDAO();
	public List<Usuario> buscaUsuarioDAO(Usuario usuario);
}
