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
	private int idEncomenda; // Id do funcionário a ser alterado.
	private int linhaSelecionada; // Índice da linha selecionada no JTable.
	private ModeloTabelaEncomenda mtTabela; // Modelo que define a estrutura da tabela.


	// Construtor.
	public AlteracaoEncomenda(int idEncomenda, String produto, String fornecedor, String quantidade,
							int linha, ModeloTabelaEncomenda mtTabela) { 
		// Instanciação e configuração dos componentes de interface.
		setTitle("Alteração de Encomendas"); // Título da janela.
		setSize(550, 400); // Tamanho da janela em pixels.
		setLocationRelativeTo(null); // Centraliza a janela na tela.
		setModal(true); // Torna a janela "modal" (janela que não permite acesso a outras janelas abertas).
		
		lbProduto = new JLabel("Produto");
		lbFornecedor = new JLabel("Fornecedor");
		lbQuantidade = new JLabel("Quantidade");
		
		tfQuantidade = new JTextField();
		
		cbProduto = new JComboBox<>();
		cbFornecedor = new JComboBox<>();	
		
		List<Produto> produtos = new ArrayList<Produto>();
		List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
		
		// Retorna um ArrayList de objetos Cargo, contendo o Id e a Descrição dos cargos cadastrados.
		produtos = new EncomendaController().consultaProdutos();
		if (produtos != null) // Se existir pelo menos um cargo cadastrado.
			for (Produto p : produtos)
				// O método addItem adiciona o objeto Cargo (contendo os atributos Id e Descrição) ao JComboBox. Ao ser carregado,
				// o JComboBox chama automaticamente o método toString dos objetos Cargo para convertê-los para String, pois o
				// dado a ser exibido no JComboBox deve ser deste tipo. Como o método toString foi sobrescrito na classe Cargo, 
				// de modo a retornar a descrição do cargo, é esta a informação que será exibida no JComboBox.
				cbProduto.addItem(p);
		
		String erro = new EncomendaController().getExcecao();
		
		if (erro != null) // Caso ocorra qualquer tipo de exceção.
			JOptionPane.showMessageDialog(null, "Não foi possível recuperar os dados dos produtos:\n" + erro, 
					                      "Erro", JOptionPane.ERROR_MESSAGE);
		
		
		fornecedores = new EncomendaController().consultaFornecedores();
		if (fornecedores != null) // Se existir pelo menos um cargo cadastrado.
			for (Fornecedor f : fornecedores)
				// O método addItem adiciona o objeto Cargo (contendo os atributos Id e Descrição) ao JComboBox. Ao ser carregado,
				// o JComboBox chama automaticamente o método toString dos objetos Cargo para convertê-los para String, pois o
				// dado a ser exibido no JComboBox deve ser deste tipo. Como o método toString foi sobrescrito na classe Cargo, 
				// de modo a retornar a descrição do cargo, é esta a informação que será exibida no JComboBox.
				cbFornecedor.addItem(f);
		
		erro = new EncomendaController().getExcecao();
		
		if (erro != null) // Caso ocorra qualquer tipo de exceção.
			JOptionPane.showMessageDialog(null, "Não foi possível recuperar os dados dos fornecedores:\n" + erro, 
					                      "Erro", JOptionPane.ERROR_MESSAGE);
		
		
		btSalvar = new JButton("Salvar");
		
		this.idEncomenda = idEncomenda; // Recebe o id do funcionário selecionado no JTable.
		
		// Carrega os dados do funcionário selecionado no JTable de funcionários.
		tfQuantidade.setText(quantidade);
		
		for (int i = 0; i < cbProduto.getItemCount(); i++)
			if (cbProduto.getItemAt(i).getNome().equals(produto))
				cbProduto.setSelectedItem(cbProduto.getItemAt(i));
		
		for (int i = 0; i < cbFornecedor.getItemCount(); i++)
			if (cbFornecedor.getItemAt(i).getNome().equals(fornecedor))
				cbFornecedor.setSelectedItem(cbFornecedor.getItemAt(i));
		
		this.linhaSelecionada = linha; // Recebe o índice da linha selecionada no JTable.
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
		
		// Adição dos componentes de interface ao container.
		cp.add(lbProduto);
		cp.add(lbFornecedor);
		cp.add(lbQuantidade);

		cp.add(tfQuantidade);
		
		cp.add(cbFornecedor);
		cp.add(cbFornecedor);
		
		cp.add(btSalvar);

		// Declaração do processador de evento referente ao clique no botão Salvar.
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btSalvarAction();
			}
		});

	} // Final do construtor.

	private void btSalvarAction() { // Método acionado pelo clique no botão Salvar.
		List<String> erros = new ArrayList<String>();
		
		// Envia os dados do funcionário (informados no formulário) ao controller. 
		// O controller retorna então um ArrayList contendo os erros encontrados.
		erros = new EncomendaController().alteraEncomenda(this.idEncomenda,
											                  (Produto) cbProduto.getSelectedItem(),
											                  (Fornecedor) cbFornecedor.getSelectedItem(),
											                  Integer.parseInt(tfQuantidade.getText()));	
		
		if (erros.get(0) == null) { // Se o primeiro elemento do ArrayList for null.
			JOptionPane.showMessageDialog(this, "Encomenda alterada com sucesso.", 
                    					  "Informação", JOptionPane.INFORMATION_MESSAGE);
			// Atualiza os dados do JTable após a alteração do funcionário.
			// O método setValueAt recebe o valor (Object), a linha (int) e a coluna (int) 
			// para atualizar com este valor cada coluna da linha selecionada.
			this.mtTabela.setValueAt(cbProduto.getSelectedItem(), this.linhaSelecionada, 1);
			this.mtTabela.setValueAt(cbFornecedor.getSelectedItem(), this.linhaSelecionada, 2);
			this.mtTabela.setValueAt(tfQuantidade.getText(), this.linhaSelecionada, 3);
			this.setVisible(false); // Fecha a janela.
		} else { // Se o primeiro elemento do ArrayList não for null.
			String mensagem = "Não foi possível alterar a encomenda:\n";
			for (String e : erros) // Cria uma mensagem contendo todos os erros armazenados no ArrayList.
				mensagem = mensagem + e + "\n";
			JOptionPane.showMessageDialog(this, mensagem, "Erros", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}