/**
 * 
 */
package opet.mygarage.model.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import opet.mygarage.vo.Usuario;
import opet.mygarage.util.ConnectionFactory;
import opet.mygarage.util.MensagemRetorno;

/**
 * Classe UsuarioDAO persistencia PersistenciaUsuario
 * 
 * @author Juliano Zapelini Batista / Tiago Mazzer
 * 
 * @since 19/04/2016
 * 
 * @version 1.0
 * 
 */
public class UsuarioDAO implements IUsuarioDAO {

	/*
	 * Variáveis de instância
	 */
	private Connection connection;

	/*
	 * Função construtora da classe
	 */

	public UsuarioDAO() {

		System.out.println("LOG::DAO::CONSTRUTOR");

	}

	/*
	 * Operações da classe
	 */

	/**
	 * Cadastra um novo Usuario na tabela Usuario
	 * 
	 * @see cadastro.pessoa.persistencia.IPessoaDAO#salvar(cadastro.pessoa.vo.Pessoa)
	 */
	@Override
	public Usuario cadastraUsuarioDAO(Usuario usuario) {

		PreparedStatement preparedStatement = null;

		String query = null;

		Integer count = null;
		try {
			connection = ConnectionFactory.getConnection();

			// Processamento dos dados
			if (connection == null) {
				MensagemRetorno.setCodigodMensagem(101);
				MensagemRetorno.setDescMensagem("Erro ao abrir o Banco de dados");
				System.out.println("LOG::DAO:: " + MensagemRetorno.getDescMensagem());
				return null;
			} 

			query = "INSERT INTO USUARIO "
					+ "(USUARIO.idUsuario, USUARIO.nome, USUARIO.sobrenome, USUARIO.telefone, USUARIO.email, USUARIO.senha) "
					+ "VALUES (idUsuario_SEQUENCE.NEXTVAL, ?, ?, ?, ?, ?)";


			preparedStatement = connection.prepareStatement(query);

			// ESTES CAMPOS SÃO OBRIGATORIOS. DEVEM TER SIDO VALIDADOS ANTES
			preparedStatement.setString(1, usuario.getNome());
			preparedStatement.setString(4, usuario.getEmail());
			preparedStatement.setString(5, usuario.getSenha());

			// campos NAO obrigarorios. Se vazios, devem ser tratados como NULL
			if (usuario.getSobrenome() != null) {

				preparedStatement.setString(2, usuario.getSobrenome());

			} else {
				preparedStatement.setNull(2, Types.NULL);
			}

			if (usuario.getTelefone() != null) {
				preparedStatement.setString(3, usuario.getTelefone());

			} else {
				preparedStatement.setNull(3, Types.NULL);
			}

			// Executa INSERT

			count = new Integer(preparedStatement.executeUpdate());

			if (count != null && count.equals(1)) {
				System.out.println("LOG::DAO:Insert com sucesso");
				connection.commit();
				MensagemRetorno.setCodigodMensagem(0);
			} else {
				MensagemRetorno.setCodigodMensagem(105);
				MensagemRetorno.setDescMensagem("Erro ao inserir os dados!");
				System.out.println("LOG::DAO:: " + MensagemRetorno.getDescMensagem());
			}

		} catch (SQLException e) {
			MensagemRetorno.setCodigodMensagem(105);
			MensagemRetorno.setDescMensagem("Erro ao inserir os dados!");
			System.out.println("LOG::DAO:: " + MensagemRetorno.getDescMensagem());
			System.out.println("LOG::DAO::ERRO::  " + e);
			usuario = null;

			e.printStackTrace();

		} finally {
			ConnectionFactory.closeConnection();
		}
		return usuario;
	}

	/**
	 * Consulta Usuario existente na tabela Usuario
	 * 
	 * @see cadastro.pessoa.persistencia.IPessoaDAO#salvar(cadastro.pessoa.vo.Pessoa)
	 */
	@Override
	public Usuario consultaUsuarioDAO(Usuario usuario) {

		PreparedStatement preparedStatement = null;

		String query = null;

		ResultSet resultSet = null;
		try {
			connection = ConnectionFactory.getConnection();

			if (connection == null) {
				MensagemRetorno.setCodigodMensagem(101);
				MensagemRetorno.setDescMensagem("Erro ao abrir o Banco de dados");
				System.out.println("LOG::DAO:: " + MensagemRetorno.getDescMensagem());
				return null;
			} 

			query = "SELECT * FROM USUARIO WHERE IDUSUARIO = ?";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, usuario.getIdUsuario());

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				usuario.setNome(resultSet.getString("NOME"));
				usuario.setEmail(resultSet.getString("EMAIL"));
				usuario.setSenha(resultSet.getString("SENHA"));

				if (resultSet.getString("SOBRENOME") != null) {

					usuario.setSobrenome(resultSet.getString("SOBRENOME"));

				} else {

					usuario.setSobrenome(null);
				}

				if (resultSet.getString("TELEFONE") != null) {

					usuario.setTelefone(resultSet.getString("TELEFONE"));

				} else {

					usuario.setTelefone(null);
				}
			} else {
				MensagemRetorno.setCodigodMensagem(100);
				MensagemRetorno.setDescMensagem("Select não retornou dados");
				System.out.println("LOG::DAO:: " + MensagemRetorno.getDescMensagem());
				return null;
			}

		} catch (SQLException e) {
			MensagemRetorno.setCodigodMensagem(100);
			MensagemRetorno.setDescMensagem("Select não retornou dados");
			System.out.println("LOG::DAO:: " + MensagemRetorno.getDescMensagem());
			System.out.println("LOG::DAO::ERRO::  " + e);
			usuario = null;
			e.printStackTrace();
		}

		return usuario;
	}

	/**
	 * Exclui Usuario existente na tabela Usuario Retorna TRUE para Excluido com
	 * sucesso Retorna FALSE se ERRO ao Excluir
	 * 
	 * @see cadastro.pessoa.persistencia.IPessoaDAO#salvar(cadastro.pessoa.vo.Pessoa)
	 */
	@Override
	public Boolean excluiUsuarioDAO(Usuario usuario) {

		PreparedStatement preparedStatement = null;

		String query = null;

		Integer count = null;
		try {
			connection = ConnectionFactory.getConnection();
			// Processamento dos dados

			if (connection == null) {
				MensagemRetorno.setCodigodMensagem(101);
				MensagemRetorno.setDescMensagem("Erro ao abrir o Banco de dados");
				System.out.println("LOG::DAO:: " + MensagemRetorno.getDescMensagem());
				return null;
			} 

			query = "DELETE FROM USUARIO WHERE IDUSUARIO = ?";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, usuario.getIdUsuario());

			count = new Integer(preparedStatement.executeUpdate());

			if (count != null && count.equals(1)) {
				connection.commit();

				MensagemRetorno.setCodigodMensagem(0);
				MensagemRetorno.setDescMensagem("Dados excluídos com sucesso!");
				return true;

			} else {
				MensagemRetorno.setCodigodMensagem(102);
				MensagemRetorno.setDescMensagem("Erro ao excluir os dados!");
				System.out.println("LOG::DAO:: " + MensagemRetorno.getDescMensagem());
				return false;
			}

		} catch (SQLException e) {
			MensagemRetorno.setCodigodMensagem(102);
			MensagemRetorno.setDescMensagem("Erro ao excluir os dados!");
			System.out.println("LOG::DAO:: " + MensagemRetorno.getDescMensagem());
			System.out.println("LOG::DAO::ERRO::  " + e);
			e.printStackTrace();
			return false;

		}
	}

	/**
	 * Altera Usuario existente na tabela Usuario
	 * 
	 * @see cadastro.pessoa.persistencia.IPessoaDAO#salvar(cadastro.pessoa.vo.Pessoa)
	 */
	@Override
	public Usuario alteraUsuarioDAO(Usuario usuario) {

		PreparedStatement preparedStatement = null;

		String query = null;

		Integer count = null;

		try {

			connection = ConnectionFactory.getConnection();

			if (connection == null) {
				MensagemRetorno.setCodigodMensagem(101);
				MensagemRetorno.setDescMensagem("Erro ao abrir o Banco de dados");
				System.out.println("LOG::DAO:: " + MensagemRetorno.getDescMensagem());
				return null;
			} 
			
			query = "UPDATE USUARIO SET " + "USUARIO.nome = ?, " + "USUARIO.sobrenome = ?, " + "USUARIO.telefone = ?, "
					+ "USUARIO.email = ?, " + "USUARIO.senha = ? " + "WHERE USUARIO.idUsuario = ?";

			preparedStatement = connection.prepareStatement(query);

			// ESTES CAMPOS SÃO OBRIGATORIOS. DEVEM TER SIDO VALIDADOS ANTES
			preparedStatement.setString(1, usuario.getNome());
			preparedStatement.setString(4, usuario.getEmail());
			preparedStatement.setString(5, usuario.getSenha());
			preparedStatement.setInt(6, usuario.getIdUsuario());

			// campos NAO obrigarorios. Se vazios, devem ser tratados como NULL
			if (usuario.getSobrenome() != null) {

				preparedStatement.setString(2, usuario.getSobrenome());

			} else {

				preparedStatement.setNull(2, Types.NULL);
			}

			if (usuario.getTelefone() != null) {

				preparedStatement.setString(3, usuario.getTelefone());

			} else {

				preparedStatement.setNull(3, Types.NULL);
			}

			// Executa Update

			count = new Integer(preparedStatement.executeUpdate());

			if (count != null && count.equals(1)) {
				connection.commit();
				MensagemRetorno.setCodigodMensagem(0);
			} else {
				MensagemRetorno.setCodigodMensagem(104);
				MensagemRetorno.setDescMensagem("Erro ao Atualizar os dados!");
				System.out.println("LOG::DAO:: " + MensagemRetorno.getDescMensagem());
			}

		} catch (SQLException e) {			
			MensagemRetorno.setCodigodMensagem(104);
			MensagemRetorno.setDescMensagem("Erro ao Atualizar os dados!");
			System.out.println("LOG::DAO:: " + MensagemRetorno.getDescMensagem());
			System.out.println("LOG::DAO::ERRO::  " + e);
			usuario = null;

			e.printStackTrace();

		} finally {

			ConnectionFactory.closeConnection();
		}

		return usuario;
	}

	/**
	 * Valida Login válido na tabela Usuario
	 * 
	 * @see cadastro.pessoa.persistencia.IPessoaDAO#salvar(cadastro.pessoa.vo.Pessoa)
	 */
	@Override
	public Usuario validaLoginUsuarioDAO(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

}
