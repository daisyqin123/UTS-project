package realitycheck.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import realitycheck.model.Vote;

public interface VoteRepo extends CrudRepository<Vote, Integer> {

	@Query("SELECT COUNT(videoId) FROM Vote WHERE upvote=true AND videoId=?1")
	int calUpVoteByVideoId(Integer videoId);
	@Query("SELECT COUNT(videoId) FROM Vote WHERE upvote=false AND videoId=?1")
	int calDownVoteByVideoId(Integer videoId);
}
