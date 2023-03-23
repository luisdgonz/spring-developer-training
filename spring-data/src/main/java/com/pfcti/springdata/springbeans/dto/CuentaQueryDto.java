package com.pfcti.springdata.springbeans.dto;

import lombok.Data;

@Data
public class CuentaQueryDto {
    private String textoBusqueda;
    private CuentaQueryType tipoBusqueda;
}
