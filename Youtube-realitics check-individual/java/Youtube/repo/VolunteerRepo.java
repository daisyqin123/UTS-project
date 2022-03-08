package Youtube.repo;

import org.springframework.data.repository.CrudRepository;

import Youtube.model.Volunteer;



public interface VolunteerRepo extends CrudRepository<Volunteer, Integer> {

}
