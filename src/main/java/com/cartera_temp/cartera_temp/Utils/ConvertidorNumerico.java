package com.cartera_temp.cartera_temp.Utils;

public class ConvertidorNumerico {

    private static final String[] UNIDADES = {"", "UN", "DOS", "TRES", "CUATRO", "CINCO", "SEIS", "SIETE", "OCHO", "NUEVE"};
    private static final String[] DECENAS = {"", "DIEZ", "VEINTE", "TREINTA", "CUARENTA", "CINCUENTA", "SESENTA", "SETENTA", "OCHENTA", "NOVENTA"};
    private static final String[] DIEZ_A_DIECINUEVE = {"DIEZ", "ONCE", "DOCE", "TRECE", "CATORCE", "QUINCE", "DIECISEIS", "DIECISIETE", "DIECIOCHO", "DIECINUEVE"};
    private static final String[] CENTENAS = {"", "CIENTO", "DOSCIENTOS", "TRESCIENTOS", "CUATROCIENTOS", "QUINIENTOS", "SEISCIENTOS", "SETECIENTOS", "OCHOCIENTOS", "NOVECIENTOS"};

    private static final String[] GRANDES_UNIDADES = {"", "MIL", "MILLON", "MILLONES", "BILLON", "BILLONES"};

    public static String convertirNumeroEnPalabras(long numero) {
        if (numero < 0) {
            return "Número no soportado";
        }

        if (numero == 0) {
            return "CERO";
        }

        String resultado = "";
        int indiceGrande = 0;

        while (numero > 0) {
            long bloque = numero % 1_000_000_000;

            if (bloque != 0) {
                resultado = convertirBloqueEnPalabras(bloque) + " " + GRANDES_UNIDADES[indiceGrande] + " " + resultado;
            }

            numero /= 1_000_000_000;
            indiceGrande += 2;
        }

        return resultado.trim();
    }

    private static String convertirBloqueEnPalabras(long bloque) {
        String resultado = "";

        if ((bloque / 1_000_000) > 0) {
            resultado += convertirUnidades((int) (bloque / 1_000_000)) + " MILLONES ";
            bloque %= 1_000_000;
        }

        resultado += convertirUnidades((int) bloque);

        return resultado.trim();
    }

    private static String convertirUnidades(int numero) {
        if ((numero >= 1) && (numero <= 9)) {
            return UNIDADES[numero];
        } else if ((numero >= 10) && (numero <= 19)) {
            return DIEZ_A_DIECINUEVE[numero - 10];
        } else if ((numero >= 20) && (numero <= 99)) {
            return DECENAS[numero / 10] + ((numero % 10 != 0) ? " Y " + UNIDADES[numero % 10] : "");
        } else if ((numero >= 100) && (numero <= 999)) {
            return CENTENAS[numero / 100] + ((numero % 100 != 0) ? " " + convertirUnidades(numero % 100) : "");
        }

        return "";
    }
}
