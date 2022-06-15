package view.fornecedor;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.FornecedorController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class CadastroFornecedor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfCnpj;
	private JTextField tfTel;
	private JTextField tfEmail;
	private JTextField tfNome;

	
	public CadastroFornecedor() {
		setTitle("Cadastro Fornecedor");
		setBounds(100, 100, 401, 313);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		
		JLabel lbCnpj = new JLabel("CNPJ");
		lbCnpj.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbCnpj.setBounds(35, 40, 46, 14);
		contentPane.add(lbCnpj);
		
		JLabel lbTel = new JLabel("Tel");
		lbTel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbTel.setBounds(34, 143, 46, 14);
		contentPane.add(lbTel);
		
		JLabel lbEmail = new JLabel("Email");
		lbEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbEmail.setBounds(34, 193, 46, 14);
		contentPane.add(lbEmail);
		
		tfCnpj = new JTextField();
		tfCnpj.setBounds(70, 37, 131, 20);
		contentPane.add(tfCnpj);
		tfCnpj.setColumns(10);
		
		tfTel = new JTextField();
		tfTel.setBounds(69, 140, 131, 20);
		contentPane.add(tfTel);
		tfTel.setColumns(10);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(69, 190, 218, 20);
		contentPane.add(tfEmail);
		tfEmail.setColumns(10);
		
		JButton btnCadFornecedor = new JButton("Cadastrar Fornecedor");
		btnCadFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCadFornecedor();
			}
		});
		btnCadFornecedor.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCadFornecedor.setBounds(201, 231, 161, 32);
		contentPane.add(btnCadFornecedor);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(35, 90, 46, 14);
		contentPane.add(lblNewLabel);
		
		tfNome = new JTextField();
		tfNome.setBounds(70, 87, 217, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
	}
	
	private void btnCadFornecedor() {
		List<String> erros = new ArrayList<String>();
		
		erros = new FornecedorController().insereFornecedor(tfNome.getText(), tfEmail.getText(), tfCnpj.getText(), tfTel.getText());
		
		if (erros.get(0) == null) { // Se o primeiro elemento do ArrayList for null.
			JOptionPane.showMessageDialog(this, "Fornecedor cadastrado com sucesso.", 
					                      "Informação", JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(false); // Fecha a janela.
		} else { // Se o primeiro elemento do ArrayList não for null.
			String mensagem = "Não foi possível cadastrar o fornecedor:\n";
			for (String e : erros) // Cria uma mensagem contendo todos os erros armazenados no ArrayList.
				mensagem = mensagem + e + "\n";
			JOptionPane.showMessageDialog(this, mensagem, "Erros", JOptionPane.ERROR_MESSAGE);
		}
	}
}
