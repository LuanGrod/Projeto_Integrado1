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
	private JTable tbProduto; // Tabela que exibirá os funcionários cadastrados no banco de dados.
	private ModeloTabelaProduto mtTabela; // Modelo que definirá a estrutura da tabela e permitirá o carregamento dos dados nela.
	private JScrollPane spTabela; // Painel de rolagem ao qual será vinculado o JTable.
	private JButton btAlterar;
	private JButton btExcluir;
	private Container cp; // Container para organizar os componentes na janela.

	public ConsultaProduto() { // Construtor.
		// Instanciação e configuração dos componentes de interface.
		setTitle("Consulta de Produtos"); // Título da janela.
		setSize(700, 320); // Tamanho da janela em pixels.
		setLocationRelativeTo(null); // Centraliza a janela na tela.
		setModal(true); // Torna a janela "modal" (janela que não permite acesso a outras janelas abertas).
		
		String excecaoProdutos = null;
		String excecaoCategorias = null;
		
		List<Produto> produtos = new ProdutoController().selectProdutos();
		excecaoProdutos = new ProdutoController().getExcecao();
		
		List<Categoria> categorias = new ProdutoController().selectCategorias();
		excecaoCategorias = new ProdutoController().getExcecao();
		
		if (excecaoProdutos != null) { // Caso ocorra qualquer tipo de exceção.
			JOptionPane.showMessageDialog(null, "Não foi possível recuperar os dados dos p:\n" + excecaoProdutos, 
					                      "Erro", JOptionPane.ERROR_MESSAGE);
			mtTabela = new ModeloTabelaProduto(); // Chama o construtor da classe FuncionarioModeloTabela, que apenas define a estrutura do JTable.
		} else if (excecaoCategorias != null) { // Caso ocorra qualquer tipo de exceção.
			JOptionPane.showMessageDialog(null, "Não foi possível recuperar os dados dos cargos dos funcionários:\n" + excecaoCategorias, 
						                  "Erro", JOptionPane.ERROR_MESSAGE);
			mtTabela = new ModeloTabelaProduto(); // Chama o construtor da classe FuncionarioModeloTabela, que apenas define a estrutura do JTable.
		} else		
			// Chama o construtor da classe FuncionarioModeloTabela, que define a estrutura do JTable e carrega os dados nele.
			mtTabela = new ModeloTabelaProduto(produtos, categorias);
		
		lbTitulo = new JLabel("Consulta de Produtos");
		lbTitulo.setFont(new Font("Arial", Font.BOLD, 19)); // Ajusta a fonte do JLabel.
		
		// Inclui o modelo da tabela no JTable. Nesse momento, são chamados os métodos da classe FuncionarioModeloTabela.
		tbProduto = new JTable(mtTabela);
		spTabela = new JScrollPane(tbProduto); // Vincula o JTable ao painel de rolagem.
		
		tbProduto.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Desabilita o dimensionamento automático das colunas.
		// Configura a largura de cada coluna do JTable (em pixels).
		tbProduto.getColumnModel().getColumn(0).setPreferredWidth(50);
		tbProduto.getColumnModel().getColumn(1).setPreferredWidth(200);
		tbProduto.getColumnModel().getColumn(2).setPreferredWidth(100);
		tbProduto.getColumnModel().getColumn(3).setPreferredWidth(100);
		tbProduto.getColumnModel().getColumn(4).setPreferredWidth(100);
		tbProduto.getColumnModel().getColumn(5).setPreferredWidth(200);
		
		// Configura a fonte do cabeçalho do JTable.
		tbProduto.getTableHeader().setFont(new Font(null, Font.BOLD, 12));
		
		// Centraliza o conteúdo da coluna referente ao Id do funcionário (índice 0).
		DefaultTableCellRenderer dtcrCentro = new DefaultTableCellRenderer();
		dtcrCentro.setHorizontalAlignment(SwingConstants.CENTER);
		tbProduto.getColumnModel().getColumn(0).setCellRenderer(dtcrCentro);
		
		DefaultTableCellRenderer dtcrLeft = new DefaultTableCellRenderer();
		dtcrLeft.setHorizontalAlignment(SwingConstants.LEFT);
		tbProduto.getColumnModel().getColumn(4).setCellRenderer(dtcrLeft);
		
		tbProduto.getTableHeader().setReorderingAllowed(false); // Desabilita a reordenação das colunas do JTable.
		// Habilita o modo de seleção simples, onde é possível selecionar apenas uma linha de cada vez no JTable.
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

		// Adição dos componentes de interface ao container.
		cp.add(lbTitulo);
		cp.add(spTabela);
		cp.add(btAlterar);
		cp.add(btExcluir);
		
		// Declaração do processador de evento referente ao clique no botão Alterar.
		btAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btAlterarAction();
			}
		});
		
		// Declaração do processador de evento referente ao clique no botão Excluir.
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btExcluirAction();
			}
		});
	} // Final do construtor.
	
	private void btAlterarAction() { // Método acionado pelo clique no botão Alterar.		
		if (tbProduto.getSelectedRow() != -1){ // Se uma linha está selecionada no JTable.
			int linhaSelecionada = tbProduto.getSelectedRow(); // Recupera o índice da linha selecionada no JTable.
			
			// Recupera os dados de cada coluna da linha selecionada no JTable.
			int id = Integer.parseInt(tbProduto.getModel().getValueAt(linhaSelecionada, 0).toString());
			String nome = tbProduto.getModel().getValueAt(linhaSelecionada, 1).toString(); 
			String precoCusto = tbProduto.getModel().getValueAt(linhaSelecionada, 2).toString(); 
			String precoVenda = tbProduto.getModel().getValueAt(linhaSelecionada, 3).toString(); 
			String qtdEstoque = tbProduto.getModel().getValueAt(linhaSelecionada, 4).toString(); 
			String categoria = tbProduto.getModel().getValueAt(linhaSelecionada, 5).toString();

			
			SwingUtilities.invokeLater(new Runnable(){ // Chama o formulário de alteração de funcionário.
				@Override
				public void run(){ new AlteracaoProduto(id, nome, precoCusto, precoVenda, qtdEstoque, categoria, linhaSelecionada, mtTabela).setVisible(true); }});
		} else { // Se nenhuma linha está selecionada no JTable.
			JOptionPane.showMessageDialog(this, "Selecione um produto.", "Mensagem", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void btExcluirAction() { // Método acionado pelo clique no botão Excluir.
		if (tbProduto.getSelectedRow() != -1) { // Se uma linha está selecionada no JTable.			
			// Mensagem para confirmação da exclusão do funcionário.
			int resposta = JOptionPane.showConfirmDialog(this, "Confirma a exclusão?", "Confirmação", 
					 									 JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (resposta == 0) { // Sim
				int linhaSelecionada = tbProduto.getSelectedRow(); // Recupera o índice da linha selecionada no JTable.
				
				// Recupera o id do funcionário presente na linha selecionada no JTable.
				int id = Integer.parseInt(tbProduto.getModel().getValueAt(linhaSelecionada, 0).toString()); // idFuncionario
				
				String erro = "";
				
				// Envia o id do funcionário ao controller. 
				// O controller retorna então true ou false indicando se houve ou não sucesso na exclusão.
				erro = new ProdutoController().excluiProduto(id);
				
				if (erro == null) { // Se a string for null.
					JOptionPane.showMessageDialog(this, "Produto excluído com sucesso.", 
		                                          "Informação", JOptionPane.INFORMATION_MESSAGE);
					mtTabela.removeProdutoTabela(linhaSelecionada); // Apaga o funcionário do JTable.
				} else { // Se a string não for null.
					String mensagem = "Não foi possível excluir o produto:\n";
				    mensagem = mensagem + erro + "\n";
					JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
				}
			} else if (resposta == 1) // Não
				JOptionPane.showMessageDialog(this, "Operação cancelada.", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
		} else { // Se nenhuma linha está selecionada no JTable.
			JOptionPane.showMessageDialog(this, "Selecione um produto.", "Mensagem", JOptionPane.WARNING_MESSAGE);
		}
	}
}