package controller;

import java.util.List;

import dao.ItemPedidoDao;
import model.itemPedido.ItemPedido;

public class ItemPedidoController {
	private ItemPedido ip;
	private List<String> erros;
	
	
	public String insereItemPedidoDoCarrinho(){
		return new ItemPedidoDao().insereItemPedidoDoCarrinho();
	}
	
	
	
}
