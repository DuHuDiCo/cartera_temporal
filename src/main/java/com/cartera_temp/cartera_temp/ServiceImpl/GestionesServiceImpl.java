package com.cartera_temp.cartera_temp.ServiceImpl;

import GestionesDataDto.GestionesDataDto;
import com.cartera_temp.cartera_temp.Dtos.AcuerdoPagoDto;
import com.cartera_temp.cartera_temp.Dtos.GestionResponse;
import com.cartera_temp.cartera_temp.Dtos.GestionToSaveDto;
import com.cartera_temp.cartera_temp.Dtos.GestionesDto;
import com.cartera_temp.cartera_temp.Models.AcuerdoPago;
import com.cartera_temp.cartera_temp.Models.AsesorCartera;

import com.cartera_temp.cartera_temp.Models.ClasificacionGestion;
import com.cartera_temp.cartera_temp.Models.ClasificacionTarea;
import com.cartera_temp.cartera_temp.Models.CuentasPorCobrar;
import com.cartera_temp.cartera_temp.Models.Cuotas;
import com.cartera_temp.cartera_temp.Models.Gestiones;
import com.cartera_temp.cartera_temp.Models.Nota;
import com.cartera_temp.cartera_temp.Models.Tarea;
import com.cartera_temp.cartera_temp.ModelsClients.Usuario;
import com.cartera_temp.cartera_temp.Service.AsesorCarteraService;
import com.cartera_temp.cartera_temp.Service.FileService;
import com.cartera_temp.cartera_temp.Service.GestionesService;
import com.cartera_temp.cartera_temp.Service.UsuarioClientService;
import com.cartera_temp.cartera_temp.Utils.Functions;
import com.cartera_temp.cartera_temp.Utils.SaveFiles;
import com.cartera_temp.cartera_temp.repository.AcuerdoPagoRepository;
import com.cartera_temp.cartera_temp.repository.BancoRepository;
import com.cartera_temp.cartera_temp.repository.ClasificacionGestionRepository;

import com.cartera_temp.cartera_temp.repository.ClasificacionTareaRepository;
import com.cartera_temp.cartera_temp.repository.CuentasPorCobrarRepository;
import com.cartera_temp.cartera_temp.repository.CuotaRepository;
import com.cartera_temp.cartera_temp.repository.GestionesRepository;
import com.cartera_temp.cartera_temp.repository.NotaRepository;
import com.cartera_temp.cartera_temp.repository.SedeRepository;
import com.cartera_temp.cartera_temp.repository.TareaRepository;
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

    private final AsesorCarteraService asesorCartera;
    private final FileService fileService;
    private final SedeRepository sedeRepository;
    private final BancoRepository bancoRepository;
    private final SaveFiles saveFiles;
    private final ClasificacionTareaRepository clasificacionTareaRepository;
    private final AcuerdoPagoRepository acuerdoPagoRepository;
    private final ClasificacionGestionRepository clasificacionGestionRepository;
    private final NotaRepository notaRepository;
    private final TareaRepository tareaRepository;

    public GestionesServiceImpl(GestionesRepository gestionesRepository, CuentasPorCobrarRepository cuentaCobrarRepository, UsuarioClientService usuarioClientService, AsesorCarteraService asesorCartera, FileService fileService, SedeRepository sedeRepository, BancoRepository bancoRepository, SaveFiles saveFiles, ClasificacionTareaRepository clasificacionTareaRepository, AcuerdoPagoRepository acuerdoPagoRepository, ClasificacionGestionRepository clasificacionGestionRepository, NotaRepository notaRepository, TareaRepository tareaRepository) {
        this.gestionesRepository = gestionesRepository;
        this.cuentaCobrarRepository = cuentaCobrarRepository;
        this.usuarioClientService = usuarioClientService;
        this.asesorCartera = asesorCartera;
        this.fileService = fileService;
        this.sedeRepository = sedeRepository;
        this.bancoRepository = bancoRepository;
        this.saveFiles = saveFiles;
        this.clasificacionTareaRepository = clasificacionTareaRepository;
        this.acuerdoPagoRepository = acuerdoPagoRepository;
        this.clasificacionGestionRepository = clasificacionGestionRepository;
        this.notaRepository = notaRepository;
        this.tareaRepository = tareaRepository;
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

        Gestiones gestion = new Gestiones();

        gestion.setAsesorCartera(asesor);

        gestion.setNumeroObligacion(cpc.getNumeroObligacion());

        gestion.setCuentasPorCobrar(cpc);
        gestion.setDetallesAdicionales(dto.getDetallesAdicionales());
        gestion.setDetallesGestion(dto.getGestion());

        try {
            gestion.setFechaGestion(Functions.obtenerFechaYhora());
        } catch (ParseException ex) {
            Logger.getLogger(GestionesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        //ACUERDO DE PAGO
        if (dto.getClasificacion().getTipoClasificacion().equals("Acuerdo de Pago")) {

            AcuerdoPago acuerdoPago = new AcuerdoPago();

            acuerdoPago.setAsesor(asesor);
            acuerdoPago.setDetalle(dto.getClasificacion().getAcuerdoPago().getDetalle());
            try {
                acuerdoPago.setFechaAcuerdo(Functions.obtenerFechaYhora());
            } catch (ParseException ex) {
                Logger.getLogger(GestionesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            acuerdoPago.setFechaCompromiso(dto.getClasificacion().getAcuerdoPago().getFechaCompromiso());
            acuerdoPago.setTipoAcuerdo(dto.getClasificacion().getAcuerdoPago().getTipoAcuerdo());
            acuerdoPago.setClasificacion(dto.getClasificacion().getTipoClasificacion());
            acuerdoPago.setValorCuotaMensual(dto.getClasificacion().getAcuerdoPago().getValorCuotaMensual());
            acuerdoPago.setHonoriarioAcuerdo(dto.getClasificacion().getAcuerdoPago().getHonoriarioAcuerdo());
            acuerdoPago.setValorInteresesMora(dto.getClasificacion().getAcuerdoPago().getValorInteresesMora());
            acuerdoPago.setValorTotalAcuerdo(dto.getClasificacion().getAcuerdoPago().getValorTotalAcuerdo());
            

            for (Cuotas cuotas : dto.getClasificacion().getAcuerdoPago().getCuotasList()) {
                acuerdoPago.agregarCuota(cuotas);
            }

            acuerdoPago = acuerdoPagoRepository.save(acuerdoPago);

            gestion.setClasificacion(acuerdoPago);
           
        }

        //NOTA
        if (dto.getClasificacion().getTipoClasificacion().equals("Nota")) {

            Nota nota = new Nota();

            nota.setAsesor(asesor);
            try {
                nota.setFechaNota(Functions.obtenerFechaYhora());
            } catch (ParseException ex) {
                Logger.getLogger(GestionesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            nota.setDetalleNota(dto.getClasificacion().getNota().getDetalle());
            nota.setClasificacion(dto.getClasificacion().getTipoClasificacion());
                       
            

            nota = notaRepository.save(nota);

            gestion.setClasificacion(nota);
         
        }

        //TAREA
        if (dto.getClasificacion().getTipoClasificacion().equals("Tarea")) {

            Tarea tarea = new Tarea();

            tarea.setAsesor(asesor);
            tarea.setDetalleTarea(dto.getClasificacion().getTarea().getDetalleTarea());
            tarea.setFechaFinTarea(dto.getClasificacion().getTarea().getFechaFinTarea());
            try {
                tarea.setFechaTarea(Functions.obtenerFechaYhora());
            } catch (ParseException ex) {
                Logger.getLogger(GestionesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            ClasificacionTarea clasificacionTarea = clasificacionTareaRepository.findByClasificacionTarea(dto.getClasificacion().getTarea().getClasificacion());
            if (Objects.isNull(clasificacionTarea)) {
                return null;
            }
            tarea.setClasificacionTarea(clasificacionTarea);

           tarea.setClasificacion(dto.getClasificacion().getTipoClasificacion());

       
            tarea = tareaRepository.save(tarea);

            gestion.setClasificacion(tarea);
          

        }

        gestion = gestionesRepository.save(gestion);

        ModelMapper map = new ModelMapper();
        GestionResponse gesRes = map.map(gestion, GestionResponse.class);

        gesRes.setAsesorCartera(usu.getNombres() + usu.getApellidos());

        return gesRes;

    }

    @Override
    public List<Gestiones> saveMultipleGestiones(GestionesDataDto dataDto) {

        MultipartFile multipartFile = saveFiles.convertirFile(dataDto.getMultipartFile());

        List<GestionesDto> gestiones = fileService.readFileGestiones(multipartFile, dataDto.getDelimitante());
        List<Gestiones> gestionesSaved = guardarGestiones(gestiones);

        return gestionesSaved;
    }

    @Override
    public List<GestionResponse> findHistoricoGestiones(String numeroObligacion) {

        if ("".equals(numeroObligacion) || numeroObligacion == null) {
            return null;
        }

        List<Gestiones> gestion = gestionesRepository.findByNumeroObligacion(numeroObligacion);
        List<GestionResponse> gesResList = new ArrayList<>();
        if (Objects.isNull(gestion)) {
            return gesResList;
        }
        
        for (Gestiones gestiones : gestion) {
            ModelMapper map = new ModelMapper();
            GestionResponse gesRes = map.map(gestiones, GestionResponse.class);

            Usuario usu = usuarioClientService.obtenerUsuarioById(gestiones.getAsesorCartera().getUsuarioId());
            if (Objects.isNull(usu)) {
                return gesResList;
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

            CuentasPorCobrar cuenta = cuentaCobrarRepository.findByNumeroObligacion(gestione.getNumeroObligacion());
            if (Objects.isNull(cuenta)) {
                continue;

            }
            newGestion.setAsesorCartera(cuenta.getAsesor());
            newGestion.setNumeroObligacion(gestione.getNumeroObligacion());
            newGestion.setFechaGestion(gestione.getFechaGestion());

            ClasificacionTarea clasi = clasificacionTareaRepository.findByClasificacionTarea(gestione.getClasificacion());
            if (Objects.isNull(clasi)) {
                clasi = new ClasificacionTarea();
                clasi.setClasificacionTarea(gestione.getClasificacion().toUpperCase());
                clasi = clasificacionTareaRepository.save(clasi);
            }

            Nota nota = new Nota();
            nota.setDetalleNota(gestione.getDetallesAdicionales());
            try {
                nota.setFechaNota(Functions.obtenerFechaYhora());
            } catch (ParseException ex) {
                Logger.getLogger(GestionesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            nota.setAsesor(cuenta.getAsesor());

            ClasificacionGestion clasificacion = clasificacionGestionRepository.save(nota);

            newGestion.setClasificacion(clasificacion);

            cuenta.agregarGestion(newGestion);
            gestionesSaved.add(newGestion);

            System.out.println(gestionesSaved.size());

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

        String datoAdicionalUltimaGestion = ultimaGestion.getDetallesAdicionales();
        return datoAdicionalUltimaGestion;

    }

}
