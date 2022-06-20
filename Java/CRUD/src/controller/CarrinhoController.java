package controller;

import java.util.ArrayList;
import java.util.List;

import dao.CarrinhoDao;
import model.produto.Produto;

public class CarrinhoController {

	private List<String> erros;
	
	
	public List<String> insereItemCarrinho(Integer qntd, Produto produto) {
		erros = new ArrayList<>();
		erros.add(new CarrinhoDao().InsereItemCarrinho(qntd, produto));
			
		return erros;
	}
}
