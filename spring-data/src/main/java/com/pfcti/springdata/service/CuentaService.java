package com.pfcti.springdata.service;

import com.pfcti.springdata.criteria.CuentaSpecification;
import com.pfcti.springdata.dto.ClienteDTO;
import com.pfcti.springdata.dto.CuentaDTO;
import com.pfcti.springdata.model.Cliente;
import com.pfcti.springdata.model.Cuenta;
import com.pfcti.springdata.repository.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class CuentaService {
    private CuentaRepository cuentaRepository;
    private CuentaSpecification cuentaSpecification;

    public List<CuentaDTO> buscarCuentasDinamicamentePorCriterio(CuentaDTO cuentaDtoFilter){
        return cuentaRepository.findAll(cuentaSpecification.buildFilter(cuentaDtoFilter))
                .stream().map(this::fromCuentaToDto).collect(Collectors.toList());
    }

    private CuentaDTO fromCuentaToDto(Cuenta cuenta){
        CuentaDTO cuentaDTO = new CuentaDTO();
        BeanUtils.copyProperties(cuenta, cuentaDTO);
        return cuentaDTO;
    }

    public void insertarCuenta(CuentaDTO cuentaDTO){
        Cuenta cuenta = new Cuenta();
        cuenta.setNumero(cuentaDTO.getNumero());
        cuenta.setTipo(cuentaDTO.getTipo());
        cuenta.setEstado(cuentaDTO.getEstado());
        cuentaRepository.save(cuenta);
    }

    public List<CuentaDTO> buscarCuentasPorCliente(int idCliente) {
        List<CuentaDTO> cuentasPorCliente = new ArrayList<>();
        cuentaRepository.findCuentasByCliente_IdAndEstadoIsTrue(idCliente)
                .stream()
                .map(cuenta -> {
                    cuentasPorCliente.add(fromCuentaToDto(cuenta));
                    log.info("Cuenta de Cliente :{}", cuenta);
                    return cuenta;}
                ).collect(Collectors.toList());
        return cuentasPorCliente;
    }
}
