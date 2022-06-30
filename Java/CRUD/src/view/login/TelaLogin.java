package view.login;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.LoginDao;
import view.main.TelaPrincipal;
import java.awt.Toolkit;


@SuppressWarnings("serial")
public class TelaLogin extends JFrame {
	
	//elementos do frame
	private JPanel cp;
	private JTextField tfUsuario;
	private JPasswordField pfSenha;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaLogin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Arthur\\Desktop\\Projeto Bd\\pngaaa.com-1392699.png"));
		setResizable(false);
		setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 344, 362);
		cp = new JPanel();
		cp.setForeground(new Color(0, 0, 0));
		cp.setToolTipText("");
		setContentPane(cp);

		JLabel lbUsuario = new JLabel("Usu\u00E1rio");
		lbUsuario.setBounds(33, 66, 89, 32);
		lbUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lbSenha = new JLabel("Senha");
		lbSenha.setBounds(33, 145, 96, 32);
		lbSenha.setFont(new Font("Tahoma", Font.PLAIN, 18));

		tfUsuario = new JTextField();
		tfUsuario.setForeground(Color.BLACK);
		tfUsuario.setBounds(33, 104, 254, 23);
		tfUsuario.setColumns(10);

		JButton btConfirmar = new JButton("Confirmar");
		btConfirmar.setBounds(47, 264, 96, 32);
		btConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginDao login = new LoginDao();
				//recebe o cargo do login para fazer a gest�o de acesso
				@SuppressWarnings("deprecation")
				int cargo = login.validaLogin(tfUsuario.getText(), pfSenha.getText());
				
				if (login.getExcecao() == null) {
					TelaPrincipal principal = null;
					try {
						principal = new TelaPrincipal(cargo);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					principal.setVisible(true);
					fechaTela();
					
				}else {
					JOptionPane.showMessageDialog(null, login.getExcecao());
					tfUsuario.setText("");
					pfSenha.setText("");
				}
			}
		});
		


		JButton btCancelar = new JButton("Cancelar");
		btCancelar.setBounds(174, 264, 96, 32);
		cp.setLayout(null);
		cp.add(btConfirmar);
		cp.add(lbSenha);
		cp.add(lbUsuario);
		cp.add(btCancelar);
		cp.add(tfUsuario);

		pfSenha = new JPasswordField();
		pfSenha.setForeground(Color.BLACK);
		pfSenha.setBounds(33, 188, 254, 23);
		cp.add(pfSenha);


	}

	protected void fechaTela() {this.dispose();}
}
