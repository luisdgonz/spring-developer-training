package com.pfcti.springdata.service;

import com.pfcti.springdata.dto.ClienteDTO;
import com.pfcti.springdata.dto.TarjetaDTO;
import com.pfcti.springdata.model.Tarjeta;
import com.pfcti.springdata.repository.TarjetaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TarjetaService {
    private TarjetaRepository tarjetaRepository;

    public TarjetaDTO cambiarEstadoTarjeta(int id, Boolean estado){
        TarjetaDTO tarjetaDTO = new TarjetaDTO();
        Tarjeta tarjeta = tarjetaRepository.findById(id).orElseThrow(() -> {throw new RuntimeException("Tarjeta no existe");});
        tarjetaDTO.setEstado(estado);
        tarjetaDTO.setId(tarjeta.getId());
        tarjetaDTO.setTipo(tarjeta.getTipo());
        tarjetaDTO.setNumero(tarjeta.getNumero());
        return tarjetaDTO;
    }
}
