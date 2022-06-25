package controller;

import java.util.ArrayList;
import java.util.List;

import dao.ClienteDao;
import model.cliente.Cliente;


public class ClienteController {
	private Cliente cliente;
	private List<String> erros;
	
	public List<String> insereCliente(String nome,String  cpf, String telefone, String email, String rua, String bairro, String cidade, String cep, String estado ){
		recebeDadosCliente(null, nome, cpf, telefone, email, rua, bairro, cidade, cep, estado);
		
		if (erros.size() == 0) 
			erros.add(new ClienteDao().insereCliente(cliente));
		
		return erros;
	}

	public void recebeDadosCliente(Integer id, String nome,String  cpf, String telefone, String email, String rua, String bairro, String cidade, String cep, String estado) {
		cliente = new Cliente();
    	erros = new ArrayList<String>();

    	cliente.setId(id);
    	cliente.setNome(nome);
    	cliente.setCpf(cpf);
    	cliente.setTelefone(telefone);
    	cliente.setEmail(email);
    	cliente.setRua(rua);
    	cliente.setBairro(bairro);
    	cliente.setCidade(cidade);
    	cliente.setCep(cep);
    	cliente.setEstado(estado);
    	//erros =  ClienteValidacao.validaCliente(cliente);
    	
	}
	
	public String getExcecao() {
    	return new ClienteDao().getExcecao();
    }
	
    public List<Cliente> consultaClientes() {
		return new ClienteDao().consultaClientes();
    }
    
    public List<String> alteraCliente(Integer id, String nome,String  cpf, String telefone, String email, String rua, String bairro, String cidade, String cep, String estado) {
    	recebeDadosCliente(id, nome, cpf, telefone, email, rua, bairro, cidade, cep, estado);
    	
		// Se nenhum erro de validação for encontrado, tenta inserir o funcionário no banco.
		if (erros.size() == 0)
			erros.add(new ClienteDao().alteraCliente(cliente));
		
		// Retorna o ArrayList contendo:
		// - Em caso de sucesso: null na 1ª posição; OU
		// - Em caso de exceção: uma mensagem de exceção na 1ª posição; OU
		// - Em caso de erro de validação: mensagens de erro iniciando na 1ª posição.
		return erros;
    }

    public String excluiCliente(Integer id) {
    	String erro = new ClienteDao().excluiCliente(id);
        return erro;
    }
}
