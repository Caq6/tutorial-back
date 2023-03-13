package com.ccsw.tutorial.author;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.tutorial.author.model.AuthorDto;
import com.ccsw.tutorial.author.model.AuthorSearchDto;
import com.ccsw.tutorial.config.mapper.BeanMapper;

/**
 * 
 * @author caliagaq
 *
 */
@CrossOrigin("*")
@RequestMapping(value = "/author")
@RestController
public class AuthorController {
	@Autowired
	AuthorService authorService;
	
	@Autowired
	BeanMapper beanMapper;
	
	/**
	 * Método para recuperar a todos los autores
	 * @return
	 */
	@RequestMapping(path="", method= RequestMethod.GET)
	public List<AuthorDto> findAll(){
	    return this.beanMapper.mapList(this.authorService.findAll(), AuthorDto.class);
	}
	
	/**
	 * Método para recuperar listado paginado
	 * @param authorSearchDto
	 * @return
	 */
	@RequestMapping(path = "", method = RequestMethod.POST)
	public Page<AuthorDto> findPage(@RequestBody AuthorSearchDto authorSearchDto){
		return this.beanMapper.mapPage(this.authorService.findPage(authorSearchDto), AuthorDto.class);
	}
	
	/**
	 * Método para crear o actualizar un autor
	 * @param id
	 * @param dto
	 */
	@RequestMapping(path = {"", "/{id}"}, method = RequestMethod.PUT)
	public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody AuthorDto dto) {
		this.authorService.save(id, dto);
	}
	
	/**
	 * Método para eliminar un autor
	 * @param id
	 */
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {
		this.authorService.delete(id);
	}
}
