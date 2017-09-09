package uy.edu.ctc.sgapp.web_service.Servicios;

import uy.edu.ctc.sgapp.enumerado.Constantes;

/**
 * Created by aa on 03-ago-17.
 */

public class WS_EvaluacionAlumno {

    public static final Servicio servicio                       = new Servicio(Constantes.URL_WS.getValor() + "ws_EvaluacionAlumno", "http://WebService/");
    public static final Servicio.Metodo EvaluacionesPorAlumno   = new Servicio.Metodo("EvaluacionesParaInscripcion", "http://WebService/ws_EvaluacionAlumno/EvaluacionesParaInscripcionRequests");
    public static final Servicio.Metodo ListaPorAlumno          = new Servicio.Metodo("ListaPorAlumno", "http://WebService/ws_EvaluacionAlumno/EvaluacionesFinalizadasRequest");
    public static final Servicio.Metodo ListaPendiente          = new Servicio.Metodo("ListaPendiente", "http://WebService/ws_EvaluacionAlumno/ListaPendienteRequest");
    public static final Servicio.Metodo InscribirAlumno         = new Servicio.Metodo("InscribirAlumno", "http://WebService/ws_EvaluacionAlumno/InscribirAlumnoRequest");
    public static final Servicio.Metodo DesinscribirAlumno      = new Servicio.Metodo("DesinscribirAlumno", "http://WebService/ws_EvaluacionAlumno/DesinscribirAlumnoRequest");
    //--------------------------------------------------------------------------




    WS_EvaluacionAlumno(){

    }
}
