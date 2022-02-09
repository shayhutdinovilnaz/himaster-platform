package ee.himaster.platform.facades.facade;

import ee.himaster.platform.dto.CategoryDto;
import java.util.List;

public interface CategoryFacade {
    /**
     * Find category by id.
     *
     * @param categoryId - category's id
     *
     * @return the category
     */
    CategoryDto getById(Integer categoryId);

    /**
     * Find all root categories
     *
     * @return the list of root's categories
     */
    List<CategoryDto> getRootCategories();
}
