package realitycheck.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import realitycheck.model.Vote;
import realitycheck.repo.VoteRepo;

@Service
public class VoteCalculate {
	@Autowired
	private VoteRepo votes;
	public void upVote(Integer volunteerId, Integer videoId) {
		Vote vote = new Vote(volunteerId, videoId, true); 
		votes.save(vote);
	}
	public void downVote(Integer volunteerId, Integer videoId) {
		Vote vote = new Vote(volunteerId, videoId, false); 
		votes.save(vote);
	}
	public int countUpVote(Integer videoId) {
		return votes.calUpVoteByVideoId(videoId);
	}
	public int countDownVote(Integer videoId) {
		return votes.calDownVoteByVideoId(videoId);
	}
}

