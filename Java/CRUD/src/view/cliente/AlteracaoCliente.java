package view.cliente;

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

import controller.ClienteController;

@SuppressWarnings("serial")
public class AlteracaoCliente extends JDialog {
	private JLabel lbCpf, lbNome, lbTelefone, lbEmail, lbRua, lbBairro, lbCidade, lbCep, lbEstado;
	private JTextField tfCpf, tfNome, tfTelefone, tfEmail, tfRua, tfBairro, tfCidade, tfCep, tfEstado;
	private JButton btSalvar;
	private Container cp; // Container para organizar os componentes na janela.	
	private int idCliente; // Id do funcionário a ser alterado.
	private int linhaSelecionada; // Índice da linha selecionada no JTable.
	private ModeloTabelaCliente mtTabela; // Modelo que define a estrutura da tabela.

	// Construtor.
	public AlteracaoCliente(int idCliente, String nome, String cpf, String telefone, 
							String email, String rua, String bairro, String cidade, String cep, String estado, 
							int linha, ModeloTabelaCliente mtTabela) { 
		// Instanciação e configuração dos componentes de interface.
		setTitle("Alteração de Clientes"); // Título da janela.
		setSize(550, 400); // Tamanho da janela em pixels.
		setLocationRelativeTo(null); // Centraliza a janela na tela.
		setModal(true); // Torna a janela "modal" (janela que não permite acesso a outras janelas abertas).
		
		lbCpf = new JLabel("CPF");
		lbNome = new JLabel("Nome");
		lbTelefone = new JLabel("Tel.");
		lbEmail = new JLabel("Email");
		lbRua = new JLabel("Rua");
		lbBairro = new JLabel("Bairro");
		lbCidade = new JLabel("Cidade");
		lbCep = new JLabel("CEP");
		lbEstado = new JLabel("Estado");
		
		tfCpf = new JTextField();
		tfNome = new JTextField();
		tfTelefone = new JTextField();
		tfEmail = new JTextField();		
		tfRua = new JTextField();
		tfBairro = new JTextField();
		tfCidade= new JTextField();
		tfCep = new JTextField();
		tfEstado = new JTextField();
		
		btSalvar = new JButton("Salvar");
		
		this.idCliente = idCliente; // Recebe o id do funcionário selecionado no JTable.
		
		// Carrega os dados do funcionário selecionado no JTable de funcionários.
		tfCpf.setText(cpf);
		tfNome.setText(nome);
		tfTelefone.setText(telefone);
		tfEmail.setText(email);
		tfRua.setText(rua);
		tfCidade.setText(cidade);
		tfBairro.setText(bairro);
		tfCep.setText(cep);
		tfEstado.setText(estado);
		
		this.linhaSelecionada = linha; // Recebe o índice da linha selecionada no JTable.
		this.mtTabela = mtTabela; // Recebe o modelo que define a estrutura da tabela.

		cp = getContentPane(); // Instancia o container da janela.
		cp.setLayout(null); // Configura o layout do container como nulo.
		cp.setBackground(new Color(180, 205, 205)); // Configura a cor de fundo do container.

		// Posicionamento dos componentes de interface na janela.
		lbCpf.setBounds(26, 78, 46, 14);
		lbNome.setBounds(26, 38, 46, 14);
		lbTelefone.setBounds(276, 75, 46, 14);
		lbEmail.setBounds(276, 38, 46, 14);
		lbRua.setBounds(26, 121, 46, 14);
		lbBairro.setBounds(276, 121, 46, 14);
		lbCidade.setBounds(26, 161, 46, 14);
		lbCep.setBounds(26, 201, 46, 14);
		lbEstado.setBounds(276, 161, 46, 14);

		tfCpf.setBounds(80, 77, 86, 20);
		tfNome.setBounds(80, 37, 180, 20);
		tfTelefone.setBounds(330, 74, 86, 20);
		tfEmail.setBounds(329, 37, 140, 20);
		tfRua.setBounds(80, 120, 180, 20);
		tfBairro.setBounds(329, 120, 140, 20);
		tfCidade.setBounds(80, 160, 180, 20);
		tfCep.setBounds(80, 200, 86, 20);
		tfEstado.setBounds(330, 160, 140, 20);
		btSalvar.setBounds(304, 251, 140, 37);
		
		// Adição dos componentes de interface ao container.
		cp.add(lbCpf);
		cp.add(lbNome);
		cp.add(lbTelefone);
		cp.add(lbEmail);
		cp.add(lbRua);
		cp.add(lbBairro);
		cp.add(lbCidade);
		cp.add(lbCep);
		cp.add(lbEstado);

		cp.add(tfCpf);
		cp.add(tfNome);
		cp.add(tfTelefone);
		cp.add(tfEmail);
		cp.add(tfRua);
		cp.add(tfBairro);
		cp.add(tfCidade);
		cp.add(tfCep);
		cp.add(tfEstado);

	
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
		erros = new ClienteController().alteraCliente(this.idCliente,
															  tfNome.getText(),
															  tfCpf.getText(),
															  tfTelefone.getText(),
															  tfEmail.getText(),
															  tfRua.getText(),
															  tfBairro.getText(),
															  tfCidade.getText(),
															  tfCep.getText(),
															  tfEstado.getText()
															  );	
		
		if (erros.get(0) == null) { // Se o primeiro elemento do ArrayList for null.
			JOptionPane.showMessageDialog(this, "Cliente alterado com sucesso.", 
                    					  "Informação", JOptionPane.INFORMATION_MESSAGE);
			// Atualiza os dados do JTable após a alteração do funcionário.
			// O método setValueAt recebe o valor (Object), a linha (int) e a coluna (int) 
			// para atualizar com este valor cada coluna da linha selecionada.
			this.mtTabela.setValueAt(tfNome.getText(), this.linhaSelecionada, 1);
			this.mtTabela.setValueAt(tfCpf.getText(), this.linhaSelecionada, 2);
			this.mtTabela.setValueAt(tfTelefone.getText(), this.linhaSelecionada, 3);
			this.mtTabela.setValueAt(tfEmail.getText(), this.linhaSelecionada, 4);
			this.mtTabela.setValueAt(tfRua.getText(), this.linhaSelecionada, 5);
			this.mtTabela.setValueAt(tfBairro.getText(), this.linhaSelecionada, 6);
			this.mtTabela.setValueAt(tfCidade.getText(), this.linhaSelecionada, 7);
			this.mtTabela.setValueAt(tfCep.getText(), this.linhaSelecionada, 8);
			this.mtTabela.setValueAt(tfEstado.getText(), this.linhaSelecionada, 9);
			this.setVisible(false); // Fecha a janela.
		} else { // Se o primeiro elemento do ArrayList não for null.
			String mensagem = "Não foi possível alterar o cliente:\n";
			for (String e : erros) // Cria uma mensagem contendo todos os erros armazenados no ArrayList.
				mensagem = mensagem + e + "\n";
			JOptionPane.showMessageDialog(this, mensagem, "Erros", JOptionPane.ERROR_MESSAGE);
		}
	}
}