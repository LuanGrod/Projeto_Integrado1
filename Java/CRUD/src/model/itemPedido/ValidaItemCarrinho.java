package model.itemPedido;

import java.util.ArrayList;
import java.util.List;

import controller.CarrinhoController;
import controller.ProdutoController;
import model.produto.Produto;

public class ValidaItemCarrinho {
	private static List<String> errosValidacao;
	private static List<ItemCarrinho> ics;

	public static List<String> validaItemCarrinho(ItemCarrinho ic) {
		errosValidacao = new ArrayList<>();

		ics = new CarrinhoController().consultaItemCarrinho();

		for (ItemCarrinho it : ics) {
			if (ic.getProduto().getId() == it.getProduto().getId()) {
				errosValidacao.add("Produto já adicionado. Para alterar a quantidade, acesse o carrinho.");
			}
		}

		Produto produto = new ProdutoController().consultaProdutoById(ic.getProduto().getId());
		if (produto.getQtdEstoque() < ic.getQuantidade())
			errosValidacao.add("* Estoque insuficiente/" + "Total em estoque: " + produto.getQtdEstoque());

		return errosValidacao;
	}

	public static String validaQuantidade(int id, int qntd) {
		Produto produto = new ProdutoController().consultaProdutoById(id);

		if (produto.getQtdEstoque() < qntd)
			return "* Estoque insuficiente/" + "Total em estoque: " + produto.getQtdEstoque();

		return null;
	}
}
