package view.itemPedido;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

import controller.CarrinhoController;
import controller.ItemPedidoController;
import controller.PedidoController;
import controller.UsuarioController;
import model.cliente.Cliente;
import model.itemPedido.ItemCarrinho;
import model.produto.Produto;
import view.main.TelaPrincipal;

public class CarrinhoConsulta extends JDialog {

	
	private static final long serialVersionUID = 1L;
	private JTable tbCarrinho; 
	private JLabel lblValorTotal, tfNome;
	private CarrinhoModeloTabela mtTabela;
	private JScrollPane spTabela;
	private Container cp;	
	private List<ItemCarrinho> produtosCarrinho;
	private List <Produto> produtos;
	private JTextField tfAlteraQntd;
	private JTextField tfCpf;
	private Cliente cliente;
	
	
	public CarrinhoConsulta() {
		setTitle("Consulta Carrinho"); // Título da janela.
		setSize(449, 700); // Tamanho da janela em pixels.
		setLocationRelativeTo(null); // Centraliza a janela na tela.
		setModal(true); // Torna a janela "modal" (janela que não 
		
		JButton btnFecharPedido = new JButton("Fechar Pedido");
		btnFecharPedido.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnFecharPedido.setBounds(136, 585, 161, 43);
		setLocationRelativeTo(null);

		
		//carregaTabela();
		//String excecaoItemCarrinho = null;
		//String excecaoProduto = null;
		
		produtosCarrinho = new CarrinhoController().consultaItemCarrinho();
		//produtosCarrinho = new CarrinhoController().getExcecao();
		
		produtos = new CarrinhoController().consultaProdutosCarrinho();
		
		
		mtTabela = new CarrinhoModeloTabela(produtos, produtosCarrinho);
		
		
		tbCarrinho = new JTable(mtTabela);
		tbCarrinho.setBackground(Color.WHITE);
		tbCarrinho.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		spTabela = new JScrollPane(tbCarrinho);
		spTabela.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		// Configura a largura de cada coluna do JTable (em pixels).
		tbCarrinho.getColumnModel().getColumn(0).setPreferredWidth(50);
		tbCarrinho.getColumnModel().getColumn(1).setPreferredWidth(160);
		tbCarrinho.getColumnModel().getColumn(2).setPreferredWidth(70);
		tbCarrinho.getColumnModel().getColumn(3).setPreferredWidth(70);
		
		// Configura a fonte do cabeçalho do JTable.
		tbCarrinho.getTableHeader().setFont(new Font(null, Font.BOLD, 12));
				
		// Centraliza o conteúdo da coluna referente ao Id do funcionário (índice 0).
		DefaultTableCellRenderer dtcrCentro = new DefaultTableCellRenderer();
		dtcrCentro.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i<4; i++) {
			tbCarrinho.getColumnModel().getColumn(i).setCellRenderer(dtcrCentro);
		}
		
		tbCarrinho.getTableHeader().setReorderingAllowed(false); // Desabilita a reordenação das colunas do JTable.
		// Habilita o modo de seleção simples, onde é possível selecionar apenas uma linha de cada vez no JTable.
		tbCarrinho.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		cp = getContentPane();
		cp.setLayout(null);
		
		// Posicionamento dos componentes de interface na janela.
		spTabela.setBounds(40, 41, 352, 202);

		// Adição dos componentes de interface ao container.
		cp.add(spTabela);
		cp.add(btnFecharPedido);
		
		JButton btnAltQntd = new JButton("Alterar Quantidade");
		btnAltQntd.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAltQntd.setBounds(60, 275, 126, 36);
		getContentPane().add(btnAltQntd);
		
		JButton btnExcluiProdCarrinho = new JButton("Excluir Produto");
		btnExcluiProdCarrinho.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnExcluiProdCarrinho.setBounds(246, 275, 126, 36);
		getContentPane().add(btnExcluiProdCarrinho);
		
		tfAlteraQntd = new JTextField();
		tfAlteraQntd.setBounds(80, 322, 86, 20);
		getContentPane().add(tfAlteraQntd);
		tfAlteraQntd.setColumns(10);
		
		JPanel panelCliente = new JPanel();
		panelCliente.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelCliente.setBounds(60, 383, 312, 89);
		getContentPane().add(panelCliente);
		Border border = BorderFactory.createTitledBorder("Cliente (Opcional)");
		panelCliente.setBorder(border);
		panelCliente.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("CPF");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(28, 34, 19, 14);
		panelCliente.add(lblNewLabel_1);
		
		tfCpf = new JTextField();
		tfCpf.setBounds(52, 31, 126, 20);
		panelCliente.add(tfCpf);
		tfCpf.setColumns(10);
		
		JButton btnBuscaCliente = new JButton("Buscar Cliente");
		btnBuscaCliente.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnBuscaCliente.setBounds(188, 30, 110, 23);
		panelCliente.add(btnBuscaCliente);
		
		tfNome = new JLabel("New label");
		tfNome.setVisible(false);
		tfNome.setHorizontalAlignment(SwingConstants.CENTER);
		tfNome.setBounds(70, 64, 171, 14);
		panelCliente.add(tfNome);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(65, 105, 225));
		panel.setBounds(63, 507, 307, 57);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Valor Total: ");
		lblNewLabel.setBounds(107, 7, 92, 15);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblValorTotal = new JLabel("Valor Total: R$");
		lblValorTotal.setBounds(112, 27, 82, 19);
		lblValorTotal.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblValorTotal);
		lblValorTotal.setForeground(new Color(255, 255, 255));
		lblValorTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
		atualizaValorTotal();

		btnAltQntd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAltQntdAction();
			}
		});
		

		btnExcluiProdCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnExcluiProdCarrinhoAction();
			}
		});

		btnFecharPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFecharPedidoAction();
			}
		});
		

		btnBuscaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBuscaCliente();
			}
		});
	}
	
	private void btnAltQntdAction(){
		try {
			int qntd = Integer.parseInt(tfAlteraQntd.getText());
			
			if (tbCarrinho.getSelectedRow() != -1) {
				int linhaSelecionada = tbCarrinho.getSelectedRow();
				int id = Integer.parseInt(tbCarrinho.getModel().getValueAt(linhaSelecionada, 0).toString());
				String erro = new CarrinhoController().alteraQntdCarrinho(Integer.parseInt(tfAlteraQntd.getText()), id);
				if(erro != null) {
					String mensagem = "Não foi possivel atualizar a quantidade:\n";
				    mensagem = mensagem + erro + "\n";
					JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
				}else {
					if(qntd > 0) { //valida quantidade
						this.mtTabela.setValueAt(tfAlteraQntd.getText(), linhaSelecionada, 3);
						atualizaValorTotal();
					}else {
						JOptionPane.showMessageDialog(this, "Insira uma quantidade maior que 0.", "Mensagem", JOptionPane.WARNING_MESSAGE);
					}
					
				}
			}else { // Se nenhuma linha está selecionada no JTable.
				JOptionPane.showMessageDialog(this, "Selecione um produto.", "Mensagem", JOptionPane.WARNING_MESSAGE);
		}
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Insira um número.", "Mensagem", JOptionPane.WARNING_MESSAGE);
		}
		

	}
	
	
	private void btnExcluiProdCarrinhoAction() {
		if (tbCarrinho.getSelectedRow() != -1) {
			int resposta = JOptionPane.showConfirmDialog(this, "Confirma exclusão?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if (resposta == 0) { //sim
				int linhaSelecionada = tbCarrinho.getSelectedRow();
				int idProd = Integer.parseInt(tbCarrinho.getModel().getValueAt(linhaSelecionada, 0).toString());
				String erro = "";
				erro = new CarrinhoController().excluiProdutoCarrinho(idProd);
				
				if(erro == null) {
					mtTabela.removeProdutoTabela(linhaSelecionada);
					atualizaValorTotal();
					if (new CarrinhoController().consultaItemCarrinho().size() == 0) {
						this.setVisible(false); 
						TelaPrincipal.btnCarrinhoEnabled(false);
					}
				} else {
					String mensagem = "Não foi possível excluir o produto do carrinho:\n";
				    mensagem = mensagem + erro + "\n";
					JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}else if (resposta == 1) // Não
				JOptionPane.showMessageDialog(this, "Operação cancelada.", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
		} else { // Se nenhuma linha está selecionada no JTable.
			JOptionPane.showMessageDialog(this, "Selecione um produto.", "Mensagem", JOptionPane.WARNING_MESSAGE);
		}
	}

	
	private void btnBuscaCliente(){
		cliente = new CarrinhoController().buscaClienteByCpf(tfCpf.getText());
		String erro = new CarrinhoController().getExcecao();
		if (erro == null) {
			if(cliente != null) {
				tfNome.setText("Nome do cliente: " + cliente.getNome());
				tfNome.setVisible(true);
			}else {
				JOptionPane.showMessageDialog(this, "Não existe cliente cadastrado com esse cpf", "Mensagem", JOptionPane.WARNING_MESSAGE);
				tfNome.setVisible(false);
				tfCpf.setText("");
				
			}
		}else {
			String mensagem = "Não foi possível buscar o cliente:\n";
			mensagem = mensagem + erro;
			JOptionPane.showMessageDialog(this, mensagem, "Erros", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private void btnFecharPedidoAction() {
		//new itemPedido, vai receber a chave do pedido, do produto e a quantidade de cada produto.
		List<String> errosPedido = new ArrayList<String>();
		String erroItem = null;
				
		if(cliente == null) {
			errosPedido = new PedidoController().inserePedido(Calendar.getInstance(), new UsuarioController().getUsuarioAtual());
		} else {
			errosPedido = new PedidoController().inserePedidoComCliente(Calendar.getInstance(), new UsuarioController().getUsuarioAtual(), cliente);
		}
		
		erroItem =  new ItemPedidoController().insereItemPedidoDoCarrinho();
		System.out.println(erroItem);
		
			if (errosPedido.get(0) == null) { // Se o primeiro elemento do ArrayList for null.
				JOptionPane.showMessageDialog(this, "Pedido feito com sucesso!", 
						                      "Informação", JOptionPane.INFORMATION_MESSAGE);
				this.setVisible(false); // Fecha a janela.
				TelaPrincipal.btnCarrinhoEnabled(false);
			} else { // Se o primeiro elemento do ArrayList não for null.
				String mensagem = "Não foi possível realizar o pedido:\n";
				for (String e : errosPedido) // Cria uma mensagem contendo todos os erros armazenados no ArrayList.
					mensagem = mensagem + e + "\n";
				JOptionPane.showMessageDialog(this, mensagem, "Erros", JOptionPane.ERROR_MESSAGE);
			}
		
	}
	
	
	
	public void atualizaValorTotal() {
		//pagar a quantidade de cada produto no carrinho e multiplicar pelo seu preco 
		//guardar na variavel valorTotal
		int quantidade = 0;
		double preco = 0, valorTotal = 0;
		
		for (ItemCarrinho ic: produtosCarrinho) {
			quantidade = ic.getQuantidade();
			
			for(Produto p: produtos) {
				if (p.getId() == ic.getProduto().getId()) {
					preco = p.getPrecoVenda();
				}
			}
			valorTotal += quantidade*preco;
		}
		lblValorTotal.setText("R$" + String.valueOf(valorTotal));
	}
}
