import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.event.MouseInputAdapter;

public class Compose {

	private JFrame frame;
	private JTextField textField;
	protected String mailid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Compose window = new Compose();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Compose() {
		initialize();
	}

	public Compose(String mailid) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("EmailSystem");
		frame.setBounds(100, 100, 615, 463);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JSplitPane splitPane =new JSplitPane();

		splitPane.setBounds(5,0,590,400);
		splitPane.setOneTouchExpandable (true);
		splitPane.setDividerLocation (120);
		splitPane.setVisible(true);
		frame.getContentPane().add(splitPane);
		
		JMenuBar menubar= new JMenuBar();

		frame.setJMenuBar(menubar);
		JMenu log=new JMenu("Log");
		menubar.add(log);
		JMenu about =new JMenu(); 
		about.setText("about");
		menubar.add(about);
		JMenu help=new JMenu("Help");
		menubar.add(help);

		JMenuItem login=new JMenuItem("Login"); 
		JMenuItem logout=new JMenuItem("Logout"); 
		JMenuItem quit=new JMenuItem("Exit");
		JMenuItem aboutUs= new JMenuItem("About Us");
		JMenuItem helpl= new JMenuItem("Help   F1");
		log.add(login); 
		log.add(logout);
		log.add(quit);
		help.add(helpl);
		quit.addActionListener (new ActionListener() { 
	        public void actionPerformed (ActionEvent a) { 
	            System.exit(0);
	        }
	    });
		about.add(aboutUs);
		logout.setVisible(false);
		login.setVisible(true);
		
		JPanel panelA = new JPanel();
		splitPane.setLeftComponent (panelA);
		panelA.setLayout (null);		
		
		JPanel panelB=new JPanel();
		splitPane.setRightComponent (panelB);
		panelB.setLayout(null);
		
		JLabel L_Compose = new JLabel("New Mail");
		L_Compose.setFont(new Font("Times New Roman", Font.BOLD, 18));
		L_Compose.setBounds(178, 27, 80, 21);
		panelB.add(L_Compose);
		
		JLabel L_from = new JLabel("From :-");
		L_from.setBounds(0, 62, 56, 14);
		panelB.add(L_from);
		
		textField = new JTextField();
		textField.setBounds(70, 59, 380, 19);
		panelB.add(textField);
		textField.setColumns(10);
		
		JLabel L_To=new JLabel ("To:-");
		L_To.setBounds (0, 90, 45, 15);
		panelB.add(L_To);
		L_To.setVisible(true);

		JLabel L_Subject= new JLabel("Subject:");
		L_Subject.setBounds (0, 120, 60, 10);
		panelB.add(L_Subject); 
		L_Subject.setVisible (true);

		JLabel L_Message= new JLabel ("Message:");
		L_Message.setBounds(0, 150, 75, 20);
		panelB.add (L_Message);
		L_Message.setVisible (true);

		JTextField textFieldTo= new JTextField();
		textFieldTo.setBounds (70, 88, 380, 20);
		panelB.add(textFieldTo);
		textFieldTo.setVisible(true);

		JTextField textFieldSubject = new JTextField();
		textFieldSubject.setColumns (10);
		textFieldSubject.setBounds(70,120,380,20);
		panelB.add(textFieldSubject);
		textFieldSubject.setVisible(true);

		JEditorPane editorPane=new JEditorPane();
		editorPane.setBounds(70, 150, 380, 195);
		panelB.add(editorPane);
		editorPane.setVisible (true);

		DefaultListModel model=new DefaultListModel();
		JButton bSend =new JButton("Send");
		bSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String values = "1";
				
				
				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","12345");
					Statement stmt=con.createStatement();
					String query1="INSERT INTO allmails"
							 + " (`mailto`, `mailfrom`, `message`, `subject`, `read`) "
							 + "VALUES('" +textFieldTo.getText() +"','"+textField.getText()+"',"+editorPane.getText()+",'"+textFieldSubject.getText()+"','"+values+"')";
							
					stmt.execute(query1);
					con.close();
					
					JOptionPane.showMessageDialog(bSend, "Email is succesfully");
				
				}
				catch(Exception e1) {
					 e1.printStackTrace();
				}				
			}
		});
		bSend.setBackground (new Color(0, 128, 255));
		bSend.setForeground (new Color (255, 255, 255));
		bSend. setFont(new Font("Times New Roman", Font.BOLD, 16));
		bSend.setBounds (98, 356, 91, 31);
		panelB.add(bSend);
		
		
		
		JButton bInbox = new JButton("Inbox");
		bInbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Inbox(mailid);
				frame.setVisible(false);
			}
		});
		bInbox.setBounds(7, 125, 90, 25);
		panelA.add(bInbox);
		
		JButton bUnread = new JButton("UnRead(0)");
		bUnread.setBounds(7, 185, 90, 25);
		panelA.add(bUnread);
		
		JButton bRead = new JButton("Read(0)");
		bRead.setBounds(7, 160, 90, 25);
		panelA.add(bRead);
		
		JButton bCompose = new JButton("Compose");
		bCompose.setForeground(Color.WHITE);
		bCompose.setFont(new Font("Times New Roman", Font.BOLD, 16));
		bCompose.setBackground(new Color(255, 51, 51));
		bCompose.setBounds(7, 65, 100, 25);
		panelA.add(bCompose);
		
		JButton L_Cancel = new JButton("Cancel");
		L_Cancel.setBackground(new Color(255, 0, 0));
		L_Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textFieldTo.setText(""); 
				textField.setText("");
				editorPane.setText("");
				textFieldSubject.setText("");
				
			}
		});
		L_Cancel.setBounds(329, 356, 91, 31);
		panelB.add(L_Cancel);
		
		JLabel lblNewLabel = new JLabel("words:- 250 ");
		lblNewLabel.setBounds(370, 137, 80, 13);
		panelB.add(lblNewLabel);
	}
}
