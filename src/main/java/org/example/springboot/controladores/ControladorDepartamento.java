package org.example.springboot.controladores;

import org.example.springboot.modelo.dao.IDepartamentoDAO;
import org.example.springboot.modelo.entidades.EntidadDepartamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api-rest/departamentos")
public class ControladorDepartamento {
    @Autowired
    IDepartamentoDAO departamentoDAO;

    @GetMapping
    public List<EntidadDepartamento> buscarDepartamentos() {
        return (List<EntidadDepartamento>) departamentoDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntidadDepartamento> buscarDepartamentoPorId(@PathVariable(value = "id") int idDepto) {
        Optional<EntidadDepartamento> departamento = departamentoDAO.findById(idDepto);
        if (departamento.isPresent())
            return ResponseEntity.ok().body(departamento.get());
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public EntidadDepartamento guardarDepartamento(@Validated @RequestBody EntidadDepartamento departamento) {
        return departamentoDAO.save(departamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarDepartamento(@PathVariable(value = "id") int idDepto) {
        Optional<EntidadDepartamento> departamento = departamentoDAO.findById(idDepto);
        if (departamento.isPresent()) {
            departamentoDAO.deleteById(idDepto);
            return ResponseEntity.ok().body("Borrado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarDepartamento(@Validated@RequestBody EntidadDepartamento nuevoDepartamento, @PathVariable(value = "id") int idDepto) {
        Optional<EntidadDepartamento> departamento = departamentoDAO.findById(idDepto);
        if (departamento.isPresent()) {
            departamento.get().setNomDepto(nuevoDepartamento.getNomDepto());
            departamento.get().setSede(nuevoDepartamento.getSede());
            departamento.get().setListEmpleados(nuevoDepartamento.getListEmpleados());
            departamentoDAO.save(departamento.get());
            return ResponseEntity.ok().body("Actualizado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
