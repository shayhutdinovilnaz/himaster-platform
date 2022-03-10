package ee.himaster.platform.facades.facade.impl;

import ee.himaster.core.service.converter.impl.BasicConverter;
import ee.himaster.core.service.exception.ModelNotFoundException;
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
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCategoryFacadeTest {

    @Mock
    private CategoryService categoryService;

    @Mock
    private BasicConverter<CategoryDto, CategoryModel> categoryConverter;

    @InjectMocks
    private DefaultCategoryFacade underTest;

    @Test
    public void getById_categoryExist_success() {
        final var categoryModel = new CategoryModel();
        final var categoryDto = new CategoryDto();
        final var categoryId = 1;

        when(categoryService.getById(categoryId)).thenReturn(categoryModel);
        when(categoryConverter.convert(categoryModel)).thenReturn(categoryDto);

        CategoryDto result = underTest.getById(categoryId);
        Assert.assertEquals(categoryDto, result);

        verify(categoryService).getById(categoryId);
        verify(categoryConverter).convert(categoryModel);
        verifyNoMoreInteractions(categoryService, categoryConverter);
    }

    @Test(expected = ModelNotFoundException.class)
    public void getById_categoryNotExist_exception() {
        final var categoryId = 1;

        when(categoryService.getById(categoryId)).thenThrow(new ModelNotFoundException());

        underTest.getById(categoryId);
    }

    @Test
    public void getRootCategories_rootCategoriesExist_success() {
        final var categoryModel = new CategoryModel();
        final var categoryDto = new CategoryDto();

        when(categoryService.getRootCategories()).thenReturn(Collections.singletonList(categoryModel));
        when(categoryConverter.convert(categoryModel)).thenReturn(categoryDto);

        List<CategoryDto> result = underTest.getRootCategories();
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(categoryDto, result.get(0));

        verify(categoryService).getRootCategories();
        verify(categoryConverter).convert(categoryModel);
        verifyNoMoreInteractions(categoryService, categoryConverter);
    }

    @Test
    public void getRootCategories_rootCategoriesNotExist_emptyList() {
        when(categoryService.getRootCategories()).thenReturn(null);

        List<CategoryDto> result = underTest.getRootCategories();
        Assert.assertTrue(result.isEmpty());

        verify(categoryService).getRootCategories();
        verifyNoMoreInteractions(categoryService);
        verifyNoInteractions(categoryConverter);
    }

}