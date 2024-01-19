
package com.cartera_temp.cartera_temp.Controllers;

import com.cartera_temp.cartera_temp.Models.Notificaciones;
import com.cartera_temp.cartera_temp.Service.NotificacionesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/notificaciones")
@CrossOrigin(origins = "*", maxAge = 3600)
public class NotificacionesController {
    
    private final NotificacionesService notificacionesService;

    public NotificacionesController(NotificacionesService notificacionesService) {
        this.notificacionesService = notificacionesService;
    }
    
    @GetMapping("/getAllNotificaciones")
    public ResponseEntity<List<Notificaciones>> obtenerNotificacionesByAsignated(@RequestParam(name = "username") String username){
        List<Notificaciones> notificaciones = notificacionesService.getNotificacionesAscendente(username);
        return ResponseEntity.ok(notificaciones);
    }
    
    @GetMapping("/getAllNotificacionesVencidas")
    public ResponseEntity<List<Notificaciones>> obtenerNotificacionesVencidasByAsignated(@RequestParam(name = "username") String username){
        List<Notificaciones> notificaciones = notificacionesService.getNotificacionesVencidasAscendente(username);
        return ResponseEntity.ok(notificaciones);
    }
    
    @PutMapping("/desactivateNotification")
    public ResponseEntity<HttpStatus> desactivateNotificaciones(@RequestParam(name = "idNotificion")Long id){
        boolean bol = notificacionesService.desactivateNotificacion(id);
        if(bol == false){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/hideNotification")
    public ResponseEntity<HttpStatus> hideNotificaciones(@RequestParam(name = "idNotificion")Long id){
        boolean bol = notificacionesService.hideNotificationChecked(id);
        if(bol == false){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/getAllNotificacionesRealizadas")
    public ResponseEntity<List<Notificaciones>> obtenerNotificacionesRealizadasByAsignated(@RequestParam(name = "username") String username){
        List<Notificaciones> notificaciones = notificacionesService.getRealizadas(username);
        return ResponseEntity.ok(notificaciones);
    }

}
