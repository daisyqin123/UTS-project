package realitycheck.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import realitycheck.model.CategorisedVideo;

public interface CategorisedVideoRepo extends CrudRepository<CategorisedVideo, Integer> {

    List<CategorisedVideo> findBycategoryId(int categoryId) ;

    List<CategorisedVideo> findByvideoId(int videoId) ;
    
    CategorisedVideo findByCategoryIdAndVideoId(int categoryId, int videoId) ;

}
