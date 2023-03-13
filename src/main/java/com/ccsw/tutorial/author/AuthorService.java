package com.ccsw.tutorial.author;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ccsw.tutorial.author.model.Author;
import com.ccsw.tutorial.author.model.AuthorDto;
import com.ccsw.tutorial.author.model.AuthorSearchDto;

public interface AuthorService {

    /**
     * Método para recuperar un {@link com.ccsw.tutorial.author.model.Author} a través de su ID
     * @param id
     * @return author
     */
    Author get(Long id);
    
    /**
     * Método para recuperar todos los {@link com.ccsw.tutorial.author.model.Author}
     * @return
     */
    List<Author> findAll();
    
	/**
	 * Método para recuperar un listado paginado de {@link com.ccsw.tutorial.author.model.Author}
	 * @param authorSearchDto
	 * @return
	 */
	Page<Author> findPage(AuthorSearchDto authorSearchDto);

	/**
	 * Método para crear o actualizar un {@link com.ccsw.tutorial.author.model.Author}
	 * @param id
	 * @param dto
	 */
	void save(Long id, AuthorDto dto);

	/**
	 * Método para borrar un {@link com.ccsw.tutorial.author.model.Author}
	 * @param id
	 */
	void delete(Long id);

}
