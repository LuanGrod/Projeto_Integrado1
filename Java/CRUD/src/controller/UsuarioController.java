package controller;

import java.util.ArrayList;
import java.util.List;

import dao.UsuarioDao;
import model.cargo.Cargo;
import model.usuario.Usuario; 

public class UsuarioController {
	private Usuario usuario;
	private List<String> erros;

	
    public List<String> insereUsuario(String login, String senha, String cpf, String nome, String telefone, String email, String rua, String bairro, String cidade, String cep, String estado, Cargo cargo) {
    	recebeDadosUsuario(null, login, senha, cpf, nome, telefone, email, rua, bairro, cidade, cep, estado, cargo);

		if (erros.size() == 0) 
			erros.add(new UsuarioDao().insereUsuario(usuario));


		return erros; 
    }

    public void recebeDadosUsuario(Integer id, String login, String senha, String cpf, String nome, String telefone, String email, String rua, String bairro, String cidade, String cep, String estado, Cargo cargo) {
    	usuario = new Usuario(null, login, senha, cpf, nome, telefone, email, rua, bairro, cidade, cep, estado, cargo);
    	erros = new ArrayList<String>();
	
		//erros = UsuarioValidacao.validaUsuario(usuario);
    	erros = null;
    }

 

    public String getExcecao() {
    	// Retorna a exceção lançada ao recuperar os cargos (ao abrir a tela "Cadastro de Funcionário").
    	return new UsuarioDao().getExcecao();
    }
	 

}
