package com.cartera_temp.cartera_temp.Service;

import com.cartera_temp.cartera_temp.Models.Sede;
import java.util.List;

public interface SedeService {

    public Sede guardarSede(Sede sede);

    public List<Sede> listarSede();

    public Sede findSede(String sede);
}
