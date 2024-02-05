package org.example.springboot.modelo.dao;

import org.example.springboot.modelo.entidades.EntidadEmpleado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmpleadoDAO extends CrudRepository<EntidadEmpleado, String> {
    EntidadEmpleado findByNomEmpIgnoreCase(String nomEmp);

}
