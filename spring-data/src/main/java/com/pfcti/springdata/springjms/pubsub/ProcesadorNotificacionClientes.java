package com.pfcti.springdata.springjms.pubsub;

import com.pfcti.springdata.dto.CuentaDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.messaging.Message;

@Component
@Slf4j
@AllArgsConstructor
public class ProcesadorNotificacionClientes {

    @ServiceActivator(inputChannel = "pubSubNotification")
    public Message<String> consumirMensajesParaClientes(Message<CuentaDTO> message){
        CuentaDTO cuentaDTO = message.getPayload();
        log.info("cuentaDTO consumirMensajesParaClientes");
        return MessageBuilder.withPayload("Mensaje recibido por consumirMensajesParaClientes").build();
    }

}
