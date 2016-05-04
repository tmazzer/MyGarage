/**
 * 
 */
package opet.mygarage.util;

/**
 * Classe padrão para tratamento de Mensagens do Sistema
 * 
 * @author Juliano Zapelini Batista / Tiago Mazzer
 * 
 * @since 19/04/2016
 * 
 * @version 1.0
 * 
 */
public class SessaoSistema {
	/*
	 * Variáveis de instância
	 */
	public static Integer codigodMensagem;
	private static String  descMensagem;
	private static Integer idUsuarioLogado;
	private static String nomeUsuarioLogado;
	
	/*
	 * Função construtora da classe
	 */
	
	public SessaoSistema(){
		
	}
	
	
	/*
	 * Métodos de acesso
	 */
	
	/**
	 * @return the idUsuarioLogado
	 */
	public static Integer getIdUsuarioLogado() {
		return idUsuarioLogado;
	}

	/**
	 * @param idUsuarioLogado the idUsuarioLogado to set
	 */
	public static void setIdUsuarioLogado(Integer idUsuarioLogado) {
		SessaoSistema.idUsuarioLogado = idUsuarioLogado;
	}

	/**
	 * @return the nomeUsuarioLogado
	 */
	public static String getNomeUsuarioLogado() {
		return nomeUsuarioLogado;
	}

	/**
	 * @param nomeUsuarioLogado the nomeUsuarioLogado to set
	 */
	public static void setNomeUsuarioLogado(String nomeUsuarioLogado) {
		SessaoSistema.nomeUsuarioLogado = nomeUsuarioLogado;
	}

	/**
	 * @return the descMensagem
	 */
	public static String getDescMensagem() {
		return descMensagem;
	}

	/**
	 * @return the codigodMensagem
	 */
	public static Integer getCodigodMensagem() {
		return codigodMensagem;
	}

	/**
	 * @param codigodMensagem the codigodMensagem to set
	 * Tabela códigos padrão:
	 *    0 - Sucesso
	 *    1 - Campo obrigatório não preenchido
	 *    2 - Campo informado inválido
	 *    3 - Erro ao cadastrar Codigo de Relacionamento do Usuario
	 *    100 - Select não retornou dados
	 *    101 - Erro ao abrir o BD
	 *    102 - Erro ao excluir os dados!
	 *    103 - Erro ao consultar os dados!
	 *    104 - Erro ao atualizar os dados!
	 *    105 - Erro ao inserir os dados!
	 */
	public static void setCodigodMensagem(Integer codigodMensagem) {
		SessaoSistema.codigodMensagem = codigodMensagem;
	}

	/**
	 * @param descMensagem the descMensagem to set
	 */
	public static void setDescMensagem(String descMensagem) {
		SessaoSistema.descMensagem = descMensagem;
	}
	
	
	/*
	 * Operações da classe
	 */
	
	public static void limpaSessao(){
		codigodMensagem = 0;
		descMensagem = "";
		idUsuarioLogado = 0;
		nomeUsuarioLogado = "";
	}

}
