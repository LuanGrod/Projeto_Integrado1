package view.login;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import view.main.TelaPrincipal;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JPasswordField;
import controller.UsuarioController;
import dao.ConnectionDatabase;

import model.usuario.Usuario;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class TelaLogin extends JFrame {
	private JPanel cp;
	private JTextField tfUsuario;
	private JPasswordField pfSenha;

	private ResultSet rs; // Atributo que recebe os dados retornados por uma instrução SQL.


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
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
		setBounds(100, 100, 343, 362);
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
				rs = new UsuarioController().validaLogin(tfUsuario.getText(), pfSenha.getText());
				try {
					if(rs.next()) {
						TelaPrincipal principal = new TelaPrincipal();
						TelaLogin telaLogin = new TelaLogin();
						telaLogin.setVisible(false);
						principal.setVisible(true);
					} else
					JOptionPane.showMessageDialog(null, "Usuário e/ou senha invalido(s)");
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Tipo de Exceção: " + ex.getClass().getSimpleName() + "\nMensagem: " + ex.getMessage());
				}
				
				/*
				try {
					instrucaoSql = "select login senha from usuario where login = ? and senha = ?";
					String excecao = ConnectionDatabase.conectaBd(); // Abre a conexão com o banco de dados.
					pst = new ConnectionDatabase().getConexaoBd().prepareStatement(instrucaoSql);
					Usuario usuario = new Usuario();
					usuario.setLogin(tfUsuario.getText());
					usuario.setSenha(pfSenha.getText());;
					pst.setObject(1, usuario.getLogin());
					pst.setObject(2, usuario.getSenha());
					rs = pst.executeQuery();
					if(rs.next()) {
						TelaPrincipal principal = new TelaPrincipal();
						TelaLogin telaLogin = new TelaLogin();
						telaLogin.setVisible(false);
						principal.setVisible(true);
					} else
					JOptionPane.showMessageDialog(null, "Usuário e/ou senha invalido(s)");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Tipo de Exceção: " + ex.getClass().getSimpleName() + "\nMensagem: " + ex.getMessage());
				}
				 */
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
}
