package ee.himaster.platform.services.service;

import ee.himaster.platform.services.model.CategoryModel;
import java.util.List;

public interface CategoryService {
    /**
     * Retrieve all categories
     *
     * @return list of search result categories
     */
    List<CategoryModel> getAllCategories();

    /**
     * Retrieve a category by id
     *
     * @return the category
     */
    CategoryModel getCategoryById(Integer id);
}
