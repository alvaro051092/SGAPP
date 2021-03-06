package uy.edu.ctc.sgapp.web_service.Servicios;

import uy.edu.ctc.sgapp.enumerado.Constantes;

/**
 * Created by aa on 20-ago-17.
 */

public class WS_Solicitud {

    public static final Servicio servicio                       = new Servicio(Constantes.URL_WS.getValor() + "ws_solicitudes", "http://WebService/");
    public static final Servicio.Metodo RealizarSolicitud       = new Servicio.Metodo("realizarSolicitud", "http://WebService/ws_solicitudes/realizarSolicitudRequest");
    public static final Servicio.Metodo SolicitudesFinalizadas  = new Servicio.Metodo("lstSolicitudesFinalizadas", "http://WebService/ws_solicitudes/lstSolicitudesFinalizadasRequest");
    public static final Servicio.Metodo SolicitudesActivas      = new Servicio.Metodo("lstSolicitudesActivas", "http://WebService/ws_solicitudes/lstSolicitudesActivasRequest");

    WS_Solicitud() {

    }
}
