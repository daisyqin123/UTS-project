package realitycheck.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
public class Category {
	@Id

	private Integer id;

	private String name;
@ManyToMany(fetch = FetchType.EAGER) 
@JoinTable(
		name = "Categorised_Video123",
    	joinColumns =  @JoinColumn(name = "categoryId") ,
            inverseJoinColumns = @JoinColumn(name = "id")) 
		public Set<Video> catevideos;


	private Category() {

	}

	public void setCatevideos(Set<Video> set) { 
		this.catevideos = set;
	}
	public Set<Video> getCatevideos() { 
		return this.catevideos;
	}
	public Category(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return id + ": " + name;
	}
//	public List<Video> getVideos(){
//		return videos;
//	}
//	public void catevideo(Video video) {
//		catevideos.add(video);
//	}
}
