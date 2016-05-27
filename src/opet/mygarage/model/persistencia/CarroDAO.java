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
import opet.mygarage.vo.Acessorios;
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
				System.out.println("CarroDAO::cadastraCarroDAO:: " + SessaoSistema.getDescMensagem());
				return null;
			}

			query = "INSERT INTO CARRO "
					+ "(CARRO.idCarro, "
					+ "CARRO.TPCOMBUST_IDTPCOMBUST, "
					+ "CARRO.usuario_idusuario, "
					+ "CARRO.apelidocarro, "
					+ "CARRO.marca, "
					+ "CARRO.modelo, "
					+ "CARRO.anofabricacao, "
					+ "CARRO.anomodelo, "
					+ "CARRO.cor, "
					+ "CARRO.placa, "
					+ "CARRO.kilometragem, "
					+ "CARRO.FOTO) "
					+ "VALUES ("
					+ "idCarro_SEQUENCE.NEXTVAL, ?, ?, " 
					+ "?, ?, ?, " 
					+ "?, ?, " 
					+ "?, ?, " 
					+ "?, ?)";

			preparedStatement = connection.prepareStatement(query);

			// ESTES CAMPOS SÃO OBRIGATORIOS. DEVEM TER SIDO VALIDADOS ANTES
			preparedStatement.setInt(1, carro.getTpCombust());
			preparedStatement.setInt(2, SessaoSistema.getIdUsuarioLogado());
			
			// ESTES CAMPOS PODEM SER NULOS. 
			if (carro.getApelido() != null) {
				preparedStatement.setString(3, carro.getApelido());

			} else {
				preparedStatement.setNull(3, Types.NULL);
			}
			
			if (carro.getMarca() != null) {
				preparedStatement.setString(4, carro.getMarca());

			} else {
				preparedStatement.setNull(4, Types.NULL);
			}
			
			if (carro.getModelo() != null) {
				preparedStatement.setString(5, carro.getModelo());

			} else {
				preparedStatement.setNull(5, Types.NULL);
			}
			
			if (carro.getAnoFabricacao() != null) {
				preparedStatement.setString(6, carro.getAnoFabricacao());

			} else {
				preparedStatement.setNull(6, Types.NULL);
			}
			
			if (carro.getAnoModelo() != null) {
				preparedStatement.setString(7, carro.getAnoModelo());

			} else {
				preparedStatement.setNull(7, Types.NULL);
			}
			
			if (carro.getCor() != null) {
				preparedStatement.setString(8, carro.getCor());

			} else {
				preparedStatement.setNull(8, Types.NULL);
			}
			
			if (carro.getPlaca() != null) {
				preparedStatement.setString(9, carro.getPlaca());

			} else {
				preparedStatement.setNull(9, Types.NULL);
			}
			
			if (carro.getQuilometragem() != null) {
				preparedStatement.setInt(10, carro.getQuilometragem());

			} else {
				preparedStatement.setNull(10, Types.NULL);
			}
			
			if (carro.getFoto() != null) {
				preparedStatement.setString(11, carro.getFoto());

			} else {
				preparedStatement.setNull(11, Types.NULL);
			}
			

			// Executa INSERT

			count = new Integer(preparedStatement.executeUpdate());

			if (count.equals(1)) {
			
				//recuperar ID do CARRO										
				try {
					ResultSet resultSet = null;
					
					query = "SELECT IDCARRO FROM CARRO "
							+ "WHERE APELIDOCARRO = ? AND "
							+ "MARCA = ? AND "
							+ "MODELO = ? ";

					preparedStatement = connection.prepareStatement(query);

					preparedStatement.setString(1, carro.getApelido());
					preparedStatement.setString(2, carro.getMarca());
					preparedStatement.setString(3, carro.getModelo());					
					
					resultSet = preparedStatement.executeQuery();

					if (resultSet.next()) {
						carro.setIdCarro(resultSet.getInt("IDCARRO"));
					} else {
						SessaoSistema.setCodigodMensagem(100);
						SessaoSistema.setDescMensagem("Select não retornou dados");
						System.out.println("CarroDAO::cadastraCarroDAO:: " + SessaoSistema.getDescMensagem());
						return null;
					}

				} catch (SQLException e) {
					SessaoSistema.setCodigodMensagem(103);
					SessaoSistema.setDescMensagem("Erro ao consultar os dados!");
					System.out.println("CarroDAO::cadastraCarroDAO:: " + SessaoSistema.getDescMensagem());
					System.out.println("CarroDAO::cadastraCarroDAO::ERRO::  " + e);
					carro = null;
					e.printStackTrace();
				}
				
				System.out.println("CarroDAO::cadastraCarroDAO::Insert com sucesso");
				connection.commit();
				SessaoSistema.setCodigodMensagem(0);
			} else {
				SessaoSistema.setCodigodMensagem(105);
				SessaoSistema.setDescMensagem("Erro ao inserir os dados!");
				System.out.println("CarroDAO::cadastraCarroDAO:: " + SessaoSistema.getDescMensagem());
			}

		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(105);
			SessaoSistema.setDescMensagem("Erro ao inserir os dados!");
			System.out.println("CarroDAO::cadastraCarroDAO:: " + SessaoSistema.getDescMensagem());
			System.out.println("CarroDAO::cadastraCarroDAO::ERRO::  " + e);
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
				System.out.println("CarroDAO::consultaCarroDAO:: " + SessaoSistema.getDescMensagem());
				return null;
			}

			query = "SELECT * FROM CARRO, TPCOMBUST WHERE IDCARRO = ? AND IDTPCOMBUST = ?";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, carro.getIdCarro());
			preparedStatement.setInt(2, carro.getTpCombust());

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				carro.setIdCarro(resultSet.getInt("IDCARRO"));
				carro.setDescCombut(resultSet.getString("DESCRICAO"));
				carro.setApelido(resultSet.getString("APELIDOCARRO"));
				carro.setMarca(resultSet.getString("MARCA"));
				carro.setModelo(resultSet.getString("MODELO"));
				carro.setAnoFabricacao(resultSet.getString("ANOFABRICACAO"));
				carro.setAnoModelo(resultSet.getString("ANOMODELO"));
				carro.setCor(resultSet.getString("COR"));
				carro.setPlaca(resultSet.getString("PLACA"));
				carro.setQuilometragem(resultSet.getInt("KILOMETRAGEM"));
				carro.setFoto(resultSet.getString("FOTO"));

			} else {
				SessaoSistema.setCodigodMensagem(100);
				SessaoSistema.setDescMensagem("Select não retornou dados");
				System.out.println("CarroDAO::consultaCarroDAO:: " + SessaoSistema.getDescMensagem());
				return null;
			}

		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(103);
			SessaoSistema.setDescMensagem("Erro ao consultar os dados!");
			System.out.println("CarroDAO::consultaCarroDAO:: " + SessaoSistema.getDescMensagem());
			System.out.println("CarroDAO::consultaCarroDAO::ERRO::  " + e);
			carro = null;
			e.printStackTrace();
		}

		return carro;
	}

	/**
	 * Exclui Carro existente na tabela Carro Retorna TRUE para Excluido com
	 * sucesso Retorna FALSE se ERRO ao Excluir
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
				System.out.println("CarroDAO::excluiCarroDAO:: " + SessaoSistema.getDescMensagem());
				return null;
			}
			
			query = "DELETE FROM ACESSORIOS WHERE CARRO_IDCARRO = ?";
			
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, carro.getIdCarro());

			count = new Integer(preparedStatement.executeUpdate());

			connection.commit();

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
				System.out.println("CarroDAO::excluiCarroDAO:: " + SessaoSistema.getDescMensagem());
				return false;
			}

		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(102);
			SessaoSistema.setDescMensagem("Erro ao excluir os dados!");
			System.out.println("CarroDAO::excluiCarroDAO:: " + SessaoSistema.getDescMensagem());
			System.out.println("CarroDAO::excluiCarroDAO::ERRO::  " + e);
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
				System.out.println("CarroDAO::alteraCarroDAO:: " + SessaoSistema.getDescMensagem());
				return null;
			}

			query = "UPDATE CARRO SET "
					+ "CARRO.TPCOMBUST_IDTPCOMBUST = ?, "
					+ "CARRO.USUARIO_IDUSUARIO = ?, "
					+ "CARRO.apelidocarro = ?, " 
					+ "CARRO.marca = ?, " 
					+ "CARRO.modelo = ?, "
					+ "CARRO.anofabricacao = ?, " 
					+ "CARRO.anomodelo = ?, " 
					+ "CARRO.cor = ?, " 
					+ "CARRO.placa = ?, "
					+ "CARRO.kilometragem = ?, "
					+ "CARRO.foto = ? "
					+ "WHERE CARRO.idCarro = ?";

			preparedStatement = connection.prepareStatement(query);

			// ESTES CAMPOS SÃO OBRIGATORIOS. DEVEM TER SIDO VALIDADOS ANTES
			preparedStatement.setInt(1, carro.getTpCombust());
			preparedStatement.setInt(2, carro.getUsuarioIdUsuario());
			
			preparedStatement.setInt(12, carro.getIdCarro());

			// campos NAO obrigarorios. Se vazios, devem ser tratados como NULL
			if (carro.getApelido() != null) {

				preparedStatement.setString(3, carro.getApelido());

			} else {

				preparedStatement.setNull(3, Types.NULL);
			}

			if (carro.getMarca() != null) {

				preparedStatement.setString(4, carro.getMarca());

			} else {

				preparedStatement.setNull(4, Types.NULL);
			}

			if (carro.getModelo() != null) {

				preparedStatement.setString(5, carro.getModelo());

			} else {

				preparedStatement.setNull(5, Types.NULL);
			}

			if (carro.getAnoFabricacao() != null) {

				preparedStatement.setString(6, carro.getAnoFabricacao());

			} else {

				preparedStatement.setNull(6, Types.NULL);
			}

			if (carro.getAnoModelo() != null) {

				preparedStatement.setString(7, carro.getAnoModelo());

			} else {

				preparedStatement.setNull(7, Types.NULL);
			}

			if (carro.getCor() != null) {

				preparedStatement.setString(8, carro.getCor());

			} else {

				preparedStatement.setNull(8, Types.NULL);
			}

			if (carro.getPlaca() != null) {

				preparedStatement.setString(9, carro.getPlaca());

			} else {

				preparedStatement.setNull(9, Types.NULL);
			}

			if (carro.getQuilometragem() != null) {

				preparedStatement.setInt(10, carro.getQuilometragem());

			} else {

				preparedStatement.setNull(10, Types.NULL);
			}
			
			if (carro.getFoto() != null) {

				preparedStatement.setString(11, carro.getFoto());

			} else {

				preparedStatement.setNull(11, Types.NULL);
			}

			// Executa Update

			count = new Integer(preparedStatement.executeUpdate());

			if (count != null && count.equals(1)) {
				connection.commit();
				SessaoSistema.setCodigodMensagem(0);
			} else {
				SessaoSistema.setCodigodMensagem(104);
				SessaoSistema.setDescMensagem("Erro ao Atualizar os dados!");
				System.out.println("CarroDAO::alteraCarroDAO:: " + SessaoSistema.getDescMensagem());
			}

		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(104);
			SessaoSistema.setDescMensagem("Erro ao Atualizar os dados!");
			System.out.println("CarroDAO::alteraCarroDAO:: " + SessaoSistema.getDescMensagem());
			System.out.println("CarroDAO::alteraCarroDAO::ERRO::  " + e);
			carro = null;

			e.printStackTrace();

		} finally {

			ConnectionFactory.closeConnection();
		}

		return carro;
	}

	/**
	 * Lista todos os carros do Usuario informado
	 */
	@Override
	public List<Carro> listaCarrosDAO(Integer idUsuario) {
		List<Carro> carroList = null;
		Carro carro = null;

		PreparedStatement preparedStatement = null;

		String query = null;

		ResultSet resultSet = null;
		try {
			connection = ConnectionFactory.getConnection();

			if (connection == null) {
				SessaoSistema.setCodigodMensagem(101);
				SessaoSistema.setDescMensagem("Erro ao abrir o Banco de dados");
				System.out.println("CarroDAO::listaCarrosDAO:: " + SessaoSistema.getDescMensagem());
				return null;
			}

			query = "SELECT * FROM CARRO "
					+ "WHERE USUARIO_IDUSUARIO = ?";

			preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			
			preparedStatement.setInt(1, idUsuario);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.last()) {

				resultSet.beforeFirst();
				carroList = new ArrayList<>();

				while (resultSet.next()) {

					carro = new Carro();
					carro.setIdCarro(resultSet.getInt("IDCARRO"));
					carro.setTpCombust(resultSet.getInt("TPCOMBUST_IDTPCOMBUST"));
					carro.setUsuarioIdUsuario(resultSet.getInt("USUARIO_IDUSUARIO"));

					if (resultSet.getString("APELIDOCARRO") != null) {
						carro.setApelido(resultSet.getString("APELIDOCARRO"));
					} else {
						carro.setApelido(null);
					}

					if (resultSet.getString("MARCA") != null) {
						carro.setMarca(resultSet.getString("MARCA"));
					} else {
						carro.setMarca(null);
					}

					if (resultSet.getString("MODELO") != null) {
						carro.setModelo(resultSet.getString("MODELO"));
					} else {
						carro.setModelo(null);
					}

					if (resultSet.getString("ANOFABRICACAO") != null) {
						carro.setAnoFabricacao(resultSet.getString("ANOFABRICACAO"));
					} else {
						carro.setAnoFabricacao(null);
					}

					if (resultSet.getString("ANOMODELO") != null) {
						carro.setAnoModelo(resultSet.getString("ANOMODELO"));
					} else {
						carro.setAnoModelo(null);
					}

					if (resultSet.getString("COR") != null) {
						carro.setCor(resultSet.getString("COR"));
					} else {
						carro.setCor(null);
					}

					if (resultSet.getString("PLACA") != null) {
						carro.setPlaca(resultSet.getString("PLACA"));
					} else {
						carro.setPlaca(null);
					}

					if (resultSet.getString("KILOMETRAGEM") != null) {
						carro.setQuilometragem(resultSet.getInt("KILOMETRAGEM"));
					} else {
						carro.setQuilometragem(null);
					}
					
					if (resultSet.getString("FOTO") != null) {
						carro.setFoto(resultSet.getString("FOTO"));
					} else {
						carro.setFoto(null);
					}

					carroList.add(carro);
				}
			} else {
				SessaoSistema.setCodigodMensagem(100);
				SessaoSistema.setDescMensagem("Select não retornou dados");
				System.out.println("CarroDAO::listaCarrosDAO:: " + SessaoSistema.getDescMensagem());
				return null;
			}

		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(103);
			SessaoSistema.setDescMensagem("Erro ao consultar os dados!");
			System.out.println("CarroDAO::listaCarrosDAO:: " + SessaoSistema.getDescMensagem());
			System.out.println("CarroDAO::listaCarrosDAO::ERRO::  " + e);
			carro = null;
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection();
		}

		return carroList;
	}
	
	/**
	 * Cadastra um novo Acessorio na tabela Acessorios
	 */
	@Override
	public Acessorios cadastraAcessoriosDAO(Carro carro, Acessorios acessorios) {
		PreparedStatement preparedStatement = null;

		String query = null;

		Integer count = null;
		try {
			connection = ConnectionFactory.getConnection();

			// Processamento dos dados
			if (connection == null) {
				SessaoSistema.setCodigodMensagem(101);
				SessaoSistema.setDescMensagem("Erro ao abrir o Banco de dados");
				System.out.println("CarroDAO::cadastraAcessoriosDAO:: " + SessaoSistema.getDescMensagem());
				return null;
			}

			query = "INSERT INTO ACESSORIOS "
					+ "(ACESSORIOS.IDACESSORIOS, "
					+ "ACESSORIOS.CARRO_IDCARRO, "
					+ "ACESSORIOS.NOME, "
					+ "ACESSORIOS.DESCRICAO, "
					+ "ACESSORIOS.MARCA, "
					+ "ACESSORIOS.MODELO, "
					+ "ACESSORIOS.FOTO) "
					+ "VALUES (IDACESSORIOS_SEQUENCE.NEXTVAL, ?, ?, ?, ?, ?, ?)";

			preparedStatement = connection.prepareStatement(query);

			// ESTES CAMPOS SÃO OBRIGATORIOS. DEVEM TER SIDO VALIDADOS ANTES
			preparedStatement.setInt(1, carro.getIdCarro());
			preparedStatement.setString(2, acessorios.getNome());
			
			// ESTES CAMPOS PODEM SER NULOS
			if (acessorios.getDescricao() != null) {
				preparedStatement.setString(3, acessorios.getDescricao());

			} else {
				preparedStatement.setNull(3, Types.NULL);
			}
			
			if (acessorios.getMarca() != null) {
				preparedStatement.setString(4, acessorios.getMarca());

			} else {
				preparedStatement.setNull(4, Types.NULL);
			}
			
			if (acessorios.getModelo() != null) {
				preparedStatement.setString(5, acessorios.getModelo());

			} else {
				preparedStatement.setNull(5, Types.NULL);
			}
			
			if (acessorios.getFoto() != null) {
				preparedStatement.setString(6, acessorios.getFoto());

			} else {
				preparedStatement.setNull(6, Types.NULL);
			}

			// Executa INSERT

			count = new Integer(preparedStatement.executeUpdate());

			if (count.equals(1)) {
				
				
				try {
					ResultSet resultSet = null;
					
					query = "SELECT IDACESSORIOS FROM ACESSORIOS "
							+ "WHERE CARRO_IDCARRO = ? "
							+ "AND NOME = ? "
							+ "AND MARCA = ?";

					preparedStatement = connection.prepareStatement(query);					
					
					preparedStatement.setInt(1, carro.getIdCarro());
					preparedStatement.setString(2, acessorios.getNome());
					preparedStatement.setString(3, acessorios.getMarca());

					resultSet = preparedStatement.executeQuery();

					if (resultSet.next()) {
						acessorios.setIdAcessorios(resultSet.getInt("IDACESSORIOS"));
					} else {
						SessaoSistema.setCodigodMensagem(100);
						SessaoSistema.setDescMensagem("Select não retornou dados");
						System.out.println("CarroDAO::cadastraAcessoriosDAO:: " + SessaoSistema.getDescMensagem());
						acessorios = null;
					}

				} catch (SQLException e) {
					SessaoSistema.setCodigodMensagem(103);
					SessaoSistema.setDescMensagem("Erro ao consultar os dados!");
					System.out.println("CarroDAO::cadastraAcessoriosDAO:: " + SessaoSistema.getDescMensagem());
					System.out.println("CarroDAO::cadastraAcessoriosDAO::ERRO::  " + e);
					acessorios = null;
					e.printStackTrace();
				}							
				
				System.out.println("CarroDAO::cadastraAcessoriosDAO:Insert com sucesso");
				connection.commit();
				SessaoSistema.setCodigodMensagem(0);
			} else {
				SessaoSistema.setCodigodMensagem(105);
				SessaoSistema.setDescMensagem("Erro ao inserir os dados!");
				System.out.println("CarroDAO::cadastraAcessoriosDAO:: " + SessaoSistema.getDescMensagem());
			}

		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(105);
			SessaoSistema.setDescMensagem("Erro ao inserir os dados!");
			System.out.println("CarroDAO::cadastraAcessoriosDAO:: " + SessaoSistema.getDescMensagem());
			System.out.println("CarroDAO::cadastraAcessoriosDAO::ERRO::  " + e);
			carro = null;

			e.printStackTrace();

		} finally {
			ConnectionFactory.closeConnection();
		}
		return (acessorios);
	}

	@Override
	public Acessorios consultaAcessoriosDAO(Acessorios acessorios) {
		PreparedStatement preparedStatement = null;

		String query = null;

		ResultSet resultSet = null;

		try {
			connection = ConnectionFactory.getConnection();

			if (connection == null) {
				SessaoSistema.setCodigodMensagem(101);
				SessaoSistema.setDescMensagem("Erro ao abrir o Banco de dados");
				System.out.println("CarroDAO::consultaAcessoriosDAO:: " + SessaoSistema.getDescMensagem());
				return null;
			}

			query = "SELECT * FROM ACESSORIOS WHERE IDACESSORIOS = ?";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, acessorios.getIdAcessorios());

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				acessorios.setIdAcessorios(resultSet.getInt("IDACESSORIOS"));
				acessorios.setCarro_idCarro(resultSet.getInt("CARRO_IDCARRO"));
				acessorios.setNome(resultSet.getString("NOME"));
				acessorios.setDescricao(resultSet.getString("DESCRICAO"));
				acessorios.setMarca(resultSet.getString("MARCA"));
				acessorios.setModelo(resultSet.getString("MODELO"));
				acessorios.setFoto(resultSet.getString("FOTO"));
				

			} else {
				SessaoSistema.setCodigodMensagem(100);
				SessaoSistema.setDescMensagem("Select não retornou dados");
				System.out.println("CarroDAO::consultaAcessoriosDAO:: " + SessaoSistema.getDescMensagem());
				return null;
			}

		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(103);
			SessaoSistema.setDescMensagem("Erro ao consultar os dados!");
			System.out.println("CarroDAO::consultaAcessoriosDAO:: " + SessaoSistema.getDescMensagem());
			System.out.println("CarroDAO::consultaAcessoriosDAO::ERRO::  " + e);
			acessorios = null;
			e.printStackTrace();
		}

		return acessorios;
	}

	@Override
	public Boolean excluiAcessoriosDAO(Acessorios acessorios) {
		PreparedStatement preparedStatement = null;

		String query = null;

		Integer count = null;

		try {
			connection = ConnectionFactory.getConnection();
			// Processamento dos dados

			if (connection == null) {
				SessaoSistema.setCodigodMensagem(101);
				SessaoSistema.setDescMensagem("Erro ao abrir o Banco de dados");
				System.out.println("CarroDAO::excluiAcessoriosDAO:: " + SessaoSistema.getDescMensagem());
				return null;
			}

			query = "DELETE FROM ACESSORIOS WHERE IDACESSORIOS = ?";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, acessorios.getIdAcessorios());

			count = new Integer(preparedStatement.executeUpdate());

			if (count.equals(1)) {
				connection.commit();

				SessaoSistema.setCodigodMensagem(0);
				SessaoSistema.setDescMensagem("Dados excluídos com sucesso!");
				return true;

			} else {
				SessaoSistema.setCodigodMensagem(102);
				SessaoSistema.setDescMensagem("Erro ao excluir os dados! Usuario não encontrado");
				System.out.println("CarroDAO::excluiAcessoriosDAO:: " + SessaoSistema.getDescMensagem());
				return false;
			}

		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(102);
			SessaoSistema.setDescMensagem("Erro ao excluir os dados!");
			System.out.println("CarroDAO::excluiAcessoriosDAO:: " + SessaoSistema.getDescMensagem());
			System.out.println("CarroDAO::excluiAcessoriosDAO::ERRO::  " + e);
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Acessorios alteraAcessoriosDAO(Acessorios acessorios) {
		PreparedStatement preparedStatement = null;

		String query = null;

		Integer count = null;

		try {

			connection = ConnectionFactory.getConnection();

			if (connection == null) {
				SessaoSistema.setCodigodMensagem(101);
				SessaoSistema.setDescMensagem("Erro ao abrir o Banco de dados");
				System.out.println("CarroDAO::alteraAcessoriosDAO:: " + SessaoSistema.getDescMensagem());
				return null;
			}

			query = "UPDATE ACESSORIOS SET " 
					+ "ACESSORIOS.CARRO_IDCARRO = ?, " 
					+ "ACESSORIOS.NOME = ?, " 
					+ "ACESSORIOS.DESCRICAO = ?, "
					+ "ACESSORIOS.MARCA = ?, " 
					+ "ACESSORIOS.MODELO = ?, "
					+ "ACESSORIOS.FOTO = ? "
					+ "WHERE ACESSORIOS.IDACESSORIOS = ?";

			preparedStatement = connection.prepareStatement(query);

			// ESTES CAMPOS SÃO OBRIGATORIOS. DEVEM TER SIDO VALIDADOS ANTES
			preparedStatement.setInt(7, acessorios.getIdAcessorios());
			preparedStatement.setInt(1, acessorios.getCarro_idCarro());
			preparedStatement.setString(2, acessorios.getNome());

			// campos NAO obrigarorios. Se vazios, devem ser tratados como NULL
			if (acessorios.getDescricao() != null) {

				preparedStatement.setString(3, acessorios.getDescricao());

			} else {

				preparedStatement.setNull(3, Types.NULL);
			}

			if (acessorios.getMarca() != null) {

				preparedStatement.setString(4, acessorios.getMarca());

			} else {

				preparedStatement.setNull(4, Types.NULL);
			}

			if (acessorios.getModelo() != null) {

				preparedStatement.setString(5, acessorios.getModelo());

			} else {

				preparedStatement.setNull(5, Types.NULL);
			}
			
			if (acessorios.getFoto() != null) {

				preparedStatement.setString(6, acessorios.getFoto());

			} else {

				preparedStatement.setNull(6, Types.NULL);
			}

			// Executa Update

			count = new Integer(preparedStatement.executeUpdate());

			if (count != null && count.equals(1)) {
				connection.commit();
				SessaoSistema.setCodigodMensagem(0);
			} else {
				SessaoSistema.setCodigodMensagem(104);
				SessaoSistema.setDescMensagem("Erro ao Atualizar os dados!");
				System.out.println("CarroDAO::alteraAcessoriosDAO::: " + SessaoSistema.getDescMensagem());
			}

		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(104);
			SessaoSistema.setDescMensagem("Erro ao Atualizar os dados!");
			System.out.println("CarroDAO::alteraAcessoriosDAO::: " + SessaoSistema.getDescMensagem());
			System.out.println("CarroDAO::alteraAcessoriosDAO:::ERRO::  " + e);
			acessorios = null;

			e.printStackTrace();

		} finally {

			ConnectionFactory.closeConnection();
		}

		return acessorios;
	}

	public List<Acessorios> listaAcessoriosDAO(Carro carro) {
		List<Acessorios> acessoriosList = null;
		Acessorios acessorios = null;

		PreparedStatement preparedStatement = null;

		String query = null;

		ResultSet resultSet = null;
		
		try {
			connection = ConnectionFactory.getConnection();

			if (connection == null) {
				SessaoSistema.setCodigodMensagem(101);
				SessaoSistema.setDescMensagem("Erro ao abrir o Banco de dados");
				System.out.println("CarroDAO::listaAcessoriosDAO::: " + SessaoSistema.getDescMensagem());
				return null;
			}

			query = "SELECT * FROM ACESSORIOS "
					+ "WHERE CARRO_IDCARRO = ?";

			preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			preparedStatement.setInt(1, carro.getIdCarro());

			resultSet = preparedStatement.executeQuery();

			if (resultSet.last()) {

				resultSet.beforeFirst();
				acessoriosList = new ArrayList<>();

				while (resultSet.next()) {

					acessorios = new Acessorios();
					acessorios.setIdAcessorios(resultSet.getInt("IDACESSORIOS"));
					acessorios.setCarro_idCarro(resultSet.getInt("CARRO_IDCARRO"));
					acessorios.setNome(resultSet.getString("NOME"));

					if (resultSet.getString("DESCRICAO") != null) {
						acessorios.setDescricao(resultSet.getString("DESCRICAO"));
					} else {
						acessorios.setDescricao(null);
					}

					if (resultSet.getString("MARCA") != null) {
						acessorios.setMarca(resultSet.getString("MARCA"));
					} else {
						acessorios.setMarca(null);
					}

					if (resultSet.getString("MODELO") != null) {
						acessorios.setModelo(resultSet.getString("MODELO"));
					} else {
						acessorios.setModelo(null);
					}
					
					if (resultSet.getString("FOTO") != null) {
						acessorios.setFoto(resultSet.getString("FOTO"));
					} else {
						acessorios.setFoto(null);
					}


					acessoriosList.add(acessorios);
				}
			} else {
				SessaoSistema.setCodigodMensagem(100);
				SessaoSistema.setDescMensagem("Select não retornou dados");
				System.out.println("CarroDAO::listaAcessoriosDAO:: " + SessaoSistema.getDescMensagem());
				return null;
			}

		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(103);
			SessaoSistema.setDescMensagem("Erro ao consultar os dados!");
			System.out.println("CarroDAO::listaAcessoriosDAO:: " + SessaoSistema.getDescMensagem());
			System.out.println("CarroDAO::listaAcessoriosDAO::  " + e);
			acessorios = null;
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection();
		}

		return acessoriosList;
	}

	@Override
	public Boolean validaApelidoCarroDAO(Integer idUsuario, String apelidoCarro) {
		
		PreparedStatement preparedStatement = null;

		String query = null;

		ResultSet resultSet = null;

		try {
			connection = ConnectionFactory.getConnection();

			if (connection == null) {
				SessaoSistema.setCodigodMensagem(101);
				SessaoSistema.setDescMensagem("Erro ao abrir o Banco de dados");
				System.out.println("CarroDAO::validaApelidoCarroDAO:: " + SessaoSistema.getDescMensagem());
				return null;
			}

			query = "SELECT * FROM CARRO WHERE "
					+ "USUARIO_IDUSUARIO = ? AND "
					+ "APELIDOCARRO = ?";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, idUsuario);
			preparedStatement.setString(2, apelidoCarro);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(103);
			SessaoSistema.setDescMensagem("Erro ao consultar os dados!");
			System.out.println("CarroDAO::validaApelidoCarroDAO:: " + SessaoSistema.getDescMensagem());
			System.out.println("CarroDAO::validaApelidoCarroDAO::ERRO::  " + e);
			e.printStackTrace();
			return null; 
		}
	}

	@Override
	public Boolean validaNomeAcessorioDAO(Integer idCarro, String nomeAcessorio) {
		PreparedStatement preparedStatement = null;

		String query = null;

		ResultSet resultSet = null;

		try {
			connection = ConnectionFactory.getConnection();

			if (connection == null) {
				SessaoSistema.setCodigodMensagem(101);
				SessaoSistema.setDescMensagem("Erro ao abrir o Banco de dados");
				System.out.println("CarroDAO::validaNomeAcessorioDAO:: " + SessaoSistema.getDescMensagem());
				return null;
			}

			query = "SELECT * FROM ACESSORIOS WHERE "
					+ "CARRO_IDCARRO = ? AND "
					+ "NOME = ?";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, idCarro);
			preparedStatement.setString(2, nomeAcessorio);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			SessaoSistema.setCodigodMensagem(103);
			SessaoSistema.setDescMensagem("Erro ao consultar os dados!");
			System.out.println("CarroDAO::validaNomeAcessorioDAO:: " + SessaoSistema.getDescMensagem());
			System.out.println("CarroDAO::validaNomeAcessorioDAO::ERRO::  " + e);
			e.printStackTrace();
			return null; 
		}
	}

}
