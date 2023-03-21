package com.pfcti.springdata.service;

import com.pfcti.springdata.dto.TarjetaDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class TarjetaServiceTest {

    @Autowired
    private TarjetaService tarjetaService;
    @Test
    void cambiarEstadoTarjeta() {
        TarjetaDTO tarjetaDTO = new TarjetaDTO();
        tarjetaDTO = tarjetaService.cambiarEstadoTarjeta(2,true);
        System.out.println("Id: " + tarjetaDTO.getId());
        System.out.println("Numero: " + tarjetaDTO.getNumero());
        System.out.println("Estado: " + tarjetaDTO.getEstado());
        System.out.println("Tipo: " + tarjetaDTO.getTipo());
        assertEquals(true,tarjetaDTO.getEstado());
    }
}