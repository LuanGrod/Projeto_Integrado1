package view.fornecedor;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.FornecedorController;

@SuppressWarnings("serial")
public class AlteracaoFornecedor extends JDialog {
	private JLabel lbCnpj, lbTelefone, lbEmail, lbNome;
	private JTextField tfCnpj, tfNome, tfTelefone, tfEmail;
	private JButton btSalvar;
	private Container cp; // Container para organizar os componentes na janela.	
	private int idFornecedor; // Id do funcionário a ser alterado.
	private int linhaSelecionada; // Índice da linha selecionada no JTable.
	private ModeloTabelaFornecedor mtTabela; // Modelo que define a estrutura da tabela.

	// Construtor.
	public AlteracaoFornecedor(int idFornecedor, String cnpj, String telefone, 
							String email, String nome, 
							int linha, ModeloTabelaFornecedor mtTabela) { 
		// Instanciação e configuração dos componentes de interface.
		setTitle("Alteração de Fornecedores"); // Título da janela.
		setSize(550, 400); // Tamanho da janela em pixels.
		setLocationRelativeTo(null); // Centraliza a janela na tela.
		setModal(true); // Torna a janela "modal" (janela que não permite acesso a outras janelas abertas).
		
		lbCnpj= new JLabel("CNPJ");
		lbNome = new JLabel("Nome");
		lbTelefone = new JLabel("Tel.");
		lbEmail = new JLabel("Email");
		
		tfCnpj = new JTextField();
		tfNome = new JTextField();
		tfTelefone = new JTextField();
		tfEmail = new JTextField();		
		
		btSalvar = new JButton("Salvar");
		
		this.idFornecedor = idFornecedor; // Recebe o id do funcionário selecionado no JTable.
		
		// Carrega os dados do funcionário selecionado no JTable de funcionários.
		tfCnpj.setText(cnpj);
		tfNome.setText(nome);
		tfTelefone.setText(telefone);
		tfEmail.setText(email);
		tfNome.setText(nome);
		
		this.linhaSelecionada = linha; // Recebe o índice da linha selecionada no JTable.
		this.mtTabela = mtTabela; // Recebe o modelo que define a estrutura da tabela.

		cp = getContentPane(); // Instancia o container da janela.
		cp.setLayout(null); // Configura o layout do container como nulo.
		cp.setBackground(new Color(180, 205, 205)); // Configura a cor de fundo do container.

		// Posicionamento dos componentes de interface na janela.
		lbCnpj.setBounds(35, 40, 46, 14);
		lbNome.setBounds(35, 90, 46, 14);
		lbTelefone.setBounds(34, 143, 46, 14);
		lbEmail.setBounds(34, 193, 46, 14);

		tfCnpj.setBounds(70, 37, 131, 20);
		tfNome.setBounds(70, 87, 217, 20);
		tfTelefone.setBounds(69, 140, 131, 20);
		tfEmail.setBounds(69, 190, 218, 20);
		btSalvar.setBounds(201, 231, 161, 32);
		
		// Adição dos componentes de interface ao container.
		cp.add(lbCnpj);
		cp.add(lbNome);
		cp.add(lbTelefone);
		cp.add(lbEmail);

		cp.add(tfCnpj);
		cp.add(tfNome);
		cp.add(tfTelefone);
		cp.add(tfEmail);
	
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
		erros = new FornecedorController().alteraFornecedor(this.idFornecedor,
															  tfCnpj.getText(),
															  tfTelefone.getText(),
															  tfEmail.getText(),
															  tfNome.getText()
															  );	
		
		if (erros.get(0) == null) { // Se o primeiro elemento do ArrayList for null.
			JOptionPane.showMessageDialog(this, "Fornecedor alterado com sucesso.", 
                    					  "Informação", JOptionPane.INFORMATION_MESSAGE);
			// Atualiza os dados do JTable após a alteração do funcionário.
			// O método setValueAt recebe o valor (Object), a linha (int) e a coluna (int) 
			// para atualizar com este valor cada coluna da linha selecionada.
			this.mtTabela.setValueAt(tfCnpj.getText(), this.linhaSelecionada, 1);
			this.mtTabela.setValueAt(tfTelefone.getText(), this.linhaSelecionada, 2);
			this.mtTabela.setValueAt(tfEmail.getText(), this.linhaSelecionada, 3);
			this.mtTabela.setValueAt(tfNome.getText(), this.linhaSelecionada, 4);
			this.setVisible(false); // Fecha a janela.
		} else { // Se o primeiro elemento do ArrayList não for null.
			String mensagem = "Não foi possível alterar o fornecedor:\n";
			for (String e : erros) // Cria uma mensagem contendo todos os erros armazenados no ArrayList.
				mensagem = mensagem + e + "\n";
			JOptionPane.showMessageDialog(this, mensagem, "Erros", JOptionPane.ERROR_MESSAGE);
		}
	}
}