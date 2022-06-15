package view.encomenda;

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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.EncomendaController;
import model.fornecedor.Fornecedor;
import model.produto.Produto;

public class CadastroEncomenda extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfQnt;
	private JComboBox<Produto> cbProduto;
	private JComboBox<Fornecedor> cbFornecedor;



	public CadastroEncomenda() {
		setTitle("Encomenda");
		setBounds(100, 100, 450, 252);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Produto");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(30, 40, 46, 14);
		contentPane.add(lblNewLabel);
		
		cbProduto = new JComboBox<>();
		cbProduto.setBounds(86, 36, 132, 22);
		contentPane.add(cbProduto);
		List<Produto> produtos = new ArrayList<>();
		produtos = new EncomendaController().consultaProdutos();
		if(produtos != null)
			for(Produto p : produtos)
				cbProduto.addItem(p);
		
		JLabel lblNewLabel_1 = new JLabel("Fornecedor");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(30, 80, 68, 14);
		contentPane.add(lblNewLabel_1);
		
		cbFornecedor = new JComboBox<>();
		cbFornecedor.setBounds(96, 76, 146, 22);
		contentPane.add(cbFornecedor);
		List <Fornecedor> fornecedores = new ArrayList<>();
		fornecedores = new EncomendaController().consultaFornecedores();
		if(fornecedores != null)
			for(Fornecedor f : fornecedores)
				cbFornecedor.addItem(f);
		
		JLabel lblNewLabel_2 = new JLabel("Quantidade");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(30, 120, 68, 14);
		contentPane.add(lblNewLabel_2);
		
		tfQnt = new JTextField();
		tfQnt.setBounds(95, 117, 86, 20);
		contentPane.add(tfQnt);
		tfQnt.setColumns(10);
		
		JButton btnCadEncomenda = new JButton("Cadastrar Encomenda");
		btnCadEncomenda.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCadEncomenda.setBounds(238, 156, 146, 30);
		contentPane.add(btnCadEncomenda);
		setLocationRelativeTo(null);
		
		btnCadEncomenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCadEncomenda();
			}
		});

	}
	private void btnCadEncomenda() {
		List<String> erros = new ArrayList<String>();
		
		erros = new EncomendaController().insereEncomenda((Produto) cbProduto.getSelectedItem(), (Fornecedor) cbFornecedor.getSelectedItem(), Integer.parseInt(tfQnt.getText()));
		
		if (erros.get(0) == null) { // Se o primeiro elemento do ArrayList for null.
			JOptionPane.showMessageDialog(this, "Encomenda cadastrada com sucesso.", 
					                      "Informação", JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(false); // Fecha a janela.
		} else { // Se o primeiro elemento do ArrayList não for null.
			String mensagem = "Não foi possível cadastrar a encomenda:\n";
			for (String e : erros) // Cria uma mensagem contendo todos os erros armazenados no ArrayList.
				mensagem = mensagem + e + "\n";
			JOptionPane.showMessageDialog(this, mensagem, "Erros", JOptionPane.ERROR_MESSAGE);
		}
	}
}
