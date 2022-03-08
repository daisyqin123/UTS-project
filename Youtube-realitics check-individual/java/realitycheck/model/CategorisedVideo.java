package realitycheck.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CategorisedVideo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer videoId;
	private Integer categoryId;

	private CategorisedVideo() {

	}

	public CategorisedVideo(Integer videoId, Integer categoryId) {
		this.videoId = videoId;
		this.categoryId = categoryId;
	}

	public Integer getvideoId() {
		return videoId;
	}

	public Integer categoryId() {
		return categoryId;
	}

	public Integer id() {
		return id;
	}

}
