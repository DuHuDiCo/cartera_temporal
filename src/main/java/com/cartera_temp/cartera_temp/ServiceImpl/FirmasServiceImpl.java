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
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FirmasServiceImpl implements FirmasService {

    private final FirmasRespository fr;
    private final SaveFiles save;

    @Value("${ruta.firmas}")
    private String ruta;

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

        String[] data = dto.getBase64().split(",");
        
        MultipartFile firma = new CustomMultipartFile(data[1], data[0]);
        
        
        
        String nombreFirmaBd = firma.getOriginalFilename();

        String firmaSave = null;
        try {
            firmaSave = save.saveFile(firma.getBytes(), nombreFirmaBd, ruta.concat("/").concat(nombreFirmaBd));

        } catch (IOException ex) {
            Logger.getLogger(FirmasServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        Firmas fSave = new Firmas();

        fSave.setFilename(nombreFirmaBd);
        fSave.setUsername(dto.getUsername());
        fSave.setRuta(ruta.concat("/").concat(firmaSave));
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
