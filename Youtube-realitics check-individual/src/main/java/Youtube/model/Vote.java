package Youtube.model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Vote {

    @Id
    private Integer id ;
    private String nominatedchannelName ;
    private String volunteerName ;

    private Vote() {
    }

    public Vote(String nominatedchannelName, String volunteerName) {
        this.nominatedchannelName = nominatedchannelName;
        this.volunteerName = volunteerName;
    }
    
    public Vote(Integer id, String nominatedchannelName, String volunteerName) {
        this.id = id;
        this.nominatedchannelName = nominatedchannelName;
        this.volunteerName = volunteerName;
    }

    public Integer getId() {
        return id;
    }

    public String getNominatedchannelName() {
        return nominatedchannelName;
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public String toString() {
        return id + ": " + nominatedchannelName + ", " + volunteerName ;
    }
}