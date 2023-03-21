package com.pfcti.springdata.service;

import com.pfcti.springdata.dto.ClienteDTO;
import com.pfcti.springdata.dto.CuentaDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CuentaServiceTest {
    @Autowired
    private CuentaService cuentaService;
    @PersistenceContext
    private EntityManager entityManager;
    @Test
    void buscarCuentasDinamicamentePorCriterio() {
        CuentaDTO cuentaDTO = new CuentaDTO();
        //cuentaDTO.setNombre("RAUL");
        List<CuentaDTO> resultadoCriteriosConDatosDTO = cuentaService.buscarCuentasDinamicamentePorCriterio(cuentaDTO);
        resultadoCriteriosConDatosDTO.forEach(cuentaDTOResultado -> {System.out.println("CuentaDTO es:"+ cuentaDTOResultado);});
        assertEquals(1,resultadoCriteriosConDatosDTO.size());
    }
}