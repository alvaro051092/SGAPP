package uy.edu.ctc.sgapp.web_service.Servicios;

/**
 * Created by aa on 20-ago-17.
 */

public class WS_Estudios {

    public static final Servicio servicio                   = new Servicio("http://192.168.1.2:8084/GestionAcademica/ws_estudios", "http://WebService/");
    public static final Servicio.Metodo Estudios            = new Servicio.Metodo("lstEstudiosPorAlumno", "http://WebService/ws_estudios/lstEstudiosPorAlumnoRequest");
    public static final Servicio.Metodo EstudiosPrevios     = new Servicio.Metodo("lstEstudiosPreviosPorAlumno", "http://WebService/ws_estudios/lstEstudiosPreviosPorAlumnoRequest");
//    public static final Servicio.Metodo Escolaridad         = new Servicio.Metodo("escolaridadAlumno", "http://WebService/ws_estudios/escolaridadAlumnoRequest");

    WS_Estudios() {

    }
}
