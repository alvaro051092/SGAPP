package uy.edu.ctc.sgapp.web_service.Servicios;

/**
 * Created by alvar on 02/08/2017.
 */

public class WS_Persona {

    public static final Servicio servicio                       = new Servicio("http://192.168.0.100:8084/GestionAcademica/ws_persona", "http://WebService/");
    public static final Servicio.Metodo ObtenerPersonaByUser    = new Servicio.Metodo("ObtenerPersonaByUser", "http://WebService/ws_persona/ObtenerPersonaByUserRequest");
    public static final Servicio.Metodo ObtenerPersonaByCod    = new Servicio.Metodo("ObtenerPersonaByCod", "http://WebService/ws_persona/ObtenerPersonaByCodRequest");
    public static final Servicio.Metodo PersonaActualizarToken  = new Servicio.Metodo("PersonaActualizarToken", "http://WebService/ws_persona/PersonaActualizarTokenRequest");


    WS_Persona(){

    }


}
