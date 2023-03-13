package com.ccsw.tutorial.game;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ccsw.tutorial.game.model.Game;
import com.ccsw.tutorial.game.model.GameDto;

/**
 * 
 * @author caliagaq
 *
 */
@Service
public interface GameService {

    /**
     * Recupera los juegos filtrando opcionalmente por título y/o categoría
     * @param title
     * @param category
     * @return
     */
     List<Game> find(String title, Long category);

     /**
     * Guarda o modifica un juego, dependiendo de si el id está o no informado
     * @param id
     * @param dto
     */
     void save(Long id, GameDto dto);
     
     /**
      * Recupera un juego por su id
      * @param id
      * @return
      */
     Game get(Long id);
   

}
