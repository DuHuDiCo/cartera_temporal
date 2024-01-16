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
import com.cartera_temp.cartera_temp.Models.ReciboPago;
import com.cartera_temp.cartera_temp.ModelsClients.Usuario;
import com.cartera_temp.cartera_temp.Service.CuentasPorCobrarService;
import com.cartera_temp.cartera_temp.Service.PagosService;
import com.cartera_temp.cartera_temp.Utils.Functions;
import com.cartera_temp.cartera_temp.Utils.SaveFiles;
import com.cartera_temp.cartera_temp.repository.AcuerdoPagoRepository;
import com.cartera_temp.cartera_temp.repository.CuentasPorCobrarRepository;
import com.cartera_temp.cartera_temp.repository.CuotaRepository;
import com.cartera_temp.cartera_temp.repository.GestionesRepository;
import com.cartera_temp.cartera_temp.repository.PagosRespositoty;
import com.cartera_temp.cartera_temp.repository.ReciboPagoRepository;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PagosServiceImpl implements PagosService {

    private final PagosRespositoty pagosRespositoty;
    private final CuentasPorCobrarRepository cpcr;
    private final usuario_client usuClient;
    private final GestionesRepository gr;
    private final AcuerdoPagoRepository apr;
    private final GenerarPdf generarPdf;
    private final SaveFiles saveFiles;
    private final ReciboPagoRepository reciboPagoRepository;
    private final CuotaRepository cuotaRepository;

    @Value("${ruta.recibos}")
    private String path;

    public PagosServiceImpl(PagosRespositoty pagosRespositoty, CuentasPorCobrarRepository cpcr, usuario_client usuClient, GestionesRepository gr, AcuerdoPagoRepository apr, GenerarPdf generarPdf, SaveFiles saveFiles, ReciboPagoRepository reciboPagoRepository, CuotaRepository cuotaRepository) {
        this.pagosRespositoty = pagosRespositoty;
        this.cpcr = cpcr;
        this.usuClient = usuClient;
        this.gr = gr;
        this.apr = apr;
        this.generarPdf = generarPdf;
        this.saveFiles = saveFiles;
        this.reciboPagoRepository = reciboPagoRepository;
        this.cuotaRepository = cuotaRepository;
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

        acuPag.setIsCumpliendo(dto.isCumpliendo());
        try {
            String base64 = generarPdf.generarReportePagoCuotas(dto);

            if (Objects.isNull(base64)) {
                return null;
            }

            List<Pagos> pagos = new ArrayList<>();

            ReciboPago recibo = generarRecibo(dto, cpc.getSede().getSede(), usu.getIdUsuario(), base64);
         

            for (CuotasDto cuotasDto : dto.getCuotasDto()) {
                
                Cuotas cuota = cuotaRepository.findById(cuotasDto.getIdCuota()).orElse(null);
                if(Objects.isNull(cuota)){
                    return null;
                }
                
                cuota.setCapitalCuota(cuotasDto.getCapitalCuota());
                cuota.setHonorarios(cuotasDto.getHonorarios());
                cuota.setInteresCuota(cuotasDto.getInteresCuota());
                
                
                Pagos pago = new Pagos();
                pago.setFechaPago(cuotasDto.getPagosDto().getFechaPago());
                pago.setSaldoCuota(cuotasDto.getPagosDto().getSaldoCuota());
                pago.setValorPago(cuotasDto.getPagosDto().getValorPago());
                pago.setUsuarioId(usu.getIdUsuario());
                pago.setDetalle(dto.getDetalle());
                pago.setReciboPago(recibo);
                
                
                recibo.agregarReciboPago(pago);
                cuota.setPagos(pago);
                pagos.add(pago);
                
                
            }

            pagos = pagosRespositoty.saveAll(pagos);

            acuPag = apr.save(acuPag);

            PagosCuotasResponse pgr = new PagosCuotasResponse();
            pgr.setBase64(base64);
            pgr.setNombreArchivo(cpc.getCliente().concat(".pdf"));
            pgr.setReciboPago(recibo);
            return pgr;
        } catch (IOException ex) {
            Logger.getLogger(PagosServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PagosServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(PagosServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    private ReciboPago generarRecibo(PagosCuotasDto dto, String sede, Long idUsuario, String base64) throws IOException, ClassNotFoundException, ParseException {

        MultipartFile multipartFile = saveFiles.convertirFile(base64);
        if (Objects.isNull(multipartFile)) {
            return null;
        }

        String fileName = multipartFile.getOriginalFilename();
        String ruta = saveFiles.obtenerRuta(fileName, path, sede);

        String save = saveFiles.saveFile(multipartFile.getBytes(), fileName, ruta);
        if (Objects.isNull(save)) {
            return null;
        }

        ReciboPago recibo = new ReciboPago();
        recibo.setNombreArchivo(fileName);
        recibo.setNumeroRecibo(dto.getNumeroRecibo());
        recibo.setFechaRecibo(Functions.obtenerFechaYhora());
        recibo.setValorRecibo(dto.getValorTotal());
        recibo.setRuta(ruta);
        recibo.setUsuarioId(idUsuario);

        return recibo;

    }

}
