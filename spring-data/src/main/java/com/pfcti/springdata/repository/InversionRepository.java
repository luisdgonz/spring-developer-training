package com.pfcti.springdata.repository;

import com.pfcti.springdata.model.Cliente;
import com.pfcti.springdata.model.Inversion;
import com.pfcti.springdata.model.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InversionRepository extends JpaRepository<Inversion,Integer> {

    void deleteAllByCliente_Id(int clientId);

    List<Inversion> findInversionsByCliente_IdAndEstadoIsTrue(int clientId);
}
