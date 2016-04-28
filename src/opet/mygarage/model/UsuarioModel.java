/**
 * 
 */
package opet.mygarage.model;

import opet.mygarage.model.persistencia.PersistenciaUsuario;
import opet.mygarage.util.MensagemRetorno;
import opet.mygarage.vo.Usuario;

/**
 * Classe Model do Usuario
 * 
 * @author Juliano Zapelini Batista / Tiago Mazzer
 * 
 * @since 19/04/2016
 * 
 * @version 1.0
 * 
 */
public class UsuarioModel {
	/*
	 * Variáveis de instância
	 */
	private PersistenciaUsuario persistenciaUsuario;
	
	/*
	 * Função construtora
	 */

	public UsuarioModel() {
		persistenciaUsuario = new PersistenciaUsuario();
		MensagemRetorno.setCodigodMensagem(0);
		MensagemRetorno.setDescMensagem("");
	}
	/*
	 * Operações da classe
	 */
	
	
	/**
	 * Cadastra um novo Usuario. Acessa a Persistencia pra realizar SQL
	 * Valida Campos de entrada.
	 */
	public Usuario cadastrarUsuarioModel(Usuario usuario){
		
		if (usuario.getNome() == null || (usuario.getNome().equalsIgnoreCase(""))){
			MensagemRetorno.setCodigodMensagem(1);
			MensagemRetorno.setDescMensagem("Campo Nome não informado");
			return usuario = null;
		}
		
		if (usuario.getEmail() == null || (usuario.getEmail().equalsIgnoreCase(""))){
			MensagemRetorno.setCodigodMensagem(1);
			MensagemRetorno.setDescMensagem("Campo Email não informado");
			return usuario = null;
		}
		
		if (usuario.getSenha() == null || (usuario.getSenha().equalsIgnoreCase(""))){
			MensagemRetorno.setCodigodMensagem(1);
			MensagemRetorno.setDescMensagem("Campo Senha não informado");
			return usuario = null;
		}
		
		return persistenciaUsuario.cadastraUsuarioDAO(usuario);
	}

	/**
	 * Consulta Usuario. Acessa a Persistencia pra realizar SQL
	 * 
	 */
	public Usuario consultaUsuarioModel(Usuario usuario) {
		
		return persistenciaUsuario.consultaUsuarioDAO(usuario);
	}

	/**
	 * Exclui Usuario. Acessa a Persistencia pra realizar SQL
	 * 
	 */
	public Boolean excluiUsuarioModel(Usuario usuario) {
		if(persistenciaUsuario.excluiUsuarioDAO(usuario)){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Altera Usuario. Acessa a Persistencia pra realizar SQL
	 * 
	 */
	public Usuario alteraUsuarioModel(Usuario usuario) {
		if (usuario.getNome() == null || (usuario.getNome().equalsIgnoreCase(""))){
			MensagemRetorno.setCodigodMensagem(1);
			MensagemRetorno.setDescMensagem("Campo Nome não informado");
			return usuario;
		}
		
		if (usuario.getEmail() == null || (usuario.getEmail().equalsIgnoreCase(""))){
			MensagemRetorno.setCodigodMensagem(1);
			MensagemRetorno.setDescMensagem("Campo Email não informado");
			return usuario;
		}
		
		if (usuario.getSenha() == null || (usuario.getSenha().equalsIgnoreCase(""))){
			MensagemRetorno.setCodigodMensagem(1);
			MensagemRetorno.setDescMensagem("Campo Senha não informado");
			return usuario;
		}
		return persistenciaUsuario.alteraUsuarioDAO(usuario);
	}

	/**
	 * Valida usuario valido. Acessa a Persistencia pra realizar SQL
	 * 
	 */
	public boolean validaLoginModel(Usuario usuarioTela) {
		
		Usuario usuarioBase = new Usuario();
			
		usuarioBase.setEmail(usuarioTela.getEmail());
		
		persistenciaUsuario.validaLoginUsuarioDAO(usuarioBase);
		
		if(usuarioTela.getSenha().equalsIgnoreCase(usuarioBase.getSenha())){
			return true;
		}else{
			return false;
		}
		
		//ALTERAR Tabela - email obrigatorio!!!! chave primaria

	}

}
