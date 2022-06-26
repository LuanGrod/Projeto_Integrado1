package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.encomenda.Encomenda;
import model.fornecedor.Fornecedor;
import model.produto.Produto;

public class EncomendaDao extends GenericDao{
	private String instrucaoSql; // Atributo para armazenar a instru��o SQL a ser executada.
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
			excecao = "Tipo de Exce��o: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
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
			excecao = "Tipo de Exce��o: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
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
	        	        	encomenda = new Encomenda();
	        	        	encomenda.setId(registros.getInt("id"));
	        	        	
	        	            // Atribui o id do cargo ao objeto Cargo por meio do m�todo set.
	        	            produto = new Produto();
	        	            produto.setId(registros.getInt("Produto_idProduto"));
	        	            encomenda.setProduto(produto);
	        	            
	        	            // Atribui o id do cargo ao objeto Cargo por meio do m�todo set.
	        	            fornecedor = new Fornecedor();
	        	            fornecedor.setId(registros.getInt("Fornecedor_idFornecedor"));
	        	            encomenda.setFornecedor(fornecedor);
	        	            
	        	            encomenda.setSituacao(registros.getString("Situacao"));
	        	            encomendas.add(encomenda);
	        	        }
	        	    }
	                registros.close(); // Libera os recursos usados pelo objeto ResultSet.
	                comando.close(); // Libera os recursos usados pelo objeto PreparedStatement.
	                // Libera os recursos usados pelo objeto Connection e fecha a conex�o com o banco de dados.
	                ConnectionDatabase.getConexaoBd().close(); 
	            }
	        } catch (Exception e) {
	        	excecao = "Tipo de Exce��o: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
	        	encomendas = null; // Caso ocorra qualquer exce��o.
	        }
	        return encomendas; // Retorna o ArrayList de objetos Funcion�rio.
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
