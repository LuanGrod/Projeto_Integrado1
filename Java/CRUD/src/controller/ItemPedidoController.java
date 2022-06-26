package controller;

import dao.ItemPedidoDao;

public class ItemPedidoController {
	//private ItemPedido ip;
	//private List<String> erros;
	
	
	public String insereItemPedidoDoCarrinho(){
		return new ItemPedidoDao().insereItemPedidoDoCarrinho();
	}
	
	
	
}
