package com.cartera_temp.cartera_temp.ServiceImpl;

import com.cartera_temp.cartera_temp.Dtos.GestionResponse;
import com.cartera_temp.cartera_temp.Dtos.GestionToSaveDto;
import com.cartera_temp.cartera_temp.Dtos.GestionesDto;
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

    public GestionesServiceImpl(GestionesRepository gestionesRepository, CuentasPorCobrarRepository cuentaCobrarRepository, UsuarioClientService usuarioClientService, ClasificacionRepository clasificacionRepository, AsesorCarteraService asesorCartera, FileService fileService, SedeRepository sedeRepository, BancoRepository bancoRepository) {
        this.gestionesRepository = gestionesRepository;
        this.cuentaCobrarRepository = cuentaCobrarRepository;
        this.usuarioClientService = usuarioClientService;
        this.clasificacionRepository = clasificacionRepository;
        this.asesorCartera = asesorCartera;
        this.fileService = fileService;
        this.sedeRepository = sedeRepository;
        this.bancoRepository = bancoRepository;
    }

    @Override
    public GestionResponse saveOneGestion(GestionToSaveDto dto) {

        if (dto.getNumeroObligacion() == null || dto.getNumeroObligacion().equals("") || dto.getClasificacion() == null || dto.getClasificacion().equals("") || dto.getGestion().equals("") || dto.getGestion() == null) {
            return null;
        }

        CuentasPorCobrar cpc = cuentaCobrarRepository.findByNumeroObligacion(dto.getNumeroObligacion());
        if (Objects.isNull(cpc)) {
            return null;
        }

        Usuario usu = usuarioClientService.obtenerUsuarioById(cpc.getAsesor().getUsuarioId());
        if (Objects.isNull(usu)) {
            return null;
        }

        AsesorCartera asesor = asesorCartera.findAsesor(usu.getIdUsuario());
        if (Objects.isNull(asesor)) {
            return null;
        }

        Clasificacion clasificacion = clasificacionRepository.findClasificacionByTipoClasificacion(dto.getClasificacion());
        if (Objects.isNull(clasificacion)) {
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
    public List<GestionesDto> saveMultipleGestiones(MultipartFile file, String delimitante) {
        List<GestionesDto> gestiones = fileService.readFileGestiones(file, delimitante);

        //List<Gestiones> gestionesSaved = guardarGestiones(gestiones);
        return gestiones;
    }

    @Override
    public List<GestionResponse> findHistoricoGestiones(String numeroObligacion) {

        if ("".equals(numeroObligacion) || numeroObligacion == null) {
            return null;
        }

        List<Gestiones> gestion = gestionesRepository.findGestionesByNumeroObligacion(numeroObligacion);

        if (Objects.isNull(gestion)) {
            return null;
        }

        List<GestionResponse> gesResList = new ArrayList<>();
        for (Gestiones gestiones : gestion) {
            ModelMapper map = new ModelMapper();
            GestionResponse gesRes = map.map(gestiones, GestionResponse.class);

            Usuario usu = usuarioClientService.obtenerUsuarioById(gestiones.getAsesorCartera().getUsuarioId());
            if (Objects.isNull(usu)) {
                return null;
            }

            gesRes.setAsesorCartera(usu.getNombres() + usu.getApellidos());

            gesResList.add(gesRes);
        }

        return gesResList;

    }

    @Override
    public List<Gestiones> guardarGestiones(List<GestionesDto> gestiones) {

        List<Gestiones> gestionesSaved = new ArrayList<>();

        for (GestionesDto gestione : gestiones) {
            Gestiones newGestion = new Gestiones();

            newGestion.setDatosAdicionales(gestione.getDetallesAdicionales());
            newGestion.setFechaCompromiso(gestione.getFechaCompromiso());
            newGestion.setFechaGestion(gestione.getFechaGestion());
            newGestion.setGestion(gestione.getGestion());
            newGestion.setGestionLlamada(gestione.getGestionLlamada());
            newGestion.setNombreCliente(gestione.getCliente());
            newGestion.setNumeroDoc(gestione.getCedula());
            newGestion.setNumeroObligacion(gestione.getNumeroObligacion());
            newGestion.setValorCompromiso(gestione.getValorCompromiso());

            CuentasPorCobrar cuenta = cuentaCobrarRepository.findByNumeroObligacion(gestione.getNumeroObligacion());
            if (Objects.nonNull(cuenta)) {
                newGestion.setAsesorCartera(cuenta.getAsesor());
                newGestion.setBanco(cuenta.getBanco());
                newGestion.setSede(cuenta.getSede());

            }

            Clasificacion clasi = clasificacionRepository.findClasificacionByTipoClasificacion(gestione.getClasificacion());
            if (Objects.nonNull(clasi)) {
                newGestion.setClasificacion(clasi);
            }

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

        Gestiones ultimaGestion = gestionesRepository.findTopByNumeroObligacionOrderByFechaGestionDesc(numeroObligacion);

        if (Objects.isNull(ultimaGestion)) {
            return null;
        }

        String datoAdicionalUltimaGestion = ultimaGestion.getDatosAdicionales();
        
        return datoAdicionalUltimaGestion;

    }

}
