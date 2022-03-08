package realitycheck.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import realitycheck.model.Category;
import realitycheck.repo.CategoryRepo;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	public List<Category> getCategories() {
		System.out.println("start get category");
		List<Category> result = new ArrayList<Category>();
		Iterable<Category> iterable = categoryRepo.findAll();
		iterable.forEach(result::add);
		System.out.println("finish get category");
		return result;
	}

}
