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
	 * Variáveis de instância
	 */
	private Connection connection;

	private List<Timeline> timelineList;

	/*
	 * Função construtora
	 */
	public TimelineDAO() {

	}

	/*
	 * Operações da classe
	 */

	/**
	 * Acessa a base Timeline para recuperar a lista de Timelines
	 * 
	 * @see opet.mygarage.model.persistencia.ITimelineDAO#listaTimelineDAO()
	 */
	@Override
	public List<Timeline> listaTimelineDAO() {

		ResultSet resultSet = null;

		Timeline timeline;

		timelineList = new ArrayList<>();

		PreparedStatement preparedStatement = null;

		String query = null;

		ResultSet resultSetLike = null;
		
		CallableStatement callstmt = null;

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

				// INICIO - Validar se o usurio logado deu like no timeline
				try {
					query = "SELECT * FROM TIMELINE_ACAO WHERE " + "USUARIO_IDUSUARIO = ? AND " // 1
							+ "TIMELINE_IDTIMELINE = ? AND " // 2
							+ "CURTIR = 'S'";

					preparedStatement = connection.prepareStatement(query);

					preparedStatement.setInt(1, SessaoSistema.getIdUsuarioLogado());
					preparedStatement.setInt(2, timeline.getIdTimeline());

					resultSetLike = preparedStatement.executeQuery();

					if (resultSetLike.next()) {
						timeline.setLike("S");
					} else {
						timeline.setLike(null);
					}
				} catch (SQLException e) {
					SessaoSistema.setCodigodMensagem(106);
					SessaoSistema.setDescMensagem("Erro ao executar select!");
					System.out.println("TimelineDAO::listaTimelineDAO:: " + SessaoSistema.getDescMensagem());
					System.out.println("TimelineDAO::listaTimelineDAO::ERRO::  " + e);
					e.printStackTrace();
					return null;
				}
				// FIM - Validar se o usurio logado deu like no timeline listado
				
				
				// INICIO - Recupera quantidade de likes em cada post
				try {
					callstmt = connection.prepareCall("{ CALL ? := COUNT_LIKES(?)}");
					
					callstmt.registerOutParameter(1, OracleTypes.INTEGER);
					
					callstmt.setInt(2, timeline.getIdTimeline());
					
					callstmt.execute();
				
					timeline.setQtddLike(callstmt.getInt(1));
					
				} catch (SQLException e) {
					SessaoSistema.setCodigodMensagem(106);
					SessaoSistema.setDescMensagem("Erro ao executar Function COUNT_LIKES!");
					System.out.println("LOG::TimelineAcaoDAO::listaTimelineDAO " + SessaoSistema.getDescMensagem());
					System.out.println("LOG::TimelineAcaoDAO::listaTimelineDAO::  " + e);
					e.printStackTrace();
					return null;
				}
				// FIM - Recupera quantidade de LIKES em cada post
				
				// INICIO - Recupera quantidade de COMENTARIOS em cada post
				try {
					callstmt = connection.prepareCall("{ CALL ? := COUNT_COMENTARIOS(?)}");
					
					callstmt.registerOutParameter(1, OracleTypes.INTEGER);
					
					callstmt.setInt(2, timeline.getIdTimeline());
					
					callstmt.execute();
				
					timeline.setQtddComentarios(callstmt.getInt(1));
					
				} catch (SQLException e) {
					SessaoSistema.setCodigodMensagem(106);
					SessaoSistema.setDescMensagem("Erro ao executar Function COUNT_COMENTARIOS!");
					System.out.println("LOG::TimelineAcaoDAO::listaTimelineDAO " + SessaoSistema.getDescMensagem());
					System.out.println("LOG::TimelineAcaoDAO::listaTimelineDAO::  " + e);
					e.printStackTrace();
					return null;
				}
				// FIM - Recupera quantidade de likes em cada post			
				
				
				
				timelineList.add(timeline);

			}
			if (callstmt != null)
				callstmt.close();

		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(106);
			SessaoSistema.setDescMensagem("TimelineDAO::listaTimelineDAO::Erro ao executar Store Procedure!");
			System.out.println("TimelineDAO::listaTimelineDAO:: " + SessaoSistema.getDescMensagem());
			System.out.println("TimelineDAO::listaTimelineDAO::ERRO::  " + e);
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
		
		ResultSet resultSetLike = null;
		
		CallableStatement callstmt = null;

		try {
			connection = ConnectionFactory.getConnection();

			if (connection == null) {
				SessaoSistema.setCodigodMensagem(101);
				SessaoSistema.setDescMensagem("Erro ao abrir o Banco de dados");
				System.out.println("TimelineDAO::listaTimelineUsuarioDAO:: " + SessaoSistema.getDescMensagem());
				return null;
			}

			query = "SELECT * FROM TIMELINE " 
					+ "WHERE USUARIO_IDUSUARIO = ? " 
					+ "ORDER BY DATACADASTRO";

			// CAMPOS:
			// IDTIMELINE - Integer
			// USUARIO_IDUSUARIO - Integer
			// DATACADASTRO - Date
			// DESCRICAO - VarChar

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

					
					
					// INICIO - Validar se o usurio logado deu like no timeline
					try {
						query = "SELECT * FROM TIMELINE_ACAO WHERE " + "USUARIO_IDUSUARIO = ? AND " // 1
								+ "TIMELINE_IDTIMELINE = ? AND " // 2
								+ "CURTIR = 'S'";

						preparedStatement = connection.prepareStatement(query);

						preparedStatement.setInt(1, SessaoSistema.getIdUsuarioLogado());
						preparedStatement.setInt(2, timeline.getIdTimeline());

						resultSetLike = preparedStatement.executeQuery();

						if (resultSetLike.next()) {
							timeline.setLike("S");
						} else {
							timeline.setLike(null);
						}
					} catch (SQLException e) {
						SessaoSistema.setCodigodMensagem(106);
						SessaoSistema.setDescMensagem("Erro ao executar select!");
						System.out.println("TimelineDAO::listaTimelineUsuarioDAO:: " + SessaoSistema.getDescMensagem());
						System.out.println("TimelineDAO::listaTimelineUsuarioDAO::ERRO::  " + e);
						e.printStackTrace();
						return null;
					}
					// FIM - Validar se o usurio logado deu like no timeline listado
					
					
					// INICIO - Recupera quantidade de likes em cada post
					try {
						callstmt = connection.prepareCall("{ CALL ? := COUNT_LIKES(?)}");
						
						callstmt.registerOutParameter(1, OracleTypes.INTEGER);
						
						callstmt.setInt(2, timeline.getIdTimeline());
						
						callstmt.execute();
					
						timeline.setQtddLike(callstmt.getInt(1));
						
					} catch (SQLException e) {
						SessaoSistema.setCodigodMensagem(106);
						SessaoSistema.setDescMensagem("Erro ao executar Function COUNT_LIKES!");
						System.out.println("LOG::TimelineAcaoDAO::listaTimelineUsuarioDAO " + SessaoSistema.getDescMensagem());
						System.out.println("LOG::TimelineAcaoDAO::listaTimelineUsuarioDAO::  " + e);
						e.printStackTrace();
						return null;
					}
					// FIM - Recupera quantidade de LIKES em cada post
					
					// INICIO - Recupera quantidade de COMENTARIOS em cada post
					try {
						callstmt = connection.prepareCall("{ CALL ? := COUNT_COMENTARIOS(?)}");
						
						callstmt.registerOutParameter(1, OracleTypes.INTEGER);
						
						callstmt.setInt(2, timeline.getIdTimeline());
						
						callstmt.execute();
					
						timeline.setQtddComentarios(callstmt.getInt(1));
						
					} catch (SQLException e) {
						SessaoSistema.setCodigodMensagem(106);
						SessaoSistema.setDescMensagem("Erro ao executar Function COUNT_COMENTARIOS!");
						System.out.println("LOG::TimelineAcaoDAO::listaTimelineUsuarioDAO " + SessaoSistema.getDescMensagem());
						System.out.println("LOG::TimelineAcaoDAO::listaTimelineUsuarioDAO::  " + e);
						e.printStackTrace();
						return null;
					}
					// FIM - Recupera quantidade de likes em cada post		
					
					timelineList.add(timeline);
				}
			} else {
				SessaoSistema.setCodigodMensagem(100);
				SessaoSistema.setDescMensagem("Select não retornou dados");
				System.out.println("TimelineDAO::listaTimelineUsuarioDAO:: " + SessaoSistema.getDescMensagem());
				timelineList = null;
			}

		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(103);
			SessaoSistema.setDescMensagem("Erro ao consultar os dados!");
			System.out.println("TimelineDAO::listaTimelineUsuarioDAO:: " + SessaoSistema.getDescMensagem());
			System.out.println("TimelineDAO::listaTimelineUsuarioDAO::ERRO::  " + e);
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

			query = "INSERT INTO TIMELINE " + "(IDTIMELINE, " + "USUARIO_IDUSUARIO, " + "DATACADASTRO, " + "DESCRICAO) "
					+ "VALUES (" + "IDTIMELINE_SEQUENCE.NEXTVAL, " + "?, " + "SYSDATE, " + "?)";

			// CAMPOS:
			// IDTIMELINE - Integer
			// USUARIO_IDUSUARIO - Integer
			// DATACADASTRO - Date
			// DESCRICAO - VarChar

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
				SessaoSistema.setDescMensagem("Dados excluídos com sucesso!");
				return true;

			} else {
				SessaoSistema.setCodigodMensagem(102);
				SessaoSistema.setDescMensagem("Erro ao excluir os dados! Timeline não encontrado");
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
