package com.cartera_temp.cartera_temp.Service;

import com.cartera_temp.cartera_temp.ModelsClients.Usuario;



public interface UsuarioClientService {
    
    public Usuario obtenerUsuario(String username);
    
    public Usuario obtenerUsuarioById(Long id);
    
}
