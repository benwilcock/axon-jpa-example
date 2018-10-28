package com.example.demo.persistence;

import javax.persistence.*;

@Entity
public class PupilEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;

  @ManyToOne
  private ClassEntity classEntity;

  public PupilEntity() {
  }

  public PupilEntity(String name, ClassEntity classEntity) {
    this.name = name;
    this.classEntity = classEntity;
  }

  public Long getId() {
    return id;
  }


  public String getName() {
    return name;
  }


  public ClassEntity getClassEntity() {
    return classEntity;
  }

  @Override
  public String toString() {
    return "Pupil{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", class=" + classEntity +
            '}';
  }
}
