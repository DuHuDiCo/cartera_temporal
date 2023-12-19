package com.cartera_temp.cartera_temp.Components.Impl;

import com.cartera_temp.cartera_temp.Components.GenerarPdf;
import com.cartera_temp.cartera_temp.Models.AcuerdoPago;
import com.cartera_temp.cartera_temp.Models.ClasificacionGestion;
import com.cartera_temp.cartera_temp.Models.CuentasPorCobrar;
import com.cartera_temp.cartera_temp.Models.Cuotas;
import com.cartera_temp.cartera_temp.Models.Gestiones;
import com.cartera_temp.cartera_temp.Utils.Functions;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

@Component
public class GenerarPdfImpl implements GenerarPdf {

    @Override
    public String generarReporteAcuerdoPagoToClient(CuentasPorCobrar cpc) throws IOException, ClassNotFoundException {

        if (Objects.isNull(cpc)) {
            return null;
        }

        List<Gestiones> gestion = cpc.getGestiones();

        List<Gestiones> gestionList = gestion.stream().filter(g -> g.getClasificacion().getClasificacion().equals("ACUERDO DE PAGO") && g.getClasificacion() instanceof AcuerdoPago && ((AcuerdoPago) g.getClasificacion()).isIsActive() == true).collect(Collectors.toList());

        String titulo = "REPORTE ACUERDOS DE PAGO";
        String fecha = Functions.fechaDateToStringSinHora();
        String sede = cpc.getSede().getSede();
        String nombreCliente = cpc.getCliente();
        String numeroDocumento = cpc.getDocumentoCliente();
        String numeroObligacion = cpc.getNumeroObligacion();

        try {
            try (PDDocument doc = new PDDocument()) {
                PDPage page = new PDPage();
                doc.addPage(page);
                int height = (int) page.getTrimBox().getHeight();
                int width = (int) page.getTrimBox().getWidth();
                int initX = 20;
                int initY = height - 150;
                int cellHeight = 20;
                int cellWidth = 95;
                int colCount = 6;

                try (PDPageContentStream contens = new PDPageContentStream(doc, page)) {
                    //CREACION DEL TITULO
                    nuevaLinea(titulo, 160, 750, contens, PDType1Font.HELVETICA_BOLD, 18);
                    int con = 10;

                    //CREACION DE LAS VARIABLES SUBTITULOS ANTES DE LAS TABLAS
                    String fechaDoc = "Fecha: ".concat(fecha).toUpperCase();
                    String sedeDoc = "Sede: ".concat(sede).toUpperCase().toUpperCase();
                    String nombreClienteDoc = "Nombre del cliente: ".concat(nombreCliente).toUpperCase();
                    String numeroDocumentoDoc = "Documento del cliente: ".concat(numeroDocumento).toUpperCase();
                    String numeroObligacionDoc = "Numero de la obligacion: ".concat(numeroObligacion).toUpperCase();

                    //CREACION DE SUBTITULOS
                    nuevaLinea(nombreClienteDoc, 70, 720, contens, PDType1Font.HELVETICA_BOLD, 12);
                    nuevaLinea(numeroDocumentoDoc, 70, 700, contens, PDType1Font.HELVETICA_BOLD, 12);
                    nuevaLinea(sedeDoc, 70, 680, contens, PDType1Font.HELVETICA_BOLD, 12);
                    nuevaLinea(fechaDoc, 70, 660, contens, PDType1Font.HELVETICA_BOLD, 12);
                    nuevaLinea(numeroObligacionDoc, 70, 640, contens, PDType1Font.HELVETICA_BOLD, 12);

                    String[] cabecerosList = {"°Cuotas", "F.Vencimiento", "Valor cuota", "Capital", "Honorarios", "Intereses"};

                    int inicioTablaX = 40;
                    int inicioTablaY = 610;

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
                                    nuevaLinea(body.get(j), inicioTablaX + 5, inicioTablaY + 3, contens, PDType1Font.HELVETICA_BOLD, 14);

                                    inicioTablaX = inicioTablaX + cellWidth;
                                } else {

                                    if (j == 1) {
                                        cellWidth = 110;
                                        contens.addRect(inicioTablaX, inicioTablaY, cellWidth, cellHeight);
                                        contens.stroke();
                                        nuevaLinea(body.get(j), inicioTablaX + 5, inicioTablaY + 3, contens, PDType1Font.HELVETICA_BOLD, 14);

                                        inicioTablaX = inicioTablaX + cellWidth;
                                    } else {
                                        cellWidth = 95;
                                        contens.addRect(inicioTablaX, inicioTablaY, cellWidth, cellHeight);
                                        contens.stroke();
                                        nuevaLinea(body.get(j), inicioTablaX + 5, inicioTablaY + 3, contens, PDType1Font.HELVETICA_BOLD, 14);

                                        inicioTablaX = inicioTablaX + cellWidth;
                                    }
                                }

                            }
                        }
                        if (i == acuPago.getCuotasList().size()) {

                            for (int j = 0; j < 5; j++) {
                                inicioTablaX = 40;
                                if (j == 0) {
                                    contens.addRect(inicioTablaX, inicioTablaY - cellHeight, 180, cellHeight);
                                    contens.stroke();
                                    nuevaLinea("Valores totales del acuerdo", inicioTablaX + 5, inicioTablaY +3 -cellHeight, contens, PDType1Font.HELVETICA_BOLD, 12);
                                }
                                if(j == 2){
                                    cellWidth = 95;
                                    contens.addRect(inicioTablaX + 180, inicioTablaY - cellHeight, cellWidth, cellHeight);
                                    contens.stroke();
                                    nuevaLinea(Double.toString(acuPago.getValorTotalAcuerdo()), inicioTablaX + 185, inicioTablaY +3 -cellHeight, contens, PDType1Font.HELVETICA, 12);
                                }
                                if(j == 3){
                                    cellWidth = 70;
                                    contens.addRect(inicioTablaX + 180 + 95, inicioTablaY - cellHeight, cellWidth, cellHeight);
                                    contens.stroke();
                                    nuevaLinea(Double.toString(totalCuotas), inicioTablaX + 180 + 100, inicioTablaY + 3 -cellHeight, contens, PDType1Font.HELVETICA, 12);
                                }
                                if(j == 4){
                                    cellWidth = 95;
                                    contens.addRect(inicioTablaX + 180 + 95 + 70, inicioTablaY - cellHeight, cellWidth, cellHeight);
                                    contens.stroke();
                                    nuevaLinea(Double.toString(acuPago.getHonoriarioAcuerdo()), inicioTablaX + 180 + 100 + 75, inicioTablaY +3 -cellHeight, contens, PDType1Font.HELVETICA, 12);
                                }
                                if(j == 5){
                                    cellWidth = 95;
                                    contens.addRect(inicioTablaX + 180 + 100 + 70 + 190, inicioTablaY - cellHeight, cellWidth, cellHeight);
                                    contens.stroke();
                                    nuevaLinea(Double.toString(acuPago.getValorInteresesMora()), inicioTablaX + 180 + 100 + 75 + 100, inicioTablaY +3 -cellHeight, contens, PDType1Font.HELVETICA, 12);
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

        contens.showText(linea);
        contens.endText();
    }
}
