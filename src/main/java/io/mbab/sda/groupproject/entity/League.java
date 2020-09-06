package io.mbab.sda.groupproject.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Builder(toBuilder = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"uuid"})
public class League {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(length = 128, nullable = false)
  private String name;



  @ManyToOne
  private Country country;

  @OneToMany(mappedBy = "league", cascade = CascadeType.ALL)
  private List<Team> teams;

  @Transient private UUID uuid = UUID.randomUUID();
}
