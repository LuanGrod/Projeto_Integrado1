package controller;

import java.sql.ResultSet;
import dao.UsuarioDao;
import model.usuario.Usuario;

public class UsuarioController {
	private Usuario usuario;

	/**
	 * Transforma os atributos recebidos como parametro pelo TextField da TelaLogin em um objeto Usuario
	 * @param login recebido pelo tfUsuario
	 * @param senharecebido pelo pfSenha
	 * @return os dados da consulta (ResultSet)
	 */
	public ResultSet validaLogin(String login, String senha){
		usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setSenha(senha);
		
		ResultSet rs = new UsuarioDao().validaLogin(usuario);
		return rs;
	}
	
	
	
	
	
	
	
	
	/*
    public List<String> insereUsuario(String login,String senha, Pessoa pessoa, Cargo cargo) {
    	recebeDadosUsuario(null, login, senha, pessoa, cargo);

		// Se nenhum erro de validação for encontrado, tenta inserir o funcionário no banco.
		if (erros.size() == 0) 
			erros.add(new UsuarioDao().insereUsuario(usuario));

		// Retorna o ArrayList contendo:
		// - Em caso de sucesso: null na 1ª posição; OU
		// - Em caso de exceção: uma mensagem de exceção na 1ª posição; OU
		// - Em caso de erro de validação: mensagens de erro iniciando na 1ª posição.
		return erros; 
    }

    // Método usado pelas operações de inserção e alteração de funcionário.
    public void recebeDadosUsuario(int id, String login,String senha, Pessoa pessoa, Cargo cargo) {
    	usuario = new Usuario();
    	erros = new ArrayList<String>();

		// Os métodos set abaixo criam um objeto Funcionario contendo os dados do funcionário informado.
		// Este objeto será enviado à classe DAO, que fará a inserção de seus dados no banco.
    	usuario.setId(id);
    	usuario.setLogin(login);
    	usuario.setSenha(senha);
    	usuario.setPessoa(pessoa);
    	usuario.setCargo(cargo);

		// Retorna um ArrayList contendo os erros encontrados em regras de validação e de negócios.
		erros = UsuarioValidacao.validaUsuario(usuario);
    }

    public List<Cargo> recuperaCargos() {
    	// Recupera os cargos cadastrados no banco de dados para que sejam carregados no JComboBox Cargo.
		return new FuncionarioDao().recuperaCargos();		
    }


    public String getExcecao() {
    	// Retorna a exceção lançada ao recuperar os cargos (ao abrir a tela "Cadastro de Funcionário").
    	return new FuncionarioDao().getExcecao();
    }
	 */

}
