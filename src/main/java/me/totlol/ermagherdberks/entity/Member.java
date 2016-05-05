package me.totlol.ermagherdberks.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by Márton Tóth
 */
@Data
@Table(name = "member", uniqueConstraints = {
    @UniqueConstraint(name = "unique_email", columnNames = "email")
})
@Entity
public class Member {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_gen")
    @SequenceGenerator(name = "member_seq_gen", sequenceName = "member_id_seq")
    @JsonProperty
    private Long id;
    @Column(name = "email")
    @JsonProperty
    private String email;
    @Column(name = "password")
    @JsonProperty
    private String password;

}
