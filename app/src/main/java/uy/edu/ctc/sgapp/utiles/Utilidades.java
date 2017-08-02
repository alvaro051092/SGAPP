/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ctc.sgapp.utiles;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


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

    public Date GetFecha(String fecha)
    {
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


}
