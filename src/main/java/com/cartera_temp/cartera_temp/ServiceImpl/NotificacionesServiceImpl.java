package com.cartera_temp.cartera_temp.ServiceImpl;

import com.cartera_temp.cartera_temp.FeignClients.usuario_client;
import com.cartera_temp.cartera_temp.Models.Notificaciones;
import com.cartera_temp.cartera_temp.ModelsClients.Usuario;
import com.cartera_temp.cartera_temp.Service.NotificacionesService;
import com.cartera_temp.cartera_temp.Utils.Functions;
import com.cartera_temp.cartera_temp.repository.NotificacionesRepository;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

@Service
public class NotificacionesServiceImpl implements NotificacionesService {

    private final NotificacionesRepository notificacionesRepository;
    private final usuario_client usuarioClient;

    public NotificacionesServiceImpl(NotificacionesRepository notificacionesRepository, usuario_client usuarioClient) {
        this.notificacionesRepository = notificacionesRepository;
        this.usuarioClient = usuarioClient;
    }

    @Override
    public Notificaciones crearNotificaciones(Notificaciones notificaciones) {
        Notificaciones notificacionesSaved = notificacionesRepository.save(notificaciones);
        return notificacionesSaved;
    }
    
    @Override
    public List<Notificaciones> getNotificacionesAscendente(String username) {
        
        Usuario user = usuarioClient.getUserByUsername(username);
        if (Objects.isNull(user)) {
            return null;
        }
        
        List<Notificaciones> notiFind = notificacionesRepository.findAllByDesignatedToOrderByFechaCreacionAsc(user.getIdUsuario());
        return notiFind;
        
    }

    @Override
    public List<Notificaciones> getNotificacionesVencidasAscendente(String username) {
        
        Usuario user = usuarioClient.getUserByUsername(username);
        if (Objects.isNull(user)) {
            return null;
        }
        
        List<Notificaciones> notiFind = new ArrayList<>();
        try {
            notiFind = notificacionesRepository.findAllByDesignatedToAndFechaFinalizacionBefore(user.getIdUsuario(), Functions.obtenerFechaYhora());
        } catch (ParseException ex) {
            Logger.getLogger(NotificacionesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notiFind;
        
    }
}
