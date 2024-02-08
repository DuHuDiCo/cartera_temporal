package com.cartera_temp.cartera_temp.Utils;

import com.cartera_temp.cartera_temp.ServiceImpl.CustomMultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SaveFiles {

    public MultipartFile convertirFile(String base64) {

        final String[] base64Array = base64.split(",");

        String dataUir, data;

        if (base64Array.length > 1) {
            dataUir = base64Array[0];
            data = base64Array[1];
        } else {
            dataUir = "data:application/pdf;base64";
            data = base64Array[0];
        }

        MultipartFile multipartFile = new CustomMultipartFile(data, dataUir);
        return multipartFile;
    }

    public String saveFile(byte[] bytes, String name, String ruta) throws FileNotFoundException, IOException {

        Path path = Paths.get(ruta);
        Files.write(path, bytes);
        return name;

    }

    public  String obtenerRuta(String nombreArchivo, String ruta, String sede) throws IOException {

        Path reportesFolderPath = Paths.get(ruta.concat("/").concat("RECIBOS"));

        if (!Files.exists(reportesFolderPath)) {
            //CREA DIRECTORIO PRINCIPAL "DOCUMENTACION"
            Files.createDirectories(reportesFolderPath);
        }

        Path sedePath = reportesFolderPath.resolve(sede);

        if (!Files.exists(sedePath)) {
            //CREAR DIRECTORIO DE RECIBOS POR SEDES
            Files.createDirectories(sedePath);

            Path folderCuentaPath = sedePath.resolve(sedePath);
            if (!Files.exists(folderCuentaPath)) {
                //CREA DIRECTRIO POR NUMERO DE OBLIGACION EN LA SEDE
                Files.createDirectories(folderCuentaPath);
                
                
                return folderCuentaPath.resolve(nombreArchivo).toString();
            }

            return folderCuentaPath.resolve(nombreArchivo).toString();
        }
        return sedePath.resolve(nombreArchivo).toString();
    }
    
     public String pdfToBase64(String ruta) throws FileNotFoundException, IOException {
        File img = new File(ruta);
        Base64 base64 = new Base64();
        byte[] imageBytes = new byte[(int) img.length()];
        InputStream inputStream = new FileInputStream(img);
        inputStream.read(imageBytes);
        String encodedFile = base64.encodeToString(imageBytes);
        
        return encodedFile;
    }
     
     public byte[] fileToByte(String ruta) throws FileNotFoundException, IOException{
         System.out.println(ruta);
         File file = new File(ruta);
         byte[] imageBytes = new byte[(int) file.length()];
         
         return imageBytes;
     }

}
