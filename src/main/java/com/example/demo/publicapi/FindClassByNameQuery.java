package com.example.demo.publicapi;

public class FindClassByNameQuery {

  private final String className;

  public FindClassByNameQuery(String className) {
    this.className = className;
  }

  public String getClassName() {
    return className;
  }
}
