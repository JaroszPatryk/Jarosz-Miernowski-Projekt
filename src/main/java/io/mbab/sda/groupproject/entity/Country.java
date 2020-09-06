package io.mbab.sda.groupproject.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"name"})
public class Country {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 64, nullable = false)
  private String name;
}
