package uy.edu.ctc.sgapp.web_service.Servicios;

/**
 * Created by alvar on 02/08/2017.
 */

public class Servicio {

    public String URL;
    public String NAMESPACE;

    public Servicio(String URL, String NAMESPACE) {
        this.URL = URL;
        this.NAMESPACE = NAMESPACE;
    }

    public static class Metodo {
        public String METHOD_NAME;
        public String SOAP_ACTION;

        public Metodo(String METHOD_NAME, String SOAP_ACTION) {
            this.METHOD_NAME = METHOD_NAME;
            this.SOAP_ACTION = SOAP_ACTION;
        }


    }
}
