import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.ListModel;
import javax.swing.event.MouseInputAdapter;

import array.allmails;

import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import array.*;

public class Inbox {

	private JFrame frame;
	private String mailid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inbox window = new Inbox(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param mailid 
	 */
	public Inbox(String mailid) {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("EmailSystem");
		frame.setBounds(100, 100, 615, 457);
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
		
		JLabel L_Inbox = new JLabel("Inbox");
		L_Inbox.setFont(new Font("Times New Roman", Font.BOLD, 24));
		L_Inbox.setBounds(180, 30, 150, 45);
		panelB.add(L_Inbox);
		L_Inbox.setVisible(true);

		JLabel L_Mails = new JLabel("Mails");
		L_Mails.setFont (new Font ("Times New Roman", Font. PLAIN, 16));
		L_Mails.setBounds (20, 60, 55, 15);
		panelB.add(L_Mails);
		L_Mails.setVisible (true);

		DefaultListModel model=new DefaultListModel();
		
		
		 try {
             Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","12345");

             PreparedStatement st = (PreparedStatement) connection
                 .prepareStatement("Select * from allmails where mailfrom=?");
             st.setString(1, mailid);
             ResultSet rs = st.executeQuery();
             
             
             ArrayList<allmails> mail = new ArrayList<allmails>(); 
             while (rs.next()) {   
                 allmails mails = new allmails(); 
                 
                 //mailto,mailfrom,message,subject,read
                 mails.mailto=rs.getString("mailto"); 
                 mails.mailfrom=rs.getString("mailfrom");
                 mails.message=rs.getString("message");
                 mails.subject=rs.getString("subject");
                 mail.add(mails);
                 model.addElement(mail);
             }
             
             
             
		 }catch (SQLException sqlException) {
             sqlException.printStackTrace();
         }

         
         
			JList list= new JList(model);
			list.setBackground (new Color (240,240,240));
			list.setBounds (20, 83, 434, 304);
			panelB.add(list);
			list.setVisible(true);
		
		list.addMouseListener(new MouseInputAdapter(){
            public void mouseClicked(MouseEvent m){
                JOptionPane.showMessageDialog(null, list.getSelectedValue());
            }
        });
		
		JButton bInbox = new JButton("Inbox");
		bInbox.setBounds(7, 125, 90, 25);
		panelA.add(bInbox);
		
		JButton bUnread = new JButton("UnRead(0)");
		bUnread.setBounds(7, 185, 90, 25);
		panelA.add(bUnread);
		
		JButton bRead = new JButton("Read(0)");
		bRead.setBounds(7, 160, 90, 25);
		panelA.add(bRead);
		
		JButton bCompose = new JButton("Compose");
		bCompose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Compose(mailid);
				frame.setVisible(false);
			}			
		});
		bCompose.setForeground(Color.WHITE);
		bCompose.setFont(new Font("Times New Roman", Font.BOLD, 16));
		bCompose.setBackground(new Color(255, 51, 51));
		bCompose.setBounds(7, 65, 100, 25);
		panelA.add(bCompose);
		
	}
}
