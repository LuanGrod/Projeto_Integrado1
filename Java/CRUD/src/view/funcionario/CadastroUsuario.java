package view.funcionario;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

public class CadastroUsuario extends JFrame{

	private JFrame frame;


	/**
	 * Create the application.
	 */
	public CadastroUsuario() {
		setTitle("Cadastrar Funcionario");
		setSize(500, 335);
		setLocationRelativeTo(null);
		initialize();
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