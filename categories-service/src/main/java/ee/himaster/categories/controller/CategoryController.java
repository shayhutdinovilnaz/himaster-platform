package ee.himaster.categories.controller;

import ee.himaster.categories.api.controller.CategoryApi;
import ee.himaster.categories.dto.CategoryDto;
import ee.himaster.categories.model.CategoryModel;
import ee.himaster.categories.service.CategoryService;
import ee.himaster.core.service.converter.impl.BasicConverter;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
@RequiredArgsConstructor
@Controller
public class CategoryController implements CategoryApi {
    private final CategoryService categoryService;
    private final BasicConverter<CategoryDto, CategoryModel> categoryConverter;

    @Override
    public ResponseEntity<List<CategoryDto>> getAll() {
        final List<CategoryDto> categories = CollectionUtils.emptyIfNull(categoryService.getAllCategories())
                .stream()
                .map(categoryConverter::convert)
                .collect(Collectors.toList());
        return ResponseEntity.ok(categories);
    }

    @Override
    public ResponseEntity<CategoryDto> getById(Integer categoryId) {
        final CategoryDto category = categoryConverter.convert(categoryService.getCategoryById(categoryId.longValue()));
        return ResponseEntity.ok(category);
    }
}
