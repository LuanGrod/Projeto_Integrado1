package view.cliente;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.ClienteController;


public class CadastroCliente extends JFrame{


	private static final long serialVersionUID = 1L;
	private JFrame frmCadastroCliente;
	private JTextField tfNome;
	private JTextField tfCpf;
	private JTextField tfTel;
	private JTextField tfRua;
	private JTextField tfCidade;
	private JTextField tfCep;
	private JTextField tfEmail;
	private JTextField tfBairro;
	private JTextField tfEstado;


	public CadastroCliente() {
		
		setTitle("Cadastro Cliente");
		setSize(518, 365);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		
		JLabel lblNewLabel = new JLabel("*Nome");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(26, 38, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("*CPF");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(26, 78, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Tel");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(276, 75, 46, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(276, 38, 46, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Rua");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(26, 121, 46, 14);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Bairro");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(276, 121, 46, 14);
		getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Cidade");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(26, 161, 46, 14);
		getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Estado");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(276, 161, 46, 14);
		getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("CEP");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setBounds(26, 201, 46, 14);
		getContentPane().add(lblNewLabel_8);
		
		tfNome = new JTextField();
		tfNome.setBounds(80, 37, 180, 20);
		getContentPane().add(tfNome);
		tfNome.setColumns(10);
		
		tfCpf = new JTextField();
		tfCpf.setBounds(80, 77, 86, 20);
		getContentPane().add(tfCpf);
		tfCpf.setColumns(10);
		
		tfTel = new JTextField();
		tfTel.setBounds(330, 74, 86, 20);
		getContentPane().add(tfTel);
		tfTel.setColumns(10);
		
		tfRua = new JTextField();
		tfRua.setBounds(80, 120, 180, 20);
		getContentPane().add(tfRua);
		tfRua.setColumns(10);
		
		tfCidade = new JTextField();
		tfCidade.setBounds(80, 160, 180, 20);
		getContentPane().add(tfCidade);
		tfCidade.setColumns(10);
		
		tfCep = new JTextField();
		tfCep.setBounds(80, 200, 86, 20);
		getContentPane().add(tfCep);
		tfCep.setColumns(10);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(329, 37, 140, 20);
		getContentPane().add(tfEmail);
		tfEmail.setColumns(10);
		
		tfBairro = new JTextField();
		tfBairro.setBounds(329, 120, 140, 20);
		getContentPane().add(tfBairro);
		tfBairro.setColumns(10);
		
		tfEstado = new JTextField();
		tfEstado.setBounds(330, 160, 140, 20);
		getContentPane().add(tfEstado);
		tfEstado.setColumns(10);
		
		JButton btnCadastroCliente = new JButton("Cadastrar Cliente");
		btnCadastroCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCadastrarCliente();
			}
		});
		btnCadastroCliente.setBounds(304, 251, 124, 37);
		getContentPane().add(btnCadastroCliente);
		initialize();
	}
	
	private void btnCadastrarCliente() {
		List<String> erros = new ArrayList<String>();
		erros = new ClienteController().insereCliente(tfNome.getText(), tfCpf.getText(), tfTel.getText(), tfEmail.getText(), tfRua.getText(), 
				tfBairro.getText(), tfCidade.getText(), tfCep.getText(), tfEstado.getText());
		
		if (erros.get(0) == null) { // Se o primeiro elemento do ArrayList for null.
			JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso.", 
					                      "Informação", JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(false); // Fecha a janela.
		} else { // Se o primeiro elemento do ArrayList não for null.
			String mensagem = "Não foi possível cadastrar o cliente:\n";
			for (String e : erros) // Cria uma mensagem contendo todos os erros armazenados no ArrayList.
				mensagem = mensagem + e + "\n";
			JOptionPane.showMessageDialog(this, mensagem, "Erros", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	
	
	
	
	
	
	
	private void initialize() {
		frmCadastroCliente = new JFrame();
		frmCadastroCliente.setTitle("Cadastro Cliente");
		frmCadastroCliente.setBounds(100, 100, 537, 318);
		frmCadastroCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}




		