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
		instrucaoSql = "INSERT INTO USUARIO (login, senha, cpf, nome, telefone, email, rua, bairro, cidade, cep, estado, cargo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		return insere(instrucaoSql, usuario.getLogin(), usuario.getSenha(),usuario.getCpf(), usuario.getNome(), usuario.getTelefone(), usuario.getEmail(),
				usuario.getRua(), usuario.getBairro(), usuario.getCidade(), usuario.getCep(),  usuario.getCargo().getId());
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
	
	public String getExcecao() { 
		return excecao;
	}




}

	
