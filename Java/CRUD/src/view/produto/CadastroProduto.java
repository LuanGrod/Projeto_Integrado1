package view.produto;

import java.awt.Container;
import java.awt.Font;
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

public class  CadastroProduto extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField tfNome;
	private JTextField tfQntEstq;
	private JTextField tfPrecoVenda;
	private JTextField tfPrecoCusto;
	public  static JComboBox<Categoria> cbCategoria;
	private Container cp;

	
	public CadastroProduto() {
		setTitle("Cadastro Produto");
		setBounds(100, 100, 485, 340);
		setLocationRelativeTo(null);
		cp = getContentPane(); 
		cp.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(30, 30, 46, 14);
		cp.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Preco Custo");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(30, 153, 78, 14);
		cp.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Preco Venda");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(30, 192, 78, 14);
		cp.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Quantidade em Estoque");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_3.setBounds(30, 110, 148, 14);
		cp.add(lblNewLabel_3);
		
		tfNome = new JTextField();
		tfNome.setBounds(74, 27, 148, 20);
		cp.add(tfNome);
		tfNome.setColumns(10);
		
		tfQntEstq = new JTextField("0");
		tfQntEstq.setBounds(158, 107, 51, 20);
		cp.add(tfQntEstq);
		tfQntEstq.setColumns(10);
		
		tfPrecoVenda = new JTextField("0.00");
		tfPrecoVenda.setColumns(10);
		tfPrecoVenda.setBounds(112, 188, 78, 20);
		cp.add(tfPrecoVenda);
		
		tfPrecoCusto = new JTextField("0.00");
		tfPrecoCusto.setColumns(10);
		tfPrecoCusto.setBounds(112, 149, 78, 20);
		cp.add(tfPrecoCusto);
		
		cbCategoria = new JComboBox<>();
		cbCategoria.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbCategoria.setBounds(95, 67, 114, 22);
		cp.add(cbCategoria);
		populaCb();	
		
			
		JButton btnCriaCategoria = new JButton("Criar nova categoria");
		btnCriaCategoria.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCriaCategoria.setBounds(242, 67, 136, 23);
		cp.add(btnCriaCategoria);
		
		JButton btnCadastraProduto = new JButton("Cadastrar Produto");
		btnCadastraProduto.setBounds(270, 211, 148, 28);
		cp.add(btnCadastraProduto);
		
		JLabel lblNewLabel_4 = new JLabel("Categoria");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4.setBounds(30, 71, 55, 14);
		cp.add(lblNewLabel_4);
		
		

		btnCriaCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCriaCategoria();
			}
		});
		

		btnCadastraProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCadastrarProduto();
			}
		});
	}


	public static void populaCb() {
		cbCategoria.removeAllItems();
		List<Categoria> categorias = new ArrayList<>();
		categorias = new ProdutoController().recuperaCategorias();
		if(categorias != null) {
			for(Categoria c : categorias) {
				cbCategoria.addItem(c);
			}
		}
	}
	
	
	
	
	public void btnCriaCategoria() {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){ new CadastraCategoria().setVisible(true); }});
		SwingUtilities.updateComponentTreeUI(cp);
	}
	
	private void btnCadastrarProduto() {
		List<String> erros = new ArrayList<String>();
		double pcusto = 0, pvenda = 0;
		int	qnt = 0;
		
		try{
			pcusto = Double.parseDouble(tfPrecoCusto.getText());
			pvenda = Double.parseDouble(tfPrecoVenda.getText());
			qnt = Integer.parseInt(tfQntEstq.getText());
			
		}catch(NumberFormatException nfe){
			System.out.println("ERRO: " + nfe.getMessage());
		}
		
		
		
		erros = new ProdutoController().insereProduto(tfNome.getText(), pcusto, pvenda, qnt, (Categoria)cbCategoria.getSelectedItem());
		
		if (erros.get(0) == null) { // Se o primeiro elemento do ArrayList for null.
			JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso.", 
					                      "Informação", JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(false); // Fecha a janela.
		} else { // Se o primeiro elemento do ArrayList não for null.
			String mensagem = "Não foi possível cadastrar o produto:\n";
			for (String e : erros) // Cria uma mensagem contendo todos os erros armazenados no ArrayList.
				mensagem = mensagem + e + "\n";
			JOptionPane.showMessageDialog(this, mensagem, "Erros", JOptionPane.ERROR_MESSAGE);
		}
	}
}
