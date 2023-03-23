package com.pfcti.springdata.springbeans.business.impl;

import com.pfcti.springdata.dto.ClienteDTO;
import com.pfcti.springdata.dto.CuentaDTO;
import com.pfcti.springdata.springbeans.business.BuscadorClientes;
import com.pfcti.springdata.springbeans.business.BuscadorCuentas;
import com.pfcti.springdata.springbeans.dto.ClienteQueryDto;
import com.pfcti.springdata.springbeans.dto.ClienteQueryType;
import com.pfcti.springdata.springbeans.dto.CuentaQueryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sistemaExternoCuentas")
public class BuscadorCuentasSistemaExterno implements BuscadorCuentas {

    @Override
    public List<CuentaDTO> obtenerListaCuentas(CuentaQueryDto cuentaQueryDto) {
        System.out.println("sistemaExterno");
        return null;
    }
}
