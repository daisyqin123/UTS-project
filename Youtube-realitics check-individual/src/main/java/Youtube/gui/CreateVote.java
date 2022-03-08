package Youtube.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import Youtube.App;
import Youtube.model.NominatedChannel;
import Youtube.model.Vote;
import Youtube.repo.NominatedChannelRepo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;

import com.mysql.cj.xdevapi.Statement;

import java.awt.*;
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
import java.util.List;
import java.util.Map;



public class CreateVote extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton voteButton;
	private JButton cancelButton;
	private JTextField txtVoteID;
	private JTextField txtNominatedChannelName;
	private JTextField txtVolunteerName;
	private Vote vote;
	private JLabel lblVoteID2;
	private JLabel lblNominatedchannel2;
	private JLabel lblVolunteername2;
	
	
	
	//check id, volunteer name, nominated channel name can not empty before click vote
private boolean inputComplete() {
	
	
	if (txtVoteID.getText().isEmpty() || !txtVoteID.isValid())
		return false ;
	
	if (txtNominatedChannelName.getText().isEmpty())
		return false ;
	
	if (txtVolunteerName.getText().isEmpty())
		return false ;
	
	return true ;
}
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreateVote dialog = new CreateVote(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateVote(Vote vote) {
		this.vote = vote ;
		this.setModal(true);
		
		setBounds(100, 100, 450, 382);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		//Prevent non-number
		
		txtVoteID = new JFormattedTextField(new DecimalFormat("#####"));
		txtVoteID.setBounds(201, 85, 96, 20);
		txtVoteID.addKeyListener(new KeyAdapter() {
		
			//there are instruction show ID can not be blank. If volunteer enter their vote id, this instruction will disappear
			@Override
			public void keyPressed(KeyEvent e) {
				voteButton.setEnabled(inputComplete());
				if (txtVoteID.getText().isEmpty())
				{	lblVoteID2. setVisible(true);}
				else
				{	lblVoteID2. setVisible(false);}	
					
				
				
			}
		});
		contentPanel.add(txtVoteID);
		txtVoteID.setColumns(10);
		
		JLabel lblVoteID = new JLabel("Vote ID");
		lblVoteID.setBounds(39, 88, 49, 14);
		contentPanel.add(lblVoteID);
		{
			JLabel lblVolunteerName = new JLabel("Volunteer Name");
			lblVolunteerName.setBounds(39, 241, 129, 14);
			contentPanel.add(lblVolunteerName);
		}
		{
			JLabel lblNominatedChannelName = new JLabel("Nominated Channel Name");
			lblNominatedChannelName.setBounds(39, 162, 198, 14);
			contentPanel.add(lblNominatedChannelName);
		}
		{
			txtNominatedChannelName = new JTextField();
			txtNominatedChannelName.addKeyListener(new KeyAdapter() {
				//there are instruction show channel can not be blank. If volunteer enter their nominated channel name, this instruction will disappear
				@Override
				public void keyPressed(KeyEvent e) {
					voteButton.setEnabled(inputComplete());
					if (txtNominatedChannelName.getText().isEmpty())
					{	lblNominatedchannel2. setVisible(true);}
					else
					{	lblNominatedchannel2. setVisible(false);}
				}
			});
			txtNominatedChannelName.setBounds(201, 159, 96, 20);
			
			contentPanel.add(txtNominatedChannelName);
			txtNominatedChannelName.setColumns(10);
		}
		{
			txtVolunteerName = new JTextField();
			txtVolunteerName.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					voteButton.setEnabled(inputComplete());
					
					if (txtVolunteerName.getText().isEmpty() )
					{	lblVolunteername2. setVisible(true);}
					else
					{	lblVolunteername2. setVisible(false);}	
				}
			});
			txtVolunteerName.setBounds(201, 238, 96, 20);
			contentPanel.add(txtVolunteerName);
			txtVolunteerName.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("Please provide your vote details");
			lblNewLabel.setBounds(39, 11, 217, 20);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(lblNewLabel);
		}
		{
			lblVoteID2 = new JLabel("Vote ID can not be empty!");
			lblVoteID2.setForeground(Color.BLUE);
			lblVoteID2.setBounds(201, 43, 164, 31);
			contentPanel.add(lblVoteID2);
		}
		
		{
			lblNominatedchannel2 = new JLabel("Nominated channel can not be empty!");
			lblNominatedchannel2.setForeground(Color.BLUE);
			lblNominatedchannel2.setBounds(198, 128, 320, 20);
			contentPanel.add(lblNominatedchannel2);
		}
		{
			lblVolunteername2 = new JLabel("Volunteer name can not be empty!");
			lblVolunteername2.setForeground(Color.BLUE);
			lblVolunteername2.setBounds(201, 203, 320, 20);
			contentPanel.add(lblVolunteername2);
		}
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				voteButton = new JButton("Vote");
				voteButton.setEnabled(false);
				
				voteButton.addMouseListener(new MouseAdapter() {

				    @Override
				    public void mouseClicked(MouseEvent e) {
				       //code here will be executed whenever the user clicks on the vote button
				    	// if volunteer enter repeat ID or volunteer enter their name that already exist in MYSQL , there will be a pop up window shows'unsuccessful'
				    	//this prevent one volunteer can vote twice
				    	// link MYSQL method in the bottom of this page
				    	String volunteername = txtVolunteerName.getText();
				    	ArrayList<String> VolunteernameList = getVolunteernamelist();
				    	String VoteID = txtVoteID.getText();
						ArrayList<String> VoteIDList = getVoteIDlist();	
					
						if((VoteIDList.contains(VoteID) ||(VolunteernameList.contains(volunteername)))) {
							{JOptionPane.showMessageDialog(null,"Please check your vote information!");}	
						}
						else
						{
						
				    	createVote();
				    	printlist ();
				    	JOptionPane.showMessageDialog(null, "Your vote is successful!");
				    	close();
				    }
					
						
						
						
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
		
		if (vote != null) {
			txtVoteID.setText(vote.getId().toString());
			txtNominatedChannelName.setText(vote.getNominatedchannelName());
			txtVolunteerName.setText(vote.getVolunteerName());
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
	
	
	
	//list mySQL's vote information
	public ArrayList<String> getVolunteernamelist()
    {
        ArrayList<String> list = new ArrayList<>();
        try {
            Connection con = getConnection();
            java.sql.Statement st = con.createStatement();
            ResultSet result = st.executeQuery("select Volunteer_name from vote");
            while (result.next())
            {
                list.add(result.getString(1));
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return list;
    }
	
	
	public ArrayList<String> getVoteIDlist()
    {
        ArrayList<String> list = new ArrayList<>();
        try {
            Connection con = getConnection();
            java.sql.Statement st = con.createStatement();
            ResultSet result = st.executeQuery("select id from vote");
            while (result.next())
            {
                list.add(result.getString(1));
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return list;
    }
	
	
	//method that meet threshold
	
	
	//find meet threshold list
	public ArrayList<String> getmeetThresholdchannellist()
    {
        ArrayList<String> list = new ArrayList<>();
        
        
        //list meet threshold's nominated channel from my SQL
        try {
            Connection con = getConnection();
            java.sql.Statement st = con.createStatement();
            ResultSet result = st.executeQuery("select name from nominated_channel where num_of_vote >= 5");
            while (result.next())
            {
                list.add(result.getString(1));
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return list;
    }
	
	//print threshold (print in console)
	public void printlist () {
		ArrayList<String> meetThresholdchannellist = getmeetThresholdchannellist();
		System.out.println("This nominated channel name"+ meetThresholdchannellist + "will be informed to experts!");
		
	}
	
	
	 //link to my database
	  public static Connection getConnection() throws Exception
	    {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        return DriverManager.getConnection("jdbc:mysql://localhost:3306/youtube", "root", "1996510MengMeng@");
	    }
	  
	  
	


}
