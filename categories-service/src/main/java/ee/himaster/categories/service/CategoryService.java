package ee.himaster.categories.service;

import ee.himaster.categories.model.CategoryModel;
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
    CategoryModel getCategoryById(Long id);
}
