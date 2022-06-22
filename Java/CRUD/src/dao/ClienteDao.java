package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

	 public List<Cliente> consultaClientes() {
	    	Cliente cliente;
	        List<Cliente> clientes = new ArrayList<Cliente>();
	        instrucaoSql = "SELECT * FROM CLIENTE";
	  
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
	        	            cliente = new Cliente();
	        	            cliente.setId(registros.getInt("idCliente"));
	        	            cliente.setNome(registros.getString("Nome"));
	        	            cliente.setCpf(registros.getString("CPF"));
	        	            cliente.setTelefone(registros.getString("Telefone"));
	        	            cliente.setEmail(registros.getString("Email"));
	        	            cliente.setRua(registros.getString("Rua"));
	        	            cliente.setBairro(registros.getString("Bairro"));
	        	            cliente.setCidade(registros.getString("Cidade"));
	        	            cliente.setCep(registros.getString("CEP"));
	        	            cliente.setEstado(registros.getString("Estado"));
	        	            clientes.add(cliente);
	        	        }
	        	    }
	                registros.close(); // Libera os recursos usados pelo objeto ResultSet.
	                comando.close(); // Libera os recursos usados pelo objeto PreparedStatement.
	                // Libera os recursos usados pelo objeto Connection e fecha a conexão com o banco de dados.
	                ConnectionDatabase.getConexaoBd().close(); 
	            }
	        } catch (Exception e) {
	        	excecao = "Tipo de Exceção: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
	        	clientes = null; // Caso ocorra qualquer exceção.
	        }
	        return clientes; // Retorna o ArrayList de objetos Funcionário.
	    }
	
}
