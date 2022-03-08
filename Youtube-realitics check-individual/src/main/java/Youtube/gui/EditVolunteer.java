
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

public class EditVolunteer extends JDialog {

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
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditVolunteer dialog = new EditVolunteer(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditVolunteer(Volunteer volunteer) {
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
		txtVolunteerFirstName.setBounds(264, 150, 96, 20);
		contentPanel.add(txtVolunteerFirstName);
		txtVolunteerFirstName.setColumns(10);
			}
		
			{
		txtVolunteerLastName = new JTextField();
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
		lblVolunteerID2 = new JLabel("Please do not change volunteer ID!");
		lblVolunteerID2.setForeground(SystemColor.textHighlight);
		lblVolunteerID2.setBounds(229, 55, 170, 20);
		contentPanel.add(lblVolunteerID2);
			}
			
			{
		lblVolunteerFirstName2 = new JLabel("please change volunteer first name");
		lblVolunteerFirstName2.setForeground(SystemColor.textHighlight);
		lblVolunteerFirstName2.setBounds(226, 104, 200, 50);
		contentPanel.add(lblVolunteerFirstName2);
			}
			{
		lblVolunteerLastName2 = new JLabel("please change volunteer last name");
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
				    		editvolunteer();
					    	JOptionPane.showMessageDialog(null, "This volunteer detail is changed");
					    	close();
					  
				    	
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
	
	public void editvolunteer() {
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
