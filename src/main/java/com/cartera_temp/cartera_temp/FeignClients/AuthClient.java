

package com.cartera_temp.cartera_temp.FeignClients;



import com.cartera_temp.cartera_temp.Dtos.TokenDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "auth-microservice", url = "${auth.microservice.url}")
public interface AuthClient {
    
    
    
     /**
     * Método para guardar permisos en el sistema.
     *
     * @param  token Token para extraer el username .
     * @return String username en caso de exito.
     */
    @GetMapping("/extract/{token}")
    String extractUsername(@PathVariable("token") String token);
    
     /**
     * Método para guardar permisos en el sistema.
     *
     * @param  token Token para validar .
     * @return Objeto de tipo TokenDto en caso de exito, y nulo en caso de token invalido.
     */
    
    @GetMapping("/validate/{token}")
    TokenDto validateToken(@PathVariable("token") String token);
}
