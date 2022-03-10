package ee.himaster.platform.services.service.impl;

import ee.himaster.core.service.service.impl.AbstractModelService;
import ee.himaster.platform.services.model.CategoryModel;
import ee.himaster.platform.services.repository.CategoryRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.jpa.repository.JpaRepository;

public class DefaultCategoryServiceTest extends AbstractModelServiceTest<CategoryModel> {
    @InjectMocks
    private DefaultCategoryService underTest;

    @Mock
    private CategoryRepository categoryRepository;

    @Override
    protected Class<CategoryModel> getGenericClassOfService() {
        return CategoryModel.class;
    }

    @Override
    protected AbstractModelService<CategoryModel> getGenericModelService() {
        return underTest;
    }

    @Override
    protected JpaRepository<CategoryModel, Integer> getModelRepository() {
        return categoryRepository;
    }
}