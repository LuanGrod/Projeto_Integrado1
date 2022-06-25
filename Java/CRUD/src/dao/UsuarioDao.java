package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.cargo.Cargo;
import model.usuario.Usuario;

public class UsuarioDao extends GenericDao{
	private String instrucaoSql; // Atributo para armazenar a instrução SQL a ser executada.
	private static String excecao = null; 
	private PreparedStatement comando; 
	private ResultSet registros;

	public String insereUsuario(Usuario usuario) {
		instrucaoSql = "INSERT INTO USUARIO (login, senha, cpf, nome, telefone, email, rua, bairro, cidade, cep, estado, cargo_idcargo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		return insere(instrucaoSql, usuario.getLogin(), usuario.getSenha(),usuario.getCpf(), usuario.getNome(), usuario.getTelefone(), usuario.getEmail(),
				usuario.getRua(), usuario.getBairro(), usuario.getCidade(), usuario.getCep(), usuario.getEstado(),  usuario.getCargo().getId());
	}

	
	public List<Usuario> consultaUsuarios(){
		Usuario usuario;
		Cargo cargo;
		List<Usuario> usuarios = new ArrayList<Usuario>();
		instrucaoSql = "SELECT * FROM USUARIO";
		
		try {
			excecao = ConnectionDatabase.conectaBd();
			if(excecao == null) {
				comando = ConnectionDatabase.getConexaoBd().prepareStatement(instrucaoSql);
				registros = comando.executeQuery();
				
				while(registros.next()) {
					usuario = new Usuario();
					usuario.setId(registros.getInt("Idusuario"));
					usuario.setLogin(registros.getString("Login"));
					
					cargo = new Cargo();
    	            cargo.setId(registros.getInt("Cargo_IdCargo"));
    	            usuario.setCargo(cargo);
    	            usuarios.add(usuario);
					
				}
				ConnectionDatabase.getConexaoBd().close();
			}
		}catch (Exception e) {
        	excecao = "Tipo de Exceção: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
        	usuarios = null;
	}
		return usuarios;
	}
	
	public Usuario getUsuarioAtual() {
		Usuario usuario = new Usuario();
		usuario.setId(new LoginDao().getIdUsuario());
		return usuario;
	}
	
	public List<Cargo> recuperaCargos(){
		 Cargo cargo;
	     List<Cargo> cargos = new ArrayList<Cargo>();
	     instrucaoSql = "SELECT * FROM CARGO WHERE idCargo > ?";
	     
	       try {
	        	excecao = ConnectionDatabase.conectaBd(); // Abre a conexão com o banco de dados.
	        	if (excecao == null) {
	                // Obtém os dados de conexão com o banco de dados e prepara a instrução SQL.
	                comando = ConnectionDatabase.getConexaoBd().prepareStatement(instrucaoSql);
	                comando.setObject(1, new LoginDao().getCargo());
	                // Executa a instrução SQL e retorna os dados ao objeto ResultSet.
	                registros = comando.executeQuery();
	     
	                
	        	      while (registros.next()) {
	                        // Atribui o Id e a Descrição do cargo ao objeto Cargo por meio dos métodos set e
	                        // adiciona este objeto ao ArrayList funcionarios.
	        	           cargo = new Cargo();
	        	           cargo.setId(registros.getInt("idCargo"));
	        	           cargo.setDescricao(registros.getString("Descricao"));
	        	           cargos.add(cargo);
	        	      }
	        	    
	                registros.close(); // Libera os recursos usados pelo objeto ResultSet.
	                comando.close(); // Libera os recursos usados pelo objeto PreparedStatement.
	                // Libera os recursos usados pelo objeto Connection e fecha a conexão com o banco de dados.
	                ConnectionDatabase.getConexaoBd().close(); 
	            }
	        } catch (Exception e) {
	        	excecao = "Tipo de Exceção: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
	        	cargos = null; // Caso ocorra qualquer exceção.
	        }
	        return cargos; // Retorna o ArrayList de objetos Cargo.
	}
	
	public String getExcecao() { 
		return excecao;
	}

	//recupera 2.0
	 public List<Cargo> selectCargos() {
	        Cargo cargo;
	        List<Cargo> cargos = new ArrayList<Cargo>();
	        instrucaoSql = "SELECT * FROM CARGO";
	        
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
	                        // Atribui o Id e a Descrição do cargo ao objeto Cargo por meio dos métodos set e
	                        // adiciona este objeto ao ArrayList funcionarios.
	        	            cargo = new Cargo();
	        	            cargo.setId(registros.getInt("idCargo"));
	        	            cargo.setDescricao(registros.getString("Descricao"));
	        	            cargo.setDepartamento(registros.getString("Departamento"));
	        	            cargo.setSalario(registros.getDouble("Salario"));
	        	            cargos.add(cargo);
	        	        }
	        	    }
	                registros.close(); // Libera os recursos usados pelo objeto ResultSet.
	                comando.close(); // Libera os recursos usados pelo objeto PreparedStatement.
	                // Libera os recursos usados pelo objeto Connection e fecha a conexão com o banco de dados.
	                ConnectionDatabase.getConexaoBd().close(); 
	            }
	        } catch (Exception e) {
	        	excecao = "Tipo de Exceção: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
	        	cargos = null; // Caso ocorra qualquer exceção.
	        }
	        return cargos; // Retorna o ArrayList de objetos Cargo.
	    }
	    

	    public List<Usuario> selectUsuarios() {
	    	Usuario usuario;
	        Cargo cargo;
	        List<Usuario> usuarios = new ArrayList<Usuario>();
	        instrucaoSql = "SELECT * FROM USUARIO";
	  
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
	        	            usuario = new Usuario();
	        	            usuario.setId(registros.getInt("idUsuario"));
	        	            usuario.setLogin(registros.getString("Login"));
	        	            usuario.setSenha(registros.getString("Senha"));
	        	            usuario.setCpf(registros.getString("CPF"));
	        	            usuario.setNome(registros.getString("Nome"));
	        	            usuario.setTelefone(registros.getString("Telefone"));
	        	            usuario.setEmail(registros.getString("Email"));
	        	            usuario.setRua(registros.getString("Rua"));
	        	            usuario.setBairro(registros.getString("Bairro"));
	        	            usuario.setCidade(registros.getString("Cidade"));
	        	            usuario.setCep(registros.getString("CEP"));
	        	            usuario.setEstado(registros.getString("Estado"));
	        	            
	        	            // Atribui o id do cargo ao objeto Cargo por meio do método set.
	        	            cargo = new Cargo();
	        	            cargo.setId(registros.getInt("Cargo_idCargo"));
	        	            usuario.setCargo(cargo);
	        	            usuarios.add(usuario);
	        	        }
	        	    }
	                registros.close(); // Libera os recursos usados pelo objeto ResultSet.
	                comando.close(); // Libera os recursos usados pelo objeto PreparedStatement.
	                // Libera os recursos usados pelo objeto Connection e fecha a conexão com o banco de dados.
	                ConnectionDatabase.getConexaoBd().close(); 
	            }
	        } catch (Exception e) {
	        	excecao = "Tipo de Exceção: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
	        	usuarios = null; // Caso ocorra qualquer exceção.
	        }
	        return usuarios; // Retorna o ArrayList de objetos Funcionário.
	    }


	    public String alteraUsuario(Usuario usuario) {
	    	instrucaoSql = "UPDATE USUARIO SET Login = ?, Senha = ?, CPF = ?, Nome = ?, Telefone = ?, Email = ?, Rua = ?, Bairro = ?, Cidade = ?, CEP = ?, Estado = ?, Cargo_idCargo = ? " +
	                       "WHERE idUsuario = ?";
	    	return insere(instrucaoSql, usuario.getLogin(), usuario.getSenha(), usuario.getCpf(), usuario.getNome(), usuario.getTelefone(), usuario.getEmail(), usuario.getRua(),
	        		                               usuario.getBairro(), usuario.getCidade(), usuario.getCep(), usuario.getEstado(), usuario.getCargo().getId(), usuario.getId());
	    }
	    
	    public String excluiUsuario(int id) {
	    	instrucaoSql = "DELETE FROM USUARIO WHERE idUsuario = ?";
	    	return insere(instrucaoSql, id);
	    }
}

	
