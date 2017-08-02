package uy.edu.ctc.sgapp.web_service;

import android.os.AsyncTask;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import uy.edu.ctc.sgapp.entidad.Persona;
import uy.edu.ctc.sgapp.enumerado.TipoMensaje;
import uy.edu.ctc.sgapp.logica.Seguridad;
import uy.edu.ctc.sgapp.user_interface.Alvaro;
import uy.edu.ctc.sgapp.user_interface.MainActivity;
import uy.edu.ctc.sgapp.utiles.Mensajes;
import uy.edu.ctc.sgapp.utiles.Retorno_MsgObj;
import uy.edu.ctc.sgapp.utiles.Utilidades;

/**
 * Created by alvar on 01/08/2017.
 */

public class ws_persona extends AsyncTask<String,Integer,Boolean> {
    private Retorno_MsgObj retorno;
    private Alvaro alvaro;

    public ws_persona(Alvaro mAct) {
        this.alvaro = mAct;
    }

    protected Boolean doInBackground(String... params) {

    Log.e("SERVICIO", "Iniciando servicio");

    retorno = new Retorno_MsgObj(new Mensajes("Error", TipoMensaje.ERROR));
        Persona persona = new Persona();


        boolean resul = true;

    final String NAMESPACE = "http://WebService/";
    final String URL="http://192.168.0.100:8084/GestionAcademica/ws_persona";
    final String METHOD_NAME = "ObtenerPersonaByUser";
    final String SOAP_ACTION = "http://WebService/ws_persona/ObtenerPersonaByUserRequest";

    SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

    SoapSerializationEnvelope envelope =
            new SoapSerializationEnvelope(SoapEnvelope.VER11);
    //envelope.dotNet = true;

    request.addProperty("token", "token");
    request.addProperty("pUser", "alumno1");

    envelope.setOutputSoapObject(request);

    HttpTransportSE transporte = new HttpTransportSE(URL);

    try
    {
        transporte.call(SOAP_ACTION, envelope);

        //SoapPrimitive resSoap =(SoapPrimitive)envelope.getResponse();

        SoapObject resSoapObj =(SoapObject) envelope.getResponse();


        for (int i = 0; i < resSoapObj.getPropertyCount(); i++)
        {
            SoapObject ic = (SoapObject) resSoapObj.getProperty(i);

            PropertyInfo pi_ = new PropertyInfo();
            resSoapObj.getPropertyInfo(i, pi_);
           // Log.e("RESULTADO_", pi_.name + " : " + resSoapObj.getProperty(i).toString());

            String objetoPadre = pi_.name;

            for (int f = 0; f < ic.getPropertyCount(); f++) {

                PropertyInfo pi = new PropertyInfo();
                ic.getPropertyInfo(f, pi);

             //   Log.e("RESULTADO", pi.name + " : " + ic.getProperty(f).toString());

                if(objetoPadre.equals("mensaje")) retorno.setField(pi.name, ic.getProperty(f).toString());

                if(objetoPadre.equals("objeto")) persona.setField(pi.name, ic.getProperty(f).toString());

            }
        }

    }
    catch (Exception e)
    {
        e.printStackTrace();
        resul = false;
    }

    retorno.setObjeto(persona);

    return resul;
}

    protected void onPostExecute(Boolean result) {

        if (result) {

            Log.e("RESULTADO", retorno.getObjeto().toString());
            Log.e("RESULTADO", retorno.getMensaje().getMensaje());

            alvaro.MiFuncion(retorno);
        }
            /*
            //Rellenamos la lista con los nombres de los clientes
            final String[] datos = new String[listaClientes.length];

            for(int i=0; i<listaClientes.length; i++)
                datos[i] = listaClientes[i].nombre;

            ArrayAdapter<String> adaptador =
                    new ArrayAdapter<String>(MainActivity.this,
                            android.R.layout.simple_list_item_1, datos);

            lstClientes.setAdapter(adaptador);
        }
        else
        {
            txtResultado.setText("Error!");
        }
        */
        }


}