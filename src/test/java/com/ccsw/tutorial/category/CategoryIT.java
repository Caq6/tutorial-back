package com.ccsw.tutorial.category;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.ccsw.tutorial.category.model.CategoryDto;

/**
 * 
 * @author caliagaq
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CategoryIT {

	public static final String LOCALHOST = "http://localhost:";
	public static final String SERVICE_PATH = "/category/";
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
    ParameterizedTypeReference<List<CategoryDto>> responseType = new ParameterizedTypeReference<List<CategoryDto>>(){};

    public static final String NEW_CATEGORY_NAME = "CATEGORY4";
    public static final Long NEW_CATEGORY_ID = 4L;
    public static final Long EXISTING_CATEGORY_ID = 2L;
	private static final Long NOT_EXISTING_CATEGORY_ID = 100L;
    
    @Test
    public void findAllShouldReturnAllCategories() {
    	ResponseEntity<List<CategoryDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET, null, responseType);
    	
    	assertNotNull(response);
    	assertEquals(3, response.getBody().size());	
    }
    
    @Test
    public void saveWithoutCategoryIdShouldCreateNewCategory() {
    	CategoryDto categoryDto = new CategoryDto();
    	categoryDto.setName(NEW_CATEGORY_NAME);
    	
    	restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, new HttpEntity<>(categoryDto), Void.class);
    	
    	ResponseEntity<List<CategoryDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET, null, responseType);
    	assertNotNull(response);
    	assertEquals(4, response.getBody().size());
    	
    	CategoryDto categorySearch = response.getBody().stream().filter(item -> item.getId().equals(NEW_CATEGORY_ID)).findFirst().orElse(null);
    	assertNotNull(categorySearch);
    	assertEquals(NEW_CATEGORY_NAME, categorySearch.getName());
    }
    
    @Test
    public void saveExistingCategoryIdShouldUpdateCategory() {
    	CategoryDto categoryDto = new CategoryDto();
    	categoryDto.setName(NEW_CATEGORY_NAME);
    	
    	restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + EXISTING_CATEGORY_ID, HttpMethod.PUT, new HttpEntity<>(categoryDto), Void.class);
    	
    	ResponseEntity<List<CategoryDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET, null, responseType);
    	assertNotNull(response);
    	assertEquals(3, response.getBody().size());
    	
    	CategoryDto categorySearch = response.getBody().stream().filter(item -> item.getId().equals(EXISTING_CATEGORY_ID)).findFirst().orElse(null);
    	assertNotNull(categorySearch);
    	assertEquals(NEW_CATEGORY_NAME, categorySearch.getName());
    }
    
    @Test
    public void modifyNotExistingCategoryIdShouldInternalError() {
    	CategoryDto categoryDto = new CategoryDto();
    	categoryDto.setName(NEW_CATEGORY_NAME);
    	
    	ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + NOT_EXISTING_CATEGORY_ID, HttpMethod.PUT, new HttpEntity<>(categoryDto), Void.class);
    	
    	assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
    
    @Test
    public void deleteExistingCategoryIdShouldDeleteCategory() {    	
        ResponseEntity<?> responseDelete = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + EXISTING_CATEGORY_ID, HttpMethod.DELETE, null, Void.class);
        assertEquals(HttpStatus.OK, responseDelete.getStatusCode());

    	ResponseEntity<List<CategoryDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET, null, responseType);
    	assertNotNull(response);
    	assertEquals(2, response.getBody().size());
    	
    	CategoryDto categorySearch = response.getBody().stream().filter(item -> item.getId().equals(EXISTING_CATEGORY_ID)).findFirst().orElse(null);
    	assertNull(categorySearch);
    }
    
    @Test
    public void deleteNotExistingCategoryIdShouldInternalError() {
        ResponseEntity<?>response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + NOT_EXISTING_CATEGORY_ID, HttpMethod.DELETE, null, Void.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
    
    
}
