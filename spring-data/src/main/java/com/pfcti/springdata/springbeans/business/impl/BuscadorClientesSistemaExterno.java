package com.pfcti.springdata.springbeans.business.impl;

import com.pfcti.springdata.dto.ClienteDTO;
import com.pfcti.springdata.springbeans.business.BuscadorClientes;
import com.pfcti.springdata.springbeans.dto.ClienteQueryDto;
import com.pfcti.springdata.springbeans.dto.ClienteQueryType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service("sistemaExterno")
public class BuscadorClientesSistemaExterno implements BuscadorClientes {

    @Override
    public List<ClienteDTO> obtenerListaClientes(ClienteQueryDto clienteQueryDto) {
        List<ClienteDTO> resultadoClientes = List.of(
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
        return resultadoClientes.stream().filter(filter ->
                        clienteQueryDto.getTipoBusqueda() == ClienteQueryType.NOMBRES ?
                                filter.getNombre().equals(clienteQueryDto.getTextoBusqueda())
                                : filter.getCedula().equals(clienteQueryDto.getTextoBusqueda()))
                .toList();
    }
}
