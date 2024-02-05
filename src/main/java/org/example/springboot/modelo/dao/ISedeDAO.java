package org.example.springboot.modelo.dao;

import org.example.springboot.modelo.entidades.EntidadSede;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISedeDAO extends CrudRepository<EntidadSede, Integer> {

}
