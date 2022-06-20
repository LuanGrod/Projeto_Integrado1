package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.produto.Produto;

public class CarrinhoDao extends GenericDao{
	
	private String instrucaoSql; // Atributo para armazenar a instrução SQL a ser executada.
	private static String excecao = null; 
	private PreparedStatement comando; 
	private ResultSet registros;
	
	public String InsereItemCarrinho(Integer qntd, Produto produto) {
        instrucaoSql = "INSERT INTO CARRINHO VALUES(?, ?)";
        return insere(instrucaoSql, qntd, produto.getId());          
	}
	
	public String LimpaCarrinho() {
		instrucaoSql = "delete from carrinho";
		return insere(instrucaoSql);
		
	}

	
}
