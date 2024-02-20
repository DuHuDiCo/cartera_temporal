

package com.cartera_temp.cartera_temp.Service;

import com.cartera_temp.cartera_temp.Dtos.NotificacionRequest;
import com.cartera_temp.cartera_temp.Models.Notificaciones;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotificacionesService {

    public Notificaciones crearNotificaciones(Notificaciones notificaciones);
    
    public  Page<Notificaciones> getNotificacionesAscendente(String username, Pageable pageble);
    
    public  Page<Notificaciones> getNotificacionesVencidasAscendente(String username, Pageable pageble);
    
    public boolean desactivateNotificacion( NotificacionRequest notificacionRequest);
    
    public boolean hideNotificationChecked(Long idNotificacion);
    
    public  Page<Notificaciones> getRealizadas(String username, Pageable pageble);
    
    public Notificaciones getById(Long id);
    
    public  Page<Notificaciones> findBySede(String sede,  String username, String tipo, Pageable pageble);
    public  Page<Notificaciones> findBySedeAll(String sede,  String username , String tipo, Pageable pageble);
     Page<Notificaciones> findBySedeRealizadas(String sede, String username, String tipo, Pageable pageble) ;
}
