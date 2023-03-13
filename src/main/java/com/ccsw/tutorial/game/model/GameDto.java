package com.ccsw.tutorial.game.model;

import com.ccsw.tutorial.author.model.AuthorDto;
import com.ccsw.tutorial.category.model.CategoryDto;

/**
 * 
 * @author caliagaq
 *
 */
public class GameDto {

    private Long id;

    private String title;

    private String age;

    private CategoryDto category;

    private AuthorDto author;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id new value of {@link #getId}.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title new value of {@link #getTitle}.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the age
     */
    public String getAge() {
        return age;
    }

    /**
     * @param age new value of {@link #getAge}.
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * @return the category
     */
    public CategoryDto getCategory() {
        return category;
    }

    /**
     * @param category new value of {@link #getCategory}.
     */
    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    /**
     * @return the author
     */
    public AuthorDto getAuthor() {
        return author;
    }

    /**
     * @param author new value of {@link #getAuthor}.
     */
    public void setAuthor(AuthorDto author) {
        this.author = author;
    }
    
    
}
