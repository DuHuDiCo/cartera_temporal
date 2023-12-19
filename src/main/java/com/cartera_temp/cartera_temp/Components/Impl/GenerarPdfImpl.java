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
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
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

        Gestiones lastGestion = gestion.get(gestion.size() - 1);

        ClasificacionGestion clasiGestion = lastGestion.getClasificacionGestion();

        AcuerdoPago acuPago = new AcuerdoPago();

        if (clasiGestion instanceof AcuerdoPago) {

            acuPago = (AcuerdoPago) clasiGestion;

        }

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
                    String fechaDoc = "Fecha: ".concat(fecha);
                    String sedeDoc = "Sede: ".concat(sede).toUpperCase();
                    String nombreClienteDoc = "Nombre del cliente: ".concat(nombreCliente).toUpperCase();
                    String numeroDocumentoDoc = "Documento del cliente: ".concat(numeroDocumento);
                    String numeroObligacionDoc = "Numero de la obligacion: ".concat(numeroObligacion).toUpperCase();

                    //CREACION DE SUBTITULOS
                    nuevaLinea(nombreClienteDoc, 70, 720, contens, PDType1Font.HELVETICA_BOLD, 12);
                    nuevaLinea(numeroDocumentoDoc, 70, 700, contens, PDType1Font.HELVETICA_BOLD, 12);
                    nuevaLinea(sedeDoc, 70, 680, contens, PDType1Font.HELVETICA_BOLD, 12);
                    nuevaLinea(fechaDoc, 70, 660, contens, PDType1Font.HELVETICA_BOLD, 12);
                    nuevaLinea(numeroObligacionDoc, 70, 640, contens, PDType1Font.HELVETICA_BOLD, 12);

                    String[] cabecerosList = {"°Cuotas", "F.Vencimiento","Valor cuota", "Capital", "Honorarios", "Intereses"};
                    
                    int inicioTablaX = 40;
                    int inicioTablaY = 610;
                    
//                    for(int i = 0; i < acuPago.getCuotasList().size(); i++){
//                        
//                        for (int j = 0; j < cabecerosList.length; j++) {
//                            
//                            contens.addRect(inicioTablaX, inicioTablaY, cellWidth, cellHeight);
//                            contens.stroke();
//                            nuevaLinea(cabecerosList[0], inicioTablaX + 5, inicioTablaY -5, contens, PDType1Font.HELVETICA_BOLD, 14);
//                            
//                            inicioTablaX = inicioTablaX + cellWidth;
//                        }
//                        break;
//                    }
                    System.out.println(acuPago.getCuotasList().size() + "___________________________________________");
                    
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

    public static String retornarCabeceros(int num) {
        String pal = null;
        switch (num) {
            case 1:
                pal = "N° Cuota";
                break;
            case 2:
                pal = "Fecha vencimiento";
                break;
            case 3:
                pal = "Valor cuota";
                break;
            case 4:
                pal = "Capital cuota";
                break;
            case 5:
                pal = "Honorarios";
                break;
            default:
                pal = "Intereses";
        }

        return pal;
    }

    public static String retornarBody(int num, Cuotas cuota) {
        String pal = null;

        switch (num) {
            case 1:
                pal = Integer.toString(cuota.getNumeroCuota());
                break;

            case 2: {
                try {
                    pal = Functions.dateToString(cuota.getFechaVencimiento());
                } catch (ParseException ex) {
                    Logger.getLogger(GenerarPdfImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

            case 3:
                pal = Double.toString(cuota.getValorCuota());
                break;
            case 4:
                pal = Double.toString(cuota.getCapitalCuota());
                break;

            case 5:
                pal = Double.toString(cuota.getHonorarios());
                break;

            default:
                pal = Double.toString(cuota.getInteresCuota());
        }

        return pal;
    }

}
