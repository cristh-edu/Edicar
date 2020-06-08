package com.edicar.carros.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Cliente.
 */
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3)
    @Column(name = "nm_cliente", nullable = false)
    private String nmCliente;

    @NotNull
    @Size(min = 14, max = 14)
    @Column(name = "cpf", length = 14, nullable = false)
    private String cpf;

    @NotNull
    @Size(min = 15, max = 15)
    @Column(name = "telefone", length = 15, nullable = false)
    private String telefone;

    @OneToMany(mappedBy = "cliente")
    private Set<Compra> compras = new HashSet<>();

    @OneToMany(mappedBy = "cliente")
    private Set<Venda> vendas = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNmCliente() {
        return nmCliente;
    }

    public Cliente nmCliente(String nmCliente) {
        this.nmCliente = nmCliente;
        return this;
    }

    public void setNmCliente(String nmCliente) {
        this.nmCliente = nmCliente;
    }

    public String getCpf() {
        return cpf;
    }

    public Cliente cpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public Cliente telefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Set<Compra> getCompras() {
        return compras;
    }

    public Cliente compras(Set<Compra> compras) {
        this.compras = compras;
        return this;
    }

    public Cliente addCompra(Compra compra) {
        this.compras.add(compra);
        compra.setCliente(this);
        return this;
    }

    public Cliente removeCompra(Compra compra) {
        this.compras.remove(compra);
        compra.setCliente(null);
        return this;
    }

    public void setCompras(Set<Compra> compras) {
        this.compras = compras;
    }

    public Set<Venda> getVendas() {
        return vendas;
    }

    public Cliente vendas(Set<Venda> vendas) {
        this.vendas = vendas;
        return this;
    }

    public Cliente addVenda(Venda venda) {
        this.vendas.add(venda);
        venda.setCliente(this);
        return this;
    }

    public Cliente removeVenda(Venda venda) {
        this.vendas.remove(venda);
        venda.setCliente(null);
        return this;
    }

    public void setVendas(Set<Venda> vendas) {
        this.vendas = vendas;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cliente)) {
            return false;
        }
        return id != null && id.equals(((Cliente) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Cliente{" +
            "id=" + getId() +
            ", nmCliente='" + getNmCliente() + "'" +
            ", cpf='" + getCpf() + "'" +
            ", telefone='" + getTelefone() + "'" +
            "}";
    }
}
