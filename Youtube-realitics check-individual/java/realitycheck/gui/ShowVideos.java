package realitycheck.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import realitycheck.model.InWhichQueue;
import realitycheck.model.Video;
import realitycheck.repo.VideoRepo;
import realitycheck.service.CategoryService;
import realitycheck.service.VideoService;
import realitycheck.service.VoteCalculate;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;

import javax.swing.JScrollPane;

public class ShowVideos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	

	private VideoTableModel model ;
	private static VideoService videoService;
	private static VoteCalculate voteCalculate;
	private static VolunteerLogin volunteerLogin;
	private Video selectedVideo ;
	private JPanel contentPane;
	private JTable tblVideos;
	private JButton btnVoteVideo;
	private JButton cancelButton;
	private String categoryName;

	public static void main(String[] args) {
		try {
			ShowVideos dialog = new ShowVideos(null,null,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 */
	public ShowVideos(VideoService videoService,VoteCalculate voteCalculate,String categoryName) {
		this.videoService = videoService;
		this.voteCalculate = voteCalculate;
		
		this.model = new VideoTableModel(categoryName) ;
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Videos");
		
		
		JScrollPane scrollPane = new JScrollPane();
		
		btnVoteVideo = new JButton("VoteVideo");
		btnVoteVideo.setEnabled(false);
		btnVoteVideo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				voteVideo(selectedVideo);
			}
		});
		
		cancelButton = new JButton("Cancel");
		cancelButton.setEnabled(true);
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				close();
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnVoteVideo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)))
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
						.addComponent(btnVoteVideo)
						.addComponent(cancelButton)))
		);
		
		tblVideos = new JTable(model);
		tblVideos.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {

	        	int row = tblVideos.getSelectedRow();
	        	
	        	if (row >= 0)
	        		selectedVideo = model.getVideoAtRow(row) ;
	        	else
	        		selectedVideo = null ;
	        	
	        	btnVoteVideo.setEnabled(selectedVideo != null);
	        }
	    });
		scrollPane.setViewportView(tblVideos);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void close() {
		 this.contentPane.setVisible(false);
		    this.dispose();
	}
	
	private void voteVideo(Video video) {
/**/		VoteVideo dlg = new VoteVideo(video, videoService, voteCalculate,volunteerLogin) ;
	    Video voteVideos = dlg.showDialog();

	    if (voteVideos != null) {

	        //save the student to the repository
//	        videos.save(voteVideos) ;

	        //update the table model, which will cause this new student to be shown in the table
	        model.refreshData();
	    }
	}
	
	private class VideoTableModel extends AbstractTableModel {
		
	    private String[] columnNames = {"Video ID", "Name","Queue","Votes","updateDate"} ;
	    
	    private List<Video> rows ;
	    private String categoryName;
	    
	    
	    public VideoTableModel(String category) {
	    	this.categoryName = category;
	    	refreshData();
	    }

	    public void refreshData() {
	    	this.rows = new ArrayList<Video>();
	    	
	    	for (Video video: videoService.getVideosByCategory(categoryName)) {
	    		this.rows.add(video) ;
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
	    	
	    	Video video = rows.get(row) ;
	    	
	    	switch (col) {
	    		case 0: return video.getId();
	    		case 1: return video.getName() ;
//	    		case 2: return video.getcategoryId() ;
	    		case 2: return video.getinWhichQueue() ;
	    		case 3: return video.getnumberOfVote() ;
	    		case 4: return video.getupdateDate() ;
	    	}
	    	
	    	return null ;
	    	
	    }

	    public Class getColumnClass(int col) {
	    	switch (col) {
	    		case 0: return Integer.class;
	    		case 1: return String.class ;
//	    		case 2: return Integer.class ;
	    		case 2: return InWhichQueue.class ;
	    		case 3: return Integer.class ;
	    		case 4: return Date.class ;
	    	}
	    	
	    	return null ;
    	} 
	    
	    public Video getVideoAtRow(int row) {
	    	return rows.get(row);
	    }
	    
	}
}
