package model.fornecedor;

/**
 * <p>Classe de domínio da entidade Fornecedor. Juntas, as classes de domínio modelam  
   a estrutura de negócio da aplicação. Possuem basicamente atributos e seus respectivos   
   métodos get e set, usados para recuperação e atribuição de valores aos seus dados.</p>
 *
 */
public class Fornecedor {
	private Integer id;
	private String cnpj;
	private String telefone;
	private String email;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
