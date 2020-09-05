package io.mbab.sda.groupproject.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 64, nullable = false)
    private String firstName;


    @Column(length = 64, nullable = false)
    private String lastName;

    @Column(length = 15, nullable = false)
    private int dateOfBirth;
//
//    @Column(length = 64, nullable = false)
//    private Country country;

    @Column(length = 64, nullable = false)
    private Team team;

}
