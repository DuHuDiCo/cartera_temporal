package com.cartera_temp.cartera_temp.ServiceImpl;

import com.cartera_temp.cartera_temp.Components.GenerarPdf;
import com.cartera_temp.cartera_temp.Dtos.CuentasPorCobrarResponse;
import com.cartera_temp.cartera_temp.Dtos.CuotasDto;
import com.cartera_temp.cartera_temp.Dtos.PagosCuotasDto;
import com.cartera_temp.cartera_temp.Dtos.PagosCuotasResponse;
import com.cartera_temp.cartera_temp.FeignClients.usuario_client;
import com.cartera_temp.cartera_temp.Models.AcuerdoPago;
import com.cartera_temp.cartera_temp.Models.CuentasPorCobrar;
import com.cartera_temp.cartera_temp.Models.Cuotas;
import com.cartera_temp.cartera_temp.Models.Gestiones;
import com.cartera_temp.cartera_temp.Models.Pagos;
import com.cartera_temp.cartera_temp.ModelsClients.Usuario;
import com.cartera_temp.cartera_temp.Service.CuentasPorCobrarService;
import com.cartera_temp.cartera_temp.Service.PagosService;
import com.cartera_temp.cartera_temp.repository.AcuerdoPagoRepository;
import com.cartera_temp.cartera_temp.repository.CuentasPorCobrarRepository;
import com.cartera_temp.cartera_temp.repository.GestionesRepository;
import com.cartera_temp.cartera_temp.repository.PagosRespositoty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

@Service
public class PagosServiceImpl implements PagosService {

    private final PagosRespositoty pagosRespositoty;
    private final CuentasPorCobrarRepository cpcr;
    private final usuario_client usuClient;
    private final GestionesRepository gr;
    private final AcuerdoPagoRepository apr;
    private final GenerarPdf generarPdf;

    public PagosServiceImpl(PagosRespositoty pagosRespositoty, CuentasPorCobrarRepository cpcr, usuario_client usuClient, GestionesRepository gr, AcuerdoPagoRepository apr, GenerarPdf generarPdf) {
        this.pagosRespositoty = pagosRespositoty;
        this.cpcr = cpcr;
        this.usuClient = usuClient;
        this.gr = gr;
        this.apr = apr;
        this.generarPdf = generarPdf;
    }

    

    @Override
    public PagosCuotasResponse guardarPago(PagosCuotasDto dto) {

        if (dto.getNumeroObligacion() == "" || dto.getNumeroObligacion() == null || dto.getCuotasDto() == null) {
            return null;
        }

        CuentasPorCobrar cpc = cpcr.findByNumeroObligacion(dto.getNumeroObligacion());
        if (Objects.isNull(cpc)) {
            return null;
        }

        List<Gestiones> ges = cpc.getGestiones();

        AcuerdoPago acuPag = null;

        for (Gestiones ge : ges) {

            if (ge.getClasificacionGestion() instanceof AcuerdoPago) {
                AcuerdoPago acu = (AcuerdoPago) ge.getClasificacionGestion();
                if (acu.isIsActive()) {
                    acuPag = acu;
                }

            }

        }
        
        Usuario usu = usuClient.getUserByUsername(dto.getUsername());
                if (Objects.isNull(usu)) {
                    return null;
                }

        for (int i = 0; i < dto.getCuotasDto().size(); i++) {

            if (Objects.nonNull(dto.getCuotasDto().get(i).getPagosDto())) {
                Pagos pago = new Pagos();
                pago.setFechaPago(dto.getCuotasDto().get(i).getPagosDto().getFechaPago());
                pago.setSaldoCuota(dto.getCuotasDto().get(i).getPagosDto().getSaldoCuota());
                pago.setValorPago(dto.getCuotasDto().get(i).getPagosDto().getValorPago());
                pago.setUsuarioId(usu.getIdUsuario());
                acuPag.getCuotasList().get(i).setPagos(pago);
                
            }

        }
        
        try {
            String base64 = generarPdf.generarReportePagoCuotas(dto);
            PagosCuotasResponse pgr = new PagosCuotasResponse();
            pgr.setBase64(base64);
            return pgr;
        } catch (IOException ex) {
            Logger.getLogger(PagosServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PagosServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
        //apr.save(acuPag);
        //return acuPag;
    }

}
