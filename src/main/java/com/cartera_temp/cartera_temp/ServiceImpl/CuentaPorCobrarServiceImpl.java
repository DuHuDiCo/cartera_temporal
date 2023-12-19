package com.cartera_temp.cartera_temp.ServiceImpl;

import com.cartera_temp.cartera_temp.Dtos.AsesorCarteraResponse;
import com.cartera_temp.cartera_temp.Dtos.ClientesDto;
import com.cartera_temp.cartera_temp.Dtos.CuentaToCalculateDto;
import com.cartera_temp.cartera_temp.Dtos.CuentasPorCobrarDto;
import com.cartera_temp.cartera_temp.Dtos.CuentasPorCobrarResponse;
import com.cartera_temp.cartera_temp.FeignClients.ClientesClient;
import com.cartera_temp.cartera_temp.FeignClients.usuario_client;
import com.cartera_temp.cartera_temp.Models.AsesorCartera;
import com.cartera_temp.cartera_temp.Models.Banco;
import com.cartera_temp.cartera_temp.Models.CuentasPorCobrar;
import com.cartera_temp.cartera_temp.Models.Gestiones;
import com.cartera_temp.cartera_temp.Models.Sede;
import com.cartera_temp.cartera_temp.ModelsClients.Usuario;
import com.cartera_temp.cartera_temp.Service.AsesorCarteraService;
import com.cartera_temp.cartera_temp.Service.BancoService;
import com.cartera_temp.cartera_temp.Service.CuentasPorCobrarService;
import com.cartera_temp.cartera_temp.Service.FileService;
import com.cartera_temp.cartera_temp.Service.SedeService;
import com.cartera_temp.cartera_temp.repository.AsesorCarteraRepository;
import com.cartera_temp.cartera_temp.repository.BancoRepository;
import com.cartera_temp.cartera_temp.repository.CuentasPorCobrarRepository;
import com.cartera_temp.cartera_temp.repository.SedeRepository;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    public CuentaPorCobrarServiceImpl(CuentasPorCobrarRepository cuentasPorCobrarRepository, SedeService sedeService, AsesorCarteraService asesorCarteraService, BancoService bancoService, usuario_client usuarioClient, ModelMapper modelMapper, ClientesClient clientesClient, FileService fileService, HttpServletRequest httpServletRequest, BancoRepository bancoRepository, SedeRepository sedeRepository, AsesorCarteraRepository asesorCarteraRepository) {
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
    }

    @Override
    public List<CuentasPorCobrar> guardarCuentas(List<CuentasPorCobrarDto> cuentasPorCobrarDto) {

        List<CuentasPorCobrar> cuentasSaved = new ArrayList<>();

        for (CuentasPorCobrarDto cuentaPorCobrar : cuentasPorCobrarDto) {
            CuentasPorCobrar cuentas = cuentasPorCobrarRepository.findByNumeroObligacion(cuentaPorCobrar.getNumeroObligacion());
            if (Objects.isNull(cuentas)) {
                cuentas = new CuentasPorCobrar();

            }

            cuentas.setNumeroObligacion(cuentaPorCobrar.getNumeroObligacion());
            cuentas.setClasificacion(cuentaPorCobrar.getClasificacion());
            cuentas.setClasificacionJuridica(cuentaPorCobrar.getClasificacionJuridica());
            cuentas.setCliente(cuentaPorCobrar.getCliente());
            cuentas.setCondicionEspecial(cuentaPorCobrar.getCondicionEspecial());
            cuentas.setCuotas(cuentaPorCobrar.getNumeroCuotas());
            cuentas.setCuotasMora(cuentaPorCobrar.getCoutasMora());
            cuentas.setDetalle(cuentaPorCobrar.getDetalle());
            cuentas.setDiasVencidos(cuentaPorCobrar.getDiasVencidos());
            cuentas.setDocumentoCliente(cuentaPorCobrar.getCedula());
            cuentas.setEdadVencimiento(cuentaPorCobrar.getEdadVencimiento());
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

            Sede sede = sedeService.findSede(cuentaPorCobrar.getSede());

            if (Objects.isNull(sede)) {
                sede = sedeService.guardarSede(cuentaPorCobrar.getSede());
                System.out.println(sede.getSede());
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
            System.out.println(cuentasSaved.size());
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

        Page<CuentasPorCobrar> cuentas = cuentasPorCobrarRepository.findByAsesor(asesor, pageable);
        List<CuentasPorCobrarResponse> cuentasResponse = new ArrayList<>();

        for (CuentasPorCobrar cuenta : cuentas.getContent()) {
            CuentasPorCobrarResponse c = modelMapper.map(cuenta, CuentasPorCobrarResponse.class);
            AsesorCarteraResponse asesorResponse = new AsesorCarteraResponse();
            asesorResponse.setIdAsesorCartera(cuenta.getAsesor().getIdAsesorCartera());
            asesorResponse.setUsuario(usuario);
            c.setAsesorCarteraResponse(asesorResponse);

            List<ClientesDto> clientes = clientesClient.buscarClientesByNumeroObligacion(cuenta.getDocumentoCliente(), token);
            c.setClientes(clientes);

            cuentasResponse.add(c);
        }

        Page<CuentasPorCobrarResponse> cuentasPage = new PageImpl(cuentasResponse, pageable, cuentas.getTotalElements());

        return cuentasPage;

    }

    @Override
    public List<CuentasPorCobrar> processingData(MultipartFile file, String delimitante) {
        List<CuentasPorCobrarDto> cuentasDto = fileService.readFile(file, delimitante);
        System.out.println(cuentasDto.size());

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

        CuentasPorCobrarResponse cpcRes = new CuentasPorCobrarResponse();

        ModelMapper map = new ModelMapper();

        cpcRes = map.map(cpc, CuentasPorCobrarResponse.class);

        List<ClientesDto> cliente = clientesClient.buscarClientesByNumeroObligacion(cpc.getDocumentoCliente(), token);

        if (CollectionUtils.isEmpty(cliente)) {
            return null;
        }

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

            CuentasPorCobrarResponse cuentasPorCobrarResponse = new CuentasPorCobrarResponse();

            List<ClientesDto> cliente = clientesClient.buscarClientesByNumeroObligacion(cuentasPorCobrar.getNumeroObligacion(), token);
            if (CollectionUtils.isEmpty(cliente)) {
                return null;
            }
            cuentasPorCobrarResponse.setClientes(cliente);

            Usuario usu = usuarioClient.getUsuarioById(cuentasPorCobrar.getAsesor().getUsuarioId(), token);

            if (Objects.isNull(usu)) {
                return null;
            }

            AsesorCarteraResponse asesor = new AsesorCarteraResponse();

            asesor.setUsuario(usu);
            asesor.setIdAsesorCartera(cuentasPorCobrar.getAsesor().getIdAsesorCartera());

            cuentasPorCobrarResponse.setAsesorCarteraResponse(asesor);

            cuentasPorCobrarResponse = map.map(cuentasPorCobrar, CuentasPorCobrarResponse.class);
            cpcResList.add(cuentasPorCobrarResponse);

        }

        return cpcResList;

    }

    @Override
    public CuentasPorCobrarResponse updateCpcToCalculate(CuentaToCalculateDto dto) {

        if(dto.getFechaVencimiento() == null || dto.getNumeroObligacion() == null || dto.getNumeroObligacion() == "" || dto.getUsername() == null || dto.getUsername() == "" || dto.getValorTotal() == 0){
            return null;
        }
        
        CuentasPorCobrar cpc = cuentasPorCobrarRepository.findByNumeroObligacion(dto.getNumeroObligacion());
        if(Objects.isNull(cpc)){
            return null;
        }
        
        Usuario usu = usuarioClient.getUserByUsername(dto.getUsername());
        
        if(Objects.isNull(usu)){
            return  null;
        }
        
        AsesorCartera asesor = asesorCarteraRepository.findByUsuarioId(usu.getIdUsuario());
        if(Objects.isNull(asesor)){
            return null;
        }
        
        if(cpc.getAsesor() != asesor ){
            cpc.setAsesor(asesor);
        }
        
        if(cpc.getTotalObligatoria() != dto.getValorTotal()){
            cpc.setTotalObligatoria(dto.getValorTotal());
        }
        
        if(cpc.getMoraObligatoria() != dto.getMoraObligatoria()){
            cpc.setMoraObligatoria(dto.getMoraObligatoria());
        }
        
        if(cpc.getFechaVencimiento() != dto.getFechaVencimiento()){
            cpc.setFechaVencimiento(dto.getFechaVencimiento());
        }
        
        cpc =cuentasPorCobrarRepository.save(cpc);
        
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

}
