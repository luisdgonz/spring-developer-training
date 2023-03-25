package com.pfcti.springdata.service;

import com.pfcti.springdata.criteria.ClienteSpecification;
import com.pfcti.springdata.dto.*;
import com.pfcti.springdata.model.Cliente;
import com.pfcti.springdata.model.Cuenta;
import com.pfcti.springdata.model.Inversion;
import com.pfcti.springdata.model.Tarjeta;
import com.pfcti.springdata.repository.*;
import jakarta.persistence.Tuple;
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
public class ClienteService {
    private ClienteRepository clienteRepository;
    private CuentaRepository cuentaRepository;
    private DireccionRepository direccionRepository;
    private InversionRepository inversionRepository;
    private TarjetaRepository tarjetaRepository;

    private ClienteSpecification clienteSpecification;
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
        cuentaRepository.deleteAllByCliente_Id(id);
        direccionRepository.deleteAllByCliente_Id(id);
        cuentaRepository.deleteAllByCliente_Id(id);
        tarjetaRepository.deleteAllByCliente_Id(id);
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

    public List<ClienteDTO> obtenerClientesPorCodigoISOPaisYCuentasActivas(String codigoISOPais){
        List<ClienteDTO> resultadoClientesDto = new ArrayList<>();
        List<Cliente> clientes = clienteRepository.findClientesByPaisNacimientoAndCuentas_EstadoIsTrue(codigoISOPais);
        clientes.forEach(cliente -> {
            ClienteDTO clienteDto = new ClienteDTO();
            clienteDto.setId(cliente.getId());
            clienteDto.setApellidos(cliente.getApellidos());
            clienteDto.setNombre(cliente.getNombre());
            clienteDto.setCedula(cliente.getCedula());
            clienteDto.setPaisNacimiento(cliente.getPaisNacimiento());
            resultadoClientesDto.add(clienteDto);
            System.out.println(clienteDto);
        });
        return resultadoClientesDto;
    }

    public List<Cliente> buscarPorApellido(String apellidos){
        return clienteRepository.buscarPorApellidos(apellidos);
    }

    public List<ClienteDTO> buscarClientesPorApellidoNativo(String apellidos){
        List<ClienteDTO> clienteDtos = new ArrayList<>();
        List<Tuple> tuples = clienteRepository.buscarPorApellidosNativo(apellidos);
        tuples.forEach(tuple -> {
            ClienteDTO clienteDto = new ClienteDTO();
            clienteDto.setApellidos((String) tuple.get("apellidos"));
            clienteDto.setNombre((String) tuple.get("nombre"));
            clienteDto.setCedula((String) tuple.get("cedula"));
            clienteDtos.add(clienteDto);
            System.out.println(tuple.get("apellidos"));
        });
        return clienteDtos;
    }

    public List<ClienteDTO> obtenerClientesPorCodigoISOPaisYTarjetasInactivas(String codigoISOPais){
        List<ClienteDTO> resultadoClientesDto = new ArrayList<>();
        List<Cliente> clientes = clienteRepository.findClientesByPaisNacimientoNotContainsAndTarjetas_EstadoIsFalse(codigoISOPais);
        clientes.forEach(cliente -> {
            ClienteDTO clienteDto = new ClienteDTO();
            clienteDto.setId(cliente.getId());
            clienteDto.setApellidos(cliente.getApellidos());
            clienteDto.setNombre(cliente.getNombre());
            clienteDto.setCedula(cliente.getCedula());
            clienteDto.setPaisNacimiento(cliente.getPaisNacimiento());
            resultadoClientesDto.add(clienteDto);
            System.out.println(clienteDto);
        });
        return resultadoClientesDto;
    }

    public List<ClienteDTO> buscarClientesDinamicamentePorCriterio(ClienteDTO clienteDtoFilter){
        return clienteRepository.findAll(clienteSpecification.buildFilter(clienteDtoFilter))
                .stream().map(this::fromClienteToDto).collect(Collectors.toList());
    }

    private ClienteDTO fromClienteToDto(Cliente cliente){
        ClienteDTO clienteDto = new ClienteDTO();
        BeanUtils.copyProperties(cliente, clienteDto);
        return clienteDto;
    }

    private TarjetaDTO fromTarjetaToDto(Tarjeta tarjeta){
        TarjetaDTO tarjetaDto = new TarjetaDTO();
        BeanUtils.copyProperties(tarjeta, tarjetaDto);
        return tarjetaDto;
    }

    private InversionDTO fromInversionToDto(Inversion inversion){
        InversionDTO inversionDTO = new InversionDTO();
        BeanUtils.copyProperties(inversion, inversionDTO);
        return inversionDTO;
    }

    private CuentaDTO fromCuentaToDto(Cuenta cuenta){
        CuentaDTO cuentaDTO = new CuentaDTO();
        BeanUtils.copyProperties(cuenta, cuentaDTO);
        return cuentaDTO;
    }

    public ProductosDTO obtenerProductosPorCliente(Integer id){
        ProductosDTO productosDTO = new ProductosDTO();
        List<TarjetaDTO> tarjetas = new ArrayList<>();
        tarjetaRepository.findTarjetasByCliente_IdAndEstadoIsTrue(id).forEach(tarjeta -> {
            TarjetaDTO tarjetaDTO;
            tarjetaDTO = fromTarjetaToDto(tarjeta);
            tarjetas.add(tarjetaDTO);
        });
        productosDTO.setTarjetas(tarjetas);

        List<InversionDTO> inversiones = new ArrayList<>();
        inversionRepository.findInversionsByCliente_IdAndEstadoIsTrue(id).forEach(inversion -> {
            InversionDTO inversionDTO;
            inversionDTO = fromInversionToDto(inversion);
            inversiones.add(inversionDTO);
        });
        productosDTO.setInversiones(inversiones);

        List<CuentaDTO> cuentas = new ArrayList<>();
        cuentaRepository.findCuentasByCliente_IdAndEstadoIsTrue(id).forEach(cuenta -> {
            CuentaDTO cuentaDTO;
            cuentaDTO = fromCuentaToDto(cuenta);
            cuentas.add(cuentaDTO);
        });
        productosDTO.setCuentas(cuentas);

        return productosDTO;
    }

    public List<ClienteDTO> listarTodosLosClientes(){
        List<ClienteDTO> clienteDtoList = new ArrayList<>();
        clienteRepository
                .findAll()
                .stream()
                .map(cliente -> {
                    clienteDtoList.add(fromClienteToDto(cliente));
                    return cliente;
                })
                .collect(Collectors.toList());
        return clienteDtoList;
    }

    public io.spring.guides.gs_producing_web_service.Cliente obtenerClienteSoap(int idCliente){
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() -> { throw new RuntimeException("Cliente no existe");});
        io.spring.guides.gs_producing_web_service.Cliente clienteWs = new io.spring.guides.gs_producing_web_service.Cliente();
        clienteWs.setId(cliente.getId());
        clienteWs.setNombre(cliente.getNombre());
        clienteWs.setTelefono(cliente.getTelefono());
        clienteWs.setApellidos(cliente.getApellidos());
        clienteWs.setCedula(cliente.getCedula());
        clienteWs.setPaisNacimiento(cliente.getPaisNacimiento());
        return clienteWs;
    }
}
