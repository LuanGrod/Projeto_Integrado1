package model.cliente;

import model.pessoa.Pessoa;

/**
 * <p>Classe de dom�nio da entidade Cliente. Juntas, as classes de dom�nio modelam  
   a estrutura de neg�cio da aplica��o. Possuem basicamente atributos e seus respectivos   
   m�todos get e set, usados para recupera��o e atribui��o de valores aos seus dados.</p>
 *
 */
public class Cliente {
	private Integer id;
	private Pessoa pessoa;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
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
