package ee.himaster.platform.services.service.impl;

import ee.himaster.core.service.service.impl.AbstractModelService;
import ee.himaster.platform.services.model.CategoryModel;
import ee.himaster.platform.services.repository.CategoryRepository;
import ee.himaster.platform.services.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class DefaultCategoryService extends AbstractModelService<CategoryModel> implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryModel> getRootCategories() {
        return categoryRepository.getAllByParentCategoryIsNull();
    }

    @Override
    protected JpaRepository<CategoryModel, Integer> getItemRepository() {
        return categoryRepository;
    }
}
