package controller;

import java.util.ArrayList;
import java.util.List;

import dao.EncomendaDao;
import dao.ProdutoDao;
import model.encomenda.Encomenda;
import model.fornecedor.Fornecedor;
import model.produto.Produto;

public class EncomendaController {
	
	private Encomenda encomenda;
	private List<String> erros;
	
	public List<String> insereEncomenda(Produto idProduto, Fornecedor idForncedor, int quantidade){
		recebeDadosUsuario(null, idProduto, idForncedor, quantidade);
		
		if(erros.size() == 0)
			erros.add(new EncomendaDao().insereEncomenda(encomenda));
		
		return erros;
	}
	
	public void recebeDadosUsuario(Integer id, Produto idProduto, Fornecedor idForncedor, int quantidade) {
		encomenda = new Encomenda();
		erros = new ArrayList<>();
		
		encomenda.setId(id);
		encomenda.setProduto(idProduto);
		encomenda.setFornecedor(idForncedor);
		encomenda.setQuantidade(quantidade);
		encomenda.setSituacao("Em aberto");
		//erros = EncomendaValidacao().validaEncomenda();
	}
	
	public List<Produto> consultaProdutos(){
		return new EncomendaDao().consultaProdutos();
	}
	
	public List<Fornecedor> consultaFornecedores(){
		return new EncomendaDao().consultaFornecedores();
	}
	
	public String getExcecao() {
    	return new ProdutoDao().getExcecao();
    }
}
