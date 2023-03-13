package com.ccsw.tutorial.category;

import java.util.List;

import com.ccsw.tutorial.category.model.Category;
import com.ccsw.tutorial.category.model.CategoryDto;

/**
 * 
 * @author caliagaq
 *
 */
public interface CategoryService {
    
    /**
     * Método para recuperar una {@link com.ccsw.tutorial.category.model.Category} con su ID
     * @param id
     * @return
     */
    Category get(Long id);
    
	/**
     * Método para recuperar todas las {@link com.ccsw.tutorial.category.model.Category}
     * @return
     */
    List<Category> findAll();
    
    /**
     * Método para crear o actualizar una {@link com.ccsw.tutorial.category.model.Category}
     * @param dto
     * @param id
     */
    void save(Long id, CategoryDto dto);
    
    /**
     * Método para borrar una {@link com.ccsw.tutorial.category.model.Category}
     * @param id
     */
    void delete(Long id);
}
