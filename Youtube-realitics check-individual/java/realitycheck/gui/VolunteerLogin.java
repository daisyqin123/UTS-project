package realitycheck.gui;

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

import realitycheck.model.Volunteer;
import realitycheck.repo.VolunteerRepo;
import realitycheck.service.*;
import realitycheck.model.Category;

public class VolunteerLogin extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JButton cancelButton;
	public static Volunteer volunteer;
	private JTextField txtID;
	private JTextField txtFirst;
	private JTextField txtLast;
	public	VolunteerLogin volunteerLogin;
	private static CategoryService categoryService;
	private static VideoService videoService;
	private static VoteCalculate voteCalculate;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VolunteerLogin dialog = new VolunteerLogin(null,null,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VolunteerLogin(VoteCalculate voteCalculate,VideoService videoService,CategoryService categoryService) {
		this.voteCalculate = voteCalculate;
		this.videoService = videoService;
		this.categoryService = categoryService;
//		this.volunteerLogin = volunteerLogin;
		
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
		lblinstruction.setBounds(33, 30, 343, 14);
		contentPanel.add(lblinstruction);
		
		JLabel lblNewLabel_1 = new JLabel("Last Name");
		lblNewLabel_1.setBounds(33, 154, 107, 16);
		contentPanel.add(lblNewLabel_1);
		
		txtLast = new JTextField();
		txtLast.setBounds(262, 149, 107, 26);
		contentPanel.add(txtLast);
		txtLast.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {

//				    @Override
//				    public void mouseClicked(MouseEvent e) {
//				       //any code here will be executed whenever the user clicks on the vote button
//				    	System.out.println("Volunteer details enter successful!" );
//				    	JOptionPane.showMessageDialog(null, "Volunteer details enter successful!");
//				    }
				});
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							Class.forName("com.mysql.jdbc.Driver");
							Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/realityCheck","root","123456Alo");
							Statement stmt=con.createStatement();
							String sql="Select * from Volunteer where id='"+txtID.getText().toString()+"'and first_Name='"+txtFirst.getText()+"'and last_Name='"+txtLast.getText()+"'";
							ResultSet rs=stmt.executeQuery(sql);
							if(rs.next()) {
								VolunteerLogin.volunteer = new Volunteer(Integer.valueOf(txtID.getText()), txtFirst.getText(), txtLast.getText());
								CategoryList info = new CategoryList(videoService, categoryService,voteCalculate);
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
//							
//						String first = txtFirst.getText();
//						String last = txtLast.getText();
//	/**/					int id = Integer.parseInt(txtID.getText());
					
						
//						if(first.contains("YanMan")&&last.contains("Lo")&&id.contains("1304")) {
//							txtFirst.setText(null);
//							txtLast.setText(null);
//							txtID.setText(null);
//							System.out.println("Success");
//							CategoryList info = new CategoryList(videoService, categoryService,voteCalculate);
//						
//						}
//						else {
//							
//							JOptionPane.showMessageDialog(null,"Invalid Login", "Login Error",JOptionPane.ERROR_MESSAGE);
//							txtFirst.setText(null);
//							txtLast.setText(null);
//							txtID.setText(null);
//							
//							
//						}
					
					}
				});
				
				okButton.setActionCommand("OK");
				
				
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				
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
//	public boolean inputComplete() {
//		if (txtFirst.getText().length() == 0) {
//			return false;
//		}
//		if (txtLast.getText().length() == 0) {
//			return false;
//		}
//		if (txtID.getText().length() == 0) {
//			return false;
//		}
//		return true;
//	}

	public Volunteer showDialog() {
		setVisible(true);
		return volunteer;
	}

	public void close() {
		this.setVisible(false);
		this.dispose();
	}
}
