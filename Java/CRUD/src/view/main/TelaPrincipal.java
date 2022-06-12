package view.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import view.usuario.CrudUsuario;

@SuppressWarnings("serial")
public class TelaPrincipal extends JFrame {

	public JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public TelaPrincipal(int cargo) {
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("ERP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.GRAY);
		menuBar.setBounds(0, 0, 884, 22);
		contentPane.add(menuBar);
		
		JMenu mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);
		
		JMenuItem miCliente = new JMenuItem("Cliente");
		miCliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
		mnCadastro.add(miCliente);
		
		JMenuItem miFuncionario = new JMenuItem("Funcion\u00E1rio");
		miFuncionario.setEnabled(false);
		if (cargo == 1) {
			miFuncionario.setEnabled(true);
		}
		miFuncionario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK));
		mnCadastro.add(miFuncionario);
		
		JMenuItem miProduto = new JMenuItem("Produto");
		miProduto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
		mnCadastro.add(miProduto);
		miProduto.setEnabled(false);
		if (cargo == 1) {
			miProduto.setEnabled(true);
		}
		
		JMenuItem miForncecedor = new JMenuItem("Fornecedor");
		mnCadastro.add(miForncecedor);
		miForncecedor.setEnabled(false);
		if (cargo == 1) {
			miForncecedor.setEnabled(true);
		}
		
		JMenu mnRelatorio = new JMenu("Relat\u00F3rio");
		mnRelatorio.setEnabled(false);
		if (cargo == 1) {
			mnRelatorio.setEnabled(true);
		}
		menuBar.add(mnRelatorio);
		
		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);
		
		JMenu mnNewMenu_3 = new JMenu("Sair");
		mnNewMenu_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechaTela();
			}
		});
		
		JMenu mnOpcoes = new JMenu("Op\u00E7\u00F5es");
		menuBar.add(mnOpcoes);
		menuBar.add(mnNewMenu_3);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 21, 211, 640);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome do Sistema");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(36, 41, 157, 32);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Produto");
		btnNewButton.setBounds(0, 104, 211, 57);
		panel.add(btnNewButton);
		
		JButton btnCliente = new JButton("Cliente");
		btnCliente.setBounds(0, 158, 211, 57);
		panel.add(btnCliente);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(210, 21, 674, 640);
		contentPane.add(layeredPane);
		
		miFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { miFuncionarioAction(); }
		});
	}
	
	private void miFuncionarioAction() { 
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){ new CrudUsuario().setVisible(true); }});
	}

	
	protected void fechaTela() {this.dispose();}
}
