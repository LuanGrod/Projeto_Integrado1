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

}
