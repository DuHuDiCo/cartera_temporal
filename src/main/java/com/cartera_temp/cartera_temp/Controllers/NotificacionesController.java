
package com.cartera_temp.cartera_temp.Controllers;

import com.cartera_temp.cartera_temp.Models.Notificaciones;
import com.cartera_temp.cartera_temp.Service.NotificacionesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/notificaciones")
@CrossOrigin(origins = "*", maxAge = 3600)
public class NotificacionesController {
    
    private final NotificacionesService notificacionesService;

    public NotificacionesController(NotificacionesService notificacionesService) {
        this.notificacionesService = notificacionesService;
    }
    
    @GetMapping("/")
    public ResponseEntity<List<Notificaciones>> obtenerNotificacionesByAsignated(@RequestParam(name = "id") Long id){
        List<Notificaciones> notificaciones = notificacionesService.listarNotificacionesByAsignated(id);
        return ResponseEntity.ok(notificaciones);
    }

}