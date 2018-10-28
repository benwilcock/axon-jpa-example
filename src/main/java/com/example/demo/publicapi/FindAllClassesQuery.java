package com.example.demo.publicapi;

import com.example.demo.persistence.ClassEntity;

import java.util.Collections;
import java.util.List;

public class FindAllClassesQuery {

  private List<ClassEntity> classEntities;

  public List<ClassEntity> getClassEntities() {
    return Collections.unmodifiableList(classEntities);
  }

  public void setClassEntities(List<ClassEntity> classEntities) {
    this.classEntities = classEntities;
  }
}
