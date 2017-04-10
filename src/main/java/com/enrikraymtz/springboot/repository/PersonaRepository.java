package com.enrikraymtz.springboot.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.enrikraymtz.springboot.model.Persona;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonaRepository extends MongoRepository<Persona, String> {

	List<Persona> findByLastName(@Param("name") String name);

}