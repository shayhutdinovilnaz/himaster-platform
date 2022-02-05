package ee.himaster.platform.web.application.controller;

import ee.himaster.core.service.converter.impl.BasicConverter;
import ee.himaster.platform.api.controller.CategoryApi;
import ee.himaster.platform.dto.CategoryDto;
import ee.himaster.platform.services.model.CategoryModel;
import ee.himaster.platform.services.service.CategoryService;
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
        final CategoryDto category = categoryConverter.convert(categoryService.getCategoryById(categoryId));
        return ResponseEntity.ok(category);
    }
}
