package com.example.demo.persistence;

import org.springframework.data.repository.CrudRepository;

public interface ClassRepository extends CrudRepository<ClassEntity, Long> {

  ClassEntity findByName(String name);
}
