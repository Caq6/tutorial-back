package com.ccsw.tutorial.game;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.tutorial.config.mapper.BeanMapper;
import com.ccsw.tutorial.game.model.Game;
import com.ccsw.tutorial.game.model.GameDto;

/**
 * 
 * @author caliagaq
 *
 */

@CrossOrigin("*")
@RequestMapping(value = "/game")
@RestController
public class GameController {
    
    @Autowired
    GameService gameService;
    
    @Autowired
    BeanMapper beanMapper;
    
    /**
     * 
     * @param title
     * @param idCategory
     * @return
     */
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<GameDto> find(@RequestParam(value="title", required=false) String title, 
            @RequestParam(value="idCategory", required=false) Long idCategory) {
        
        List<Game> games = this.gameService.find(title, idCategory);
        
        return this.beanMapper.mapList(games, GameDto.class);
    }

    /**
     * 
     * @param id
     * @param dto
     */
    @RequestMapping(path={"", "/{id}"}, method = RequestMethod.PUT)
    public void save(@PathVariable(value="id", required=false) Long id, @RequestBody GameDto dto) {
        this.gameService.save(id, dto);

    }

    
}
