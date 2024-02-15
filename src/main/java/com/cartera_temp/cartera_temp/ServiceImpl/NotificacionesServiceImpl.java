package com.cartera_temp.cartera_temp.ServiceImpl;

import com.cartera_temp.cartera_temp.Dtos.NotificacionRequest;
import com.cartera_temp.cartera_temp.FeignClients.usuario_client;
import com.cartera_temp.cartera_temp.Models.AsesorCartera;
import com.cartera_temp.cartera_temp.Models.Gestiones;
import com.cartera_temp.cartera_temp.Models.Notificaciones;
import com.cartera_temp.cartera_temp.Models.Tarea;
import com.cartera_temp.cartera_temp.ModelsClients.Usuario;
import com.cartera_temp.cartera_temp.Service.NotificacionesService;
import com.cartera_temp.cartera_temp.Utils.Functions;
import com.cartera_temp.cartera_temp.repository.GestionesRepository;
import com.cartera_temp.cartera_temp.repository.NotificacionesRepository;
import com.cartera_temp.cartera_temp.repository.TareaRepository;
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
    private final TareaRepository tareaRepository;
    private final GestionesRepository gestionesRepository;

    public NotificacionesServiceImpl(NotificacionesRepository notificacionesRepository, usuario_client usuarioClient, TareaRepository tareaRepository, GestionesRepository gestionesRepository) {
        this.notificacionesRepository = notificacionesRepository;
        this.usuarioClient = usuarioClient;
        this.tareaRepository = tareaRepository;
        this.gestionesRepository = gestionesRepository;
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

        List<Notificaciones> notiFind = notificacionesRepository.obtenerTodasNotificaciones(true, user.getIdUsuario(), "VER");
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
            notiFind = notificacionesRepository.findByIsActiveAndDesignatedToAndVerRealizadasAndFechaFinalizacionBefore(true, user.getIdUsuario(), "VER", Functions.obtenerFechaYhora());
        } catch (ParseException ex) {
            Logger.getLogger(NotificacionesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notiFind;

    }

    @Override
    public boolean desactivateNotificacion(NotificacionRequest notificacionRequest) {
        Notificaciones noti = notificacionesRepository.findById(notificacionRequest.getIdNotificacion()).orElse(null);
        if (Objects.nonNull(noti)) {
            noti.setIsActive(false);
            
            noti = notificacionesRepository.save(noti);

            if (Objects.isNull(notificacionRequest.getIdClasificacion())) {
                Gestiones gestion = gestionesRepository.findFirstByNumeroObligacionAndFechaGestion(notificacionRequest.getNumeroObligacion(), notificacionRequest.getFechaCreacion());
                if(Objects.isNull(gestion)){
                    return false;
                }
                Tarea tarea = tareaRepository.findById(gestion.getClasificacionGestion().getIdClasificacionGestion()).orElse(null);
                if (Objects.nonNull(tarea)) {
                    tarea.setIsActive(false);
                    tarea = tareaRepository.save(tarea);
                     return true;
                }

                return false;
            } else {
                Tarea tarea = tareaRepository.findById(notificacionRequest.getIdClasificacion()).orElse(null);
                if (Objects.nonNull(tarea)) {
                    tarea.setIsActive(false);
                    tarea = tareaRepository.save(tarea);
                    return true;
                }
                return false;

                
            }

        } else {
            return false;
        }
    }

    @Override
    public boolean hideNotificationChecked(Long idNotificacion) {

        Notificaciones noti = notificacionesRepository.findById(idNotificacion).orElse(null);
        if (Objects.nonNull(noti)) {
            noti.setVerRealizadas("HIDE");
            noti = notificacionesRepository.save(noti);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public List<Notificaciones> getRealizadas(String username) {

        if (username == "" || username == null) {
            return null;
        }

        Usuario usu = usuarioClient.getUserByUsername(username);
        if (Objects.isNull(usu)) {
            return null;
        }

        List<Notificaciones> noti = notificacionesRepository.findByIsActiveAndDesignatedToAndVerRealizadasAndTipoGestionOrderByFechaCreacionAsc(false, usu.getIdUsuario(), "VER", "TAREA");
        return noti;

    }

    @Override
    public Notificaciones getById(Long id) {
        return notificacionesRepository.findById(id).orElse(null);
    }

    @Override
    public List<Notificaciones> findBySede(String sede, String username, String tipo) {

        Usuario user = usuarioClient.getUserByUsername(username);
        if (Objects.isNull(user)) {
            return null;
        }

        List<Notificaciones> notificacionesBySede = new ArrayList<>();
        try {
            if(tipo.equals("CEDULA")){
                notificacionesBySede = notificacionesRepository.findByIsActiveAndDesignatedToAndVerRealizadasAndFechaFinalizacionBeforeAndClienteContaining(true, user.getIdUsuario(), "VER", Functions.obtenerFechaYhora(), sede);
            }
            if(tipo.equals("SEDE")){
                notificacionesBySede = notificacionesRepository.findByIsActiveAndDesignatedToAndVerRealizadasAndFechaFinalizacionBeforeAndNumeroObligacionContaining(true, user.getIdUsuario(), "VER", Functions.obtenerFechaYhora(), sede);
            }
            
        } catch (ParseException ex) {
            Logger.getLogger(NotificacionesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notificacionesBySede;
    }

    @Override
    public List<Notificaciones> findBySedeAll(String sede, String username, String tipo) {

        Usuario user = usuarioClient.getUserByUsername(username);
        if (Objects.isNull(user)) {
            return null;
        }

        List<Notificaciones> notificacionesBySede = null;
        
        if(tipo.equals("SEDE")){
           notificacionesBySede  = notificacionesRepository.findByIsActiveAndDesignatedToAndNumeroObligacionContaining(true, user.getIdUsuario(),sede);
        }
        
         if(tipo.equals("CEDULA")){
                notificacionesBySede = notificacionesRepository.findByIsActiveAndDesignatedToAndClienteContaining(true, user.getIdUsuario(), sede);
            }

        return notificacionesBySede;
    }

    @Override
    public List<Notificaciones> findBySedeRealizadas(String sede, String username, String tipo) {

        Usuario usu = usuarioClient.getUserByUsername(username);
        if (Objects.isNull(usu)) {
            return null;
        }

        List<Notificaciones> noti = null;

         if(tipo.equals("SEDE")){
           noti  = notificacionesRepository.findByIsActiveAndDesignatedToAndVerRealizadasAndNumeroObligacionContainingAndTipoGestionOrderByFechaCreacionAsc(false, usu.getIdUsuario(), "HIDE", sede,"TAREA");
        }
        
         if(tipo.equals("CEDULA")){
                noti = notificacionesRepository.findByIsActiveAndDesignatedToAndVerRealizadasAndClienteContainingAndTipoGestionOrderByFechaCreacionAsc(false, usu.getIdUsuario(), "HIDE", sede, "TAREA");
            }
        
        return noti;
    }
}
