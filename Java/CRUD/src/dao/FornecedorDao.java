package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.fornecedor.Fornecedor;

public class FornecedorDao extends GenericDao{
	
	private String instrucaoSql; // Atributo para armazenar a instru��o SQL a ser executada.
	private static String excecao = null; 
	private PreparedStatement comando; 
	private ResultSet registros;
	
	public String insereFornecedor(Fornecedor fornecedor) {
		instrucaoSql = "INSERT INTO FORNECEDOR (nome, cnpj, telefone, email) VALUES(?,?,?,?)";
		return insere(instrucaoSql, fornecedor.getNome(), fornecedor.getCnpj(), fornecedor.getTelefone(), fornecedor.getEmail());
	}
	
	public String getExcecao() { 
		return excecao;
	}
	
	 public List<Fornecedor> consultaFornecedores() {
	    	Fornecedor fornecedor;
	        List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
	        instrucaoSql = "SELECT * FROM FORNECEDOR";
	  
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
	        	            fornecedor = new Fornecedor();
	        	            fornecedor.setId(registros.getInt("idFornecedor"));
	        	            fornecedor.setCnpj(registros.getString("CNPJ"));
	        	            fornecedor.setTelefone(registros.getString("Telefone"));
	        	            fornecedor.setEmail(registros.getString("Email"));
	        	            fornecedor.setNome(registros.getString("Nome"));
	        	            fornecedores.add(fornecedor);
	        	        }
	        	    }
	                registros.close(); // Libera os recursos usados pelo objeto ResultSet.
	                comando.close(); // Libera os recursos usados pelo objeto PreparedStatement.
	                // Libera os recursos usados pelo objeto Connection e fecha a conex�o com o banco de dados.
	                ConnectionDatabase.getConexaoBd().close(); 
	            }
	        } catch (Exception e) {
	        	excecao = "Tipo de Exce��o: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
	        	fornecedores = null; // Caso ocorra qualquer exce��o.
	        }
	        return fornecedores; // Retorna o ArrayList de objetos Funcion�rio.
	    }
	
	    public String alteraFornecedor(Fornecedor fornecedor) {
	    	instrucaoSql = "UPDATE FORNECEDOR SET CNPJ = ?, Telefone = ?, Email = ?, Nome = ? " +
	                       "WHERE idFornecedor = ?";
	    	return insere(instrucaoSql, fornecedor.getCnpj(), fornecedor.getTelefone(), fornecedor.getEmail(), fornecedor.getNome(), fornecedor.getId());
	    }
	    
	    public String excluiFornecedor(int id) {
	    	instrucaoSql = "DELETE FROM FORNECEDOR WHERE idFornecedor = ?";
	    	return insere(instrucaoSql, id);
	    }
}
