package view.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.JSpinner;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JLayeredPane;

@SuppressWarnings("serial")
public class TelaPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("ERP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.GRAY);
		menuBar.setBounds(0, 0, 884, 22);
		contentPane.add(menuBar);
		
		JMenu mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);
		
		JMenuItem miCliente = new JMenuItem("Cliente");
		miCliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
		mnCadastro.add(miCliente);
		
		JMenuItem miFuncionario = new JMenuItem("Funcion\u00E1rio");
		miFuncionario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK));
		mnCadastro.add(miFuncionario);
		
		JMenu mnRelatorio = new JMenu("Relat\u00F3rio");
		menuBar.add(mnRelatorio);
		
		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);
		
		JMenu mnNewMenu_3 = new JMenu("Sair");
		mnNewMenu_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechaTela();
			}
		});
		
		JMenu mnOpcoes = new JMenu("Op\u00E7\u00F5es");
		menuBar.add(mnOpcoes);
		menuBar.add(mnNewMenu_3);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 21, 211, 640);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome do Sistema");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(36, 41, 157, 32);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Produto");
		btnNewButton.setBounds(0, 104, 211, 57);
		panel.add(btnNewButton);
		
		JButton btnCliente = new JButton("Cliente");
		btnCliente.setBounds(0, 158, 211, 57);
		panel.add(btnCliente);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(210, 21, 674, 640);
		contentPane.add(layeredPane);
	}
	
	protected void fechaTela() {this.dispose();}
}