package com.example.demo.publicapi;

import java.util.Collections;
import java.util.List;

public class SchoolClassCreatedEvent {

  private String uuid;
  private String name;
  private List<String> pupils;

  public SchoolClassCreatedEvent(String uuid, String name, List<String> pupils) {
    this.uuid = uuid;
    this.name = name;
    this.pupils = pupils;
  }

  public String getUuid() {
    return uuid;
  }

  public List<String> getPupils() {
    return Collections.unmodifiableList(pupils);
  }

  public String getClassName() {
    return name;
  }
}
