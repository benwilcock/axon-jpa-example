package com.example.demo;

import com.example.demo.publicapi.CreateSchoolClassCommand;
import com.example.demo.publicapi.SchoolClassCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class ClassAggregate {

  private static Logger logger = LoggerFactory.getLogger(ClassAggregate.class);

  @AggregateIdentifier
  private String uuid;
  private List<String> names;

  public ClassAggregate() {}

  @CommandHandler
  public ClassAggregate(CreateSchoolClassCommand command) {
    logger.info("DEBUGGING: Command Handler for {}", CreateSchoolClassCommand.class.getSimpleName());
    apply(new SchoolClassCreatedEvent(command.getUuid(), command.getClassName(), command.getPupils()));
  }

  @EventSourcingHandler
  public void on(SchoolClassCreatedEvent event){
    logger.info("DEBUGGING: Event Sourcing Handler for {}", SchoolClassCreatedEvent.class.getSimpleName());
    this.uuid = event.getUuid();
    this.names = event.getPupils();
  }

}
