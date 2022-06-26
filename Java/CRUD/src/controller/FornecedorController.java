package controller;

import java.util.ArrayList;
import java.util.List;

import dao.FornecedorDao;
import model.fornecedor.Fornecedor;

public class FornecedorController {
	private Fornecedor fornecedor;
	private List<String> erros;

	public List<String> insereFornecedor(String nome,String cnpj,String telefone,String email){
		recebeDadosFornecedor(null, nome, cnpj, telefone, email);

		if (erros.size() == 0) 
			erros.add(new FornecedorDao().insereFornecedor(fornecedor));

		return erros;
	}

	public void recebeDadosFornecedor(Integer Id, String nome,String cnpj,String telefone,String email) {
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

	public List<Fornecedor> consultaFornecedores() {
		return new FornecedorDao().consultaFornecedores();
	}

	public List<String> alteraFornecedor(Integer id, String  cnpj, String telefone, String email, String nome) {
		recebeDadosFornecedor(id, cnpj, telefone, email, nome);

		// Se nenhum erro de validação for encontrado, tenta inserir o funcionário no banco.
		if (erros.size() == 0)
			erros.add(new FornecedorDao().alteraFornecedor(fornecedor));

		// Retorna o ArrayList contendo:
		// - Em caso de sucesso: null na 1ª posição; OU
		// - Em caso de exceção: uma mensagem de exceção na 1ª posição; OU
		// - Em caso de erro de validação: mensagens de erro iniciando na 1ª posição.
		return erros;
	}

	public String excluiFornecedor(Integer id) {
		String erro = new FornecedorDao().excluiFornecedor(id);
		return erro;
	}
}
