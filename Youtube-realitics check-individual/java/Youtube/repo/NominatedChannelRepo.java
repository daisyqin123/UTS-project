package Youtube.repo;

import Youtube.model.NominatedChannel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;

public interface NominatedChannelRepo extends CrudRepository<NominatedChannel, Integer> {

    @Query(value = "from NominatedChannel where name = ?1")
    NominatedChannel findNominatedChannelByName(String name);


    @Query("select nominatedchannelName as name,  count(*) as numOfVote from Vote group by nominatedchannelName")
    List<Map<String, Object>> countVotesByNominatedChannelName();

}
