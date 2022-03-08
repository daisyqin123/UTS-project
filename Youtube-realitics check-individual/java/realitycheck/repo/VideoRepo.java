package realitycheck.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;


import realitycheck.model.Video;


public interface VideoRepo extends CrudRepository<Video, Integer> {
	
	List<Video> findByid(Integer id) ;
    List<Video> findByName(String name) ;
//    Video findByCategoryIdAndName(int CategoryId, String name) ;

}
