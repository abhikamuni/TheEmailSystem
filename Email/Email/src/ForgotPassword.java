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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;

public class ForgotPassword extends JFrame {

	private JPanel contentPane;
	private JFrame forgot;
	ForgotPassword frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgotPassword frame = new ForgotPassword();
					frame.forgot.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ForgotPassword() {
		forgot= new JFrame();
		forgot.setTitle("EmailSystem");
		forgot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		forgot.setBounds(100,100,615,465);
		forgot.setVisible(true);
		forgot.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Forgotten Password ?");
		lblNewLabel.setBounds(221, 76, 155, 24);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		forgot.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Mail ID :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(151, 146, 129, 20);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		forgot.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Date of Birth :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(151, 205, 135, 18);
		forgot.getContentPane().add(lblNewLabel_2);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("y-M-dd");
		dateChooser.setBounds(316, 205, 111, 19);
		forgot.getContentPane().add(dateChooser);
		
		textField = new JTextField();
		textField.setBounds(314, 148, 113, 20);
		forgot.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("CANCEL");
		btnNewButton.setBounds(151, 265, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login();
				forgot.setVisible(false);
			}
		});
		forgot.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Change Password");
		btnNewButton_1.setBounds(298, 265, 184, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String dob = dateFormat.format(dateChooser.getDate());				
				String mailid = textField.getText();
				
				try {
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","12345");

                    PreparedStatement st = (PreparedStatement) connection
                        .prepareStatement("Select mailid, dob from user_infor where mailid=? and dob=?");

                    st.setString(1, mailid);
                    st.setString(2, dob);
                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {
                        dispose();  
                        new ChangePasword(mailid);
        				forgot.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(btnNewButton_1, "Wrong Username & date of birth");
                    }
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
				
			}
		});
		forgot.getContentPane().add(btnNewButton_1);
		
	}
}
