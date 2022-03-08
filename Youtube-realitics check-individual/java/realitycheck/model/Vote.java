package realitycheck.model;

import java.sql.Date;

import javax.persistence.*;

@Entity
public class Vote {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	private Integer volunteerId;
	private Integer videoId;
	private Boolean upvote;

	public Vote(Integer volunteerId, Integer videoId, Boolean upvote) {
		this.volunteerId = volunteerId;
		this.videoId = videoId;
		this.upvote = upvote;
	}

	public Integer getId() {
		return Id;
	}

	public Integer getVolunteerId() {
		return volunteerId;
	}

	public Integer getVideoId() {
		return videoId;
	}

	public Boolean upvote() {
		return upvote;
	}

}
