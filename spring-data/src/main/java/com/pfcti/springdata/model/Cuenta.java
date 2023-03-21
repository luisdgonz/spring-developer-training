package com.pfcti.springdata.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String numero;
    private String tipo;
    private Boolean estado;
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Cliente cliente;
}
