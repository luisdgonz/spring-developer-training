package com.pfcti.springdata.springwebservices.api;

import com.pfcti.springdata.dto.CuentaDTO;
import com.pfcti.springdata.service.CuentaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/cuenta")
@Slf4j
public class CuentaApi {

    @Autowired
    private CuentaService cuentaService;

    @PostMapping
    public void guardarCuenta(@Valid @RequestBody CuentaDTO cuentaDTO){
        log.info("Guardar una cuenta: {}", cuentaDTO);
        cuentaService.insertarCuenta(cuentaDTO);
    }

    @GetMapping(value = "/by-cliente/{clienteId}")
    public List<CuentaDTO> obtenerCuentasPorCliente(@PathVariable int clienteId){
        log.info("Obtener cuentas del cliente: {}", clienteId);
        return cuentaService.buscarCuentasPorCliente(clienteId);
    }
    @PutMapping
    public void desactivarCuentasCliente(@PathVariable int clienteId){
        log.info("Desactivar cuentas del cliente: {}", clienteId);
        cuentaService.desactivarCuentasPorCliente_id(clienteId);
    }

    @PostMapping(value = "/desactivar")
    public CuentaDTO desactivarCuentaPorIdCuenta(@RequestBody CuentaDTO cuentaDto){
        log.info("Desactivar Cuenta por:", cuentaDto);
        return cuentaService.desactivarCuentaPorId(cuentaDto);
    }
}
