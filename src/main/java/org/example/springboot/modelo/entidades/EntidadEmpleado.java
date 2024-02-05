package org.example.springboot.modelo.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "empleado", schema = "proyecto_orm")
public class EntidadEmpleado {
    @Id
    @Column(name = "dni", nullable = false, length = 9)
    private String dni;
    @Basic
    @NotEmpty(message="El nombre de empleado no puede estar vacío")
    @Size(min = 2, max = 40, message = "El nombre de empleado tiene que tener entre 2 y 40 caracteres")
    @Column(name = "nom_emp", nullable = false, length = 40)
    private String nomEmp;
    @ManyToOne
    @JoinColumn(name = "id_depto", referencedColumnName = "id_depto", nullable = false)
    @JsonIgnoreProperties("listEmpleados")
    private EntidadDepartamento departamento;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNomEmp() {
        return nomEmp;
    }

    public void setNomEmp(String nomEmp) {
        this.nomEmp = nomEmp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntidadEmpleado that = (EntidadEmpleado) o;
        return Objects.equals(dni, that.dni) && Objects.equals(nomEmp, that.nomEmp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni, nomEmp);
    }

    public EntidadDepartamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(EntidadDepartamento departamento) {
        this.departamento = departamento;
    }
}
