package view.login;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.LoginDao;
import view.main.TelaPrincipal;


@SuppressWarnings("serial")
public class TelaLogin extends JFrame {
	
	//elementos do frame
	private JPanel cp;
	private JTextField tfUsuario;
	private JPasswordField pfSenha;

	/**Método que realiza o login
	 * 
	 */
	/*@SuppressWarnings("deprecation")
	public void validaLogin() {
		dataHora = new SimpleDateFormat("[yyyy/MM/dd HH:mm:ss]").format(Calendar.getInstance().getTime());
		instrucaoSql = "SELECT login senha FROM usuario WHERE login = ? AND senha = ?";

		//conecta com o bd
		ConnectionDatabase.conectaBd();
		conexao = ConnectionDatabase.getConexaoBd();

		try {
			pst = conexao.prepareStatement(instrucaoSql);
			pst.setObject(1, tfUsuario.getText());
			pst.setObject(2, pfSenha.getText());
			rs = pst.executeQuery();
			if(rs.next()) {
				//emite log
				Registro log = new Registro();
				log.emitirRegistro(log.adicionarRegistro(dataHora + " - Login[" + tfUsuario.getText() + "]" + "\n"));
				
				//chama a tela principal
				TelaPrincipal principal = new TelaPrincipal();
				principal.setVisible(true);
				fechaTela();
			} else {
				JOptionPane.showMessageDialog(null, "Usuário e/ou Senha inválido(s)");
				tfUsuario.setText("");
				pfSenha.setText("");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Tipo de Exceção: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage());
		}
	}*/

	
	
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
		tfUsuario.setBounds(33, 104, 254, 23);
		tfUsuario.setColumns(10);

		JButton btConfirmar = new JButton("Confirmar");
		btConfirmar.setBounds(47, 264, 96, 32);
		btConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginDao login = new LoginDao();
				//recebe o cargo do login para fazer a gestão de acesso
				int cargo = login.validaLogin(tfUsuario.getText(), pfSenha.getText());
				
				if (login.getExcecao() == null) {
					TelaPrincipal principal = new TelaPrincipal(cargo);
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
		pfSenha.setBounds(33, 188, 254, 23);
		cp.add(pfSenha);

		JLabel lbEsqueciSenha = new JLabel("(esqueci a senha)");
		lbEsqueciSenha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lbEsqueciSenha.setBounds(174, 216, 113, 14);
		cp.add(lbEsqueciSenha);


	}

	protected void fechaTela() {this.dispose();}
}
