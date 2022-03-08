package realitycheck;

import java.sql.Date;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import realitycheck.model.InWhichQueue;
import realitycheck.model.Video;
import realitycheck.model.Vote;
import realitycheck.gui.CategoryList;
import realitycheck.gui.ShowVideos;
import realitycheck.gui.VolunteerLogin;
import realitycheck.model.CategorisedVideo;
import realitycheck.model.Category;
import realitycheck.model.Volunteer;
import realitycheck.repo.CategorisedVideoRepo;
import realitycheck.repo.CategoryRepo;
import realitycheck.repo.VideoRepo;
import realitycheck.repo.VolunteerRepo;
import realitycheck.repo.VoteRepo;
import realitycheck.service.CategoryService;
import realitycheck.service.VideoService;
import realitycheck.service.VoteCalculate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@SpringBootApplication
public class AppCheck implements InitializingBean {

	@Autowired
	private VideoRepo videos;
	
	@Autowired
	private CategoryRepo categories;
	
	
	@Autowired
	private CategorisedVideoRepo categorisedVideos;
	
	@Autowired
	private VolunteerRepo volunteers;

	@Autowired
	private VoteRepo votes;
	
	@Autowired
	private VoteCalculate voteCalculate;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private VideoService videoService;


	private void setupvideos() {
		
		System.out.println("Showing videos") ;
		
		Set<Video> set = new HashSet<>();
		Video video = new Video(9872,"Scientist laughs at climate change skeptics",1, InWhichQueue.PENDING,0,new Date(121,9,10)) ;
        videos.save(video);
        set.add(video);
        video = new Video(9877,"Why Climate Change Denial Still Exists In The U.S.",1, InWhichQueue.PENDING,0,new Date(121,9,10)) ;
        videos.save(video);
        set.add(video);
        Category category = new Category(1, "climate denial") ;
        category.setCatevideos(set);
        categories.save(category);
        

        set = new HashSet<>();
        video = new Video(9875,"Racial prejudice prevails in US town",5, InWhichQueue.PENDING,0,new Date(121,9,1)) ;
        videos.save(video) ;
        set.add(video);
        category = new Category(5, "racial prejudice") ;
        category.setCatevideos(set);
        categories.save(category);

        set = new HashSet<>();
        video = new Video(9873,"Religious exemptions from COVID-19 vaccine mandates grow",4,InWhichQueue.PENDING,0,new Date(121,9,13)) ;
        videos.save(video) ;
        set.add(video);
        video = new Video(9874,"How religious leaders are keeping the faith during COVID-19",4, InWhichQueue.PENDING,0,new Date(121,9,13)) ;
        videos.save(video) ;        
        set.add(video);
        category = new Category(4, "religious extremism") ;
        category.setCatevideos(set);
        categories.save(category);
        
        set = new HashSet<>();
        video = new Video(9873,"Religious exemptions from COVID-19 vaccine mandates grow",2,InWhichQueue.PENDING,97,new Date(121,9,13)) ;
        videos.save(video) ;
        genVotes(video.getId(),97);
        set.add(video);
        video = new Video(9876,"Why UK daily COVID-19 cases are now the highest in the world",2,InWhichQueue.PENDING,98,new Date(121,9,13)) ;
        videos.save(video) ;
        genVotes(video.getId(),98);
        set.add(video);
        video = new Video(9874,"How religious leaders are keeping the faith during COVID-19",2, InWhichQueue.PENDING,0,new Date(121,9,13)) ;
        videos.save(video) ;        
        set.add(video);
        category = new Category(2, "covid19") ;
        category.setCatevideos(set);
        categories.save(category);
        
        

		System.out.println("Showing categories") ;
		


        category = new Category(3, "political extremism") ;
        categories.save(category);

        
	}
	
	private void genVotes(Integer videoId, int numberOfVotes) {
		System.out.println("start insert votes for videoId: " + videoId);
		for(int i = 1; i <= numberOfVotes; i++) {
	        Vote curVote = new Vote(1301 + i - 1, videoId, true);
	        votes.save(curVote);
		}
		System.out.println("generated" + numberOfVotes + " votes for videoId: " + videoId);
	}
	
	private void setupvolunteers() {
		System.out.println("Showing volunteers") ;
		
        Volunteer volunteer = new Volunteer(1301, "Thamas", "Kennedy") ;
        volunteers.save(volunteer);
        volunteer = new Volunteer(1302, "Jennifer", "Martin") ;
        volunteers.save(volunteer);
        volunteer = new Volunteer(1303, "Patrina", "Magee") ;
        volunteers.save(volunteer);
        
	}



	 private void printVolunteer() {

	        System.out.println("There are " + volunteers.count() + " volunteers: ");

	        for (Volunteer volunteer: volunteers.findAll()) {
	        	
	            System.out.println("  " + volunteer) ;
	        }
	    }
	 private void printCategorisedVideo() {

	        System.out.println("There are " + categorisedVideos.count() + " categorisedVideos: ");

	        for (CategorisedVideo categorisedVideo: categorisedVideos.findAll()) {
	            System.out.println("  " + categorisedVideo) ;
	        }
	    }

	@Override
	public void afterPropertiesSet() throws Exception {
		setupvideos();
		setupvolunteers();
//		setupcategorisedVideo();
		printVolunteer();
		printCategorisedVideo();
		List<Video> var = videoService.getVideosByCategory("covid19");
		System.out.println(var.get(0).getName());
//		setupvotes();
//		 VideoList gui = new VideoList(videoService, categoryService,voteCalculate);  //(this.students) ;
//		 VideoTest2 gui = new VideoTest2(this.videos,videoService,voteCalculate); 
		 VolunteerLogin gui = new VolunteerLogin(voteCalculate, videoService,categoryService);
//		 Login gui = new Login();
//	        gui.pack();
	        gui.setVisible(true);
	    
		
	}
    public static void main(String args[]) {
    	
    	SpringApplicationBuilder builder = new SpringApplicationBuilder(AppCheck.class);
        builder.headless(false).run(args);
//        ConfigurableApplicationContext configurableApplicationContext =
//        		SpringApplication.run(AppCheck.class, args);
//        VideoRepo videoRepo = configurableApplicationContext.getBean(VideoRepo.class);
//        CategoryRepo categoryRepo = configurableApplicationContext.getBean(CategoryRepo.class);
//        videoRepo.saveAll(videos);
//        
    }

}
