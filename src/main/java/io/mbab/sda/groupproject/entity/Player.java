package io.mbab.sda.groupproject.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
  private String dateOfBirth;



  @ManyToOne
  private Country country;

  @ManyToOne
  private Team team;

  @Transient private UUID uuid = UUID.randomUUID();
}
