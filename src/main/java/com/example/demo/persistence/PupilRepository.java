package com.example.demo.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PupilRepository extends CrudRepository<PupilEntity, Long> {

  List<PupilEntity> findByClassEntity(ClassEntity classEntity);
}
