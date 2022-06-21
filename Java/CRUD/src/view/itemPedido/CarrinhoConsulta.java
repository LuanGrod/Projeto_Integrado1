package view.itemPedido;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import controller.CarrinhoController;
import controller.PedidoController;
import controller.UsuarioController;
import model.itemPedido.ItemCarrinho;
import model.produto.Produto;

public class CarrinhoConsulta extends JDialog {

	
	private static final long serialVersionUID = 1L;
	private JTable tbCarrinho; 
	private CarrinhoModeloTabela mtTabela;
	private JScrollPane spTabela;
	private Container cp;
	
	public CarrinhoConsulta() {
		setTitle("Consulta Carrinho"); // T�tulo da janela.
		setSize(449, 367); // Tamanho da janela em pixels.
		setLocationRelativeTo(null); // Centraliza a janela na tela.
		setModal(true); // Torna a janela "modal" (janela que n�o 
		
		JButton btnFecharPedido = new JButton("Fechar Pedido");
		btnFecharPedido.setBounds(148, 264, 136, 23);
		setLocationRelativeTo(null);

		
		//carregaTabela();
		//String excecaoItemCarrinho = null;
		//String excecaoProduto = null;
		
		List<ItemCarrinho> produtosCarrinho = new CarrinhoController().consultaItemCarrinho();
		//produtosCarrinho = new CarrinhoController().getExcecao();
		
		List <Produto> produtos = new CarrinhoController().consultaProdutosCarrinho();
		
		
		mtTabela = new CarrinhoModeloTabela(produtos, produtosCarrinho);
		
		
		tbCarrinho = new JTable(mtTabela);
		tbCarrinho.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		spTabela = new JScrollPane(tbCarrinho);
		spTabela.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		// Configura a largura de cada coluna do JTable (em pixels).
		tbCarrinho.getColumnModel().getColumn(0).setPreferredWidth(50);
		tbCarrinho.getColumnModel().getColumn(1).setPreferredWidth(160);
		tbCarrinho.getColumnModel().getColumn(2).setPreferredWidth(70);
		tbCarrinho.getColumnModel().getColumn(3).setPreferredWidth(70);
		
		// Configura a fonte do cabe�alho do JTable.
		tbCarrinho.getTableHeader().setFont(new Font(null, Font.BOLD, 12));
				
		// Centraliza o conte�do da coluna referente ao Id do funcion�rio (�ndice 0).
		DefaultTableCellRenderer dtcrCentro = new DefaultTableCellRenderer();
		dtcrCentro.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i<4; i++) {
			tbCarrinho.getColumnModel().getColumn(i).setCellRenderer(dtcrCentro);
		}
		
		tbCarrinho.getTableHeader().setReorderingAllowed(false); // Desabilita a reordena��o das colunas do JTable.
		// Habilita o modo de sele��o simples, onde � poss�vel selecionar apenas uma linha de cada vez no JTable.
		tbCarrinho.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		cp = getContentPane();
		cp.setLayout(null);
		
		// Posicionamento dos componentes de interface na janela.
		spTabela.setBounds(33, 41, 352, 182);

		// Adi��o dos componentes de interface ao container.
		cp.add(spTabela);
		cp.add(btnFecharPedido);

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
					                      "Informa��o", JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(false); // Fecha a janela.
		} else { // Se o primeiro elemento do ArrayList n�o for null.
			String mensagem = "N�o foi poss�vel realizar o pedido:\n";
			for (String e : erros) // Cria uma mensagem contendo todos os erros armazenados no ArrayList.
				mensagem = mensagem + e + "\n";
			JOptionPane.showMessageDialog(this, mensagem, "Erros", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
