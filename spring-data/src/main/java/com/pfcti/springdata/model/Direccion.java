package com.pfcti.springdata.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String direccion;
    private String nomenclatura;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Cliente cliente;
}
