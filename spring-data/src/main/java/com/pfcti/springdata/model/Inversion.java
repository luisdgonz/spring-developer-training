package com.pfcti.springdata.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Inversion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String numero;
    private String tipo;
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Cliente cliente;
}
