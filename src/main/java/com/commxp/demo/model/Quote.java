package com.commxp.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "QUOTES")
public class Quote {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Long id;

    @Column(name = "QUOTE")
    private String quote;

    @Column(name = "AUTHOR")
    private String author;

    public Quote( String quote, String author) {

        this.quote = quote;
        this.author = author;
    }
}
