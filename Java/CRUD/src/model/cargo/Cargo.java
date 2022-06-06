package model.cargo;

/**
 * <p>Classe de domínio da entidade Cargo. Juntas, as classes de domínio modelam  
   a estrutura de negócio da aplicação. Possuem basicamente atributos e seus respectivos   
   métodos get e set, usados para recuperação e atribuição de valores aos seus dados.</p>
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
