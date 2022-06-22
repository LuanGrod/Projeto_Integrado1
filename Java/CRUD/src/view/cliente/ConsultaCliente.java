package view.cliente;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import controller.ClienteController;
import model.cliente.Cliente;

@SuppressWarnings("serial")
public class ConsultaCliente extends JDialog {
	private JLabel lbTitulo;
	private JTable tbCliente; // Tabela que exibir� os clientes cadastrados no banco de dados.
	private ModeloTabelaCliente mtTabela; // Modelo que definir� a estrutura da tabela e permitir� o carregamento dos dados nela.
	private JScrollPane spTabela; // Painel de rolagem ao qual ser� vinculado o JTable.
	private Container cp; // Container para organizar os componentes na janela.

	public ConsultaCliente() { // Construtor.
		// Instancia��o e configura��o dos componentes de interface.
		setTitle("Consulta de Clientes"); // T�tulo da janela.
		setSize(700, 320); // Tamanho da janela em pixels.
		setLocationRelativeTo(null); // Centraliza a janela na tela.
		setModal(true); // Torna a janela "modal" (janela que n�o permite acesso a outras janelas abertas).
		
		String excecaoClientes = null;
		
		List<Cliente> clientes = new ClienteController().consultaClientes();
		excecaoClientes = new ClienteController().getExcecao();
		
		if (excecaoClientes != null) { // Caso ocorra qualquer tipo de exce��o.
			JOptionPane.showMessageDialog(null, "N�o foi poss�vel recuperar os dados dos clientes:\n" + excecaoClientes, 
					                      "Erro", JOptionPane.ERROR_MESSAGE);
			mtTabela = new ModeloTabelaCliente(); // Chama o construtor da classe ModeloTabelaCliente, que apenas define a estrutura do JTable.
		} else		
			// Chama o construtor da classe ModeloTabelaCliente, que define a estrutura do JTable e carrega os dados nele.
			mtTabela = new ModeloTabelaCliente(clientes);
		
		lbTitulo = new JLabel("Consulta de Clientes");
		lbTitulo.setFont(new Font("Arial", Font.BOLD, 19)); // Ajusta a fonte do JLabel.
		
		// Inclui o modelo da tabela no JTable. Nesse momento, s�o chamados os m�todos da classe FuncionarioModeloTabela.
		tbCliente = new JTable(mtTabela);
		spTabela = new JScrollPane(tbCliente); // Vincula o JTable ao painel de rolagem.
		
		tbCliente.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Desabilita o dimensionamento autom�tico das colunas.
		// Configura a largura de cada coluna do JTable (em pixels).
		tbCliente.getColumnModel().getColumn(0).setPreferredWidth(50);
		tbCliente.getColumnModel().getColumn(1).setPreferredWidth(200);
		tbCliente.getColumnModel().getColumn(2).setPreferredWidth(100);
		tbCliente.getColumnModel().getColumn(3).setPreferredWidth(100);
		tbCliente.getColumnModel().getColumn(4).setPreferredWidth(200);
		tbCliente.getColumnModel().getColumn(5).setPreferredWidth(160);
		tbCliente.getColumnModel().getColumn(6).setPreferredWidth(140);
		tbCliente.getColumnModel().getColumn(7).setPreferredWidth(100);
		tbCliente.getColumnModel().getColumn(8).setPreferredWidth(100);
		tbCliente.getColumnModel().getColumn(9).setPreferredWidth(100);
		
		// Configura a fonte do cabe�alho do JTable.
		tbCliente.getTableHeader().setFont(new Font(null, Font.BOLD, 12));
		
		// Centraliza o conte�do da coluna referente ao Id do funcion�rio (�ndice 0).
		DefaultTableCellRenderer dtcrCentro = new DefaultTableCellRenderer();
		dtcrCentro.setHorizontalAlignment(SwingConstants.CENTER);
		tbCliente.getColumnModel().getColumn(0).setCellRenderer(dtcrCentro);
		
		tbCliente.getTableHeader().setReorderingAllowed(false); // Desabilita a reordena��o das colunas do JTable.
		// Habilita o modo de sele��o simples, onde � poss�vel selecionar apenas uma linha de cada vez no JTable.
		tbCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		cp = getContentPane(); // Instancia o container da janela.
		cp.setLayout(null); // Configura o layout do container como nulo.
		cp.setBackground(new Color(180, 205, 205)); // Configura a cor de fundo do container.

		// Posicionamento dos componentes de interface na janela.
		lbTitulo.setBounds(215, 10, 300, 25); // x, y, largura, altura.
		spTabela.setBounds(20, 40, 645, 182);

		// Adi��o dos componentes de interface ao container.
		cp.add(lbTitulo);
		cp.add(spTabela);
	} // Final do construtor.
}