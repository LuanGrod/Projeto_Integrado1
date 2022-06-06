package model.cliente;

import model.pessoa.Pessoa;

/**
 * <p>Classe de domínio da entidade Cliente. Juntas, as classes de domínio modelam  
   a estrutura de negócio da aplicação. Possuem basicamente atributos e seus respectivos   
   métodos get e set, usados para recuperação e atribuição de valores aos seus dados.</p>
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
		<p>Sobrescreve o método toString da classe Object, para que ele retorne o valor do 
	   atributo Descricao. Com isso, o JComboBox do formulário de cadastro de funcionários 
	   mostrará apenas a descrição dos cargos.</p>

       <p>Object é superclasse de todas as demais classes do Java, inclusive das classes 
	   criadas pelos desenvolvedores. Por este motivo, Object define alguns comportamentos 
	   comuns que todos objetos devem ter, como a habilidade de serem comparados entre si, 
	   com o método equals(), e poderem ser representados como uma cadeia de caracteres, 
	   com o método toString().</p>

	@Override
	public String toString() {
		return descricao;
	}
	 */
}
