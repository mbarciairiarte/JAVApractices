package com.website.dao;

import org.springframework.data.repository.CrudRepository;

import com.website.domain.Persona;


public interface PersonaDao extends CrudRepository<Persona, Long> {

}
