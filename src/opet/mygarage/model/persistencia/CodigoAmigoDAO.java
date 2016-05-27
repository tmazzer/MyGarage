/**
 * 
 */
package opet.mygarage.model.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import opet.mygarage.util.ConnectionFactory;
import opet.mygarage.util.SessaoSistema;
import opet.mygarage.vo.Usuario;

/**
 * Interface ICodigoAmigo - Acessa a base CODIGO_AMIGO
 * 
 * @author Juliano Zapelini Batista / Tiago Mazzer
 * 
 * @since 03/05/2016
 * 
 * @version 1.0
 * 
 */
public class CodigoAmigoDAO implements ICodigoAmigoDAO {
	/*
	 * Variáveis de instância
	 */
	private Connection connection;
	
	
	/*
	 * Função construtora da classe
	 */

	public CodigoAmigoDAO() {

	}

	/*
	 * Operações da classe
	 */
	/**
	 * Cadastra um novo codigo na tabela Codigo_Amigo
	 * 
	 * @see opet.mygarage.model.persistencia.IUsuarioDAO#salvar(opet.mygarage.vo.Usuario)
	 */
	@Override
	public Boolean cadastraCodigoAmigoDAO(Usuario usuario) {
		
		PreparedStatement preparedStatement = null;

		String query = null;

		Integer count = null;
		try {
			connection = ConnectionFactory.getConnection();

			// Processamento dos dados
			if (connection == null) {
				SessaoSistema.setCodigodMensagem(101);
				SessaoSistema.setDescMensagem("Erro ao abrir o Banco de dados");
				System.out.println("LOG::CodigoAmigoDAO:: " + SessaoSistema.getDescMensagem());
				return false;
			} 

			query = "INSERT INTO CODIGO_AMIGO "
					+ "(IDCODIGO_AMIGO, USUARIO_IDUSUARIO) "
					+ "VALUES (IDCODIGO_AMIGO_SEQUENCE.NEXTVAL, ?)";


			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, usuario.getIdUsuario());

			// Executa INSERT

			count = new Integer(preparedStatement.executeUpdate());

			if (count != null && count.equals(1)) {
				System.out.println("LOG::CodigoAmigoDAO:Insert com sucesso");
				connection.commit();
				SessaoSistema.setCodigodMensagem(0);
				return true;
			} else {
				SessaoSistema.setCodigodMensagem(105);
				SessaoSistema.setDescMensagem("Erro ao inserir os dados!");
				System.out.println("LOG::CodigoAmigoDAO:: " + SessaoSistema.getDescMensagem());
				return false;
			}

		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(105);
			SessaoSistema.setDescMensagem("Erro ao inserir os dados!");
			System.out.println("LOG::CodigoAmigoDAO:: " + SessaoSistema.getDescMensagem());
			System.out.println("LOG::CodigoAmigoDAO::ERRO::  " + e);
			e.printStackTrace();
			return false;
		} finally {
			ConnectionFactory.closeConnection();
		}
	}

	/**
	 * Retorna o codigo na tabela Codigo_Amigo
	 * 
	 * @see opet.mygarage.model.persistencia.IUsuarioDAO#salvar(opet.mygarage.vo.Usuario)
	 */
	@Override
	public Integer consultaCodigoAmigoDAO(Integer idUsuario) {
		
		PreparedStatement preparedStatement = null;

		String query = null;

		ResultSet resultSet = null;
		
		try {
			connection = ConnectionFactory.getConnection();

			if (connection == null) {
				SessaoSistema.setCodigodMensagem(101);
				SessaoSistema.setDescMensagem("Erro ao abrir o Banco de dados");
				System.out.println("LOG::CodigoAmigoDAO:: " + SessaoSistema.getDescMensagem());
				return null;
			} 

			query = "SELECT * FROM CODIGO_AMIGO WHERE USUARIO_IDUSUARIO = ?";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, idUsuario);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return resultSet.getInt("IDCODIGO_AMIGO");
				
			} else {
				SessaoSistema.setCodigodMensagem(100);
				SessaoSistema.setDescMensagem("Select não retornou dados");
				System.out.println("LOG::CodigoAmigoDAO:: " + SessaoSistema.getDescMensagem());
				return null;
			}

		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(103);
			SessaoSistema.setDescMensagem("Erro ao consultar os dados!");
			System.out.println("LOG::CodigoAmigoDAO:: " + SessaoSistema.getDescMensagem());
			System.out.println("LOG::CodigoAmigoDAO::ERRO::  " + e);
			e.printStackTrace();
			return null;
		} finally {
			ConnectionFactory.closeConnection();
		}		
	}

}
