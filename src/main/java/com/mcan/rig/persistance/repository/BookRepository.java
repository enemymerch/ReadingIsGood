package com.mcan.rig.persistance.repository;

import com.mcan.rig.persistance.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
