package com.pfcti.springdata.service;

import com.pfcti.springdata.criteria.CuentaSpecification;
import com.pfcti.springdata.dto.ClienteDTO;
import com.pfcti.springdata.dto.CuentaDTO;
import com.pfcti.springdata.model.Cliente;
import com.pfcti.springdata.model.Cuenta;
import com.pfcti.springdata.repository.*;
import com.pfcti.springdata.springjms.dto.NotificationDto;
import com.pfcti.springdata.springjms.pubsub.publishers.NotificationPubSubSender;
import com.pfcti.springdata.springjms.senders.NoticationSender;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.messaging.Message;
import org.springframework.integration.support.MessageBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class CuentaService {
    private CuentaRepository cuentaRepository;
    private CuentaSpecification cuentaSpecification;

    private NoticationSender noticationSender;

    private ClienteService clienteService;

    private NotificationPubSubSender notificationPubSubSender;

    public List<CuentaDTO> buscarCuentasDinamicamentePorCriterio(CuentaDTO cuentaDtoFilter){
        return cuentaRepository.findAll(cuentaSpecification.buildFilter(cuentaDtoFilter))
                .stream().map(this::fromCuentaToDto).collect(Collectors.toList());
    }

    private CuentaDTO fromCuentaToDto(Cuenta cuenta){
        CuentaDTO cuentaDTO = new CuentaDTO();
        BeanUtils.copyProperties(cuenta, cuentaDTO);
        return cuentaDTO;
    }

    public void insertarCuenta(CuentaDTO cuentaDTO){
        Cuenta cuenta = new Cuenta();
        cuenta.setNumero(cuentaDTO.getNumero());
        cuenta.setTipo(cuentaDTO.getTipo());
        cuenta.setEstado(cuentaDTO.getEstado());//(cuentaDTO.getClientId());
        cuentaRepository.save(cuenta);
        this.enviarNotificacion(cuentaDTO);
    }

    private void enviarNotificacion(CuentaDTO cuentaDto){
        log.info("Preparo notificacion");
        NotificationDto noticationDto = new NotificationDto();
        ClienteDTO clienteDto = clienteService.obtenerCliente(cuentaDto.getClientId());
        noticationDto.setPhoneNumber(clienteDto.getTelefono());
        noticationDto.setMailBody("Estimado " + clienteDto.getNombre() + "tu cuenta fue creada");
        noticationSender.sendSms(noticationDto);
        Message<CuentaDTO> message = MessageBuilder.withPayload(cuentaDto).build();
        notificationPubSubSender.sendNotification(message);
    }

    public List<CuentaDTO> buscarCuentasPorCliente(int idCliente) {
        List<CuentaDTO> cuentasPorCliente = new ArrayList<>();
        cuentaRepository.findCuentasByCliente_IdAndEstadoIsTrue(idCliente)
                .stream()
                .map(cuenta -> {
                    cuentasPorCliente.add(fromCuentaToDto(cuenta));
                    log.info("Cuenta de Cliente :{}", cuenta);
                    return cuenta;}
                ).collect(Collectors.toList());
        return cuentasPorCliente;
    }

    public void actualizarCuenta(CuentaDTO cuentaDTO){
        Cuenta cuenta = new Cuenta();
        cuenta.setId(cuentaDTO.getId());
        cuenta.setNumero(cuentaDTO.getNumero());
        cuenta.setTipo(cuentaDTO.getTipo());
        cuenta.setEstado(cuentaDTO.getEstado());
        cuentaRepository.save(cuenta);
    }

    public CuentaDTO obtenerCuenta(int id){
        CuentaDTO cuentaDTO = new CuentaDTO();
        Cuenta cuenta = cuentaRepository.findById(id).orElseThrow(() -> {throw new RuntimeException("Cuenta no existe");});
        cuentaDTO.setId(cuenta.getId());
        cuentaDTO.setEstado(cuenta.getEstado());
        cuentaDTO.setTipo(cuenta.getTipo());
        cuentaDTO.setNumero(cuenta.getNumero());
        return cuentaDTO;
    }

    public void desactivarCuentasPorCliente_id(Integer id){
        List<Cuenta> cuentasPorCliente = new ArrayList<>();
        cuentasPorCliente = cuentaRepository.findCuentasByCliente_IdAndEstadoIsTrue(id);
        cuentasPorCliente.forEach(cuenta -> {
            cuenta.setEstado(false);
            cuentaRepository.save(cuenta);
        });
    }

    public CuentaDTO desactivarCuentaPorId(CuentaDTO cuentaDto){
        Cuenta cuenta = cuentaRepository.findById(cuentaDto.getId()).orElseThrow(() -> {throw new RuntimeException("cuenta de Cliente No Existe");});
        cuenta.setEstado(false);
        cuentaRepository.save(cuenta);
        return fromCuentaToDto(cuenta);
    }
}
