package com.edicar.carros.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Despesa.
 */
@Entity
@Table(name = "despesa")
public class Despesa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "dt_despesa", nullable = false)
    private LocalDate dtDespesa;

    @NotNull
    @Size(min = 3)
    @Column(name = "nm_despesa", nullable = false)
    private String nmDespesa;

    @NotNull
    @Min(value = 0L)
    @Column(name = "valor", nullable = false)
    private Long valor;

    @ManyToOne
    @JsonIgnoreProperties(value = "despesas", allowSetters = true)
    private Veiculo veiculo;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDtDespesa() {
        return dtDespesa;
    }

    public Despesa dtDespesa(LocalDate dtDespesa) {
        this.dtDespesa = dtDespesa;
        return this;
    }

    public void setDtDespesa(LocalDate dtDespesa) {
        this.dtDespesa = dtDespesa;
    }

    public String getNmDespesa() {
        return nmDespesa;
    }

    public Despesa nmDespesa(String nmDespesa) {
        this.nmDespesa = nmDespesa;
        return this;
    }

    public void setNmDespesa(String nmDespesa) {
        this.nmDespesa = nmDespesa;
    }

    public Long getValor() {
        return valor;
    }

    public Despesa valor(Long valor) {
        this.valor = valor;
        return this;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public Despesa veiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
        return this;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Despesa)) {
            return false;
        }
        return id != null && id.equals(((Despesa) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Despesa{" +
            "id=" + getId() +
            ", dtDespesa='" + getDtDespesa() + "'" +
            ", nmDespesa='" + getNmDespesa() + "'" +
            ", valor=" + getValor() +
            "}";
    }
}
