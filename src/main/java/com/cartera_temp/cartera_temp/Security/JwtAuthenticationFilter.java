package com.cartera_temp.cartera_temp.Security;


import com.cartera_temp.cartera_temp.Dtos.TokenDto;
import com.cartera_temp.cartera_temp.ServiceImpl.AuthenticacionFeingService;
import com.cartera_temp.cartera_temp.ServiceImpl.UserDetailsServiceImple;


import io.jsonwebtoken.ExpiredJwtException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImple userDetailsServiceImple;

    @Autowired
    private AuthenticacionFeingService feingService;

    
    
  
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //obtenemos de la solicitud el cabecero de autorizacion, donde deberia venir el token de autenticacion
        String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

        
        //validamos si en el cabecero de autorizacion viene el token como un Bearer token, si no el token es invalido
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            //obtenemos el texto del token sin la palabra Bearer
            jwtToken = requestTokenHeader.substring(7);
            try {
                //extreemos el username
                username = this.feingService.extractUsername(jwtToken);
            } catch (ExpiredJwtException ex) {
                System.out.println("El Token ha Expirado");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Token Invalido, No empieza Con Bearer string");
        }
        
        //validamos si el username es valido
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            //buscamos el usuario en base de datos
            UserDetails userDetails = this.userDetailsServiceImple.loadUserByUsername(username);
            // validamos el token 
            TokenDto tokenDto = this.feingService.validarToken(jwtToken);
            //si es valido se autentica
            if (tokenDto != null) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                request.setAttribute("token", requestTokenHeader);
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        } else {
            System.out.println("El Token no es Valido");
        }
        filterChain.doFilter(request, response);
    }

}
