package realitycheck.model;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;


@Entity
public class Video {

	@Id
	private Integer id;
	private String name;
	
//	@ManyToOne
//	@Cascade({ CascadeType.ALL })
//	@JoinColumn(name = "Id")
//	@NotFound(action=NotFoundAction.IGNORE)
//	private Integer channelId;

	private Integer categoryId;
	private InWhichQueue inWhichQueue;
	private Integer numberOfVote;
	private Date updateDate;
	@ManyToMany
	public Set<Category> vidcategories;

	public Video(Integer id, String name, Integer categoryId, InWhichQueue inWhichQueue, Integer numberOfVote, Date updateDate) {
		this.id = id;
		this.name = name;
		this.categoryId = categoryId;
		this.inWhichQueue = inWhichQueue;
		this.numberOfVote = numberOfVote;
		this.updateDate = updateDate;
	}
	
//	public void CategorisedVideo(Category category) {
//		CategorisedVideo.add(category);
//	}
	public Video() {}

	public Integer getId() {
		return id;
	}

	public Integer getcategoryId() {
		return categoryId;
	}

	public String getName() {
		return name;
	}

	public InWhichQueue getinWhichQueue() {
		return inWhichQueue;
	}
	public void setInWhichQueue(InWhichQueue inWhichQueue) {
		this.inWhichQueue = inWhichQueue;
	}

	public Integer getnumberOfVote() {
		return numberOfVote;
	}
	public void setnumberOfVote(Integer integer) {
		this.numberOfVote = integer;
	}

	public Date getupdateDate() {
		return updateDate;
	}
	
	public void setVidCategory(Set<Category> categoryset) { 
		this.vidcategories = categoryset;
	}
	public Set<Category> getVidCategory() { 

		return this.vidcategories;
	}
//	public void addCategory(Category category) {
//		vidcategories.add(category);
//	}
} 
