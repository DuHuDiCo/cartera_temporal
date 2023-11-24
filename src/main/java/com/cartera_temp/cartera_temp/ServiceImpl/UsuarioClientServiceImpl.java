package com.cartera_temp.cartera_temp.ServiceImpl;

import com.cartera_temp.cartera_temp.FeignClients.usuario_client;
import com.cartera_temp.cartera_temp.ModelsClients.Usuario;
import com.cartera_temp.cartera_temp.Service.UsuarioClientService;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsuarioClientServiceImpl implements UsuarioClientService{

    @Autowired usuario_client usuClient;
   @Autowired   HttpServletRequest  request;
    
    @Override
    public Usuario obtenerUsuario(String username) {
       
        Usuario usu = usuClient.getUserByUsername(username);
        
        if(Objects.isNull(usu)){
            return null;
        }
        
        return usu;
        
    }

    @Override
    public Usuario obtenerUsuarioById(Long id) {
        String token = request.getAttribute("token").toString();
        Usuario usuario = usuClient.getUsuarioById(id, token);
        return Objects.isNull(usuario)?null:usuario;
    }
    
    
    
}
