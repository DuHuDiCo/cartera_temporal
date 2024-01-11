package com.cartera_temp.cartera_temp.ServiceImpl;

import com.cartera_temp.cartera_temp.Dtos.FirmasDto;
import com.cartera_temp.cartera_temp.Models.Firmas;
import com.cartera_temp.cartera_temp.Service.FirmasService;
import com.cartera_temp.cartera_temp.Utils.SaveFiles;
import com.cartera_temp.cartera_temp.repository.FirmasRespository;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FirmasServiceImpl implements FirmasService {

    private final FirmasRespository fr;
    private final SaveFiles save;


    private final String rutaRecursos = "J:\\Duvan Humberto Diaz Contreras\\ElectroHogar\\GMJHOGAR_SOFTWARE\\cartera_microservice_temporal\\cartera_temporal\\src\\main\\resources";

    public FirmasServiceImpl(FirmasRespository fr, SaveFiles save) {
        this.fr = fr;
        this.save = save;
    }

    @Override
    public Firmas guardarfirmas(FirmasDto dto) {

        if (dto.getBase64() == null || dto.getBase64() == "" || dto.getUsername() == "" || dto.getUsername() == null) {
            return null;
        }

        Firmas fFind = fr.findByUsername(dto.getUsername());
        if (Objects.nonNull(fFind)) {
            return null;
        }

        MultipartFile firma = save.convertirFile(rutaRecursos);

        String nombreFirmaBd = "firma-".toUpperCase().concat(dto.getUsername().toUpperCase().concat(".png").toUpperCase());
        
        try {
            String firmaSave = save.saveFile(firma.getBytes(), nombreFirmaBd.toUpperCase(), rutaRecursos);
        } catch (IOException ex) {
            Logger.getLogger(FirmasServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Firmas fSave = new Firmas();

        fSave.setNombreArchivo(nombreFirmaBd.toUpperCase());
        fSave.setUsername(dto.getUsername());
        fSave = fr.save(fSave);
        return fSave;

    }

    @Override
    public List<Firmas> listarFirmas() {

        List<Firmas> firmasList = fr.findAll();

        return firmasList;

    }

    @Override
    public Firmas findFirmaByUsername(String username) {

        if (username == "" || username == null) {
            return null;
        }

        Firmas firmaUsu = fr.findByUsername(username);
        if (Objects.isNull(firmaUsu)) {
            return null;
        }
        return firmaUsu;

    }

    @Override
    public Firmas findFirmaById(Long id) {

        if (id == null || id == 0) {
            return null;
        }

        Firmas fId = fr.findById(id).orElse(null);
        if (Objects.isNull(fId)) {
            return null;
        }

        return fId;

    }

    @Override
    public void deleteFirma(Long id) {

        fr.deleteById(id);

    }

}
