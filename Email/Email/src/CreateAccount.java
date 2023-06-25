import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.net.PasswordAuthentication;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser; 

public class CreateAccount extends JFrame {

	private JFrame create;
	CreateAccount frame;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateAccount frame = new CreateAccount();
					frame.create.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	
	public CreateAccount() {
		create = new JFrame();
		create.setTitle("EmailSystem");
		create.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		create.setBounds(100,100,615,465);
		create.getContentPane().setLayout(null);
		create.setVisible(true);
		
		JLabel lblNewLabel = new JLabel("Create New Account ");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setBounds(203, 49, 152, 29);
		create.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("FIRST NAME :");
		lblNewLabel_1.setBounds(146, 126, 84, 16);
		create.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("LAST NAME :");
		lblNewLabel_2.setBounds(144, 153, 74, 14);
		create.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Date of birth :");
		lblNewLabel_3.setBounds(144, 178, 102, 14);
		create.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Custom Mail ID :");
		lblNewLabel_4.setBounds(146, 203, 110, 14);
		create.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Password :");
		lblNewLabel_5.setBounds(144, 228, 74, 14);
		create.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Conform Password :");
		lblNewLabel_6.setBounds(144, 261, 133, 14);
		create.getContentPane().add(lblNewLabel_6);
		
		textField = new JTextField();
		textField.setBounds(285, 150, 110, 17);
		create.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(285, 125, 110, 17);
		create.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("y-M-dd");
		dateChooser.setBounds(285, 178, 110, 19);
		create.getContentPane().add(dateChooser);
		
		
		
		
			
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(285, 205, 110, 17);
		create.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(285, 230, 110, 17);
		create.getContentPane().add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(285, 259, 110, 17);
		create.getContentPane().add(textField_5);
		
		JButton btnNewButton_1 = new JButton("REGESTER");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String startDateString = dateFormat.format(dateChooser.getDate());
				String password = textField_4.getText();
				String conform = textField_5.getText();
				
				
			     				
				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","12345");
					Statement stmt=con.createStatement();
					String query1="INSERT INTO user_infor"
							 + " (`firstname`, `lastname`, `dob`, `mailid`, `password`, `conform`) "
							 + "VALUES('" +textField.getText() +"','"+textField_1.getText()+"',"+startDateString+",'"+textField_3.getText()+"','"+textField_4.getText()+"','"+textField_5.getText()+"')";
							
					stmt.execute(query1);
					con.close();
				
				}
				catch(Exception e1) {
					 e1.printStackTrace();
				}
				
				new Login();
				create.setVisible(false);
			}
			
			
		});
		btnNewButton_1.setBounds(306, 327, 110, 23);
		create.getContentPane().add(btnNewButton_1);
		
		
		JButton btnNewButton = new JButton("CANCEL");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login();
				create.setVisible(false);
				
			}
		});
		btnNewButton.setBounds(162, 327, 89, 23);
		create.getContentPane().add(btnNewButton);
		
		
		
		
		
		
	}
}
