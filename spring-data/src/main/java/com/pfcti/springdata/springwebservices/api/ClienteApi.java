package com.pfcti.springdata.springwebservices.api;

import com.pfcti.springdata.dto.ClienteDTO;
import com.pfcti.springdata.service.ClienteService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/client")
@Slf4j
public class ClienteApi {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public ClienteDTO buscarCliente(@PathVariable int id){
        log.info("Busquedad del cliente: {}", id);
        return clienteService.obtenerCliente(id);
    }

    @GetMapping(value = "/xml/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ClienteDTO buscarClienteXml(@PathVariable int id){
        log.info("Busquedad del cliente: {}", id);
        return clienteService.obtenerCliente(id);
    }

    @GetMapping(value = "/xml/json/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ClienteDTO buscarClienteXmlJson(@PathVariable int id){
        log.info("Busquedad del cliente: {}", id);
        return clienteService.obtenerCliente(id);
    }

    @GetMapping(value = "/parameter", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ClienteDTO buscarClienteParametro(@RequestParam int id){
        log.info("Busquedad del cliente: {}", id);
        return clienteService.obtenerCliente(id);
    }

    @PostMapping
    public void guardarCliente(@Valid @RequestBody ClienteDTO clienteDTO){
        log.info("Guardar el cliente: {}", clienteDTO);
        clienteService.insertarCliente(clienteDTO);
    }

    @GetMapping("/all")
    public List<ClienteDTO> listarTodosLosClientes(){
        return clienteService.listarTodosLosClientes();
    }

    @PutMapping
    public void actualizarCliente(ClienteDTO clienteDTO){
        log.info("Guardar el cliente: {}", clienteDTO);
        clienteService.actualizarCliente(clienteDTO);
    }

    @DeleteMapping("/{id}")
    public void borrarCliente(@PathVariable int id){
        log.info("Eliminar el cliente: {}", id);
        clienteService.eliminarCliente(id);
    }
}
