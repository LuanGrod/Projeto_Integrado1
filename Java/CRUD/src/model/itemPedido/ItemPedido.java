package model.itemPedido;

import model.pedido.Pedido;
import model.produto.Produto;

public class ItemPedido {
	private int quantidade;
	private Pedido pedido;
	private Produto produto;
	
	
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	
}
