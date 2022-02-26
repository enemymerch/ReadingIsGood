package com.mcan.rig.persistance.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "update_date")
    private Date updateDate;

    @PrePersist
    public void prePersistInitialization () {
        this.setCreationDate(new Date());
    }

    @PreUpdate
    public void preUpdateInitialization () {
        this.setUpdateDate(new Date());
    }
}
