package com.mcan.rig.service;

import com.mcan.rig.persistance.entity.Book;
import com.mcan.rig.rest.request.BookCreateRequest;

import java.util.List;

public interface BookService {

    List<Book> findALl ();

    Book create (Book book);

    Book update (Long id, Long stock);

    Book findById (Long id);

}
