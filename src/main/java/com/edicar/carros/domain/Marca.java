package com.edicar.carros.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Marca.
 */
@Entity
@Table(name = "marca")
public class Marca implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3)
    @Column(name = "nm_marca", nullable = false)
    private String nmMarca;

    @OneToMany(mappedBy = "marca")
    private Set<Modelo> modelos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNmMarca() {
        return nmMarca;
    }

    public Marca nmMarca(String nmMarca) {
        this.nmMarca = nmMarca;
        return this;
    }

    public void setNmMarca(String nmMarca) {
        this.nmMarca = nmMarca;
    }

    public Set<Modelo> getModelos() {
        return modelos;
    }

    public Marca modelos(Set<Modelo> modelos) {
        this.modelos = modelos;
        return this;
    }

    public Marca addModelo(Modelo modelo) {
        this.modelos.add(modelo);
        modelo.setMarca(this);
        return this;
    }

    public Marca removeModelo(Modelo modelo) {
        this.modelos.remove(modelo);
        modelo.setMarca(null);
        return this;
    }

    public void setModelos(Set<Modelo> modelos) {
        this.modelos = modelos;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Marca)) {
            return false;
        }
        return id != null && id.equals(((Marca) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Marca{" +
            "id=" + getId() +
            ", nmMarca='" + getNmMarca() + "'" +
            "}";
    }
}
