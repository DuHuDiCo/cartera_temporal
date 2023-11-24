package com.cartera_temp.cartera_temp.ServiceImpl;



import com.cartera_temp.cartera_temp.Dtos.TokenDto;
import com.cartera_temp.cartera_temp.FeignClients.AuthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticacionFeingService {

    /**
     *
     * Clase que utiliza la anotación @Autowired para inyectar la dependencia
     * AuthClient. Esta clase se encarga de consumir el api del microservicio de autenticacion.
     */
    @Autowired
    private AuthClient authClient;

    
    
     /**
     * Método para guardar permisos en el sistema.
     *
     * @param  token Token de autenticacion.
     * @return username del usuario autenticado.
     */
    public String extractUsername(String token) {
        //enviamos el token a la clase que se comunica con el microservicio de autenticacion
        String username = authClient.extractUsername(token);
        //si el username es nulo el token es invalido o el usuario no existe
        if (username == null) {
            return null;
        }
        //retornamos el usuario en caso de que sea valido el token 
        return username;
    }

    
     /**
     * Método para guardar permisos en el sistema.
     *
     * @param  token Token a validar .
     * @return Objeto de tipo TokenDto con los datos validados.
     */
    public TokenDto validarToken(String token) {
        //enviamos el token a la clase encargada de comunicarse con el microservicio de autenticacion
        TokenDto tokenDto = authClient.validateToken(token);
        //si el Objeto TokenDto es nulo quiere decir que el token es invalido 
        if (tokenDto == null) {
            return null;
        }
        
        //retornamos el Objeto TokenDto en caso de ser valido
        return tokenDto;
    }

}
