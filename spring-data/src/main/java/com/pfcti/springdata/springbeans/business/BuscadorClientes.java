package com.pfcti.springdata.springbeans.business;

import com.pfcti.springdata.dto.ClienteDTO;
import com.pfcti.springdata.springbeans.dto.ClienteQueryDto;

import java.util.List;

public interface BuscadorClientes {
    List<ClienteDTO> obtenerListaClientes(ClienteQueryDto clienteQueryDto);
}
