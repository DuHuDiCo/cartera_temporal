

package com.cartera_temp.cartera_temp.FeignClients;

import com.cartera_temp.cartera_temp.Dtos.ClientesDto;
import org.springframework.cloud.openfeign.FeignClient;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient (name="clientes-temporal",  url = "${clientes.temporal}")
public interface ClientesClient {

    @GetMapping("/clientesByObligacion")
    List<ClientesDto> buscarClientesByNumeroObligacion(@PathVariable("numeroObligacion") String obligacion);
    
}
