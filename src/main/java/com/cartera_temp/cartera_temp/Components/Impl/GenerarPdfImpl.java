package com.cartera_temp.cartera_temp.Components.Impl;

import com.cartera_temp.cartera_temp.Components.GenerarPdf;
import com.cartera_temp.cartera_temp.Dtos.PagosCuotasDto;
import com.cartera_temp.cartera_temp.Models.AcuerdoPago;
import com.cartera_temp.cartera_temp.Models.CuentasPorCobrar;
import com.cartera_temp.cartera_temp.Models.Gestiones;
import com.cartera_temp.cartera_temp.ModelsClients.Usuario;
import com.cartera_temp.cartera_temp.Service.UsuarioClientService;
import com.cartera_temp.cartera_temp.Utils.Functions;
import com.cartera_temp.cartera_temp.repository.CuentasPorCobrarRepository;
import com.tenpisoft.n2w.MoneyConverters;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class GenerarPdfImpl implements GenerarPdf {

    private final CuentasPorCobrarRepository cpcR;
    private final UsuarioClientService usuClient;

    public GenerarPdfImpl(CuentasPorCobrarRepository cpcR, UsuarioClientService usuClient) {
        this.cpcR = cpcR;
        this.usuClient = usuClient;
    }

    @Override
    public String generarReporteAcuerdoPagoToClient(CuentasPorCobrar cpc) throws IOException, ClassNotFoundException {

        if (Objects.isNull(cpc)) {
            return null;
        }

        List<Gestiones> gestion = cpc.getGestiones();

        List<Gestiones> gestionList = gestion.stream().filter(g -> g.getClasificacion().getClasificacion().equals("ACUERDO DE PAGO") && g.getClasificacion() instanceof AcuerdoPago && ((AcuerdoPago) g.getClasificacion()).isIsActive() == true).collect(Collectors.toList());

        String titulo = "REPORTE ACUERDOS DE PAGO";
        String fecha = "";
        try {
            fecha = Functions.dateToString(gestionList.get(0).getFechaGestion());
        } catch (ParseException ex) {
            Logger.getLogger(GenerarPdfImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sede = cpc.getSede().getSede();
        String nombreCliente = cpc.getCliente();
        String numeroDocumento = cpc.getDocumentoCliente();
        String numeroObligacion = cpc.getNumeroObligacion();

        try {
            try (PDDocument doc = new PDDocument()) {
                PDPage page = new PDPage();
                doc.addPage(page);
                int cellHeight = 20;
                int cellWidth = 95;
                int height = (int) page.getTrimBox().getHeight();//792
                int width = (int) page.getTrimBox().getWidth();//612

                ClassPathResource resource = new ClassPathResource("electrohogarOpa.png");
                InputStream inputStream = resource.getInputStream();
                PDImageXObject logoImage = PDImageXObject.createFromByteArray(doc, IOUtils.toByteArray(inputStream), "electrohogarOpa.png");

                try (PDPageContentStream contens = new PDPageContentStream(doc, page)) {

                    contens.drawImage(logoImage, width / 2 - 150, height / 2 - 30, 300, 100);
                    //CREACION DEL TITULO
                    nuevaLinea(titulo, 160, 750, contens, PDType1Font.HELVETICA_BOLD, 18);

                    //CREACION DE LAS VARIABLES SUBTITULOS ANTES DE LAS TABLAS
                    String fechaDoc = "Fecha: ".toUpperCase();
                    String sedeDoc = "Sede: ".toUpperCase();
                    String nombreClienteDoc = "Nombre del cliente: ".toUpperCase();
                    String numeroDocumentoDoc = "Documento del cliente: ".toUpperCase();
                    String numeroObligacionDoc = "Numero de la obligacion: ".toUpperCase();

                    //CREACION DE SUBTITULOS
                    nuevaLinea(nombreClienteDoc, 70, 720, contens, PDType1Font.HELVETICA_BOLD, 12);
                    nuevaLinea(nombreCliente, 210, 720, contens, PDType1Font.HELVETICA, 12);

                    nuevaLinea(numeroDocumentoDoc, 70, 700, contens, PDType1Font.HELVETICA_BOLD, 12);
                    nuevaLinea(numeroDocumento, 238, 700, contens, PDType1Font.HELVETICA, 12);

                    nuevaLinea(sedeDoc, 70, 680, contens, PDType1Font.HELVETICA_BOLD, 12);
                    nuevaLinea(sede, 110, 680, contens, PDType1Font.HELVETICA, 12);

                    nuevaLinea(fechaDoc, 70, 660, contens, PDType1Font.HELVETICA_BOLD, 12);
                    nuevaLinea(fecha, 117, 660, contens, PDType1Font.HELVETICA, 12);

                    nuevaLinea(numeroObligacionDoc, 70, 640, contens, PDType1Font.HELVETICA_BOLD, 12);
                    nuevaLinea(numeroObligacion, 247, 640, contens, PDType1Font.HELVETICA, 12);

                    String[] cabecerosList = {"#Cuotas", "F.Vencimiento", "Valor cuota", "Capital", "Honorarios", "Intereses"};

                    int inicioTablaX = 40;
                    int inicioTablaY = 590;

                    Gestiones ges = gestionList.get(0);

                    AcuerdoPago acuPago = new AcuerdoPago();

                    if (ges.getClasificacion() instanceof AcuerdoPago) {
                        acuPago = (AcuerdoPago) ges.getClasificacion();
                    }

                    Double totalCuotas = 0.0;

                    for (int i = 0; i < acuPago.getCuotasList().size() + 1; i++) {
                        if (i == 0) {
                            for (int j = 0; j < cabecerosList.length; j++) {

                                if (j == 0 || j == 3) {
                                    cellWidth = 70;
                                    contens.addRect(inicioTablaX, inicioTablaY, cellWidth, cellHeight);
                                    contens.stroke();
                                    nuevaLinea(cabecerosList[j], inicioTablaX + 5, inicioTablaY + 3, contens, PDType1Font.HELVETICA_BOLD, 14);

                                    inicioTablaX = inicioTablaX + cellWidth;
                                } else {

                                    if (j == 1) {
                                        cellWidth = 110;
                                        contens.addRect(inicioTablaX, inicioTablaY, cellWidth, cellHeight);
                                        contens.stroke();
                                        nuevaLinea(cabecerosList[j], inicioTablaX + 5, inicioTablaY + 3, contens, PDType1Font.HELVETICA_BOLD, 14);

                                        inicioTablaX = inicioTablaX + cellWidth;
                                    } else {
                                        cellWidth = 95;
                                        contens.addRect(inicioTablaX, inicioTablaY, cellWidth, cellHeight);
                                        contens.stroke();
                                        nuevaLinea(cabecerosList[j], inicioTablaX + 5, inicioTablaY + 3, contens, PDType1Font.HELVETICA_BOLD, 14);

                                        inicioTablaX = inicioTablaX + cellWidth;
                                    }
                                }

                            }
                        }
                        if (i > 0) {
                            inicioTablaX = 40;
                            inicioTablaY = inicioTablaY - cellHeight;
                            List<String> body = new ArrayList<>();
                            body.add(Integer.toString(acuPago.getCuotasList().get(i - 1).getNumeroCuota()));
                            try {
                                body.add(Functions.dateToString(acuPago.getCuotasList().get(i - 1).getFechaVencimiento()));
                            } catch (ParseException ex) {
                                Logger.getLogger(GenerarPdfImpl.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            body.add(Double.toString(acuPago.getCuotasList().get(i - 1).getValorCuota()));
                            body.add(Double.toString(acuPago.getCuotasList().get(i - 1).getCapitalCuota()));
                            totalCuotas = totalCuotas + acuPago.getCuotasList().get(i - 1).getCapitalCuota();
                            body.add(Double.toString(acuPago.getCuotasList().get(i - 1).getHonorarios()));
                            body.add(Double.toString(acuPago.getCuotasList().get(i - 1).getInteresCuota()));

                            for (int j = 0; j < body.size(); j++) {
                                if (j == 0 || j == 3) {
                                    cellWidth = 70;
                                    contens.addRect(inicioTablaX, inicioTablaY, cellWidth, cellHeight);
                                    contens.stroke();
                                    nuevaLinea(body.get(j), inicioTablaX + 5, inicioTablaY + 3, contens, PDType1Font.HELVETICA, 14);

                                    inicioTablaX = inicioTablaX + cellWidth;
                                } else {

                                    if (j == 1) {
                                        cellWidth = 110;
                                        contens.addRect(inicioTablaX, inicioTablaY, cellWidth, cellHeight);
                                        contens.stroke();
                                        nuevaLinea(body.get(j), inicioTablaX + 5, inicioTablaY + 3, contens, PDType1Font.HELVETICA, 14);

                                        inicioTablaX = inicioTablaX + cellWidth;
                                    } else {
                                        cellWidth = 95;
                                        contens.addRect(inicioTablaX, inicioTablaY, cellWidth, cellHeight);
                                        contens.stroke();
                                        nuevaLinea(body.get(j), inicioTablaX + 5, inicioTablaY + 3, contens, PDType1Font.HELVETICA, 14);

                                        inicioTablaX = inicioTablaX + cellWidth;
                                    }
                                }

                            }
                        }
                        if (i == acuPago.getCuotasList().size()) {

                            for (int j = 0; j < 6; j++) {
                                inicioTablaX = 40;
                                if (j == 0) {
                                    contens.addRect(inicioTablaX, inicioTablaY - cellHeight, 180, cellHeight);
                                    contens.stroke();
                                    nuevaLinea("Valores totales del acuerdo", inicioTablaX + 5, inicioTablaY + 3 - cellHeight, contens, PDType1Font.HELVETICA_BOLD, 13);
                                }
                                if (j == 2) {
                                    cellWidth = 95;
                                    contens.addRect(inicioTablaX + 180, inicioTablaY - cellHeight, cellWidth, cellHeight);
                                    contens.stroke();
                                    nuevaLinea(Double.toString(acuPago.getValorTotalAcuerdo()), inicioTablaX + 185, inicioTablaY + 3 - cellHeight, contens, PDType1Font.HELVETICA, 13);
                                }
                                if (j == 3) {
                                    cellWidth = 70;
                                    contens.addRect(inicioTablaX + 180 + 95, inicioTablaY - cellHeight, cellWidth, cellHeight);
                                    contens.stroke();
                                    nuevaLinea(Double.toString(totalCuotas), inicioTablaX + 180 + 100, inicioTablaY + 3 - cellHeight, contens, PDType1Font.HELVETICA, 13);
                                }
                                if (j == 4) {
                                    cellWidth = 95;
                                    contens.addRect(inicioTablaX + 180 + 95 + 70, inicioTablaY - cellHeight, cellWidth, cellHeight);
                                    contens.stroke();
                                    nuevaLinea(Double.toString(acuPago.getHonorarioAcuerdo()), inicioTablaX + 180 + 100 + 70, inicioTablaY + 3 - cellHeight, contens, PDType1Font.HELVETICA, 13);
                                }
                                if (j == 5) {
                                    cellWidth = 95;
                                    contens.addRect(inicioTablaX + 180 + 100 + 70 + 90, inicioTablaY - cellHeight, cellWidth, cellHeight);
                                    contens.stroke();
                                    nuevaLinea(Double.toString(acuPago.getValorInteresesMora()), inicioTablaX + 180 + 100 + 75 + 90, inicioTablaY + 3 - cellHeight, contens, PDType1Font.HELVETICA, 13);
                                }

                            }

                        }
                    }

                    contens.close();
                    String base64 = convertPdfToBase64(doc);
                    return base64;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public String generarReportePagoCuotas(PagosCuotasDto dto) throws IOException, ClassNotFoundException {

        CuentasPorCobrar cpc = cpcR.findByNumeroObligacion(dto.getNumeroObligacion());

        if (Objects.isNull(cpc)) {
            return null;
        }

        String titulo = "RECIBO DE CAJA";

        String sedeComercial = cpc.getSede().getNombreComercialSede().toUpperCase();
        String direccionSede = cpc.getSede().getDireccionSede().toUpperCase();
        String numeroSede = cpc.getSede().getTelefonoSede();
        //YEIMAR DIJO QUE EL NIT ES IGUAL PARA TODOS(SI NO ES ASI LO ACABO A PALO)
        String NIT = "NIT: 901056810-9 REGIMEN COMUN".toUpperCase();
        String idPago = "#".concat(dto.getNumeroRecibo());
        String cedulaDoc = "C.C: ".concat(cpc.getDocumentoCliente());
        String fechaRecibo = "";
        try {
            fechaRecibo = "Fecha: ".concat(Functions.dateToString(Functions.obtenerFechaYhora()));
        } catch (ParseException ex) {
            Logger.getLogger(GenerarPdfImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        //DECLARACION DE VARIABLES PARA EL BODY DE LA TABLA
        String clientePago = cpc.getCliente();
        int valorPago = dto.getValorTotal();
        MoneyConverters converter1 = MoneyConverters.SPANISH_BANKING_MONEY_VALUE;
        String valorEnPalabras = converter1.asWords(new BigDecimal(valorPago)).toUpperCase();

        String[] splitPalabras = valorEnPalabras.split(" ");

        if (splitPalabras[0].equals("UNO") || splitPalabras[0].equals("uno")) {
            splitPalabras[0] = "UN";
        }

        String valorDocumentoPalabras = "";

        for (String splitPalabra : splitPalabras) {
            valorDocumentoPalabras = valorDocumentoPalabras.concat(" ").concat(splitPalabra);
        }

        Usuario usu = usuClient.obtenerUsuario(dto.getUsername());
        if (Objects.isNull(usu)) {
            return null;
        }

        String tiposPago = dto.getMetodoPago();

        int pagoEfectivo = 0, pagoCheque = 0, pagoDebito = 0, pagoCredito = 0, adelantos = 0;
        int saldoTotalAcuerdo = dto.getAcuerdoTotal();
        int saldoTotalCapital = dto.getCapitalTotal();
        int saldoTotalHonorarios = dto.getHonorariosTotal();
        int saldoTotalIntereses = dto.getInteresesTotal();

        switch (tiposPago) {
            case "EFECTIVO":
                pagoEfectivo = dto.getValorTotal();
                break;
            case "CHEQUE":
                pagoCheque = dto.getValorTotal();
                break;
            case "T. DEBITO":
                pagoDebito = dto.getValorTotal();
                break;
            case "T. CREDITO":
                pagoCredito = dto.getValorTotal();
                break;
            case "ADELANTOS APLICADOS":
                adelantos = dto.getValorTotal();
                break;
            default:
                throw new AssertionError();
        }

        String detallePago = dto.getDetalle().toUpperCase();
        String usuarioPago = usu.getNombres().toUpperCase().concat(" ").concat(usu.getApellidos()).toUpperCase();
        String saldo = "SALDO: ".concat(Integer.toString(dto.getSaldo()));
        String footer = "GMJ HOGAS S.A.S.";
        String firma = "Recibi conforme: __________________________";

        try {
            try (PDDocument doc = new PDDocument()) {
                PDPage page = new PDPage();
                doc.addPage(page);
                int cellHeight = 90;
                int cellWidth = 550;
                int height = (int) page.getTrimBox().getHeight() / 2;//346
                int width = (int) page.getTrimBox().getWidth();//612

                ClassPathResource resource = new ClassPathResource("electrohogarOpa.png");
                InputStream inputStream = resource.getInputStream();
                PDImageXObject logoImage = PDImageXObject.createFromByteArray(doc, IOUtils.toByteArray(inputStream), "electrohogarOpa.png");

                try (PDPageContentStream contens = new PDPageContentStream(doc, page)) {

                    contens.drawImage(logoImage, 200, 700, 200, 80);

                    //TITULO PARTE SUPERIOR DERECHA NUEVO CAMBIO
                    nuevaLinea(titulo, 50, 740, contens, PDType1Font.HELVETICA_BOLD, 12);
                    nuevaLinea(idPago, 50, 725, contens, PDType1Font.HELVETICA, 12);

                    //PARTE SUPERIOR DERECHA NUEVO CAMBIO
                    nuevaLinea(sedeComercial, 400, 750, contens, PDType1Font.HELVETICA, 12);
                    nuevaLinea(direccionSede, 400, 735, contens, PDType1Font.HELVETICA, 12);
                    nuevaLinea(numeroSede, 400, 720, contens, PDType1Font.HELVETICA, 12);
                    nuevaLinea(NIT, 400, 705, contens, PDType1Font.HELVETICA, 12);

                    int inicioTablaX = 30;
                    int inicioTablaY = 620;

                    for (int i = 0; i < 2; i++) {
                        if (i == 0) {
                            contens.addRect(inicioTablaX, inicioTablaY + 20, cellWidth, cellHeight - 40);
                            contens.stroke();
                            nuevaLinea("Hemos recibido de: ".concat(clientePago), inicioTablaX + 5, inicioTablaY + 55, contens, PDType1Font.HELVETICA, 11);
                            nuevaLinea("La cantidad de: ".concat("$").concat(formatNumber(valorPago)), inicioTablaX + 5, inicioTablaY + 40, contens, PDType1Font.HELVETICA, 11);
                            nuevaLinea("Son: ".concat(valorDocumentoPalabras.trim()).toUpperCase(), inicioTablaX + 5, inicioTablaY + 25, contens, PDType1Font.HELVETICA, 9);

                            //PARTE SUPERIOR DERECHA PRIMERA FILA DE LA TABLA NUEVO CAMBIO
                            nuevaLinea(fechaRecibo, inicioTablaX + 400, inicioTablaY + 45, contens, PDType1Font.HELVETICA, 12);
                            nuevaLinea(cedulaDoc, inicioTablaX + 400, inicioTablaY + 30, contens, PDType1Font.HELVETICA, 12);
                        }

                        if (i == 1) {
                            contens.addRect(inicioTablaX, inicioTablaY - cellHeight - 50, cellWidth, 160);
                            contens.stroke();
                            nuevaLinea("Por concepto de: ".concat(detallePago), inicioTablaX + 5, inicioTablaY + 5, contens, PDType1Font.HELVETICA, 11);
                            nuevaLinea("Total efectivo: ", inicioTablaX + 5, inicioTablaY - 25, contens, PDType1Font.HELVETICA, 11);
                            nuevaLinea("Total cheque: ", inicioTablaX + 5, inicioTablaY - 45, contens, PDType1Font.HELVETICA, 11);
                            nuevaLinea("Total tarjeta débito: ", inicioTablaX + 5, inicioTablaY - 65, contens, PDType1Font.HELVETICA, 11);
                            nuevaLinea("Total tarjeta crédito: ", inicioTablaX + 5, inicioTablaY - 85, contens, PDType1Font.HELVETICA, 11);
                            nuevaLinea("Adelantos aplicados: ", inicioTablaX + 5, inicioTablaY - 105, contens, PDType1Font.HELVETICA, 11);
                            nuevaLinea("$".concat(formatNumber(pagoEfectivo)), inicioTablaX + 120, inicioTablaY - 25, contens, PDType1Font.HELVETICA, 11);
                            nuevaLinea("$".concat(formatNumber(pagoCheque)), inicioTablaX + 120, inicioTablaY - 45, contens, PDType1Font.HELVETICA, 11);
                            nuevaLinea("$".concat(formatNumber(pagoDebito)), inicioTablaX + 120, inicioTablaY - 65, contens, PDType1Font.HELVETICA, 11);
                            nuevaLinea("$".concat(formatNumber(pagoCredito)), inicioTablaX + 120, inicioTablaY - 85, contens, PDType1Font.HELVETICA, 11);
                            nuevaLinea("$".concat(formatNumber(adelantos)), inicioTablaX + 120, inicioTablaY - 105, contens, PDType1Font.HELVETICA, 11);

                            nuevaLinea("Saldo acuerdo: ".concat("$").concat(formatNumber(saldoTotalAcuerdo)), inicioTablaX + 350, inicioTablaY - 30, contens, PDType1Font.HELVETICA, 11);
                            nuevaLinea("Saldo capital: ".concat("$").concat(formatNumber(saldoTotalCapital)), inicioTablaX + 350, inicioTablaY - 45, contens, PDType1Font.HELVETICA, 11);
                            nuevaLinea("Saldo intereses: ".concat("$").concat(formatNumber(saldoTotalIntereses)), inicioTablaX + 350, inicioTablaY - 60, contens, PDType1Font.HELVETICA, 11);
                            nuevaLinea("Saldo honorarios: ".concat("$").concat(formatNumber(saldoTotalHonorarios)), inicioTablaX + 350, inicioTablaY - 75, contens, PDType1Font.HELVETICA, 11);

                            nuevaLinea(firma, inicioTablaX + 5, inicioTablaY - 125, contens, PDType1Font.HELVETICA, 11);
                            nuevaLinea(usuarioPago, inicioTablaX + 380, inicioTablaY - 125, contens, PDType1Font.HELVETICA, 11);
                            nuevaLinea(footer, 270, inicioTablaY - 137, contens, PDType1Font.HELVETICA, 8);

                        }

                    }

                    contens.close();
                    String base64 = convertPdfToBase64(doc);
                    return base64;

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String convertPdfToBase64(PDDocument document) throws IOException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            try (document) {
                document.save(byteArrayOutputStream);
            }
            // Codificar en Base64
            byte[] bytes = byteArrayOutputStream.toByteArray();
            return Base64.getEncoder().encodeToString(bytes);
        }
    }

    public static void nuevaLinea(String linea, int x, int y, PDPageContentStream contens, PDFont fuente, int tamañoFont) throws IOException {
        contens.beginText();
        PDFont font = fuente;
        contens.setFont(font, tamañoFont);
        contens.newLineAtOffset(x, y);

        int caracteresPorLinea = 80;
        if (linea.length() > caracteresPorLinea) {
            for (int i = 0; i < linea.length(); i += caracteresPorLinea) {
                int finIndice = Math.min(i + caracteresPorLinea, linea.length());
                String segmento = linea.substring(i, finIndice);
                contens.showText(segmento);
                contens.newLineAtOffset(0, -tamañoFont); // Salto de línea
            }
            contens.endText();
        } else {
            contens.showText(linea);
            contens.endText();
        }
    }

    public static String formatNumber(int numero1) {
        String numeroString = Integer.toString(numero1);
        int maxNumber = 3;

        if (numeroString.length() > maxNumber) {
            StringBuilder formattedNumber = new StringBuilder();
            int count = 0;

            for (int i = numeroString.length() - 1; i >= 0; i--) {
                char digit = numeroString.charAt(i);
                formattedNumber.insert(0, digit);

                count++;
                if (count == maxNumber && i > 0) {
                    formattedNumber.insert(0, '.');
                    count = 0;
                }
            }

            return formattedNumber.toString();
        }

        return numeroString;
    }

}
