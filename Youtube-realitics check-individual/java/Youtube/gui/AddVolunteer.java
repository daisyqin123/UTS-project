package Youtube.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

import Youtube.model.Volunteer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddVolunteer extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JButton cancelButton;
	private Volunteer volunteer;
	private JTextField txtVolunteerID;
	private JTextField txtVolunteerName;
	private JTextField txtVolunteerPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddVolunteer dialog = new AddVolunteer();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddVolunteer() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblVolunteerID = new JLabel("Volunterer ID");
			lblVolunteerID.setBounds(33, 85, 128, 14);
			contentPanel.add(lblVolunteerID);
		}
		
		txtVolunteerID = new JTextField();
		txtVolunteerID.setBounds(264, 82, 96, 20);
		contentPanel.add(txtVolunteerID);
		txtVolunteerID.setColumns(10);
		
		JLabel lblVolunteerName = new JLabel("Volunteer Name");
		lblVolunteerName.setBounds(33, 140, 128, 14);
		contentPanel.add(lblVolunteerName);
		
		txtVolunteerName = new JTextField();
		txtVolunteerName.setBounds(264, 137, 96, 20);
		contentPanel.add(txtVolunteerName);
		txtVolunteerName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Volunteer Password");
		lblNewLabel.setBounds(33, 190, 156, 14);
		contentPanel.add(lblNewLabel);
		
		txtVolunteerPassword = new JTextField();
		txtVolunteerPassword.setBounds(264, 190, 96, 20);
		contentPanel.add(txtVolunteerPassword);
		txtVolunteerPassword.setColumns(10);
		
		JLabel lblinstruction = new JLabel("Please provide the details for new volunteer");
		lblinstruction.setBounds(33, 30, 343, 14);
		contentPanel.add(lblinstruction);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {

				    @Override
				    public void mouseClicked(MouseEvent e) {
				       //any code here will be executed whenever the user clicks on the vote button
				    	System.out.println("Volunteer details enter successful!" );
				    	JOptionPane.showMessageDialog(null, "Volunteer details enter successful!");
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
	}
	public void addvolunteer() {
	    this.volunteer = new Volunteer(
	        Integer.valueOf(txtVolunteerID.getText()),
	        txtVolunteerName.getText(),
	        txtVolunteerPassword.getText()
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
}
