package uy.edu.ctc.sgapp.web_service.Servicios;

/**
 * Created by aa on 20-ago-17.
 */

public class WS_Solicitud {

    public static final Servicio servicio                       = new Servicio("http://www.alvarodevotto.com/ws_solicitudes", "http://WebService/");
    public static final Servicio.Metodo RealizarSolicitud       = new Servicio.Metodo("realizarSolicitud", "http://WebService/ws_solicitudes/realizarSolicitudRequest");
    public static final Servicio.Metodo SolicitudesFinalizadas  = new Servicio.Metodo("lstSolicitudesFinalizadas", "http://WebService/ws_solicitudes/lstSolicitudesFinalizadasRequest");
    public static final Servicio.Metodo SolicitudesActivas      = new Servicio.Metodo("lstSolicitudesActivas", "http://WebService/ws_solicitudes/lstSolicitudesActivasRequest");

    WS_Solicitud() {

    }
}
