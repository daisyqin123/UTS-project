package Youtube.repo;


import Youtube.model.NominatedChannel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import Youtube.model.Vote;

import java.util.List;
import java.util.Map;


public interface VoteRepo extends CrudRepository<Vote, Integer> {
//	List<Vote> findByVolunteerName(String VolunteerName) ;
}