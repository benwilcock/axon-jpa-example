package com.example.demo.publicapi;


import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreateSchoolClassCommand {

  @TargetAggregateIdentifier
  private final String uuid;
  private final String className;
  private List<String> pupils;

  public CreateSchoolClassCommand(String uuid, String className) {
    this.uuid = uuid;
    this.className = className;
    pupils = new ArrayList<String>();
  }

  public String getUuid() {
    return uuid;
  }

  public String getClassName() {
    return className;
  }

  public List<String> getPupils() {
    return Collections.unmodifiableList(pupils);
  }

  public void addPupil(String pupil) {
    this.pupils.add(pupil);
  }
}
