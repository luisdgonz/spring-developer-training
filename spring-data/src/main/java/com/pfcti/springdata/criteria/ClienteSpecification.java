package com.pfcti.springdata.criteria;

import com.pfcti.springdata.dto.ClienteDTO;
import com.pfcti.springdata.model.Cliente;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Locale;

@Component
public class ClienteSpecification {

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

    private Specification<Cliente> apellidosCriteria(ClienteDTO clienteDto){
        return like("apellidos", clienteDto.getApellidos());
    }

    private Specification<Cliente> nombreCriteria(ClienteDTO clienteDto){
        return like("nombre", clienteDto.getNombre());
    }

    private Specification<Cliente> cedulaCriteria(ClienteDTO clienteDto){
        return like("cedula", clienteDto.getCedula());
    }

    private Specification<Cliente> telefonoCriteria(ClienteDTO clienteDto){
        return like("telefono", clienteDto.getTelefono());
    }

    private Specification<Cliente> paisNacimientoCriteria(ClienteDTO clienteDto){
        return like("paisNacimiento", clienteDto.getPaisNacimiento());
    }

    public Specification<Cliente> buildFilter(ClienteDTO clienteDto){
        System.out.println("Terms of Criteria:" + clienteDto);
        return Specification
                .where(apellidosCriteria(clienteDto))
                .and(nombreCriteria(clienteDto))
                .and(cedulaCriteria(clienteDto))
                .and(telefonoCriteria(clienteDto))
                .and(paisNacimientoCriteria(clienteDto));
    }
}
