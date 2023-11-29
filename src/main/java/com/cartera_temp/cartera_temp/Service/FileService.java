

package com.cartera_temp.cartera_temp.Service;

import com.cartera_temp.cartera_temp.Dtos.CuentasPorCobrarDto;
import com.cartera_temp.cartera_temp.Dtos.GestionesDto;
import com.cartera_temp.cartera_temp.Models.CuentasPorCobrar;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;


public interface FileService {
    
    
    public List<CuentasPorCobrarDto> readFile(MultipartFile file, String delimitante);

    public List<GestionesDto> readFileGestiones(MultipartFile file, String delimitante);
}
