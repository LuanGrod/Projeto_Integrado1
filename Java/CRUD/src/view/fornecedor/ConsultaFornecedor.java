package view.fornecedor;

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

import controller.FornecedorController;
import model.fornecedor.Fornecedor;


@SuppressWarnings("serial")
public class ConsultaFornecedor extends JDialog {
	private JLabel lbTitulo;
	private JTable tbFornecedor; // Tabela que exibirá os clientes cadastrados no banco de dados.
	private ModeloTabelaFornecedor mtTabela; // Modelo que definirá a estrutura da tabela e permitirá o carregamento dos dados nela.
	private JScrollPane spTabela; // Painel de rolagem ao qual será vinculado o JTable.
	private JButton btAlterar;
	private JButton btExcluir;
	private Container cp; // Container para organizar os componentes na janela.

	public ConsultaFornecedor() { // Construtor.
		// Instanciação e configuração dos componentes de interface.
		setTitle("Consulta de Fornecedores"); // Título da janela.
		setSize(700, 320); // Tamanho da janela em pixels.
		setLocationRelativeTo(null); // Centraliza a janela na tela.
		setModal(true); // Torna a janela "modal" (janela que não permite acesso a outras janelas abertas).
		
		String excecaoFornecedores = null;
		
		List<Fornecedor> fornecedores = new FornecedorController().consultaFornecedores();
		excecaoFornecedores = new FornecedorController().getExcecao();
		
		if (excecaoFornecedores != null) { // Caso ocorra qualquer tipo de exceção.
			JOptionPane.showMessageDialog(null, "Não foi possível recuperar os dados dos fornecedores:\n" + excecaoFornecedores, 
					                      "Erro", JOptionPane.ERROR_MESSAGE);
			mtTabela = new ModeloTabelaFornecedor(); // Chama o construtor da classe ModeloTabelaCliente, que apenas define a estrutura do JTable.
		} else		
			// Chama o construtor da classe ModeloTabelaCliente, que define a estrutura do JTable e carrega os dados nele.
			mtTabela = new ModeloTabelaFornecedor(fornecedores);
		
		lbTitulo = new JLabel("Consulta de Fornecedores");
		lbTitulo.setFont(new Font("Arial", Font.BOLD, 19)); // Ajusta a fonte do JLabel.
		
		// Inclui o modelo da tabela no JTable. Nesse momento, são chamados os métodos da classe FuncionarioModeloTabela.
		tbFornecedor = new JTable(mtTabela);
		spTabela = new JScrollPane(tbFornecedor); // Vincula o JTable ao painel de rolagem.
		
		tbFornecedor.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Desabilita o dimensionamento automático das colunas.
		// Configura a largura de cada coluna do JTable (em pixels).
		tbFornecedor.getColumnModel().getColumn(0).setPreferredWidth(50);
		tbFornecedor.getColumnModel().getColumn(1).setPreferredWidth(150);
		tbFornecedor.getColumnModel().getColumn(2).setPreferredWidth(100);
		tbFornecedor.getColumnModel().getColumn(3).setPreferredWidth(200);
		tbFornecedor.getColumnModel().getColumn(4).setPreferredWidth(200);
		
		// Configura a fonte do cabeçalho do JTable.
		tbFornecedor.getTableHeader().setFont(new Font(null, Font.BOLD, 12));
		
		// Centraliza o conteúdo da coluna referente ao Id do funcionário (índice 0).
		DefaultTableCellRenderer dtcrCentro = new DefaultTableCellRenderer();
		dtcrCentro.setHorizontalAlignment(SwingConstants.CENTER);
		tbFornecedor.getColumnModel().getColumn(0).setCellRenderer(dtcrCentro);
		
		tbFornecedor.getTableHeader().setReorderingAllowed(false); // Desabilita a reordenação das colunas do JTable.
		// Habilita o modo de seleção simples, onde é possível selecionar apenas uma linha de cada vez no JTable.
		tbFornecedor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
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
		if (tbFornecedor.getSelectedRow() != -1){ // Se uma linha está selecionada no JTable.
			int linhaSelecionada = tbFornecedor.getSelectedRow(); // Recupera o índice da linha selecionada no JTable.
			
			// Recupera os dados de cada coluna da linha selecionada no JTable.
			int id = Integer.parseInt(tbFornecedor.getModel().getValueAt(linhaSelecionada, 0).toString()); 
			String cnpj = tbFornecedor.getModel().getValueAt(linhaSelecionada, 1).toString(); 
			String telefone = tbFornecedor.getModel().getValueAt(linhaSelecionada, 2).toString(); 
			String email = tbFornecedor.getModel().getValueAt(linhaSelecionada, 3).toString();
			String nome = tbFornecedor.getModel().getValueAt(linhaSelecionada, 4).toString(); 
			
			SwingUtilities.invokeLater(new Runnable(){ // Chama o formulário de alteração de funcionário.
				@Override
				public void run(){ new AlteracaoFornecedor(id, cnpj, telefone, email, nome, 
						                                    linhaSelecionada, mtTabela).setVisible(true); }});
		} else { // Se nenhuma linha está selecionada no JTable.
			JOptionPane.showMessageDialog(this, "Selecione um fornecedor.", "Mensagem", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void btExcluirAction() { // Método acionado pelo clique no botão Excluir.
		if (tbFornecedor.getSelectedRow() != -1) { // Se uma linha está selecionada no JTable.			
			// Mensagem para confirmação da exclusão do funcionário.
			int resposta = JOptionPane.showConfirmDialog(this, "Confirma a exclusão?", "Confirmação", 
					 									 JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (resposta == 0) { // Sim
				int linhaSelecionada = tbFornecedor.getSelectedRow(); // Recupera o índice da linha selecionada no JTable.
				
				// Recupera o id do funcionário presente na linha selecionada no JTable.
				int id = Integer.parseInt(tbFornecedor.getModel().getValueAt(linhaSelecionada, 0).toString()); // idFuncionario
				
				String erro = "";
				
				// Envia o id do funcionário ao controller. 
				// O controller retorna então true ou false indicando se houve ou não sucesso na exclusão.
				erro = new FornecedorController().excluiFornecedor(id);
				
				if (erro == null) { // Se a string for null.
					JOptionPane.showMessageDialog(this, "Fornecedor excluído com sucesso.", 
		                                          "Informação", JOptionPane.INFORMATION_MESSAGE);
					mtTabela.removeFornecedorTabela(linhaSelecionada); // Apaga o funcionário do JTable.
				} else { // Se a string não for null.
					String mensagem = "Não foi possível excluir o fornecedor:\n";
				    mensagem = mensagem + erro + "\n";
					JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
				}
			} else if (resposta == 1) // Não
				JOptionPane.showMessageDialog(this, "Operação cancelada.", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
		} else { // Se nenhuma linha está selecionada no JTable.
			JOptionPane.showMessageDialog(this, "Selecione um fornecedor.", "Mensagem", JOptionPane.WARNING_MESSAGE);
		}
	}
}