/**
 * 
 */
package opet.mygarage.model.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
	public Boolean desativaRelacionamentoDAO(Integer codigoAmigo, Integer idUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Lista relacionamentos da base Relacionamento
	 * 
	 * @see opet.mygarage.model.persistencia.IRelacionamentoDAO#listaAmigosRelacionamentoDAO(java.lang.Integer)
	 */
	@Override
	public List<Relacionamento> listaAmigosRelacionamentoDAO(Integer codigoAmigo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * Acessa a base Relacionamento para consultar Relacionamento (AMIGO)
	 * 
	 * @see opet.mygarage.model.persistencia.IRelacionamentoDAO#listaAmigosRelacionamentoDAO(java.lang.Integer)
	 */
	@Override
	public Boolean consultaRelacionamentoDAO(Integer codigoAmigo, Integer idUsuario){
	
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

			query = "SELECT * FROM RELACIONAMENTO WHERE "
					+ "CODIGO_AMIGO_IDCODIGO_AMIGO = ? AND "
					+ "USUARIO_IDUSUARIO = ?";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, codigoAmigo);
			preparedStatement.setInt(2, idUsuario);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return true;
				
			} else {
				SessaoSistema.setCodigodMensagem(100);
				SessaoSistema.setDescMensagem("Select não retornou dados");
				System.out.println("LOG::RelacionamentoDAO:: " + SessaoSistema.getDescMensagem());
				return false;
			}

		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(103);
			SessaoSistema.setDescMensagem("Erro ao consultar os dados!");
			System.out.println("LOG::RelacionamentoDAO:: " + SessaoSistema.getDescMensagem());
			System.out.println("LOG::RelacionamentoDAO::ERRO::  " + e);
			e.printStackTrace();
			return false;
		}		
	}

}
