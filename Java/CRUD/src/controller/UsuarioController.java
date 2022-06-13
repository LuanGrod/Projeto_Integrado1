package controller;

import java.util.ArrayList;
import java.util.List;

import dao.UsuarioDao;
import model.cargo.Cargo;
import model.usuario.Usuario;
import model.usuario.UsuarioValidacao; 

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
    	usuario = new Usuario();
    	erros = new ArrayList<String>();
    	
    	usuario.setId(id);
    	usuario.setLogin(login);
    	usuario.setSenha(senha);
    	usuario.setCpf(cpf);
    	usuario.setNome(nome);
    	usuario.setTelefone(telefone);
    	usuario.setEmail(email);
    	usuario.setRua(rua);
    	usuario.setBairro(bairro);
    	usuario.setCidade(cidade);
    	usuario.setCep(cep);
    	usuario.setEstado(estado);
    	usuario.setCargo(cargo);
		erros = UsuarioValidacao.validaUsuario(usuario);
    }
    
    public List<Usuario> consultaUsuarios() {
		return new UsuarioDao().consultaUsuarios();
    }

    public List<Cargo> recuperaCargos() {
    	// Recupera os cargos cadastrados no banco de dados para que sejam carregados no JComboBox Cargo.
		return new UsuarioDao().recuperaCargos();		
    }

    public String getExcecao() {
    	// Retorna a exceção lançada ao recuperar os cargos (ao abrir a tela "Cadastro de Funcionário").
    	return new UsuarioDao().getExcecao();
    }
	 

}
