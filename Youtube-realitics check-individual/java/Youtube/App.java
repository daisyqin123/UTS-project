package Youtube;


import Youtube.gui.ManageVotes;
import Youtube.model.NominatedChannel;
import Youtube.model.Vote;
import Youtube.repo.NominatedChannelRepo;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

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

    // private Youtube.service.InteractiveReviewer reviewer ;

    private void setupVotes() {

        System.out.println("Setting up votes");

        Vote vote;

        vote = new Vote(1, "21 useful bsiness tools to grow your business", "Daisy");
        votes.save(vote);

        vote = new Vote(2, "How to care of the environment", " Romain");
        votes.save(vote);

        vote = new Vote(3, "One earth", "Bob ");
        votes.save(vote);

        vote = new Vote(4, "One earth", "Bob 1");
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

    public void countVotes(){
        List<Map<String, Object>> voteCount = nominatedChannelRepo.countVotesByNominatedChannelName();
        voteCount.forEach(vote -> {
            NominatedChannel nominatedChannel = new NominatedChannel();
            nominatedChannel.setName(String.valueOf(vote.get("name")));
            nominatedChannel.setNumOfVote(Integer.valueOf(String.valueOf(vote.get("numOfVote"))));
            System.out.println("name : " + vote.get("name") + " , numOfVote : " + vote.get("numOfVote"));
            nominatedChannelRepo.save(nominatedChannel);
        });
    }

    public void addVotes(String nominatedChannelName) {
        NominatedChannel nominatedChannel = nominatedChannelRepo.findNominatedChannelByName(nominatedChannelName);;
        if (nominatedChannel == null) {
            nominatedChannel = new NominatedChannel();
            nominatedChannel.setName(nominatedChannelName);
            nominatedChannel.setNumOfVote(0);
        }

        nominatedChannel.upVote();

        nominatedChannelRepo.save(nominatedChannel);
    }


    public static void main(String args[]) {

        SpringApplicationBuilder builder = new SpringApplicationBuilder(App.class);
        builder.headless(false).run(args);

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setupVotes();
        printVotes();


        ManageVotes gui = new ManageVotes(this.votes);
        gui.pack();
        gui.setVisible(true);


    }
}



