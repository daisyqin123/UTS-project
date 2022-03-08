package realitycheck.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.Random;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import realitycheck.model.InWhichQueue;
import realitycheck.model.Video;
import realitycheck.service.VideoService;
import realitycheck.service.VoteCalculate;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VoteVideo extends JDialog {
private final JPanel contentPanel = new JPanel();
	
	private Video video ;
	private VideoService videoService;
	private VoteCalculate voteCalculate;
	/**/	private VolunteerLogin volunteerLogin;
	private JLabel txtVideoName;
	private JLabel txtNoOfVote;
	private JButton saveButton;
	private JButton downButton;
//	int count1 = 0;
//	int count2 = 0;
//	int sum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VoteVideo dialog = new VoteVideo(null,null,null,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	/**/public VoteVideo(Video video,VideoService videoService,VoteCalculate voteCalculate,VolunteerLogin volunteerLogin) {
		
		this.video = video ;
		this.videoService = videoService;
		this.voteCalculate = voteCalculate;
		/**/		this.volunteerLogin = volunteerLogin;
		Integer numberOfVote = video.getnumberOfVote();
		String name = video.getName();
		this.setModal(true);
		Random ran = new Random();
//		int x = ran.nextInt(100) + 1301;
		int volunteerId = VolunteerLogin.volunteer.getId();
		
		setBounds(100, 100, 650, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblInstructions = new JLabel("Please choose up or down vote");
		
		Integer videoId = video.getId();
		JLabel lblNoOfVote = new JLabel("Number of UpVotes: ");
		txtNoOfVote = new JLabel();
		txtNoOfVote.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});

		
		
		JLabel lblVideoName = new JLabel("Video");
		
		txtVideoName = new JLabel();
		txtVideoName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});

		if(name!=null) {
			txtVideoName.setText(name);
		}
		
		JButton UpButton = new JButton("Up");
		UpButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
//				count1=1;
				voteCalculate.upVote(volunteerId, videoId);
				video.setnumberOfVote(voteCalculate.countUpVote(videoId));
				if(voteCalculate.countUpVote(videoId)-voteCalculate.countDownVote(videoId)>=100) {
					video.setInWhichQueue(InWhichQueue.ACTIVE);
				}
				videoService.saveVideo(video);
				lblNoOfVote.setText("Number of UpVotes:"+voteCalculate.countUpVote(videoId));
				close();
			}
		});
		
		JLabel lblNewLabel = new JLabel("Number of DownVotes: "+voteCalculate.countDownVote(videoId));
		JButton downButton = new JButton("Down");
		downButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				count2=1;
				voteCalculate.downVote(volunteerId, videoId);
				video.setnumberOfVote(voteCalculate.countUpVote(videoId));
				videoService.saveVideo(video);
				lblNewLabel.setText("Number of DownVotes:"+voteCalculate.countDownVote(videoId));
				close();
			}
		});

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
									.addComponent(lblInstructions)
									.addGroup(gl_contentPanel.createSequentialGroup()
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_contentPanel.createSequentialGroup()
												.addGap(7)
												.addComponent(lblVideoName, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
											.addComponent(lblNoOfVote))
										.addGap(18)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(txtNoOfVote)
											.addComponent(txtVideoName, GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE))))
								.addComponent(UpButton, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(downButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel))
					.addContainerGap(58, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblInstructions)
					.addGap(30)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVideoName)
						.addComponent(txtVideoName))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNoOfVote)
						.addComponent(lblNoOfVote))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel)
					.addGap(65)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(downButton)
						.addComponent(UpButton))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						close();
			
					}
					
				});
//				{
//					saveButton = new JButton("Save");
//					buttonPane.add(saveButton);
//					saveButton.addActionListener(new ActionListener() {
//						public void actionPerformed(ActionEvent e) {
//						}
//					});
//
//					
//					saveButton.setEnabled(true);
//					saveButton.addMouseListener(new MouseAdapter() {
//						@Override
//						public void mouseClicked(MouseEvent e) {
//							int sum = count1 + numberOfVote;
//							System.out.println(sum);
////							createVote(sum);
//							
//						}
//					});
//					saveButton.setActionCommand("Save");
//					getRootPane().setDefaultButton(saveButton);
//				}
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
				
		}
		
		if (video != null) {
			txtNoOfVote.setText(video.getnumberOfVote().toString());
			txtVideoName.setText(video.getName());
		}
	}
	
//	private boolean inputComplete() {
//		
//		if (txtStudentId.getText().isEmpty() || !txtStudentId.isValid())
//			return false ;
//		
//		if (txtFirstName.getText().isEmpty())
//			return false ;
//		
//		if (txtLastName.getText().isEmpty())
//			return false ;
//		
//		return true ;
//	}
	
//	private void createVote(Integer numberOfVote) {
//	    this.video = new Video(
//	        null,
//	        null,
//	        null,
//	        null,
//	        Integer.valueOf(sum),
//	        null
//	       
//	    );
//	}
	
	private void close() {
	    this.setVisible(false);
	    this.dispose();
	}
	
	public Video showDialog() {
	    setVisible(true);
	    return video;
	}
}