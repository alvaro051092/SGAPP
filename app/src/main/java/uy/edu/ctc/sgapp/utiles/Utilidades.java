/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ctc.sgapp.utiles;

import android.util.Log;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author alvar
 */
public class Utilidades {
    private static Utilidades instancia;


    private Utilidades() {

    }

    public static Utilidades GetInstancia() {
        if (instancia == null) {
            instancia = new Utilidades();
        }

        return instancia;
    }

    public void MostrarMensajeConsola(String TAG, String Msg) {
        Log.e(TAG, Msg);
    }

    public Date GetFecha(String fecha){
        Log.e("UTILIDAD", fecha);

        SimpleDateFormat httpHeaderDateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US);
        Date date = null;
        if (fecha != null && fecha.length() > 0) {
            Log.e("UTILDIAD", "a");
            try {
                Log.e("UTILDIAD", "b");
                date = httpHeaderDateFormatter.parse(fecha);
            } catch (ParseException e) {
                // otherwise we just leave it empty
            }
        }
        return date;
    }

    public void prueba(){

    }

    public Object JsonToObject(String jsonValue, Object unObj) {

        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            // convert user object to json string and return it
            return mapper.readValue(jsonValue, unObj.getClass());
        } // catch various errors
        catch (JsonGenerationException e) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException ex) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
