
package com.cartera_temp.cartera_temp.ServiceImpl;

import com.cartera_temp.cartera_temp.Models.Notificaciones;
import com.cartera_temp.cartera_temp.Service.NotificacionesService;
import com.cartera_temp.cartera_temp.repository.NotificacionesRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class NotificacionesServiceImpl implements NotificacionesService{
    
    private final NotificacionesRepository notificacionesRepository;

    public NotificacionesServiceImpl(NotificacionesRepository notificacionesRepository) {
        this.notificacionesRepository = notificacionesRepository;
    }
    
    

    @Override
    public Notificaciones crearNotificaciones(Notificaciones notificaciones) {
        Notificaciones notificacionesSaved = notificacionesRepository.save(notificaciones);
        return notificacionesSaved;
    }

    @Override
    public List<Notificaciones> listarNotificacionesByAsignated(Long idAsignated) {
      List<Notificaciones> notify = notificacionesRepository.findByDesignatedTo(idAsignated);
      return notify;
    }
    
    

}
