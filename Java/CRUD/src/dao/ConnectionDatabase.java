package dao;

import java.sql.*;

public class ConnectionDatabase {
	private static Connection conexao; // Atributo que receber� os dados para conex�o com o banco de dados.

	// String de conex�o (Dados: servidor, banco de dados, usu�rio, senha, uso de SSL e fuso hor�rio).
	private static final String SERVER = "jdbc:mysql://localhost";
	private static final String DATABASE = "/crud";
	private static final String USER = "?user=root";
	private static final String PASSWORD = "&password=1234567";

	// O protocolo SSL criptografa o fluxo de dados entre o servidor de banco de dados 
	// e a aplica��o cliente, protegendo-o de ataques externos.
	private static final String USE_SSL = "&useSSL=false";

	// Dependendo da vers�o do MySQL e de como seu servidor est� configurado, tamb�m
	// pode ser preciso determinar um fuso hor�rio espec�fico do servidor MySQL.
	private static final String USE_TIMEZONE = "&useTimezone=true";
	private static final String SERVER_TIMEZONE = "&serverTimezone=UTC";

	private static final String STRING_CONNECTION = SERVER + DATABASE + USER + PASSWORD + USE_SSL + USE_TIMEZONE + SERVER_TIMEZONE;

	/**
	 * M�todo respons�vel por abrir a conex�o com o banco de dados.
	 * @return NULL: caso n�o ocorra nenhum erro durante a conex�o com o DB </br>
	 * STRING: caso ocorra algum erro, retorna uma string com o tipo de excess�o e sua descri��o
	 */
	public static String conectaBd() { // Abre a conex�o com o banco de dados.
		try {
			conexao = DriverManager.getConnection(STRING_CONNECTION); // Atribui os dados de conex�o ao objeto "conexao".
		} catch (Exception e) {
			// Caso ocorra qualquer tipo de exce��o.
			return "Tipo de Exce��o: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage(); 
		}
		return null; // Caso a conex�o ocorra com sucesso.
	}

	public static Connection getConexaoBd() { // Retorna o objeto que cont�m os dados para conex�o com o banco de dados.
		return conexao;
	}
}
