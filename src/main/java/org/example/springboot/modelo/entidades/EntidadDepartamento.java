package org.example.springboot.modelo.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "departamento", schema = "proyecto_orm")
public class EntidadDepartamento {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_depto", nullable = false)
    private int idDepto;
    @Basic
    @Column(name = "nom_depto", nullable = false, length = 32)
    private String nomDepto;
    @ManyToOne
    @JoinColumn(name = "id_sede", referencedColumnName = "id_sede", nullable = false)
    @JsonIgnoreProperties("listDepartamentos")
    private EntidadSede sede;
    @OneToMany(mappedBy = "departamento")
    @JsonIgnoreProperties("departamento")
    private Collection<EntidadEmpleado> listEmpleados;

    public int getIdDepto() {
        return idDepto;
    }

    public void setIdDepto(int idDepto) {
        this.idDepto = idDepto;
    }

    public String getNomDepto() {
        return nomDepto;
    }

    public void setNomDepto(String nomDepto) {
        this.nomDepto = nomDepto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntidadDepartamento that = (EntidadDepartamento) o;
        return idDepto == that.idDepto && Objects.equals(nomDepto, that.nomDepto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDepto, nomDepto);
    }

    public EntidadSede getSede() {
        return sede;
    }

    public void setSede(EntidadSede sede) {
        this.sede = sede;
    }

    public Collection<EntidadEmpleado> getListEmpleados() {
        return listEmpleados;
    }

    public void setListEmpleados(Collection<EntidadEmpleado> listEmpleados) {
        this.listEmpleados = listEmpleados;
    }
}
