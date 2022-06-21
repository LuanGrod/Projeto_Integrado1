package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import logs.Registro;

public class LoginDao extends GenericDao{
	private String instrucaoSql;
	private Connection conexao;
	private PreparedStatement pst; 
	private ResultSet rs;
	private static String excecao = null; 
	private static int cargo;
	private static int id;
 
	public int validaLogin(String login, String senha) {
		//String dataHora = new SimpleDateFormat("[yyyy/MM/dd HH:mm:ss]").format(Calendar.getInstance().getTime());
		String dataHora = new SimpleDateFormat("[yyyy/MM/dd HH:mm:ss]").format(Calendar.getInstance().getTime());
		instrucaoSql = "SELECT idUsuario, login, senha, Cargo_idCargo FROM usuario WHERE login = ? AND senha = ?";
	
		try {
			excecao = ConnectionDatabase.conectaBd();
			
			if (excecao == null) {
				conexao = ConnectionDatabase.getConexaoBd();
				pst = conexao.prepareStatement(instrucaoSql);
				pst.setObject(1, login);
				pst.setObject(2, senha);
				rs = pst.executeQuery();
				
				if(rs.next()) {
					cargo = rs.getInt(4);
					id = rs.getInt(1);
					Registro log = new Registro();
					log.emitirRegistro(log.adicionarRegistro(dataHora + " - Login[" + login + "]" + "\n"));
					new CarrinhoDao().LimpaCarrinho();
					conexao.close();
				}else {
					excecao = "Usu�rio e/ou Senha inv�lido(s)";
				}
			}
		} catch (Exception e) {
		 		excecao = "Tipo de Exce��o: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();		
		 	}
		return cargo;
	}
	
	public int getCargo() {//retorna o cargo do usuario
		return cargo;
	}
	
	public String getExcecao() { //retorna a exce��o da valida��o de login
		return excecao;
	}
	
	public int getIdUsuario() {//retorna o id do usuario
		return id;
	}
}
