package ee.himaster.platform.services.service;

import ee.himaster.core.service.service.ModelService;
import ee.himaster.platform.services.model.CategoryModel;

import java.util.List;

public interface CategoryService extends ModelService<CategoryModel> {
    /**
     * Retrieve root categories
     *
     * @return list of  root categories
     */
    List<CategoryModel> getRootCategories();
}
