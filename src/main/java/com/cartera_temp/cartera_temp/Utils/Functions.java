package com.cartera_temp.cartera_temp.Utils;

import com.cartera_temp.cartera_temp.Exceptions.DateFormatException;
import static com.cartera_temp.cartera_temp.Utils.Functions.fechaDateToStringSinHora;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
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

    public static String dateToString(Date fecha) throws ParseException {
        try {
            SimpleDateFormat formatEntrada = new SimpleDateFormat("yyyy-MM-dd");
            String fechaEntrada = formatEntrada.format(fecha);

            Date fechaOk = formatEntrada.parse(fechaEntrada);

            SimpleDateFormat formatSalida = new SimpleDateFormat("dd/MM/yyyy");
            String fechaFormateada = formatSalida.format(fechaOk);

            return fechaFormateada;
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
        // Obtener la fecha y hora actual en milisegundos
        long tiempoActual = System.currentTimeMillis();

        // Crear un objeto de fecha y hora usando el tiempo actual
        Date fecha = new Date(tiempoActual);

        // Configurar el formato de la fecha y la zona horaria a GMT-5 (Bogotá)
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Formatear la fecha y la hora con la zona horaria de Bogotá
        String fechaTexto = formatter.format(fecha);

        // Convertir el texto formateado nuevamente a un objeto de fecha y hora
        Date fechaFormateada = formatter.parse(fechaTexto);

        // Devolver la fecha y hora formateada
        return fechaFormateada;

    }

    public static String formatearFecha(Date fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("dd MMMM 'de' yyyy");
        return formato.format(fecha);
    }

    public static String obtenerTextoFechaConvenio() {
        try {
            return "El presente convenio se establece a los " + fechaFormatToLetrasAcuerdo() + " en la ciudad de Medellín.";
        } catch (Exception e) {
            e.printStackTrace();
            return ""; // Puedes ajustar esto según tus necesidades
        }
    }

    public static String fechaFormatToLetrasAcuerdo() {
        try {
            String[] fechaToFormat = fechaDateToStringSinHora().split("-");
            String ToLetrasAcuerdo = fechaToFormat[2].concat(" días del mes ").concat(fechaToFormat[1]).concat(" del año ").concat(fechaToFormat[0]);
            return ToLetrasAcuerdo;
        } catch (Exception e) {
            e.printStackTrace();
            return ""; // Puedes ajustar esto según tus necesidades
        }
    }

    public static Date stringToDateAndFormat(String fecha) throws ParseException {
        try {

            SimpleDateFormat formatSalida = new SimpleDateFormat("yyyy-MM-dd");

            Date fechaOk = formatSalida.parse(fecha);

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

    public static Date fechaDateToStringNormal(String fecha) throws ParseException {

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date fechaOk = formato.parse(fecha);

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

    public static int diferenciaFechas(Date fechaVencimiento) {
        Date now = new Date();
        long diferenciaMili = now.getTime() - fechaVencimiento.getTime();

        long dias = TimeUnit.DAYS.convert(diferenciaMili, TimeUnit.MILLISECONDS);
        return (int) dias;
    }

    public static Date obtenerFechaInicalMes(int dia) {

        // Obtener la fecha actual
        Date fechaActual = new Date();

        // Crear un objeto Calendar y establecer la fecha actual
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);

        // Establecer el día del mes en 1
        calendar.set(Calendar.DAY_OF_MONTH, dia);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        // Obtener la fecha del primer día del mes actual
        Date primerDiaDelMes = calendar.getTime();
        

        return primerDiaDelMes;
    }

    public static Date obtenerFechaInicalDia() {

        // Obtener la fecha actual
        Date fechaActual = new Date();

        // Crear un objeto Calendar y establecer la fecha actual
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);

        // Establecer el día del mes en 1
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        // Obtener la fecha del primer día del mes actual
        Date primerDiaDelMes = calendar.getTime();

        
        return primerDiaDelMes;
    }

    public static boolean validarFechaPertenece(Date fecha) {

        Date fechaActual = new Date();

        // Obtener el primer día del mes actual
        Calendar calInicio = Calendar.getInstance();
        calInicio.setTime(fechaActual);
        calInicio.set(Calendar.DAY_OF_MONTH, 1);
        Date fechaInicioRango = calInicio.getTime();

        // Obtener el último día del mes actual
        Calendar calFin = Calendar.getInstance();
        calFin.setTime(fechaActual);
        calFin.set(Calendar.DAY_OF_MONTH, calFin.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date fechaFinRango = calFin.getTime();

        boolean estaEnRango = fecha.after(fechaInicioRango) && fecha.before(fechaFinRango)
                || fecha.equals(fechaInicioRango) || fecha.equals(fechaFinRango);

        return false;
    }

    public static Date obtenerFechaInicialFinalMes(boolean obtenerFechaInicial, String mesOrDay) {
        Date fecha = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);

        switch (mesOrDay) {
            case "MES":
                // Si el indicador es verdadero, obtener la fecha inicial del mes
                if (obtenerFechaInicial) {
                    cal.set(Calendar.DAY_OF_MONTH, 1); // Establecer el día del mes como 1
                    cal.set(Calendar.HOUR_OF_DAY, 0); // Establecer las horas a 0
                    cal.set(Calendar.MINUTE, 0); // Establecer los minutos a 0
                    cal.set(Calendar.SECOND, 0); // Establecer los segundos a 0
                    cal.set(Calendar.MILLISECOND, 0); // Establecer los milisegundos a 0
                } else { // Si el indicador es falso, obtener la fecha final del mes
                    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); // Establecer el día del mes como el último día del mes
                    cal.set(Calendar.HOUR_OF_DAY, 23); // Establecer las horas a 23
                    cal.set(Calendar.MINUTE, 59); // Establecer los minutos a 59
                    cal.set(Calendar.SECOND, 59); // Establecer los segundos a 59
                    cal.set(Calendar.MILLISECOND, 999); // Establecer los milisegundos a 999
                }
                break;
            case "DIA":
                // Si el indicador es verdadero, obtener la fecha inicial del mes
                if (obtenerFechaInicial) {
                    cal.set(Calendar.HOUR_OF_DAY, 0); // Establecer las horas a 0
                    cal.set(Calendar.MINUTE, 0); // Establecer los minutos a 0
                    cal.set(Calendar.SECOND, 0); // Establecer los segundos a 0
                    cal.set(Calendar.MILLISECOND, 0); // Establecer los milisegundos a 0
                } else { // Si el indicador es falso, obtener el final del día
                    cal.set(Calendar.HOUR_OF_DAY, 23); // Establecer las horas a 23
                    cal.set(Calendar.MINUTE, 59); // Establecer los minutos a 59
                    cal.set(Calendar.SECOND, 59); // Establecer los segundos a 59
                    cal.set(Calendar.MILLISECOND, 999); // Establecer los milisegundos a 999
                }

        }
        return cal.getTime(); // Devolver la fecha resultante
    }
}
