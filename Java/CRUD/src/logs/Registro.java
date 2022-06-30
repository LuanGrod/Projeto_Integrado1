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
        	excecao = ConnectionDatabase.conectaBd(); // Abre a conexão com o banco de dados.
        	if (excecao == null) {
                // Obtém os dados de conexão com o banco de dados e prepara a instrução SQL.
                comando = ConnectionDatabase.getConexaoBd().prepareStatement(instrucaoSql,ResultSet.TYPE_SCROLL_SENSITIVE, 
                        ResultSet.CONCUR_UPDATABLE);
                
                // Executa a instrução SQL e retorna os dados ao objeto ResultSet.
                registros = comando.executeQuery(); 
                
                if (registros.next()) { // Se for retornado pelo menos um registro.
                    registros.beforeFirst(); // Retorna o cursor para antes do 1º registro.
        	        while (registros.next()) {
                        // Atribui os dados do funcionário ao objeto Funcionario por meio dos métodos set e
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
                // Libera os recursos usados pelo objeto Connection e fecha a conexão com o banco de dados.
                ConnectionDatabase.getConexaoBd().close(); 
            }
        } catch (Exception e) {
        	excecao = "Tipo de Exceção: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
        	relatorios = null; // Caso ocorra qualquer exceção.
        }
        return relatorios;
	}
	
	public void printaRelatorio(List<Log> relatorios) {
		try {
			FileWriter fw = new FileWriter("C:\\reports\\log.txt", true);
			BufferedWriter bf = new BufferedWriter(fw);
			bf.write("*RELATÓRIO DE PEDIDOS*\n\n");
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
