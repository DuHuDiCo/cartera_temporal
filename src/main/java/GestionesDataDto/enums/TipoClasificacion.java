
package GestionesDataDto.enums;


public enum TipoClasificacion {
    
    ACUERDODEPAGO("ACUERDO DE PAGO"),
    NOTA("NOTA"),
    TAREA("TAREA");
    
    private final String dato;

    private TipoClasificacion(String dato) {
        this.dato = dato;
    }

    public String getDato() {
        return dato;
    }
    
    
    
}
