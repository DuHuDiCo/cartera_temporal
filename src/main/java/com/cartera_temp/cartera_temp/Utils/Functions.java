package com.cartera_temp.cartera_temp.Utils;

import com.cartera_temp.cartera_temp.Exceptions.DateFormatException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Functions {

    public static String quitarCaracteresEspeciales(String cadena) {
        String caracteres = "[\\.\\'\\p{P}]";

        String cleanInput = cadena.replaceAll(caracteres, "");

        String cleanInput2 = cleanInput.replaceAll("\\s+", " ");
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

    public static Date obtenerFechaYhora() throws ParseException {
        Date fecha = new Date(Calendar.getInstance().getTimeInMillis());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT-5"));
        String fechaTexto = formatter.format(fecha);
        Date date = formatter.parse(fechaTexto);
        return date;

    }

    public static Date stringToDateAndFormat(String fecha) throws ParseException {
        try {

            SimpleDateFormat formatSalida = new SimpleDateFormat("yyyy-MM-dd");
            String fechafor = formatSalida.format(fecha);

            Date fechaOk = formatSalida.parse(fechafor);

            return fechaOk;
        } catch (ParseException e) {
            throw new DateFormatException("Error al formatear la fecha: " + e.getMessage(), e.getErrorOffset());

        }
    }

    public static boolean stringToBoolean(String valor) {
        if (valor != null) {
            String lowerCaseValor = valor.toLowerCase().trim();
            return lowerCaseValor.equals("true") || lowerCaseValor.equals("t") || lowerCaseValor.equals("1")
                    || lowerCaseValor.equals("si") || lowerCaseValor.equals("s");
        }
        return false;
    }

    public static Date fechaConHora(String date, String dato) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        switch (dato) {
            case "inicio":
                String fechaInicio = date.concat(" 00:00:00");
                Date fechaHoraInicio = dateFormat.parse(fechaInicio);
                return fechaHoraInicio;

            case "fin":
                String fechaFin = date.concat(" 23:59:59");
                Date fechaHoraFin = dateFormat.parse(fechaFin);
                return fechaHoraFin;

        }
        return null;

    }

    public static Date fechaDateToString(String fecha) throws ParseException {

        SimpleDateFormat formatoInput = new SimpleDateFormat("dd/mm/yyyy HH:mm");
        Date fechaFor = formatoInput.parse(fecha);

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date = formato.format(fechaFor);

        Date fechaOk = formato.parse(date);

        return fechaOk;
    }

    public static String stringDoubleToMoney(double valor) {
        NumberFormat moneda = NumberFormat.getCurrencyInstance();
        String modenaString = moneda.format(valor);
        return modenaString;
    }

    public static String fechaDateToStringSinHora() {
        Date fecha = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaString = formato.format(fecha);
        return fechaString;
    }

}
