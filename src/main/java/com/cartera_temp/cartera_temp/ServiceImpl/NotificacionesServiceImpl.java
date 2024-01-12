package com.cartera_temp.cartera_temp.ServiceImpl;

import com.cartera_temp.cartera_temp.FeignClients.usuario_client;
import com.cartera_temp.cartera_temp.Models.Notificaciones;
import com.cartera_temp.cartera_temp.ModelsClients.Usuario;
import com.cartera_temp.cartera_temp.Service.NotificacionesService;
import com.cartera_temp.cartera_temp.repository.NotificacionesRepository;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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
    public List<Notificaciones> listarNotificacionesByAsignated(String username) {

        Usuario user = usuarioClient.getUserByUsername(username);
        if (Objects.isNull(user)) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Date fechaInicio = cal.getTime();

        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        
        Date fechaFin = cal.getTime();
        List<Notificaciones> notify = notificacionesRepository.findByDesignatedToAndFechaFinalizacionBetween(user.getIdUsuario(), fechaInicio, fechaFin);
        return notify;
    }

}
