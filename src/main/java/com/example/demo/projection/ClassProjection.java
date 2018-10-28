package com.example.demo.projection;

import com.example.demo.persistence.ClassEntity;
import com.example.demo.persistence.ClassRepository;
import com.example.demo.persistence.PupilEntity;
import com.example.demo.persistence.PupilRepository;
import com.example.demo.publicapi.*;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ClassProjection {

  private static Logger logger = LoggerFactory.getLogger(ClassProjection.class);

  private ClassRepository theClassRepository;
  private PupilRepository thePupilRepository;

  @Autowired
  public ClassProjection(ClassRepository theClassRepository, PupilRepository thePupilRepository) {
    this.theClassRepository = theClassRepository;
    this.thePupilRepository = thePupilRepository;
  }

  @EventHandler
  public void on(SchoolClassCreatedEvent event){
    logger.info("DEBUGGING: Event Handler for {}", SchoolClassCreatedEvent.class);
    ClassEntity theClass = new ClassEntity();
    theClass.setName(event.getClassName());
    theClassRepository.save(theClass);

    for (String name : event.getPupils()) {
      thePupilRepository.save(new PupilEntity(name, theClass));
     }
  }

  @QueryHandler
  public ClassEntity answer(final FindClassByNameQuery query){
    logger.info("DEBUGGING: Handling query {} with class name {}", query.getClass().getSimpleName(), query.getClassName());
    ClassEntity classEntity = theClassRepository.findByName(query.getClassName());
    logger.info("DEBUGGING: Found Class {}", classEntity);
    return classEntity;
  }

  @QueryHandler
  public FindPupilsByClassQuery answer(final FindPupilsByClassQuery query){
    logger.info("DEBUGGING: Handling query {} with class name {}", query.getClass().getSimpleName(), query.getClassEntity().getName());
    List<PupilEntity> pupils = thePupilRepository.findByClassEntity(query.getClassEntity());
    logger.info("DEBUGGING: Found {} class Pupils {}", query.getClassEntity().getName(), Arrays.toString(pupils.toArray()));
    query.setPupils(pupils);
    return query;
  }

  @QueryHandler
  public FindAllPupilsQuery answer(FindAllPupilsQuery query){
    logger.info("DEBUGGING: Handling query {}", query.getClass().getSimpleName());
    Iterable<PupilEntity> pupilEntities = thePupilRepository.findAll();
    List<PupilEntity> pupils = new ArrayList<>();
    pupilEntities.forEach(pupils::add);
    logger.info("DEBUGGING: Found All the Pupils {}", Arrays.toString(pupils.toArray()));
    query.setPupilEntities(pupils);
    return query;
  }

  @QueryHandler
  public FindAllClassesQuery answer(FindAllClassesQuery query){
    logger.info("DEBUGGING: Handling query {}", query.getClass().getSimpleName());
    Iterable<ClassEntity> classEntities = theClassRepository.findAll();
    List<ClassEntity> classes = new ArrayList<>();
    classEntities.forEach(classes::add);
    logger.info("DEBUGGING: Found All the Classes {}", Arrays.toString(classes.toArray()));
    query.setClassEntities(classes);
    return query;
  }
}
