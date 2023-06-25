import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.util.regex.Pattern;
import java.sql.*;

public class Login extends JFrame {

	private JFrame login;
	Login frame;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.login.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	/**
	 * Create the frame.
	 */
	public Login() {
		login= new JFrame();
		login.setTitle("EmailSystem");
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setBounds(100,100,615,465);
		login.getContentPane().setLayout(null);
		login.setVisible(true);
		
		
		JLabel lblNewLabel = new JLabel("Email ID :");
		lblNewLabel.setBounds(180, 125, 58, 14);
		login.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Login Page");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1.setBounds(233, 51, 92, 28);
		login.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(273, 122, 130, 20);
		login.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Passward :");
		lblNewLabel_2.setBounds(174, 176, 64, 14);
		login.getContentPane().add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(273, 173, 130, 20);
		login.getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("SIGN IN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mailid = textField.getText();
                String password = passwordField.getText();
				
               				
				  try {
	                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","12345");

	                    PreparedStatement st = (PreparedStatement) connection
	                        .prepareStatement("Select mailid, password from user_infor where mailid=? and password=?");

	                    st.setString(1, mailid);
	                    st.setString(2, password);
	                    ResultSet rs = st.executeQuery();
	                    if (rs.next()) {
	                        dispose();
	                        new Inbox(mailid);
	        				login.setVisible(false);
	                       
	                        JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in");
	                    } else {
	                        JOptionPane.showMessageDialog(btnNewButton, "Wrong Username & Password");
	                    }
	                } catch (SQLException sqlException) {
	                    sqlException.printStackTrace();
	                }
				  
				  
			
			}
		});
		btnNewButton.setBounds(266, 237, 89, 23);
		login.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Forgot password");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ForgotPassword();
				login.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(115, 283, 147, 23);
		login.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Create account");
		btnNewButton_2.setBounds(336, 283, 142, 23);
		login.getContentPane().add(btnNewButton_2);
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateAccount();
				login.setVisible(false);
				
			}
		});
	}
}

