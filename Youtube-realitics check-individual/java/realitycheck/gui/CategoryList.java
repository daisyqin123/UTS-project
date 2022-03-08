package realitycheck.gui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import java.awt.EventQueue;
import java.util.List;
import javax.swing.JFrame;
import realitycheck.model.Category;
import realitycheck.repo.VideoRepo;
import realitycheck.service.CategoryService;
import realitycheck.service.VideoService;
import realitycheck.service.VoteCalculate;

import javax.swing.JList;
import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class CategoryList extends JFrame {

	public JFrame frame;
	private static CategoryService categoryService;
	private static VideoService videoService;
	private static VoteCalculate voteCalculate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

	}

	/**
	 * Create the application.
	 * 
	 * @return
	 */

	public CategoryList(VideoService videoService, CategoryService categoryService, VoteCalculate voteCalculate) {
		getContentPane().setBackground(Color.WHITE);

		this.categoryService = categoryService;
		this.videoService = videoService;
		this.voteCalculate = voteCalculate;
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		List<Category> categoryList = categoryService.getCategories();
		String[] stringArray = new String[categoryList.size()];
		for (int i = 0; i < categoryList.size(); i++) {
			System.out.println(categoryList.get(i).getName());
			stringArray[i] = categoryList.get(i).getName();
		}

		JList<String> categoryListForUI = new JList<String>();
		categoryListForUI.setBounds(100, 100, 200, 200);
		categoryListForUI.setListData(stringArray);
		Container container = this.getContentPane();
		categoryListForUI.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {

				if (e.getValueIsAdjusting() == false) {
					ShowVideos info = new ShowVideos(videoService, voteCalculate, categoryListForUI.getSelectedValue());
					info.setVisible(true);
					System.out.println(categoryListForUI.getSelectedValue());
					System.out.println(videoService.getVideosByCategory(categoryListForUI.getSelectedValue()));
				}

			};

		});

		this.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		container.add(panel);
		panel.setBounds(0, 0, 458, 292);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Category");
		lblNewLabel.setBounds(25, 21, 61, 16);
		panel.add(lblNewLabel);
		panel.add(categoryListForUI);

//		JComboBox comboBox = new JComboBox();
//		comboBox.setBounds(28, 66, 52, 27);
//		panel.add(comboBox);
		frame.getContentPane().add(panel);

		frame.setVisible(true);

	}

}
