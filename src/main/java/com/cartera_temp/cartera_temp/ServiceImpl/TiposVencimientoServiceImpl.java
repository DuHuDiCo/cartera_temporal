package com.cartera_temp.cartera_temp.ServiceImpl;

import com.cartera_temp.cartera_temp.Models.TiposVencimiento;
import com.cartera_temp.cartera_temp.Service.TiposVencimientoService;
import com.cartera_temp.cartera_temp.repository.TiposVencimientoRepository;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;


@Service
public class TiposVencimientoServiceImpl implements TiposVencimientoService{
    
    private final TiposVencimientoRepository tiposVencimientoRepository;

    public TiposVencimientoServiceImpl(TiposVencimientoRepository tiposVencimientoRepository) {
        this.tiposVencimientoRepository = tiposVencimientoRepository;
    }

    @Override
    public TiposVencimiento guardarTipoVencimiento(TiposVencimiento tv) {
        
        if(tv.getTipoVencimiento() == null){
            return null;
        }
        
        TiposVencimiento tvFind = tiposVencimientoRepository.findByTipoVencimiento(tv.getTipoVencimiento());
        
        if(!Objects.isNull(tvFind)){
            return null;
        }
        
        TiposVencimiento tvSave = new TiposVencimiento();
        
        tvSave.setTipoVencimiento(tv.getTipoVencimiento().toUpperCase());
        
        tvSave = tiposVencimientoRepository.save(tvSave);
        return tvSave;
        
    }

    @Override
    public List<TiposVencimiento> obtenerTiposVencimiento() {
        List<TiposVencimiento> tv = tiposVencimientoRepository.findAll();
        return tv;
    }

    @Override
    public TiposVencimiento obtenerTipoVencimientoById(Long idTipoVencimiento) {
        
        if(idTipoVencimiento == null){
            return null;
        }
        
        TiposVencimiento tv = tiposVencimientoRepository.findById(idTipoVencimiento).orElse(null);
        if(Objects.isNull(tv)){
            return null;
        }
        return tv;        
    }

    @Override
    public TiposVencimiento obtenerTipoVencimientoByNombre(String tipoVencimiento) {
        if(tipoVencimiento == null || tipoVencimiento == ""){
            return null;
        }
        
        TiposVencimiento tv = tiposVencimientoRepository.findByTipoVencimiento(tipoVencimiento);
        if(Objects.isNull(tv)){
            return null;
        }
        return tv;
    }

    @Override
    public TiposVencimiento actualizarTipoVencimiento(TiposVencimiento tv) {
        
        if(tv.getIdTipoVencimiento() == null || tv.getIdTipoVencimiento() == 0 || tv.getTipoVencimiento() == "" || tv.getTipoVencimiento() == null){
            return null;
        }
        
        TiposVencimiento tvUpdate = tiposVencimientoRepository.findById(tv.getIdTipoVencimiento()).orElse(null);
        if(Objects.nonNull(tvUpdate)){
            tvUpdate = tiposVencimientoRepository.save(tv);
            return tvUpdate;
        }
        return null;
    }
    
    
    
}
