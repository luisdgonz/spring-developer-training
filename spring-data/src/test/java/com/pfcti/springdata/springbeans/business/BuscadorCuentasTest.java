package com.pfcti.springdata.springbeans.business;

import com.pfcti.springdata.dto.ClienteDTO;
import com.pfcti.springdata.dto.CuentaDTO;
import com.pfcti.springdata.springbeans.dto.ClienteQueryDto;
import com.pfcti.springdata.springbeans.dto.ClienteQueryType;
import com.pfcti.springdata.springbeans.dto.CuentaQueryDto;
import com.pfcti.springdata.springbeans.dto.CuentaQueryType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BuscadorCuentasTest {

    @Autowired
    private BuscadorCuentas baseDeDatosCuentas;
    @Autowired
    @Qualifier("sistemaExternoCuentas")
    private BuscadorCuentas buscadorCuentas;
    @Test
    void obtenerListaCuentas() {
        CuentaQueryDto cuentaQueryDto = new CuentaQueryDto();
        cuentaQueryDto.setTextoBusqueda("22222");
        cuentaQueryDto.setTipoBusqueda(CuentaQueryType.NUMERO);
        List<CuentaDTO> resultadoCuentasDtos = baseDeDatosCuentas.obtenerListaCuentas(cuentaQueryDto);
        resultadoCuentasDtos.forEach(cuentaDTO ->
                {
                    System.out.println("Resultado de la busqueda" + cuentaDTO.getNumero() + cuentaDTO.getTipo());
                }
        );
        Assertions.assertEquals(1, resultadoCuentasDtos.size());
    }

    @Test
    void obtenerListaCuentasSistemaExterno() {
        CuentaQueryDto cuentaQueryDto = new CuentaQueryDto();

        buscadorCuentas.obtenerListaCuentas(cuentaQueryDto);
    }

    @Test
    void buscarCuentasPorClienteBdd() {
        List<CuentaDTO> resultado = baseDeDatosCuentas.buscarCuentasPorCliente(1);
        resultado.forEach(cuentaDto -> System.out.println("Cuenta de cliente: "+ cuentaDto));
        Assertions.assertEquals(2, resultado.size());
    }

    @Test
    void buscarCuentasPorClienteSistemaExterno() {
        List<CuentaDTO> resultado = buscadorCuentas.buscarCuentasPorCliente(1);
        resultado.forEach(cuentaDto -> System.out.println("Cuenta de cliente: "+ cuentaDto));
        Assertions.assertEquals(1, resultado.size());
    }
}