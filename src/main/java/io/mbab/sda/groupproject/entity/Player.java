package io.mbab.sda.groupproject.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Player implements CrudEntites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 64, nullable = false)
    private String firstName;

    @Column(length = 64, nullable = false)
    private String lastName;

    @Column(length = 15, nullable = false)
    private String dateOfBirth;

    @ManyToOne
    private Country country;

    @ManyToOne
    private Team team;

    @Transient
    private UUID uuid = UUID.randomUUID();

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public String toString() {
        String teamName;
        if (team == null) {
            teamName = "BRAK";
        } else {
            teamName = team.getName();
        }
        return "Player{"
        + "id="
        + id
        + ", firstName='"
        + firstName
        + '\''
        + ", lastName='"
        + lastName
        + '\''
        + ", dateOfBirth='"
        + dateOfBirth
        + '\''
        + ", country="
        + country.getName()
        + ", team="
        + teamName
        + '}';
  }
}
