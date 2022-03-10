package ee.himaster.platform.services.repository;

import ee.himaster.platform.services.model.CategoryModel;
import ee.himaster.platform.util.AbstractIntegrationTest;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoryRepositoryITest extends AbstractIntegrationTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void getById_categoryExist_retrieved() {
        CategoryModel categoryModel = categoryRepository.getById(1);
        Assert.assertNotNull(categoryModel);
    }

    @Test
    public void getAllByParentCategoryIsNull_categoriesExist_retrieved() {
        List<CategoryModel> roots = categoryRepository.getAllByParentCategoryIsNull();
        Assert.assertTrue(CollectionUtils.isNotEmpty(roots));
    }
}
