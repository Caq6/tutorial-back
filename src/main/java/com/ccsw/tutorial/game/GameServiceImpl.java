package com.ccsw.tutorial.game;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.tutorial.author.AuthorService;
import com.ccsw.tutorial.category.CategoryService;
import com.ccsw.tutorial.game.model.Game;
import com.ccsw.tutorial.game.model.GameDto;
/**
 * 
 * @author caliagaq
 *
 */
@Service
@Transactional
public class GameServiceImpl implements GameService {

    @Autowired
    GameRepository gameRepository;
    
    @Autowired
    AuthorService authorService;

    @Autowired
    CategoryService categoryService;
    
    @Override
    public List<Game> find(String title, Long category) {
        return this.gameRepository.find(title, category);
        
    }

    @Override
    public void save(Long id, GameDto dto) {
        Game game = null;
        if(id != null) {
            game = this.gameRepository.findById(id).orElse(null);
        }
        else {
            game = new Game();
        }
        
        BeanUtils.copyProperties(dto, game, "id", "author", "category");    //Se ignoran porque solo contienen los ids y queremos las entidades
        
        game.setAuthor(authorService.get(dto.getAuthor().getId()));
        game.setCategory(categoryService.get(dto.getCategory().getId()));
        
        this.gameRepository.save(game);
        
    }

    @Override
    public Game get(Long id) {
        return this.gameRepository.findById(id).orElse(null);
    }

}
