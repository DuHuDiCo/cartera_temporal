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
        notificaciones = notificacionesRepository.save(notificaciones);
        return notificaciones;
    }

    @Override
    public List<Notificaciones> getNotificacionesAscendente(String username) {

        Usuario user = usuarioClient.getUserByUsername(username);
        if (Objects.isNull(user)) {
            return null;
        }

        List<Notificaciones> notiFind = notificacionesRepository.findAllByIsActiveAndDesignatedToAndVerRealizadasOrderByFechaCreacionAsc(true, user.getIdUsuario(), "VER");
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
            notiFind = notificacionesRepository.findAllByIsActiveAndDesignatedToAndVerRealizadasAndFechaFinalizacionBefore(true, user.getIdUsuario(), "VER", Functions.obtenerFechaYhora());
        } catch (ParseException ex) {
            Logger.getLogger(NotificacionesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notiFind;

    }

    @Override
    public boolean desactivateNotificacion(Long idNotificacion) {
        Notificaciones noti = notificacionesRepository.findById(idNotificacion).orElse(null);
        if(Objects.nonNull(noti)){
            noti.setIsActive(false);
            noti = notificacionesRepository.save(noti);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean hideNotificationChecked(Long idNotificacion) {
        
        Notificaciones noti = notificacionesRepository.findById(idNotificacion).orElse(null);
        if(Objects.nonNull(noti)){
            noti.setVerRealizadas("HIDE");
            noti = notificacionesRepository.save(noti);
            return true;
        }else{
            return false;
        }
        
    }

    @Override
    public List<Notificaciones> getRealizadas(String username) {
        
        if(username == "" || username == null){
            return null;
        }
        
        Usuario usu = usuarioClient.getUserByUsername(username);
        if(Objects.isNull(usu)){
            return null;
        }
        
        List<Notificaciones> noti = notificacionesRepository.findAllByIsActiveAndDesignatedToAndVerRealizadasOrderByFechaCreacionAsc(false, usu.getIdUsuario(), "VER");
        return noti;
        
    }
}
