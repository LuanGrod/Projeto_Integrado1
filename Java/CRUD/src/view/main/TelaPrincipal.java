package view.main;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import dao.LoginDao;
import view.cliente.CadastroCliente;
import view.encomenda.CadastroEncomenda;
import view.fornecedor.CadastroFornecedor;
import view.produto.CadastroProduto;
import view.usuario.CadastroUsuario;
import javax.swing.JButton;


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
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(SystemColor.inactiveCaption);
		menuBar.setBounds(0, 0, 1036, 22);
		contentPane.add(menuBar);
		
		JMenu mnCliente = new JMenu("Cliente");
		menuBar.add(mnCliente);
		
		JMenuItem miCliente = new JMenuItem("Cadastro");
		mnCliente.add(miCliente);
		miCliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
		
		
		
		JMenuItem miConsultaCliente = new JMenuItem("Consulta");
		miConsultaCliente.setHorizontalAlignment(SwingConstants.CENTER);
		mnCliente.add(miConsultaCliente);
		
		JMenu mnLoja = new JMenu("Loja");
		menuBar.add(mnLoja);
		mnLoja.setEnabled(false);
		
		JMenu mntmCadastro = new JMenu("Cadastro");
		mnLoja.add(mntmCadastro);
		
		JMenu mntmConsulta = new JMenu("Consulta");
		mnLoja.add(mntmConsulta);
		
		JMenuItem miFuncionario = new JMenuItem("Funcion\u00E1rio");
		miFuncionario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK));
		mntmCadastro.add(miFuncionario);
		
		JMenuItem miProduto = new JMenuItem("Produto");
		miProduto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
		mntmCadastro.add(miProduto);

		JMenuItem miForncecedor = new JMenuItem("Fornecedor");
		mntmCadastro.add(miForncecedor);
		
		JMenuItem miEncomenda = new JMenuItem("Encomenda");
		mntmCadastro.add(miEncomenda);

		JMenu mnRelatorio = new JMenu("Relat\u00F3rio");
		mnRelatorio.setEnabled(false);
		menuBar.add(mnRelatorio);
		
		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);
		
		JMenu mnOpcoes = new JMenu("Op\u00E7\u00F5es");
		menuBar.add(mnOpcoes);
		
		JMenuItem miSair = new JMenuItem("Sair");
		miSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
		mnOpcoes.add(miSair);
		
		
		JLabel lblNewLabel = new JLabel("Dep\u00F3sito do Seu Z\u00E9");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(297, 51, 306, 52);
		contentPane.add(lblNewLabel);
		
		
		if (cargo == 1 || cargo == 2) {
			mnLoja.setEnabled(true);
			mnRelatorio.setEnabled(true);
		}
		
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
		
		miForncecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miForncecedorAction();
			}
		});
		
		miProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miProdutoAction();
			}
		});
		
		miEncomenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miEncomendaAction();
			}
		});
		

		miSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechaTela();
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
	
	private void miForncecedorAction() {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){ new CadastroFornecedor().setVisible(true); }});
	}
	
	private void miProdutoAction() {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){ new CadastroProduto().setVisible(true);}});
	}
	
	private void miEncomendaAction() {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){ new CadastroEncomenda().setVisible(true);}});
	}
	
	private void fechaTela() {
		int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Atenção", JOptionPane.YES_NO_OPTION);
		if(sair == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
}
