package org.example.springboot.modelo.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "sede", schema = "proyecto_orm")
public class EntidadSede {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_sede", nullable = false)
    private int idSede;
    @Basic
    @Column(name = "nom_sede", nullable = false, length = 20)
    private String nomSede;
    @OneToMany(mappedBy = "sede")
    @JsonIgnoreProperties("sede")
    private Collection<EntidadDepartamento> listDepartamentos;

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    public String getNomSede() {
        return nomSede;
    }

    public void setNomSede(String nomSede) {
        this.nomSede = nomSede;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntidadSede that = (EntidadSede) o;
        return idSede == that.idSede && Objects.equals(nomSede, that.nomSede);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSede, nomSede);
    }

    public Collection<EntidadDepartamento> getListDepartamentos() {
        return listDepartamentos;
    }

    public void setListDepartamentos(Collection<EntidadDepartamento> listDepartamentos) {
        this.listDepartamentos = listDepartamentos;
    }
}
