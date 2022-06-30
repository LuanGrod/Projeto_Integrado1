package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.itemPedido.ItemPedido;
import model.pedido.Pedido;
import model.produto.Produto;

public class ItemPedidoDao extends GenericDao{
	private String instrucaoSql; // Atributo para armazenar a instrução SQL a ser executada.
	private static String excecao = null; 
	private PreparedStatement comando; 
	private ResultSet registros;
	
	
	public String insereItemPedido(ItemPedido ip) {
		instrucaoSql = "INSERT INTO PEDIDO_HAS_PRODUTO (Quantidade, Pedido_idPedido, Produto_idProduto) VALUES (?,?,?)";
		return insere(instrucaoSql, ip.getQuantidade(), ip.getPedido().getId(), ip.getProduto().getId());
	}
	
	public List<ItemPedido> consultaItemPedido(){
		ItemPedido ip;
		Pedido pedido;
		Produto produto;
		List<ItemPedido> ips = new ArrayList<>();
		instrucaoSql = "SELECT * FROM PEDIDO_HAS_PRODUTO";
		
		try {
			excecao = ConnectionDatabase.conectaBd();
			if(excecao == null) {
				comando = ConnectionDatabase.getConexaoBd().prepareStatement(instrucaoSql);
                registros = comando.executeQuery();
                
                while (registros.next()) {
                	ip = new ItemPedido();
                	ip.setQuantidade(registros.getInt("Quantidade"));
                	pedido = new Pedido();
                	produto = new Produto();
                	pedido.setId(registros.getInt("Pedido_idPedido"));
                	produto.setId(registros.getInt("Produto_idProduto"));
                	ip.setPedido(pedido);
                	ip.setProduto(produto);
                	ips.add(ip);
                }
			}
		}catch(Exception e) {
			excecao = "Tipo de Exceção: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
			System.out.println(excecao);
			ips = null; 
		}
		return ips;
	}

	public String insereItemPedidoDoCarrinho(){
		ItemPedido ip;
		Produto produto;
		instrucaoSql = "SELECT * FROM CARRINHO";
		
		try {
			excecao = ConnectionDatabase.conectaBd();
			if(excecao == null) {
				comando = ConnectionDatabase.getConexaoBd().prepareStatement(instrucaoSql);
                registros = comando.executeQuery();
                
                while (registros.next()) {
                	ip = new ItemPedido();
                	ip.setQuantidade(registros.getInt("quantidade"));
                	produto = new Produto();
                	produto.setId(registros.getInt("Produto_idProduto"));
                	ip.setProduto(produto);
                	ip.setPedido(new PedidoDao().getUltimoPedido());
                	excecao = insereItemPedido(ip);
                }
                new CarrinhoDao().LimpaCarrinho();
                
			}
		}catch(Exception e) {
			excecao = "Tipo de Exceção: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
	}
	return excecao;
	}
	
	public String getExcecao() { 
		return excecao;
	}
}
