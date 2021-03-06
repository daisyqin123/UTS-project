package Youtube.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id ;

    private String name ;
    
    private Category() {
    	
    }
    
    public Category(String name) {
    	this.name = name;
    }
    
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return id + ": " + name ;
    }
}
