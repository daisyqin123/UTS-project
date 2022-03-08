package Youtube.gui;

import Youtube.model.NominatedChannel;
import Youtube.model.Vote;
import Youtube.repo.NominatedChannelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class CreateVote extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton voteButton;
	private JButton cancelButton;
	private JTextField txtVoteID;
	private JTextField txtNominatedChannelName;
	private JTextField txtVolunteerName;
	private JTextField txtCategory1;
	private JTextField txtCategory2;
	private JTextField txtCategory3;
	private Vote vote;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreateVote dialog = new CreateVote();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateVote() {
		setBounds(100, 100, 450, 382);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		txtVoteID = new JTextField();
		txtVoteID.setBounds(319, 39, 96, 20);
		contentPanel.add(txtVoteID);
		txtVoteID.setColumns(10);
		
		JLabel lblVoteID = new JLabel("Vote ID");
		lblVoteID.setBounds(51, 42, 49, 14);
		contentPanel.add(lblVoteID);
		{
			JLabel lblVolunteerName = new JLabel("Volunteer Name");
			lblVolunteerName.setBounds(51, 250, 129, 14);
			contentPanel.add(lblVolunteerName);
		}
		{
			JLabel lblNominatedChannelName = new JLabel("Nominated Channel Name");
			lblNominatedChannelName.setBounds(43, 80, 256, 14);
			contentPanel.add(lblNominatedChannelName);
		}
		{
			txtNominatedChannelName = new JTextField();
			txtNominatedChannelName.setBounds(319, 70, 96, 20);
			contentPanel.add(txtNominatedChannelName);
			txtNominatedChannelName.setColumns(10);
		}
		{
			txtVolunteerName = new JTextField();
			txtVolunteerName.setBounds(319, 247, 96, 20);
			contentPanel.add(txtVolunteerName);
			txtVolunteerName.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("Please provide your vote details");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel.setBounds(10, 11, 289, 20);
			contentPanel.add(lblNewLabel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				voteButton = new JButton("Vote");
				voteButton.addMouseListener(new MouseAdapter() {

				    @Override
				    public void mouseClicked(MouseEvent e) {
				       //any code here will be executed whenever the user clicks on the vote button
						createVote();
				    	System.out.println("Your vote is successful!" );
				    	JOptionPane.showMessageDialog(null, "Your vote is successful!");
				    }
				});
				
				
			
				voteButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

					}
				});
				voteButton.setActionCommand("vote");
				buttonPane.add(voteButton);
				getRootPane().setDefaultButton(voteButton);
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
	
	public void createVote() {
	    this.vote = new Vote(
	        Integer.valueOf(txtVoteID.getText()),
	        txtNominatedChannelName.getText(),
	        txtVolunteerName.getText()
	    );
	}
	
	public Vote showDialog() {
	    setVisible(true);
	    return vote;
	}
	
	public void close() {
	    this.setVisible(false);
	    this.dispose();
	}
}
