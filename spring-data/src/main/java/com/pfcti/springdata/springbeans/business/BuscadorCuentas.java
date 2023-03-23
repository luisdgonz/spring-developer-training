package com.pfcti.springdata.springbeans.business;

import com.pfcti.springdata.dto.ClienteDTO;
import com.pfcti.springdata.dto.CuentaDTO;
import com.pfcti.springdata.springbeans.dto.ClienteQueryDto;
import com.pfcti.springdata.springbeans.dto.CuentaQueryDto;

import java.util.List;

public interface BuscadorCuentas {
    List<CuentaDTO> obtenerListaCuentas(CuentaQueryDto cuentaQueryDto);

    List<CuentaDTO> buscarCuentasPorCliente(int idCliente);
}
