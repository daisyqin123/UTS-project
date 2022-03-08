package Youtube.gui;

import Youtube.model.Vote;

import Youtube.repo.VoteRepo;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ManageVotes extends JFrame {

    private VoteRepo votes;
    private VoteTableModel model;

    private Vote selectedVote;

    private JPanel contentPane;
    private JTable tblVotes;
    private JButton btnVote;


    /**
     * Create the frame.
     */
    public ManageVotes(VoteRepo votes) {

        this.votes = votes;
        this.model = new VoteTableModel();


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
                createVote();

            }


        });

        JScrollPane scrollPane = new JScrollPane();

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
        				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
        				.addComponent(lblNewLabel)
        				.addComponent(btnVote, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(lblNewLabel)
        			.addGap(13)
        			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(btnVote))
        );

        tblVotes = new JTable(model);
       
        scrollPane.setViewportView(tblVotes);
        contentPane.setLayout(gl_contentPane);
    }


    private void createVote() {

        CreateVote dlg = new CreateVote(null);
        Vote createdVote = dlg.showDialog();

        if (createdVote != null) {

            //save the vote to the repository
            votes.save(createdVote);

            //update the table model, which will cause this new vote to be shown in the table

            model.refreshdata();
			model.fireTableDataChanged();
            

        }
    }

    private class VoteTableModel extends DefaultTableModel {

        private String[] columnNames = {"Vote ID", "Nominated Channel Name", "Volunteer Name"};

        private List<Vote> rows;

        public VoteTableModel() {
            refreshdata();
        }

        public void refreshdata() {
            this.rows = new ArrayList<Vote>();

			this.setRowCount(0);
            for (Vote vote : votes.findAll()) {
                this.rows.add(vote);
                super.addRow(new Object[]{vote.getId(), vote.getNominatedchannelName(), vote.getVolunteerName()});
            }
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public int getRowCount() {
            return super.getRowCount();
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        @Override
        public Object getValueAt(int row, int col) {

            if (row >= rows.size()) {
        		return null;
            }
            Vote vote = rows.get(row);

            switch (col) {
                case 0:
                    return vote.getId();
                case 1:
                    return vote.getNominatedchannelName();
                case 2:
                    return vote.getVolunteerName();
            }

            return null;

        }

        @Override
        public Class getColumnClass(int col) {
            switch (col) {
                case 0:
                    return Integer.class;
                case 1:
                    return String.class;
                case 2:
                    return String.class;
            }

            return null;
        }

        public Vote getVoteAtRow(int row) {
            return rows.get(row);
        }
    }
}
