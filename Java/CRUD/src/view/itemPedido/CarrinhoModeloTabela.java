package view.itemPedido;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.itemPedido.ItemCarrinho;
import model.produto.Produto;

@SuppressWarnings("serial")
public class CarrinhoModeloTabela extends AbstractTableModel{
	int id = 0;
	String nome = null;
	double preco = 0;

	private String[] colunas = {"Código", "Nome", "Preço(R$)", "Quantidade"};
	private List<Produto> produtos;
	private List<ItemCarrinho> produtosCarrinho;
	
	public CarrinhoModeloTabela(){
	};
	
	public CarrinhoModeloTabela(List<Produto> produtos, List<ItemCarrinho> produtosCarrinho){
		this.produtos = produtos;
		this.produtosCarrinho = produtosCarrinho;
	};

	@Override
	public int getRowCount() {
		if (produtosCarrinho != null) // Se existir pelo menos um produto no carrinho.
			return produtosCarrinho.size();	
		return 0;
	}
	

	@Override
	public int getColumnCount() {
		return colunas.length;
	}
	
	@Override
	public String getColumnName(int coluna) {
		return colunas[coluna];
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		ItemCarrinho prodCarrinho = produtosCarrinho.get(linha);
		
		Object valor = null;
		
		
		switch (coluna) {
		
			case 0:
				id = prodCarrinho.getProduto().getId();
				valor = id;
				for(Produto p: produtos) {
					if (p.getId() == id) {
						nome = p.getNome();
						preco = p.getPrecoVenda();
					}
				}
				break;
			case 1:
				valor = nome;
				break;
			case 2:
				valor = preco;
				break;
			case 3:
				valor = prodCarrinho.getQuantidade();
				break;
		}
		
		return valor;
	}
	

	
}
