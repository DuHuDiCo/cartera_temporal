package com.cartera_temp.cartera_temp.ServiceImpl;

import com.cartera_temp.cartera_temp.Dtos.CuentasPorCobrarDto;
import com.cartera_temp.cartera_temp.Dtos.GestionesDto;
import com.cartera_temp.cartera_temp.Service.FileService;
import com.cartera_temp.cartera_temp.Utils.Functions;
import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public List<CuentasPorCobrarDto> readFile(MultipartFile file, String delimitante) {
        if (Objects.isNull(file)) {
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String firstLine = reader.readLine();
            String[] columnNames = firstLine.split(delimitante);

            String line;

            List<CuentasPorCobrarDto> cuentasDto = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(delimitante);
                try {
                    StringBuilder sbNumeroObligacion = new StringBuilder();
                    sbNumeroObligacion.append(values[0] == null || " ".equals(values[0]) ? "N/A" : Functions.quitarCaracteresEspeciales(values[0]));
                    String numeroObligacion = sbNumeroObligacion.toString();

                    StringBuilder sbClientes = new StringBuilder();
                    sbClientes.append(values[1] == null || " ".equals(values[1]) ? "N/A" : values[1]);
                    String clientes = sbClientes.toString();

                    Date fechaCredito = (values[2] == null || " ".equals(values[2])) ? null : Functions.stringToDate(values[2]);
                    Date fechaVencimiento = (values[3] == null || " ".equals(values[3])) ? null : Functions.stringToDate(values[3]);

                    StringBuilder sbTipo = new StringBuilder();
                    sbTipo.append(values[4] == null || " ".equals(values[4]) ? "N/A" : Functions.quitarCaracteresEspeciales(values[4]));
                    String tipo = sbTipo.toString();

                    double valorNotaDebito = (values[5] == null || " ".equals(values[5])) ? null : Double.valueOf(Functions.containsOnlyNumbers(values[5]));
                    double valorCuota = (values[6] == null || " ".equals(values[6])) ? null : Double.valueOf(Functions.containsOnlyNumbers(values[6]));
                    double valorPagos = (values[7] == null || " ".equals(values[7])) ? null : Double.valueOf(Functions.containsOnlyNumbers(values[7]));

                    StringBuilder sbNombreUsuario = new StringBuilder();
                    sbNombreUsuario.append(values[8] == null || " ".equals(values[8]) ? null : Functions.quitarCaracteresEspeciales(values[8]));
                    String nombreUsuario = sbNombreUsuario.toString();

                    StringBuilder sbClasificacion = new StringBuilder();
                    sbClasificacion.append(values[9] == null || " ".equals(values[9]) ? null : values[9]);
                    String clasificacion = sbClasificacion.toString();

                    StringBuilder sbVendedor = new StringBuilder();
                    sbVendedor.append(values[10] == null || " ".equals(values[10]) ? null : Functions.quitarCaracteresEspeciales(values[10]));
                    String vendedor = sbVendedor.toString();

                    StringBuilder sbDetalle = new StringBuilder();
                    sbDetalle.append(values[11] == null || " ".equals(values[11]) ? null : Functions.quitarCaracteresEspeciales(values[11]));
                    String detalle = sbDetalle.toString();

                    StringBuilder sbSede = new StringBuilder();
                    sbSede.append(values[12] == null || " ".equals(values[12]) ? null : Functions.quitarCaracteresEspeciales(values[12]));
                    String sede = sbSede.toString();

                    StringBuilder sbBanco = new StringBuilder();
                    sbBanco.append(values[13] == null || " ".equals(values[13]) ? null : Functions.quitarCaracteresEspeciales(values[13]));
                    String banco = sbBanco.toString();

                    StringBuilder sbCedula = new StringBuilder();
                    sbCedula.append(values[14] == null || " ".equals(values[14]) ? null : Functions.quitarCaracteresEspeciales(values[14]));
                    String cedula = sbCedula.toString();

                    StringBuilder sbClasificacionJuridica = new StringBuilder();
                    sbClasificacionJuridica.append(values[15] == null || " ".equals(values[15]) ? null : Functions.quitarCaracteresEspeciales(values[15]));
                    String clasificacionJuridica = sbClasificacionJuridica.toString();

                    StringBuilder sbAsesorCartera = new StringBuilder();
                    sbAsesorCartera.append(values[16] == null || " ".equals(values[16]) ? null : Functions.quitarCaracteresEspeciales(values[16]));
                    String asesorCartera = sbAsesorCartera.toString();

                    int diasVencidos = (values[17] == null || " ".equals(values[17]) ? null : Integer.parseInt(values[17]));

                    StringBuilder sbEdadVencimiento = new StringBuilder();
                    sbEdadVencimiento.append(values[18] == null || " ".equals(values[18]) ? null : Functions.quitarCaracteresEspeciales(values[18]));
                    String edadVencimiento = sbEdadVencimiento.toString();

                    StringBuilder sbCondicionEspecail = new StringBuilder();
                    sbCondicionEspecail.append(values[19] == null || " ".equals(values[19]) ? null : Functions.quitarCaracteresEspeciales(values[19]));
                    String condicionEspecail = sbCondicionEspecail.toString();

                    int creditos = (values[20] == null || " ".equals(values[20]) ? null : Integer.parseInt(values[20]));

                    StringBuilder sbPagare = new StringBuilder();
                    sbPagare.append(values[21] == null || " ".equals(values[21]) ? null : Functions.quitarCaracteresEspeciales(values[21]));
                    String pagare = sbPagare.toString();
                    double moraObligatoria = (values[22] == null || " ".equals(values[22])) ? null : Double.valueOf(Functions.containsOnlyNumbers(values[22]));
                    double totalObligatoria = (values[23] == null || " ".equals(values[23])) ? null : Double.valueOf(Functions.containsOnlyNumbers(values[23]));
                    int cuotasMora = (values[24] == null || " ".equals(values[24]) ? null : Integer.parseInt(values[24]));
                    int totalCuotas = (values[25] == null || " ".equals(values[25]) ? null : Integer.parseInt(values[25]));

                    CuentasPorCobrarDto cuentaDto = new CuentasPorCobrarDto(numeroObligacion, clientes, fechaCredito, fechaVencimiento, tipo, valorNotaDebito, valorCuota, valorPagos, nombreUsuario, clasificacion, vendedor, detalle, sede, banco, cedula, clasificacionJuridica, asesorCartera, diasVencidos, condicionEspecail, edadVencimiento, creditos, pagare, moraObligatoria, totalObligatoria, cuotasMora, totalCuotas);
                    cuentasDto.add(cuentaDto);
                } catch (NumberFormatException | ParseException e) {
                    System.err.println(e.getMessage());
                }

            }

            return cuentasDto;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<GestionesDto> readFileGestiones(MultipartFile file, String delimitante) {

        if (Objects.isNull(file)) {
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String firstLine = reader.readLine();
            String[] columnNames = firstLine.split("\\".concat(delimitante));

            String line;

            List<GestionesDto> gestionesDto = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(delimitante);
//                try {
//                    StringBuilder sbNumeroObligacion = new StringBuilder();
//                    sbNumeroObligacion.append(values[0] == null || " ".equals(values[0]) ? "N/A" : values[0]);
//                    String numeroObligacion = sbNumeroObligacion.toString();
//
//                    StringBuilder sbCedula = new StringBuilder();
//                    sbCedula.append(values[1] == null || " ".equals(values[1]) ? null : values[1]);
//                    String cedula = sbCedula.toString();
//
//                    StringBuilder sbClientes = new StringBuilder();
//                    sbClientes.append(values[2] == null || " ".equals(values[2]) ? "N/A" : values[2]);
//                    String clientes = sbClientes.toString();
//
//                    StringBuilder sbSede = new StringBuilder();
//                    sbSede.append(values[3] == null || " ".equals(values[3]) ? null : values[3]);
//                    String sede = sbSede.toString();
//
//                    StringBuilder sbBanco = new StringBuilder();
//                    sbBanco.append(values[4] == null || " ".equals(values[4]) ? null : values[4]);
//                    String banco = sbBanco.toString();
//
//                    StringBuilder sbAsesorCartera = new StringBuilder();
//                    sbAsesorCartera.append(values[5] == null || " ".equals(values[5]) ? null : values[5]);
//                    String asesor = sbAsesorCartera.toString();
//
//                    Date fechaGestion = (values[6] == null || "".equals(values[6])) ? null : Functions.fechaDateToString(values[6]);
//                    Date fechaCompromiso = (values[7] == null || "".equals(values[7])) ? null : Functions.stringToDate(values[7]);
//
//                    StringBuilder sbClasificacion = new StringBuilder();
//                    sbClasificacion.append(values[8] == null || " ".equals(values[8]) ? null : values[8]);
//                    String clasificacion = sbClasificacion.toString();
//
//                    StringBuilder sbGestion = new StringBuilder();
//                    sbGestion.append(values[9] == null || " ".equals(values[9]) ? null : values[9]);
//                    String gestion = sbGestion.toString();
//
//                    int valorCompromiso = (values[10] == null || " ".equals(values[10]) ? null : Integer.parseInt(values[10]));
//
//                    StringBuilder sbDatosAdicionales = new StringBuilder();
//                    sbDatosAdicionales.append(values[11] == null || " ".equals(values[11]) ? null : values[11]);
//                    String datosAdicionales = sbDatosAdicionales.toString();
//
//                    StringBuilder sbGestionLlamada = new StringBuilder();
//                    sbGestionLlamada.append(values[12] == null || " ".equals(values[12]) ? null : values[12]);
//                    String gestionLlamada = sbGestionLlamada.toString();
//
//                    GestionesDto gestiones = new GestionesDto(numeroObligacion, cedula, clientes, sede, banco, asesor, fechaGestion, fechaCompromiso, clasificacion, gestion, valorCompromiso, datosAdicionales, gestionLlamada);
//
//                    gestionesDto.add(gestiones);
//
//                } catch (Exception e) {
//                    System.err.println(e.getMessage());
//                }
                    System.out.println(Arrays.toString(values));
            }
            return gestionesDto;
        } catch (IOException ex) {
            Logger.getLogger(FileServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
