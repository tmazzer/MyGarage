/**
 * 
 */
package opet.mygarage.util;

/**
 * Classe padr�o para tratamento de Mensagens do Sistema
 * 
 * @author Juliano Zapelini Batista / Tiago Mazzer
 * 
 * @since 19/04/2016
 * 
 * @version 1.0
 * 
 */
public class MensagemRetorno {
	/*
	 * Vari�veis de inst�ncia
	 */
	public static Integer codigodMensagem = 0;
	private static String  descMensagem = "";
	/*
	 * Fun��o construtora da classe
	 */
	
	public MensagemRetorno(){
		
	}
	/*
	 * M�todos de acesso
	 */
	
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
	 * Tabela c�digos padr�o:
	 *    0 - Sucesso
	 *    1 - Campo obrigat�rio n�o preenchido
	 *    2 - Campo informado inv�lido
	 *    100 - Select n�o retornou dados
	 *    101 - Erro ao abrir o BD
	 *    102 - Erro ao excluir os dados!
	 *    103 - Erro ao consultar os dados!
	 *    104 - Erro ao atualizar os dados!
	 *    105 - Erro ao inserir os dados!
	 */
	public static void setCodigodMensagem(Integer codigodMensagem) {
		MensagemRetorno.codigodMensagem = codigodMensagem;
	}

	/**
	 * @param descMensagem the descMensagem to set
	 */
	public static void setDescMensagem(String descMensagem) {
		MensagemRetorno.descMensagem = descMensagem;
	}
	
	/*
	 * Opera��es da classe
	 */
}
