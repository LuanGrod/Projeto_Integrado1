package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.pedido.Pedido;

public class PedidoDao extends GenericDao{
	private String instrucaoSql; // Atributo para armazenar a instru��o SQL a ser executada.
	private static String excecao = null; 
	private PreparedStatement comando; 
	private ResultSet registros;
	
	public String inserePedido(Pedido pedido){
		instrucaoSql = "INSERT INTO PEDIDO (DataPedido, Usuario_idUsuario) VALUES (?,?)";
		return insere(instrucaoSql, pedido.getDataPedido(), pedido.getUsuario().getId());
	}

	public Pedido getUltimoPedido() {
		Pedido pedido = null;
		instrucaoSql = "SELECT * FROM lastPedido";
		
		try {
			excecao = ConnectionDatabase.conectaBd();
			if(excecao == null) {
				comando = ConnectionDatabase.getConexaoBd().prepareStatement(instrucaoSql);
                registros = comando.executeQuery();
                
                while (registros.next()) {
                	pedido = new Pedido();
                	pedido.setId(registros.getInt("maxId"));
                }
			}
		}catch(Exception e) {
			excecao = "Tipo de Exce��o: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
			System.out.println(excecao);
		}
		
		return pedido;
	}
	
	
}
