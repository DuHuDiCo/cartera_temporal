package com.cartera_temp.cartera_temp.Service;

import com.cartera_temp.cartera_temp.Models.TiposVencimiento;
import java.util.List;

public interface TiposVencimientoService {
    
    public TiposVencimiento guardarTipoVencimiento(TiposVencimiento tv);
    
    public List<TiposVencimiento> obtenerTiposVencimiento();
    
    public TiposVencimiento obtenerTipoVencimientoById(Long idTipoVencimiento);
    
    public TiposVencimiento obtenerTipoVencimientoByNombre(String tipoVencimiento);
    
    public TiposVencimiento actualizarTipoVencimiento(TiposVencimiento tv);
    
}
