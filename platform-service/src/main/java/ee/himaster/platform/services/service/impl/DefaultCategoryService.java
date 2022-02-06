package ee.himaster.platform.services.service.impl;

import ee.himaster.core.service.exception.ModelNotFoundException;
import ee.himaster.platform.services.model.CategoryModel;
import ee.himaster.platform.services.repository.CategoryRepository;
import ee.himaster.platform.services.service.CategoryService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class DefaultCategoryService implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryModel> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryModel getCategoryById(Integer id) {
        Optional<CategoryModel> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            log.error("Category by id is not found. Category id: {}.", id);
            throw new ModelNotFoundException("Category by id is not found. Category id: " + id);
        }
        return category.get();
    }
}
