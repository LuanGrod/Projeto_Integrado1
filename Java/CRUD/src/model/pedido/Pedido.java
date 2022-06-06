package model.pedido;

import java.util.Calendar;
import model.cliente.Cliente;
import model.usuario.Usuario;

/**
 * <p>Classe de dom�nio da entidade Pedido. Juntas, as classes de dom�nio modelam  
   a estrutura de neg�cio da aplica��o. Possuem basicamente atributos e seus respectivos   
   m�todos get e set, usados para recupera��o e atribui��o de valores aos seus dados.</p>
 *
 */
public class Pedido {
	private Integer id;
	private Calendar dataPedido;
	private Usuario usuario;
	private Cliente cliente;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Calendar getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(Calendar dataPedido) {
		this.dataPedido = dataPedido;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
