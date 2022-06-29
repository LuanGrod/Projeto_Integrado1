package model.produto;

import java.util.ArrayList;
import java.util.List;

import controller.ProdutoController;



public class ValidaProduto {
	private static List<String> errosValidacao;
	private static List<Produto> produtos;

	
	public static List<String> validaProduto(Produto produto) {
		errosValidacao = new ArrayList<>();
		
		produtos = new ArrayList<>();
		produtos = new ProdutoController().selectProdutos();
		
		for(Produto p : produtos) {
			if (produto.getNome().equals(p.getNome())) {
				errosValidacao.add("* Produto já existente.");
			}
		}
		
		if(produto.getNome().length() < 2) {
			errosValidacao.add("* O nome do produto deve ter pelo menos 2 caracteres");
		}
		
		return errosValidacao;
	}
}
