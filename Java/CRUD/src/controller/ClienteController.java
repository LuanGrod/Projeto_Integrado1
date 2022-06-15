package controller;

import java.util.ArrayList;
import java.util.List;

import dao.ClienteDao;
import model.cliente.Cliente;


public class ClienteController {
	private Cliente cliente;
	private List<String> erros;
	
	public List<String> insereCliente(String nome,String  cpf, String telefone, String email, String rua, String bairro, String cidade, String cep, String estado ){
		recebeDadosUsuario(null, nome, cpf, telefone, email, rua, bairro, cidade, cep, estado);
		
		if (erros.size() == 0) 
			erros.add(new ClienteDao().insereCliente(cliente));
		
		return erros;
	}

	public void recebeDadosUsuario(Integer id, String nome,String  cpf, String telefone, String email, String rua, String bairro, String cidade, String cep, String estado) {
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
}
