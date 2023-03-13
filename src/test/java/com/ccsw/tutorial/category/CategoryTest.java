package com.ccsw.tutorial.category;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ccsw.tutorial.category.model.Category;
import com.ccsw.tutorial.category.model.CategoryDto;

/**
 * 
 * @author caliagaq
 *
 */
@ExtendWith(MockitoExtension.class)
public class CategoryTest {
	
	@Mock
	private CategoryRepository categoryRepository;
	
	@InjectMocks 
	private CategoryServiceImpl categoryService;
	
	public static final String CATEGORY_NAME = "CATEGORY1";
	public static final Long EXISTING_CATEGORY_ID = 1L;
	public static final Long NOT_EXISTING_CATEGORY_ID = 100L;

	
	@Test
	public void findAllShouldReturnAllCategories() {
		List<Category> list = new ArrayList<>();
        list.add(mock(Category.class));

        when(categoryRepository.findAll()).thenReturn(list);

        List<Category> categories = categoryService.findAll();

        assertNotNull(categories);
        assertEquals(1, categories.size());
	}
	
	@Test
	public void saveWithoutCategoryIdShouldInsert() {
		CategoryDto categoryToInsert = new CategoryDto();
		categoryToInsert.setName(CATEGORY_NAME);
		
		ArgumentCaptor<Category> category = ArgumentCaptor.forClass(Category.class);
		
		categoryService.save(null, categoryToInsert);
		
		verify(categoryRepository).save(category.capture());
		
		assertEquals(CATEGORY_NAME, category.getValue().getName());
	}
	
	@Test
	public void saveExistingCategoryIdShouldUpdate() {
		CategoryDto categoryToUpdate = new CategoryDto();
		categoryToUpdate.setName(CATEGORY_NAME);
		
		Category category = mock(Category.class);
		when(categoryRepository.findById(EXISTING_CATEGORY_ID)).thenReturn(Optional.of(category));

		categoryService.save(EXISTING_CATEGORY_ID, categoryToUpdate);

		verify(categoryRepository).save(category);
	}
	
	@Test
	public void saveNotExistingCategoryIdShouldThrowNullPointerException() {
		CategoryDto categoryToInsert = new CategoryDto();
		categoryToInsert.setName(CATEGORY_NAME);
		
		when(categoryRepository.findById(NOT_EXISTING_CATEGORY_ID)).thenReturn(null);

		assertThrows(NullPointerException.class,
			()->{
				categoryService.save(NOT_EXISTING_CATEGORY_ID, categoryToInsert);
				});
	}
	
	@Test
	public void getExistingCategoryIdShouldReturnCategory() {

	      Category category = mock(Category.class);
	      when(category.getId()).thenReturn(EXISTING_CATEGORY_ID);
	      when(categoryRepository.findById(EXISTING_CATEGORY_ID)).thenReturn(Optional.of(category));

	      Category categoryResponse  = categoryService.get(EXISTING_CATEGORY_ID);

	      assertNotNull(categoryResponse);
	      assertEquals(EXISTING_CATEGORY_ID, category.getId());
	}

	@Test
	public void getNotExistingCategoryIdShouldReturnNull() {

	      when(categoryRepository.findById(NOT_EXISTING_CATEGORY_ID)).thenReturn(Optional.empty());

	      Category category = categoryService.get(NOT_EXISTING_CATEGORY_ID);

	      assertNull(category);
	}
	
	@Test
	public void deleteExistingCategoryIdShouldDelete() {
		categoryService.delete(EXISTING_CATEGORY_ID);

		verify(categoryRepository).deleteById(EXISTING_CATEGORY_ID);
	}
}
