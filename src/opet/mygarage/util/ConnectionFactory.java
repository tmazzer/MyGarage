package opet.mygarage.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsavel por conectar com o Oracle usando JDBC
 * 
 * @author Baracho - Adaptado por Juliano Zapelini Batista e Tiago Mazzer
 * 
 * @since 19/04/2016
 * 
 * @version 1.0
 * 
 */

public class ConnectionFactory {

	private static final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";
	private static final String DATABASE_URL = "jdbc:oracle:thin:@localhost:1521: xe";
	private static final String USERNAME = "aluno";
	private static final String PASSWORD = "aluno";

	private static final String CONNECTION_IS_NOT_OPEN = "A conexão com o banco de dados não foi realizada.";

	private static Connection connection;

	private ConnectionFactory() {

		openConnection();
	}

	private void openConnection() {

		// Declaração de variáveis


		// Processamento dos dados

		try {

			Class.forName(JDBC_DRIVER);

			// Connect with the data base

			connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

			connection.setAutoCommit(false);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

			connection = null;

		} catch (SQLException e) {
			e.printStackTrace();

			connection = null;
		} 
	}

	public static Connection getConnection() throws SQLException {

		// Processamento dos dados

		if (connection == null || connection.isClosed()) {

			new ConnectionFactory();
		}

		return connection;
	}

	public static void closeConnection() {

		// Processametno dos dados

		if (connection != null) {

			try {

				connection.close();

			} catch (SQLException e) {
				e.printStackTrace();

			} 

		} else {

			System.err.println("\n " + CONNECTION_IS_NOT_OPEN);
		}
	}

}