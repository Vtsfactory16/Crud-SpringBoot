package org.example.springboot.modelo.dao;

import org.example.springboot.modelo.entidades.EntidadDepartamento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDepartamentoDAO extends CrudRepository<EntidadDepartamento, Integer> {

}
