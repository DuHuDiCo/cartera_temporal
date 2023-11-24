package com.cartera_temp.cartera_temp.ServiceImpl;



import com.cartera_temp.cartera_temp.ModelsClients.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImple implements UserDetailsService {

    @Autowired
    private UsuarioClientServiceImpl usuarioClientServiceImpl ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = this.usuarioClientServiceImpl.obtenerUsuario(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no Encontrado");
        }
        return usuario;
    }

}
