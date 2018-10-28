package com.example.demo;

import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SchoolDemoApplication {

  private static Logger logger = LoggerFactory
          .getLogger(SchoolDemoApplication.class);

  @Bean
  public EventSourcingRepository<ClassAggregate> getEventSourcingRepository(EmbeddedEventStore store) {
    return EventSourcingRepository.builder(ClassAggregate.class).eventStore(store).build();
  }

  public static void main(String[] args) {
    logger.info("STARTING THE APPLICATION");
    SpringApplication.run(SchoolDemoApplication.class, args);
    logger.info("APPLICATION FINISHED");
  }

}
