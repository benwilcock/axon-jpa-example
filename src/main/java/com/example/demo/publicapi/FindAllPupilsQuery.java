package com.example.demo.publicapi;

import com.example.demo.persistence.PupilEntity;

import java.util.Collections;
import java.util.List;

public class FindAllPupilsQuery {

  private List<PupilEntity> pupilEntities;

  public List<PupilEntity> getPupilEntities() {
    return Collections.unmodifiableList(pupilEntities);
  }

  public void setPupilEntities(List<PupilEntity> pupilEntities) {
    this.pupilEntities = pupilEntities;
  }
}
