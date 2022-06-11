package dao;

import java.sql.ResultSet;
import model.usuario.Usuario;

public class UsuarioDao extends GenericDao{
	private String instrucaoSql; // Atributo para armazenar a instrução SQL a ser executada.

	/*public String insereUsuario(Usuario usuario) {
		instrucaoSql = "INSERT INTO USUARIO (login, senha, pessoa_cpf, cargo_idcargo) VALUES (?,?,?,?)";
		return insere(instrucaoSql, usuario.getLogin(), usuario.getSenha(), usuario.getPessoa().getCpf(), usuario.getCargo().getId());
	}*/

	

}
