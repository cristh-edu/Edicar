package com.edicar.carros.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Modelo.
 */
@Entity
@Table(name = "modelo")
public class Modelo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3)
    @Column(name = "nm_modelo", nullable = false)
    private String nmModelo;

    @OneToMany(mappedBy = "modelo")
    private Set<Veiculo> veiculos = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "modelos", allowSetters = true)
    private Marca marca;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNmModelo() {
        return nmModelo;
    }

    public Modelo nmModelo(String nmModelo) {
        this.nmModelo = nmModelo;
        return this;
    }

    public void setNmModelo(String nmModelo) {
        this.nmModelo = nmModelo;
    }

    public Set<Veiculo> getVeiculos() {
        return veiculos;
    }

    public Modelo veiculos(Set<Veiculo> veiculos) {
        this.veiculos = veiculos;
        return this;
    }

    public Modelo addVeiculo(Veiculo veiculo) {
        this.veiculos.add(veiculo);
        veiculo.setModelo(this);
        return this;
    }

    public Modelo removeVeiculo(Veiculo veiculo) {
        this.veiculos.remove(veiculo);
        veiculo.setModelo(null);
        return this;
    }

    public void setVeiculos(Set<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    public Marca getMarca() {
        return marca;
    }

    public Modelo marca(Marca marca) {
        this.marca = marca;
        return this;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Modelo)) {
            return false;
        }
        return id != null && id.equals(((Modelo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Modelo{" +
            "id=" + getId() +
            ", nmModelo='" + getNmModelo() + "'" +
            "}";
    }
}
