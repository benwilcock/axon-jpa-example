package com.example.demo;

import com.example.demo.publicapi.CreateSchoolClassCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Profile("!test")
public class CliRunner implements CommandLineRunner {

  private static Logger logger = LoggerFactory.getLogger(SchoolDemoApplication.class);

  private CommandGateway commandGateway;

  @Autowired
  public CliRunner(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
    logger.info("DEBUGGING: Command Gateway Set");
  }

  @Override
  public void run(String... args) throws Exception {
    logger.info("DEBUGGING: Command Line Runner is Running");

    if(args.length == 0){
      return;
    }

    String className = args[0];
    logger.info("DEBUGGING: New school class is '{}'", className);
    CreateSchoolClassCommand command = new CreateSchoolClassCommand(UUID.randomUUID().toString(), className);

    for (int i = 1; i < args.length; ++i) {
      logger.info("DEBUGGING: Adding pupil '{}' to class '{}'", args[i], className);
      command.addPupil(args[i]);
    }

    logger.info("DEBUGGING: Sending command '{}' on command bus", CreateSchoolClassCommand.class.getSimpleName());
    this.commandGateway.send(command);
  }
}
