package com.mcan.rig.service;

import com.mcan.rig.persistance.entity.Book;
import com.mcan.rig.persistance.repository.BookRepository;
import com.mcan.rig.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookServiceImpl bookService;
    @Mock
    private BookRepository bookRepository;

    private Book book;

    @BeforeEach
    public void initObjects () {
        // book
        book = new Book();
        book.setId(141552L);
        book.setStock(100L);
        book.setAuthor("JRR Tolkien");
        book.setName("Unexpected Journey");
        book.setPrice(210.20);
        book.setCreationDate(new Date());
    }

    @Test
    void create () {
        Mockito.when(bookRepository.save(book)).thenReturn(book);
        Book result = bookService.create(book);
        assertEquals(result, book);
    }

}
