
//from the admin officer perspective, so, volunteer can be added and edit by admin officer



package Youtube.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

import Youtube.model.Volunteer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.SystemColor;

public class AddVolunteer extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JButton cancelButton;
	private Volunteer volunteer;
	private JTextField txtVolunteerID;
	private JTextField txtVolunteerFirstName;
	private JTextField txtVolunteerLastName;
	private JLabel lblVolunteerID2;
	private JLabel lblVolunteerFirstName2;
	private JLabel lblVolunteerLastName2;
	private JLabel lblVolunteerLastName2_1;
	private JLabel lblVolunteerFirstName2_1;
	private JLabel lblVolunteerID2_1;
	private JLabel lblVolunteerLastName;
	private JLabel lblinstruction;
	private JLabel lblVolunteerFirstName;
	private JLabel lblVolunteerID;
	
	
	
	
	//check id, volunteer name, nominated channel name can not empty before click ok
private boolean inputComplete() {
	
	
	if (txtVolunteerID.getText().isEmpty() || !txtVolunteerID.isValid())
		return false ;
	
	if (txtVolunteerFirstName.getText().isEmpty())
		return false ;
	
	if (txtVolunteerLastName.getText().isEmpty())
		return false ;
	
	return true ;
}
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddVolunteer dialog = new AddVolunteer(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddVolunteer(Volunteer volunteer) {
		this.volunteer = volunteer ;
		this.setModal(true);
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		//Prevent non-number
		txtVolunteerID = new JFormattedTextField(new DecimalFormat("#####"));
		txtVolunteerID.setBounds(264, 82, 96, 20);
		txtVolunteerID.addKeyListener(new KeyAdapter() {
		
			
			//there are instruction show ID can not be blank. If volunteer enter their id, this instruction will disappear
			@Override
			public void keyPressed(KeyEvent e) {
				okButton.setEnabled(inputComplete());
				if (txtVolunteerID.getText().isEmpty())
				{	lblVolunteerID2. setVisible(true);}
				else
				{	lblVolunteerID2. setVisible(false);}	
					
				
				
			}
		});
		contentPanel.add(txtVolunteerID);
		txtVolunteerID.setColumns(10);
		
		
			lblVolunteerID = new JLabel("Volunterer ID");
			lblVolunteerID.setBounds(33, 85, 128, 14);
			contentPanel.add(lblVolunteerID);
			{
		lblVolunteerFirstName = new JLabel("Volunteer First Name");
		lblVolunteerFirstName.setBounds(33, 140, 128, 14);
		contentPanel.add(lblVolunteerFirstName);
			}
			
			{
		txtVolunteerFirstName = new JTextField();
		txtVolunteerFirstName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				okButton.setEnabled(inputComplete());
				if (txtVolunteerFirstName.getText().isEmpty())
				{	lblVolunteerFirstName2. setVisible(true);}
				else
				{	lblVolunteerFirstName2. setVisible(false);}
			}
		});
		txtVolunteerFirstName.setBounds(264, 150, 96, 20);
		contentPanel.add(txtVolunteerFirstName);
		txtVolunteerFirstName.setColumns(10);
			}
		
			{
		txtVolunteerLastName = new JTextField();
		txtVolunteerLastName.addKeyListener(new KeyAdapter() {
			
			//there are instruction show volunteer last name can not be blank. If volunteer enter their last name, this instruction will disappear
			@Override
			public void keyPressed(KeyEvent e) {
				okButton.setEnabled(inputComplete());
				if (txtVolunteerLastName.getText().isEmpty())
				{	lblVolunteerLastName2. setVisible(true);}
				else
				{	lblVolunteerLastName2. setVisible(false);}
			}
		});
		txtVolunteerLastName.setBounds(264, 199, 96, 20);
		contentPanel.add(txtVolunteerLastName);
		txtVolunteerLastName.setColumns(10);
	}
		
			{
				lblinstruction = new JLabel("As an admin officer, please add new volunteer here");
				lblinstruction.setBounds(33, 30, 343, 14);
				contentPanel.add(lblinstruction);
					}
			{
				lblVolunteerLastName = new JLabel("Volunteer Last Name");
				lblVolunteerLastName.setBounds(36, 202, 156, 14);
				contentPanel.add(lblVolunteerLastName);
				}
			
			{
		lblVolunteerID2 = new JLabel("Volunteer ID cannot be blank");
		lblVolunteerID2.setForeground(SystemColor.textHighlight);
		lblVolunteerID2.setBounds(229, 55, 170, 20);
		contentPanel.add(lblVolunteerID2);
			}
			
			{
		lblVolunteerFirstName2 = new JLabel("please fill in your first name");
		lblVolunteerFirstName2.setForeground(SystemColor.textHighlight);
		lblVolunteerFirstName2.setBounds(226, 104, 200, 50);
		contentPanel.add(lblVolunteerFirstName2);
			}
			{
		lblVolunteerLastName2 = new JLabel("please fill in your last name");
		lblVolunteerLastName2.setForeground(SystemColor.textHighlight);
		lblVolunteerLastName2.setBounds(229, 168, 170, 30);
		contentPanel.add(lblVolunteerLastName2);
			}
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {

				    @Override
				    public void mouseClicked(MouseEvent e) {
				       // if user want to add the same volunteer id, the database already has, there is pop up window shows "Please change your vote ID!"
				    	//i use "list mySQL's volunteer information" in the bottom of this page
				   	String VolunteerID = txtVolunteerID.getText();
				   	ArrayList<String> VolunteerIDList = getVolunteerIDlist();
				    	if(VolunteerIDList.contains(VolunteerID)) {
							{JOptionPane.showMessageDialog(null,"Please change your volunteer ID!");}	
						}
				    	else {
				    		addvolunteer();
					    	JOptionPane.showMessageDialog(null, "This volunteer detail is enter");
					    	close();
					    }
				    	
				    }
				});
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					
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
		
		if (volunteer != null) {
			txtVolunteerID.setText(volunteer.getId().toString());
			txtVolunteerFirstName.setText(volunteer.getFirstName());
			txtVolunteerLastName.setText(volunteer.getLastName());
		}
		
	}
	
	public void addvolunteer() {
	    this.volunteer = new Volunteer(
	        Integer.valueOf(txtVolunteerID.getText()),
	        txtVolunteerFirstName.getText(),
	        txtVolunteerLastName.getText()
	    );
	}
	
	public Volunteer showDialog() {
	    setVisible(true);
	    return volunteer;
	}
	
	public void close() {
	    this.setVisible(false);
	    this.dispose();
	}
	
	//list mySQL's volunteer information
	public ArrayList<String> getVolunteerIDlist()  {
       ArrayList<String> list = new ArrayList<>();
       try {
           Connection con = getConnection();
            java.sql.Statement st = con.createStatement();
            ResultSet result = st.executeQuery("select id from volunteer");
            while (result.next())
            {
                list.add(result.getString(1));
           }

       } catch (Exception exception) {
            exception.printStackTrace();
        }
        return list;
    }
	
	//link my database
	public static Connection getConnection() throws Exception
	   {
	       Class.forName("com.mysql.cj.jdbc.Driver");
	       return DriverManager.getConnection("jdbc:mysql://localhost:3306/youtube", "root", "1996510MengMeng@");
	    }
}
