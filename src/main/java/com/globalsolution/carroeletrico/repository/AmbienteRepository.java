package com.globalsolution.carroeletrico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globalsolution.carroeletrico.model.Ambiente;

@Repository
public interface AmbienteRepository extends JpaRepository<Ambiente, Long>{

}
