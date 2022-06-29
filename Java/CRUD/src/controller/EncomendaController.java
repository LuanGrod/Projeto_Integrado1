package controller;

import java.util.ArrayList;
import java.util.List;

import dao.EncomendaDao;
import model.encomenda.Encomenda;
import model.fornecedor.Fornecedor;
import model.produto.Produto;

public class EncomendaController {
	
	private Encomenda encomenda;
	private List<String> erros;
	
	public List<String> insereEncomenda(Produto idProduto, Fornecedor idForncedor, int quantidade){
		recebeDadosEncomenda(null, idProduto, idForncedor, quantidade);
		
		new EncomendaDao().insereEncomenda(encomenda);
		
		return erros;
	}
	
	public void recebeDadosEncomenda(Integer id, Produto idProduto, Fornecedor idForncedor, int quantidade) {
		encomenda = new Encomenda();
		erros = new ArrayList<>();
		
		encomenda.setId(id);
		encomenda.setProduto(idProduto);
		encomenda.setFornecedor(idForncedor);
		encomenda.setQuantidade(quantidade);
		encomenda.setSituacao("Em aberto");
	}
	
	public List<Produto> consultaProdutos(){
		return new EncomendaDao().consultaProdutos();
	}
	
	public List<Fornecedor> consultaFornecedores(){
		return new EncomendaDao().consultaFornecedores();
	}
	
	public String getExcecao() {
    	return new EncomendaDao().getExcecao();
    }
	
    public List<Encomenda> consultaEncomenda() {
		return new EncomendaDao().consultaEncomenda();
    }
    
    public List<String> alteraEncomenda(Integer id, Produto idProduto, Fornecedor idForncedor, int quantidade) {
    	recebeDadosEncomenda(id, idProduto, idForncedor, quantidade);
    	
		// Se nenhum erro de validação for encontrado, tenta inserir o funcionário no banco.
		if (erros.size() == 0)
			erros.add(new EncomendaDao().alteraEncomenda(encomenda));
		
		// Retorna o ArrayList contendo:
		// - Em caso de sucesso: null na 1ª posição; OU
		// - Em caso de exceção: uma mensagem de exceção na 1ª posição; OU
		// - Em caso de erro de validação: mensagens de erro iniciando na 1ª posição.
		return erros;
    }

    public String excluiEncomenda(Integer id) {
    	String erro = new EncomendaDao().excluiEncomenda(id);
        return erro;
    }
}
