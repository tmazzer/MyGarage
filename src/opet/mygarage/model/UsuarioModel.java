/**
 * 
 */
package opet.mygarage.model;

import opet.mygarage.model.persistencia.PersistenciaUsuario;
import opet.mygarage.model.persistencia.PersistenciaCodigoAmigo;
import opet.mygarage.model.persistencia.PersistenciaRelacionamento;
import opet.mygarage.util.SessaoSistema;
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
	 * Vari�veis de inst�ncia
	 */
	private PersistenciaUsuario persistenciaUsuario;
	private PersistenciaCodigoAmigo persistenciaCodigoAmigo;

	/*
	 * Fun��o construtora
	 */

	public UsuarioModel() {
		persistenciaUsuario = new PersistenciaUsuario();
		persistenciaCodigoAmigo = new PersistenciaCodigoAmigo();
		SessaoSistema.setCodigodMensagem(0);
		SessaoSistema.setDescMensagem("");
	}
	/*
	 * Opera��es da classe
	 */

	/**
	 * Cadastra um novo Usuario. Acessa a Persistencia pra realizar SQL Valida
	 * Campos de entrada.
	 */
	public Usuario cadastrarUsuarioModel(Usuario usuario) {

		if (usuario.getNome() == null || (usuario.getNome().equalsIgnoreCase(""))) {
			SessaoSistema.setCodigodMensagem(1);
			SessaoSistema.setDescMensagem("Campo Nome n�o informado");
			return usuario = null;
		}

		if (usuario.getEmail() == null || (usuario.getEmail().equalsIgnoreCase(""))) {
			SessaoSistema.setCodigodMensagem(1);
			SessaoSistema.setDescMensagem("Campo Email n�o informado");
			return usuario = null;
		}

		if (usuario.getSenha() == null || (usuario.getSenha().equalsIgnoreCase(""))) {
			SessaoSistema.setCodigodMensagem(1);
			SessaoSistema.setDescMensagem("Campo Senha n�o informado");
			return usuario = null;
		}
		// Valida se o email ja existe!!!!
		persistenciaUsuario.consultaPorEmailUsuarioDAO(usuario);

		if (SessaoSistema.getCodigodMensagem() == 0) {
			// email j� cadastrado
			SessaoSistema.setCodigodMensagem(4);
			SessaoSistema.setDescMensagem("E-mail j� cadastrado. Favor informar outro e-mail.");
			return null;
		}

		// Cadastra Usuario
		persistenciaUsuario.cadastraUsuarioDAO(usuario);

		// Cadastra Codigo Amigo
		if (SessaoSistema.getCodigodMensagem() == 0) {
			if (persistenciaCodigoAmigo.cadastraCodigoAmigoDAO(usuario)) {

				// Consulta codigo_amigo do Usuario logado
				Integer codigoAmigo = persistenciaCodigoAmigo
						.consultaCodigoAmigoDAO(SessaoSistema.getIdUsuarioLogado());

				// Cadastrar Relacionamento com idusuario logado e com o proprio
				// codigoAmigo (necessario por causa da Timeline)
				PersistenciaRelacionamento persistenciaRelacionamento = new PersistenciaRelacionamento();
				persistenciaRelacionamento.cadastraRelacionamentoDAO(codigoAmigo, SessaoSistema.getIdUsuarioLogado());
			} else {
				SessaoSistema.setCodigodMensagem(3);
				SessaoSistema.setDescMensagem("Erro ao cadastrar Codigo de Relacionamento do Usuario");
			}
		}

		return usuario;
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
		if (persistenciaUsuario.excluiUsuarioDAO(usuario)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Altera Usuario. Acessa a Persistencia pra realizar SQL
	 * 
	 */
	public Usuario alteraUsuarioModel(Usuario usuario, String emailAnterior) {
		if (usuario.getNome() == null || (usuario.getNome().equalsIgnoreCase(""))) {
			SessaoSistema.setCodigodMensagem(1);
			SessaoSistema.setDescMensagem("Campo Nome n�o informado");
			return usuario = null;
		}

		if (usuario.getEmail() == null || (usuario.getEmail().equalsIgnoreCase(""))) {
			SessaoSistema.setCodigodMensagem(1);
			SessaoSistema.setDescMensagem("Campo Email n�o informado");
			return usuario = null;
		}

		if (usuario.getSenha() == null || (usuario.getSenha().equalsIgnoreCase(""))) {
			SessaoSistema.setCodigodMensagem(1);
			SessaoSistema.setDescMensagem("Campo Senha n�o informado");
			return usuario = null;
		}
		
		if (!emailAnterior.equals(usuario.getEmail())) { // verifica se o Usuario alterou o email, se sim, valida:
			persistenciaUsuario.consultaPorEmailUsuarioDAO(usuario); // Valida se o email ja existe!!!!

			if (SessaoSistema.getCodigodMensagem() == 0) {
				// email j� cadastrado
				SessaoSistema.setCodigodMensagem(4);
				SessaoSistema.setDescMensagem("E-mail j� cadastrado. Favor informar outro e-mail.");
				return null;
			}
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

		persistenciaUsuario.consultaPorEmailUsuarioDAO(usuarioBase);

		if (usuarioTela.getSenha().equalsIgnoreCase(usuarioBase.getSenha())) {
			SessaoSistema.setIdUsuarioLogado(usuarioBase.getIdUsuario());
			SessaoSistema.setNomeUsuarioLogado(usuarioBase.getNome());
			return true;
		} else {
			return false;
		}
	}

}
