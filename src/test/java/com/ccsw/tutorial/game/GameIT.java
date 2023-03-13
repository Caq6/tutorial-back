package com.ccsw.tutorial.game;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.util.UriComponentsBuilder;

import com.ccsw.tutorial.author.model.AuthorDto;
import com.ccsw.tutorial.category.model.CategoryDto;
import com.ccsw.tutorial.game.model.GameDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class GameIT {
    public static final String LOCALHOST = "http://localhost:";
    public static final String SERVICE_PATH = "/game/";

    public static final Long EXISTING_GAME_ID = 1L;
    public static final Long NOT_EXISTING_GAME_ID = 0L;
    private static final String NOT_EXISTING_TITLE = "NotExists";
    private static final String EXISTING_TITLE = "Aventureros";
    private static final String NEW_TITLE = "Nuevo juego";
    private static final Long NOT_EXISTING_CATEGORY = 0L;
    private static final Long EXISTING_CATEGORY = 3L;

    private static final String TITLE_PARAM = "title";
    private static final String CATEGORY_ID_PARAM = "idCategory";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    ParameterizedTypeReference<List<GameDto>> responseType = new ParameterizedTypeReference<List<GameDto>>(){};

    private String getUrlWithParams(){
        return UriComponentsBuilder.fromHttpUrl(LOCALHOST + port + SERVICE_PATH)
        .queryParam(TITLE_PARAM, "{" + TITLE_PARAM +"}")
        .queryParam(CATEGORY_ID_PARAM, "{" + CATEGORY_ID_PARAM +"}")
        .encode()
        .toUriString();
    }

    @Test
    public void findWithoutFiltersShouldReturnAllGamesInDB() {

          int GAMES_WITH_FILTER = 6;

          Map<String, Object> params = new HashMap<>();
          params.put(TITLE_PARAM, null);
          params.put(CATEGORY_ID_PARAM, null);

          ResponseEntity<List<GameDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null, responseType, params);

          assertNotNull(response);
          assertEquals(GAMES_WITH_FILTER, response.getBody().size());
    }

    @Test
    public void findExistingTitleShouldReturnGames() {

          int GAMES_WITH_FILTER = 1;

          Map<String, Object> params = new HashMap<>();
          params.put(TITLE_PARAM, EXISTING_TITLE);
          params.put(CATEGORY_ID_PARAM, null);

          ResponseEntity<List<GameDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null, responseType, params);

          assertNotNull(response);
          assertEquals(GAMES_WITH_FILTER, response.getBody().size());
    }

    @Test
    public void findExistingCategoryShouldReturnGames() {

          int GAMES_WITH_FILTER = 2;

          Map<String, Object> params = new HashMap<>();
          params.put(TITLE_PARAM, null);
          params.put(CATEGORY_ID_PARAM, EXISTING_CATEGORY);

          ResponseEntity<List<GameDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null, responseType, params);

          assertNotNull(response);
          assertEquals(GAMES_WITH_FILTER, response.getBody().size());
    }

    @Test
    public void findExistingTitleAndCategoryShouldReturnGames() {

          int GAMES_WITH_FILTER = 1;

          Map<String, Object> params = new HashMap<>();
          params.put(TITLE_PARAM, EXISTING_TITLE);
          params.put(CATEGORY_ID_PARAM, EXISTING_CATEGORY);

          ResponseEntity<List<GameDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null, responseType, params);

          assertNotNull(response);
          assertEquals(GAMES_WITH_FILTER, response.getBody().size());
    }

    @Test
    public void findNotExistingTitleShouldReturnEmpty() {

          int GAMES_WITH_FILTER = 0;

          Map<String, Object> params = new HashMap<>();
          params.put(TITLE_PARAM, NOT_EXISTING_TITLE);
          params.put(CATEGORY_ID_PARAM, null);

          ResponseEntity<List<GameDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null, responseType, params);

          assertNotNull(response);
          assertEquals(GAMES_WITH_FILTER, response.getBody().size());
    }

    @Test
    public void findNotExistingCategoryShouldReturnEmpty() {

          int GAMES_WITH_FILTER = 0;

          Map<String, Object> params = new HashMap<>();
          params.put(TITLE_PARAM, null);
          params.put(CATEGORY_ID_PARAM, NOT_EXISTING_CATEGORY);

          ResponseEntity<List<GameDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null, responseType, params);

          assertNotNull(response);
          assertEquals(GAMES_WITH_FILTER, response.getBody().size());
    }

    @Test
    public void findNotExistingTitleOrCategoryShouldReturnEmpty() {

          int GAMES_WITH_FILTER = 0;

          Map<String, Object> params = new HashMap<>();
          params.put(TITLE_PARAM, NOT_EXISTING_TITLE);
          params.put(CATEGORY_ID_PARAM, NOT_EXISTING_CATEGORY);

          ResponseEntity<List<GameDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null, responseType, params);
          assertNotNull(response);
          assertEquals(GAMES_WITH_FILTER, response.getBody().size());

          params.put(TITLE_PARAM, NOT_EXISTING_TITLE);
          params.put(CATEGORY_ID_PARAM, EXISTING_CATEGORY);

          response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null, responseType, params);
          assertNotNull(response);
          assertEquals(GAMES_WITH_FILTER, response.getBody().size());

          params.put(TITLE_PARAM, EXISTING_TITLE);
          params.put(CATEGORY_ID_PARAM, NOT_EXISTING_CATEGORY);

          response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null, responseType, params);
          assertNotNull(response);
          assertEquals(GAMES_WITH_FILTER, response.getBody().size());
    }

    @Test
    public void saveWithoutIdShouldCreateNewGame() {

          GameDto dto = new GameDto();
          AuthorDto authorDto = new AuthorDto();
          authorDto.setId(1L);

          CategoryDto categoryDto = new CategoryDto();
          categoryDto.setId(1L);

          dto.setTitle(NEW_TITLE);
          dto.setAge("18");
          dto.setAuthor(authorDto);
          dto.setCategory(categoryDto);

          Map<String, Object> params = new HashMap<>();
          params.put(TITLE_PARAM, NEW_TITLE);
          params.put(CATEGORY_ID_PARAM, null);

          ResponseEntity<List<GameDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null, responseType, params);

          assertNotNull(response);
          assertEquals(0, response.getBody().size());

          restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

          response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null, responseType, params);

          assertNotNull(response);
          assertEquals(1, response.getBody().size());
    }

    @Test
    public void modifyWithExistingIdShouldModifyGame() {

          GameDto dto = new GameDto();
          AuthorDto authorDto = new AuthorDto();
          authorDto.setId(1L);

          CategoryDto categoryDto = new CategoryDto();
          categoryDto.setId(1L);

          dto.setTitle(NEW_TITLE);
          dto.setAge("18");
          dto.setAuthor(authorDto);
          dto.setCategory(categoryDto);

          Map<String, Object> params = new HashMap<>();
          params.put(TITLE_PARAM, NEW_TITLE);
          params.put(CATEGORY_ID_PARAM, null);

          ResponseEntity<List<GameDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null, responseType, params);

          assertNotNull(response);
          assertEquals(0, response.getBody().size());

          restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + EXISTING_GAME_ID, HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

          response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null, responseType, params);

          assertNotNull(response);
          assertEquals(1, response.getBody().size());
          assertEquals(EXISTING_GAME_ID, response.getBody().get(0).getId());
    }

    @Test
    public void modifyWithNotExistIdShouldThrowException() {

          GameDto dto = new GameDto();
          dto.setTitle(NEW_TITLE);

          ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + NOT_EXISTING_GAME_ID, HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

          assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
    
}
