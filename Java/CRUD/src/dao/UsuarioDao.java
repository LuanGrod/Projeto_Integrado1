package dao;

import java.sql.ResultSet;
import model.usuario.Usuario;

public class UsuarioDao extends GenericDao{
	private String instrucaoSql; // Atributo para armazenar a instrução SQL a ser executada.

	public String insereUsuario(Usuario usuario) {
		instrucaoSql = "INSERT INTO USUARIO (login, senha, pessoa_cpf, cargo_idcargo) VALUES (?,?,?,?)";
		return insere(instrucaoSql, usuario.getLogin(), usuario.getSenha(), usuario.getPessoa().getCpf(), usuario.getCargo().getId());
	}

	
	/**
	 * Método usado para consultar o banco e verificar se os dados do usuario(login e senha) usados como parametro existem
	 * @param usuario objeto recebido pelo método validaLogin da classe controller
	 * @return os dados da consulta (ResultSet)
	 */
	public ResultSet validaLogin(Usuario usuario) {
		instrucaoSql = "SELECT login senha FROM usuario WHERE login = ? AND senha = ?";
		return consulta(instrucaoSql, usuario.getLogin(), usuario.getSenha());
	}

}
