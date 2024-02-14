

package com.cartera_temp.cartera_temp.Service;

import com.cartera_temp.cartera_temp.Dtos.NotificacionRequest;
import com.cartera_temp.cartera_temp.Models.Notificaciones;
import java.util.List;

public interface NotificacionesService {

    public Notificaciones crearNotificaciones(Notificaciones notificaciones);
    
    public List<Notificaciones> getNotificacionesAscendente(String username);
    
    public List<Notificaciones> getNotificacionesVencidasAscendente(String username);
    
    public boolean desactivateNotificacion( NotificacionRequest notificacionRequest);
    
    public boolean hideNotificationChecked(Long idNotificacion);
    
    public List<Notificaciones> getRealizadas(String username);
    
    public Notificaciones getById(Long id);
    
    public List<Notificaciones> findBySede(String sede,  String username);
    public List<Notificaciones> findBySedeAll(String sede,  String username);
    List<Notificaciones> findBySedeRealizadas(String sede, String username) ;
}
