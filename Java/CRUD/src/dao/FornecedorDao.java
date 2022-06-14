package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.fornecedor.Fornecedor;

public class FornecedorDao extends GenericDao{
	
	private String instrucaoSql; // Atributo para armazenar a instrução SQL a ser executada.
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
	
}
