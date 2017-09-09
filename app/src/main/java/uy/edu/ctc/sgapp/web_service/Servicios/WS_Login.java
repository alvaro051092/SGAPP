package uy.edu.ctc.sgapp.web_service.Servicios;

import uy.edu.ctc.sgapp.enumerado.Constantes;

/**
 * Created by alvar on 02/08/2017.
 */

public class WS_Login {

    public static final Servicio servicio      = new Servicio(Constantes.URL_WS.getValor() + "ws_login", "http://WebService/");
    public static final Servicio.Metodo Login  = new Servicio.Metodo("Login", "http://WebService/ws_login/LoginRequest");
    public static final Servicio.Metodo Logout = new Servicio.Metodo("Logout", "http://WebService/ws_login/LogoutRequest");


    WS_Login(){

    }


}
