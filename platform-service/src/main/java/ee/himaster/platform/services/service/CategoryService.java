package ee.himaster.platform.services.service;

import ee.himaster.platform.services.model.CategoryModel;
import java.util.List;

public interface CategoryService {
    /**
     * Retrieve root categories
     *
     * @return list of  root categories
     */
    List<CategoryModel> getRootCategories();

    /**
     * Retrieve a category by id
     *
     * @return the category
     */
    CategoryModel getCategoryById(Integer id);
}
