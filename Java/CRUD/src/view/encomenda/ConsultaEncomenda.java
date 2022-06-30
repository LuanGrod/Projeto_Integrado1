package view.encomenda;

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
import javax.swing.table.DefaultTableCellRenderer;

import controller.EncomendaController;
import model.encomenda.Encomenda;
import model.fornecedor.Fornecedor;
import model.produto.Produto;

@SuppressWarnings("serial")
public class ConsultaEncomenda extends JDialog {
	private JLabel lbTitulo;
	private JTable tbEncomenda; // Tabela que exibir� os funcion�rios cadastrados no banco de dados.
	private ModeloTabelaEncomenda mtTabela; // Modelo que definir� a estrutura da tabela e permitir� o carregamento dos dados nela.
	private JScrollPane spTabela; // Painel de rolagem ao qual ser� vinculado o JTable.
	private JButton btAlterar;
	private JButton btExcluir;
	private Container cp; // Container para organizar os componentes na janela.

	public ConsultaEncomenda() { // Construtor.
		// Instancia��o e configura��o dos componentes de interface.
		setTitle("Consulta de Encomendas"); // T�tulo da janela.
		setSize(700, 320); // Tamanho da janela em pixels.
		setLocationRelativeTo(null); // Centraliza a janela na tela.
		setModal(true); // Torna a janela "modal" (janela que n�o permite acesso a outras janelas abertas).
		
		String excecaoEncomendas = null;
		String excecaoProdutos = null;
		String excecaoFornecedores = null;
		
		List<Encomenda> encomendas = new EncomendaController().consultaEncomenda();
		excecaoEncomendas = new EncomendaController().getExcecao();
		
		List<Produto> produtos = new EncomendaController().consultaProdutos();
		excecaoProdutos = new EncomendaController().getExcecao();
		
		List<Fornecedor> fornecedores = new EncomendaController().consultaFornecedores();
		excecaoFornecedores = new EncomendaController().getExcecao();
		
		if (excecaoEncomendas != null) { // Caso ocorra qualquer tipo de exce��o.
			JOptionPane.showMessageDialog(null, "N�o foi poss�vel recuperar os dados das encomendas:\n" + excecaoEncomendas, 
					                      "Erro", JOptionPane.ERROR_MESSAGE);
			mtTabela = new ModeloTabelaEncomenda(); // Chama o construtor da classe FuncionarioModeloTabela, que apenas define a estrutura do JTable.
		} else if (excecaoProdutos != null) { // Caso ocorra qualquer tipo de exce��o.
			JOptionPane.showMessageDialog(null, "N�o foi poss�vel recuperar os dados dos produtos das encomendas:\n" + excecaoProdutos, 
						                  "Erro", JOptionPane.ERROR_MESSAGE);
			mtTabela = new ModeloTabelaEncomenda(); // Chama o construtor da classe FuncionarioModeloTabela, que apenas define a estrutura do JTable.
		} else if (excecaoFornecedores != null) { // Caso ocorra qualquer tipo de exce��o.
			JOptionPane.showMessageDialog(null, "N�o foi poss�vel recuperar os dados dos fornecedores das encomendas:\n" + excecaoFornecedores, 
	                  "Erro", JOptionPane.ERROR_MESSAGE);
			mtTabela = new ModeloTabelaEncomenda(); // Chama o construtor da classe FuncionarioModeloTabela, que apenas define a estrutura do JTable.
		} else		
			// Chama o construtor da classe FuncionarioModeloTabela, que define a estrutura do JTable e carrega os dados nele.
			mtTabela = new ModeloTabelaEncomenda(encomendas, produtos, fornecedores);
		
		lbTitulo = new JLabel("Consulta de Encomendas");
		lbTitulo.setFont(new Font("Arial", Font.BOLD, 19)); // Ajusta a fonte do JLabel.
		
		// Inclui o modelo da tabela no JTable. Nesse momento, s�o chamados os m�todos da classe FuncionarioModeloTabela.
		tbEncomenda = new JTable(mtTabela);
		spTabela = new JScrollPane(tbEncomenda); // Vincula o JTable ao painel de rolagem.
		
		tbEncomenda.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Desabilita o dimensionamento autom�tico das colunas.
		// Configura a largura de cada coluna do JTable (em pixels).
		tbEncomenda.getColumnModel().getColumn(0).setPreferredWidth(50);
		tbEncomenda.getColumnModel().getColumn(1).setPreferredWidth(200);
		tbEncomenda.getColumnModel().getColumn(2).setPreferredWidth(200);
		tbEncomenda.getColumnModel().getColumn(3).setPreferredWidth(100);
		tbEncomenda.getColumnModel().getColumn(4).setPreferredWidth(200);
		
		// Configura a fonte do cabe�alho do JTable.
		tbEncomenda.getTableHeader().setFont(new Font(null, Font.BOLD, 12));
		
		// Centraliza o conte�do da coluna referente ao Id do funcion�rio (�ndice 0).
		DefaultTableCellRenderer dtcrCentro = new DefaultTableCellRenderer();
		dtcrCentro.setHorizontalAlignment(SwingConstants.CENTER);
		tbEncomenda.getColumnModel().getColumn(0).setCellRenderer(dtcrCentro);
		
		// Centraliza o conte�do da coluna referente ao Id do funcion�rio (�ndice 0).
		DefaultTableCellRenderer dtcrEsquerda = new DefaultTableCellRenderer();
		dtcrEsquerda.setHorizontalAlignment(SwingConstants.LEFT);
		tbEncomenda.getColumnModel().getColumn(3).setCellRenderer(dtcrEsquerda);
		
		tbEncomenda.getTableHeader().setReorderingAllowed(false); // Desabilita a reordena��o das colunas do JTable.
		// Habilita o modo de sele��o simples, onde � poss�vel selecionar apenas uma linha de cada vez no JTable.
		tbEncomenda.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		btAlterar = new JButton("Entregue");
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
		if (tbEncomenda.getSelectedRow() != -1){ // Se uma linha est� selecionada no JTable.
			int linhaSelecionada = tbEncomenda.getSelectedRow(); // Recupera o �ndice da linha selecionada no JTable.
			
			// Recupera os dados de cada coluna da linha selecionada no JTable.
			int id = Integer.parseInt(tbEncomenda.getModel().getValueAt(linhaSelecionada, 0).toString()); 
//			String produto = tbEncomenda.getModel().getValueAt(linhaSelecionada, 1).toString();
//			String fornecedor = tbEncomenda.getModel().getValueAt(linhaSelecionada, 2).toString();
			int quantidade = Integer.parseInt(tbEncomenda.getModel().getValueAt(linhaSelecionada, 3).toString());
//			String situacao = tbEncomenda.getModel().getValueAt(linhaSelecionada, 4).toString();
			
			String situacao = new String();
			situacao = "Entregue";
			
			String erro = "";
			erro = new EncomendaController().entregueEncomenda(id, quantidade);
			
			if (erro == null) { // Se a string for null.
				JOptionPane.showMessageDialog(this, "Encomenda atualizada com sucesso.", 
	                                          "Informa��o", JOptionPane.INFORMATION_MESSAGE);
				tbEncomenda.getModel().setValueAt(situacao, linhaSelecionada, 4);
			} else { // Se a string n�o for null.
				String mensagem = "N�o foi poss�vel atualizar a encomenda:\n";
			    mensagem = mensagem + erro + "\n";
				JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
			}
		} else { // Se nenhuma linha est� selecionada no JTable.
			JOptionPane.showMessageDialog(this, "Selecione uma encomenda.", "Mensagem", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void btExcluirAction() { // M�todo acionado pelo clique no bot�o Excluir.
		if (tbEncomenda.getSelectedRow() != -1) { // Se uma linha est� selecionada no JTable.			
			// Mensagem para confirma��o da exclus�o do funcion�rio.
			int resposta = JOptionPane.showConfirmDialog(this, "Confirma a exclus�o?", "Confirma��o", 
					 									 JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (resposta == 0) { // Sim
				int linhaSelecionada = tbEncomenda.getSelectedRow(); // Recupera o �ndice da linha selecionada no JTable.
				
				// Recupera o id do funcion�rio presente na linha selecionada no JTable.
				int id = Integer.parseInt(tbEncomenda.getModel().getValueAt(linhaSelecionada, 0).toString()); // idFuncionario
				
				String erro = "";
				
				// Envia o id do funcion�rio ao controller. 
				// O controller retorna ent�o true ou false indicando se houve ou n�o sucesso na exclus�o.
				erro = new EncomendaController().excluiEncomenda(id);
				
				if (erro == null) { // Se a string for null.
					JOptionPane.showMessageDialog(this, "Encomenda exclu�da com sucesso.", 
		                                          "Informa��o", JOptionPane.INFORMATION_MESSAGE);
					mtTabela.removeEncomendaTabela(linhaSelecionada); // Apaga o funcion�rio do JTable.
				} else { // Se a string n�o for null.
					String mensagem = "N�o foi poss�vel excluir a encomenda:\n";
				    mensagem = mensagem + erro + "\n";
					JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
				}
			} else if (resposta == 1) // N�o
				JOptionPane.showMessageDialog(this, "Opera��o cancelada.", "Confirma��o", JOptionPane.INFORMATION_MESSAGE);
		} else { // Se nenhuma linha est� selecionada no JTable.
			JOptionPane.showMessageDialog(this, "Selecione uma encomenda.", "Mensagem", JOptionPane.WARNING_MESSAGE);
		}
	}
}