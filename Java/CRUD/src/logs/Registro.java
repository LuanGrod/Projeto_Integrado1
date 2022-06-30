package logs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import dao.ConnectionDatabase;
import model.log.Log;

public class Registro {
	private String instrucaoSql;
	private static String excecao = null; 
	private PreparedStatement comando; 
	private ResultSet registros;
	
	public List<Log> consultaRelatorio() {
		Log relatorio = new Log();
		List<Log> relatorios = new ArrayList<Log>();
		instrucaoSql = "select * from relatorio ";
		
        try {
        	excecao = ConnectionDatabase.conectaBd(); // Abre a conex�o com o banco de dados.
        	if (excecao == null) {
                // Obt�m os dados de conex�o com o banco de dados e prepara a instru��o SQL.
                comando = ConnectionDatabase.getConexaoBd().prepareStatement(instrucaoSql,ResultSet.TYPE_SCROLL_SENSITIVE, 
                        ResultSet.CONCUR_UPDATABLE);
                
                // Executa a instru��o SQL e retorna os dados ao objeto ResultSet.
                registros = comando.executeQuery(); 
                
                if (registros.next()) { // Se for retornado pelo menos um registro.
                    registros.beforeFirst(); // Retorna o cursor para antes do 1� registro.
        	        while (registros.next()) {
                        // Atribui os dados do funcion�rio ao objeto Funcionario por meio dos m�todos set e
                        // adiciona este objeto ao ArrayList funcionarios.
        	        	relatorio = new Log();
        	        	relatorio.setId(registros.getInt("id"));
        	        	relatorio.setDataPedido(registros.getDate("Data do Pedido"));
        	        	relatorio.setCliente(registros.getString("Cliente"));
        	        	relatorio.setUsuario(registros.getString("Vendedor"));
        	        	relatorios.add(relatorio);
        	        }
        	    }
                registros.close(); // Libera os recursos usados pelo objeto ResultSet.
                comando.close(); // Libera os recursos usados pelo objeto PreparedStatement.
                // Libera os recursos usados pelo objeto Connection e fecha a conex�o com o banco de dados.
                ConnectionDatabase.getConexaoBd().close(); 
            }
        } catch (Exception e) {
        	excecao = "Tipo de Exce��o: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
        	relatorios = null; // Caso ocorra qualquer exce��o.
        }
        return relatorios;
	}
	
	public void printaRelatorio(List<Log> relatorios) {
		try {
			FileWriter fw = new FileWriter("C:\\reports\\log.txt", true);
			BufferedWriter bf = new BufferedWriter(fw);
			bf.write("*RELAT�RIO DE PEDIDOS*\n\n");
			for(int i=0 ; i<relatorios.size() ; i++) {
				bf.write(
						"{\nID: " + relatorios.get(i).getId() +
						"\nData: " + relatorios.get(i).getDataPedido().toString() +
						"\nCliente: " + relatorios.get(i).getCliente() +
						"\nVendedor: " + relatorios.get(i).getUsuario() + "\n}\n"
						);
			}
			
			bf.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String getExcecao() { 
		return excecao;
	}

}
