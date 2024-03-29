package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.cliente.Cliente;
import model.itemPedido.ItemCarrinho;
import model.produto.Produto;

public class CarrinhoDao extends GenericDao{
	
	private String instrucaoSql; // Atributo para armazenar a instru��o SQL a ser executada.
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
			excecao = "Tipo de Exce��o: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
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
			excecao = "Tipo de Exce��o: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
	    	ics = null; 
		}
		
		return ics;
	}

	public String excluiProdutoCarrinho(int idProd) {
		instrucaoSql = "DELETE FROM CARRINHO WHERE Produto_idProduto = ?";
		return insere(instrucaoSql, idProd);
	}
	
	public String alteraQntdCarrinho(int qntd, int id) {
		instrucaoSql = "UPDATE CARRINHO SET Quantidade = ? WHERE Produto_idProduto = ?";
		return insere(instrucaoSql, qntd, id);
		
	}
	
	
	public Cliente buscaClienteByCpf(String cpf) {
		instrucaoSql = "SELECT NOME, idCliente FROM CLIENTE WHERE CPF = ?";
		Cliente cliente = null;
		try {
			excecao = ConnectionDatabase.conectaBd();
			if(excecao == null) {
		
				comando = ConnectionDatabase.getConexaoBd().prepareStatement(instrucaoSql);
				comando.setObject(1, cpf);
                registros = comando.executeQuery();
                
                while (registros.next()) {
                	cliente = new Cliente();
                	cliente.setNome(registros.getString("Nome"));
                	cliente.setId(registros.getInt("idCliente"));
                } 
			}
		}catch(Exception e) {
			excecao = "Tipo de Exce��o: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
		}
		return cliente;
	}
	
	public String getExcecao() {
		return excecao;
	}

	
}
