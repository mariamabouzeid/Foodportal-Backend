package com.sumerge.foodportal.entity;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "SIZE", schema = "FOODPORTALDB")
public class Size implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @Column(name = "NAME")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}