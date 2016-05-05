package me.totlol.ermagherdberks.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by Márton Tóth
 */
@Entity
@Data
@Table(name = "borrowing")
public class Borrowing {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "borrowing_seq_gen")
    @SequenceGenerator(name = "borrowing_seq_gen", sequenceName = "borrowing_id_seq")
    @JsonProperty
    private Long id;
    @ManyToOne(optional=false)
    @JoinColumn(name="member_id")
    @JsonProperty
    private Member member;
    @ManyToOne(optional=false)
    @JoinColumn(name="book_id")
    @JsonProperty
    private Book book;
    @Column(name = "time_of_borrowing")
    @JsonProperty
    private Long timeOfBorrowing;
    @Column(name = "deadline")
    @JsonProperty
    private Long deadline;

}
