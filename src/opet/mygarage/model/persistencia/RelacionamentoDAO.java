/**
 * 
 */
package opet.mygarage.model.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import opet.mygarage.util.ConnectionFactory;
import opet.mygarage.util.SessaoSistema;
import opet.mygarage.vo.Relacionamento;

/**
 * Classe Persistencia da tabela Relacionamento
 * 
 * @author Juliano Zapelini Batista / Tiago Mazzer
 * 
 * @since 04/05/2016
 * 
 * @version 1.0
 * 
 */
public class RelacionamentoDAO implements IRelacionamentoDAO {
	/*
	 * Variáveis de instância
	 */
	private Connection connection;
	
	/*
	 * Função construtora
	 */
	public RelacionamentoDAO(){
		
	}
	
	/*
	 * Operações da classe
	 */
	
	
	/**
	 * Cadastrar relacionamento na base Relacionamento
	 * 
	 * @see opet.mygarage.model.persistencia.IRelacionamentoDAO#cadastraRelacionamentoDAO(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Boolean cadastraRelacionamentoDAO(Integer codigoAmigo, Integer idUsuario) {

		PreparedStatement preparedStatement = null;

		String query = null;

		Integer count = null;
		try {
			connection = ConnectionFactory.getConnection();

			if (connection == null) {
				SessaoSistema.setCodigodMensagem(101);
				SessaoSistema.setDescMensagem("Erro ao abrir o Banco de dados");
				System.out.println("LOG::RelacionamentoDAO:: " + SessaoSistema.getDescMensagem());
				return false;
			} 

			query = "INSERT INTO RELACIONAMENTO "
					+ "(idRELACIONAMENTO, "
					+ "Codigo_amigo_idCodigo_amigo, "
					+ "Usuario_idUsuario, "
					+ "tipo_grupo_idtipo_grupo, "
					+ "dtInicioRelacionamento, "
					+ "dtFimRelacionamento, "
					+ "statusRelacionamento)"
					+ "VALUES ("
					+ "IDRELACIONAMENTO_SEQUENCE.NEXTVAL, " //idRELACIONAMENTO
					+ "?, " //Codigo_amigo_idCodigo_amigo
					+ "?, " //Usuario_idUsuario
					+ "1, " //tipo_grupo_idtipo_grupo = 1 (AMIGO)
					+ "SYSDATE, " // dtInicioRelacionamento
					+ "?, "  //dtFimRelacionamento - No cadastro é nulo
					+ "'A')"; // A - ATIVO


			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, codigoAmigo);
			preparedStatement.setInt(2, idUsuario);
			preparedStatement.setNull(3, Types.NULL); //dtFimRelacionamento
			
			count = new Integer(preparedStatement.executeUpdate());

			if (count != null && count.equals(1)) {
				System.out.println("LOG::RelacionamentoDAO::Insert com sucesso");
				connection.commit();
				SessaoSistema.setCodigodMensagem(0);
				return true;
			} else {
				SessaoSistema.setCodigodMensagem(105);
				SessaoSistema.setDescMensagem("Erro ao inserir os dados!");
				System.out.println("LOG::RelacionamentoDAO:: " + SessaoSistema.getDescMensagem());
				return false;
			}

		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(105);
			SessaoSistema.setDescMensagem("Erro ao inserir os dados!");
			System.out.println("LOG::RelacionamentoDAO:: " + SessaoSistema.getDescMensagem());
			System.out.println("LOG::RelacionamentoDAO::ERRO::  " + e);
			e.printStackTrace();
			return false;
		} finally {
			ConnectionFactory.closeConnection();
		}
	}

	/**
	 * Desativar relacionamento na base Relacionamento
	 * 
	 * @see opet.mygarage.model.persistencia.IRelacionamentoDAO#desativaRelacionamentoDAO(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Boolean excluiRelacionamentoDAO(Integer codigoAmigo, Integer idUsuario) {
		PreparedStatement preparedStatement = null;

		String query = null;

		Integer count = null;
		try {
			connection = ConnectionFactory.getConnection();

			if (connection == null) {
				SessaoSistema.setCodigodMensagem(101);
				SessaoSistema.setDescMensagem("Erro ao abrir o Banco de dados");
				System.out.println("LOG::RelacionamentoDAO:: " + SessaoSistema.getDescMensagem());
				return false;
			} 

			query = "DELETE FROM RELACIONAMENTO WHERE "
					+ "CODIGO_AMIGO_IDCODIGO_AMIGO = ? AND "
					+ "USUARIO_IDUSUARIO = ?";


			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, codigoAmigo);
			preparedStatement.setInt(2, idUsuario);
			
			count = new Integer(preparedStatement.executeUpdate());

			if (count != null && count.equals(1)) {
				System.out.println("LOG::RelacionamentoDAO::Delete com sucesso");
				connection.commit();
				SessaoSistema.setCodigodMensagem(0);
				return true;
			} else {
				SessaoSistema.setCodigodMensagem(105);
				SessaoSistema.setDescMensagem("Erro ao deletar os dados!");
				System.out.println("LOG::RelacionamentoDAO:: " + SessaoSistema.getDescMensagem());
				return false;
			}

		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(105);
			SessaoSistema.setDescMensagem("Erro ao deletar os dados!");
			System.out.println("LOG::RelacionamentoDAO:: " + SessaoSistema.getDescMensagem());
			System.out.println("LOG::RelacionamentoDAO::ERRO::  " + e);
			e.printStackTrace();
			return false;
		} finally {
			ConnectionFactory.closeConnection();
		}
	}

	/**
	 * Lista relacionamentos da base Relacionamento com um Codigo informado
	 * 
	 * @see opet.mygarage.model.persistencia.IRelacionamentoDAO#listaAmigosRelacionamentoDAO(java.lang.Integer)
	 */
	@Override
	public List<Relacionamento> listaAmigosRelacionamentoDAO(Integer codigoAmigo) {
		
		List<Relacionamento> amigosList = null;
		
		Relacionamento relacionamento = null;

		PreparedStatement preparedStatement = null;

		String query = null;

		ResultSet resultSet = null;
		
		try {
			connection = ConnectionFactory.getConnection();

			if (connection == null) {
				SessaoSistema.setCodigodMensagem(101);
				SessaoSistema.setDescMensagem("Erro ao abrir o Banco de dados");
				System.out.println("LOG::RelacionamentoDAO:: " + SessaoSistema.getDescMensagem());
				return null;
			} 

			query = "SELECT * FROM RELACIONAMENTO  "
					+ "WHERE CODIGO_AMIGO_IDCODIGO_AMIGO = ? "
					+ "AND USUARIO_IDUSUARIO <> ?"
					+ "ORDER BY USUARIO_IDUSUARIO";
			
//			CAMPOS:
//			idRelacionamento				- Integer
//			Codigo_amigo_idCodigo_amigo 	- Integer
//			Usuario_idUsuario 				- Integer
//			tipo_grupo_idtipo_grupo  		- Integer
//			dtInicioRelacionamento 			- Date
//			dtFimRelacionamento 			- Date
//			statusRelacionamento 			- Char

			preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			
			preparedStatement.setInt(1, codigoAmigo);
			preparedStatement.setInt(2, SessaoSistema.getIdUsuarioLogado());
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.last()) {
				
				resultSet.beforeFirst();
				amigosList = new ArrayList<>();
				
				while (resultSet.next()) {

					relacionamento = new Relacionamento();
					relacionamento.setIdRelacionamento(new Integer(resultSet.getInt("IDRELACIONAMENTO")));
					relacionamento.setCodigoAmigo(new Integer(resultSet.getInt("Codigo_amigo_idCodigo_amigo")));
					relacionamento.setIdUsuario(new Integer(resultSet.getInt("Usuario_idUsuario")));
					relacionamento.setTipoGrupo(new Integer(resultSet.getInt("tipo_grupo_idtipo_grupo")));
				
					if (resultSet.getDate("dtInicioRelacionamento") != null) {
						relacionamento.setDataIniRelacionamento(new java.util.Date(resultSet.getDate("dtInicioRelacionamento").getTime()));
					} else
					{
						relacionamento.setDataIniRelacionamento(null);
					}
					
					if (resultSet.getDate("dtFimRelacionamento") != null) {
						relacionamento.setDataFimRelacionamento(new java.util.Date(resultSet.getDate("dtFimRelacionamento").getTime()));
					} else
					{
						relacionamento.setDataFimRelacionamento(null);
					}
					
					if (resultSet.getString("statusRelacionamento") != null) {					
						relacionamento.setStatusRelacionamento(resultSet.getString("statusRelacionamento"));
					} else
					{
						relacionamento.setStatusRelacionamento(null);
					}	
					
					amigosList.add(relacionamento);
				}
			}
			else {
				SessaoSistema.setCodigodMensagem(100);
				SessaoSistema.setDescMensagem("Select não retornou dados");
				System.out.println("LOG::RelacionamentoDAO:: " + SessaoSistema.getDescMensagem());
				amigosList = null;
			}

		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(103);
			SessaoSistema.setDescMensagem("Erro ao consultar os dados!");
			System.out.println("LOG::RelacionamentoDAO:: " + SessaoSistema.getDescMensagem());
			System.out.println("LOG::RelacionamentoDAO::ERRO::  " + e);
			e.printStackTrace();
			amigosList = null;
		} finally {
			ConnectionFactory.closeConnection();
		}

		return amigosList;
	}
	
	@Override
	public List<Relacionamento> listaUltimosAmigosRelacionamentoDAO(Integer codigoAmigo) {
		
		List<Relacionamento> amigosList = null;
		
		Relacionamento relacionamento = null;

		PreparedStatement preparedStatement = null;

		String query = null;

		ResultSet resultSet = null;
		
		try {
			connection = ConnectionFactory.getConnection();

			if (connection == null) {
				SessaoSistema.setCodigodMensagem(101);
				SessaoSistema.setDescMensagem("Erro ao abrir o Banco de dados");
				System.out.println("LOG::RelacionamentoDAO:: " + SessaoSistema.getDescMensagem());
				return null;
			} 

			query = "SELECT * FROM RELACIONAMENTO  "
					+ "WHERE CODIGO_AMIGO_IDCODIGO_AMIGO = ? "
					+ "AND USUARIO_IDUSUARIO <> ?"
					+ "ORDER BY DTINICIORELACIONAMENTO DESC";
			
//			CAMPOS:
//			idRelacionamento				- Integer
//			Codigo_amigo_idCodigo_amigo 	- Integer
//			Usuario_idUsuario 				- Integer
//			tipo_grupo_idtipo_grupo  		- Integer
//			dtInicioRelacionamento 			- Date
//			dtFimRelacionamento 			- Date
//			statusRelacionamento 			- Char

			preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			
			preparedStatement.setInt(1, codigoAmigo);
			preparedStatement.setInt(2, SessaoSistema.getIdUsuarioLogado());
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.last()) {
				
				resultSet.beforeFirst();
				amigosList = new ArrayList<>();
				
				while (resultSet.next()) {

					relacionamento = new Relacionamento();
					relacionamento.setIdRelacionamento(new Integer(resultSet.getInt("IDRELACIONAMENTO")));
					relacionamento.setCodigoAmigo(new Integer(resultSet.getInt("Codigo_amigo_idCodigo_amigo")));
					relacionamento.setIdUsuario(new Integer(resultSet.getInt("Usuario_idUsuario")));
					relacionamento.setTipoGrupo(new Integer(resultSet.getInt("tipo_grupo_idtipo_grupo")));
				
					if (resultSet.getDate("dtInicioRelacionamento") != null) {
						relacionamento.setDataIniRelacionamento(new java.util.Date(resultSet.getDate("dtInicioRelacionamento").getTime()));
					} else
					{
						relacionamento.setDataIniRelacionamento(null);
					}
					
					if (resultSet.getDate("dtFimRelacionamento") != null) {
						relacionamento.setDataFimRelacionamento(new java.util.Date(resultSet.getDate("dtFimRelacionamento").getTime()));
					} else
					{
						relacionamento.setDataFimRelacionamento(null);
					}
					
					if (resultSet.getString("statusRelacionamento") != null) {					
						relacionamento.setStatusRelacionamento(resultSet.getString("statusRelacionamento"));
					} else
					{
						relacionamento.setStatusRelacionamento(null);
					}	
					
					amigosList.add(relacionamento);
				}
			}
			else {
				SessaoSistema.setCodigodMensagem(100);
				SessaoSistema.setDescMensagem("Select não retornou dados");
				System.out.println("LOG::RelacionamentoDAO:: " + SessaoSistema.getDescMensagem());
				amigosList = null;
			}

		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(103);
			SessaoSistema.setDescMensagem("Erro ao consultar os dados!");
			System.out.println("LOG::RelacionamentoDAO:: " + SessaoSistema.getDescMensagem());
			System.out.println("LOG::RelacionamentoDAO::ERRO::  " + e);
			e.printStackTrace();
			amigosList = null;
		} finally {
			ConnectionFactory.closeConnection();
		}

		return amigosList;
	}
	
	/**
	 * Acessa a base Relacionamento para consultar Relacionamento (AMIGO)
	 * 
	 * @see opet.mygarage.model.persistencia.IRelacionamentoDAO#listaAmigosRelacionamentoDAO(java.lang.Integer)
	 */
	@Override
	public Relacionamento consultaRelacionamentoDAO(Integer codigoAmigo, Integer idUsuario){
	
		PreparedStatement preparedStatement = null;

		String query = null;

		ResultSet resultSet = null;
		
		Relacionamento relacionamento = new Relacionamento();
		
		try {
			connection = ConnectionFactory.getConnection();

			if (connection == null) {
				SessaoSistema.setCodigodMensagem(101);
				SessaoSistema.setDescMensagem("Erro ao abrir o Banco de dados");
				System.out.println("LOG::RelacionamentoDAO:: " + SessaoSistema.getDescMensagem());
				return null;
			} 

			query = "SELECT * FROM RELACIONAMENTO WHERE "
					+ "CODIGO_AMIGO_IDCODIGO_AMIGO = ? AND "
					+ "USUARIO_IDUSUARIO = ?";
			
//			CAMPOS:
//			idRelacionamento				- Integer
//			Codigo_amigo_idCodigo_amigo 	- Integer
//			Usuario_idUsuario 				- Integer
//			tipo_grupo_idtipo_grupo  		- Integer
//			dtInicioRelacionamento 			- Date
//			dtFimRelacionamento 			- Date
//			statusRelacionamento 			- Char
			

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, codigoAmigo);
			preparedStatement.setInt(2, idUsuario);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				System.out.println("LOG::RelacionamentoDAO::consultaRelacionamentoDAO:: Select com sucesso");
				
				relacionamento.setIdRelacionamento(new Integer(resultSet.getInt("IDRELACIONAMENTO")));
				relacionamento.setCodigoAmigo(new Integer(resultSet.getInt("Codigo_amigo_idCodigo_amigo")));
				relacionamento.setIdUsuario(new Integer(resultSet.getInt("Usuario_idUsuario")));
				relacionamento.setTipoGrupo(new Integer(resultSet.getInt("tipo_grupo_idtipo_grupo")));
			
				if (resultSet.getDate("dtInicioRelacionamento") != null) {
					relacionamento.setDataIniRelacionamento(new java.util.Date(resultSet.getDate("dtInicioRelacionamento").getTime()));
				} else
				{
					relacionamento.setDataIniRelacionamento(null);
				}
				
				if (resultSet.getDate("dtFimRelacionamento") != null) {
					relacionamento.setDataFimRelacionamento(new java.util.Date(resultSet.getDate("dtFimRelacionamento").getTime()));
				} else
				{
					relacionamento.setDataFimRelacionamento(null);
				}
				
				if (resultSet.getString("statusRelacionamento") != null) {					
					relacionamento.setStatusRelacionamento(resultSet.getString("statusRelacionamento"));
				} else
				{
					relacionamento.setStatusRelacionamento(null);
				}				
			} else {
				SessaoSistema.setCodigodMensagem(100);
				SessaoSistema.setDescMensagem("Select não retornou dados");
				System.out.println("LOG::RelacionamentoDAO:: " + SessaoSistema.getDescMensagem());
			}

		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(103);
			SessaoSistema.setDescMensagem("Erro ao consultar os dados!");
			System.out.println("LOG::RelacionamentoDAO:: " + SessaoSistema.getDescMensagem());
			System.out.println("LOG::RelacionamentoDAO::ERRO::  " + e);
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection();
		}		
		
		return relacionamento;
	}

}
