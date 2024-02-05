package org.example.springboot.controladores;

import org.example.springboot.modelo.dao.ISedeDAO;
import org.example.springboot.modelo.entidades.EntidadSede;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api-rest/sedes")
public class ControladorSede {
    @Autowired
    ISedeDAO sedeDAO;

    @GetMapping
    public List<EntidadSede> buscarSedes() {
        return (List<EntidadSede>) sedeDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntidadSede> buscarSedePorId(@PathVariable(value = "id") int idSede) {
        Optional<EntidadSede> sede = sedeDAO.findById(idSede);
        if (sede.isPresent())
            return ResponseEntity.ok().body(sede.get());
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public EntidadSede guardarSede(@Validated @RequestBody EntidadSede sede) {
        return sedeDAO.save(sede);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarSede(@PathVariable(value = "id") int idSede) {
        Optional<EntidadSede> sede = sedeDAO.findById(idSede);
        if (sede.isPresent()) {
            sedeDAO.deleteById(idSede);
            return ResponseEntity.ok().body("Borrado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> actualizarSede(@Validated@RequestBody EntidadSede nuevaSede, @PathVariable(value = "id") int idSede) {
        Optional<EntidadSede> sede = sedeDAO.findById(idSede);
        if (sede.isPresent()) {
            sede.get().setNomSede(nuevaSede.getNomSede());
            sede.get().setListDepartamentos(nuevaSede.getListDepartamentos());
            sedeDAO.save(sede.get());
            return ResponseEntity.ok().body("Actualizado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
