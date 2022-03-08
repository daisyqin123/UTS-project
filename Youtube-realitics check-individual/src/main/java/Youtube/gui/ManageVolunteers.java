package Youtube.gui;

import java.util.ArrayList;

import java.util.List;

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

import Youtube.model.Volunteer;
import Youtube.model.Vote;
import Youtube.repo.VolunteerRepo;

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

public class ManageVolunteers extends JFrame {
	
	private VolunteerRepo volunteers ;
	private VolunteerTableModel model ;
	
	private Volunteer selectedVolunteer ;

	private JPanel contentPane;
	private JTable tblVolunteers;
	private JButton btnEditVolunteer;
	private JButton btnAddVolunteer;


	
	/**
	 * Create the frame.
	 */
	public ManageVolunteers(VolunteerRepo volunteers) {
		
		this.volunteers = volunteers ;
		this.model = new VolunteerTableModel() ;
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Volunteers");
		
		btnAddVolunteer = new JButton("Add Volunteer");
		btnAddVolunteer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addVolunteer();
				
			}

			
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		btnEditVolunteer = new JButton("Edit Volunteer");
		btnEditVolunteer.setEnabled(false);
		btnEditVolunteer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				editVolunteer(selectedVolunteer);
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
							.addComponent(btnEditVolunteer)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAddVolunteer, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)))
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
						.addComponent(btnAddVolunteer)
						.addComponent(btnEditVolunteer)))
		);
		
		tblVolunteers = new JTable(model);
		tblVolunteers.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {

	        	int row = tblVolunteers.getSelectedRow();
	        	
	        	if (row >= 0)
	        		selectedVolunteer = model.getVolunteerAtRow(row) ;
	        	else
	        		selectedVolunteer = null ;
	        	 btnEditVolunteer.setEnabled(selectedVolunteer != null);
          
	        }
	        	
	    });
		scrollPane.setViewportView(tblVolunteers);
		contentPane.setLayout(gl_contentPane);
	}
	
	

	private void addVolunteer() {

	    AddVolunteer dlg = new AddVolunteer(null) ;
	    Volunteer createdVolunteer = dlg.showDialog();

	    if (createdVolunteer != null) {

	        //save the student to the repository
	    	volunteers.save(createdVolunteer) ;

	        //update the table model, which will cause this new student to be shown in the table
	        model.refreshdata();
	        model.fireTableDataChanged();
	    }
	}
	
	
	
	private void editVolunteer(Volunteer volunteer) {
		//TODO (in Step 3)
		EditVolunteer dlg = new EditVolunteer(volunteer) ;
	    Volunteer editedVolunteer = dlg.showDialog();


	        if (editedVolunteer != null) {

	            //save the vote to the repository
	            volunteers.save(editedVolunteer);

	            //update the table model, which will cause this new vote to be shown in the table
	            model.refreshdata();
	        }
	}
	
	private class VolunteerTableModel extends DefaultTableModel {
		
	    private String[] columnNames = {"Volunteer ID", "First Name", "Last Name"} ;
	    
	    private List<Volunteer> rows ;
	    
	    public VolunteerTableModel() {
	    	refreshdata();
	    }

	    public void refreshdata() {
	    	this.rows = new ArrayList<Volunteer>();
	    	
	    	for (Volunteer volunteer: volunteers.findAll()) {
	    		this.rows.add(volunteer) ;
	    		super.addRow(new Object[]{volunteer.getId(), volunteer.getFirstName(), volunteer.getLastName()});
	            
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
	    	Volunteer volunteer = rows.get(row) ;
	    	
	    	switch (col) {
	    		case 0: return volunteer.getId();
	    		case 1: return volunteer.getFirstName() ;
	    		case 2: return volunteer.getLastName() ;
	    	}
	    	
	    	return null ;
	    	
	    }

	    @Override
	    public Class getColumnClass(int col) {
	    	switch (col) {
	    		case 0: return Integer.class;
	    		case 1: return String.class ;
	    		case 2: return String.class ;
	    	}
	    	
	    	return null ;
    	} 
	    
	    public Volunteer getVolunteerAtRow(int row) {
	    	return rows.get(row);
	    }
	}
}
