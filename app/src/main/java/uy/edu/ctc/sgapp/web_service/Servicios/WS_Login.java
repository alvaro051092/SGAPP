package uy.edu.ctc.sgapp.web_service.Servicios;

/**
 * Created by alvar on 02/08/2017.
 */

public class WS_Login {

    public static final Servicio servicio      = new Servicio("http://www.alvarodevotto.com/ws_login", "http://WebService/");
    public static final Servicio.Metodo Login  = new Servicio.Metodo("Login", "http://WebService/ws_login/LoginRequest");
    public static final Servicio.Metodo Logout = new Servicio.Metodo("Logout", "http://WebService/ws_login/LogoutRequest");


    WS_Login(){

    }


}
