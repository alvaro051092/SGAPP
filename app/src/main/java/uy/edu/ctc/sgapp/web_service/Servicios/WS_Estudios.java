package uy.edu.ctc.sgapp.web_service.Servicios;

import uy.edu.ctc.sgapp.enumerado.Constantes;

/**
 * Created by aa on 20-ago-17.
 */

public class WS_Estudios {

    public static final Servicio servicio                   = new Servicio(Constantes.URL_WS.getValor() + "ws_estudios", "http://WebService/");
    public static final Servicio.Metodo Estudios            = new Servicio.Metodo("lstEstudiosPorAlumno", "http://WebService/ws_estudios/lstEstudiosPorAlumnoRequest");
    public static final Servicio.Metodo EstudiosPrevios     = new Servicio.Metodo("lstEstudiosPreviosPorAlumno", "http://WebService/ws_estudios/lstEstudiosPreviosPorAlumnoRequest");

    WS_Estudios() {

    }
}
