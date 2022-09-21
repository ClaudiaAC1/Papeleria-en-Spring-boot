package com.papeleria.v1.papeleria_v1.Entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FechaYhora {
    public static String obtenerFechaYHoraActual() {
        String formato = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern(formato);
        LocalDateTime ahora = LocalDateTime.now();
        return formateador.format(ahora);
    }
}
