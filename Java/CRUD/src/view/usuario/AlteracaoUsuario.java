package view.usuario;

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

import controller.UsuarioController;
import model.cargo.Cargo;

@SuppressWarnings("serial")
public class AlteracaoUsuario extends JDialog {

	private JLabel lbLogin, lbSenha, lbCpf, lbNome, lbTelefone, lbEmail, lbRua, lbBairro, lbCidade, lbCep, lbEstado, lbCargo;
	private JTextField tfLogin, tfSenha, tfCpf, tfNome, tfTelefone, tfEmail, tfRua, tfBairro, tfCidade, tfCep, tfEstado;
	private JComboBox<Cargo> cbCargo;
	private JButton btSalvar;
	private Container cp; // Container para organizar os componentes na janela.	
	private int idUsuario; // Id do funcionário a ser alterado.
	private int linhaSelecionada; // Índice da linha selecionada no JTable.
	private ModeloTabelaUsuario mtTabela; // Modelo que define a estrutura da tabela.


	// Construtor.
	public AlteracaoUsuario(int idUsuario, String login, String senha, String cpf, String nome, String telefone, 
							String email, String rua, String bairro, String cidade, String cep, String estado, 
							String cargo, int linha, ModeloTabelaUsuario mtTabela) { 
		// Instanciação e configuração dos componentes de interface.
		setTitle("Alteração de Funcionários"); // Título da janela.
		setSize(550, 400); // Tamanho da janela em pixels.
		setLocationRelativeTo(null); // Centraliza a janela na tela.
		setModal(true); // Torna a janela "modal" (janela que não permite acesso a outras janelas abertas).
		
		lbLogin = new JLabel("Login");
		lbSenha = new JLabel("Senha");
		lbCpf = new JLabel("CPF");
		lbNome = new JLabel("Nome");
		lbTelefone = new JLabel("Tel.");
		lbEmail = new JLabel("Email");
		lbRua = new JLabel("Rua");
		lbBairro = new JLabel("Bairro");
		lbCidade = new JLabel("Cidade");
		lbCep = new JLabel("CEP");
		lbEstado = new JLabel("Estado");
		lbCargo = new JLabel("Cargo");
		
		tfLogin = new JTextField();
		tfSenha = new JTextField();
		tfCpf = new JTextField();
		tfNome = new JTextField();
		tfTelefone = new JTextField();
		tfEmail = new JTextField();		
		tfRua = new JTextField();
		tfBairro = new JTextField();
		tfCidade= new JTextField();
		tfCep = new JTextField();
		tfEstado = new JTextField();
		
		cbCargo = new JComboBox<>();		
		
		List<Cargo> cargos = new ArrayList<Cargo>();
		
		// Retorna um ArrayList de objetos Cargo, contendo o Id e a Descrição dos cargos cadastrados.
		cargos = new UsuarioController().recuperaCargos();
		if (cargos != null) // Se existir pelo menos um cargo cadastrado.
			for (Cargo c : cargos)
				// O método addItem adiciona o objeto Cargo (contendo os atributos Id e Descrição) ao JComboBox. Ao ser carregado,
				// o JComboBox chama automaticamente o método toString dos objetos Cargo para convertê-los para String, pois o
				// dado a ser exibido no JComboBox deve ser deste tipo. Como o método toString foi sobrescrito na classe Cargo, 
				// de modo a retornar a descrição do cargo, é esta a informação que será exibida no JComboBox.
				cbCargo.addItem(c);
		
		String erro = new UsuarioController().getExcecao();
		
		if (erro != null) // Caso ocorra qualquer tipo de exceção.
			JOptionPane.showMessageDialog(null, "Não foi possível recuperar os dados dos cargos:\n" + erro, 
					                      "Erro", JOptionPane.ERROR_MESSAGE);
		
		
		btSalvar = new JButton("Salvar");
		
		this.idUsuario = idUsuario; // Recebe o id do funcionário selecionado no JTable.
		
		// Carrega os dados do funcionário selecionado no JTable de funcionários.
		tfLogin.setText(login);
		tfSenha.setText(senha);
		tfCpf.setText(cpf);
		tfNome.setText(nome);
		tfTelefone.setText(telefone);
		tfEmail.setText(email);
		tfRua.setText(rua);
		tfCidade.setText(cidade);
		tfBairro.setText(bairro);
		tfCep.setText(cep);
		tfEstado.setText(estado);
		
		for (int i = 0; i < cbCargo.getItemCount(); i++)
			if (cbCargo.getItemAt(i).getDescricao().equals(cargo))
				cbCargo.setSelectedItem(cbCargo.getItemAt(i));
		
		this.linhaSelecionada = linha; // Recebe o índice da linha selecionada no JTable.
		this.mtTabela = mtTabela; // Recebe o modelo que define a estrutura da tabela.

		cp = getContentPane(); // Instancia o container da janela.
		cp.setLayout(null); // Configura o layout do container como nulo.
		cp.setBackground(new Color(180, 205, 205)); // Configura a cor de fundo do container.

		// Posicionamento dos componentes de interface na janela.
		lbLogin.setBounds(251, 151, 46, 14); // x, y, largura, altura.
		lbSenha.setBounds(251, 191, 46, 14);
		lbCpf.setBounds(25, 31, 46, 14);
		lbNome.setBounds(25, 71, 46, 14);
		lbTelefone.setBounds(25, 111, 46, 14);
		lbEmail.setBounds(25, 151, 46, 14);
		lbRua.setBounds(25, 231, 46, 14);
		lbBairro.setBounds(251, 231, 46, 14);
		lbCidade.setBounds(25, 273, 46, 14);
		lbCep.setBounds(25, 192, 46, 14);
		lbEstado.setBounds(251, 273, 46, 14);
		lbCargo.setBounds(251, 111, 46, 14);
		
		tfLogin.setBounds(295, 149, 151, 20); // x, y, largura, altura.
		tfSenha.setBounds(295, 188, 125, 20);
		tfCpf.setBounds(62, 28, 120, 20);
		tfNome.setBounds(62, 68, 239, 20);
		tfTelefone.setBounds(62, 108, 120, 20);
		tfEmail.setBounds(62, 148, 151, 20);
		tfRua.setBounds(62, 229, 151, 20);
		tfBairro.setBounds(295, 229, 86, 20);
		tfCidade.setBounds(62, 270, 151, 20);
		tfCep.setBounds(62, 189, 96, 20);
		tfEstado.setBounds(295, 270, 86, 20);
		cbCargo.setBounds(295, 107, 113, 22);
		btSalvar.setBounds(186, 316, 102, 33);

		// Adição dos componentes de interface ao container.
		cp.add(lbLogin);
		cp.add(lbSenha);
		cp.add(lbCpf);
		cp.add(lbNome);
		cp.add(lbTelefone);
		cp.add(lbEmail);
		cp.add(lbRua);
		cp.add(lbBairro);
		cp.add(lbCidade);
		cp.add(lbCep);
		cp.add(lbEstado);
		cp.add(lbCargo);

		cp.add(tfLogin);
		cp.add(tfSenha);
		cp.add(tfCpf);
		cp.add(tfNome);
		cp.add(tfTelefone);
		cp.add(tfEmail);
		cp.add(tfRua);
		cp.add(tfBairro);
		cp.add(tfCidade);
		cp.add(tfCep);
		cp.add(tfEstado);
		
		cp.add(cbCargo);
		
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
		erros = new UsuarioController().alteraUsuario(this.idUsuario,
															  tfLogin.getText(),
															  tfSenha.getText(),
															  tfCpf.getText(),
															  tfNome.getText(),
															  tfTelefone.getText(),
															  tfEmail.getText(),
															  tfRua.getText(),
															  tfBairro.getText(),
															  tfCidade.getText(),
															  tfCep.getText(),
															  tfEstado.getText(),
											                  (Cargo) cbCargo.getSelectedItem());	
		
		if (erros.get(0) == null) { // Se o primeiro elemento do ArrayList for null.
			JOptionPane.showMessageDialog(this, "Funcionário alterado com sucesso.", 
                    					  "Informação", JOptionPane.INFORMATION_MESSAGE);
			// Atualiza os dados do JTable após a alteração do funcionário.
			// O método setValueAt recebe o valor (Object), a linha (int) e a coluna (int) 
			// para atualizar com este valor cada coluna da linha selecionada.
			this.mtTabela.setValueAt(tfLogin.getText(), this.linhaSelecionada, 1);
			this.mtTabela.setValueAt(tfSenha.getText(), this.linhaSelecionada, 2);
			this.mtTabela.setValueAt(tfCpf.getText(), this.linhaSelecionada, 3);
			this.mtTabela.setValueAt(tfNome.getText(), this.linhaSelecionada, 4);
			this.mtTabela.setValueAt(tfTelefone.getText(), this.linhaSelecionada, 5);
			this.mtTabela.setValueAt(tfEmail.getText(), this.linhaSelecionada, 6);
			this.mtTabela.setValueAt(tfRua.getText(), this.linhaSelecionada, 7);
			this.mtTabela.setValueAt(tfBairro.getText(), this.linhaSelecionada, 8);
			this.mtTabela.setValueAt(tfCidade.getText(), this.linhaSelecionada, 9);
			this.mtTabela.setValueAt(tfCep.getText(), this.linhaSelecionada, 10);
			this.mtTabela.setValueAt(tfEstado.getText(), this.linhaSelecionada, 11);
			this.mtTabela.setValueAt(cbCargo.getSelectedItem(), this.linhaSelecionada, 12);
			this.setVisible(false); // Fecha a janela.
		} else { // Se o primeiro elemento do ArrayList não for null.
			String mensagem = "Não foi possível alterar o funcionário:\n";
			for (String e : erros) // Cria uma mensagem contendo todos os erros armazenados no ArrayList.
				mensagem = mensagem + e + "\n";
			JOptionPane.showMessageDialog(this, mensagem, "Erros", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}