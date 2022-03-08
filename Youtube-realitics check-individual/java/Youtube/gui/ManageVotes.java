package Youtube.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import Youtube.model.Vote;
import Youtube.repo.VoteRepo;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;

public class ManageVotes extends JFrame {
	
	private VoteRepo votes;
	private VoteTableModel model ;
	
	private Vote selectedVote ;

	private JPanel contentPane;
	private JTable tblVotes;
	private JButton btnCancelVote;
	private JButton btnVote;


	
	/**
	 * Create the frame.
	 */
	public ManageVotes(VoteRepo votes) {
		
		this.votes = votes ;
		this.model = new VoteTableModel() ;
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Votes");
		
		btnVote = new JButton("Vote");
		btnVote.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CreateVote();
				System.out.println("dosomething");
			}

			
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		btnCancelVote = new JButton("Cancel Vote");
		btnCancelVote.setEnabled(false);
		btnCancelVote.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cancelVote(selectedVote);
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
						.addComponent(lblNewLabel)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btnCancelVote)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnVote, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(13)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnVote)
						.addComponent(btnCancelVote)))
		);
		
		tblVotes = new JTable(model);
		tblVotes.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {

	        	int row = tblVotes.getSelectedRow();
	        	
	        	if (row >= 0)
	        		selectedVote = model.getVoteAtRow(row) ;
	        	else
	        		selectedVote = null ;
	        	
	        	btnCancelVote.setEnabled(selectedVote != null);
	        }
	    });
		scrollPane.setViewportView(tblVotes);
		contentPane.setLayout(gl_contentPane);
	}
	
	

	private void CreateVote() {

	    CreateVote dlg = new CreateVote() ;
	    Vote createdVote = dlg.showDialog();

	    if (createdVote != null) {

	        //save the student to the repository
	        votes.save(createdVote) ;

	        //update the table model, which will cause this new student to be shown in the table
	        model.refreshData();
	        setVisible(true);
	    }
	}
	
	
	
	private void cancelVote(Vote vote) {
		//TODO (in Step 3)
	}
	
	private class VoteTableModel extends AbstractTableModel {
		
	    private String[] columnNames = {"Vote ID", "Nominated Channel Name", "Volunteer Name"} ;
	    
	    private List<Vote> rows ;
	    
	    public VoteTableModel() {
	    	refreshData();
	    }

	    public void refreshData() {
	    	this.rows = new ArrayList<Vote>();
	    	
	    	for (Vote vote: votes.findAll()) {
	    		this.rows.add(vote) ;
	    	}
	    }
	    
	    public int getColumnCount() {
	        return columnNames.length;
	    }

	    public int getRowCount() {
	        return rows.size();
	    }

	    public String getColumnName(int col) {
	        return columnNames[col];
	    }

	    public Object getValueAt(int row, int col) {
	    	
	    	Vote vote = rows.get(row) ;
	    	
	    	switch (col) {
	    		case 0: return vote.getId();
	    		case 1: return vote.getNominatedchannelName() ;
	    		case 2: return vote.getVolunteerName() ;
	    	}
	    	
	    	return null ;
	    	
	    }

	    public Class getColumnClass(int col) {
	    	switch (col) {
	    		case 0: return Integer.class;
	    		case 1: return String.class ;
	    		case 2: return String.class ;
	    	}
	    	
	    	return null ;
    	} 
	    
	    public Vote getVoteAtRow(int row) {
	    	return rows.get(row);
	    }
	}
}
