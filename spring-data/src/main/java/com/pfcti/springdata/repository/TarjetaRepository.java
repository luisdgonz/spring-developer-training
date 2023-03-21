package com.pfcti.springdata.repository;

import com.pfcti.springdata.model.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta,Integer> {

    void deleteAllByCliente_Id(int clientId);

    List<Tarjeta> findTarjetasByCliente_IdAndEstadoIsTrue(int clientId);
}
