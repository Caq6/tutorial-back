package com.ccsw.tutorial.author;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ccsw.tutorial.author.model.Author;

/**
 * 
 * @author caliagaq
 *
 */
@ExtendWith(MockitoExtension.class)
public class AuthorTest {
    public static final Long EXISTING_AUTHOR_ID = 1L;
    public static final Long NOT_EXISTING_AUTHOR_ID = 0L;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Test
    public void getExistingAuthorIdShouldReturnAuthor() {

       Author author = mock(Author.class);
       when(author.getId()).thenReturn(EXISTING_AUTHOR_ID);
       when(authorRepository.findById(EXISTING_AUTHOR_ID)).thenReturn(Optional.of(author));

       Author authorResponse = authorService.get(EXISTING_AUTHOR_ID);

       assertNotNull(authorResponse);

       assertEquals(EXISTING_AUTHOR_ID, authorResponse.getId());
    }

    @Test
    public void getNotExistingAuthorIdShouldReturnNull() {

       when(authorRepository.findById(NOT_EXISTING_AUTHOR_ID)).thenReturn(Optional.empty());

       Author author = authorService.get(NOT_EXISTING_AUTHOR_ID);

       assertNull(author);
    }

    
}
