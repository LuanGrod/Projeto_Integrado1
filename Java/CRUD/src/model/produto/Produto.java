package model.produto;

import model.categoria.Categoria;

/**
 * <p>Classe de dom�nio da entidade Produto. Juntas, as classes de dom�nio modelam  
   a estrutura de neg�cio da aplica��o. Possuem basicamente atributos e seus respectivos   
   m�todos get e set, usados para recupera��o e atribui��o de valores aos seus dados.</p>
 *
 */
public class Produto {
	private Integer id;
	private String nome;
	private double precoCusto;
	private double precoVenda;
	private int qtdEstoque;
	private Categoria categoria;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getPrecoCusto() {
		return precoCusto;
	}
	public void setPrecoCusto(double precoCusto) {
		this.precoCusto = precoCusto;
	}
	public double getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}
	public int getQtdEstoque() {
		return qtdEstoque;
	}
	public void setQtdEstoque(int qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	/*
	<p>Sobrescreve o m�todo toString da classe Object, para que ele retorne o valor do 
   atributo Descricao. Com isso, o JComboBox do formul�rio de cadastro de funcion�rios 
   mostrar� apenas a descri��o dos cargos.</p>

   <p>Object � superclasse de todas as demais classes do Java, inclusive das classes 
   criadas pelos desenvolvedores. Por este motivo, Object define alguns comportamentos 
   comuns que todos objetos devem ter, como a habilidade de serem comparados entre si, 
   com o m�todo equals(), e poderem ser representados como uma cadeia de caracteres, 
   com o m�todo toString().</p>

@Override
public String toString() {
	return descricao;
}

 */
}
