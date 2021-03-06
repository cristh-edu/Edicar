package com.edicar.carros.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Veiculo.
 */
@Entity
@Table(name = "veiculo")
public class Veiculo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 8, max = 8)
    @Column(name = "placa", length = 8, nullable = false)
    private String placa;

    @NotNull
    @Size(min = 3)
    @Column(name = "cor", nullable = false)
    private String cor;

    @NotNull
    @Min(value = 1900)
    @Max(value = 2100)
    @Column(name = "ano", nullable = false)
    private Integer ano;

    @NotNull
    @Min(value = 0L)
    @Column(name = "valor", nullable = false)
    private Long valor;

    @OneToMany(mappedBy = "veiculo")
    private Set<Despesa> despesas = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "veiculos", allowSetters = true)
    private Modelo modelo;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public Veiculo placa(String placa) {
        this.placa = placa;
        return this;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCor() {
        return cor;
    }

    public Veiculo cor(String cor) {
        this.cor = cor;
        return this;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Integer getAno() {
        return ano;
    }

    public Veiculo ano(Integer ano) {
        this.ano = ano;
        return this;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Long getValor() {
        return valor;
    }

    public Veiculo valor(Long valor) {
        this.valor = valor;
        return this;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }

    public Set<Despesa> getDespesas() {
        return despesas;
    }

    public Veiculo despesas(Set<Despesa> despesas) {
        this.despesas = despesas;
        return this;
    }

    public Veiculo addDespesa(Despesa despesa) {
        this.despesas.add(despesa);
        despesa.setVeiculo(this);
        return this;
    }

    public Veiculo removeDespesa(Despesa despesa) {
        this.despesas.remove(despesa);
        despesa.setVeiculo(null);
        return this;
    }

    public void setDespesas(Set<Despesa> despesas) {
        this.despesas = despesas;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public Veiculo modelo(Modelo modelo) {
        this.modelo = modelo;
        return this;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Veiculo)) {
            return false;
        }
        return id != null && id.equals(((Veiculo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Veiculo{" +
            "id=" + getId() +
            ", placa='" + getPlaca() + "'" +
            ", cor='" + getCor() + "'" +
            ", ano=" + getAno() +
            ", valor=" + getValor() +
            "}";
    }
}
