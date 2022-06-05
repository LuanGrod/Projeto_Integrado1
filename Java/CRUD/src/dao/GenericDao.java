package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GenericDao {
	private PreparedStatement comando; // Atributo usado para preparar e executar instru��es SQL.
	private ResultSet registros; // Atributo que recebe os dados retornados por uma instru��o SQL.

	/**
	 * M�todo generico usado para inserir uma tupla em uma tabela do banco de dados.
	 * @param instrucaoSql Instru��o SQL a ser executada.
	 * @param parametros Valores dos campos da instru��o SQL. As retic�ncias no tipo Object 
	 * indicam que "parametros" pode receber um n�mero vari�vel de argumentos Object. 
	 * @return NULL: caso n�o ocorra nenhum erro durante a conex�o com o DB </br>
	 * STRING: caso ocorra algum erro, retorna uma string com o tipo de excess�o e sua descri��o.
	 */
	protected String insere(String instrucaoSql, Object... parametros) {
		try {
			String excecao = ConnectionDatabase.conectaBd(); // Abre a conex�o com o banco de dados.
			if (excecao == null) {
				// Obt�m os dados de conex�o com o banco de dados e prepara a instru��o SQL.
				comando = ConnectionDatabase.getConexaoBd().prepareStatement(instrucaoSql);

				// Associa cada par�metro Object recebido ao objeto "comando".
				for (int i = 0; i < parametros.length; i++)
					// 1� argumento: posi��o do par�metro na instru��o SQL; 2� argumento: par�metro.
					// Para objetos Funcionario: 1) Nome, 2) Sexo, 3) Salario, 4) PlanoSaude, 5) IdCargo
					comando.setObject(i + 1, parametros[i]);

				comando.execute(); // Executa a instru��o SQL.

				comando.close(); // Libera os recursos usados pelo objeto PreparedStatement.
				// Libera os recursos usados pelo objeto Connection e fecha a conex�o com o banco de dados.
				ConnectionDatabase.getConexaoBd().close();
			} else
				return excecao; // Caso ocorra exce��o ao tentar conectar com o banco de dados.
		} catch (Exception e) {
			// Caso ocorra qualquer tipo de exce��o.
			return "Tipo de Exce��o: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage(); 
		}
		return null; // Se o registro foi inserido com sucesso.
	}

	/**
	 * M�todo generico usado para realizar uma consulta ao banco de dados.
	 * @param instrucaoSql Instru��o SQL a ser executada.
	 * @param parametros Valores dos campos da instru��o SQL. As retic�ncias no tipo Object 
	 * indicam que "parametros" pode receber um n�mero vari�vel de argumentos Object. 
	 * @return os dados da consulta (ResultSet)
	 */
	protected ResultSet consulta(String instrucaoSql, Object... parametros) {
		try {
			String excecao = ConnectionDatabase.conectaBd(); // Abre a conex�o com o banco de dados.
			if (excecao == null) {
				// Obt�m os dados de conex�o com o banco de dados e prepara a instru��o SQL.
				comando = ConnectionDatabase.getConexaoBd().prepareStatement(instrucaoSql);

				// Associa cada par�metro Object recebido ao objeto "comando".
				for (int i = 0; i < parametros.length; i++)
					// 1� argumento: posi��o do par�metro na instru��o SQL; 2� argumento: par�metro.
					// Para objetos Funcionario: 1) Nome, 2) Sexo, 3) Salario, 4) PlanoSaude, 5) IdCargo
					comando.setObject(i + 1, parametros[i]);
				
				registros = comando.executeQuery(); // Executa a instru��o SQL.

				return registros;
			}
		} catch (Exception e) {
			System.out.println( "Tipo de Exce��o: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage()); 
		}
		return registros;
	}
}
