package view.main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import view.cliente.CadastroCliente;
import view.usuario.CadastroUsuario;


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
		miFuncionario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK));
		mnCadastro.add(miFuncionario);
		
		JMenuItem miProduto = new JMenuItem("Produto");
		miProduto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
		mnCadastro.add(miProduto);
		miProduto.setEnabled(false);

		
		JMenuItem miForncecedor = new JMenuItem("Fornecedor");
		mnCadastro.add(miForncecedor);
		miForncecedor.setEnabled(false);

		
		JMenu mnRelatorio = new JMenu("Relat\u00F3rio");
		mnRelatorio.setEnabled(false);
		
		menuBar.add(mnRelatorio);
		
		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);
		
		if (cargo == 1 || cargo == 2) {
			mnRelatorio.setEnabled(true);
			miForncecedor.setEnabled(true);
			miProduto.setEnabled(true);
			miFuncionario.setEnabled(true);
		}
		
		JMenu mnSair = new JMenu("Sair");
		mnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechaTela();
			}
		});
		
		JMenu mnOpcoes = new JMenu("Op\u00E7\u00F5es");
		menuBar.add(mnOpcoes);
		menuBar.add(mnSair);
		
		miCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miClienteAction();
			}
		});
		
		miFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				miFuncionarioAction(); 
				}
		});
		
	}
	
	private void miFuncionarioAction() { 
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){ new CadastroUsuario().setVisible(true); }});
	}
	
	private void miClienteAction() { 
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){ new CadastroCliente().setVisible(true); }});
	}

	
	protected void fechaTela() {this.dispose();}
}
