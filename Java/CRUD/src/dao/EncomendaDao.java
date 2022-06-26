package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.encomenda.Encomenda;
import model.fornecedor.Fornecedor;
import model.produto.Produto;

public class EncomendaDao extends GenericDao{
	private String instrucaoSql; // Atributo para armazenar a instrução SQL a ser executada.
	private static String excecao = null; 
	private PreparedStatement comando; 
	private ResultSet registros;
	
	public String insereEncomenda(Encomenda encomenda) {
		instrucaoSql = "INSERT INTO ENCOMENDA (Produto_idProduto, Fornecedor_idFornecedor, quantidade, situacao) VALUES (?,?,?,?)";
		return insere(instrucaoSql, encomenda.getProduto().getId(), encomenda.getFornecedor().getId(), encomenda.getQuantidade(), encomenda.getSituacao());
	}
	
	public List<Produto> consultaProdutos(){
		Produto produto;
		List<Produto> produtos = new ArrayList<>();
		instrucaoSql = "SELECT idProduto, nome FROM PRODUTO";
		
		try {
			excecao = ConnectionDatabase.conectaBd();
			if(excecao == null) {
				comando = ConnectionDatabase.getConexaoBd().prepareStatement(instrucaoSql);
                registros = comando.executeQuery();
                
                while (registros.next()) {
                	produto = new Produto();
                	produto.setId(registros.getInt("idProduto"));
                	produto.setNome(registros.getString("nome"));
              
                	produtos.add(produto);
                }
                
			}
		}catch(Exception e) {
			excecao = "Tipo de Exceção: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
	    	produtos = null; 
		}
		return produtos;
	}
	
	public List<Fornecedor> consultaFornecedores(){
		Fornecedor fornecedor;
		List<Fornecedor> fornecedores = new ArrayList<>();
		instrucaoSql = "SELECT idFornecedor, nome FROM FORNECEDOR";
		
		try {
			excecao = ConnectionDatabase.conectaBd();
			if(excecao == null) {
				comando = ConnectionDatabase.getConexaoBd().prepareStatement(instrucaoSql);
                registros = comando.executeQuery();
               
                while (registros.next()) {
                	fornecedor = new Fornecedor();
                	fornecedor.setId(registros.getInt("idFornecedor"));
                	fornecedor.setNome(registros.getString("nome"));
                	
                	fornecedores.add(fornecedor);
                }
               
			}
		}catch(Exception e) {
			excecao = "Tipo de Exceção: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
			fornecedores = null; 
		}
		return fornecedores;
	}
	
	public String getExcecao() { 
		return excecao;
	}
	
	   public List<Encomenda> consultaEncomenda() {
	    	Encomenda encomenda;
	        Produto produto;
	        Fornecedor fornecedor;
	        List<Encomenda> encomendas = new ArrayList<Encomenda>();
	        instrucaoSql = "SELECT * FROM ENCOMENDA";
	  
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
	        	        	encomenda = new Encomenda();
	        	        	encomenda.setId(registros.getInt("id"));
	        	        	
	        	            // Atribui o id do cargo ao objeto Cargo por meio do método set.
	        	            produto = new Produto();
	        	            produto.setId(registros.getInt("Produto_idProduto"));
	        	            encomenda.setProduto(produto);
	        	            
	        	            // Atribui o id do cargo ao objeto Cargo por meio do método set.
	        	            fornecedor = new Fornecedor();
	        	            fornecedor.setId(registros.getInt("Fornecedor_idFornecedor"));
	        	            encomenda.setFornecedor(fornecedor);
	        	            
	        	            encomenda.setSituacao(registros.getString("Situacao"));
	        	            encomendas.add(encomenda);
	        	        }
	        	    }
	                registros.close(); // Libera os recursos usados pelo objeto ResultSet.
	                comando.close(); // Libera os recursos usados pelo objeto PreparedStatement.
	                // Libera os recursos usados pelo objeto Connection e fecha a conexão com o banco de dados.
	                ConnectionDatabase.getConexaoBd().close(); 
	            }
	        } catch (Exception e) {
	        	excecao = "Tipo de Exceção: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
	        	encomendas = null; // Caso ocorra qualquer exceção.
	        }
	        return encomendas; // Retorna o ArrayList de objetos Funcionário.
	    }


	    public String alteraEncomenda(Encomenda encomenda) {
	    	instrucaoSql = "UPDATE ENCOMENDA SET Produto_idProduto = ?, Fornecedor_idFornecedor = ?, Quantidade = ? " +
	                       "WHERE id = ?";
	    	return insere(instrucaoSql, encomenda.getProduto().getId(), encomenda.getFornecedor().getId(), 
	    								encomenda.getQuantidade(), encomenda.getId());
	    }
	    
	    public String excluiEncomenda(int id) {
	    	instrucaoSql = "DELETE FROM ENCOMENDA WHERE id = ?";
	    	return insere(instrucaoSql, id);
	    }

}
