package com.ccsw.tutorial.game.model;

import javax.persistence.*;

import com.ccsw.tutorial.author.model.Author;
import com.ccsw.tutorial.category.model.Category;

/**
 * 
 * @author caliagaq
 *
 */
@Entity
@Table(name="Game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;
    
    @Column(name="title", nullable = false)
    private String title;
    
    @Column(name="age", nullable = false)
    private String age;
    
    @ManyToOne
    @JoinColumn(name="category_id", nullable = false)
    private Category category;
    
    @ManyToOne
    @JoinColumn(name="author_id", nullable = false)
    private Author author;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id new value of {@link #getid}.
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
     * @param title new value of {@link #gettitle}.
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
     * @param age new value of {@link #getage}.
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param category new value of {@link #getcategory}.
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * @return the author
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * @param author new value of {@link #getauthor}.
     */
    public void setAuthor(Author author) {
        this.author = author;
    }
 
}
