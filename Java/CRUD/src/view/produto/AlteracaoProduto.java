package view.produto;

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
import javax.swing.SwingUtilities;

import controller.ProdutoController;
import model.categoria.Categoria;


@SuppressWarnings("serial")
public class AlteracaoProduto extends JDialog {
	private JLabel lbNome, lbPrecoCusto, lbPrecoVenda, lbQtdEstoque, lbCategoria;
	private JTextField tfNome, tfPrecoCusto, tfPrecoVenda, tfQtdEstoque;
	private JComboBox<Categoria> cbCategoria;
	private JButton btSalvar ,btCriaCategoria;
	private Container cp; // Container para organizar os componentes na janela.	
	private int idProduto; // Id do funcion�rio a ser alterado.
	private int linhaSelecionada; // �ndice da linha selecionada no JTable.
	private ModeloTabelaProduto mtTabela; // Modelo que define a estrutura da tabela.


	// Construtor.
	public AlteracaoProduto(int idProduto, String nome, String precoCusto, 
							String precoVenda, String QtdEstoque, String categoria, 
							int linha, ModeloTabelaProduto mtTabela) { 
		// Instancia��o e configura��o dos componentes de interface.
		setTitle("Altera��o de Produtos"); // T�tulo da janela.
		setSize(550, 400); // Tamanho da janela em pixels.
		setLocationRelativeTo(null); // Centraliza a janela na tela.
		setModal(true); // Torna a janela "modal" (janela que n�o permite acesso a outras janelas abertas).
		
		lbNome = new JLabel("Nome");
		lbPrecoCusto = new JLabel("Pre�o Custo");
		lbPrecoVenda = new JLabel("Pre�o Venda");
		lbQtdEstoque = new JLabel("Quantidade em Estoque");
		lbCategoria = new JLabel("Categoria");
		
		tfNome = new JTextField();
		tfPrecoCusto = new JTextField("0.00");
		tfPrecoVenda = new JTextField("0.00");		
		tfQtdEstoque = new JTextField();
	
		cbCategoria = new JComboBox<>();
		List<Categoria> categorias = new ArrayList<Categoria>();
		
		// Retorna um ArrayList de objetos Cargo, contendo o Id e a Descri��o dos cargos cadastrados.
		categorias = new ProdutoController().recuperaCategorias();
		if (categorias != null) // Se existir pelo menos um cargo cadastrado.
			for (Categoria c : categorias)
				// O m�todo addItem adiciona o objeto Cargo (contendo os atributos Id e Descri��o) ao JComboBox. Ao ser carregado,
				// o JComboBox chama automaticamente o m�todo toString dos objetos Cargo para convert�-los para String, pois o
				// dado a ser exibido no JComboBox deve ser deste tipo. Como o m�todo toString foi sobrescrito na classe Cargo, 
				// de modo a retornar a descri��o do cargo, � esta a informa��o que ser� exibida no JComboBox.
				cbCategoria.addItem(c);
		
		String erro = new ProdutoController().getExcecao();
		
		if (erro != null) // Caso ocorra qualquer tipo de exce��o.
			JOptionPane.showMessageDialog(null, "N�o foi poss�vel recuperar os dados das categorias:\n" + erro, 
					                      "Erro", JOptionPane.ERROR_MESSAGE);
		
		
		btSalvar = new JButton("Salvar");
		btCriaCategoria = new JButton("Criar nova categoria");
		
		this.idProduto = idProduto; // Recebe o id do funcion�rio selecionado no JTable.
		
		// Carrega os dados do funcion�rio selecionado no JTable de funcion�rios.
		tfNome.setText(nome);
		tfPrecoCusto.setText(precoCusto);
		tfPrecoVenda.setText(precoVenda);
		tfQtdEstoque.setText(QtdEstoque);
		
		for (int i = 0; i < cbCategoria.getItemCount(); i++)
			if (cbCategoria.getItemAt(i).getDescricao().equals(categoria))
				cbCategoria.setSelectedItem(cbCategoria.getItemAt(i));
		
		this.linhaSelecionada = linha; // Recebe o �ndice da linha selecionada no JTable.
		this.mtTabela = mtTabela; // Recebe o modelo que define a estrutura da tabela.

		cp = getContentPane(); // Instancia o container da janela.
		cp.setLayout(null); // Configura o layout do container como nulo.
		cp.setBackground(new Color(180, 205, 205)); // Configura a cor de fundo do container.

		// Posicionamento dos componentes de interface na janela.
		lbNome.setBounds(30, 30, 46, 14);
		lbPrecoCusto.setBounds(30, 153, 78, 14);
		lbPrecoVenda.setBounds(30, 192, 78, 14);
		lbQtdEstoque.setBounds(30, 110, 148, 14);
		lbCategoria.setBounds(30, 71, 55, 14);
		
		tfNome.setBounds(74, 27, 148, 20);
		tfPrecoCusto.setBounds(112, 149, 78, 20);
		tfPrecoVenda.setBounds(112, 188, 78, 20);
		tfQtdEstoque.setBounds(170, 107, 51, 20);
		cbCategoria.setBounds(95, 67, 114, 22);
		
		btSalvar.setBounds(270, 211, 148, 28);
		btCriaCategoria.setBounds(242, 67, 136, 23);

		// Adi��o dos componentes de interface ao container.
		cp.add(lbNome);
		cp.add(lbPrecoCusto);
		cp.add(lbPrecoVenda);
		cp.add(lbQtdEstoque);
		cp.add(lbCategoria);

		cp.add(tfNome);
		cp.add(tfPrecoCusto);
		cp.add(tfPrecoVenda);
		cp.add(tfQtdEstoque);
		cp.add(cbCategoria);
		
		cp.add(btSalvar);
		cp.add(btCriaCategoria);

		// Declara��o do processador de evento referente ao clique no bot�o Salvar.
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btSalvarAction();
			}
		});

		//criar nova categoria - falta implementar
		btCriaCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCriaCategoriaAction();
			}
		});
	} // Final do construtor.

	private void btSalvarAction() { // M�todo acionado pelo clique no bot�o Salvar.
		Double precoCusto = null;
		Double precoVenda = null;
		Integer qtdEstoque = null;
		
		if (!tfPrecoCusto.getText().equals(""))
			precoCusto = new Double(tfPrecoCusto.getText().replace(".", "").replace(",", "."));
		
		if (!tfPrecoVenda.getText().equals(""))
			precoVenda = new Double(tfPrecoVenda.getText().replace(".", "").replace(",", "."));
		
		if (!tfQtdEstoque.getText().equals(""))
			qtdEstoque = new Integer(tfQtdEstoque.getText());
		
		List<String> erros = new ArrayList<String>();
		
		// Envia os dados do funcion�rio (informados no formul�rio) ao controller. 
		// O controller retorna ent�o um ArrayList contendo os erros encontrados.
		erros = new ProdutoController().alteraProduto(this.idProduto,
															  tfNome.getText(),
															  precoCusto,
															  precoVenda,
															  qtdEstoque,
											                  (Categoria) cbCategoria.getSelectedItem());	
		
		if (erros.get(0) == null) { // Se o primeiro elemento do ArrayList for null.
			JOptionPane.showMessageDialog(this, "Produto alterado com sucesso.", 
                    					  "Informa��o", JOptionPane.INFORMATION_MESSAGE);
			// Atualiza os dados do JTable ap�s a altera��o do funcion�rio.
			// O m�todo setValueAt recebe o valor (Object), a linha (int) e a coluna (int) 
			// para atualizar com este valor cada coluna da linha selecionada.
			this.mtTabela.setValueAt(tfNome.getText(), this.linhaSelecionada, 1);
			this.mtTabela.setValueAt(precoCusto, this.linhaSelecionada, 2);
			this.mtTabela.setValueAt(precoVenda, this.linhaSelecionada, 3);
			this.mtTabela.setValueAt(qtdEstoque, this.linhaSelecionada, 4);
			this.mtTabela.setValueAt(cbCategoria.getSelectedItem(), this.linhaSelecionada, 5);
			this.setVisible(false); // Fecha a janela.
		} else { // Se o primeiro elemento do ArrayList n�o for null.
			String mensagem = "N�o foi poss�vel alterar o funcion�rio:\n";
			for (String e : erros) // Cria uma mensagem contendo todos os erros armazenados no ArrayList.
				mensagem = mensagem + e + "\n";
			JOptionPane.showMessageDialog(this, mensagem, "Erros", JOptionPane.ERROR_MESSAGE);
		}
	}

	
	private void btnCriaCategoriaAction() { 
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){ new CadastraCategoria().setVisible(true); }});
	}
}