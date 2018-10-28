package com.example.demo.publicapi;

import com.example.demo.persistence.ClassEntity;
import com.example.demo.persistence.PupilEntity;

import java.util.Collections;
import java.util.List;

public class FindPupilsByClassQuery {

  private ClassEntity classEntity;
  private List<PupilEntity> pupils;

  public FindPupilsByClassQuery(ClassEntity classEntity) {
    this.classEntity = classEntity;
  }

  public ClassEntity getClassEntity() {
    return classEntity;
  }

  public List<PupilEntity> getPupils() {
    return Collections.unmodifiableList(pupils);
  }

  public void setPupils(List<PupilEntity> pupils) {
    this.pupils = pupils;
  }
}
