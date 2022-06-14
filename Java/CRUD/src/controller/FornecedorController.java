package controller;

import java.util.ArrayList;
import java.util.List;

import dao.FornecedorDao;
import model.fornecedor.Fornecedor;

public class FornecedorController {
	private Fornecedor fornecedor;
	private List<String> erros;
	
	public List<String> insereFornecedor(String nome,String cnpj,String telefone,String email){
		recebeDadosUsuario(null, nome, cnpj, telefone, email);
		
		if (erros.size() == 0) 
			erros.add(new FornecedorDao().insereFornecedor(fornecedor));
		
		return erros;
	}
	
	public void recebeDadosUsuario(Integer Id, String nome,String cnpj,String telefone,String email) {
		fornecedor = new Fornecedor();
		erros= new ArrayList<>();
		
		fornecedor.setId(Id);
		fornecedor.setCnpj(cnpj);
		fornecedor.setEmail(email);
		fornecedor.setTelefone(telefone);
		fornecedor.setNome(nome);
	}
	
	public String getExcecao() {
    	return new FornecedorDao().getExcecao();
    }
	
}
