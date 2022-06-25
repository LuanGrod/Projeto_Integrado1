package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.categoria.Categoria;
import model.produto.Produto;

public class ProdutoDao extends GenericDao{

	private String instrucaoSql; // Atributo para armazenar a instru��o SQL a ser executada.
	private static String excecao = null; 
	private PreparedStatement comando; 
	private ResultSet registros;

	public String insereProduto(Produto produto){
		
		instrucaoSql = "INSERT INTO PRODUTO(nome, precoCusto, PrecoVenda, QntEstoque, Categoria_idCategoria) VALUES(?,?,?,?,?)";
		return insere(instrucaoSql, produto.getNome(), produto.getPrecoCusto(), produto.getPrecoVenda(), produto.getQtdEstoque(), produto.getCategoria().getId());
	}
	
	public List<Produto> consultaProdutos(){
		Produto produto;
		Categoria categoria;
		List<Produto> produtos = new ArrayList<>();
		instrucaoSql = "SELECT * FROM PRODUTO";
		
		try {
			excecao = ConnectionDatabase.conectaBd();
			if(excecao == null) {
				comando = ConnectionDatabase.getConexaoBd().prepareStatement(instrucaoSql);
                registros = comando.executeQuery();
                
                while (registros.next()) {
                	produto = new Produto();
                	produto.setNome(registros.getString("nome"));
                	produto.setPrecoCusto(registros.getDouble("precoCusto"));
                	produto.setPrecoVenda(registros.getDouble("precoVenda"));
                	produto.setQtdEstoque(registros.getInt("QntEstoque")); 
                	
                	categoria = new Categoria();
                	categoria.setId(registros.getInt("Categoria_idCategoria"));
                	produto.setCategoria(categoria);
                	produtos.add(produto);
                }
                
			}
		}catch(Exception e) {
			excecao = "Tipo de Exce��o: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
	    	produtos = null; 
		}
		return produtos;
	}
	
	public List<Categoria> recuperaCategorias(){
		Categoria categoria;
		List<Categoria> categorias = new ArrayList<>();
		instrucaoSql = "SELECT * FROM CATEGORIA";
		
		try {
			excecao = ConnectionDatabase.conectaBd();
			if (excecao == null) {
                comando = ConnectionDatabase.getConexaoBd().prepareStatement(instrucaoSql);
                registros = comando.executeQuery();
                
                while (registros.next()) {
                    categoria = new Categoria();
                    categoria.setId(registros.getInt("idCategoria"));
                    categoria.setDescricao(registros.getString("Descricao"));
                    categorias.add(categoria);
                    
    	      }
                registros.close(); 
                comando.close(); 
                ConnectionDatabase.getConexaoBd().close();
		}
	}catch (Exception e) {
    	excecao = "Tipo de Exce��o: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
    	categorias = null; 
    }
	return categorias;
}
	
	public Produto consultaProdutoById(int id) {
		Produto produto = null;
		instrucaoSql = "SELECT * FROM PRODUTO WHERE idProduto = ?";
		
		try {
			excecao = ConnectionDatabase.conectaBd();
			if (excecao == null) {
                comando = ConnectionDatabase.getConexaoBd().prepareStatement(instrucaoSql);
                comando.setObject(1, id);
                registros = comando.executeQuery();
                
                if(registros.next()) {
                	produto = new Produto();
                	produto.setId(id);
                	produto.setNome(registros.getString("nome"));
                	produto.setPrecoVenda(registros.getDouble("PrecoVenda"));
                	produto.setQtdEstoque(registros.getInt("QntEstoque"));
                	
                }else {
                	excecao = "Esse produto n�o existe";
                }
		}
			registros.close(); 
            comando.close(); 
            ConnectionDatabase.getConexaoBd().close();
            
	}catch (Exception e) {
    	excecao = "Tipo de Exce��o: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
    	produto = null; 
	}
		return produto;
	}
	
	public String getExcecao() { 
		return excecao;
	}

	//recupera 2.0
	 public List<Categoria> selectCategorias() {
		Categoria categoria;
        List<Categoria> categorias = new ArrayList<Categoria>();
        instrucaoSql = "SELECT * FROM CATEGORIA";
        
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
                        // Atribui o Id e a Descri��o do cargo ao objeto Cargo por meio dos m�todos set e
                        // adiciona este objeto ao ArrayList funcionarios.
        	        	categoria = new Categoria();
        	        	categoria.setId(registros.getInt("idCategoria"));
        	        	categoria.setDescricao(registros.getString("Descricao"));
        	            categorias.add(categoria);
        	        }
        	    }
                registros.close(); // Libera os recursos usados pelo objeto ResultSet.
                comando.close(); // Libera os recursos usados pelo objeto PreparedStatement.
                // Libera os recursos usados pelo objeto Connection e fecha a conex�o com o banco de dados.
                ConnectionDatabase.getConexaoBd().close(); 
            }
        } catch (Exception e) {
        	excecao = "Tipo de Exce��o: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
        	categorias = null; // Caso ocorra qualquer exce��o.
        }
        return categorias; // Retorna o ArrayList de objetos Cargo.
	 }
	    

	    public List<Produto> selectProdutos() {
	    	Produto produto;
	    	Categoria categoria;
	        List<Produto> produtos = new ArrayList<Produto>();
	        instrucaoSql = "SELECT * FROM PRODUTO";
	  
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
	        	        	produto = new Produto();
	        	        	produto.setId(registros.getInt("idProduto"));
	        	        	produto.setNome(registros.getString("Nome"));
	        	        	produto.setPrecoCusto(registros.getDouble("PrecoCusto"));
	        	        	produto.setPrecoVenda(registros.getDouble("PrecoVenda"));
	        	        	produto.setQtdEstoque(registros.getInt("QntEstoque"));
	        	        	
	        	            // Atribui o id do cargo ao objeto Cargo por meio do m�todo set.
	        	        	categoria = new Categoria();
	        	        	categoria.setId(registros.getInt("Categoria_idCategoria"));
	        	        	produto.setCategoria(categoria);
	        	        	produtos.add(produto);
	        	        }
	        	    }
	                registros.close(); // Libera os recursos usados pelo objeto ResultSet.
	                comando.close(); // Libera os recursos usados pelo objeto PreparedStatement.
	                // Libera os recursos usados pelo objeto Connection e fecha a conex�o com o banco de dados.
	                ConnectionDatabase.getConexaoBd().close(); 
	            }
	        } catch (Exception e) {
	        	excecao = "Tipo de Exce��o: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
	        	produtos = null; // Caso ocorra qualquer exce��o.
	        }
	        return produtos; // Retorna o ArrayList de objetos Funcion�rio.
	    }

	    public String alteraProduto(Produto produto) {
	    	instrucaoSql = "UPDATE PRODUTO SET Nome = ?, PrecoCusto = ?, PrecoVenda = ?, QntEstoque = ?, Categoria_idCategoria = ? " +
	                       "WHERE idProduto = ?";
	    	return insere(instrucaoSql, produto.getNome(), produto.getPrecoCusto(), produto.getPrecoVenda(), produto.getQtdEstoque(),
	    											produto.getCategoria().getId(), produto.getId());
	    }
	    
	    public String excluiProduto(int id) {
	    	instrucaoSql = "DELETE FROM PRODUTO WHERE idProduto = ?";
	    	return insere(instrucaoSql, id);
	    }
	    
}
