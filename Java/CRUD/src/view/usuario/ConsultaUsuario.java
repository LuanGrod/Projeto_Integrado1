package view.usuario;

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

import controller.UsuarioController;
import model.cargo.Cargo;
import model.usuario.Usuario;


@SuppressWarnings("serial")
public class ConsultaUsuario extends JDialog {
	private JLabel lbTitulo;
	private JTable tbUsuario; // Tabela que exibirá os funcionários cadastrados no banco de dados.
	private ModeloTabelaUsuario mtTabela; // Modelo que definirá a estrutura da tabela e permitirá o carregamento dos dados nela.
	private JScrollPane spTabela; // Painel de rolagem ao qual será vinculado o JTable.
	private JButton btAlterar;
	private JButton btExcluir;
	private Container cp; // Container para organizar os componentes na janela.

	public ConsultaUsuario() { // Construtor.
		// Instanciação e configuração dos componentes de interface.
		setTitle("Consulta de Funcionários"); // Título da janela.
		setSize(700, 320); // Tamanho da janela em pixels.
		setLocationRelativeTo(null); // Centraliza a janela na tela.
		setModal(true); // Torna a janela "modal" (janela que não permite acesso a outras janelas abertas).
		
		String excecaoUsuarios = null;
		String excecaoCargos = null;
		
		List<Usuario> usuarios = new UsuarioController().selectUsuarios();
		excecaoUsuarios = new UsuarioController().getExcecao();
		
		List<Cargo> cargos = new UsuarioController().selectCargos();
		excecaoCargos = new UsuarioController().getExcecao();
		
		if (excecaoUsuarios != null) { // Caso ocorra qualquer tipo de exceção.
			JOptionPane.showMessageDialog(null, "Não foi possível recuperar os dados dos funcionários:\n" + excecaoUsuarios, 
					                      "Erro", JOptionPane.ERROR_MESSAGE);
			mtTabela = new ModeloTabelaUsuario(); // Chama o construtor da classe FuncionarioModeloTabela, que apenas define a estrutura do JTable.
		} else if (excecaoCargos != null) { // Caso ocorra qualquer tipo de exceção.
			JOptionPane.showMessageDialog(null, "Não foi possível recuperar os dados dos cargos dos funcionários:\n" + excecaoCargos, 
						                  "Erro", JOptionPane.ERROR_MESSAGE);
			mtTabela = new ModeloTabelaUsuario(); // Chama o construtor da classe FuncionarioModeloTabela, que apenas define a estrutura do JTable.
		} else		
			// Chama o construtor da classe FuncionarioModeloTabela, que define a estrutura do JTable e carrega os dados nele.
			mtTabela = new ModeloTabelaUsuario(usuarios, cargos);
		
		lbTitulo = new JLabel("Consulta de Funcionários");
		lbTitulo.setFont(new Font("Arial", Font.BOLD, 19)); // Ajusta a fonte do JLabel.
		
		// Inclui o modelo da tabela no JTable. Nesse momento, são chamados os métodos da classe FuncionarioModeloTabela.
		tbUsuario = new JTable(mtTabela);
		spTabela = new JScrollPane(tbUsuario); // Vincula o JTable ao painel de rolagem.
		
		tbUsuario.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Desabilita o dimensionamento automático das colunas.
		// Configura a largura de cada coluna do JTable (em pixels).
		tbUsuario.getColumnModel().getColumn(0).setPreferredWidth(50);
		tbUsuario.getColumnModel().getColumn(1).setPreferredWidth(100);
		tbUsuario.getColumnModel().getColumn(2).setPreferredWidth(100);
		tbUsuario.getColumnModel().getColumn(3).setPreferredWidth(100);
		tbUsuario.getColumnModel().getColumn(4).setPreferredWidth(200);
		tbUsuario.getColumnModel().getColumn(5).setPreferredWidth(100);
		tbUsuario.getColumnModel().getColumn(6).setPreferredWidth(200);
		tbUsuario.getColumnModel().getColumn(7).setPreferredWidth(100);
		tbUsuario.getColumnModel().getColumn(8).setPreferredWidth(100);
		tbUsuario.getColumnModel().getColumn(9).setPreferredWidth(100);
		tbUsuario.getColumnModel().getColumn(10).setPreferredWidth(100);
		tbUsuario.getColumnModel().getColumn(11).setPreferredWidth(100);
		tbUsuario.getColumnModel().getColumn(12).setPreferredWidth(200);
		
		// Configura a fonte do cabeçalho do JTable.
		tbUsuario.getTableHeader().setFont(new Font(null, Font.BOLD, 12));
		
		// Centraliza o conteúdo da coluna referente ao Id do funcionário (índice 0).
		DefaultTableCellRenderer dtcrCentro = new DefaultTableCellRenderer();
		dtcrCentro.setHorizontalAlignment(SwingConstants.CENTER);
		tbUsuario.getColumnModel().getColumn(0).setCellRenderer(dtcrCentro);
		
		
		tbUsuario.getTableHeader().setReorderingAllowed(false); // Desabilita a reordenação das colunas do JTable.
		// Habilita o modo de seleção simples, onde é possível selecionar apenas uma linha de cada vez no JTable.
		tbUsuario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
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
		if (tbUsuario.getSelectedRow() != -1){ // Se uma linha está selecionada no JTable.
			int linhaSelecionada = tbUsuario.getSelectedRow(); // Recupera o índice da linha selecionada no JTable.
			
			// Recupera os dados de cada coluna da linha selecionada no JTable.
			int id = Integer.parseInt(tbUsuario.getModel().getValueAt(linhaSelecionada, 0).toString()); 
			String login = tbUsuario.getModel().getValueAt(linhaSelecionada, 1).toString();
			String senha = tbUsuario.getModel().getValueAt(linhaSelecionada, 2).toString();
			String cpf = tbUsuario.getModel().getValueAt(linhaSelecionada, 3).toString();
			String nome = tbUsuario.getModel().getValueAt(linhaSelecionada, 4).toString();
			String telefone = tbUsuario.getModel().getValueAt(linhaSelecionada, 5).toString();
			String email = tbUsuario.getModel().getValueAt(linhaSelecionada, 6).toString();
			String rua = tbUsuario.getModel().getValueAt(linhaSelecionada, 7).toString();
			String bairro = tbUsuario.getModel().getValueAt(linhaSelecionada, 8).toString();
			String cidade = tbUsuario.getModel().getValueAt(linhaSelecionada, 9).toString();
			String cep = tbUsuario.getModel().getValueAt(linhaSelecionada, 10).toString();
			String estado = tbUsuario.getModel().getValueAt(linhaSelecionada, 11).toString();
			String cargo = tbUsuario.getModel().getValueAt(linhaSelecionada, 12).toString();
			
			
			SwingUtilities.invokeLater(new Runnable(){ // Chama o formulário de alteração de funcionário.
				@Override
				public void run(){ new AlteracaoUsuario(id, login, senha, cpf, nome, telefone, email, rua, bairro, cidade, cep, estado, cargo, 
						                                linhaSelecionada, mtTabela).setVisible(true); }});
		} else { // Se nenhuma linha está selecionada no JTable.
			JOptionPane.showMessageDialog(this, "Selecione um funcionário.", "Mensagem", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void btExcluirAction() { // Método acionado pelo clique no botão Excluir.
		if (tbUsuario.getSelectedRow() != -1) { // Se uma linha está selecionada no JTable.			
			// Mensagem para confirmação da exclusão do funcionário.
			int resposta = JOptionPane.showConfirmDialog(this, "Confirma a exclusão?", "Confirmação", 
					 									 JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (resposta == 0) { // Sim
				int linhaSelecionada = tbUsuario.getSelectedRow(); // Recupera o índice da linha selecionada no JTable.
				
				// Recupera o id do funcionário presente na linha selecionada no JTable.
				int id = Integer.parseInt(tbUsuario.getModel().getValueAt(linhaSelecionada, 0).toString()); // idFuncionario
				
				String erro = "";
				
				// Envia o id do funcionário ao controller. 
				// O controller retorna então true ou false indicando se houve ou não sucesso na exclusão.
				erro = new UsuarioController().excluiUsuario(id);
				
				if (erro == null) { // Se a string for null.
					JOptionPane.showMessageDialog(this, "Funcionário excluído com sucesso.", 
		                                          "Informação", JOptionPane.INFORMATION_MESSAGE);
					mtTabela.removeUsuarioTabela(linhaSelecionada); // Apaga o funcionário do JTable.
				} else { // Se a string não for null.
					String mensagem = "Não foi possível excluir o funcionário:\n";
				    mensagem = mensagem + erro + "\n";
					JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
				}
			} else if (resposta == 1) // Não
				JOptionPane.showMessageDialog(this, "Operação cancelada.", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
		} else { // Se nenhuma linha está selecionada no JTable.
			JOptionPane.showMessageDialog(this, "Selecione um funcionário.", "Mensagem", JOptionPane.WARNING_MESSAGE);
		}
	}
}