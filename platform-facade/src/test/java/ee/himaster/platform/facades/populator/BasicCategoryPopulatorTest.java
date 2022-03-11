package ee.himaster.platform.facades.populator;

import ee.himaster.core.localization.model.LocalizedStringModel;
import ee.himaster.core.localization.service.LocalizedStringService;
import ee.himaster.platform.dto.CategoryDto;
import ee.himaster.platform.services.model.CategoryModel;
import ee.himaster.platform.services.service.CategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BasicCategoryPopulatorTest {

    @InjectMocks
    private BasicCategoryPopulator underTest;

    @Mock
    private CategoryService categoryService;

    @Mock
    private LocalizedStringService localizedStringService;


    @Test
    public void populate_validSource_success() {

        final var source = new CategoryModel();
        final var target = new CategoryDto();
        final var parentId = 1;
        final var childCategoryModel = new CategoryModel();
        final var childId = 2;
        final var titleValue = "categoryTitle";
        final var title = new LocalizedStringModel();
        source.setId(parentId);
        source.setTitle(title);
        source.setChildrenCategories(Collections.singletonList(childCategoryModel));

        childCategoryModel.setId(childId);
        childCategoryModel.setParentCategory(source);
        when(localizedStringService.getLocalizedStringValue(title)).thenReturn(titleValue);

        underTest.populate(source, target);

        Assert.assertEquals(source.getId(), target.getId());
        Assert.assertEquals(titleValue, titleValue);
        Assert.assertEquals(1, target.getChildren().size());
        Assert.assertEquals(2, target.getChildren().get(0), 0);

        verify(localizedStringService).getLocalizedStringValue(title);
        verifyNoMoreInteractions(localizedStringService);
    }

    @Test
    public void reversePopulate_validSource_success() {
        final int parentId = 1;
        final int childId = 2;
        final var titleValue = "categoryTitle";

        final var source = new CategoryDto();
        source.setId(parentId);
        source.setTitle(titleValue);
        source.setChildren(Collections.singletonList(childId));

        final var title = new LocalizedStringModel();
        final var target = new CategoryModel();
        target.setTitle(title);
        final var childCategoryModel = new CategoryModel();
        childCategoryModel.setId(childId);

        when(categoryService.getById(childId)).thenReturn(childCategoryModel);

        underTest.reversePopulate(source, target);

        Assert.assertNotNull(target);
        Assert.assertEquals(childId, target.getChildrenCategories().get(0).getId(), 0);
        verify(categoryService).getById(childId);
        verify(localizedStringService).addLocalizedStringValue(titleValue, title);
        verifyNoMoreInteractions(categoryService);
    }
}