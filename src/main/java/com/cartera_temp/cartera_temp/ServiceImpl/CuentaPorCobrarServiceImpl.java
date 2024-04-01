package com.cartera_temp.cartera_temp.ServiceImpl;

import GestionesDataDto.enums.TipoClasificacion;
import com.cartera_temp.cartera_temp.Dtos.AsesorCarteraResponse;
import com.cartera_temp.cartera_temp.Dtos.ClasificacionGestionFiltro;
import com.cartera_temp.cartera_temp.Dtos.ClientesDto;
import com.cartera_temp.cartera_temp.Dtos.CuentaToCalculateDto;
import com.cartera_temp.cartera_temp.Dtos.CuentasPorCobrarDto;
import com.cartera_temp.cartera_temp.Dtos.CuentasPorCobrarResponse;
import com.cartera_temp.cartera_temp.Dtos.FiltroDto;
import com.cartera_temp.cartera_temp.FeignClients.ClientesClient;
import com.cartera_temp.cartera_temp.FeignClients.usuario_client;
import com.cartera_temp.cartera_temp.Models.AcuerdoPago;
import com.cartera_temp.cartera_temp.Models.AsesorCartera;
import com.cartera_temp.cartera_temp.Models.Banco;
import com.cartera_temp.cartera_temp.Models.ClasificacionJuridica;
import com.cartera_temp.cartera_temp.Models.CondicionEspecial;
import com.cartera_temp.cartera_temp.Models.CuentasPorCobrar;
import com.cartera_temp.cartera_temp.Models.Cuotas;
import com.cartera_temp.cartera_temp.Models.Gestiones;
import com.cartera_temp.cartera_temp.Models.Nota;
import com.cartera_temp.cartera_temp.Models.Sede;
import com.cartera_temp.cartera_temp.Models.Tarea;
import com.cartera_temp.cartera_temp.Models.TiposVencimiento;
import com.cartera_temp.cartera_temp.ModelsClients.Usuario;
import com.cartera_temp.cartera_temp.Service.AsesorCarteraService;
import com.cartera_temp.cartera_temp.Service.BancoService;
import com.cartera_temp.cartera_temp.Service.ClasificacionJuridicaService;
import com.cartera_temp.cartera_temp.Service.CuentasPorCobrarService;
import com.cartera_temp.cartera_temp.Service.FileService;
import com.cartera_temp.cartera_temp.Service.SedeService;
import com.cartera_temp.cartera_temp.Service.TiposVencimientoService;
import com.cartera_temp.cartera_temp.Utils.CuentaPorCobrarSpecification;
import com.cartera_temp.cartera_temp.Utils.Functions;
import com.cartera_temp.cartera_temp.Utils.SaveFiles;
import com.cartera_temp.cartera_temp.repository.AsesorCarteraRepository;
import com.cartera_temp.cartera_temp.repository.BancoRepository;
import com.cartera_temp.cartera_temp.repository.CondicionEspecialRepository;
import com.cartera_temp.cartera_temp.repository.CuentasPorCobrarRepository;
import com.cartera_temp.cartera_temp.repository.SedeRepository;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CuentaPorCobrarServiceImpl implements CuentasPorCobrarService {

    private final CuentasPorCobrarRepository cuentasPorCobrarRepository;
    private final SedeService sedeService;
    private final AsesorCarteraService asesorCarteraService;
    private final BancoService bancoService;
    private final usuario_client usuarioClient;
    private final ModelMapper modelMapper;
    private final ClientesClient clientesClient;
    private final FileService fileService;
    private final HttpServletRequest httpServletRequest;
    private final BancoRepository bancoRepository;
    private final SedeRepository sedeRepository;
    private final AsesorCarteraRepository asesorCarteraRepository;
    private final TiposVencimientoService tiposVencimientoService;
    private final SaveFiles saveFiles;
    private final ClasificacionJuridicaService clasificacionJuridicaService;
    private final CondicionEspecialRepository condicionEspecialRepository;

    public CuentaPorCobrarServiceImpl(CuentasPorCobrarRepository cuentasPorCobrarRepository, SedeService sedeService, AsesorCarteraService asesorCarteraService, BancoService bancoService, usuario_client usuarioClient, ModelMapper modelMapper, ClientesClient clientesClient, FileService fileService, HttpServletRequest httpServletRequest, BancoRepository bancoRepository, SedeRepository sedeRepository, AsesorCarteraRepository asesorCarteraRepository, TiposVencimientoService tiposVencimientoService, SaveFiles saveFiles, ClasificacionJuridicaService clasificacionJuridicaService, CondicionEspecialRepository condicionEspecialRepository) {
        this.cuentasPorCobrarRepository = cuentasPorCobrarRepository;
        this.sedeService = sedeService;
        this.asesorCarteraService = asesorCarteraService;
        this.bancoService = bancoService;
        this.usuarioClient = usuarioClient;
        this.modelMapper = modelMapper;
        this.clientesClient = clientesClient;
        this.fileService = fileService;
        this.httpServletRequest = httpServletRequest;
        this.bancoRepository = bancoRepository;
        this.sedeRepository = sedeRepository;
        this.asesorCarteraRepository = asesorCarteraRepository;
        this.tiposVencimientoService = tiposVencimientoService;
        this.saveFiles = saveFiles;
        this.clasificacionJuridicaService = clasificacionJuridicaService;
        this.condicionEspecialRepository = condicionEspecialRepository;
    }

    @Override
    public List<CuentasPorCobrar> guardarCuentas(List<CuentasPorCobrarDto> cuentasPorCobrarDto) {

        List<CuentasPorCobrar> cuentasSaved = new ArrayList<>();

        for (CuentasPorCobrarDto cuentaPorCobrar : cuentasPorCobrarDto) {
            CuentasPorCobrar cuentas = cuentasPorCobrarRepository.findByNumeroObligacion(cuentaPorCobrar.getNumeroObligacion());
            if (Objects.isNull(cuentas)) {
                cuentas = new CuentasPorCobrar();

            }

            TiposVencimiento tv = tiposVencimientoService.obtenerTipoVencimientoByNombre(cuentaPorCobrar.getEdadVencimiento().toUpperCase());
            if (Objects.isNull(tv)) {
                return null;
            }

            ClasificacionJuridica cj = clasificacionJuridicaService.getClasificacionByNombre(cuentaPorCobrar.getClasificacionJuridica().toUpperCase());
            if (Objects.isNull(cj)) {
                return null;
            }

            CondicionEspecial ce = condicionEspecialRepository.findByCondicionEspecial(cuentaPorCobrar.getCondicionEspecial().toUpperCase());
            if (Objects.isNull(ce)) {
                return null;
            }

            cuentas.setNumeroObligacion(cuentaPorCobrar.getNumeroObligacion());
            cuentas.setClasificacion(cuentaPorCobrar.getClasificacion());
            cuentas.setClasificacionJuridica(cj);
            cuentas.setCliente(cuentaPorCobrar.getCliente());
            cuentas.setCondicionEspecial(ce);
            cuentas.setCuotas(cuentaPorCobrar.getNumeroCuotas());
            cuentas.setCuotasMora(cuentaPorCobrar.getCoutasMora());
            cuentas.setDetalle(cuentaPorCobrar.getDetalle());
            cuentas.setDiasVencidos(cuentaPorCobrar.getDiasVencidos());
            cuentas.setDocumentoCliente(cuentaPorCobrar.getCedula());
            cuentas.setTiposVencimiento(tv);
            cuentas.setFechaCuentaCobrar(cuentaPorCobrar.getFechaCuenta());
            cuentas.setFechaVencimiento(cuentaPorCobrar.getFechaVencimiento());
            cuentas.setMoraObligatoria(cuentaPorCobrar.getMoraObligatoria());
            cuentas.setNombre_usuario(cuentaPorCobrar.getNombreUsuario());
            cuentas.setNumeroCreditos(cuentaPorCobrar.getNumeroCreditos());
            cuentas.setPagare(cuentaPorCobrar.getPagare());
            cuentas.setTipo(cuentaPorCobrar.getTipo());
            cuentas.setValorCuota(cuentaPorCobrar.getValorCuota());
            cuentas.setValorNotaDebito(cuentaPorCobrar.getValorNotaDebito());
            cuentas.setValorPagos(cuentaPorCobrar.getValorPagos());
            cuentas.setVendedor(cuentaPorCobrar.getVendedor());
            cuentas.setTotalObligatoria(cuentaPorCobrar.getTotalObligacion());

            tv.agreegarCuentaCobrar(cuentas);
            cj.agregarCuentaCobrar(cuentas);
            ce.agreegarCuentaCobrar(cuentas);

            Sede sede = sedeService.findSede(cuentaPorCobrar.getSede());

            if (Objects.isNull(sede)) {
                sede = new Sede();
                sede.setSede(cuentaPorCobrar.getSede());
                sede = sedeService.guardarSede(sede);
            }

            cuentas.setSede(sede);

            Banco banco = bancoService.findBanco(cuentaPorCobrar.getBanco());
            if (Objects.isNull(banco)) {
                banco = bancoService.guardarBanco(cuentaPorCobrar.getBanco());
            }
            cuentas.setBanco(banco);

            String[] asesorSplit = cuentaPorCobrar.getAsesorCartera().split(" ");

            String token = httpServletRequest.getAttribute("token").toString();

            Usuario user = usuarioClient.getUsuarioByNombresAndApellidos(asesorSplit[0], asesorSplit[1], token);
            if (Objects.isNull(user)) {
                return null;
            }

            AsesorCartera asesor = asesorCarteraService.findAsesor(user.getIdUsuario());
            if (Objects.isNull(asesor)) {
                asesor = asesorCarteraService.guardarAsesor(user.getIdUsuario());
            }
            cuentas.setAsesor(asesor);
            cuentasSaved.add(cuentas);
        }
        return cuentasPorCobrarRepository.saveAll(cuentasSaved);

    }

    @Override
    public Page<CuentasPorCobrarResponse> listarCuentasCobrarByAsesor(String username, Pageable pageable) {
        String token = httpServletRequest.getAttribute("token").toString();

        Usuario usuario = usuarioClient.getUserByUsername(username);
        if (Objects.isNull(usuario)) {
            return null;
        }

        AsesorCartera asesor = asesorCarteraService.findAsesor(usuario.getIdUsuario());
        if (Objects.isNull(asesor)) {
            return null;
        }

        Page<CuentasPorCobrar> cuentas = cuentasPorCobrarRepository.findByAsesorOrderByDiasVencidosDesc(asesor.getIdAsesorCartera(), pageable);
        List<CuentasPorCobrarResponse> cuentasResponse = new ArrayList<>();

        for (CuentasPorCobrar cuenta : cuentas.getContent()) {
            //calcular nuevos dias vencidos
            int diasVecidos = Functions.diferenciaFechas(cuenta.getFechaVencimiento());
            CuentasPorCobrarResponse c = modelMapper.map(cuenta, CuentasPorCobrarResponse.class);
            if (diasVecidos < 0) {
                c.setDiasVencidos(0);
            } else {
                c.setDiasVencidos(diasVecidos);
            }

            c.setTiposVencimiento(cuenta.getTiposVencimiento());
            AsesorCarteraResponse asesorResponse = new AsesorCarteraResponse();
            asesorResponse.setIdAsesorCartera(cuenta.getAsesor().getIdAsesorCartera());
            asesorResponse.setUsuario(usuario);
            c.setAsesorCarteraResponse(asesorResponse);

            c.setGestion(cuenta.getGestiones());

            c.setGestion(cuenta.getGestiones());

            String obligacion = cuenta.getDocumentoCliente().concat(cuenta.getSede().getSede()).concat(cuenta.getBanco().getBanco());

            List<ClientesDto> clientes = clientesClient.buscarClientesByNumeroObligacion(obligacion, token);
            c.setClientes(clientes);

            if (cuenta.getGestiones().size() > 0) {
                for (Gestiones gestione : cuenta.getGestiones()) {
                    if (gestione.getClasificacionGestion() instanceof AcuerdoPago) {
                        AcuerdoPago acuPago = (AcuerdoPago) gestione.getClasificacionGestion();
                        for (Cuotas cuotas : acuPago.getCuotasList()) {
                            if (Objects.nonNull(cuotas.getPagos())) {
                                String base = null;
                                try {
                                    base = saveFiles.pdfToBase64(cuotas.getPagos().getReciboPago().getRuta());
                                } catch (IOException ex) {
                                    Logger.getLogger(CuentaPorCobrarServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                cuotas.getPagos().getReciboPago().setRuta(base);
                            }
                        }

                    }
                }
            }

            cuentasResponse.add(c);
        }

        Page<CuentasPorCobrarResponse> cuentasPage = new PageImpl(cuentasResponse, pageable, cuentas.getTotalElements());

        return cuentasPage;

    }

    @Override
    public List<CuentasPorCobrar> processingData(MultipartFile file, String delimitante) {
        List<CuentasPorCobrarDto> cuentasDto = fileService.readFile(file, delimitante);

        CuentasPorCobrar cuentasClean = cuentasPorCobrarRepository.isEmpty();
        if (Objects.nonNull(cuentasClean)) {
            cuentasPorCobrarRepository.deleteAll();
//            cuentasPorCobrarRepository.reinicarIds();
//            bancoRepository.reinicarIds();
//            sedeRepository.reinicarIds();
//            asesorCarteraRepository.reinicarIds();
        }

        List<CuentasPorCobrar> cuentas = guardarCuentas(cuentasDto);
        return cuentas;
    }

    @Override
    public CuentasPorCobrarResponse getCpcByNumeroObligacion(String numeroObligacion) {

        String token = httpServletRequest.getAttribute("token").toString();

        if (numeroObligacion == "" || numeroObligacion == null) {
            return null;
        }

        CuentasPorCobrar cpc = cuentasPorCobrarRepository.findByNumeroObligacion(numeroObligacion);

        if (Objects.isNull(cpc)) {
            return null;
        }

        //calcular nuevos dias vencidos
        int diasVecidos = Functions.diferenciaFechas(cpc.getFechaVencimiento());

        CuentasPorCobrarResponse cpcRes = new CuentasPorCobrarResponse();

        ModelMapper map = new ModelMapper();

        cpcRes = map.map(cpc, CuentasPorCobrarResponse.class);
        cpcRes.setDiasVencidos(diasVecidos);

        String obligacion = cpc.getDocumentoCliente().concat(cpc.getSede().getSede()).concat(cpc.getBanco().getBanco());
        List<ClientesDto> cliente = clientesClient.buscarClientesByNumeroObligacion(obligacion, token);

        if (CollectionUtils.isEmpty(cliente)) {
            return null;
        }

        cpcRes.setCondicionEspecial(cpc.getCondicionEspecial().getCondicionEspecial());

        cpcRes.setClasificacionJuridica(cpc.getClasificacionJuridica().getClasificacionJuridica());

        cpcRes.setClientes(cliente);

        Usuario usu = usuarioClient.getUsuarioById(cpc.getAsesor().getUsuarioId(), token);

        if (Objects.isNull(usu)) {
            return null;
        }

        AsesorCarteraResponse asesor = new AsesorCarteraResponse();

        asesor.setUsuario(usu);
        asesor.setIdAsesorCartera(cpc.getAsesor().getIdAsesorCartera());

        cpcRes.setAsesorCarteraResponse(asesor);

        return cpcRes;

    }

    @Override
    public List<CuentasPorCobrarResponse> getCpcByNumeroObligacionContaining(String numeroObligacion) {

        String token = httpServletRequest.getAttribute("token").toString();

        if (numeroObligacion == "" || numeroObligacion == null) {
            return null;
        }

        List<CuentasPorCobrar> cpcList = cuentasPorCobrarRepository.findByNumeroObligacionContaining(numeroObligacion);

        if (CollectionUtils.isEmpty(cpcList)) {
            return null;
        }

        List<CuentasPorCobrarResponse> cpcResList = new ArrayList<>();

        ModelMapper map = new ModelMapper();

        for (CuentasPorCobrar cuentasPorCobrar : cpcList) {

            for (Gestiones gestiones : cuentasPorCobrar.getGestiones()) {
                if (gestiones.getClasificacionGestion() instanceof AcuerdoPago) {
                    AcuerdoPago acuerdo = (AcuerdoPago) gestiones.getClasificacionGestion();
                    for (Cuotas cuotas : acuerdo.getCuotasList()) {
                        if (Objects.nonNull(cuotas.getPagos())) {
                            String ruta;
                            try {
                                ruta = saveFiles.pdfToBase64(cuotas.getPagos().getReciboPago().getRuta());
                                if (Objects.nonNull(ruta)) {
                                    cuotas.getPagos().getReciboPago().setRuta(ruta);

                                }
                            } catch (IOException ex) {
                                Logger.getLogger(CuentaPorCobrarServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    }
                }

            }

            //calcular nuevos dias vencidos
            int diasVecidos = Functions.diferenciaFechas(cuentasPorCobrar.getFechaVencimiento());

            CuentasPorCobrarResponse cuentasPorCobrarResponse = map.map(cuentasPorCobrar, CuentasPorCobrarResponse.class);

            List<ClientesDto> cliente = clientesClient.buscarClientesByNumeroObligacion(cuentasPorCobrar.getDocumentoCliente(), token);
            if (CollectionUtils.isEmpty(cliente)) {
                return null;
            }
            cuentasPorCobrarResponse.setClientes(cliente);

            if (diasVecidos < 0) {
                cuentasPorCobrarResponse.setDiasVencidos(0);
            } else {
                cuentasPorCobrarResponse.setDiasVencidos(diasVecidos);
            }

            Usuario usu = usuarioClient.getUsuarioById(cuentasPorCobrar.getAsesor().getUsuarioId(), token);

            if (Objects.isNull(usu)) {
                return null;
            }

            AsesorCarteraResponse asesor = new AsesorCarteraResponse();
            asesor.setUsuario(usu);
            asesor.setIdAsesorCartera(cuentasPorCobrar.getAsesor().getIdAsesorCartera());

            cuentasPorCobrarResponse.setAsesorCarteraResponse(asesor);

            cuentasPorCobrarResponse.setGestion(cuentasPorCobrar.getGestiones());

            cpcResList.add(cuentasPorCobrarResponse);

        }

        return cpcResList;

    }

    @Override
    public CuentasPorCobrarResponse updateCpcToCalculate(CuentaToCalculateDto dto) {

        if (dto.getFechaVencimiento() == null || dto.getNumeroObligacion() == null || dto.getNumeroObligacion() == "" || dto.getUsername() == null || dto.getUsername() == "" || dto.getValorTotal() == 0) {
            return null;
        }

        CuentasPorCobrar cpc = cuentasPorCobrarRepository.findByNumeroObligacion(dto.getNumeroObligacion());
        if (Objects.isNull(cpc)) {
            return null;
        }

        Usuario usu = usuarioClient.getUserByUsername(dto.getUsername());

        if (Objects.isNull(usu)) {
            return null;
        }

        AsesorCartera asesor = asesorCarteraRepository.findByUsuarioId(usu.getIdUsuario());
        if (Objects.isNull(asesor)) {
            return null;
        }

        if (cpc.getAsesor() != asesor) {
            cpc.setAsesor(asesor);
        }

        if (cpc.getTotalObligatoria() != dto.getValorTotal()) {
            cpc.setTotalObligatoria(dto.getValorTotal());
        }

        if (cpc.getMoraObligatoria() != dto.getMoraObligatoria()) {
            cpc.setMoraObligatoria(dto.getMoraObligatoria());
        }

        if (cpc.getFechaVencimiento() != dto.getFechaVencimiento()) {
            cpc.setFechaVencimiento(dto.getFechaVencimiento());
        }

        cpc = cuentasPorCobrarRepository.save(cpc);

        ModelMapper map = new ModelMapper();

        CuentasPorCobrarResponse cpcRes = map.map(cpc, CuentasPorCobrarResponse.class);

        AsesorCarteraResponse asesorRes = new AsesorCarteraResponse();

        asesorRes.setIdAsesorCartera(asesor.getIdAsesorCartera());
        asesorRes.setUsuario(usu);

        cpcRes.setAsesorCarteraResponse(asesorRes);

        String token = httpServletRequest.getAttribute("token").toString();

        List<ClientesDto> clientesDto = clientesClient.buscarClientesByNumeroObligacion(cpc.getDocumentoCliente(), token);

        cpcRes.setClientes(clientesDto);

        return cpcRes;

    }

    @Override
    public List<CuentasPorCobrarResponse> buscarCuentasByDatos(String dato) {
        String token = httpServletRequest.getAttribute("token").toString();

        List<ClientesDto> clientes = clientesClient.buscarClientesByDatos(dato, token);
        if (CollectionUtils.isEmpty(clientes)) {
            return new ArrayList<>();
        }

        List<CuentasPorCobrarResponse> cuentasCobrar = new ArrayList<>();

        List<ClientesDto> clientesFilter = clientes.stream().filter(c -> c.getNumeroDocumento().equals(dato) || c.getNombreTitular().equals(dato)).collect(Collectors.toList());

        if (CollectionUtils.isEmpty(clientesFilter)) {
            clientesFilter = clientes;
        }
        for (ClientesDto cliente : clientesFilter) {
            List<CuentasPorCobrar> cuenta = cuentasPorCobrarRepository.findByDocumentoCliente(cliente.getNit());
            if (!CollectionUtils.isEmpty(cuenta)) {
                for (CuentasPorCobrar cuentasPorCobrar : cuenta) {

                    CuentasPorCobrarResponse cuentasPorCobrarResponse = modelMapper.map(cuentasPorCobrar, CuentasPorCobrarResponse.class);
                    int diasVecidos = Functions.diferenciaFechas(cuentasPorCobrar.getFechaVencimiento());
                    if (diasVecidos < 0) {
                        cuentasPorCobrarResponse.setDiasVencidos(0);
                    } else {
                        cuentasPorCobrarResponse.setDiasVencidos(diasVecidos);
                    }
                    cuentasPorCobrarResponse.setClientes(Arrays.asList(cliente));
                    cuentasPorCobrarResponse.setTiposVencimiento(cuentasPorCobrar.getTiposVencimiento());

                    AsesorCarteraResponse asesor = new AsesorCarteraResponse();
                    asesor.setIdAsesorCartera(cuentasPorCobrar.getAsesor().getIdAsesorCartera());

                    Usuario usu = usuarioClient.getUsuarioById(cuentasPorCobrar.getAsesor().getUsuarioId(), token);

                    if (Objects.isNull(usu)) {
                        return null;
                    }
                    asesor.setUsuario(usu);

                    cuentasPorCobrarResponse.setGestion(cuentasPorCobrar.getGestiones());

                    cuentasPorCobrarResponse.setAsesorCarteraResponse(asesor);
                    cuentasCobrar.add(cuentasPorCobrarResponse);
                }
            }

        }

        return cuentasCobrar;
    }

    @Override
    public Page<CuentasPorCobrarResponse> filtrosCpcs(FiltroDto dto, Pageable pageable) {

        Page<CuentasPorCobrar> cpc = null;

        if (Objects.isNull(dto.getFechaCompromisoInicio())) {
            Usuario usuFiltro = new Usuario();
            Specification<CuentasPorCobrar> spec = null;
            if (!dto.getUsername().isBlank()) {
                System.out.println("username no blank");
                usuFiltro = usuarioClient.getUserByUsername(dto.getUsername());
                spec = CuentaPorCobrarSpecification.filtrarCuentas(dto, usuFiltro.getIdUsuario());
            } else {
                System.out.println("username blank");
                spec = CuentaPorCobrarSpecification.filtrarCuentas(dto, 0L);

            }
            cpc = cuentasPorCobrarRepository.findAll(spec, pageable);
            var list = CollectionUtils.isEmpty(cpc.getContent()) ? null : cpc.getTotalElements();
            System.out.println("Lista---" + list);

        } else {

            Usuario usuFiltro = usuarioClient.getUserByUsername(dto.getUsername());
            if (Objects.isNull(usuFiltro)) {
                return null;
            }

            AsesorCartera asesor = asesorCarteraRepository.findByUsuarioId(usuFiltro.getIdUsuario());
            if (Objects.isNull(asesor)) {
                return null;
            }

            try {
                cpc = cuentasPorCobrarRepository.obtenerCuentasByFechaCompromiso(Functions.stringToDateAndFormat(dto.getFechaCompromisoInicio()), asesor.getIdAsesorCartera(), pageable);
                var list = CollectionUtils.isEmpty(cpc.getContent()) ? null : cpc.getContent().size();
                System.out.println("Lista---" + list);

            } catch (ParseException ex) {
                Logger.getLogger(CuentaPorCobrarServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

//        List<CuentasPorCobrar> cpc = cuentasPorCobrarRepository.findAll(spec);
        List<CuentasPorCobrarResponse> cpcRes = new ArrayList<>();
        ModelMapper map = new ModelMapper();
        for (CuentasPorCobrar cuentasPorCobrar : cpc.getContent()) {

            int diasVecidos = Functions.diferenciaFechas(cuentasPorCobrar.getFechaVencimiento());
            CuentasPorCobrarResponse cpcResFor = map.map(cuentasPorCobrar, CuentasPorCobrarResponse.class);
            if (diasVecidos < 0) {
                cpcResFor.setDiasVencidos(0);
            } else {
                cpcResFor.setDiasVencidos(diasVecidos);
            }
            AsesorCarteraResponse asesor = new AsesorCarteraResponse();
            asesor.setIdAsesorCartera(cuentasPorCobrar.getAsesor().getIdAsesorCartera());
            String token = httpServletRequest.getAttribute("token").toString();
            Usuario usu = usuarioClient.getUsuarioById(cuentasPorCobrar.getAsesor().getUsuarioId(), token);
            asesor.setUsuario(usu);
            cpcResFor.setAsesorCarteraResponse(asesor);

            cpcResFor.setGestion(cuentasPorCobrar.getGestiones());

            List<ClientesDto> clientes = clientesClient.buscarClientesByNumeroObligacion(cuentasPorCobrar.getDocumentoCliente(), token);
            cpcResFor.setClientes(clientes);
            
            
            
            cpcRes.add(cpcResFor);

            
        }

        Page<CuentasPorCobrarResponse> cuentasPage = new PageImpl(cpcRes, pageable, cpc.getTotalElements());

        return cuentasPage;

    }

    @Override
    public Page<CuentasPorCobrarResponse> listarCuentasCobrar(Pageable pageable) {
        Page<CuentasPorCobrar> pageCuentas = cuentasPorCobrarRepository.findByAll(pageable);

        List<CuentasPorCobrarResponse> cuentasResponse = new ArrayList<>();

        String token = httpServletRequest.getAttribute("token").toString();

        for (CuentasPorCobrar cuentasPorCobrar : pageCuentas.getContent()) {
            //calcular nuevos dias vencidos
            int diasVecidos = Functions.diferenciaFechas(cuentasPorCobrar.getFechaVencimiento());
            CuentasPorCobrarResponse c = modelMapper.map(cuentasPorCobrar, CuentasPorCobrarResponse.class);
            if (diasVecidos < 0) {
                c.setDiasVencidos(0);
            } else {
                c.setDiasVencidos(diasVecidos);
            }

            c.setTiposVencimiento(cuentasPorCobrar.getTiposVencimiento());
            AsesorCarteraResponse asesorResponse = new AsesorCarteraResponse();
            asesorResponse.setIdAsesorCartera(cuentasPorCobrar.getAsesor().getIdAsesorCartera());

            Usuario usuario = usuarioClient.getUsuarioById(cuentasPorCobrar.getAsesor().getUsuarioId(), token);
            if (Objects.isNull(usuario)) {
                return null;
            }
            asesorResponse.setUsuario(usuario);
            c.setAsesorCarteraResponse(asesorResponse);

            c.setGestion(cuentasPorCobrar.getGestiones());

            c.setGestion(cuentasPorCobrar.getGestiones());

            List<ClientesDto> clientes = clientesClient.buscarClientesByNumeroObligacion(cuentasPorCobrar.getDocumentoCliente(), token);
            c.setClientes(clientes);

            if (cuentasPorCobrar.getGestiones().size() > 0) {
                for (Gestiones gestione : cuentasPorCobrar.getGestiones()) {
                    if (gestione.getClasificacionGestion() instanceof AcuerdoPago) {
                        AcuerdoPago acuPago = (AcuerdoPago) gestione.getClasificacionGestion();
                        for (Cuotas cuotas : acuPago.getCuotasList()) {
                            if (Objects.nonNull(cuotas.getPagos())) {
                                String base = null;
                                try {
                                    base = saveFiles.pdfToBase64(cuotas.getPagos().getReciboPago().getRuta());
                                } catch (IOException ex) {
                                    Logger.getLogger(CuentaPorCobrarServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                cuotas.getPagos().getReciboPago().setRuta(base);
                            }
                        }

                    }
                }
            }

            cuentasResponse.add(c);

        }
        Page<CuentasPorCobrarResponse> cuentasPage = new PageImpl(cuentasResponse, pageable, pageCuentas.getTotalElements());
        return cuentasPage;

    }
    
    
    private List<Gestiones> organizarGestiones(List<Gestiones> gestionesDesorganizadas){
        
        List<Gestiones> gestionesOrganizadas = new ArrayList<>();
        
        List<Gestiones> gestionesAcuerdos = gestionesDesorganizadas.stream().filter(ges-> {
            if(ges.getClasificacionGestion() instanceof AcuerdoPago){
                AcuerdoPago acuerdo = (AcuerdoPago) ges.getClasificacionGestion();
                return acuerdo.isIsActive();
            }
            return false;
        }).collect(Collectors.toList());
        System.out.println(gestionesAcuerdos.size());
        gestionesAcuerdos.forEach(ges-> gestionesOrganizadas.add( ges));
        
         List<Gestiones> gestionesTareas = gestionesDesorganizadas.stream().filter(ges-> {
            if(ges.getClasificacionGestion() instanceof Tarea){
                Tarea tarea = (Tarea) ges.getClasificacionGestion();
                return tarea.isIsActive();
            }
            return false;
        }).collect(Collectors.toList());
        System.out.println(gestionesTareas.size());
         gestionesTareas.forEach(ges-> gestionesOrganizadas.add( ges));
         
         List<Gestiones> gestionesNotas = gestionesDesorganizadas.stream().filter(ges-> {
            if(ges.getClasificacionGestion() instanceof Nota){
                return ges.getClasificacionGestion().getClasificacion().equals(TipoClasificacion.NOTA.getDato());
            }
            return false;
        }).collect(Collectors.toList());
        System.out.println(gestionesNotas.size());
         gestionesNotas.forEach(ges-> gestionesOrganizadas.add( ges));
         
        return gestionesOrganizadas;
    }

}
