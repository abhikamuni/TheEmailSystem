import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class ChangePasword extends JFrame {

	private JFrame change;
	ChangePasword frame;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private String mailid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePasword frame = new ChangePasword();
					frame.change.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 public ChangePasword(String mailid) {
	    	this.mailid = mailid;
		}

	/**
	 * Create the frame.
	 */
	public ChangePasword() {
		change= new JFrame();
		change.setTitle("EmailSystem");
		change.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		change.setBounds(100,100,615,465);
		change.getContentPane().setLayout(null);
		change.setVisible(true);
		
		JLabel lblNewLabel = new JLabel("Changing Password ");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setBounds(202, 80, 144, 21);
		change.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter New Password :");
		lblNewLabel_1.setBounds(135, 156, 140, 21);
		change.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(285, 156, 119, 21);
		change.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Conform Password :");
		lblNewLabel_2.setBounds(135, 220, 140, 14);
		change.getContentPane().add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(285, 217, 119, 20);
		change.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		    
			    String password = textField.getText();
			    String Cpassword = textField_1.getText();
			    
				
				try {
					Connection connection = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","12345");
					 
					PreparedStatement st = (PreparedStatement) connection
		                        .prepareStatement("update user_infor set passward=?,comform=? where Id=? ");
					
					
					
				    st.setString(1, password);
					st.setString(2, Cpassword);
					st.setString(3, mailid);
					ResultSet rs = st.executeQuery();
					if(rs.next()){
						dispose();
					
					new Login();
					change.setVisible(false);
					JOptionPane.showMessageDialog(btnNewButton, "Password is updated successfully......");
					}
					else {
                        JOptionPane.showMessageDialog(btnNewButton, "password and conforme password is must be same....");
					}
				}
				catch(Exception e1) {
					 e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setBounds(217, 281, 89, 23);
		change.getContentPane().add(btnNewButton);
	}

	

}
