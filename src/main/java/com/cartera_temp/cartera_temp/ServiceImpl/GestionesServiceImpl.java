package com.cartera_temp.cartera_temp.ServiceImpl;

import GestionesDataDto.GestionesDataDto;
import com.cartera_temp.cartera_temp.Components.GenerarPdf;
import com.cartera_temp.cartera_temp.Dtos.AcuerdoPagoDto;
import com.cartera_temp.cartera_temp.Dtos.ClientesDto;
import com.cartera_temp.cartera_temp.Dtos.CuotaDto;
import com.cartera_temp.cartera_temp.Dtos.CuotasDto;
import com.cartera_temp.cartera_temp.Dtos.GestionResponse;
import com.cartera_temp.cartera_temp.Dtos.GestionToSaveDto;
import com.cartera_temp.cartera_temp.Dtos.GestionesDto;
import com.cartera_temp.cartera_temp.Dtos.LinkDto;
import com.cartera_temp.cartera_temp.Dtos.LinkToClient;
import com.cartera_temp.cartera_temp.Dtos.Telefono;
import com.cartera_temp.cartera_temp.FeignClients.ClientesClient;
import com.cartera_temp.cartera_temp.Models.AcuerdoPago;
import com.cartera_temp.cartera_temp.Models.AsesorCartera;

import com.cartera_temp.cartera_temp.Models.ClasificacionGestion;

import com.cartera_temp.cartera_temp.Models.CuentasPorCobrar;
import com.cartera_temp.cartera_temp.Models.Cuotas;
import com.cartera_temp.cartera_temp.Models.Gestiones;
import com.cartera_temp.cartera_temp.Models.NombresClasificacion;
import com.cartera_temp.cartera_temp.Models.HistoricoAcuerdosPago;
import com.cartera_temp.cartera_temp.Models.Nota;
import com.cartera_temp.cartera_temp.Models.Notificaciones;
import com.cartera_temp.cartera_temp.Models.Tarea;
import com.cartera_temp.cartera_temp.ModelsClients.Usuario;
import com.cartera_temp.cartera_temp.Service.AsesorCarteraService;
import com.cartera_temp.cartera_temp.Service.FileService;
import com.cartera_temp.cartera_temp.Service.GestionesService;
import com.cartera_temp.cartera_temp.Service.NotificacionesService;
import com.cartera_temp.cartera_temp.Service.UsuarioClientService;
import com.cartera_temp.cartera_temp.Utils.Functions;
import com.cartera_temp.cartera_temp.Utils.SaveFiles;
import com.cartera_temp.cartera_temp.repository.AcuerdoPagoRepository;
import com.cartera_temp.cartera_temp.repository.BancoRepository;
import com.cartera_temp.cartera_temp.repository.ClasificacionGestionRepository;

import com.cartera_temp.cartera_temp.repository.CuentasPorCobrarRepository;
import com.cartera_temp.cartera_temp.repository.CuotaRepository;
import com.cartera_temp.cartera_temp.repository.GestionesRepository;
import com.cartera_temp.cartera_temp.repository.NombresClasificacionRepository;
import com.cartera_temp.cartera_temp.repository.HistoricoAcuerdoPagoRepository;
import com.cartera_temp.cartera_temp.repository.NotaRepository;
import com.cartera_temp.cartera_temp.repository.SedeRepository;
import com.cartera_temp.cartera_temp.repository.TareaRepository;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
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
    private final NotificacionesService notificacionesService;
    private final AcuerdoPagoRepository acuerdoPagoRepository;
    private final ClasificacionGestionRepository clasificacionGestionRepository;
    private final NotaRepository notaRepository;
    private final TareaRepository tareaRepository;
    private final NombresClasificacionRepository nombresClasificacionRepository;
    private final CuotaRepository cuotaRepository;
    private final HistoricoAcuerdoPagoRepository historicoAcuerdoPagoRepository;
    private final ClientesClient clientesClient;
    private final HttpServletRequest request;
    private final GenerarPdf pdf;

    public GestionesServiceImpl(GestionesRepository gestionesRepository, CuentasPorCobrarRepository cuentaCobrarRepository, UsuarioClientService usuarioClientService, AsesorCarteraService asesorCartera, FileService fileService, SedeRepository sedeRepository, BancoRepository bancoRepository, SaveFiles saveFiles, NotificacionesService notificacionesService, AcuerdoPagoRepository acuerdoPagoRepository, ClasificacionGestionRepository clasificacionGestionRepository, NotaRepository notaRepository, TareaRepository tareaRepository, NombresClasificacionRepository nombresClasificacionRepository, CuotaRepository cuotaRepository, HistoricoAcuerdoPagoRepository historicoAcuerdoPagoRepository, ClientesClient clientesClient, HttpServletRequest request, GenerarPdf pdf) {
        this.gestionesRepository = gestionesRepository;
        this.cuentaCobrarRepository = cuentaCobrarRepository;
        this.usuarioClientService = usuarioClientService;
        this.asesorCartera = asesorCartera;
        this.fileService = fileService;
        this.sedeRepository = sedeRepository;
        this.bancoRepository = bancoRepository;
        this.saveFiles = saveFiles;
        this.notificacionesService = notificacionesService;
        this.acuerdoPagoRepository = acuerdoPagoRepository;
        this.clasificacionGestionRepository = clasificacionGestionRepository;
        this.notaRepository = notaRepository;
        this.tareaRepository = tareaRepository;
        this.nombresClasificacionRepository = nombresClasificacionRepository;
        this.cuotaRepository = cuotaRepository;
        this.historicoAcuerdoPagoRepository = historicoAcuerdoPagoRepository;
        this.clientesClient = clientesClient;
        this.request = request;
        this.pdf = pdf;
    }

    @Override
    public GestionResponse saveOneGestion(GestionToSaveDto dto) {

        if (dto.getNumeroObligacion() == null || dto.getNumeroObligacion().equals("") || dto.getClasificacion() == null || dto.getClasificacion().equals("")) {
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

        Usuario usuDesignated = usuarioClientService.obtenerUsuario(dto.getUsernameToSetNotificacion());
        if (Objects.isNull(usuDesignated)) {
            return null;
        }

        Usuario userNotifying = usuarioClientService.obtenerUsuario(dto.getUserNotifying());
        if (Objects.isNull(userNotifying)) {
            return null;
        }

        AsesorCartera asesor = asesorCartera.findAsesor(userNotifying.getIdUsuario());
        if (Objects.isNull(asesor)) {
            return null;
        }

        Gestiones gestion = new Gestiones();

        gestion.setAsesorCartera(asesor);

        gestion.setNumeroObligacion(cpc.getNumeroObligacion());

        gestion.setCuentasPorCobrar(cpc);
        gestion.setDetallesAdicionales(dto.getDetallesAdicionales());

        try {
            gestion.setFechaGestion(Functions.obtenerFechaYhora());
        } catch (ParseException ex) {
            Logger.getLogger(GestionesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        NombresClasificacion clasificacion = nombresClasificacionRepository.findByNombreAndTipo(dto.getClasificacion().getNombreClasificacion(), dto.getClasificacion().getTipoClasificacion());
        if (Objects.isNull(clasificacion)) {
            return null;
        }

        //ACUERDO DE PAGO
        if (clasificacion.getTipo().equals("Acuerdo de Pago".toUpperCase())) {

            AcuerdoPago acuerdoPago = new AcuerdoPago();

            acuerdoPago.setAsesor(asesor);
            acuerdoPago.setDetalle(dto.getClasificacion().getAcuerdoPago().getDetalle());
            try {
                acuerdoPago.setFechaAcuerdo(Functions.obtenerFechaYhora());
            } catch (ParseException ex) {
                Logger.getLogger(GestionesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                acuerdoPago.setFechaCompromiso(Functions.stringToDateAndFormat(dto.getClasificacion().getAcuerdoPago().getFechaCompromiso()));
            } catch (ParseException ex) {
                Logger.getLogger(GestionesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            acuerdoPago.setTipoAcuerdo(dto.getClasificacion().getAcuerdoPago().getTipoAcuerdo());
            acuerdoPago.setClasificacion(dto.getClasificacion().getTipoClasificacion());
            acuerdoPago.setValorCuotaMensual(dto.getClasificacion().getAcuerdoPago().getValorCuotaMensual());
            acuerdoPago.setHonorarioAcuerdo(dto.getClasificacion().getAcuerdoPago().getHonoriarioAcuerdo());
            acuerdoPago.setValorInteresesMora(dto.getClasificacion().getAcuerdoPago().getValorInteresesMora());
            acuerdoPago.setValorTotalAcuerdo(dto.getClasificacion().getAcuerdoPago().getValorTotalAcuerdo());
            acuerdoPago.setIsActive(true);

            for (CuotasDto cuotas : dto.getClasificacion().getAcuerdoPago().getCuotasList()) {
                Cuotas couta = new Cuotas();
                couta.setCapitalCuota(cuotas.getCapitalCuota());
                couta.setCumplio(false);
                try {
                    couta.setFechaVencimiento(Functions.stringToDate(cuotas.getFechaVencimiento()));
                } catch (ParseException ex) {
                    Logger.getLogger(GestionesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                couta.setHonorarios(cuotas.getHonorarios());
                couta.setInteresCuota(cuotas.getInteresCuota());
                couta.setNumeroCuota(cuotas.getNumeroCuota());
                couta.setValorCuota(cuotas.getValorCuota());

                acuerdoPago.agregarCuota(couta);
            }

            NombresClasificacion nombre = nombresClasificacionRepository.findFirstByNombre(clasificacion.getNombre());
            if (Objects.isNull(nombre)) {
                return null;
            }

            acuerdoPago.setNombresClasificacion(nombre);

            Notificaciones notificacion = new Notificaciones();
            notificacion.setTipoGestion("ACUERDO DE PAGO");
            try {
                notificacion.setFechaCreacion(Functions.obtenerFechaYhora());
            } catch (ParseException ex) {
                Logger.getLogger(GestionesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            notificacion.setFechaFinalizacion(acuerdoPago.getFechaCompromiso());
            notificacion.setNumeroObligacion(cpc.getNumeroObligacion());
            notificacion.setDesignatedTo(usuDesignated.getIdUsuario());
            notificacion.setIsActive(true);
            notificacion.setDesignatedBy(userNotifying.getNombres().toUpperCase().concat(" ").concat(userNotifying.getApellidos().toUpperCase()));
            notificacion.setVerRealizadas("VER");
            notificacion.setCliente(cpc.getCliente());
            acuerdoPago = acuerdoPagoRepository.save(acuerdoPago);
            notificacion = notificacionesService.crearNotificaciones(notificacion);

            gestion.setClasificacion(acuerdoPago);

        }

        //NOTA
        if (clasificacion.getTipo().equals("Nota".toUpperCase())) {

            Nota nota = new Nota();

            nota.setAsesor(asesor);
            try {
                nota.setFechaNota(Functions.obtenerFechaYhora());
            } catch (ParseException ex) {
                Logger.getLogger(GestionesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            nota.setDetalleNota(dto.getClasificacion().getNota().getDetalle());
            nota.setClasificacion(dto.getClasificacion().getTipoClasificacion());

            NombresClasificacion nombre = nombresClasificacionRepository.findFirstByNombre(clasificacion.getNombre());
            if (Objects.isNull(nombre)) {
                return null;
            }

            nota.setNombresClasificacion(nombre);

            nota = notaRepository.save(nota);

            gestion.setClasificacion(nota);

        }

        //TAREA
        if (clasificacion.getTipo().equals("Tarea".toUpperCase())) {

            Tarea tarea = new Tarea();

            tarea.setAsesor(asesor);
            tarea.setIsParteOfRecaudo(dto.getClasificacion().getTarea().getIsPartOfRecaudo());
            tarea.setDetalleTarea(dto.getClasificacion().getTarea().getDetalleTarea());
            tarea.setIsActive(true);
            try {
                tarea.setFechaFinTarea(Functions.stringToDateAndFormat(dto.getClasificacion().getTarea().getFechaFinTarea()));
            } catch (ParseException ex) {
                Logger.getLogger(GestionesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                tarea.setFechaTarea(Functions.obtenerFechaYhora());
            } catch (ParseException ex) {
                Logger.getLogger(GestionesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            tarea.setClasificacion(dto.getClasificacion().getTipoClasificacion());
            tarea.setDesignatedTo(usuDesignated.getIdUsuario());

            NombresClasificacion nombre = nombresClasificacionRepository.findFirstByNombre(clasificacion.getNombre());
            if (Objects.isNull(nombre)) {
                return null;
            }
            tarea.setNombresClasificacion(nombre);

            //guardar en tabla notificaciones
            Notificaciones notificacion = new Notificaciones();
            notificacion.setTipoGestion("TAREA");
            notificacion.setFechaCreacion(tarea.getFechaTarea());
            notificacion.setFechaFinalizacion(tarea.getFechaFinTarea());
            notificacion.setNumeroObligacion(cpc.getNumeroObligacion());
            notificacion.setDesignatedTo(tarea.getDesignatedTo());
            notificacion.setDesignatedBy(userNotifying.getNombres().toUpperCase().concat(userNotifying.getApellidos().toUpperCase()));
            notificacion.setVerRealizadas("VER");
            notificacion.setIsActive(true);
            notificacion.setCliente(cpc.getCliente());

            tarea = tareaRepository.save(tarea);

            notificacion = notificacionesService.crearNotificaciones(notificacion);

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

        List<Gestiones> gestion = gestionesRepository.findByNumeroObligacionOrderByFechaGestionDesc(numeroObligacion);
        List<GestionResponse> gesResList = new ArrayList<>();
        if (Objects.isNull(gestion)) {
            return gesResList;
        }

        for (Gestiones gestiones : gestion) {

            if (gestiones.getClasificacionGestion() instanceof AcuerdoPago) {
                AcuerdoPago acuerdo = (AcuerdoPago) gestiones.getClasificacionGestion();
                for (Cuotas cuotas : acuerdo.getCuotasList()) {
                    if (Objects.nonNull(cuotas.getPagos())) {
                        try {
                            String base64 = saveFiles.pdfToBase64(cuotas.getPagos().getReciboPago().getRuta());
                            cuotas.getPagos().getReciboPago().setRuta(base64);
                        } catch (IOException ex) {
                            Logger.getLogger(GestionesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }

            ModelMapper map = new ModelMapper();
            GestionResponse gesRes = map.map(gestiones, GestionResponse.class);

            Usuario usu = usuarioClientService.obtenerUsuarioById(gestiones.getAsesorCartera().getUsuarioId());
            if (Objects.isNull(usu)) {
                return gesResList;
            }

            gesRes.setAsesorCartera(usu.getNombres().concat(" ".concat(usu.getApellidos())));

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

    @Override
    @Transactional
    public void desactivateAcuerdoPago(Long idAcuerdoPago) {

        Gestiones gestion = gestionesRepository.findById(idAcuerdoPago).orElse(null);
        if (Objects.isNull(gestion)) {
            System.out.println("nulll");
            return;
        }

        AcuerdoPago acuerdo = new AcuerdoPago();

        List<Cuotas> cuotas = null;
        if (gestion.getClasificacionGestion() instanceof AcuerdoPago) {
            acuerdo = (AcuerdoPago) gestion.getClasificacionGestion();
            cuotas = new ArrayList<>(acuerdo.getCuotasList());

        } else {
            return;
        }

        HistoricoAcuerdosPago hap = new HistoricoAcuerdosPago();

        Usuario usuario = usuarioClientService.obtenerUsuarioById(acuerdo.getAsesor().getUsuarioId());
        if (Objects.isNull(usuario)) {
            System.out.println("null");
        }

        CuentasPorCobrar cpc = cuentaCobrarRepository.findByNumeroObligacion(acuerdo.getGestiones().getNumeroObligacion());

        String mora_total = acuerdo.getTipoAcuerdo();
        String valorAcuerdo = Double.toString(acuerdo.getValorTotalAcuerdo());
        String intMora = Double.toString(acuerdo.getValorInteresesMora());
        String valorCuotaMes = Double.toString(acuerdo.getValorCuotaMensual());
        String fechaCorte = acuerdo.getFechaAcuerdo().toString();
        double valorTotalCuotasPagadas = 0;
        int totalCuotasPagadas = 0;
        String cuotasTosave = "Cliente: ".concat(cpc.getCliente().concat("\n Numero de Obligacion: ").concat(cpc.getNumeroObligacion())
                .concat("\n Asesor Cartera: ").concat(usuario.getNombres()).concat(usuario.getApellidos())
                .concat("\n Acuerdo pactuado por mora o total: ").concat(mora_total)
                .concat("\n Valor del acuerdo de pago: $").concat(String.valueOf(valorAcuerdo))
                .concat("\n Intereses por mora: $").concat(String.valueOf(intMora))
                .concat("\n Valor de la cuota mensual: $").concat(String.valueOf(valorCuotaMes))
                .concat("\n Fecha de corte del acuerdo de pago: ").concat(String.valueOf(fechaCorte)).concat("\n"));

        acuerdo.setIsActive(false);

        for (Cuotas cuota : cuotas) {
            cuotasTosave = cuotasTosave.concat(Integer.toString(cuota.getNumeroCuota()).concat(" ").concat(Double.toString(cuota.getValorCuota())).concat(" ").concat(Double.toString(cuota.getCapitalCuota())).concat(" ").concat(cuota.getFechaVencimiento().toString()).concat(" ").concat(Double.toString(cuota.getHonorarios()))).concat("\n");

            if (cuota.isCumplio()) {
                valorTotalCuotasPagadas = valorTotalCuotasPagadas + cuota.getValorCuota();
                totalCuotasPagadas += 1;
            }

        }

        hap.setAsesorCartera(usuario.getNombres().concat(usuario.getApellidos()));
        hap.setFechaCreacionAcuerdo(acuerdo.getFechaAcuerdo());
        hap.setHistorico(cuotasTosave);
        hap.setNumeroObligacion(cpc.getNumeroObligacion());
        hap.setTotalValorCuotasPagadas(valorTotalCuotasPagadas);
        hap.setTotalCuotasPagadas(totalCuotasPagadas);
        hap.setTotalCuotasAcuerdo(cuotas.size());
        hap.setValorTotalAcuerdo(acuerdo.getValorTotalAcuerdo());

        hap = historicoAcuerdoPagoRepository.save(hap);

        if (Objects.nonNull(acuerdo)) {
            acuerdo.getCuotasList().clear();
            acuerdoPagoRepository.save(acuerdo);
        }
    }

    @Override
    public LinkToClient sendLinkAndPdfToClient(LinkDto dto) {

        if (dto.getNumeroObligacion() == "" || dto.getNumeroObligacion() == null || dto.getCedula() == "" || dto.getCedula() == null) {
            System.out.println("numero obligacion o cedula vacio");
            System.out.println(dto.getNumeroObligacion());
            System.out.println(dto.getCedula());
            return null;
        }
        
        CuentasPorCobrar cpc = cuentaCobrarRepository.findByNumeroObligacion(dto.getNumeroObligacion());
        if (Objects.isNull(cpc)) {
            System.out.println("cp vacio");
            return null;
        }
        
        String token = request.getAttribute("token").toString();

        List<ClientesDto> client = clientesClient.buscarClientesByNumeroObligacion(dto.getCedula(), token);
        if (client.isEmpty()) {
            System.out.println("clientes vacio");
            return null;
        }
        
        System.out.println(client.size());

        Usuario usu = usuarioClientService.obtenerUsuarioById(cpc.getAsesor().getUsuarioId());
        if (Objects.isNull(usu)) {
            System.out.println("usuario cedula vacio");
            return null;
        }
        
        ClientesDto clientToSend = new ClientesDto();

        for (ClientesDto clientesDto : client) {

            if (clientesDto.getNumeroDocumento().equals(dto.getCedula()) == true) {
                clientToSend = clientesDto;
                break;
            }
            System.out.println("numero docuomento y cedula diferentes");
        }

        List<Telefono> telefono = clientToSend.getTelefonos().stream().filter(t -> t.isIsCurrent() == true).collect(Collectors.toList());

        String telToMessage;
        if (Objects.nonNull(dto.getNumeroAlterno())) {
            telToMessage = "57 ".concat(dto.getNumeroAlterno());
        } else {
            telToMessage = telefono.get(0).getIndicativo().concat(" ").concat(telefono.get(0).getNumero());
        }

        LinkToClient link = new LinkToClient();

        String nombreTitular = clientToSend.getNombreTitular().replaceAll(" ", "%20").toUpperCase();
        String asesorCartera = usu.getNombres().replaceAll(" ", "%20").concat("%20").concat(usu.getApellidos().replaceAll(" ", "%20")).toUpperCase();

        String message = "&text=Buen%20día%20señor/a%20".concat(nombreTitular).concat(",%20se%20comunica%20con%20GMJ%20hogar;%20por%20medio%20de%20este%20mensaje%20le%20notificamos")
                .concat("%20que%20su%20acuerdo%20de%20pago%20ha%20sido%20efectuado%20exitosamente,%20a%20continuación%20enviaremos%20un%20PDF%20con%20la%20")
                .concat("información%20de%20su%20acuerdo%20de%20pago,%20este%20contiene%20las%20fechas%20de%20pago%20y%20los%20valores%20de%20las%20cuotas%20")
                .concat("mensuales%20acordadas%20con%20nuestro%20asesor/a%20de%20cartera%20".concat(asesorCartera).concat(",%20si%20tiene%20alguna%20duda%20por%20favor%20ponerse%20"))
                .concat("en%20contacto%20por%20este%20mismo%20medio,%20muchas%20gracias");

        link.setMessageToWpp("https://api.whatsapp.com/send?phone=".concat("+").concat(telToMessage).concat(message));
        try {
            link.setBase64(pdf.generarReporteAcuerdoPagoToClient(cpc, dto.getUsername()));
        } catch (IOException ex) {
            System.out.println(ex);
            Logger.getLogger(GestionesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
            Logger.getLogger(GestionesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return link;

    }

    @Override
    public List<Cuotas> cuotaCumplio(List<Long> idCuota) {
        List<Cuotas> cuota = cuotaRepository.findAllById(idCuota);
        if (CollectionUtils.isEmpty(cuota)) {
            return null;
        }
        for (Cuotas cuotas : cuota) {
            cuotas.setCumplio(true);
        }
        return cuotaRepository.saveAll(cuota);
    }
}
