package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dao.PedidoDao;
import model.cliente.Cliente;
import model.pedido.Pedido;
import model.usuario.Usuario;

public class PedidoController {
	
	private List<String> erros;
	private Pedido pedido;

	public List<String> inserePedido(Calendar data, Usuario usuario) {
		recebeDadosUsuario(null, data, usuario);
		
		erros.add(new PedidoDao().inserePedido(pedido));
		erros.add(new ItemPedidoController().insereItemPedidoDoCarrinho()); 
			
		return erros;
	}

	public void recebeDadosUsuario(Integer id, Calendar data,Usuario usuario ) {
		erros = new ArrayList<String>();
		pedido = new Pedido();
		
		pedido.setDataPedido(data);
		pedido.setId(id);
		pedido.setUsuario(usuario);
	}
	
	public List<String> inserePedidoComCliente(Calendar data, Usuario usuario, Cliente cliente) {
		recebeDadosUsuario(null, data, usuario, cliente);
		
		erros.add(new PedidoDao().inserePedidoComCliente(pedido));
		erros.add(new ItemPedidoController().insereItemPedidoDoCarrinho()); 
			
		return erros;
	}

	public void recebeDadosUsuario(Integer id, Calendar data,Usuario usuario, Cliente cliente) {
		erros = new ArrayList<String>();
		pedido = new Pedido();
		
		pedido.setDataPedido(data);
		pedido.setId(id);
		pedido.setUsuario(usuario);
		pedido.setCliente(cliente);
	}
	

	
	public String getExcecao() {
    	return new PedidoDao().getExcecao();
    }
}
