

package com.cartera_temp.cartera_temp.Service;

import com.cartera_temp.cartera_temp.Models.Notificaciones;
import java.util.List;

public interface NotificacionesService {

    public Notificaciones crearNotificaciones(Notificaciones notificaciones);
    
    public List<Notificaciones> getNotificacionesAscendente(String username);
    
    public List<Notificaciones> getNotificacionesVencidasAscendente(String username);
    
    
    
}
