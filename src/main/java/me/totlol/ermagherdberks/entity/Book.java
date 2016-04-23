package me.totlol.ermagherdberks.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Márton Tóth
 */
@Table(name = "book")
@Entity
@Data
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq_gen")
    @SequenceGenerator(name = "book_seq_gen", sequenceName = "book_id_seq")
    @JsonProperty
    private Long id;
    @Column(name = "title")
    @NotNull
    @JsonProperty
    private String title;
    @NotNull
    @Column(name = "author")
    @JsonProperty
    private String author;
    @Column(name = "isbn")
    @NotNull
    @JsonProperty
    private String ISBN;
    @Column(name = "count")
    @NotNull
    @JsonProperty
    private Integer count;

}
