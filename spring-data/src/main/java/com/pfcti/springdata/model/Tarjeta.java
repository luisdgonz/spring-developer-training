package com.pfcti.springdata.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Tarjeta {
    @Id
    private int id;
    private String numero;
    private String tipo;
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Cliente cliente;
}
