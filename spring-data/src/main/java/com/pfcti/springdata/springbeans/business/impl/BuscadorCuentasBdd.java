package com.pfcti.springdata.springbeans.business.impl;

import com.pfcti.springdata.dto.ClienteDTO;
import com.pfcti.springdata.dto.CuentaDTO;
import com.pfcti.springdata.model.Cliente;
import com.pfcti.springdata.model.Cuenta;
import com.pfcti.springdata.repository.ClienteRepository;
import com.pfcti.springdata.repository.CuentaRepository;
import com.pfcti.springdata.service.CuentaService;
import com.pfcti.springdata.springbeans.business.BuscadorClientes;
import com.pfcti.springdata.springbeans.business.BuscadorCuentas;
import com.pfcti.springdata.springbeans.dto.ClienteQueryDto;
import com.pfcti.springdata.springbeans.dto.ClienteQueryType;
import com.pfcti.springdata.springbeans.dto.CuentaQueryDto;
import com.pfcti.springdata.springbeans.dto.CuentaQueryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("baseDeDatosCuentas")
public class BuscadorCuentasBdd implements BuscadorCuentas {
    @Autowired
    private CuentaRepository cuentaRepository;
    private CuentaQueryType defaultCuentaQueryType;

    @Autowired
    private CuentaService cuentaService;
    @Override
    public List<CuentaDTO> obtenerListaCuentas(CuentaQueryDto cuentaQueryDto) {
        List<CuentaDTO> resultadoCuentas = new ArrayList<>();
        List<Cuenta> cuentas = null;
        if (cuentaQueryDto.getTipoBusqueda() == null) {
            cuentaQueryDto.setTipoBusqueda(defaultCuentaQueryType);
        }
        if (CuentaQueryType.TIPO.equals(cuentaQueryDto.getTipoBusqueda())) {
            cuentas = this.cuentaRepository.findCuentasByTipo(cuentaQueryDto.getTextoBusqueda());
        } else if (CuentaQueryType.NUMERO.equals(cuentaQueryDto.getTipoBusqueda())) {
            cuentas = this.cuentaRepository.findCuentasByNumero(cuentaQueryDto.getTextoBusqueda());
        }
        return Optional.ofNullable(cuentas).map(cuentasAux-> cuentasAux.stream().map(cuenta -> {
            CuentaDTO cuentaDTO = new CuentaDTO();
            cuentaDTO.setTipo(cuenta.getTipo());
            cuentaDTO.setNumero(cuenta.getNumero());
            cuentaDTO.setEstado(cuenta.getEstado());
            return cuentaDTO;
        }).collect(Collectors.toList())).orElse(new ArrayList<>());
    }

    @Override
    public List<CuentaDTO> buscarCuentasPorCliente(int idCliente) {
        return cuentaService.buscarCuentasPorCliente(idCliente);
    }
}
