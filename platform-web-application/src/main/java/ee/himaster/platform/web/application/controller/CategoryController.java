package ee.himaster.platform.web.application.controller;

import ee.himaster.platform.api.controller.CategoryApi;
import ee.himaster.platform.dto.CategoryDto;
import ee.himaster.platform.facades.facade.CategoryFacade;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.RequestScope;

@RequestMapping
@RequiredArgsConstructor
@Controller
@RequestScope
public class CategoryController implements CategoryApi {
    private final CategoryFacade categoryFacade;

    @Override
    public ResponseEntity<List<CategoryDto>> getRootsCategories() {
        return ResponseEntity.ok(categoryFacade.getRootCategories());
    }

    @Override
    public ResponseEntity<CategoryDto> getById(Integer categoryId) {
        return ResponseEntity.ok(categoryFacade.getById(categoryId));
    }
}
