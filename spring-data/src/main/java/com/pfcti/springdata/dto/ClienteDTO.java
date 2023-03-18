package com.pfcti.springdata.dto;

import com.pfcti.springdata.model.Direccion;
import lombok.Data;

import java.util.List;
@Data
public class ClienteDTO {
    private int id;
    private String nombre;
    private String apellidos;
    private String cedula;
    private String telefono;
    private String Pais;
    private List<DireccionDTO> direcciones;
}
