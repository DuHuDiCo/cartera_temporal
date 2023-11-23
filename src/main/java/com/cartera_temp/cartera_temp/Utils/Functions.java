
package com.cartera_temp.cartera_temp.Utils;

import com.cartera_temp.cartera_temp.Exceptions.DateFormatException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Functions {
    
    
    public static String quitarCaracteresEspeciales(String cadena){
        String caracteres = "[\\.\\'\\p{P}]";
        
        String cleanInput = cadena.replaceAll( caracteres, "");
        
        String cleanInput2 = cleanInput.replaceAll("\\s+"," ");
        return cleanInput2;
    }
    
    
    public static Date stringToDate(String fecha) throws ParseException {
        try {

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date date = format.parse(fecha);

            SimpleDateFormat formatSalida = new SimpleDateFormat("yyyy-MM-dd");
            String fechafor = formatSalida.format(date);

            Date fechaOk = formatSalida.parse(fechafor);

            return fechaOk;
        } catch (ParseException e) {
            throw new DateFormatException("Error al formatear la fecha: " + e.getMessage(), e.getErrorOffset());

        }

    }
    
    public static String containsOnlyNumbers(String input) {
        String regex = "^[0-9]+$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            return input;
        } else {
            throw new NumberFormatException("EL NUMERO DE  ".concat(" : ").concat(input).concat(" TIENE UN FORMATO INCORRECTO.  POR FAVOR INGRESE SOLO NUMEROS  "));
        }
    }

}
