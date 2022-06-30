package controller;


import dao.ItemPedidoDao;

public class ItemPedidoController {
	
	
	public String insereItemPedidoDoCarrinho(){
		 return new ItemPedidoDao().insereItemPedidoDoCarrinho();
		
	}
	
	
	
}
