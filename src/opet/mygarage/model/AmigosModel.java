/**
 * 
 */
package opet.mygarage.model;

import java.util.List;

import opet.mygarage.model.persistencia.PersistenciaCodigoAmigo;
import opet.mygarage.model.persistencia.PersistenciaRelacionamento;
import opet.mygarage.model.persistencia.PersistenciaUsuario;
import opet.mygarage.util.SessaoSistema;
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
	
	private PersistenciaCodigoAmigo persistenciaCodigoAmigo;
	
	private PersistenciaRelacionamento persistenciaRelacionamento;
	
	/*
	 * Função construtora
	 */

	public AmigosModel() {
		persistenciaUsuario = new PersistenciaUsuario();
		
		persistenciaCodigoAmigo = new PersistenciaCodigoAmigo();
		
		persistenciaRelacionamento = new PersistenciaRelacionamento();
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
		
		//VALIDAR SE JA EXISTE AMIZADE!! SE SIM!! APENAS ALTERAR STATUS
		
		Integer codigoAmigo = 0;
		//Consulta codigo_amigo do Usuario logado
		codigoAmigo = persistenciaCodigoAmigo.consultaCodigoAmigoDAO(SessaoSistema.getIdUsuarioLogado());
		// Insere : codigo_amigo (usuario Logado) e idUsuario (amigo)
		if(persistenciaRelacionamento.cadastraRelacionamentoDAO(codigoAmigo, usuario.getIdUsuario())){
			//Consulta codigo_amigo do Usuario Amigo
			codigoAmigo = persistenciaCodigoAmigo.consultaCodigoAmigoDAO(usuario.getIdUsuario());
			// Insere : codigo_amigo (usuario Amigo) e idUsuario (logado)
			return persistenciaRelacionamento.cadastraRelacionamentoDAO(codigoAmigo, SessaoSistema.getIdUsuarioLogado());
		} else
		{
			return false;
		}	 
	}
	
	/**
	 * Exclui Amigo
	 */
	public boolean excluiAmigoModel(Usuario usuario) {
		//exclusao logica
		
		return false;
	}
	/**
	 * Valida se o Usuario selecionado é amigo do Usuario logado
	 * 
	 */
	public boolean validaAmigo(Integer idUsuario) {
		Integer codigoAmigoUsuarioLogado = 0;
		//Consulta codigo_amigo do Usuario logado
		codigoAmigoUsuarioLogado = persistenciaCodigoAmigo.consultaCodigoAmigoDAO(SessaoSistema.getIdUsuarioLogado());
		return persistenciaRelacionamento.consultaRelacionamentoDAO(codigoAmigoUsuarioLogado, idUsuario);
	
	}



}
