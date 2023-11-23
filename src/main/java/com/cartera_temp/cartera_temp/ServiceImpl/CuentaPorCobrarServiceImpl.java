package com.cartera_temp.cartera_temp.ServiceImpl;

import com.cartera_temp.cartera_temp.Dtos.AsesorCarteraResponse;
import com.cartera_temp.cartera_temp.Dtos.ClientesDto;
import com.cartera_temp.cartera_temp.Dtos.CuentasPorCobrarDto;
import com.cartera_temp.cartera_temp.Dtos.CuentasPorCobrarResponse;
import com.cartera_temp.cartera_temp.FeignClients.ClientesClient;
import com.cartera_temp.cartera_temp.FeignClients.usuario_client;
import com.cartera_temp.cartera_temp.Models.AsesorCartera;
import com.cartera_temp.cartera_temp.Models.Banco;
import com.cartera_temp.cartera_temp.Models.CuentasPorCobrar;
import com.cartera_temp.cartera_temp.Models.Sede;
import com.cartera_temp.cartera_temp.ModelsClients.Usuario;
import com.cartera_temp.cartera_temp.Service.AsesorCarteraService;
import com.cartera_temp.cartera_temp.Service.BancoService;
import com.cartera_temp.cartera_temp.Service.CuentasPorCobrarService;
import com.cartera_temp.cartera_temp.Service.SedeService;
import com.cartera_temp.cartera_temp.repository.CuentasPorCobrarRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CuentaPorCobrarServiceImpl implements CuentasPorCobrarService {

    private final CuentasPorCobrarRepository cuentasPorCobrarRepository;
    private final SedeService sedeService;
    private final AsesorCarteraService asesorCarteraService;
    private final BancoService bancoService;
    private final usuario_client usuarioClient;
    private final ModelMapper modelMapper;
    private final ClientesClient clientesClient;

    public CuentaPorCobrarServiceImpl(CuentasPorCobrarRepository cuentasPorCobrarRepository, SedeService sedeService, AsesorCarteraService asesorCarteraService, BancoService bancoService, usuario_client usuarioClient, ModelMapper modelMapper, ClientesClient clientesClient) {
        this.cuentasPorCobrarRepository = cuentasPorCobrarRepository;
        this.sedeService = sedeService;
        this.asesorCarteraService = asesorCarteraService;
        this.bancoService = bancoService;
        this.usuarioClient = usuarioClient;
        this.modelMapper = modelMapper;
        this.clientesClient = clientesClient;
    }

    

    

    @Override
    public List<CuentasPorCobrar> guardarCuentas(List<CuentasPorCobrarDto> cuentasPorCobrarDto) {
        
        List<CuentasPorCobrar> cuentasSaved = new ArrayList<>();
        
        for (CuentasPorCobrarDto cuentaPorCobrar : cuentasPorCobrarDto) {
            CuentasPorCobrar cuentas = new CuentasPorCobrar();

            cuentas.setClasificacion(cuentaPorCobrar.getClasificacion());
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

            Sede sede = sedeService.findSede(cuentaPorCobrar.getSede());
            if (Objects.isNull(sede)) {
                sede = sedeService.guardarSede(cuentaPorCobrar.getSede());
            }
            cuentas.setSede(sede);

            Banco banco = bancoService.findBanco(cuentaPorCobrar.getBanco());
            if (Objects.isNull(banco)) {
                banco = bancoService.guardarBanco(cuentaPorCobrar.getBanco());
            }
            cuentas.setBanco(banco);

            String[] asesorSplit = cuentaPorCobrar.getAsesorCartera().split(" ");

            Usuario user = usuarioClient.getUsuarioByNombresAndApellidos(asesorSplit[0], asesorSplit[1]);
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
    public List<CuentasPorCobrarResponse> listarCuentasCobrarByAsesor(String username) {
        Usuario usuario = usuarioClient.getUserByUsername(username);
        if(Objects.isNull(usuario)){
            return null;
        }
        
        AsesorCartera asesor = asesorCarteraService.findAsesor(usuario.getIdUsuario());
        if(Objects.isNull(asesor)){
            return null;
        }
        
        List<CuentasPorCobrar> cuentas = cuentasPorCobrarRepository.findByAsesor(asesor);
        List<CuentasPorCobrarResponse> cuentasResponse = new ArrayList<>();
        
        for (CuentasPorCobrar cuenta : cuentas) {
            CuentasPorCobrarResponse c = modelMapper.map(cuenta, CuentasPorCobrarResponse.class);
            AsesorCarteraResponse asesorResponse = new AsesorCarteraResponse();
            asesorResponse.setIdAsesorCartera(cuenta.getAsesor().getIdAsesorCartera());
            asesorResponse.setUsuario(usuario);
            c.setAsesorCarteraResponse(asesorResponse);
            
            
            List<ClientesDto> clientes = clientesClient.buscarClientesByNumeroObligacion(cuenta.getDocumentoCliente());
            c.setClientes(clientes);
            
            
            
            cuentasResponse.add(c);
        }
        
        
        
        return cuentasResponse;
        
    }

}
