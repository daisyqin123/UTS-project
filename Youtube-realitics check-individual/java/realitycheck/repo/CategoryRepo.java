package realitycheck.repo;

import realitycheck.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepo extends CrudRepository<Category, Integer> {
	Category findByid(Integer id);
	Category findByName(String name);
	
}
