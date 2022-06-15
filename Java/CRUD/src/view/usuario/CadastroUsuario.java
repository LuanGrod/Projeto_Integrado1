package view.usuario;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.UsuarioController;
import model.cargo.Cargo;

public class CadastroUsuario extends JFrame{

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField tfCPF;
	private JTextField tfNome;
	private JTextField tfTelefone;
	private JTextField tfEmail;
	private JTextField tfLogin;
	private JTextField tfSenha;
	private JTextField tfCEP;
	private JTextField tfRua;
	private JTextField tfCidade;
	private JTextField tfBairro;
	private JTextField tfEstado;
	private JComboBox<Cargo> cbCargo;


	/**
	 * Create the application.
	 */
	public CadastroUsuario() {
		setTitle("Cadastro Funcionario");
		setSize(550, 400);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(25, 71, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("CPF");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(25, 31, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Senha");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(251, 191, 46, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Login");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(251, 151, 46, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Cargo");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(251, 111, 46, 14);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Cel");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(25, 111, 46, 14);
		getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Email");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(25, 151, 46, 14);
		getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Rua");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(25, 231, 46, 14);
		getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Bairro");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_8.setBounds(251, 231, 46, 14);
		getContentPane().add(lblNewLabel_8);
		
		tfCPF = new JTextField();
		tfCPF.setBounds(62, 28, 120, 20);
		getContentPane().add(tfCPF);
		tfCPF.setColumns(10);
		
		tfNome = new JTextField();
		tfNome.setBounds(62, 68, 239, 20);
		getContentPane().add(tfNome);
		tfNome.setColumns(10);
		
		tfTelefone = new JTextField();
		tfTelefone.setBounds(62, 108, 120, 20);
		getContentPane().add(tfTelefone);
		tfTelefone.setColumns(10);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(62, 148, 151, 20);
		getContentPane().add(tfEmail);
		tfEmail.setColumns(10);
		
		cbCargo = new JComboBox<>();
		List<Cargo> cargos = new ArrayList<Cargo>();
		cargos = new UsuarioController().recuperaCargos();
		cbCargo.setBounds(295, 107, 113, 22);
		getContentPane().add(cbCargo);
		if (cargos != null)
			for (Cargo c : cargos)
				cbCargo.addItem(c);
		
		String erro = new UsuarioController().getExcecao();
		
		if (erro != null) // Caso ocorra qualquer tipo de exceção.
			JOptionPane.showMessageDialog(null, "Não foi possível recuperar os dados dos cargos:\n" + erro, 
					                      "Erro", JOptionPane.ERROR_MESSAGE);
		
		tfLogin = new JTextField();
		tfLogin.setBounds(295, 149, 151, 20);
		getContentPane().add(tfLogin);
		tfLogin.setColumns(10);
		
		tfSenha = new JTextField();
		tfSenha.setBounds(295, 188, 125, 20);
		getContentPane().add(tfSenha);
		tfSenha.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("CEP");
		lblNewLabel_9.setBounds(25, 192, 46, 14);
		getContentPane().add(lblNewLabel_9);
		
		tfCEP = new JTextField();
		tfCEP.setBounds(62, 189, 96, 20);
		getContentPane().add(tfCEP);
		tfCEP.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Cidade");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_10.setBounds(25, 273, 46, 14);
		getContentPane().add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Estado");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_11.setBounds(251, 273, 46, 14);
		getContentPane().add(lblNewLabel_11);
		
		tfRua = new JTextField();
		tfRua.setBounds(62, 229, 151, 20);
		getContentPane().add(tfRua);
		tfRua.setColumns(10);
		
		tfCidade = new JTextField();
		tfCidade.setBounds(62, 270, 151, 20);
		getContentPane().add(tfCidade);
		tfCidade.setColumns(10);
		
		tfBairro = new JTextField();
		tfBairro.setBounds(295, 229, 86, 20);
		getContentPane().add(tfBairro);
		tfBairro.setColumns(10);
		
		tfEstado = new JTextField();
		tfEstado.setBounds(295, 270, 86, 20);
		getContentPane().add(tfEstado);
		tfEstado.setColumns(10);
		
		JButton btnCadastrarUsuario = new JButton("Cadastrar");
		btnCadastrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCadastrarUsuarioAction();
			}
		});
		btnCadastrarUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCadastrarUsuario.setBounds(186, 316, 102, 33);
		getContentPane().add(btnCadastrarUsuario);
		initialize();
	}
	
	

	private void btnCadastrarUsuarioAction() {
		List<String> erros = new ArrayList<String>();
				
		erros = new UsuarioController().insereUsuario(tfLogin.getText(), tfSenha.getText(), tfCPF.getText(),  tfNome.getText(), 
				tfTelefone.getText(),tfEmail.getText(), tfRua.getText(), tfBairro.getText(), tfCidade.getText(), tfCEP.getText(), 
				tfEstado.getText(),   (Cargo)cbCargo.getSelectedItem());
		
		if (erros.get(0) == null) { // Se o primeiro elemento do ArrayList for null.
			JOptionPane.showMessageDialog(this, "Funcionário cadastrado com sucesso.", 
					                      "Informação", JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(false); // Fecha a janela.
		} else { // Se o primeiro elemento do ArrayList não for null.
			String mensagem = "Não foi possível cadastrar o funcionário:\n";
			for (String e : erros) // Cria uma mensagem contendo todos os erros armazenados no ArrayList.
				mensagem = mensagem + e + "\n";
			JOptionPane.showMessageDialog(this, mensagem, "Erros", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
