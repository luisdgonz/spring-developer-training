package com.pfcti.springdata.dto;

import com.pfcti.springdata.model.Cliente;
import lombok.Data;

@Data
public class CuentaDTO {
    private int id;
    private String numero;
    private String tipo;
    private String estado;
}
