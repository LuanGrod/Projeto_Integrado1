//nao ta sendo usado atualmente

package dao;

import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import logs.Registro;

public class GenericDao {
	private PreparedStatement comando; // Atributo usado para preparar e executar instruções SQL.
	String dataHora = new SimpleDateFormat("[yyyy/MM/dd HH:mm:ss]").format(Calendar.getInstance().getTime());

	/**
	 * Método generico usado para inserir uma tupla em uma tabela do banco de dados.
	 * @param instrucaoSql Instrução SQL a ser executada.
	 * @param parametros Valores dos campos da instrução SQL. As reticências no tipo Object 
	 * indicam que "parametros" pode receber um número variável de argumentos Object. 
	 * @return NULL: caso não ocorra nenhum erro durante a conexão com o DB </br>
	 * STRING: caso ocorra algum erro, retorna uma string com o tipo de excessão e sua descrição.
	 */
	protected String insere(String instrucaoSql, Object... parametros) {
		try {
			String excecao = ConnectionDatabase.conectaBd();
			if (excecao == null) {
				comando = ConnectionDatabase.getConexaoBd().prepareStatement(instrucaoSql);
				for (int i = 0; i < parametros.length; i++)
					comando.setObject(i + 1, parametros[i]);

				comando.execute(); 
				comando.close(); 
				ConnectionDatabase.getConexaoBd().close();
			} else
				return excecao;
		} catch (Exception e) {
			return "Tipo de Exceção: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage(); 
		}
		return null;
	}
}
