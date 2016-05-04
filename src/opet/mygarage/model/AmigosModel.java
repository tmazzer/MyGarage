/**
 * 
 */
package opet.mygarage.model;

import java.util.List;

import opet.mygarage.model.persistencia.PersistenciaUsuario;
import opet.mygarage.vo.Usuario;

/**
 * Classe Model da Lista de Amigos
 * 
 * @author Juliano Zapelini Batista / Tiago Mazzer
 * 
 * @since 02/05/2016
 * 
 * @version 1.0
 * 
 */
public class AmigosModel {
	/*
	 * Variáveis de instância
	 */
	private PersistenciaUsuario persistenciaUsuario;
	
	/*
	 * Função construtora
	 */

	public AmigosModel() {
		persistenciaUsuario = new PersistenciaUsuario();
//		MensagemRetorno.setCodigodMensagem(0);
//		MensagemRetorno.setDescMensagem("");
	}
	/*
	 * Operações da classe
	 */
	
	/**
	 * Lista todos os amigos do Usuario
	 */
	public List<Usuario> listaAmigosModel() {
		return persistenciaUsuario.listaAmigosDAO();
	}
	
	/**
	 * Busca Usuarios com o Nome
	 */
	public List<Usuario> consultaUsuariosModel(Usuario usuario) {
		return persistenciaUsuario.buscaUsuarioDAO(usuario);
	}
	/**
	 * Adiciona Amigo
	 */
	public boolean adicionaAmigoModel(Usuario usuario) {
		
//		busca codigo usuario logado
//		insere com :
//				codigo usuario logado
//				id do usuario
//		busca codigo usuario (amigo)
//		insere com:
//				codigo usuario amigo
//				idusuariologado
		 
		return false;
	}
	
	/**
	 * Exclui Amigo
	 */
	public boolean excluiAmigoModel(Usuario usuario) {
		return false;
	}



}
