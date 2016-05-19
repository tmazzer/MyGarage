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

import opet.mygarage.vo.Usuario;
import opet.mygarage.util.ConnectionFactory;
import opet.mygarage.util.SessaoSistema;

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

	}

	/*
	 * Operações da classe
	 */

	/**
	 * Cadastra um novo Usuario na tabela Usuario
	 * 
	 * @see opet.mygarage.model.persistencia.IUsuarioDAO#salvar(opet.mygarage.vo.Usuario)
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
				SessaoSistema.setCodigodMensagem(101);
				SessaoSistema.setDescMensagem("Erro ao abrir o Banco de dados");
				System.out.println("LOG::UsuarioDAO:: " + SessaoSistema.getDescMensagem());
				return null;
			} 

			query = "INSERT INTO USUARIO "
					+ "(USUARIO.idUsuario, USUARIO.nome, USUARIO.sobrenome, USUARIO.telefone, USUARIO.foto, USUARIO.email, USUARIO.senha) "
					+ "VALUES (idUsuario_SEQUENCE.NEXTVAL, ?, ?, ?, ?, ?, ?)";


			preparedStatement = connection.prepareStatement(query);

			// ESTES CAMPOS SÃO OBRIGATORIOS. DEVEM TER SIDO VALIDADOS ANTES
			preparedStatement.setString(1, usuario.getNome());
			preparedStatement.setString(5, usuario.getEmail());
			preparedStatement.setString(6, usuario.getSenha());

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
			
			if (usuario.getFoto() != null) {
				preparedStatement.setString(4, usuario.getFoto());

			} else {
				preparedStatement.setNull(4, Types.NULL);
			}

			// Executa INSERT

			count = new Integer(preparedStatement.executeUpdate());

			if (count != null && count.equals(1)) {
				System.out.println("LOG::UsuarioDAO:Insert com sucesso");
				connection.commit();
				SessaoSistema.setCodigodMensagem(0);
				usuario = consultaPorEmailUsuarioDAO(usuario);	
				SessaoSistema.setIdUsuarioLogado(usuario.getIdUsuario());
				SessaoSistema.setNomeUsuarioLogado(usuario.getNome());
			} else {
				SessaoSistema.setCodigodMensagem(105);
				SessaoSistema.setDescMensagem("Erro ao inserir os dados!");
				System.out.println("LOG::UsuarioDAO:: " + SessaoSistema.getDescMensagem());
			}

		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(105);
			SessaoSistema.setDescMensagem("Erro ao inserir os dados!");
			System.out.println("LOG::UsuarioDAO:: " + SessaoSistema.getDescMensagem());
			System.out.println("LOG::UsuarioDAO::ERRO::  " + e);
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
	 * @see opet.mygarage.model.persistencia.IUsuarioDAO#salvar(opet.mygarage.vo.Usuario)
	 */
	@Override
	public Usuario consultaUsuarioDAO(Usuario usuario) {

		PreparedStatement preparedStatement = null;

		String query = null;

		ResultSet resultSet = null;
		try {
			connection = ConnectionFactory.getConnection();

			if (connection == null) {
				SessaoSistema.setCodigodMensagem(101);
				SessaoSistema.setDescMensagem("Erro ao abrir o Banco de dados");
				System.out.println("LOG::UsuarioDAO:: " + SessaoSistema.getDescMensagem());
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
				
				if (resultSet.getString("FOTO") != null) {

					usuario.setFoto(resultSet.getString("FOTO"));

				} else {

					usuario.setFoto(null);
				}
			} else {
				SessaoSistema.setCodigodMensagem(100);
				SessaoSistema.setDescMensagem("Select não retornou dados");
				System.out.println("LOG::UsuarioDAO:: " + SessaoSistema.getDescMensagem());
				return null;
			}

		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(103);
			SessaoSistema.setDescMensagem("Erro ao consultar os dados!");
			System.out.println("LOG::UsuarioDAO:: " + SessaoSistema.getDescMensagem());
			System.out.println("LOG::UsuarioDAO::ERRO::  " + e);
			usuario = null;
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection();
		}

		return usuario;
	}

	/**
	 * Exclui Usuario existente na tabela Usuario Retorna TRUE para Excluido com
	 * sucesso Retorna FALSE se ERRO	ao Excluir
	 * 
	 * @see opet.mygarage.model.persistencia.IUsuarioDAO#salvar(opet.mygarage.vo.Usuario)
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
				SessaoSistema.setCodigodMensagem(101);
				SessaoSistema.setDescMensagem("Erro ao abrir o Banco de dados");
				System.out.println("LOG::UsuarioDAO:: " + SessaoSistema.getDescMensagem());
				return null;
			} 

			query = "DELETE FROM USUARIO WHERE IDUSUARIO = ?";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, usuario.getIdUsuario());		

			count = new Integer(preparedStatement.executeUpdate());

			if (count != null && count.equals(1)) {
				connection.commit();

				SessaoSistema.setCodigodMensagem(0);
				SessaoSistema.setDescMensagem("Dados excluídos com sucesso!");
				return true;

			} else {
				SessaoSistema.setCodigodMensagem(102);
				SessaoSistema.setDescMensagem("Erro ao excluir os dados! Usuario não encontrado");
				System.out.println("LOG::UsuarioDAO:: " + SessaoSistema.getDescMensagem());
				return false;
			}

		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(102);
			SessaoSistema.setDescMensagem("Erro ao excluir os dados!");
			System.out.println("LOG::UsuarioDAO:: " + SessaoSistema.getDescMensagem());
			System.out.println("LOG::UsuarioDAO::ERRO::  " + e);
			e.printStackTrace();
			return false;
		} finally {
			ConnectionFactory.closeConnection();
		}
	}

	/**
	 * Altera Usuario existente na tabela Usuario
	 * 
	 * @see opet.mygarage.model.persistencia.IUsuarioDAO#salvar(opet.mygarage.vo.Usuario)
	 */
	@Override
	public Usuario alteraUsuarioDAO(Usuario usuario) {

		PreparedStatement preparedStatement = null;

		String query = null;

		Integer count = null;

		try {

			connection = ConnectionFactory.getConnection();

			if (connection == null) {
				SessaoSistema.setCodigodMensagem(101);
				SessaoSistema.setDescMensagem("Erro ao abrir o Banco de dados");
				System.out.println("LOG::UsuarioDAO:: " + SessaoSistema.getDescMensagem());
				return null;
			} 
			
			query = "UPDATE USUARIO SET " + "USUARIO.nome = ?, " + "USUARIO.sobrenome = ?, " 
					+ "USUARIO.telefone = ?, USUARIO.foto = ?, "
					+ "USUARIO.email = ?, " + "USUARIO.senha = ? " + "WHERE USUARIO.idUsuario = ?";

			preparedStatement = connection.prepareStatement(query);

			// ESTES CAMPOS SÃO OBRIGATORIOS. DEVEM TER SIDO VALIDADOS ANTES
			preparedStatement.setString(1, usuario.getNome());
			preparedStatement.setString(5, usuario.getEmail());
			preparedStatement.setString(6, usuario.getSenha());
			preparedStatement.setInt(7, usuario.getIdUsuario());

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
			
			if (usuario.getFoto() != null) {

				preparedStatement.setString(4, usuario.getFoto());

			} else {

				preparedStatement.setNull(4, Types.NULL);
			}

			// Executa Update

			count = new Integer(preparedStatement.executeUpdate());

			if (count != null && count.equals(1)) {
				connection.commit();
				SessaoSistema.setCodigodMensagem(0);
				SessaoSistema.setIdUsuarioLogado(usuario.getIdUsuario());
				SessaoSistema.setNomeUsuarioLogado(usuario.getNome());
			} else {
				SessaoSistema.setCodigodMensagem(104);
				SessaoSistema.setDescMensagem("Erro ao Atualizar os dados!");
				System.out.println("LOG::UsuarioDAO:: " + SessaoSistema.getDescMensagem());
			}

		} catch (SQLException e) {			
			SessaoSistema.setCodigodMensagem(104);
			SessaoSistema.setDescMensagem("Erro ao Atualizar os dados!");
			System.out.println("LOG::UsuarioDAO:: " + SessaoSistema.getDescMensagem());
			System.out.println("LOG::UsuarioDAO::ERRO::  " + e);
			usuario = null;

			e.printStackTrace();

		} finally {

			ConnectionFactory.closeConnection();
		}

		return usuario;
	}

	/**
	 * Consulta Usuario, procurando por Email
	 * 
	 * @see opet.mygarage.model.persistencia.IUsuarioDAO#salvar(opet.mygarage.vo.Usuario)
	 */
	@Override
	public Usuario consultaPorEmailUsuarioDAO(Usuario usuario) {

		PreparedStatement preparedStatement = null;

		String query = null;

		ResultSet resultSet = null;
		try {
			connection = ConnectionFactory.getConnection();

			if (connection == null) {
				SessaoSistema.setCodigodMensagem(101);
				SessaoSistema.setDescMensagem("Erro ao abrir o Banco de dados");
				System.out.println("LOG::UsuarioDAO:: " + SessaoSistema.getDescMensagem());
				return null;
			} 

			query = "SELECT * FROM USUARIO WHERE EMAIL = ?";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, usuario.getEmail());

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				usuario.setIdUsuario(resultSet.getInt("IDUSUARIO"));
				usuario.setNome(resultSet.getString("NOME"));
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
				if (resultSet.getString("FOTO") != null) {

					usuario.setFoto(resultSet.getString("FOTO"));

				} else {

					usuario.setFoto(null);
				}
			} else {
				SessaoSistema.setCodigodMensagem(100);
				SessaoSistema.setDescMensagem("Select não retornou dados! Login invalido");
				System.out.println("LOG::UsuarioDAO:: " + SessaoSistema.getDescMensagem());
				return null;
			}

		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(103);
			SessaoSistema.setDescMensagem("Erro ao consultar os dados!");
			System.out.println("LOG::UsuarioDAO:: " + SessaoSistema.getDescMensagem());
			System.out.println("LOG::UsuarioDAO::ERRO::  " + e);
			usuario = null;
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection();
		}
		
		return usuario;
	}

	/**
	 * Consulta Usuario, procurando por Nome
	 * 
	 * @see opet.mygarage.model.persistencia.IUsuarioDAO#salvar(opet.mygarage.vo.Usuario)
	 */
	@Override
	public List<Usuario> buscaUsuarioDAO(Usuario usuario) {
		
		List<Usuario> usuarioList = null;
		
		PreparedStatement preparedStatement = null;

		String query = null;

		ResultSet resultSet = null;
		try {		
			
			connection = ConnectionFactory.getConnection();

			if (connection == null) {
				SessaoSistema.setCodigodMensagem(101);
				SessaoSistema.setDescMensagem("Erro ao abrir o Banco de dados");
				System.out.println("LOG::UsuarioDAO:: " + SessaoSistema.getDescMensagem());
				return null;
			} 
			
			String concatenaLike = "%" + usuario.getNome() + "%";
			
			query = "SELECT * FROM USUARIO WHERE NOME LIKE ? ORDER BY NOME ASC";
		
			preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
		
			preparedStatement.setString(1, concatenaLike);

			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.last()) {
				
				resultSet.beforeFirst();
				usuarioList = new ArrayList<>();
				
				while (resultSet.next()) {

					usuario = new Usuario();
					usuario.setIdUsuario(resultSet.getInt("IDUSUARIO"));
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
					if (resultSet.getString("FOTO") != null) {

						usuario.setFoto(resultSet.getString("FOTO"));

					} else {

						usuario.setFoto(null);
					}
					
					usuarioList.add(usuario);
				}
			}
			else {
				SessaoSistema.setCodigodMensagem(100);
				SessaoSistema.setDescMensagem("Select não retornou dados");
				System.out.println("LOG::UsuarioDAO:: " + SessaoSistema.getDescMensagem());
				return null;
			}

		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(103);
			SessaoSistema.setDescMensagem("Erro ao consultar os dados!");
			System.out.println("LOG::UsuarioDAO:: " + SessaoSistema.getDescMensagem());
			System.out.println("LOG::UsuarioDAO::ERRO::  " + e);
			usuario = null;
			e.printStackTrace();
			return null;
		} finally {
			ConnectionFactory.closeConnection();
		}

		return usuarioList;
	}
}
