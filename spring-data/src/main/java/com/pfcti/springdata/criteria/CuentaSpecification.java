package com.pfcti.springdata.criteria;


import com.pfcti.springdata.dto.ClienteDTO;
import com.pfcti.springdata.dto.CuentaDTO;
import com.pfcti.springdata.model.Cliente;
import com.pfcti.springdata.model.Cuenta;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Locale;

@Component
public class CuentaSpecification {
    public <T> Specification<T> equals(String fieldName, String fieldValue) {
        return fieldValue == null ? null :
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.equal(root.get(fieldName), fieldValue);
    }

    public static <T> Specification<T> like(String fieldName, String fieldValue) {
        if (fieldValue != null) {
            String uppercaseValue = MessageFormat.format("%{0}%", fieldValue.trim().toUpperCase(Locale.ROOT)).replaceAll(" ", "%");
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.upper(root.get(fieldName)), uppercaseValue);
        } else {
            return null;
        }
    }

    private Specification<Cuenta> numeroCriteria(CuentaDTO cuentaDto){

        return like("numero", cuentaDto.getNumero());
    }

    private Specification<Cuenta> tipoCriteria(CuentaDTO cuentaDto){
        return like("tipo", cuentaDto.getTipo());
    }

    private Specification<Cuenta> estadoCriteria(CuentaDTO cuentaDto){
        return like("estado", cuentaDto.getEstado().toString());
    }

    public Specification<Cuenta> buildFilter(CuentaDTO cuentaDto){
        System.out.println("Terms of Criteria:" + cuentaDto);
        return Specification
                .where(numeroCriteria(cuentaDto))
                .and(tipoCriteria(cuentaDto))
                .and(estadoCriteria(cuentaDto));
    }
}
