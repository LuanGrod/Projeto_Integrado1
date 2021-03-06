package controller;

import java.util.ArrayList;
import java.util.List;

import dao.CarrinhoDao;
import model.cliente.Cliente;
import model.itemPedido.ItemCarrinho;
import model.itemPedido.ValidaItemCarrinho;
import model.produto.Produto;

public class CarrinhoController {

	private List<String> erros;
	
	
	public List<String> insereItemCarrinho(Integer qntd, Produto produto) {
		erros = new ArrayList<>();
		ItemCarrinho carrinho = new ItemCarrinho();
		carrinho.setQuantidade(qntd);
		carrinho.setProduto(produto);
		erros = ValidaItemCarrinho.validaItemCarrinho(carrinho);
		
		if(erros.size() == 0)
			erros.add(new CarrinhoDao().InsereItemCarrinho(carrinho));
			
		return erros;
	}
	
	public List<Produto> consultaProdutosCarrinho(){
		return new CarrinhoDao().consultaProdutosCarrinho();
	}
	
	public List<ItemCarrinho> consultaItemCarrinho(){
		return new CarrinhoDao().consultaItemCarrinho();
	}

	public String excluiProdutoCarrinho(int idProd) {
		return new CarrinhoDao().excluiProdutoCarrinho(idProd);
	}
	
	public String alteraQntdCarrinho(int qntd, int id) {
		String erro = null;
		erro = ValidaItemCarrinho.validaQuantidade(id, qntd);
		if(erro == null)
			return new CarrinhoDao().alteraQntdCarrinho(qntd, id);
		return erro;
	}
	
	 
	 public Cliente buscaClienteByCpf(String cpf) {
		 return new CarrinhoDao().buscaClienteByCpf(cpf);
	 }
	 
	 public String getExcecao() {
		    return new CarrinhoDao().getExcecao();
	}
		 
}
