package com.mcan.rig.service.impl;

import com.mcan.rig.exception.BookBadCreateRequestException;
import com.mcan.rig.exception.BookNotFoundException;
import com.mcan.rig.exception.BookStockUpdateBadRequestException;
import com.mcan.rig.persistance.entity.Book;
import com.mcan.rig.persistance.repository.BookRepository;
import com.mcan.rig.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl (BookRepository bookRepository) {this.bookRepository = bookRepository;}

    @Override
    public List<Book> findALl () {
        return bookRepository.findAll();
    }

    @Override
    public Book create (Book book) {
        if ((book == null) || (book.getName() == null) || (book.getAuthor() == null) || (book.getStock() == null) || (book.getStock() < 0L) || (book.getPrice() == null) || (book.getPrice() < 0)) {
            throw new BookBadCreateRequestException();
        }
        Book createdBook = bookRepository.save(book);
        return createdBook;
    }

    @Override
    public Book update (Long id, Long stock) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        if (stock == null || stock < 0) {
            throw new BookStockUpdateBadRequestException();
        }
        book.setStock(stock);
        Book updatedBook = bookRepository.save(book);
        return book;
    }

    @Override
    public Book findById (Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }
}
