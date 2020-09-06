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
public class Team {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 128, nullable = false)
  private String name;

  @Column(length = 128, nullable = false)
  private String city;

  @ManyToOne
  private Country country;

  @Column(columnDefinition = "int default 0")
  private double value;

  @ManyToOne
  private League league;

  @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
  private List<Player> players;

  @Transient private UUID uuid = UUID.randomUUID();
}
