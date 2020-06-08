package com.edicar.carros.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Compra.
 */
@Entity
@Table(name = "compra")
public class Compra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "dt_compra", nullable = false)
    private LocalDate dtCompra;

    @NotNull
    @Column(name = "valor", nullable = false)
    private Long valor;

    @OneToOne
    @JoinColumn(unique = true)
    private Veiculo veiculoCompra;

    @OneToOne
    @JoinColumn(unique = true)
    private Veiculo veiculoTroca;

    @ManyToOne
    @JsonIgnoreProperties(value = "compras", allowSetters = true)
    private Cliente cliente;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDtCompra() {
        return dtCompra;
    }

    public Compra dtCompra(LocalDate dtCompra) {
        this.dtCompra = dtCompra;
        return this;
    }

    public void setDtCompra(LocalDate dtCompra) {
        this.dtCompra = dtCompra;
    }

    public Long getValor() {
        return valor;
    }

    public Compra valor(Long valor) {
        this.valor = valor;
        return this;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }

    public Veiculo getVeiculoCompra() {
        return veiculoCompra;
    }

    public Compra veiculoCompra(Veiculo veiculo) {
        this.veiculoCompra = veiculo;
        return this;
    }

    public void setVeiculoCompra(Veiculo veiculo) {
        this.veiculoCompra = veiculo;
    }

    public Veiculo getVeiculoTroca() {
        return veiculoTroca;
    }

    public Compra veiculoTroca(Veiculo veiculo) {
        this.veiculoTroca = veiculo;
        return this;
    }

    public void setVeiculoTroca(Veiculo veiculo) {
        this.veiculoTroca = veiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Compra cliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Compra)) {
            return false;
        }
        return id != null && id.equals(((Compra) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Compra{" +
            "id=" + getId() +
            ", dtCompra='" + getDtCompra() + "'" +
            ", valor=" + getValor() +
            "}";
    }
}
