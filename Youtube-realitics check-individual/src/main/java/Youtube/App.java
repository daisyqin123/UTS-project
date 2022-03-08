package Youtube;


import Youtube.gui.AddVolunteer;

import Youtube.gui.ManageVolunteers;
import Youtube.gui.ManageVotes;
import Youtube.gui.VolunteerLogin;
import Youtube.model.NominatedChannel;
import Youtube.model.Volunteer;
import Youtube.model.Vote;
import Youtube.repo.NominatedChannelRepo;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;


@SpringBootApplication
public class App implements InitializingBean {


    @Autowired
    private Youtube.repo.VolunteerRepo volunteers;

    @Autowired
    private Youtube.repo.VoteRepo votes;


    @Autowired
    private NominatedChannelRepo nominatedChannelRepo;

    @Autowired
    private EntityManager entityManager;

    // private Youtube.vote ;

    private void setupVotes() {

        System.out.println("Setting up votes");

        Vote vote;

        vote = new Vote(2221, "21 useful bsiness tools to grow your business", "Daisy");
        votes.save(vote);

        vote = new Vote(2222, "How to care of the environment", " Romain");
        votes.save(vote);

        vote = new Vote(2223, "One earth", "Bob ");
        votes.save(vote);

        vote = new Vote(2224, "One earth", "Kyer");
        votes.save(vote);


        vote = new Vote(2221, "One earth", "Mason");
        votes.save(vote);

        vote = new Vote(2222, "One earth", " Jack");
        votes.save(vote);

        vote = new Vote(2223, "One earth", "Avey ");
        votes.save(vote);

        vote = new Vote(2224, "One earth", "Wyatt");
        votes.save(vote);
        

        vote = new Vote(2225, "One earth", "Carter");
        votes.save(vote);

        vote = new Vote(2226, "How to care of the environment", " Grayson");
        votes.save(vote);

        vote = new Vote(2227, "One earth", "Jaxon ");
        votes.save(vote);

        vote = new Vote(2228, "One earth", "Hazel");
        votes.save(vote);
        

        vote = new Vote(2229, "21 useful bsiness tools to grow your business", "Coopper");
        votes.save(vote);

        vote = new Vote(2230, "How to care of the environment", " Greyson");
        votes.save(vote);

        vote = new Vote(2231, "One earth", "Ivy ");
        votes.save(vote);

        vote = new Vote(2224, "One earth", "Everett");
        votes.save(vote);
        
        
        System.out.println("Finished setting up votes");
    }

    public void printVotes() {

        System.out.println("There are " + votes.count() + " votes: ");

        for (Vote vote : votes.findAll()) {
            System.out.println("  " + vote);
            addVotes(vote.getNominatedchannelName());
        }
    }

 //(method  to count vote,+1
    
    public void countVotes() {
        List<Map<String, Object>> voteCount = nominatedChannelRepo.countVotesByNominatedChannelName();
        voteCount.forEach(vote -> {
            saveOrUpdateVote(vote);
        });
    }

    public void saveOrUpdateVote(Map<String, Object> vote) {
        String nominatedChannelName = String.valueOf(vote.get("name"));
        NominatedChannel nominatedChannel = nominatedChannelRepo.findNominatedChannelByName(nominatedChannelName);

        if (nominatedChannel == null) {
            nominatedChannel = new NominatedChannel();
            nominatedChannel.setName(String.valueOf(vote.get("name")));
            nominatedChannel.setNumOfVote(Integer.valueOf(String.valueOf(vote.get("numOfVote"))));
            nominatedChannelRepo.save(nominatedChannel);
        } else {
            nominatedChannel.setNumOfVote(Integer.valueOf(String.valueOf(vote.get("numOfVote"))));
            nominatedChannelRepo.updateNumOfVoteByIds(nominatedChannel.getId(),
                    nominatedChannel.getNumOfVote());
        }
        System.out.println("name : " + vote.get("name") + " , numOfVote : " + vote.get("numOfVote"));
    }

    public void addVotes(String nominatedChannelName) {
        NominatedChannel nominatedChannel = nominatedChannelRepo.findNominatedChannelByName(nominatedChannelName);
        if (nominatedChannel == null) {
            nominatedChannel = new NominatedChannel();
            nominatedChannel.setName(nominatedChannelName);
            nominatedChannel.setNumOfVote(0);
        }

        nominatedChannel.upVote();

        nominatedChannelRepo.save(nominatedChannel);
    }

    
    //) count vote, then save those information to nominated channel repository)
    
    
    
    // volunteer data
    private void setupvolunteers() {
		System.out.println("Showing volunteers") ;
		
        Volunteer volunteer = new Volunteer(2001, "Jasmine", "Kennedy") ;
        volunteers.save(volunteer);
        
        volunteer = new Volunteer(2002, "Mary", "Martin") ;
        volunteers.save(volunteer);
        
        volunteer = new Volunteer(2003, "Crystal", "Magee") ;
        volunteers.save(volunteer);
        
        volunteer = new Volunteer(2004, "Annsley", "Brice") ;
        volunteers.save(volunteer);
        
        volunteer = new Volunteer(2005, "Gard", "Blaze") ;
        volunteers.save(volunteer);
        
        volunteer = new Volunteer(2006, "Cleva", "Breri") ;
        volunteers.save(volunteer);
        
        volunteer = new Volunteer(2007, "Winsor", "Duff") ;
        volunteers.save(volunteer);
        
        volunteer = new Volunteer(2008, "Shelton", "Falynn") ;
        volunteers.save(volunteer);
        
        volunteer = new Volunteer(2009, "Robinson", "Dusty") ;
        volunteers.save(volunteer);
        
        volunteer = new Volunteer(2010, "Daune", "Fanny") ;
        volunteers.save(volunteer);
        
        volunteer = new Volunteer(2011, "Bress", "Cage") ;
        volunteers.save(volunteer);
        
        volunteer = new Volunteer(2012, "Tyra", "Fanny") ;
        volunteers.save(volunteer);
        
	}



	 private void printVolunteer() {

	        System.out.println("There are " + volunteers.count() + " volunteers: ");

	        for (Volunteer volunteer: volunteers.findAll()) {
	        	
	            System.out.println("  " + volunteer) ;
	        }
	    }

    public static void main(String args[]) {

        SpringApplicationBuilder builder = new SpringApplicationBuilder(App.class);
        builder.headless(false).run(args);


    }
    
    
//call method
    @Override
    public void afterPropertiesSet() throws Exception {
        setupVotes();
        printVotes();
        countVotes();
        
        setupvolunteers();
        

 
//start volunteer login dialogue(from volunteer aspect)
           VolunteerLogin gui = new VolunteerLogin(this.volunteers, votes);
            gui.pack();
            gui.setVisible(true);  
            
          //start volunteer login dialogue(from manage aspect, as an administration officer to add and edit volunteer)
           // ManageVolunteers gui = new ManageVolunteers(this.volunteers);
           //  gui.pack();
            // gui.setVisible(true);  
    }
}



