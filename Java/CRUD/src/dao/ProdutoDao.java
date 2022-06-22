package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.categoria.Categoria;
import model.produto.Produto;

public class ProdutoDao extends GenericDao{

	private String instrucaoSql; // Atributo para armazenar a instrução SQL a ser executada.
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
			excecao = "Tipo de Exceção: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
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
    	excecao = "Tipo de Exceção: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
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
                	excecao = "Esse produto não existe";
                }
		}
			registros.close(); 
            comando.close(); 
            ConnectionDatabase.getConexaoBd().close();
            
	}catch (Exception e) {
    	excecao = "Tipo de Exceção: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
    	produto = null; 
	}
		return produto;
	}
	
	public String getExcecao() { 
		return excecao;
	}


}
