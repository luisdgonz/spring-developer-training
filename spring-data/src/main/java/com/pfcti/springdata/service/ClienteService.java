package com.pfcti.springdata.service;

import com.pfcti.springdata.dto.ClienteDTO;
import com.pfcti.springdata.model.Cliente;
import com.pfcti.springdata.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClienteService {
    private ClienteRepository clienteRepository;

    public void insertarCliente(ClienteDTO clienteDto){
        Cliente cliente = new Cliente();
        cliente.setApellidos(clienteDto.getApellidos());
        cliente.setNombre(clienteDto.getNombre());
        cliente.setCedula(clienteDto.getCedula());
        cliente.setTelefono(clienteDto.getTelefono());
        clienteRepository.save(cliente);
    }

    public void actualizarCliente(ClienteDTO clienteDTO){
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellidos(clienteDTO.getApellidos());
        cliente.setCedula(clienteDTO.getCedula());
        cliente.setTelefono(clienteDTO.getTelefono());
        clienteRepository.save(cliente);
    }

    public void eliminarCliente(int id){
        clienteRepository.deleteById(id);
    }

    public ClienteDTO obtenerCliente(int id){
        ClienteDTO clienteDTO = new ClienteDTO();
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> {throw new RuntimeException("Cliente no existe");});
        clienteDTO.setNombre(cliente.getNombre());
        clienteDTO.setApellidos(cliente.getApellidos());
        clienteDTO.setTelefono(cliente.getTelefono());
        clienteDTO.setCedula(cliente.getCedula());
        return clienteDTO;
    }

    public List<ClienteDTO> obtenerClientesPorPais(String pais){
                List<Cliente> clientes = clienteRepository.findAll();
                List<ClienteDTO> clientesDTO = null;
                ClienteDTO clienteDTO = new ClienteDTO();
                for(Cliente cliente :clientes) {
                    /*if (cliente.getPais().compareTo(pais)==0){
                        clienteDTO.setId(cliente.getId());
                        clienteDTO.setApellidos(cliente.getApellidos());
                        clienteDTO.setNombre(cliente.getNombre());
                        clienteDTO.setTelefono(cliente.getTelefono());
                        clienteDTO.setCedula(cliente.getCedula());
                        clientesDTO.add(clienteDTO);
                    }*/
        }
        return clientesDTO;
    }
}
