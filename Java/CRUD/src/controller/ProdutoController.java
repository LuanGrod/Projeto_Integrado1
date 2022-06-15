package controller;

import java.util.ArrayList;
import java.util.List;

import dao.ProdutoDao;
import model.categoria.Categoria;
import model.produto.Produto;

public class ProdutoController  {

	private Produto produto;
	private List<String> erros;

	
	public List<String> insereProduto(String nome, double precoCusto, double PrecoVenda, int QntEstoque, Categoria Categoria_idCategoria){
		recebeDadosUsuario(null, nome, precoCusto, PrecoVenda, QntEstoque,Categoria_idCategoria);
		
			if(erros.size() == 0)
				erros.add(new ProdutoDao().insereProduto(produto));
		
		return erros;
	}
	
	 public void recebeDadosUsuario(Integer Id, String nome, double precoCusto, double PrecoVenda, int QntEstoque, Categoria Categoria_idCategoria) {
		 produto = new Produto();
		 erros = new ArrayList<>();
		 
		 produto.setId(Id);
		 produto.setNome(nome);
		 produto.setPrecoCusto(precoCusto);
		 produto.setPrecoVenda(PrecoVenda);
		 produto.setQtdEstoque(QntEstoque);
		 produto.setCategoria(Categoria_idCategoria);
		 //erros = ProdutoValidacao().ValidaProduto();
	 }
	 
	 
	 public  List<Categoria> recuperaCategorias(){
		 return new ProdutoDao().recuperaCategorias();
	 }
	 
	 public String getExcecao() {
	    	return new ProdutoDao().getExcecao();
	    }
}
