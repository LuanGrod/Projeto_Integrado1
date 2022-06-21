package controller;

import java.util.ArrayList;
import java.util.List;

import dao.CarrinhoDao;
import model.itemPedido.ItemCarrinho;
import model.produto.Produto;

public class CarrinhoController {

	private List<String> erros;
	
	
	public List<String> insereItemCarrinho(Integer qntd, Produto produto) {
		erros = new ArrayList<>();
		ItemCarrinho carrinho = new ItemCarrinho();
		carrinho.setQuantidade(qntd);
		carrinho.setProduto(produto);
		erros.add(new CarrinhoDao().InsereItemCarrinho(carrinho));
			
		return erros;
	}
	
	public List<Produto> consultaProdutosCarrinho(){
		return new CarrinhoDao().consultaProdutosCarrinho();
	}
	
	public List<ItemCarrinho> consultaItemCarrinho(){
		return new CarrinhoDao().consultaItemCarrinho();
	}
	
}
