package Youtube.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Youtube.model.Category;
import Youtube.model.Volunteer;
import Youtube.model.Vote;
import Youtube.repo.VolunteerRepo;
import Youtube.repo.VoteRepo;
import Youtube.service.*;
import java.awt.Color;

public class VolunteerLogin extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JButton cancelButton;
	public static Volunteer volunteer;
	private JTextField txtID;
	private JTextField txtFirst;
	private JTextField txtLast;
	public	VolunteerLogin volunteerLogin;
	private VolunteerRepo volunteers ;
	private Youtube.repo.VoteRepo votes;
	
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			VolunteerLogin dialog = new VolunteerLogin(null,null,null);
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public VolunteerLogin(VolunteerRepo volunteers,VoteRepo votes) {
		this.volunteers = volunteers ;
		this.votes = votes;
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblVolunteerID = new JLabel("Volunterer ID");
			lblVolunteerID.setBounds(33, 71, 128, 14);
			contentPanel.add(lblVolunteerID);
		}
		
		txtID = new JTextField();
		txtID.setBounds(264, 68, 107, 20);
		contentPanel.add(txtID);
		txtID.setColumns(10);
		
		
		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setBounds(33, 113, 156, 14);
		contentPanel.add(lblNewLabel);
		
		txtFirst = new JTextField();
		txtFirst.setBounds(264, 110, 107, 20);
		contentPanel.add(txtFirst);
		txtFirst.setColumns(10);
		
		JLabel lblinstruction = new JLabel("Enter Details to Login");
		lblinstruction.setBounds(33, 21, 343, 14);
		contentPanel.add(lblinstruction);
		
		JLabel lblNewLabel_1 = new JLabel("Last Name");
		lblNewLabel_1.setBounds(33, 154, 107, 16);
		contentPanel.add(lblNewLabel_1);
		
		txtLast = new JTextField();
		txtLast.setBounds(262, 149, 107, 26);
		contentPanel.add(txtLast);
		txtLast.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("(Please make sure you login detail stay the same with your last log in)");
		lblNewLabel_2.setForeground(Color.BLUE);
		lblNewLabel_2.setBounds(33, 35, 413, 14);
		contentPanel.add(lblNewLabel_2);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {

				});
				okButton.addActionListener(new ActionListener() {
					
					//make sure volunteer login's information match in MYSQL, if their new typed information are match with database, they can log in
					
					public void actionPerformed(ActionEvent arg0) {
						try {
							Class.forName("com.mysql.jdbc.Driver");
							Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/youtube","root","1996510MengMeng@");
							Statement stmt=con.createStatement();
							String sql="Select * from Volunteer where id='"+txtID.getText().toString()+"'and first_Name='"+txtFirst.getText()+"'and last_Name='"+txtLast.getText()+"'";
							ResultSet rs=stmt.executeQuery(sql);
							if(rs.next()) {
								VolunteerLogin.volunteer = new Volunteer(Integer.valueOf(txtID.getText()), txtFirst.getText(), txtLast.getText());
								createvote();
								close();
							} 
							else {
								JOptionPane.showMessageDialog(null, "Login fail");
								txtFirst.setText(null);
								txtLast.setText(null);
								txtID.setText(null);		
							}
							rs.close();
							stmt.close();
							con.close();
					}catch(Exception e) {System.out.print(e);}
					
					}
				});
				
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			
			{cancelButton = new JButton("Cancel");
				
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						close()	;
					}
				});
				
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}


//	public Volunteer showDialog() {
//		setVisible(true);
//		return volunteer;
//	}
	
	private void createvote() {

		ManageVotes gui = new ManageVotes(this.votes);
		     gui.pack();
		     gui.setVisible(true);
	}

	public void close() {
		this.setVisible(false);
		this.dispose();
	}
}
