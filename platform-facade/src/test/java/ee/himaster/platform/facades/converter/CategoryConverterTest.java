package ee.himaster.platform.facades.converter;

import ee.himaster.platform.dto.CategoryDto;
import ee.himaster.platform.facades.populator.BasicCategoryPopulator;
import ee.himaster.platform.services.model.CategoryModel;
import ee.himaster.platform.services.service.CategoryService;
import java.util.Collections;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
public class CategoryConverterTest {
//
//    @Mock
//    private BasicCategoryPopulator basicCategoryPopulator;
//
//    @Mock
//    private CategoryService categoryService;
//
//    private CategoryConverter underTest;
//
//    @Before
//    public void setUp() {
//        underTest = new CategoryConverter(Collections.singletonList(basicCategoryPopulator), CategoryDto::new, CategoryModel::new, categoryService);
//    }
//
//    @Test
//    public void convert_validSource_success() {
//        final CategoryModel parentCategoryModel = new CategoryModel();
//        final Integer parentId = 1;
//        final CategoryModel childCategoryModel = new CategoryModel();
//        final Integer childId = 2;
//        parentCategoryModel.setId(parentId);
//        parentCategoryModel.setChildrenCategories(Collections.singletonList(childCategoryModel));
//
//        childCategoryModel.setId(childId);
//        childCategoryModel.setParentCategory(parentCategoryModel);
//
//        CategoryDto result = underTest.convert(parentCategoryModel);
//
//        Assert.assertNotNull(result);
//        verify(basicCategoryPopulator).populate(eq(parentCategoryModel), any(CategoryDto.class));
//        verifyNoMoreInteractions(basicCategoryPopulator);
//    }
//
//    @Test
//    public void reverseConvert_validSource_success() {
//        final int parentId = 1;
//        final int childId = 2;
//
//        final CategoryDto parentCategoryDTO = new CategoryDto();
//        parentCategoryDTO.setId(parentId);
//        parentCategoryDTO.setChildren(Collections.singletonList(childId));
//
//        CategoryModel childCategoryModel = new CategoryModel();
//        childCategoryModel.setId(childId);
//
//        when(categoryService.getById(childId)).thenReturn(childCategoryModel);
//
//        CategoryModel result = underTest.reverseConvert(parentCategoryDTO);
//
//        Assert.assertNotNull(result);
//        Assert.assertEquals(childId, result.getChildrenCategories().get(0).getId(), 0);
//        verify(categoryService).getById(childId);
//        verify(basicCategoryPopulator).reversePopulate(eq(parentCategoryDTO), any(CategoryModel.class));
//        verifyNoMoreInteractions(basicCategoryPopulator);
//    }
}