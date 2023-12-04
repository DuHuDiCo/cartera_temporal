package com.cartera_temp.cartera_temp.ServiceImpl;

import GestionesDataDto.GestionesDataDto;
import com.cartera_temp.cartera_temp.Dtos.GestionResponse;
import com.cartera_temp.cartera_temp.Dtos.GestionToSaveDto;
import com.cartera_temp.cartera_temp.Dtos.GestionesDto;
import com.cartera_temp.cartera_temp.Models.AcuerdoPago;
import com.cartera_temp.cartera_temp.Models.AsesorCartera;
import com.cartera_temp.cartera_temp.Models.Clasificacion;
import com.cartera_temp.cartera_temp.Models.CuentasPorCobrar;
import com.cartera_temp.cartera_temp.Models.Gestiones;
import com.cartera_temp.cartera_temp.ModelsClients.Usuario;
import com.cartera_temp.cartera_temp.Service.AsesorCarteraService;
import com.cartera_temp.cartera_temp.Service.FileService;
import com.cartera_temp.cartera_temp.Service.GestionesService;
import com.cartera_temp.cartera_temp.Service.UsuarioClientService;
import com.cartera_temp.cartera_temp.Utils.Functions;
import com.cartera_temp.cartera_temp.Utils.SaveFiles;
import com.cartera_temp.cartera_temp.repository.BancoRepository;
import com.cartera_temp.cartera_temp.repository.ClasificacionRepository;
import com.cartera_temp.cartera_temp.repository.CuentasPorCobrarRepository;
import com.cartera_temp.cartera_temp.repository.GestionesRepository;
import com.cartera_temp.cartera_temp.repository.SedeRepository;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class GestionesServiceImpl implements GestionesService {
    
    private final GestionesRepository gestionesRepository;
    private final CuentasPorCobrarRepository cuentaCobrarRepository;
    private final UsuarioClientService usuarioClientService;
    private final ClasificacionRepository clasificacionRepository;
    private final AsesorCarteraService asesorCartera;
    private final FileService fileService;
    private final SedeRepository sedeRepository;
    private final BancoRepository bancoRepository;
    private final SaveFiles saveFiles;
    
    public GestionesServiceImpl(GestionesRepository gestionesRepository, CuentasPorCobrarRepository cuentaCobrarRepository, UsuarioClientService usuarioClientService, ClasificacionRepository clasificacionRepository, AsesorCarteraService asesorCartera, FileService fileService, SedeRepository sedeRepository, BancoRepository bancoRepository, SaveFiles saveFiles) {
        this.gestionesRepository = gestionesRepository;
        this.cuentaCobrarRepository = cuentaCobrarRepository;
        this.usuarioClientService = usuarioClientService;
        this.clasificacionRepository = clasificacionRepository;
        this.asesorCartera = asesorCartera;
        this.fileService = fileService;
        this.sedeRepository = sedeRepository;
        this.bancoRepository = bancoRepository;
        this.saveFiles = saveFiles;
    }
    
    @Override
    public GestionResponse saveOneGestion(GestionToSaveDto dto) {
        
        return null;
        
    }
    
    @Override
    public List<Gestiones> saveMultipleGestiones(GestionesDataDto dataDto) {
        
        MultipartFile multipartFile = saveFiles.convertirFile(dataDto.getMultipartFile());
        
        List<GestionesDto> gestiones = fileService.readFileGestiones(multipartFile, dataDto.getDelimitante());
        //        List<Gestiones> gestionesSaved = guardarGestiones(gestiones);

        System.out.println(gestiones.size());
        List<Gestiones> gestionesSaved = new ArrayList<>();
        return gestionesSaved;
    }
    
    @Override
    public List<GestionResponse> findHistoricoGestiones(String numeroObligacion) {
        
        if ("".equals(numeroObligacion) || numeroObligacion == null) {
            return null;
        }

//        List<Gestiones> gestion = gestionesRepository.findGestionesByNumeroObligacion(numeroObligacion);
//        if (Objects.isNull(gestion)) {
//            return null;
//        }
//        List<GestionResponse> gesResList = new ArrayList<>();
//        for (Gestiones gestiones : gestion) {
//            ModelMapper map = new ModelMapper();
//            GestionResponse gesRes = map.map(gestiones, GestionResponse.class);
//
//            Usuario usu = usuarioClientService.obtenerUsuarioById(gestiones.getAsesorCartera().getUsuarioId());
//            if (Objects.isNull(usu)) {
//                return null;
//            }
//
//            gesRes.setAsesorCartera(usu.getNombres() + usu.getApellidos());
//
//            gesResList.add(gesRes);
//        }
        return null;
        
    }
    
    @Override
    public List<Gestiones> guardarGestiones(List<GestionesDto> gestiones) {
        
        List<Gestiones> gestionesSaved = new ArrayList<>();
        
        for (GestionesDto gestione : gestiones) {
            Gestiones newGestion = new Gestiones();
            
            newGestion.setDatosAdicionales(gestione.getDetallesAdicionales());
            
            CuentasPorCobrar cuenta = cuentaCobrarRepository.findByNumeroObligacion(gestione.getNumeroObligacion());
            if (Objects.isNull(cuenta)) {
                continue;
                
            }
            newGestion.setAsesorCartera(cuenta.getAsesor());
            
            Clasificacion clasi = clasificacionRepository.findClasificacionByTipoClasificacion(gestione.getClasificacion());
            if (Objects.isNull(clasi)) {
                continue;
            }
            
            newGestion.setClasificacion(clasi);
            newGestion.setNumeroObligacion(gestione.getNumeroObligacion());
            newGestion.setFechaGestion(gestione.getFechaGestion());
            
            cuenta.agregarGestion(newGestion);
            gestionesSaved.add(newGestion);
            
        }
        
        return gestionesRepository.saveAll(gestionesSaved);
    }
    
    @Override
    public String sendLastDatoAdicional(String numeroObligacion) {
        
        if ("".equals(numeroObligacion) || numeroObligacion == null) {
            return null;
        }

//        Gestiones ultimaGestion = gestionesRepository.findTopByNumeroObligacionOrderByFechaGestionDesc(numeroObligacion);
//        if (Objects.isNull(ultimaGestion)) {
//            return null;
//        }
//
//        String datoAdicionalUltimaGestion = ultimaGestion.getDatosAdicionales();
        return null;
        
    }
    
}
