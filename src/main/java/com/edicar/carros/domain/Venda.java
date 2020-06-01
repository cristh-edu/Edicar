package com.edicar.carros.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Venda.
 */
@Entity
@Table(name = "venda")
public class Venda implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "dt_compra", nullable = false)
    private LocalDate dtCompra;

    @NotNull
    @Min(value = 0L)
    @Column(name = "valor", nullable = false)
    private Long valor;

    @OneToOne
    @JoinColumn(unique = true)
    private Veiculo veiculoVenda;

    @OneToOne
    @JoinColumn(unique = true)
    private Veiculo veiculoTroca;

    @ManyToOne
    @JsonIgnoreProperties(value = "vendas", allowSetters = true)
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

    public Venda dtCompra(LocalDate dtCompra) {
        this.dtCompra = dtCompra;
        return this;
    }

    public void setDtCompra(LocalDate dtCompra) {
        this.dtCompra = dtCompra;
    }

    public Long getValor() {
        return valor;
    }

    public Venda valor(Long valor) {
        this.valor = valor;
        return this;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }

    public Veiculo getVeiculoVenda() {
        return veiculoVenda;
    }

    public Venda veiculoVenda(Veiculo veiculo) {
        this.veiculoVenda = veiculo;
        return this;
    }

    public void setVeiculoVenda(Veiculo veiculo) {
        this.veiculoVenda = veiculo;
    }

    public Veiculo getVeiculoTroca() {
        return veiculoTroca;
    }

    public Venda veiculoTroca(Veiculo veiculo) {
        this.veiculoTroca = veiculo;
        return this;
    }

    public void setVeiculoTroca(Veiculo veiculo) {
        this.veiculoTroca = veiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Venda cliente(Cliente cliente) {
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
        if (!(o instanceof Venda)) {
            return false;
        }
        return id != null && id.equals(((Venda) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Venda{" +
            "id=" + getId() +
            ", dtCompra='" + getDtCompra() + "'" +
            ", valor=" + getValor() +
            "}";
    }
}
