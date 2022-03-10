package ee.himaster.platform.services.service.impl;

import ee.himaster.core.service.exception.ModelNotFoundException;
import ee.himaster.core.service.model.ItemModel;
import ee.himaster.core.service.service.impl.AbstractModelService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public abstract class AbstractModelServiceTest<T extends ItemModel> {

    @Test
    public void testSaveNewItem() {
        final T item = mock(getGenericClassOfService());

        getGenericModelService().save(item);
        verify(item).getCreatedTime();
        verify(item).setCreatedTime(Mockito.any());
        verify(item).setModifiedTime(Mockito.any());
        verifyNoMoreInteractions(item);
        verify(getModelRepository()).save(item);
        verifyNoMoreInteractions(getModelRepository());
    }

    @Test
    public void testSaveExistItem() {
        final T item = mock(getGenericClassOfService());

        when(item.getCreatedTime()).thenReturn(new Date());

        getGenericModelService().save(item);
        verify(item).getCreatedTime();
        verify(item).setModifiedTime(Mockito.any());
        verifyNoMoreInteractions(item);
        verify(getModelRepository()).save(item);
        verifyNoMoreInteractions(getModelRepository());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaveIllegalItem() {
        getGenericModelService().save(null);
    }

    @Test(expected = ModelNotFoundException.class)
    public void testGetByIdNotExistItem() {
        final int itemId = 1;

        when(getModelRepository().findById(itemId)).thenReturn(Optional.empty());

        getGenericModelService().getById(itemId);
    }

    @Test
    public void testGetByIdExistItem() {
        final int itemId = 1;
        final T item = mock(getGenericClassOfService());

        when(getModelRepository().findById(itemId)).thenReturn(Optional.of(item));

        Assert.assertEquals(item, getGenericModelService().getById(itemId));
        verify(getModelRepository()).findById(itemId);
        verifyNoMoreInteractions(getModelRepository());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteNullableItem() {
        doThrow(new IllegalArgumentException()).when(getModelRepository()).delete(null);
        getGenericModelService().delete(null);
    }

    @Test
    public void testDelete() {
        final T item = mock(getGenericClassOfService());

        getGenericModelService().delete(item);
        verify(getModelRepository()).delete(item);
        verifyNoMoreInteractions(getModelRepository());
    }

    protected abstract Class<T> getGenericClassOfService();

    protected abstract AbstractModelService<T> getGenericModelService();

    protected abstract JpaRepository<T, Integer> getModelRepository();
}