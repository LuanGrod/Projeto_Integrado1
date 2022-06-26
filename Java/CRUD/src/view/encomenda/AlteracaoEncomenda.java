package view.encomenda;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.EncomendaController;
import model.fornecedor.Fornecedor;
import model.produto.Produto;

@SuppressWarnings("serial")
public class AlteracaoEncomenda extends JDialog {

	private JLabel lbProduto, lbFornecedor, lbQuantidade;
	private JTextField tfQuantidade;
	private JComboBox<Produto> cbProduto;
	private JComboBox<Fornecedor> cbFornecedor;
	private JButton btSalvar;
	private Container cp; // Container para organizar os componentes na janela.	
	private int idEncomenda; // Id do funcion�rio a ser alterado.
	private int linhaSelecionada; // �ndice da linha selecionada no JTable.
	private ModeloTabelaEncomenda mtTabela; // Modelo que define a estrutura da tabela.


	// Construtor.
	public AlteracaoEncomenda(int idEncomenda, String produto, String fornecedor, String quantidade,
							int linha, ModeloTabelaEncomenda mtTabela) { 
		// Instancia��o e configura��o dos componentes de interface.
		setTitle("Altera��o de Encomendas"); // T�tulo da janela.
		setSize(550, 400); // Tamanho da janela em pixels.
		setLocationRelativeTo(null); // Centraliza a janela na tela.
		setModal(true); // Torna a janela "modal" (janela que n�o permite acesso a outras janelas abertas).
		
		lbProduto = new JLabel("Produto");
		lbFornecedor = new JLabel("Fornecedor");
		lbQuantidade = new JLabel("Quantidade");
		
		tfQuantidade = new JTextField();
		
		cbProduto = new JComboBox<>();
		cbFornecedor = new JComboBox<>();	
		
		List<Produto> produtos = new ArrayList<Produto>();
		List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
		
		// Retorna um ArrayList de objetos Cargo, contendo o Id e a Descri��o dos cargos cadastrados.
		produtos = new EncomendaController().consultaProdutos();
		if (produtos != null) // Se existir pelo menos um cargo cadastrado.
			for (Produto p : produtos)
				// O m�todo addItem adiciona o objeto Cargo (contendo os atributos Id e Descri��o) ao JComboBox. Ao ser carregado,
				// o JComboBox chama automaticamente o m�todo toString dos objetos Cargo para convert�-los para String, pois o
				// dado a ser exibido no JComboBox deve ser deste tipo. Como o m�todo toString foi sobrescrito na classe Cargo, 
				// de modo a retornar a descri��o do cargo, � esta a informa��o que ser� exibida no JComboBox.
				cbProduto.addItem(p);
		
		String erro = new EncomendaController().getExcecao();
		
		if (erro != null) // Caso ocorra qualquer tipo de exce��o.
			JOptionPane.showMessageDialog(null, "N�o foi poss�vel recuperar os dados dos produtos:\n" + erro, 
					                      "Erro", JOptionPane.ERROR_MESSAGE);
		
		
		fornecedores = new EncomendaController().consultaFornecedores();
		if (fornecedores != null) // Se existir pelo menos um cargo cadastrado.
			for (Fornecedor f : fornecedores)
				// O m�todo addItem adiciona o objeto Cargo (contendo os atributos Id e Descri��o) ao JComboBox. Ao ser carregado,
				// o JComboBox chama automaticamente o m�todo toString dos objetos Cargo para convert�-los para String, pois o
				// dado a ser exibido no JComboBox deve ser deste tipo. Como o m�todo toString foi sobrescrito na classe Cargo, 
				// de modo a retornar a descri��o do cargo, � esta a informa��o que ser� exibida no JComboBox.
				cbFornecedor.addItem(f);
		
		erro = new EncomendaController().getExcecao();
		
		if (erro != null) // Caso ocorra qualquer tipo de exce��o.
			JOptionPane.showMessageDialog(null, "N�o foi poss�vel recuperar os dados dos fornecedores:\n" + erro, 
					                      "Erro", JOptionPane.ERROR_MESSAGE);
		
		
		btSalvar = new JButton("Salvar");
		
		this.idEncomenda = idEncomenda; // Recebe o id do funcion�rio selecionado no JTable.
		
		// Carrega os dados do funcion�rio selecionado no JTable de funcion�rios.
		tfQuantidade.setText(quantidade);
		
		for (int i = 0; i < cbProduto.getItemCount(); i++)
			if (cbProduto.getItemAt(i).getNome().equals(produto))
				cbProduto.setSelectedItem(cbProduto.getItemAt(i));
		
		for (int i = 0; i < cbFornecedor.getItemCount(); i++)
			if (cbFornecedor.getItemAt(i).getNome().equals(fornecedor))
				cbFornecedor.setSelectedItem(cbFornecedor.getItemAt(i));
		
		this.linhaSelecionada = linha; // Recebe o �ndice da linha selecionada no JTable.
		this.mtTabela = mtTabela; // Recebe o modelo que define a estrutura da tabela.

		cp = getContentPane(); // Instancia o container da janela.
		cp.setLayout(null); // Configura o layout do container como nulo.
		cp.setBackground(new Color(180, 205, 205)); // Configura a cor de fundo do container.

		// Posicionamento dos componentes de interface na janela.
		lbProduto.setBounds(30, 40, 46, 14); // x, y, largura, altura.
		lbFornecedor.setBounds(30, 80, 68, 14);
		lbQuantidade.setBounds(30, 120, 68, 14);
		
		tfQuantidade.setBounds(95, 117, 86, 20); // x, y, largura, altura.

		cbProduto.setBounds(86, 36, 132, 22);
		cbFornecedor.setBounds(96, 76, 146, 22);
		
		btSalvar.setBounds(186, 316, 102, 33);
		
		// Adi��o dos componentes de interface ao container.
		cp.add(lbProduto);
		cp.add(lbFornecedor);
		cp.add(lbQuantidade);

		cp.add(tfQuantidade);
		
		cp.add(cbFornecedor);
		cp.add(cbFornecedor);
		
		cp.add(btSalvar);

		// Declara��o do processador de evento referente ao clique no bot�o Salvar.
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btSalvarAction();
			}
		});

	} // Final do construtor.

	private void btSalvarAction() { // M�todo acionado pelo clique no bot�o Salvar.
		List<String> erros = new ArrayList<String>();
		
		// Envia os dados do funcion�rio (informados no formul�rio) ao controller. 
		// O controller retorna ent�o um ArrayList contendo os erros encontrados.
		erros = new EncomendaController().alteraEncomenda(this.idEncomenda,
											                  (Produto) cbProduto.getSelectedItem(),
											                  (Fornecedor) cbFornecedor.getSelectedItem(),
											                  Integer.parseInt(tfQuantidade.getText()));	
		
		if (erros.get(0) == null) { // Se o primeiro elemento do ArrayList for null.
			JOptionPane.showMessageDialog(this, "Encomenda alterada com sucesso.", 
                    					  "Informa��o", JOptionPane.INFORMATION_MESSAGE);
			// Atualiza os dados do JTable ap�s a altera��o do funcion�rio.
			// O m�todo setValueAt recebe o valor (Object), a linha (int) e a coluna (int) 
			// para atualizar com este valor cada coluna da linha selecionada.
			this.mtTabela.setValueAt(cbProduto.getSelectedItem(), this.linhaSelecionada, 1);
			this.mtTabela.setValueAt(cbFornecedor.getSelectedItem(), this.linhaSelecionada, 2);
			this.mtTabela.setValueAt(tfQuantidade.getText(), this.linhaSelecionada, 3);
			this.setVisible(false); // Fecha a janela.
		} else { // Se o primeiro elemento do ArrayList n�o for null.
			String mensagem = "N�o foi poss�vel alterar a encomenda:\n";
			for (String e : erros) // Cria uma mensagem contendo todos os erros armazenados no ArrayList.
				mensagem = mensagem + e + "\n";
			JOptionPane.showMessageDialog(this, mensagem, "Erros", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}