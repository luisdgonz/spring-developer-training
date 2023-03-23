package com.pfcti.springdata.springbeans;

import com.pfcti.springdata.dto.ClienteDTO;
import com.pfcti.springdata.repository.ClienteRepository;
import com.pfcti.springdata.service.ClienteService;
import com.pfcti.springdata.springbeans.dto.ClienteQueryDto;
import com.pfcti.springdata.springbeans.dto.ClienteQueryType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AdministradorClientesConAutowiredTest {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private AdministradorClientes defaultCedula;

    @Autowired
    @Qualifier("defaultNombres")
    private AdministradorClientes administradorClientes;
    @Autowired
    ClienteRepository clienteRepository;

    @BeforeEach
    public void setUp() {
        this.setupClientes();
    }

    private void setupClientes() {
        List<ClienteDTO> clienteDtos = List.of(
                new ClienteDTO(1, "Alberto", "Salazar", "1890000000", "0999714563", "CR", null, null, null, null),
                new ClienteDTO(2, "Rosa", "Salazar", "1890000001", "0983475616", "CR", null, null, null, null),
                new ClienteDTO(3, "Alexis", "Vivanco", "1890000002", "0983475616", "CR", null, null, null, null),
                new ClienteDTO(4, "Natalie", "Vivanco", "1890000003", "0983665616", "CR", null, null, null, null),
                new ClienteDTO(5, "Ximena", "Silva", "1890000004", "0983475616", "CR", null, null, null, null),
                new ClienteDTO(6, "Thalia", "Rodriguez", "1890000005", "0983475616", "CR", null, null, null, null),
                new ClienteDTO(7, "Jonh", "Rodriguez", "1890000006", "0983475616", "CR", null, null, null, null),
                new ClienteDTO(8, "Eduardo", "Guerra", "1890000007", "0983475616", "CR", null, null, null, null),
                new ClienteDTO(9, "Juan", "Vaca", "1890000008", "0983475616", "CR", null, null, null, null),
                new ClienteDTO(10, "Cristina", "Ortiz", "1890000009", "0983475616", "CR", null, null, null, null)
        );
        clienteDtos.forEach(clienteDto -> this.clienteService.insertarCliente(clienteDto));
    }
    @Test
    void obtenerListaClientesPorCriterioCedula() {
        ClienteQueryDto clienteQueryDto = new ClienteQueryDto();
        clienteQueryDto.setTipoBusqueda(ClienteQueryType.CEDULA);
        clienteQueryDto.setTextoBusqueda("1890000002");
        //Se invoca al método respectivo
        List<ClienteDTO> clienteDtos = defaultCedula.obtenerListaClientesPorCriterio(clienteQueryDto);
        Assertions.assertEquals(1, clienteDtos.size());
    }

    @Test
    void obtenerListaClientesPorCriterioNombres() {
        ClienteQueryDto clienteQueryDto = new ClienteQueryDto();
        clienteQueryDto.setTextoBusqueda("Guerra");
        //Se invoca al método respectivo
        List<ClienteDTO> clienteDtos = administradorClientes.obtenerListaClientesPorCriterio(clienteQueryDto);
        Assertions.assertEquals(1, clienteDtos.size());
    }
}
