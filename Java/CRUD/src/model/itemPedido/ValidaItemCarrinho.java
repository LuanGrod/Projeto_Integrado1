package model.itemPedido;

import java.util.ArrayList;
import java.util.List;

import controller.CarrinhoController;


public class ValidaItemCarrinho {
	private static List<String> errosValidacao;
	private static List<ItemCarrinho> ics;
	
	public static List<String> validaItemCarrinho(ItemCarrinho ic){
		errosValidacao = new ArrayList<>();
		
		ics = new CarrinhoController().consultaItemCarrinho();
		
		for (ItemCarrinho it : ics) {
			if(ic.getProduto().getId() == it.getProduto().getId()) {
				errosValidacao.add("Produto já adicionado. Para alterar a quantidade, acesse o carrinho.");
			}
		}
		
		
		
		return errosValidacao;

	}
}
