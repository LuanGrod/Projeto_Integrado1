package view.itemPedido;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.PedidoController;
import controller.UsuarioController;

public class Carrinho extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	
	public Carrinho() {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnFecharPedido = new JButton("Fechar Pedido");
		btnFecharPedido.setBounds(155, 227, 136, 23);
		contentPane.add(btnFecharPedido);
		setLocationRelativeTo(null);

		

		btnFecharPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFecharPedidoAction();
			}
		});
	}
	
	private void btnFecharPedidoAction() {
		//new pedido
		//new itemPedido, vai receber a chave do pedido, do produto e a quantidade de cada produto.
		List<String> erros = new ArrayList<String>();
		erros = new PedidoController().inserePedido(Calendar.getInstance(), new UsuarioController().getUsuarioAtual());
		
		if (erros.get(0) == null) { // Se o primeiro elemento do ArrayList for null.
			JOptionPane.showMessageDialog(this, "Pedido feito com sucesso!", 
					                      "Informação", JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(false); // Fecha a janela.
		} else { // Se o primeiro elemento do ArrayList não for null.
			String mensagem = "Não foi possível realizar o pedido:\n";
			for (String e : erros) // Cria uma mensagem contendo todos os erros armazenados no ArrayList.
				mensagem = mensagem + e + "\n";
			JOptionPane.showMessageDialog(this, mensagem, "Erros", JOptionPane.ERROR_MESSAGE);
		}
	}
}
