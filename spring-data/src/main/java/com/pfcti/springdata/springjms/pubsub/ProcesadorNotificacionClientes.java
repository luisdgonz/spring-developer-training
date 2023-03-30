package com.pfcti.springdata.springjms.pubsub;

import com.pfcti.springdata.dto.CuentaDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.messaging.Message;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;

@Component
@Slf4j
@AllArgsConstructor
public class ProcesadorNotificacionClientes {

    private final static String TWILIO_ACCOUNT_SID = "";
    private final static String TWILIO_AUTH_TOKEN = "";
    private final static String TWILIO_MESSAGE_SID = "";

    @ServiceActivator(inputChannel = "pubSubNotification")
    public Message<String> consumirMensajesParaClientes(Message<CuentaDTO> message){
        CuentaDTO cuentaDTO = message.getPayload();
        log.info("cuentaDTO consumirMensajesParaClientes");
        // Puede tener la logica que necesitemos
        String sms = "Hola desde Twilio SMS";
        Twilio.init(TWILIO_ACCOUNT_SID.trim(), TWILIO_AUTH_TOKEN.trim());
        com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber("+593999714503"),
                TWILIO_MESSAGE_SID.trim(), sms).create();
        return MessageBuilder.withPayload("Mensaje recibido por consumirMensajesParaClientes").build();
    }

}
