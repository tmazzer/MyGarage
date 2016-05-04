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
import opet.mygarage.vo.Carro;

/**
 * Classe CarroDAO persistencia PersistenciaCarro
 * 
 * @author Juliano Zapelini Batista / Tiago Mazzer
 * 
 * @since 19/04/2016
 * 
 * @version 1.0
 * 
 */
public class CarroDAO implements ICarroDAO {

	/*
	 * Variáveis de instância
	 */
	private Connection connection;

	/*
	 * Função construtora da classe
	 */

	public CarroDAO() {

		System.out.println("LOG::DAO::CONSTRUTOR");

	}
	/*
	 * Operações da classe
	 */

	/**
	 * Cadastra um novo Carro na tabela Carro
	 */
	@Override
	public Carro cadastraCarroDAO(Carro carro) {
		PreparedStatement preparedStatement = null;

		String query = null;

		Integer count = null;
		try {
			connection = ConnectionFactory.getConnection();

			// Processamento dos dados
			if (connection == null) {
				SessaoSistema.setCodigodMensagem(101);
				SessaoSistema.setDescMensagem("Erro ao abrir o Banco de dados");
				System.out.println("LOG::DAO:: " + SessaoSistema.getDescMensagem());
				return null;
			} 

			query = "INSERT INTO CARRO "
					+ "(CARRO.idCarro, CARRO.TPCOMBUST_IDTPCOMBUST, CARRO.usuario_idusuario, CARRO.apelidocarro, CARRO.marca, CARRO.modelo, CARRO.anofabricacao, CARRO.anomodelo, CARRO.cor, CARRO.placa, CARRO.kilometragem) "
					+ "VALUES (idCarro_SEQUENCE.NEXTVAL, 1, 1, "
					+ "?, ?, ?, "
					+ "?, ?, "
					+ "?, ?, "
					+ "?)";


			preparedStatement = connection.prepareStatement(query);

			// ESTES CAMPOS SÃO OBRIGATORIOS. DEVEM TER SIDO VALIDADOS ANTES
			preparedStatement.setString(1, carro.getApelido());
			preparedStatement.setString(2, carro.getMarca());
			preparedStatement.setString(3, carro.getModelo());
			
			preparedStatement.setString(4, carro.getAnoFabricacao());
			preparedStatement.setString(5, carro.getAnoModelo());
			
			preparedStatement.setString(6, carro.getCor());
			preparedStatement.setString(7, carro.getPlaca());					
					
			preparedStatement.setInt(8, Integer.parseInt(carro.getQuilometragem()));

			// Executa INSERT

			count = new Integer(preparedStatement.executeUpdate());

			if (count.equals(1)) {
				System.out.println("LOG::DAO:Insert com sucesso");
				connection.commit();
				SessaoSistema.setCodigodMensagem(0);
			} else {
				SessaoSistema.setCodigodMensagem(105);
				SessaoSistema.setDescMensagem("Erro ao inserir os dados!");
				System.out.println("LOG::DAO:: " + SessaoSistema.getDescMensagem());
			}

		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(105);
			SessaoSistema.setDescMensagem("Erro ao inserir os dados!");
			System.out.println("LOG::DAO:: " + SessaoSistema.getDescMensagem());
			System.out.println("LOG::DAO::ERRO::  " + e);
			carro = null;

			e.printStackTrace();

		} finally {
			ConnectionFactory.closeConnection();
		}
		return carro;
	}
	/**
	 * Consulta Carros existentes na tabela Carro
	 */
	@Override
	public Carro consultaCarroDAO(Carro carro) {
		
		PreparedStatement preparedStatement = null;

		String query = null;

		ResultSet resultSet = null;
		
		try {
			connection = ConnectionFactory.getConnection();

			if (connection == null) {
				SessaoSistema.setCodigodMensagem(101);
				SessaoSistema.setDescMensagem("Erro ao abrir o Banco de dados");
				System.out.println("LOG::DAO:: " + SessaoSistema.getDescMensagem());
				return null;
			} 

			query = "SELECT * FROM CARRO WHERE IDCARRO = ?";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, carro.getIdCarro());

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				carro.setIdCarro(resultSet.getInt("IDCARRO"));
				carro.setApelido(resultSet.getString("APELIDOCARRO"));
				carro.setMarca(resultSet.getString("MARCA"));
				carro.setModelo(resultSet.getString("MODELO"));
				carro.setAnoFabricacao(resultSet.getString("ANOFABRICACAO"));
				carro.setAnoModelo(resultSet.getString("ANOMODELO"));
				carro.setCor(resultSet.getString("COR"));
				carro.setPlaca(resultSet.getString("PLACA"));
				carro.setQuilometragem(resultSet.getString("KILOMETRAGEM"));

			} else {
				SessaoSistema.setCodigodMensagem(100);
				SessaoSistema.setDescMensagem("Select não retornou dados");
				System.out.println("LOG::DAO:: " + SessaoSistema.getDescMensagem());
				return null;
			}

		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(103);
			SessaoSistema.setDescMensagem("Erro ao consultar os dados!");
			System.out.println("LOG::DAO:: " + SessaoSistema.getDescMensagem());
			System.out.println("LOG::DAO::ERRO::  " + e);
			carro = null;
			e.printStackTrace();
		}

		return carro;
	}
	/**
	 * Exclui Carro existente na tabela Carro Retorna TRUE para Excluido com
	 * sucesso Retorna FALSE se ERRO	ao Excluir
	 */
	@Override
	public Boolean excluiCarroDAO(Carro carro) {
		PreparedStatement preparedStatement = null;

		String query = null;

		Integer count = null;
		
		try {
			connection = ConnectionFactory.getConnection();
			// Processamento dos dados

			if (connection == null) {
				SessaoSistema.setCodigodMensagem(101);
				SessaoSistema.setDescMensagem("Erro ao abrir o Banco de dados");
				System.out.println("LOG::DAO:: " + SessaoSistema.getDescMensagem());
				return null;
			} 

			query = "DELETE FROM CARRO WHERE IDCARRO = ?";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, carro.getIdCarro());		

			count = new Integer(preparedStatement.executeUpdate());

			if (count.equals(1)) {
				connection.commit();

				SessaoSistema.setCodigodMensagem(0);
				SessaoSistema.setDescMensagem("Dados excluídos com sucesso!");
				return true;

			} else {
				SessaoSistema.setCodigodMensagem(102);
				SessaoSistema.setDescMensagem("Erro ao excluir os dados! Usuario não encontrado");
				System.out.println("LOG::DAO:: " + SessaoSistema.getDescMensagem());
				return false;
			}

		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(102);
			SessaoSistema.setDescMensagem("Erro ao excluir os dados!");
			System.out.println("LOG::DAO:: " + SessaoSistema.getDescMensagem());
			System.out.println("LOG::DAO::ERRO::  " + e);
			e.printStackTrace();
			return false;
		}

	}
	/**
	 * Altera Carro existente na tabela Carro
	 */
	@Override
	public Carro alteraCarroDAO(Carro carro) {
		PreparedStatement preparedStatement = null;

		String query = null;

		Integer count = null;

		try {

			connection = ConnectionFactory.getConnection();

			if (connection == null) {
				SessaoSistema.setCodigodMensagem(101);
				SessaoSistema.setDescMensagem("Erro ao abrir o Banco de dados");
				System.out.println("LOG::DAO:: " + SessaoSistema.getDescMensagem());
				return null;
			} 
			
			query = "UPDATE CARRO SET " + "CARRO.apelido = ?, " + "CARRO.marca = ?, " + "CARRO.modelo = ?, "
					+ "CARRO.anofabricacao = ?, " + "CARRO.anoModelo = ? " + "CARRO.cor = ? " 
					+ "CARRO.placa = ? " + "CARRO.quilometragem = ? " + "WHERE CARRO.idCarro = ?";

			preparedStatement = connection.prepareStatement(query);

			// ESTES CAMPOS SÃO OBRIGATORIOS. DEVEM TER SIDO VALIDADOS ANTES
			preparedStatement.setString(1, carro.getApelido());
			preparedStatement.setString(2, carro.getMarca());
			preparedStatement.setString(3, carro.getModelo());
			preparedStatement.setString(4, carro.getAnoFabricacao());
			preparedStatement.setString(5, carro.getAnoModelo());
			preparedStatement.setString(6, carro.getCor());
			preparedStatement.setString(7, carro.getPlaca());
			preparedStatement.setString(8, carro.getQuilometragem());

			// Executa Update

			count = new Integer(preparedStatement.executeUpdate());

			if (count != null && count.equals(1)) {
				connection.commit();
				SessaoSistema.setCodigodMensagem(0);
			} else {
				SessaoSistema.setCodigodMensagem(104);
				SessaoSistema.setDescMensagem("Erro ao Atualizar os dados!");
				System.out.println("LOG::DAO:: " + SessaoSistema.getDescMensagem());
			}

		} catch (SQLException e) {			
			SessaoSistema.setCodigodMensagem(104);
			SessaoSistema.setDescMensagem("Erro ao Atualizar os dados!");
			System.out.println("LOG::DAO:: " + SessaoSistema.getDescMensagem());
			System.out.println("LOG::DAO::ERRO::  " + e);
			carro = null;

			e.printStackTrace();

		} finally {

			ConnectionFactory.closeConnection();
		}

		return carro;

	}

}
