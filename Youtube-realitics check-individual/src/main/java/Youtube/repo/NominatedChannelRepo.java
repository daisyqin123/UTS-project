package Youtube.repo;

import Youtube.model.NominatedChannel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
public interface NominatedChannelRepo extends CrudRepository<NominatedChannel, Integer> {

    @Query(value = "from NominatedChannel where name = ?1")
    NominatedChannel findNominatedChannelByName(String name);


    @Query("select nominatedchannelName as name,  count(id) as numOfVote from Vote group by nominatedchannelName")
    List<Map<String, Object>> countVotesByNominatedChannelName();

    @Modifying
    @Query("update NominatedChannel set numOfVote=?2 where id=?1")
    void updateNumOfVoteByIds(Integer id, Integer numOfVote);
}
