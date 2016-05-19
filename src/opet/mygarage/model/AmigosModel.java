/**
 * 
 */
package opet.mygarage.model;

import java.util.ArrayList;
import java.util.List;

import opet.mygarage.model.persistencia.PersistenciaCodigoAmigo;
import opet.mygarage.model.persistencia.PersistenciaRelacionamento;
import opet.mygarage.model.persistencia.PersistenciaTimeline;
import opet.mygarage.model.persistencia.PersistenciaUsuario;
import opet.mygarage.util.SessaoSistema;
import opet.mygarage.vo.Relacionamento;
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

	private List<Relacionamento> relacionamentoList;

	private List<Usuario> usuarioList;

	/*
	 * Função construtora
	 */

	public AmigosModel() {
		persistenciaUsuario = new PersistenciaUsuario();

		persistenciaCodigoAmigo = new PersistenciaCodigoAmigo();

		persistenciaRelacionamento = new PersistenciaRelacionamento();
	}
	/*
	 * Operações da classe
	 */

	/**
	 * Lista todos os amigos do Usuario
	 */
	public List<Usuario> listaAmigosModel() {

		Integer codigoAmigo = 0;

		// com o IDusuario(logado), recupera o codigo_amigo
		codigoAmigo = persistenciaCodigoAmigo.consultaCodigoAmigoDAO(SessaoSistema.getIdUsuarioLogado());

		// Com o codigo_amigo (logado), acessar Relacionaemnto e retorna lista
		// de usuarios com relacionamento
		relacionamentoList = persistenciaRelacionamento.listaAmigosRelacionamentoDAO(codigoAmigo);

		if (relacionamentoList != null) {
			// pra cada usuario, busca detalhes;
			Usuario usuario;
			usuarioList = new ArrayList<Usuario>();

			for (int i = 0; i < relacionamentoList.size(); i++) {

				usuario = new Usuario();

				usuario.setIdUsuario(relacionamentoList.get(i).getIdUsuario());

				usuarioList.add(persistenciaUsuario.consultaUsuarioDAO(usuario));
			}
			return usuarioList;
		} else{
			return null;
		}
			
		
	}
	
	/**
	 * Lista os ultimos 5 amigos do Usuario
	 */
	public List<Usuario> listaUltimosAmigosModel() {

		Integer codigoAmigo = 0;

		// com o IDusuario(logado), recupera o codigo_amigo
		codigoAmigo = persistenciaCodigoAmigo.consultaCodigoAmigoDAO(SessaoSistema.getIdUsuarioLogado());

		// Com o codigo_amigo (logado), acessar Relacionaemnto e retorna lista
		// de usuarios com relacionamento
		relacionamentoList = persistenciaRelacionamento.listaUltimosAmigosRelacionamentoDAO(codigoAmigo);

		if (relacionamentoList != null) {
			// pra cada usuario, busca detalhes;
			Usuario usuario;
			usuarioList = new ArrayList<Usuario>();

			for (int i = 0; i < relacionamentoList.size(); i++) {

				usuario = new Usuario();

				usuario.setIdUsuario(relacionamentoList.get(i).getIdUsuario());

				usuarioList.add(persistenciaUsuario.consultaUsuarioDAO(usuario));
			}
			return usuarioList;
		} else{
			return null;
		}
			
		
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

		Integer codigoAmigo = 0;

		// Consulta codigo_amigo do Usuario logado
		codigoAmigo = persistenciaCodigoAmigo.consultaCodigoAmigoDAO(SessaoSistema.getIdUsuarioLogado());
		
		// Insere : codigo_amigo (usuario Logado) e idUsuario (amigo)
		if (persistenciaRelacionamento.cadastraRelacionamentoDAO(codigoAmigo, usuario.getIdUsuario())) {

			// Consulta codigo_amigo do Usuario Amigo
			codigoAmigo = persistenciaCodigoAmigo.consultaCodigoAmigoDAO(usuario.getIdUsuario());

			// Insere : codigo_amigo (usuario Amigo) e idUsuario (logado)
			 if(persistenciaRelacionamento.cadastraRelacionamentoDAO(codigoAmigo, SessaoSistema.getIdUsuarioLogado())){
				 	PersistenciaTimeline persistenciaTimeline = new PersistenciaTimeline();
					String postDescricao = SessaoSistema.getNomeUsuarioLogado() + " adicionou " + usuario.getNome() + " como amigo";
				 	persistenciaTimeline.cadastraPostDAO(postDescricao);					
					return true;
			 }else{
				 return false;
			 }
			


		} else {
			return false;
		}
	}

	/**
	 * Exclui Amigo
	 */
	public boolean excluiAmigoModel(Usuario usuario) {

		Integer codigoAmigo = 0;

		// Consulta codigo_amigo do Usuario logado
		codigoAmigo = persistenciaCodigoAmigo.consultaCodigoAmigoDAO(SessaoSistema.getIdUsuarioLogado());

		// Exclui : codigo_amigo (usuario Logado) e idUsuario (amigo)
		if (persistenciaRelacionamento.excluiRelacionamentoDAO(codigoAmigo, usuario.getIdUsuario())) {

			// Consulta codigo_amigo do Usuario Amigo
			codigoAmigo = persistenciaCodigoAmigo.consultaCodigoAmigoDAO(usuario.getIdUsuario());

			// Exclui : codigo_amigo (usuario Amigo) e idUsuario (logado)
			return persistenciaRelacionamento.excluiRelacionamentoDAO(codigoAmigo, SessaoSistema.getIdUsuarioLogado());

		} else {
			return false;
		}
	}

	/**
	 * Valida se o Usuario selecionado é amigo do Usuario logado
	 * 
	 */
	public Relacionamento validaAmigo(Integer idUsuario) {

		Integer codigoAmigoUsuarioLogado = 0;
		
		// Consulta codigo_amigo do Usuario logado
		codigoAmigoUsuarioLogado = persistenciaCodigoAmigo.consultaCodigoAmigoDAO(SessaoSistema.getIdUsuarioLogado());
		
		Relacionamento relacionamento = new Relacionamento();
		
		relacionamento = persistenciaRelacionamento.consultaRelacionamentoDAO(codigoAmigoUsuarioLogado, idUsuario);
		
		if (SessaoSistema.getCodigodMensagem() == 0){
			return relacionamento;
		} else{
			return null;
		}
			
		

	}

}
