package Youtube.model;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name = "nominated_channel")
public class NominatedChannel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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
    
    public NominatedChannel() {
    	
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

    public void setName(String name) {
        this.name = name;
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
