package view.produto;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;

import controller.ProdutoController;
import model.categoria.Categoria;
import model.produto.Produto;

@SuppressWarnings("serial")
public class ConsultaProduto extends JDialog {
	private JLabel lbTitulo;
	private JTable tbProduto; // Tabela que exibir� os funcion�rios cadastrados no banco de dados.
	private ModeloTabelaProduto mtTabela; // Modelo que definir� a estrutura da tabela e permitir� o carregamento dos dados nela.
	private JScrollPane spTabela; // Painel de rolagem ao qual ser� vinculado o JTable.
	private JButton btAlterar;
	private JButton btExcluir;
	private Container cp; // Container para organizar os componentes na janela.

	public ConsultaProduto() { // Construtor.
		// Instancia��o e configura��o dos componentes de interface.
		setTitle("Consulta de Produtos"); // T�tulo da janela.
		setSize(700, 320); // Tamanho da janela em pixels.
		setLocationRelativeTo(null); // Centraliza a janela na tela.
		setModal(true); // Torna a janela "modal" (janela que n�o permite acesso a outras janelas abertas).
		
		String excecaoProdutos = null;
		String excecaoCategorias = null;
		
		List<Produto> produtos = new ProdutoController().selectProdutos();
		excecaoProdutos = new ProdutoController().getExcecao();
		
		List<Categoria> categorias = new ProdutoController().selectCategorias();
		excecaoCategorias = new ProdutoController().getExcecao();
		
		if (excecaoProdutos != null) { // Caso ocorra qualquer tipo de exce��o.
			JOptionPane.showMessageDialog(null, "N�o foi poss�vel recuperar os dados dos p:\n" + excecaoProdutos, 
					                      "Erro", JOptionPane.ERROR_MESSAGE);
			mtTabela = new ModeloTabelaProduto(); // Chama o construtor da classe FuncionarioModeloTabela, que apenas define a estrutura do JTable.
		} else if (excecaoCategorias != null) { // Caso ocorra qualquer tipo de exce��o.
			JOptionPane.showMessageDialog(null, "N�o foi poss�vel recuperar os dados dos cargos dos funcion�rios:\n" + excecaoCategorias, 
						                  "Erro", JOptionPane.ERROR_MESSAGE);
			mtTabela = new ModeloTabelaProduto(); // Chama o construtor da classe FuncionarioModeloTabela, que apenas define a estrutura do JTable.
		} else		
			// Chama o construtor da classe FuncionarioModeloTabela, que define a estrutura do JTable e carrega os dados nele.
			mtTabela = new ModeloTabelaProduto(produtos, categorias);
		
		lbTitulo = new JLabel("Consulta de Produtos");
		lbTitulo.setFont(new Font("Arial", Font.BOLD, 19)); // Ajusta a fonte do JLabel.
		
		// Inclui o modelo da tabela no JTable. Nesse momento, s�o chamados os m�todos da classe FuncionarioModeloTabela.
		tbProduto = new JTable(mtTabela);
		spTabela = new JScrollPane(tbProduto); // Vincula o JTable ao painel de rolagem.
		
		tbProduto.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Desabilita o dimensionamento autom�tico das colunas.
		// Configura a largura de cada coluna do JTable (em pixels).
		tbProduto.getColumnModel().getColumn(0).setPreferredWidth(50);
		tbProduto.getColumnModel().getColumn(1).setPreferredWidth(200);
		tbProduto.getColumnModel().getColumn(2).setPreferredWidth(100);
		tbProduto.getColumnModel().getColumn(3).setPreferredWidth(100);
		tbProduto.getColumnModel().getColumn(4).setPreferredWidth(100);
		tbProduto.getColumnModel().getColumn(5).setPreferredWidth(200);
		
		// Configura a fonte do cabe�alho do JTable.
		tbProduto.getTableHeader().setFont(new Font(null, Font.BOLD, 12));
		
		// Centraliza o conte�do da coluna referente ao Id do funcion�rio (�ndice 0).
		DefaultTableCellRenderer dtcrCentro = new DefaultTableCellRenderer();
		dtcrCentro.setHorizontalAlignment(SwingConstants.CENTER);
		tbProduto.getColumnModel().getColumn(0).setCellRenderer(dtcrCentro);
		
		DefaultTableCellRenderer dtcrLeft = new DefaultTableCellRenderer();
		dtcrLeft.setHorizontalAlignment(SwingConstants.LEFT);
		tbProduto.getColumnModel().getColumn(4).setCellRenderer(dtcrLeft);
		
		tbProduto.getTableHeader().setReorderingAllowed(false); // Desabilita a reordena��o das colunas do JTable.
		// Habilita o modo de sele��o simples, onde � poss�vel selecionar apenas uma linha de cada vez no JTable.
		tbProduto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		btAlterar = new JButton("Alterar");
		btExcluir = new JButton("Excluir");

		cp = getContentPane(); // Instancia o container da janela.
		cp.setLayout(null); // Configura o layout do container como nulo.
		cp.setBackground(new Color(180, 205, 205)); // Configura a cor de fundo do container.

		// Posicionamento dos componentes de interface na janela.
		lbTitulo.setBounds(215, 10, 300, 25); // x, y, largura, altura.
		spTabela.setBounds(20, 40, 645, 182);
		btAlterar.setBounds(215, 240, 100, 25);
		btExcluir.setBounds(355, 240, 100, 25);

		// Adi��o dos componentes de interface ao container.
		cp.add(lbTitulo);
		cp.add(spTabela);
		cp.add(btAlterar);
		cp.add(btExcluir);
		
		// Declara��o do processador de evento referente ao clique no bot�o Alterar.
		btAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btAlterarAction();
			}
		});
		
		// Declara��o do processador de evento referente ao clique no bot�o Excluir.
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btExcluirAction();
			}
		});
	} // Final do construtor.
	
	private void btAlterarAction() { // M�todo acionado pelo clique no bot�o Alterar.		
		if (tbProduto.getSelectedRow() != -1){ // Se uma linha est� selecionada no JTable.
			int linhaSelecionada = tbProduto.getSelectedRow(); // Recupera o �ndice da linha selecionada no JTable.
			
			// Recupera os dados de cada coluna da linha selecionada no JTable.
			int id = Integer.parseInt(tbProduto.getModel().getValueAt(linhaSelecionada, 0).toString());
			String nome = tbProduto.getModel().getValueAt(linhaSelecionada, 1).toString(); 
			String precoCusto = tbProduto.getModel().getValueAt(linhaSelecionada, 2).toString(); 
			String precoVenda = tbProduto.getModel().getValueAt(linhaSelecionada, 3).toString(); 
			String qtdEstoque = tbProduto.getModel().getValueAt(linhaSelecionada, 4).toString(); 
			String categoria = tbProduto.getModel().getValueAt(linhaSelecionada, 5).toString();

			
			SwingUtilities.invokeLater(new Runnable(){ // Chama o formul�rio de altera��o de funcion�rio.
				@Override
				public void run(){ new AlteracaoProduto(id, nome, precoCusto, precoVenda, qtdEstoque, categoria, linhaSelecionada, mtTabela).setVisible(true); }});
		} else { // Se nenhuma linha est� selecionada no JTable.
			JOptionPane.showMessageDialog(this, "Selecione um produto.", "Mensagem", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void btExcluirAction() { // M�todo acionado pelo clique no bot�o Excluir.
		if (tbProduto.getSelectedRow() != -1) { // Se uma linha est� selecionada no JTable.			
			// Mensagem para confirma��o da exclus�o do funcion�rio.
			int resposta = JOptionPane.showConfirmDialog(this, "Confirma a exclus�o?", "Confirma��o", 
					 									 JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (resposta == 0) { // Sim
				int linhaSelecionada = tbProduto.getSelectedRow(); // Recupera o �ndice da linha selecionada no JTable.
				
				// Recupera o id do funcion�rio presente na linha selecionada no JTable.
				int id = Integer.parseInt(tbProduto.getModel().getValueAt(linhaSelecionada, 0).toString()); // idFuncionario
				
				String erro = "";
				
				// Envia o id do funcion�rio ao controller. 
				// O controller retorna ent�o true ou false indicando se houve ou n�o sucesso na exclus�o.
				erro = new ProdutoController().excluiProduto(id);
				
				if (erro == null) { // Se a string for null.
					JOptionPane.showMessageDialog(this, "Produto exclu�do com sucesso.", 
		                                          "Informa��o", JOptionPane.INFORMATION_MESSAGE);
					mtTabela.removeProdutoTabela(linhaSelecionada); // Apaga o funcion�rio do JTable.
				} else { // Se a string n�o for null.
					String mensagem = "N�o foi poss�vel excluir o produto:\n";
				    mensagem = mensagem + erro + "\n";
					JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
				}
			} else if (resposta == 1) // N�o
				JOptionPane.showMessageDialog(this, "Opera��o cancelada.", "Confirma��o", JOptionPane.INFORMATION_MESSAGE);
		} else { // Se nenhuma linha est� selecionada no JTable.
			JOptionPane.showMessageDialog(this, "Selecione um produto.", "Mensagem", JOptionPane.WARNING_MESSAGE);
		}
	}
}