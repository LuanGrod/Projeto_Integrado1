package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.itemPedido.ItemCarrinho;
import model.produto.Produto;

public class CarrinhoDao extends GenericDao{
	
	private String instrucaoSql; // Atributo para armazenar a instrução SQL a ser executada.
	private static String excecao = null; 
	private PreparedStatement comando; 
	private ResultSet registros;
	
	public String InsereItemCarrinho(ItemCarrinho carrinho) {
        instrucaoSql = "INSERT INTO CARRINHO VALUES(?, ?)";
        return insere(instrucaoSql, carrinho.getQuantidade(), carrinho.getProduto().getId());          
	}
	
	public String LimpaCarrinho() {
		instrucaoSql = "truncate table carrinho";
		return insere(instrucaoSql);
		
	}
	
	public List<Produto> consultaProdutosCarrinho() {
		Produto produto;
		List<Produto> produtos = new ArrayList<>();
		instrucaoSql = "SELECT * FROM PRODUTO, CARRINHO WHERE PRODUTO.IDPRODUTO = CARRINHO.Produto_idProduto";
		
		
		try {
			excecao = ConnectionDatabase.conectaBd();
			if(excecao == null) {
				comando = ConnectionDatabase.getConexaoBd().prepareStatement(instrucaoSql);
                registros = comando.executeQuery();
                
                while (registros.next()) {
                	produto = new Produto();
                	produto.setId(registros.getInt("idProduto"));
                	produto.setPrecoVenda(registros.getDouble("PrecoVenda"));
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
	

	public List<ItemCarrinho> consultaItemCarrinho(){
		ItemCarrinho ic;
		Produto produto;
		List<ItemCarrinho> ics = new ArrayList<>();
		instrucaoSql = "SELECT * FROM CARRINHO";
		
		try {
			excecao = ConnectionDatabase.conectaBd();
			if(excecao == null) {
				comando = ConnectionDatabase.getConexaoBd().prepareStatement(instrucaoSql);
                registros = comando.executeQuery();
                
                while (registros.next()) {
                	ic = new ItemCarrinho();
                	ic.setQuantidade(registros.getInt("Quantidade"));
                	produto = new Produto();
                	produto.setId(registros.getInt("Produto_idProduto"));
                	ic.setProduto(produto);
  
                	ics.add(ic);
                }
                
			}
		}catch(Exception e) {
			excecao = "Tipo de Exceção: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
	    	ics = null; 
		}
		
		return ics;
	}
	
}
