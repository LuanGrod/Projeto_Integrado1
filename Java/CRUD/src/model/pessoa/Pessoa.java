package model.pessoa;

/**
 * <p>Classe de dom�nio da entidade Pessoa. Juntas, as classes de dom�nio modelam  
   a estrutura de neg�cio da aplica��o. Possuem basicamente atributos e seus respectivos   
   m�todos get e set, usados para recupera��o e atribui��o de valores aos seus dados.</p>
 *
 */
public class Pessoa {
	private String cpf;
	private String nome;
	private String telefone;
	private String email;
	private String rua;
	private String bairro;
	private String cidade;
	private String cep;
	private String estado;
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
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
