package com.cartera_temp.cartera_temp.ServiceImpl;

import com.cartera_temp.cartera_temp.Dtos.GestionResponse;
import com.cartera_temp.cartera_temp.Dtos.GestionesDto;
import com.cartera_temp.cartera_temp.Models.AsesorCartera;
import com.cartera_temp.cartera_temp.Models.Clasificacion;
import com.cartera_temp.cartera_temp.Models.CuentasPorCobrar;
import com.cartera_temp.cartera_temp.Models.Gestiones;
import com.cartera_temp.cartera_temp.ModelsClients.Usuario;
import com.cartera_temp.cartera_temp.Service.AsesorCarteraService;
import com.cartera_temp.cartera_temp.Service.ClasificacionService;
import com.cartera_temp.cartera_temp.Service.CuentasPorCobrarService;
import com.cartera_temp.cartera_temp.Service.GestionesService;
import com.cartera_temp.cartera_temp.Service.UsuarioClientService;
import com.cartera_temp.cartera_temp.Utils.Functions;
import com.cartera_temp.cartera_temp.repository.ClasificacionRepository;
import com.cartera_temp.cartera_temp.repository.CuentasPorCobrarRepository;
import com.cartera_temp.cartera_temp.repository.GestionesRepository;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestionesServiceImpl implements GestionesService{
    
    @Autowired GestionesRepository gestionesRepository;
    @Autowired CuentasPorCobrarRepository cuentaCobrarRepository;
    @Autowired UsuarioClientService usuarioClientService;
    @Autowired ClasificacionRepository clasificacionRepository;
    @Autowired AsesorCarteraService asesorCartera;

    @Override
    public GestionResponse saveOneGestion(GestionesDto dto) {
        
        if(dto.getNumeroObligacion().equals(null)||dto.getNumeroObligacion().equals("")|| dto.getClasificacion().equals(null)||dto.getClasificacion().equals("")|| dto.getAsesorCartera().equals(null)|| dto.getAsesorCartera().equals("")||dto.getGestion().equals("")||dto.getGestion().equals(null)){
            return null;
        }
        
        CuentasPorCobrar cpc = cuentaCobrarRepository.findByNumeroObligacion(dto.getNumeroObligacion());
        if(Objects.isNull(cpc)){
            return null;
        }
        
        Usuario usu = usuarioClientService.obtenerUsuario(dto.getAsesorCartera());
        if(Objects.isNull(usu)){
            return null;
        }
        
        AsesorCartera asesor = asesorCartera.findAsesor(usu.getIdUsuario());
        if(Objects.isNull(asesor)){
            return null;
        }
        
        Clasificacion clasificacion = clasificacionRepository.findClasificacionByTipoClasificacion(dto.getClasificacion());
        if(Objects.isNull(clasificacion)){
            return null;
        }
        
        Gestiones gestion = new Gestiones();
        
        gestion.setAsesorCartera(asesor);
        gestion.setBanco(cpc.getBanco());
        gestion.setClasificacion(clasificacion);
        gestion.setCuentasPorCobrar(cpc);
        gestion.setFechaCompromiso(dto.getFechaCompromiso());
        try {
            gestion.setFechaGestion(Functions.obtenerFechaYhora());
        } catch (ParseException ex) {
            Logger.getLogger(GestionesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        gestion.setGestion(dto.getGestion());
        gestion.setIsContacted(dto.isContact());
        gestion.setNombreCliente(cpc.getCliente());
        gestion.setNumeroDoc(cpc.getDocumentoCliente());
        gestion.setNumeroObligacion(dto.getNumeroObligacion());
        gestion.setSede(cpc.getSede());
        gestion.setValorCompromiso(dto.getValorCompromiso());
        gestion = gestionesRepository.save(gestion);
        
        ModelMapper map = new ModelMapper();
        GestionResponse gesRes = map.map(gestion, GestionResponse.class);
        
        gesRes.setAsesorCartera(usu.getNombres() + usu.getApellidos());
        
        return gesRes;
        
    }

    @Override
    public List<Gestiones> saveMultipleGestiones(List<GestionesDto> dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Gestiones> findHistoricoGestiones(String numeroObligacion) {
        
        if(numeroObligacion == "" || numeroObligacion == null){
            return null;
        }
        
        List<Gestiones> gestion = gestionesRepository.findGestionesByNumeroObligacion(numeroObligacion);
        
        if(Objects.isNull(gestion)){
            return null;
        }
        
        return gestion;
        
    }
    
}
