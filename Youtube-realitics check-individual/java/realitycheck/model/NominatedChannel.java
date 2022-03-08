package realitycheck.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class NominatedChannel {
    
    @Id
//    @GeneratedValue(generator = "myForeignGenerator")
//    @org.hibernate.annotations.GenericGenerator(
//        name = "myForeignGenerator",
//        strategy = "foreign",
//        parameters = @Parameter(name = "property", value = "a")
//    )
//    private Long subscriberId;
//
//    @OneToOne(mappedBy="b")
//    @PrimaryKeyJoinColumn
//    @NotFound(action=NotFoundAction.IGNORE)
    private Integer id ;
    
    private String name;
    private Date verifyDate;
    private Integer ownerId;
    private Integer numOfVote;
    private boolean isVerified;
    private String nomExplanation;
    private String rejExplanation;
    private String link;
    
    // defined value
    private static final Integer voteThres1 = 100;
    private static final Integer voteThres2 = 200;
    private static final Integer voteThres3 = 500;
    private static final Integer voteThres4 = 1000;
    
    private NominatedChannel() {
    	
    }
    
    public NominatedChannel(Integer id, String name, Integer ownerId, String link, String explanation) {
    	this.id = id;
    	this.name = name;
    	this.ownerId = ownerId;
    	this.verifyDate = null;
    	this.numOfVote = 0;
    	this.isVerified = false;
    	this.nomExplanation = explanation;
    	this.rejExplanation = null;
    	this.link = link;
    }
    
    public Integer getId() {
    	return id;
    }
    
    public String getName() {
    	return name;
    }
    
    public Integer getOwnerId() {
    	return ownerId;
    }
    
    public String getLink() {
    	return link;
    }
    
    public void setNomExplanation(String explanation) {
    	nomExplanation = explanation;
    }
    
    public String getNomExplanation() {
    	return nomExplanation;
    }
    
    public void setVerified(boolean verify) {
    	isVerified = verify;
    }
    
    public boolean isVerified() {
    	return isVerified;
    }
    
    public void setVerifiedDate(Date date) {
    	verifyDate = date;
    }
    
    public Date getVerifiedDate() {
    	return verifyDate;
    }
    
    public void setRejExplanation(String explanation) {
    	rejExplanation = explanation;
    }

    public String getRejExplanation() {
    	return rejExplanation;
    }
    
    public void setNumOfVote(Integer vote) {
    	numOfVote = vote;
    }
    
    public Integer getNumOfVote() {
    	return numOfVote;
    }
  
    public String toString() {
        return id + ": " + name ;
    } 
    
	/*    
	 * Specific methods
	*/
    public boolean meetThreshold() {
    	if (numOfVote >= voteThres1) {
    		return true;
    	}
    	return false;
    }
    
    public void upVote() {
    	this.numOfVote++;
    }
    
    public void triggerNotification() {
    	// TODO add linkage with email system
    	System.out.println("Channel " + id + " triggers email notification");
    }
    
}

