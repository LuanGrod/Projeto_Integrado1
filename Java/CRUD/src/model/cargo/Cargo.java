package model.cargo;

/**
 * <p>Classe de dom�nio da entidade Cargo. Juntas, as classes de dom�nio modelam  
   a estrutura de neg�cio da aplica��o. Possuem basicamente atributos e seus respectivos   
   m�todos get e set, usados para recupera��o e atribui��o de valores aos seus dados.</p>
 *
 */
public class Cargo {
	private Integer id;
	private String descricao;
	private String departamento;
	private double salario;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
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
