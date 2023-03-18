package com.pfcti.springdata.service;

import com.pfcti.springdata.dto.ClienteDTO;
import com.pfcti.springdata.dto.CuentaDTO;
import com.pfcti.springdata.model.Cliente;
import com.pfcti.springdata.model.Cuenta;
import com.pfcti.springdata.repository.CuentaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CuentaService {
    private CuentaRepository cuentaRepository;

    public List<CuentaDTO> obtenerCuentasPorEstadoCliente(List<ClienteDTO> lista){
        List<Cuenta> cuentas = cuentaRepository.findAll();
        List<CuentaDTO> cuentasDTO = null;
        ClienteDTO clienteDTO = new ClienteDTO();
        for(Cuenta cuenta :cuentas) {
            if (cliente.getPais().compareTo(pais)==0){
                clienteDTO.setId(cliente.getId());
                clienteDTO.setApellidos(cliente.getApellidos());
                clienteDTO.setNombre(cliente.getNombre());
                clienteDTO.setTelefono(cliente.getTelefono());
                clienteDTO.setCedula(cliente.getCedula());
                clientesDTO.add(clienteDTO);
            }
        }
        return cuentasDTO;
    }
}
