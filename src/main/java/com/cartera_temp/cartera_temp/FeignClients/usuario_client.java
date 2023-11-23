package com.cartera_temp.cartera_temp.FeignClients;


import com.cartera_temp.cartera_temp.ModelsClients.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient (name="user-microservice",  url = "${user.microservice.url}")
public interface usuario_client {
    
    @GetMapping("/getUser/{username}")
    Usuario getUserByUsername(@PathVariable("username") String username);

    @GetMapping("/{id}")
    Usuario getUsuarioById(@PathVariable("id") Long id, @RequestHeader("Authorization") String token);
    
    @GetMapping("/usuarioByNombresAndApellidos")
    Usuario getUsuarioByNombresAndApellidos(@RequestParam(name = "nombres") String nombres, @RequestParam(name = "apellidos") String apellidos);
}
