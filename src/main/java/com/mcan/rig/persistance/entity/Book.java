package com.mcan.rig.persistance.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "BOOK", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "author"}))
@Getter
@Setter
public class Book extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "stock", nullable = false)
    private Long stock;

    @Column(name = "price", nullable = false)
    private Double price;
}
