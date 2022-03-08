package realitycheck.repo;

import realitycheck.model.Volunteer;
import org.springframework.data.repository.CrudRepository;

public interface VolunteerRepo extends CrudRepository<Volunteer, Integer> {

}