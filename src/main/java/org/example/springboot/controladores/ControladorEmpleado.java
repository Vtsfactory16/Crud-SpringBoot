package org.example.springboot.controladores;

import org.example.springboot.modelo.dao.IDepartamentoDAO;
import org.example.springboot.modelo.dao.IEmpleadoDAO;
import org.example.springboot.modelo.dto.EmpleadoDTO;
import org.example.springboot.modelo.entidades.EntidadEmpleado;
import org.example.springboot.modelo.entidades.EntidadDepartamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api-rest/empleados")
public class ControladorEmpleado {
    @Autowired //inyección de dependencia: por tanto, será inicializado
    IEmpleadoDAO empleadosDAO; //creando Spring automáticamente su instancia

    @Autowired //inyección de dependencia: por tanto, será inicializado
    IDepartamentoDAO departamentoDAO;
    // Tipo de solicitud HTTP --> GET
    @GetMapping //endpoint para buscar todos
    public List<EntidadEmpleado> buscarEmpleados() {
        return (List<EntidadEmpleado>) empleadosDAO.findAll();
    }

    @GetMapping("/{id}") //endpoint para buscar un empleado por dni
    public ResponseEntity<EntidadEmpleado> buscarEmpleadoPorId(@PathVariable(value = "id") String dni) {
        Optional<EntidadEmpleado> empleado = empleadosDAO.findById(dni);
        if (empleado.isPresent())
            return ResponseEntity.ok().body(empleado.get());// HTTP 200 OK
        else return ResponseEntity.notFound().build();      // HTTP 404
    }

    // Tipo de solicitud HTTP --> GET
    @GetMapping("/dto/{id}") //endpoint para buscar un empleado por dni y cargar la info en un DTO
    public ResponseEntity<EmpleadoDTO> buscarEmpleadoDTOPorId(@PathVariable(value = "id") String dni) {
        Optional<EntidadEmpleado> empleado = empleadosDAO.findById(dni);
        if (empleado.isPresent()) {
            EmpleadoDTO empleadoDTO = new EmpleadoDTO();
            empleadoDTO.setDni(empleado.get().getDni());
            empleadoDTO.setNomEmp(empleado.get().getNomEmp());
            empleadoDTO.setIdDepto(empleado.get().getDepartamento().getIdDepto());
            empleadoDTO.setNomDepto(empleado.get().getDepartamento().getNomDepto());
            return ResponseEntity.ok().body(empleadoDTO);// HTTP 200 OK
        } else return ResponseEntity.notFound().build();      // HTTP 404
    }
    // Tipo de solicitud HTTP --> GET
    @GetMapping("/dto-mapper/{id}") //igual que la anterior pero con mapper
    public ResponseEntity<EmpleadoDTO> buscarEmpleadoDTOMapperPorId(@PathVariable(value = "id") String dni) {
        Optional<EntidadEmpleado> empleado = empleadosDAO.findById(dni);
        if (empleado.isPresent()) {
            int idDepto=empleado.get().getDepartamento().getIdDepto();
            ModelMapper mapper = new ModelMapper();
            EmpleadoDTO empleadoDTO = mapper.map(empleado.get(), EmpleadoDTO.class);
            Optional<EntidadDepartamento> departamento = departamentoDAO.findById(idDepto);
            mapper.map(departamento.get(), empleadoDTO);
            return ResponseEntity.ok().body(empleadoDTO);// HTTP 200 OK
        } else return ResponseEntity.notFound().build();      // HTTP 404
    }
    // Tipo de solicitud HTTP --> POST
    @PostMapping//endpoint para añadir un nuevo empleado
    public EntidadEmpleado guardarEmpleado(@Validated @RequestBody EntidadEmpleado empleado) {
        return empleadosDAO.save(empleado);// da error si ya existe
    }

    // Tipo de solicitud HTTP --> DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarEmpleado(@PathVariable(value = "id") String dni) {
        Optional<EntidadEmpleado> empleado = empleadosDAO.findById(dni);
        if (empleado.isPresent()) {
            empleadosDAO.deleteById(dni);
            return ResponseEntity.ok().body("Borrado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Tipo de solicitud HTTP --> PUT
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEmpleado(@RequestBody EntidadEmpleado nuevoEmpleado, @PathVariable(value = "id") String dni) {
        Optional<EntidadEmpleado> empleado = empleadosDAO.findById(dni);
        if (empleado.isPresent()) {
            empleado.get().setNomEmp(nuevoEmpleado.getNomEmp());
            empleado.get().setDepartamento(nuevoEmpleado.getDepartamento());
            empleadosDAO.save(empleado.get());
            return ResponseEntity.ok().body("Actualizado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}