package com.cartera_temp.cartera_temp.ServiceImpl;

import com.cartera_temp.cartera_temp.Dtos.CuentasPorCobrarDto;
import com.cartera_temp.cartera_temp.Service.FileService;
import com.cartera_temp.cartera_temp.Utils.Functions;
import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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
                    
                    String numeroObligacion = (values[0] == null || " ".equals(values[0])) ? "N/A" : Functions.quitarCaracteresEspeciales(values[0]);
                    String clientes = (values[1] == null || " ".equals(values[1])) ? "N/A" : values[1];
                    Date fechaCredito = (values[2] == null || " ".equals(values[2])) ? null : Functions.stringToDate(values[2]);
                    Date fechaVencimiento = (values[3] == null || " ".equals(values[3])) ? null : Functions.stringToDate(values[3]);
                    String tipo = (values[4] == null || " ".equals(values[4]) ? null : Functions.quitarCaracteresEspeciales(values[4]));
                    double valorNotaDebito = (values[5] == null || " ".equals(values[5])) ? null : Double.valueOf(Functions.containsOnlyNumbers(values[5]));
                    double valorCuota = (values[6] == null || " ".equals(values[6])) ? null : Double.valueOf(Functions.containsOnlyNumbers(values[6]));
                    double valorPagos = (values[7] == null || " ".equals(values[7])) ? null : Double.valueOf(Functions.containsOnlyNumbers(values[7]));
                    String nombreUsuario = (values[8] == null || " ".equals(values[8]) ? null : Functions.quitarCaracteresEspeciales(values[8]));
                    String clasificacion = (values[9] == null || " ".equals(values[9]) ? null : values[9]);
                    String vendedor = (values[10] == null || " ".equals(values[10]) ? null : Functions.quitarCaracteresEspeciales(values[10]));
                    String detalle = (values[11] == null || " ".equals(values[11]) ? null : Functions.quitarCaracteresEspeciales(values[11]));
                    String sede = (values[12] == null || " ".equals(values[12]) ? null : Functions.quitarCaracteresEspeciales(values[12]));
                    String banco = (values[13] == null || " ".equals(values[13]) ? null : Functions.quitarCaracteresEspeciales(values[13]));
                    String cedula = (values[14] == null || " ".equals(values[14]) ? null : Functions.quitarCaracteresEspeciales(values[14]));
                    String clasificacionJuridica = (values[15] == null || " ".equals(values[15]) ? null : Functions.quitarCaracteresEspeciales(values[15]));
                    String asesorCartera = (values[16] == null || " ".equals(values[16]) ? null : Functions.quitarCaracteresEspeciales(values[16]));
                    int diasVencidos = (values[17] == null || " ".equals(values[17]) ? null : Integer.parseInt(values[17]));
                    String edadVencimiento = (values[18] == null || " ".equals(values[18]) ? null : Functions.quitarCaracteresEspeciales(values[18]));
                    String condicionEspecail = (values[19] == null || " ".equals(values[19]) ? null : Functions.quitarCaracteresEspeciales(values[19]));
                    int creditos = (values[20] == null || " ".equals(values[20]) ? null : Integer.parseInt(values[20]));
                    String pagare = (values[21] == null || " ".equals(values[21]) ? null : Functions.quitarCaracteresEspeciales(values[21]));
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

}
