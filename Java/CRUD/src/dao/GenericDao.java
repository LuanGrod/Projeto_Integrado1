//nao ta sendo usado atualmente

package dao;

import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import logs.Registro;

public class GenericDao {
	private PreparedStatement comando; // Atributo usado para preparar e executar instru��es SQL.
	String dataHora = new SimpleDateFormat("[yyyy/MM/dd HH:mm:ss]").format(Calendar.getInstance().getTime());

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
			return "Tipo de Exce��o: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage(); 
		}
		return null;
	}
}
