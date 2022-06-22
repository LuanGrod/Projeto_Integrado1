package view.usuario;

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
import controller.UsuarioController;
import model.cargo.Cargo;
import model.usuario.Usuario;


@SuppressWarnings("serial")
public class ConsultaUsuario extends JDialog {
	private JLabel lbTitulo;
	private JTable tbUsuario; // Tabela que exibir� os funcion�rios cadastrados no banco de dados.
	private ModeloTabelaUsuario mtTabela; // Modelo que definir� a estrutura da tabela e permitir� o carregamento dos dados nela.
	private JScrollPane spTabela; // Painel de rolagem ao qual ser� vinculado o JTable.
	private Container cp; // Container para organizar os componentes na janela.

	public ConsultaUsuario() { // Construtor.
		// Instancia��o e configura��o dos componentes de interface.
		setTitle("Consulta de Funcion�rios"); // T�tulo da janela.
		setSize(700, 320); // Tamanho da janela em pixels.
		setLocationRelativeTo(null); // Centraliza a janela na tela.
		setModal(true); // Torna a janela "modal" (janela que n�o permite acesso a outras janelas abertas).
		
		String excecaoUsuarios = null;
		String excecaoCargos = null;
		
		List<Usuario> usuarios = new UsuarioController().selectUsuarios();
		excecaoUsuarios = new UsuarioController().getExcecao();
		
		List<Cargo> cargos = new UsuarioController().selectCargos();
		excecaoCargos = new UsuarioController().getExcecao();
		
		if (excecaoUsuarios != null) { // Caso ocorra qualquer tipo de exce��o.
			JOptionPane.showMessageDialog(null, "N�o foi poss�vel recuperar os dados dos funcion�rios:\n" + excecaoUsuarios, 
					                      "Erro", JOptionPane.ERROR_MESSAGE);
			mtTabela = new ModeloTabelaUsuario(); // Chama o construtor da classe FuncionarioModeloTabela, que apenas define a estrutura do JTable.
		} else if (excecaoCargos != null) { // Caso ocorra qualquer tipo de exce��o.
			JOptionPane.showMessageDialog(null, "N�o foi poss�vel recuperar os dados dos cargos dos funcion�rios:\n" + excecaoCargos, 
						                  "Erro", JOptionPane.ERROR_MESSAGE);
			mtTabela = new ModeloTabelaUsuario(); // Chama o construtor da classe FuncionarioModeloTabela, que apenas define a estrutura do JTable.
		} else		
			// Chama o construtor da classe FuncionarioModeloTabela, que define a estrutura do JTable e carrega os dados nele.
			mtTabela = new ModeloTabelaUsuario(usuarios, cargos);
		
		lbTitulo = new JLabel("Consulta de Funcion�rios");
		lbTitulo.setFont(new Font("Arial", Font.BOLD, 19)); // Ajusta a fonte do JLabel.
		
		// Inclui o modelo da tabela no JTable. Nesse momento, s�o chamados os m�todos da classe FuncionarioModeloTabela.
		tbUsuario = new JTable(mtTabela);
		spTabela = new JScrollPane(tbUsuario); // Vincula o JTable ao painel de rolagem.
		
		tbUsuario.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Desabilita o dimensionamento autom�tico das colunas.
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
		
		// Configura a fonte do cabe�alho do JTable.
		tbUsuario.getTableHeader().setFont(new Font(null, Font.BOLD, 12));
		
		// Centraliza o conte�do da coluna referente ao Id do funcion�rio (�ndice 0).
		DefaultTableCellRenderer dtcrCentro = new DefaultTableCellRenderer();
		dtcrCentro.setHorizontalAlignment(SwingConstants.CENTER);
		tbUsuario.getColumnModel().getColumn(0).setCellRenderer(dtcrCentro);
		
		
		tbUsuario.getTableHeader().setReorderingAllowed(false); // Desabilita a reordena��o das colunas do JTable.
		// Habilita o modo de sele��o simples, onde � poss�vel selecionar apenas uma linha de cada vez no JTable.
		tbUsuario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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