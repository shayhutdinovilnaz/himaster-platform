package ee.himaster.platform.services.repository;

import ee.himaster.platform.services.model.CategoryModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryModel, Integer> {

    List<CategoryModel> getAllByParentCategoryIsNull();
}
