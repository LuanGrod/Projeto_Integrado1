package view.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.CarrinhoController;
import controller.ProdutoController;
import model.produto.Produto;
import view.cliente.CadastroCliente;
import view.encomenda.CadastroEncomenda;
import view.fornecedor.CadastroFornecedor;
import view.itemPedido.CarrinhoConsulta;
import view.produto.CadastroProduto;
import view.usuario.CadastroUsuario;


@SuppressWarnings("serial")
public class TelaPrincipal extends JFrame {

	public JPanel contentPane, panelProd;
	public JLabel lblProd, lblQntd, lblPreco;
	private JTextField tfIdProd, tfQntd;
	private Produto produto;
	private JButton btnCarrinho;
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
		
		tfIdProd = new JTextField();
		tfIdProd.setBounds(216, 229, 355, 33);
		contentPane.add(tfIdProd);
		tfIdProd.setColumns(10);
		
		JButton btnConsultar = new JButton("Consultar Produto");
		btnConsultar.setBounds(588, 234, 168, 22);
		contentPane.add(btnConsultar);
		
		panelProd = new JPanel();
		panelProd.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelProd.setBounds(264, 354, 355, 134);
		contentPane.add(panelProd);
		panelProd.setVisible(false);
		panelProd.setLayout(null);
		
		lblProd = new JLabel("New label");
		lblProd.setBounds(10, 45, 121, 32);
		lblProd.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelProd.add(lblProd);
		
		JButton btnAddCarrinho = new JButton("Add");
		btnAddCarrinho.setHorizontalAlignment(SwingConstants.LEFT);
		btnAddCarrinho.setBounds(284, 54, 51, 23);
		panelProd.add(btnAddCarrinho);
		
		lblQntd = new JLabel("Quantidade: ");
		lblQntd.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblQntd.setBounds(136, 40, 94, 14);
		panelProd.add(lblQntd);
		
		lblPreco = new JLabel("Preco:");
		lblPreco.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPreco.setBounds(136, 80, 94, 14);
		panelProd.add(lblPreco);
		
		tfQntd = new JTextField("1");
		tfQntd.setBounds(240, 55, 39, 20);
		panelProd.add(tfQntd);
		tfQntd.setColumns(10);
		
		btnCarrinho = new JButton("Carrinho");
		btnCarrinho.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCarrinho.setVisible(false);
		btnCarrinho.setBounds(373, 526, 137, 46);
		contentPane.add(btnCarrinho);
		
		
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
		

		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnConsultarAction();
			}
		});
		

		btnAddCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddCarrinhoAction();
			}
		});
		

		btnCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCarrinhoAction();
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
	
	private void btnConsultarAction() {
		produto = new ProdutoController().consultaProduto(Integer.parseInt(tfIdProd.getText()));
		if (new ProdutoController().getExcecao() == null) { 
			panelProd.setVisible(true);
			lblProd.setText(produto.getNome());
			lblQntd.setText("Quantidade: " + String.valueOf(produto.getQtdEstoque()));
			lblPreco.setText("Preço: " + String.valueOf(produto.getPrecoVenda()));
			
			
		} else { 
			String mensagem = "Esse produto não existe";
			JOptionPane.showMessageDialog(this, mensagem, "Erros", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void btnAddCarrinhoAction() {
		List<String> erros = new ArrayList<String>();
		erros = new CarrinhoController().insereItemCarrinho(Integer.parseInt(tfQntd.getText()), produto);
		
		if (erros.get(0) == null) { // Se o primeiro elemento do ArrayList for null.
			btnCarrinho.setVisible(true);
			JOptionPane.showMessageDialog(this, "Produto adicionado ao carrinho", 
					                      "Informação", JOptionPane.INFORMATION_MESSAGE);
		} else { // Se o primeiro elemento do ArrayList não for null.
			String mensagem = "Não foi possível adicionar o produto ao carrinho:\n";
			for (String e : erros) // Cria uma mensagem contendo todos os erros armazenados no ArrayList.
				mensagem = mensagem + e + "\n";
			JOptionPane.showMessageDialog(this, mensagem, "Erros", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void btnCarrinhoAction() {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){ new CarrinhoConsulta().setVisible(true);}});
	}
	
	private void fechaTela() {
		int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Atenção", JOptionPane.YES_NO_OPTION);
		if(sair == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
}
