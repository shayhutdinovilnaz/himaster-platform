package ee.himaster.categories.service.impl;

import ee.himaster.categories.model.CategoryModel;
import ee.himaster.categories.repository.CategoryRepository;
import ee.himaster.categories.service.CategoryService;
import ee.himaster.core.service.exception.ModelNotFoundException;
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
    public CategoryModel getCategoryById(Long id) {
        Optional<CategoryModel> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            log.error("Category by id is not found. Category id: {}.", id);
            throw new ModelNotFoundException("Category by id is not found. Category id: " + id);
        }
        return category.get();
    }
}
