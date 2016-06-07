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
import opet.mygarage.vo.TimelineAcao;

/**
 * Classe DAO do TimelineAcaoDAO
 * 
 * @author Juliano Zapelini Batista / Tiago Mazzer
 * 
 * @since 13/05/2016
 * 
 * @version 1.0
 * 
 */
public class TimelineAcaoDAO implements ITimelineAcaoDAO {
	/*
	 * Variáveis de instância
	 */
	private Connection connection;

	/*
	 * Função construtora
	 */
	public TimelineAcaoDAO() {
	}

	/*
	 * Operações da classe
	 */

	/**
	 * Acessa a base Timeline_Acao para listar os Comentarios/Curtir
	 * 
	 * @see opet.mygarage.model.persistencia.ITimelineAcaoDAO#listaTimelineAcaoDAO()
	 */
	@Override
	public List<TimelineAcao> listaTimelineAcaoDAO(Integer idTimeline) {

		List<TimelineAcao> timelineAcaoList = null;

		TimelineAcao timelineAcao = null;

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

			query = "SELECT  T.IDTIMELINE_ACAO, " + " T.USUARIO_IDUSUARIO, " + " T.TIMELINE_IDTIMELINE, "
					+ " T.DATAACAO, " + " T.CURTIR, " + " T.COMENTARIO, " + " U.NOME " + " FROM TIMELINE_ACAO T "
					+ " INNER JOIN USUARIO U ON T.USUARIO_IDUSUARIO = U.IDUSUARIO "
					+ " WHERE T.TIMELINE_IDTIMELINE = ? " + " ORDER BY T.DATAACAO DESC ";

			// CAMPOS:
			// IDTIMELINE_ACAO NUMBER(38,0)
			// USUARIO_IDUSUARIO NUMBER(38,0)
			// TIMELINE_IDTIMELINE NUMBER(38,0)
			// DATAACAO DATE
			// CURTIR VARCHAR2(1 BYTE)
			// COMENTARIO VARCHAR2(500 BYTE)

			preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			preparedStatement.setInt(1, idTimeline);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.last()) {

				resultSet.beforeFirst();
				timelineAcaoList = new ArrayList<>();

				while (resultSet.next()) {

					System.out.println("LOG::TimelineAcaoDAO::listaTimelineAcaoDAO:: Select com sucesso");

					timelineAcao = new TimelineAcao();

					timelineAcao.setIdTimelineAcao(new Integer(resultSet.getInt("IDTIMELINE_ACAO")));
					timelineAcao.setIdUsuario(new Integer(resultSet.getInt("USUARIO_IDUSUARIO")));
					timelineAcao.setDataAcao(new java.util.Date(resultSet.getDate("DATAACAO").getTime()));

					timelineAcao.setNomeUsuario(resultSet.getString("NOME"));

					if (resultSet.getString("CURTIR") != null) {
						timelineAcao.setLike(resultSet.getString("CURTIR"));
					} else {
						timelineAcao.setLike(null);
					}

					if (resultSet.getString("COMENTARIO") != null) {
						timelineAcao.setComentario(resultSet.getString("COMENTARIO"));
					} else {
						timelineAcao.setComentario(null);
					}

					timelineAcaoList.add(timelineAcao);
				}
			} else {
				SessaoSistema.setCodigodMensagem(100);
				SessaoSistema.setDescMensagem("Select não retornou dados");
				System.out.println("TimelineAcaoDAO::listaTimelineAcaoDAO " + SessaoSistema.getDescMensagem());
				timelineAcaoList = null;
			}

		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(103);
			SessaoSistema.setDescMensagem("Erro ao consultar os dados!");
			System.out.println("LOG::TimelineAcaoDAO::listaTimelineAcaoDAO " + SessaoSistema.getDescMensagem());
			System.out.println("LOG::TimelineAcaoDAO::listaTimelineAcaoDAO::  " + e);
			e.printStackTrace();
			timelineAcaoList = null;
		} finally {
			ConnectionFactory.closeConnection();
		}

		return timelineAcaoList;
	}

	/**
	 * Acessa a base Timeline_Acao para cadastrar comentario/curtir
	 * 
	 * @see opet.mygarage.model.persistencia.ITimelineAcaoDAO#cadastraComentarioPostDAO(opet.mygarage.vo.TimelineAcao)
	 */
	@Override
	public Boolean cadastraTimelineAcaoDAO(TimelineAcao timelineAcao) {

		PreparedStatement preparedStatement = null;

		String query = null;

		Integer count = null;

		try {
			connection = ConnectionFactory.getConnection();

			if (connection == null) {
				SessaoSistema.setCodigodMensagem(101);
				SessaoSistema.setDescMensagem("Erro ao abrir o Banco de dados");
				System.out.println("LOG::TimelineAcaoDAO::cadastraTimelineAcaoDAO " + SessaoSistema.getDescMensagem());
				return false;
			}

			query = "Insert into TIMELINE_ACAO " + "(IDTIMELINE_ACAO, " + "USUARIO_IDUSUARIO, " // 1
					+ "TIMELINE_IDTIMELINE, " // 2
					+ "DATAACAO, " + "CURTIR, " // 3
					+ "COMENTARIO" // 4
					+ ") values (" + "IDTIMELINE_ACAO_SEQUENCE.NEXTVAL, " + "?, " + "?, " + "SYSDATE, " + "?, " + "?)";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, SessaoSistema.getIdUsuarioLogado());
			preparedStatement.setInt(2, timelineAcao.getIdTimeline());

			if (timelineAcao.getLike() != null) {

				preparedStatement.setString(3, timelineAcao.getLike());

			} else {
				preparedStatement.setNull(3, Types.NULL);
			}

			if (timelineAcao.getComentario() != null) {

				preparedStatement.setString(4, timelineAcao.getComentario());

			} else {
				preparedStatement.setNull(4, Types.NULL);
			}

			count = new Integer(preparedStatement.executeUpdate());

			if (count != null && count.equals(1)) {
				System.out.println("TimelineAcaoDAO::cadastraTimelineAcaoDAO::Insert com sucesso");
				connection.commit();
				SessaoSistema.setCodigodMensagem(0);
				return true;
			} else {
				SessaoSistema.setCodigodMensagem(105);
				SessaoSistema.setDescMensagem("Erro ao inserir os dados!");
				System.out.println("LOG::TimelineAcaoDAO::cadastraTimelineAcaoDAO " + SessaoSistema.getDescMensagem());
				return false;
			}

		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(105);
			SessaoSistema.setDescMensagem("Erro ao inserir os dados!");
			System.out.println("LOG::TimelineAcaoDAO::cadastraTimelineAcaoDAO " + SessaoSistema.getDescMensagem());
			System.out.println("LOG::TimelineAcaoDAO::ERRO::cadastraTimelineAcaoDAO  " + e);
			e.printStackTrace();
			return false;
		} finally {
			ConnectionFactory.closeConnection();
		}

	}

	/**
	 * Acessa a base Timeline_Acao para excluir o Like do Usuario Logado
	 * 
	 * @see opet.mygarage.model.persistencia.ITimelineAcaoDAO#excluiLikeTimelineAcaoDAO()
	 */
	@Override
	public Boolean excluiLikeTimelineAcaoDAO(TimelineAcao timelineAcao) {

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

			query = "DELETE FROM TIMELINE_ACAO WHERE " + "USUARIO_IDUSUARIO = ? AND " // 1
					+ "TIMELINE_IDTIMELINE = ? AND " // 2
					+ "CURTIR = 'S'";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, SessaoSistema.getIdUsuarioLogado());
			preparedStatement.setInt(2, timelineAcao.getIdTimeline());

			count = preparedStatement.executeUpdate();

			if (count != null && count.equals(1)) {
				connection.commit();
				SessaoSistema.setCodigodMensagem(0);
				SessaoSistema.setDescMensagem("Dados excluídos com sucesso!");
				return true;
			} else {
				SessaoSistema.setCodigodMensagem(102);
				SessaoSistema.setDescMensagem("Erro ao excluir os dados! Timeline_Acao não encontrado");
				System.out.println("TimelineAcaoDAO::excluiLikeTimelineAcaoDAO " + SessaoSistema.getDescMensagem());
				return false;
			}
		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(102);
			SessaoSistema.setDescMensagem("Erro ao excluir os dados!");
			System.out.println("TimelineAcaoDAO::excluiTimelineAcaoDAO:: " + SessaoSistema.getDescMensagem());
			System.out.println("TimelineAcaoDAO::excluiTimelineAcaoDAO::ERRO::  " + e);
			e.printStackTrace();
			return false;
		} finally {
			ConnectionFactory.closeConnection();
		}

	}

	/**
	 * Acessa a base Timeline_Acao para excluir o Comentario
	 * 
	 * @see opet.mygarage.model.persistencia.ITimelineAcaoDAO#excluiTimelineAcaoDAO()
	 */
	@Override
	public Boolean excluiTimelineAcaoDAO(Integer idTimelineAcao) {

		PreparedStatement preparedStatement = null;

		String query = null;
		
		Integer count = null;

		try {
			connection = ConnectionFactory.getConnection();

			if (connection == null) {
				SessaoSistema.setCodigodMensagem(101);
				SessaoSistema.setDescMensagem("Erro ao abrir o Banco de dados");
				System.out.println("TimelineAcaoDAO::excluiTimelineAcaoDAO:: " + SessaoSistema.getDescMensagem());
				return false;
			}

			query = "DELETE FROM TIMELINE_ACAO "
					+ "WHERE IDTIMELINE_ACAO = ?";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, idTimelineAcao);

			count = new Integer(preparedStatement.executeUpdate());

			if (count.equals(1)) {
				connection.commit();
				SessaoSistema.setCodigodMensagem(0);
				SessaoSistema.setDescMensagem("Dados excluídos com sucesso!");
				return true;
			} else {
				SessaoSistema.setCodigodMensagem(102);
				SessaoSistema.setDescMensagem("Erro ao excluir os dados! Timeline_Acao não encontrado");
				System.out.println("TimelineAcaoDAO::excluiTimelineAcaoDAO " + SessaoSistema.getDescMensagem());
				return false;
			}
				

		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(102);
			SessaoSistema.setDescMensagem("Erro ao excluir os dados!");
			System.out.println("TimelineAcaoDAO::excluiTimelineAcaoDAO:: " + SessaoSistema.getDescMensagem());
			System.out.println("TimelineAcaoDAO::excluiTimelineAcaoDAO::ERRO::  " + e);
			e.printStackTrace();
			return false;
		} finally {
			ConnectionFactory.closeConnection();
		}

	}

	@Override
	public Boolean excluiTimelineAcaoByTimelineDAO(Integer idTimeline) {
		
		PreparedStatement preparedStatement = null;

		String query = null;
		
		Integer count = null;

		try {
			connection = ConnectionFactory.getConnection();

			if (connection == null) {
				SessaoSistema.setCodigodMensagem(101);
				SessaoSistema.setDescMensagem("Erro ao abrir o Banco de dados");
				System.out.println("TimelineAcaoDAO::excluiTimelineAcaoByTimelineDAO:: " + SessaoSistema.getDescMensagem());
				return false;
			}

			query = "DELETE FROM TIMELINE_ACAO "
					+ "WHERE TIMELINE_IDTIMELINE = ?";

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
				SessaoSistema.setDescMensagem("Erro ao excluir os dados! Timeline_Acao não encontrado");
				System.out.println("TimelineAcaoDAO::excluiTimelineAcaoByTimelineDAO " + SessaoSistema.getDescMensagem());
				return false;
			}
				

		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(102);
			SessaoSistema.setDescMensagem("Erro ao excluir os dados!");
			System.out.println("TimelineAcaoDAO::excluiTimelineAcaoByTimelineDAO:: " + SessaoSistema.getDescMensagem());
			System.out.println("TimelineAcaoDAO::excluiTimelineAcaoByTimelineDAO::ERRO::  " + e);
			e.printStackTrace();
			return false;
		} finally {
			ConnectionFactory.closeConnection();
		}
	}

}
