package ee.himaster.platform.web.application.controller.v1;

import ee.himaster.platform.api.controller.CategoryApi;
import ee.himaster.platform.dto.CategoryDto;
import ee.himaster.platform.facades.facade.CategoryFacade;
import ee.himaster.platform.web.application.controller.BaseController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/v1")
@RestController
@Slf4j
public class CategoryController extends BaseController implements CategoryApi {
    private final CategoryFacade categoryFacade;

    @Override
    public ResponseEntity<CategoryDto> getById(Integer categoryId, String localeCode) {
        return ResponseEntity.ok(categoryFacade.getById(categoryId));
    }

    @Override
    public ResponseEntity<List<CategoryDto>> getRootsCategories(String localeCode) {
        return ResponseEntity.ok(categoryFacade.getRootCategories());
    }
}
