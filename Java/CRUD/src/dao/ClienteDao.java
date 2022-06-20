package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.cliente.Cliente;

public class ClienteDao extends GenericDao{
	private String instrucaoSql; // Atributo para armazenar a instrução SQL a ser executada.
	private static String excecao = null; 
	private PreparedStatement comando; 
	private ResultSet registros;
	
	public String insereCliente(Cliente cliente) {
		instrucaoSql = "INSERT INTO CLIENTE (nome, cpf, telefone, email, rua, bairro, cidade, cep, estado) VALUES(?,?,?,?,?,?,?,?,?)";
		return insere(instrucaoSql, cliente.getNome(), cliente.getCpf(), cliente.getTelefone(), cliente.getEmail(), 
				cliente.getRua(), cliente.getBairro(), cliente.getCidade(), cliente.getCep(), cliente.getEstado());
	}
	
	public Cliente findClienteByCpf() {
		Cliente cliente = new Cliente();
		return cliente;
	}
	
	public String getExcecao() { 
		return excecao;
	}
	
}
