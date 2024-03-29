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
    
    public Usuario getUsuarioAtual() {
    	return new UsuarioDao().getUsuarioAtual();
    }

    public String getExcecao() {
    	// Retorna a exce��o lan�ada ao recuperar os cargos (ao abrir a tela "Cadastro de Funcion�rio").
    	return new UsuarioDao().getExcecao();
    }
    
    //recupera 2.0
    public List<Usuario> selectUsuarios() {
		return new UsuarioDao().selectUsuarios();
    }

    public List<Cargo> selectCargos() {
    	// Recupera os cargos cadastrados no banco de dados para que sejam carregados no JComboBox Cargo.
		return new UsuarioDao().selectCargos();		
    }
    
//    altera e exclui
    public List<String> alteraUsuario(Integer id, String login, String senha, String cpf, String nome, String telefone, String email, String rua, String bairro, String cidade, String cep, String estado, Cargo cargo) {
    	recebeDadosUsuario(id, login, senha, cpf, nome, telefone, email, rua, bairro, cidade, cep, estado, cargo);
    	
		// Se nenhum erro de valida��o for encontrado, tenta inserir o funcion�rio no banco.
		if (erros.size() == 0)
			erros.add(new UsuarioDao().alteraUsuario(usuario));
		
		// Retorna o ArrayList contendo:
		// - Em caso de sucesso: null na 1� posi��o; OU
		// - Em caso de exce��o: uma mensagem de exce��o na 1� posi��o; OU
		// - Em caso de erro de valida��o: mensagens de erro iniciando na 1� posi��o.
		return erros;
    }

    public String excluiUsuario(Integer id) {
    	String erro = new UsuarioDao().excluiUsuario(id);
        return erro;
    }
}
