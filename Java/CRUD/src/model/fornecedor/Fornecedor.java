package model.fornecedor;

/**
 * <p>Classe de dom�nio da entidade Fornecedor. Juntas, as classes de dom�nio modelam  
   a estrutura de neg�cio da aplica��o. Possuem basicamente atributos e seus respectivos   
   m�todos get e set, usados para recupera��o e atribui��o de valores aos seus dados.</p>
 *
 */
public class Fornecedor {
	private Integer id;
	private String nome;
	private String cnpj;
	private String telefone;
	private String email;
	
	public Integer getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
	

	

	@Override
	public String toString() {
	return nome;
	}

	 
}
