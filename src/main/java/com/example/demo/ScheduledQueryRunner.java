package com.example.demo;

import com.example.demo.persistence.ClassEntity;
import com.example.demo.publicapi.FindAllClassesQuery;
import com.example.demo.publicapi.FindAllPupilsQuery;
import com.example.demo.publicapi.FindClassByNameQuery;
import com.example.demo.publicapi.FindPupilsByClassQuery;
import org.axonframework.queryhandling.QueryGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

@Component
@EnableScheduling
public class ScheduledQueryRunner {

  private static Logger logger = LoggerFactory.getLogger(ScheduledQueryRunner.class);
  private QueryGateway queryGateway;

  private static boolean isClassQryRun = false;
  private static boolean isPupilsForClassQryRun = false;
  private static boolean isAllPupilsQryRun = false;
  private static boolean isAllClassesQryRun;

  @Autowired
  public ScheduledQueryRunner(final QueryGateway queryGateway) {
    this.queryGateway = queryGateway;
  }

  @Scheduled(initialDelay = 10000, fixedDelay = 10000)
  public void runQuery() throws ExecutionException, InterruptedException {

    ClassEntity classEntity = runClassQry("Cedar");

    runFindAllClassesQry();

    runPupilsForClassQry(classEntity);

    runFindAllPupilsQry();

  }

  private void runFindAllClassesQry() throws InterruptedException, ExecutionException {
    if (!isAllClassesQryRun) {
      FindAllClassesQuery findAllClassesQuery = queryGateway.query(new FindAllClassesQuery(), FindAllClassesQuery.class).get();
      logger.info("DEBUGGING: FindAllClassesQuery found the classes '{}'", Arrays.toString(findAllClassesQuery.getClassEntities().toArray()));
      this.isAllClassesQryRun = true;
    }
  }

  private void runFindAllPupilsQry() throws InterruptedException, ExecutionException {
    if (!isAllPupilsQryRun) {
      FindAllPupilsQuery findAllPupilsQuery = queryGateway.query(new FindAllPupilsQuery(), FindAllPupilsQuery.class).get();
      logger.info("DEBUGGING: FindAllPupilsQuery found the pupils '{}'", Arrays.toString(findAllPupilsQuery.getPupilEntities().toArray()));
      this.isAllPupilsQryRun = true;
    }
  }

  private void runPupilsForClassQry(ClassEntity classEntity) throws InterruptedException, ExecutionException {
    if (null != classEntity && !isPupilsForClassQryRun) {
      logger.info("DEBUGGING: Looking up the Pupils of class {}", classEntity.getName());
      FindPupilsByClassQuery pupilQuery = new FindPupilsByClassQuery(classEntity);
      FindPupilsByClassQuery pupils = queryGateway.query(pupilQuery, pupilQuery.getClass()).get();
      logger.info("DEBUGGING: FindPupilsByClassQuery found the pupils '{}'", Arrays.toString(pupils.getPupils().toArray()));
      this.isPupilsForClassQryRun = true;
    }
  }

  private ClassEntity runClassQry(String className) throws InterruptedException, ExecutionException {
    if (!isClassQryRun) {
      FindClassByNameQuery query = new FindClassByNameQuery(className);
      logger.info("DEBUGGING: Looking for a Class with the name '{}'", className);
      ClassEntity classEntity = queryGateway.query(query, ClassEntity.class).get();
      this.isClassQryRun = true;
      logger.info("DEBUGGING: FindClassByNameQuery found the class with name '{}'", classEntity.getName());
      return classEntity;
    } else {
      return null;
    }

  }
}
