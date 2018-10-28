package com.example.demo;

import com.example.demo.publicapi.CreateSchoolClassCommand;
import com.example.demo.publicapi.SchoolClassCreatedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ClassAggregateTest {


  private AggregateTestFixture<ClassAggregate> fixture;
  private String AGGREGATE_ID;
  private String CLASS_NAME;

  @Before
  public void setUp() {
    fixture = new AggregateTestFixture<>(ClassAggregate.class);
    AGGREGATE_ID = UUID.randomUUID().toString();
    CLASS_NAME = "Reception Class";
  }

  @Test
  public void testAggregate() {
    CreateSchoolClassCommand command = new CreateSchoolClassCommand(AGGREGATE_ID, CLASS_NAME);
    List<String> names = Arrays.asList("Ben");
    command.addPupil("Ben");
    fixture.given()
            .when(command)
            .expectEvents(new SchoolClassCreatedEvent(AGGREGATE_ID, CLASS_NAME, names));
  }
}
