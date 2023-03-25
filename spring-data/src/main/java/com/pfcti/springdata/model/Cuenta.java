package com.pfcti.springdata.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Numero de cuenta es mandatorio")
    @NotEmpty(message = "Numero de cuenta no debe ser vacio")
    @Size(min = 1, message = "Numero de cuenta debe tener minimo un caracter")
    private String numero;
    @Size(min = 1, message = "Tipo de cuenta debe tener minimo un caracter")
    private String tipo;
    @AssertTrue
    private Boolean estado;
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Cliente cliente;

    //private int clientId;
}
