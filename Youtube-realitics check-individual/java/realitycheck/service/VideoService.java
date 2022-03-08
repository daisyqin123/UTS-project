package realitycheck.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.Comparator;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import realitycheck.gui.CategoryList;
import realitycheck.model.Category;
import realitycheck.model.Video;
import realitycheck.repo.CategoryRepo;
import realitycheck.repo.VideoRepo;

@Service
public class VideoService {

	@Autowired
	private VideoRepo videoRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	
	
	public List<Video> getVideosByCategory(String category) {
//		
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/realityCheck","root","123456Alo");
//			Statement stmt=con.createStatement();
//			String sql="Select * from Video where categoryId='"+txtID.getText().toString()+"'order by numberOfVote DESC";
//			ResultSet rs=stmt.executeQuery(sql);
//			if(rs.next()) {
//				CategoryList info = new CategoryList(videoService, categoryService,voteCalculate);}
//			    
//			else {
//				JOptionPane.showMessageDialog(null, "Login fail");
//				
//				rs.close();
//				stmt.close();
//				con.close();
//			}
//	}catch(Exception e) {System.out.print(e);}
		
		System.out.println("start get video by category");
		Set<Video> videos = categoryRepo.findByName(category).getCatevideos(); /* bug????*/
		List<Video> result = new ArrayList<Video>(videos);
		Comparator<Video> byNumberOfVote = Comparator.comparing(Video::getnumberOfVote,Comparator.reverseOrder());
		Collections.sort(result,byNumberOfVote);
		System.out.println("finish get category");
		System.out.println(result.size());
		return result;
	}
	public void saveVideo(Video video) {
		videoRepo.save(video);
	}
}
