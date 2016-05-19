/**
 * 
 */
package opet.mygarage.model.persistencia;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

import opet.mygarage.util.ConnectionFactory;
import opet.mygarage.util.SessaoSistema;
import opet.mygarage.vo.Timeline;

/**
 * @author juliano
 *
 */
public class TimelineDAO implements ITimelineDAO {
	/*
	 * Vari�veis de inst�ncia
	 */
	private Connection connection;
	
	private List<Timeline> timelineList;
	
	/*
	 * Fun��o construtora
	 */
	public TimelineDAO(){
		
	}
	
	/*
	 * Opera��es da classe
	 */
	
	
	
	/**
	 * Acessa a base Timeline para recuperar a lista de Timelines
	 * 
	 * @see opet.mygarage.model.persistencia.ITimelineDAO#listaTimelineDAO()
	 */
	@Override
	public List<Timeline> listaTimelineDAO() {
		
		ResultSet resultSet = null;	
		
		CallableStatement callstmt = null;
		
		Timeline timeline;
		
		timelineList = new ArrayList<>();
		
		try {
			connection = ConnectionFactory.getConnection();

			if (connection == null) {
				SessaoSistema.setCodigodMensagem(101);
				SessaoSistema.setDescMensagem("Erro ao abrir o Banco de dados");
				System.out.println("LOG::RelacionamentoDAO:: " + SessaoSistema.getDescMensagem());
				return null;
			} 
			
			callstmt = connection.prepareCall("{CALL MONTATIMELINE(?,?)}");
			callstmt.registerOutParameter(1, OracleTypes.CURSOR);
			callstmt.setInt(2, SessaoSistema.getIdUsuarioLogado());
			callstmt.executeQuery();

			resultSet = (ResultSet) callstmt.getObject(1);
			
			while (resultSet.next()) {
				timeline = new Timeline();
				
				timeline.setIdTimeline(resultSet.getInt("IDTIMELINE"));
				timeline.setNomeUSuario(resultSet.getString("NOME"));
				timeline.setIdUSuario(resultSet.getInt("USUARIO_IDUSUARIO"));
				timeline.setDescricao(resultSet.getString("DESCRICAO"));
				timeline.setDataCadastro(resultSet.getDate("DATACADASTRO"));
				
				timelineList.add(timeline);

			}
			if (callstmt != null)
				callstmt.close();
			
		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(106);
			SessaoSistema.setDescMensagem("Erro ao executar Store Procedure!");
			System.out.println("LOG::TimelineDAO:: " + SessaoSistema.getDescMensagem());
			System.out.println("LOG::TimelineDAO::ERRO::  " + e);
			e.printStackTrace();
			return null;
		} finally {
			ConnectionFactory.closeConnection();
		}		
		
		return timelineList;
	}
	
	/**
	 * Acessa a base Timeline e lista a TImeline de um Usuario Selecionado
	 * 
	 * @see opet.mygarage.model.persistencia.ITimelineDAO#listaTimelineDAO()
	 */
	@Override
	public List<Timeline> listaTimelineUsuarioDAO(Integer idUsuario) {
		
		List<Timeline> timelineList = null;
		
		Timeline timeline = null;

		PreparedStatement preparedStatement = null;

		String query = null;

		ResultSet resultSet = null;
		
		try {
			connection = ConnectionFactory.getConnection();

			if (connection == null) {
				SessaoSistema.setCodigodMensagem(101);
				SessaoSistema.setDescMensagem("Erro ao abrir o Banco de dados");
				System.out.println("LOG::TimelineDAO:: " + SessaoSistema.getDescMensagem());
				return null;
			} 

			query = "SELECT * FROM TIMELINE "
					+ "WHERE USUARIO_IDUSUARIO = ? "
					+ "ORDER BY DATACADASTRO";
			
//			CAMPOS:
//			IDTIMELINE			- Integer
//			USUARIO_IDUSUARIO 	- Integer
//			DATACADASTRO 		- Date
//			DESCRICAO  			- VarChar


			preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			
			preparedStatement.setInt(1, idUsuario);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.last()) {
				
				resultSet.beforeFirst();
				timelineList = new ArrayList<>();
				
				while (resultSet.next()) {

					timeline = new Timeline();
					
					timeline.setIdTimeline(resultSet.getInt("IDTIMELINE"));
					timeline.setNomeUSuario("");
					timeline.setIdUSuario(idUsuario);
					timeline.setDescricao(resultSet.getString("DESCRICAO"));
					timeline.setDataCadastro(resultSet.getDate("DATACADASTRO"));
					
					timelineList.add(timeline);
				}
			}
			else {
				SessaoSistema.setCodigodMensagem(100);
				SessaoSistema.setDescMensagem("Select n�o retornou dados");
				System.out.println("LOG::TimelineDAO:: " + SessaoSistema.getDescMensagem());
				timelineList = null;
			}

		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(103);
			SessaoSistema.setDescMensagem("Erro ao consultar os dados!");
			System.out.println("LOG::TimelineDAO:: " + SessaoSistema.getDescMensagem());
			System.out.println("LOG::TimelineDAO::ERRO::  " + e);
			e.printStackTrace();
			timelineList = null;
		} finally {
			ConnectionFactory.closeConnection();
		}

		return timelineList;
	}

	/**
	 * Acessa a base Timelinepara cadastrar um Post
	 */
	@Override
	public Boolean cadastraPostDAO(String postDescricao) {
		
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

			query = "INSERT INTO TIMELINE "
					+ "(IDTIMELINE, "
					+ "USUARIO_IDUSUARIO, "
					+ "DATACADASTRO, "
					+ "DESCRICAO) "
					+ "VALUES ("
					+ "IDTIMELINE_SEQUENCE.NEXTVAL, " 
					+ "?, " 
					+ "SYSDATE, " 
					+ "?)"; 

//			CAMPOS:
//			IDTIMELINE			- Integer
//			USUARIO_IDUSUARIO 	- Integer
//			DATACADASTRO 		- Date
//			DESCRICAO  			- VarChar			
			
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, SessaoSistema.getIdUsuarioLogado());
			preparedStatement.setString(2, postDescricao);
			
			count = new Integer(preparedStatement.executeUpdate());

			if (count != null && count.equals(1)) {
				System.out.println("LOG::cadastraPostDAO::Insert com sucesso");
				connection.commit();
				SessaoSistema.setCodigodMensagem(0);
				return true;
			} else {
				SessaoSistema.setCodigodMensagem(105);
				SessaoSistema.setDescMensagem("Erro ao inserir os dados!");
				System.out.println("LOG::cadastraPostDAO:: " + SessaoSistema.getDescMensagem());
				return false;
			}

		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(105);
			SessaoSistema.setDescMensagem("Erro ao inserir os dados!");
			System.out.println("LOG::cadastraPostDAO:: " + SessaoSistema.getDescMensagem());
			System.out.println("LOG::cadastraPostDAO::ERRO::  " + e);
			e.printStackTrace();
			return false;
		} finally {
			ConnectionFactory.closeConnection();
		}

	}
	/**
	 * Acessa a base Timeline para excluir um Post
	 */
	@Override
	public Boolean excluiPostDAO(Integer idTimeline) {
		
		PreparedStatement preparedStatement = null;

		String query = null;

		Integer count = null;
		
		try {
			connection = ConnectionFactory.getConnection();
			// Processamento dos dados

			if (connection == null) {
				SessaoSistema.setCodigodMensagem(101);
				SessaoSistema.setDescMensagem("Erro ao abrir o Banco de dados");
				System.out.println("LOG::TimelineDAO::excluiPostDAO " + SessaoSistema.getDescMensagem());
				return null;
			} 

			query = "DELETE FROM TIMELINE WHERE IDTIMELINE = ?";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, idTimeline);		

			count = new Integer(preparedStatement.executeUpdate());

			if (count.equals(1)) {
				connection.commit();

				SessaoSistema.setCodigodMensagem(0);
				SessaoSistema.setDescMensagem("Dados exclu�dos com sucesso!");
				return true;

			} else {
				SessaoSistema.setCodigodMensagem(102);
				SessaoSistema.setDescMensagem("Erro ao excluir os dados! Timeline n�o encontrado");
				System.out.println("TimelineDAO::excluiPostDAO " + SessaoSistema.getDescMensagem());
				return false;
			}

		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(102);
			SessaoSistema.setDescMensagem("Erro ao excluir os dados!");
			System.out.println("TimelineDAO::excluiPostDAO " + SessaoSistema.getDescMensagem());
			System.out.println("TimelineDAO::excluiPostDAO  " + e);
			e.printStackTrace();
			return false;
		} finally {
			ConnectionFactory.closeConnection();
		}
	}

}