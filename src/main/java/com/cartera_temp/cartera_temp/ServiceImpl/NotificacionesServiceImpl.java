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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public  Page<Notificaciones> getNotificacionesAscendente(String username, Pageable pageble) {

        Usuario user = usuarioClient.getUserByUsername(username);
        if (Objects.isNull(user)) {
            return null;
        }

       Page<Notificaciones> notiFind = notificacionesRepository.obtenerTodasNotificaciones(true, user.getIdUsuario(), "VER", pageble);
        return notiFind;

    }

    @Override
    public Page<Notificaciones> getNotificacionesVencidasAscendente(String username, Pageable pageable) {

        Usuario user = usuarioClient.getUserByUsername(username);
        if (Objects.isNull(user)) {
            return null;
        }

         Page<Notificaciones> notiFind = null;
        try {
            notiFind = notificacionesRepository.findByIsActiveAndDesignatedToAndVerRealizadasAndFechaFinalizacionBefore(true, user.getIdUsuario(), "VER", Functions.obtenerFechaYhora(), pageable);
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
    public  Page<Notificaciones> getRealizadas(String username, Pageable  pageable) {

        if (username == "" || username == null) {
            return null;
        }

        Usuario usu = usuarioClient.getUserByUsername(username);
        if (Objects.isNull(usu)) {
            return null;
        }

        Page<Notificaciones> noti = notificacionesRepository.findByIsActiveAndDesignatedToAndVerRealizadasAndTipoGestionOrderByFechaCreacionAsc(false, usu.getIdUsuario(), "VER", "TAREA", pageable);
        return noti;

    }

    @Override
    public Notificaciones getById(Long id) {
        return notificacionesRepository.findById(id).orElse(null);
    }

    @Override
    public  Page<Notificaciones> findBySede(String sede, String username, String tipo, Pageable pageable) {

        Usuario user = usuarioClient.getUserByUsername(username);
        if (Objects.isNull(user)) {
            return null;
        }

         Page<Notificaciones> notificacionesBySede = null;
        try {
            if(tipo.equals("CEDULA")){
                notificacionesBySede = notificacionesRepository.findByIsActiveAndDesignatedToAndVerRealizadasAndFechaFinalizacionBeforeAndClienteContaining(true, user.getIdUsuario(), "VER", Functions.obtenerFechaYhora(), sede, pageable);
            }
            if(tipo.equals("SEDE")){
                notificacionesBySede = notificacionesRepository.findByIsActiveAndDesignatedToAndVerRealizadasAndFechaFinalizacionBeforeAndNumeroObligacionContaining(true, user.getIdUsuario(), "VER", Functions.obtenerFechaYhora(), sede, pageable);
            }
            
        } catch (ParseException ex) {
            Logger.getLogger(NotificacionesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notificacionesBySede;
    }

    @Override
    public  Page<Notificaciones> findBySedeAll(String sede, String username, String tipo, Pageable pageable) {

        Usuario user = usuarioClient.getUserByUsername(username);
        if (Objects.isNull(user)) {
            return null;
        }

        Page<Notificaciones> notificacionesBySede = null;
        
        if(tipo.equals("SEDE")){
           notificacionesBySede  = notificacionesRepository.findByIsActiveAndDesignatedToAndNumeroObligacionContaining(true, user.getIdUsuario(),sede, pageable);
        }
        
         if(tipo.equals("CEDULA")){
                notificacionesBySede = notificacionesRepository.findByIsActiveAndDesignatedToAndClienteContaining(true, user.getIdUsuario(), sede, pageable);
            }

        return notificacionesBySede;
    }

    @Override
    public  Page<Notificaciones> findBySedeRealizadas(String sede, String username, String tipo, Pageable pageable) {

        Usuario usu = usuarioClient.getUserByUsername(username);
        if (Objects.isNull(usu)) {
            return null;
        }

         Page<Notificaciones> noti= null;

         if(tipo.equals("SEDE")){
           noti  = notificacionesRepository.findByIsActiveAndDesignatedToAndVerRealizadasAndNumeroObligacionContainingAndTipoGestionOrderByFechaCreacionAsc(false, usu.getIdUsuario(), "VER", sede,"TAREA", pageable);
        }
        
         if(tipo.equals("CEDULA")){
                noti = notificacionesRepository.findByIsActiveAndDesignatedToAndVerRealizadasAndClienteContainingAndTipoGestionOrderByFechaCreacionAsc(false, usu.getIdUsuario(), "VER", sede, "TAREA", pageable);
            }
        
        return noti;
    }
}
