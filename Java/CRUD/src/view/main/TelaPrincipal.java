package view.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
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

import controller.CarrinhoController;
import controller.ProdutoController;
import model.produto.Produto;
import view.cliente.CadastroCliente;
import view.cliente.ConsultaCliente;
import view.encomenda.CadastroEncomenda;
import view.encomenda.ConsultaEncomenda;
import view.fornecedor.CadastroFornecedor;
import view.fornecedor.ConsultaFornecedor;
import view.itemPedido.CarrinhoConsulta;
import view.produto.CadastroProduto;
import view.produto.ConsultaProduto;
import view.usuario.CadastroUsuario;
import view.usuario.ConsultaUsuario;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class TelaPrincipal extends JFrame {

	public JPanel contentPane, panelProd;
	public JLabel lblProd, lblQntd, lblPreco, lblCarrinhoVazio;
	private JTextField tfIdProd, tfQntd;
	private Produto produto;
	public static JButton btnCarrinho;
	private static Boolean statusCarrinho;

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public TelaPrincipal(int cargo) throws IOException {
		setBackground(new Color(255, 255, 255));
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Arthur\\Desktop\\Projeto Bd\\Projeto_Integrado1\\Java\\CRUD\\img\\pngaaa.com-1392699.png"));
		// setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("ERP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);


		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(new Color(255, 255, 255));
		menuBar.setBackground(new Color(65, 105, 225));
		menuBar.setBounds(0, 0, 1036, 38);
		contentPane.add(menuBar);

		// cliente
		JMenu mnCliente = new JMenu("Cliente");
		mnCliente.setForeground(new Color(255, 255, 255));
		mnCliente.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuBar.add(mnCliente);

		// cliente -> Cadastro
		JMenuItem miCliente = new JMenuItem("Cadastro");
		mnCliente.add(miCliente);
		miCliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));

		// cliente -> Consulta
		JMenuItem miConsultaCliente = new JMenuItem("Consulta");
		miConsultaCliente.setHorizontalAlignment(SwingConstants.CENTER);
		mnCliente.add(miConsultaCliente);
		miConsultaCliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));

		// loja
		JMenu mnLoja = new JMenu("Loja");
		mnLoja.setForeground(new Color(255, 255, 255));
		mnLoja.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuBar.add(mnLoja);
		mnLoja.setEnabled(false);

		// loja -> Cadastro
		JMenu mntmCadastro = new JMenu("Cadastro");
		mnLoja.add(mntmCadastro);

		// loja -> Cadastro -> Funcionario
		JMenuItem miFuncionario = new JMenuItem("Funcion\u00E1rio");
		miFuncionario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_DOWN_MASK));
		mntmCadastro.add(miFuncionario);

		// loja -> Cadastro -> Produto
		JMenuItem miProduto = new JMenuItem("Produto");
		miProduto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.CTRL_DOWN_MASK));
		mntmCadastro.add(miProduto);

		// loja -> Cadastro -> Fornecedor
		JMenuItem miForncecedor = new JMenuItem("Fornecedor");
		miForncecedor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.CTRL_DOWN_MASK));
		mntmCadastro.add(miForncecedor);

		// loja -> Cadastro -> Encomenda
		JMenuItem miEncomenda = new JMenuItem("Encomenda");
		miEncomenda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, InputEvent.CTRL_DOWN_MASK));
		mntmCadastro.add(miEncomenda);

		// loja -> Consulta
		JMenu mntmConsulta = new JMenu("Consulta");
		mnLoja.add(mntmConsulta);

		// loja -> Consulta -> Funcionario
		JMenuItem miConsultaFuncionario = new JMenuItem("Funcion\u00E1rio");
		miConsultaFuncionario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_DOWN_MASK));
		mntmConsulta.add(miConsultaFuncionario);

		// loja -> Consulta -> Produto
		JMenuItem miConsultaProduto = new JMenuItem("Produto");
		miConsultaProduto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.CTRL_DOWN_MASK));
		mntmConsulta.add(miConsultaProduto);

		// loja -> Consulta -> Fornecedor
		JMenuItem miConsultaForncecedor = new JMenuItem("Fornecedor");
		miConsultaForncecedor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.CTRL_DOWN_MASK));
		mntmConsulta.add(miConsultaForncecedor);

		// loja -> Consulta -> Encomenda
		JMenuItem miConsultaEncomenda = new JMenuItem("Encomenda");
		miConsultaEncomenda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, InputEvent.CTRL_DOWN_MASK));
		mntmConsulta.add(miConsultaEncomenda);

		// Relatorio
		JMenu mnRelatorio = new JMenu("Relat\u00F3rio");
		mnRelatorio.setForeground(new Color(255, 255, 255));
		mnRelatorio.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnRelatorio.setEnabled(false);
		menuBar.add(mnRelatorio);

		// Ajuda
		JMenu mnAjuda = new JMenu("Ajuda");
		mnAjuda.setForeground(new Color(255, 255, 255));
		mnAjuda.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuBar.add(mnAjuda);

		// Opcoes
		JMenu mnOpcoes = new JMenu("Op\u00E7\u00F5es");
		mnOpcoes.setForeground(new Color(255, 255, 255));
		mnOpcoes.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuBar.add(mnOpcoes);

		// Opcoes -> Sair
		JMenuItem miSair = new JMenuItem("Sair");
		miSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
		mnOpcoes.add(miSair);

		tfIdProd = new JTextField();
		tfIdProd.setForeground(new Color(0, 0, 0));
		tfIdProd.setBounds(216, 229, 355, 33);
		contentPane.add(tfIdProd);
		tfIdProd.setColumns(10);

		panelProd = new JPanel();
		panelProd.setBorder(null);
		panelProd.setBounds(0, 354, 884, 134);
		contentPane.add(panelProd);
		panelProd.setVisible(false);
		panelProd.setLayout(null);

		lblProd = new JLabel("New label");
		lblProd.setBounds(223, 48, 121, 32);
		lblProd.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelProd.add(lblProd);

		JButton btnAddCarrinho = new JButton("Add");
		btnAddCarrinho.setBackground(UIManager.getColor("Button.background"));
		btnAddCarrinho.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAddCarrinho.setBounds(602, 54, 61, 23);
		panelProd.add(btnAddCarrinho);

		lblQntd = new JLabel("Quantidade: ");
		lblQntd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQntd.setBounds(395, 40, 111, 14);
		panelProd.add(lblQntd);

		lblPreco = new JLabel("Preco:");
		lblPreco.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPreco.setBounds(395, 80, 111, 14);
		panelProd.add(lblPreco);

		tfQntd = new JTextField("1");
		tfQntd.setHorizontalAlignment(SwingConstants.CENTER);
		tfQntd.setBounds(541, 56, 39, 20);
		panelProd.add(tfQntd);
		tfQntd.setColumns(10);

		btnCarrinho = new JButton("Carrinho");
		btnCarrinho.setBackground(UIManager.getColor("Button.background"));
		btnCarrinho.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCarrinho.setVisible(false);
		btnCarrinhoEnabled(false);
		btnCarrinho.setBounds(373, 526, 137, 46);
		contentPane.add(btnCarrinho);

		lblCarrinhoVazio = new JLabel("O carrinho est\u00E1 vazio");
		lblCarrinhoVazio.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCarrinhoVazio.setForeground(Color.RED);
		lblCarrinhoVazio.setHorizontalAlignment(SwingConstants.CENTER);
		lblCarrinhoVazio.setBounds(365, 575, 152, 14);
		contentPane.add(lblCarrinhoVazio);

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setBounds(0, 173, 884, 154);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Busca");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(215, 11, 96, 22);
		panel.add(lblNewLabel);

		JButton btnConsultar = new JButton("Buscar");
		btnConsultar.setBackground(UIManager.getColor("Button.background"));
		btnConsultar.setSelectedIcon(null);
		btnConsultar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnConsultar.setBounds(588, 56, 75, 33);
		panel.add(btnConsultar);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(46, 38, 130, 134);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Arthur\\Desktop\\Projeto Bd\\Projeto_Integrado1\\Java\\CRUD\\img\\construcao4.png"));
		
		JLabel lblNewLabel_2 = new JLabel("Meroy Lerlyn");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel_2.setForeground(new Color(72, 61, 139));
		lblNewLabel_2.setBounds(216, 63, 365, 52);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Tudo para sua Constru\u00E7\u00E3o");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_3.setBounds(216, 115, 225, 19);
		contentPane.add(lblNewLabel_3);
		

		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnConsultarAction();
			}
		});
		lblCarrinhoVazio.setVisible(false);

		if (cargo == 1 || cargo == 2) {
			mnLoja.setEnabled(true);
			mnRelatorio.setEnabled(true);
		}

		// cadastro cliente
		miCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miClienteAction();
			}
		});

		// consulta cliente
		miConsultaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miConsultaClienteAction();
			}
		});

		// cadastro funcionario
		miFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miFuncionarioAction();
			}
		});

		// cadastro fornecedor
		miForncecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miForncecedorAction();
			}
		});

		// cadastro produto
		miProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miProdutoAction();
			}
		});

		// cadastro encomenda
		miEncomenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miEncomendaAction();
			}
		});

		// consulta funcionario
		miConsultaFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miConsultaFuncionarioAction();
			}
		});

		// consulta fornecedor
		miConsultaForncecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miConsultaForncecedorAction();
			}
		});

		// consulta produto
		miConsultaProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miConsultaProdutoAction();
			}
		});

		// consulta encomenda
		miConsultaEncomenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miConsultaEncomendaAction();
			}
		});

		// sair
		miSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechaTela();
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

		btnCarrinho.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (statusCarrinho == false)
					lblCarrinhoVazio.setVisible(true);
			}
		});
	}

	// metodos
	// cliente
	private void miClienteAction() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new CadastroCliente().setVisible(true);
			}
		});
	}

	private void miConsultaClienteAction() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ConsultaCliente().setVisible(true);
			}
		});
	}

	// usuario
	private void miFuncionarioAction() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new CadastroUsuario().setVisible(true);
			}
		});
	}

	private void miConsultaFuncionarioAction() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ConsultaUsuario().setVisible(true);
			}
		});
	}

	// fornecedor
	private void miForncecedorAction() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new CadastroFornecedor().setVisible(true);
			}
		});
	}

	private void miConsultaForncecedorAction() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ConsultaFornecedor().setVisible(true);
			}
		});
	}

	// produto
	private void miProdutoAction() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new CadastroProduto().setVisible(true);
			}
		});
	}

	private void miConsultaProdutoAction() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ConsultaProduto().setVisible(true);
			}
		});
	}

	// encomenda
	private void miEncomendaAction() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new CadastroEncomenda().setVisible(true);
			}
		});
	}

	private void miConsultaEncomendaAction() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ConsultaEncomenda().setVisible(true);
			}
		});
	}

	private void btnConsultarAction() {
		produto = new ProdutoController().consultaProdutoById(Integer.parseInt(tfIdProd.getText()));
		if (new ProdutoController().getExcecao() == null) {
			panelProd.setVisible(true);
			btnCarrinho.setVisible(true);
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
			btnCarrinhoEnabled(true);
			lblCarrinhoVazio.setVisible(false);
			JOptionPane.showMessageDialog(this, "Produto adicionado ao carrinho", "Informação",
					JOptionPane.INFORMATION_MESSAGE);
		} else { // Se o primeiro elemento do ArrayList não for null.
			String mensagem = "Não foi possível adicionar o produto ao carrinho:\n";
			for (String e : erros) // Cria uma mensagem contendo todos os erros armazenados no ArrayList.
				mensagem = mensagem + e + "\n";
			JOptionPane.showMessageDialog(this, mensagem, "Erros", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void btnCarrinhoAction() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new CarrinhoConsulta().setVisible(true);
			}
		});
	}

	private void fechaTela() {
		int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Atenção",
				JOptionPane.YES_NO_OPTION);
		if (sair == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	public static void btnCarrinhoEnabled(Boolean b) {
		btnCarrinho.setEnabled(b);
		statusCarrinho = b;
	}
}
