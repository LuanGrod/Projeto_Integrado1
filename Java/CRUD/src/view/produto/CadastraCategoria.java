package view.produto;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.ProdutoController;

public class CadastraCategoria extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfCategoria;
	
	public CadastraCategoria() {
		setTitle("Cadastrar Categoria");
		setBounds(100, 100, 450, 197);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		setModal(true);
		
		JLabel lblNewLabel = new JLabel("Nome da Categoria");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(29, 58, 105, 14);
		contentPanel.add(lblNewLabel);
		
		tfCategoria = new JTextField();
		tfCategoria.setBounds(144, 55, 193, 20);
		contentPanel.add(tfCategoria);
		tfCategoria.setColumns(10);
		{
			JButton btnCadCategoria = new JButton("Cadastrar");
			btnCadCategoria.setBounds(291, 113, 105, 23);
			contentPanel.add(btnCadCategoria);
			btnCadCategoria.setActionCommand("OK");
			getRootPane().setDefaultButton(btnCadCategoria);

			btnCadCategoria.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnCadCategoria();
				}
			});
		}
	}
	
	private void btnCadCategoria() {
		List<String> erros = new ArrayList<String>();
		erros = new ProdutoController().insereCategoria(tfCategoria.getText());
		
		if (erros.size() == 0) { // Se o primeiro elemento do ArrayList for null.
			JOptionPane.showMessageDialog(this, "Categoria cadastrada com sucesso.", 
					                      "Informação", JOptionPane.INFORMATION_MESSAGE);
			
			try{
				CadastroProduto.populaCb();
			}catch(NullPointerException e) {
				AlteracaoProduto.populaCb();
			}
			this.setVisible(false); // Fecha a janela.
			
		} else { // Se o primeiro elemento do ArrayList não for null.
			String mensagem = "Não foi possível cadastrar a categoria:\n";
			for (String e : erros) // Cria uma mensagem contendo todos os erros armazenados no ArrayList.
				mensagem = mensagem + e + "\n";
			JOptionPane.showMessageDialog(this, mensagem, "Erros", JOptionPane.ERROR_MESSAGE);
		}
	}
}
