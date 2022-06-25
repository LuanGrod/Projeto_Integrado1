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
		recebeDadosProduto(null, nome, precoCusto, PrecoVenda, QntEstoque,Categoria_idCategoria);
		
			if(erros.size() == 0)
				erros.add(new ProdutoDao().insereProduto(produto));
		
		return erros;
	}
	
	//???
	 public void recebeDadosProduto(Integer Id, String nome, double precoCusto, double PrecoVenda, int QntEstoque, Categoria Categoria_idCategoria) {
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
	 
	 public Produto consultaProdutoById(int id) {
		 return new ProdutoDao().consultaProdutoById(id);
	 }
	 
	 
	 public  List<Categoria> recuperaCategorias(){
		 return new ProdutoDao().recuperaCategorias();
	 }
	 
	 public String getExcecao() {
	    	return new ProdutoDao().getExcecao();
	 }
	 
    //recupera 2.0
    public List<Produto> selectProdutos() {
		return new ProdutoDao().selectProdutos();
    }

    public List<Categoria> selectCategorias() {
    	// Recupera os cargos cadastrados no banco de dados para que sejam carregados no JComboBox Cargo.
		return new ProdutoDao().selectCategorias();		
    }
    
    public List<String> alteraProduto(Integer Id, String nome, double precoCusto, double PrecoVenda, int QntEstoque, Categoria Categoria_idCategoria) {
    	recebeDadosProduto(Id, nome, precoCusto, PrecoVenda, QntEstoque, Categoria_idCategoria);
    	
		// Se nenhum erro de validação for encontrado, tenta inserir o funcionário no banco.
		if (erros.size() == 0)
			erros.add(new ProdutoDao().alteraProduto(produto));
		
		// Retorna o ArrayList contendo:
		// - Em caso de sucesso: null na 1ª posição; OU
		// - Em caso de exceção: uma mensagem de exceção na 1ª posição; OU
		// - Em caso de erro de validação: mensagens de erro iniciando na 1ª posição.
		return erros;
    }

    public String excluiProduto(Integer id) {
    	String erro = new ProdutoDao().excluiProduto(id);
        return erro;
    }
}
