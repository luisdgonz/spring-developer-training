package com.pfcti.springdata.dto;

import com.pfcti.springdata.model.Direccion;
import com.pfcti.springdata.model.Inversion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    private int id;
    private String nombre;
    private String apellidos;
    private String cedula;
    private String telefono;
    private String paisNacimiento;
    private List<DireccionDTO> direcciones;

    private List<CuentaDTO> cuentas;

    private List<TarjetaDTO> tarjetas;

    private List<InversionDTO> inversiones;
}
