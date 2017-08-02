package uy.edu.ctc.sgapp.web_service;

import android.os.AsyncTask;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import uy.edu.ctc.sgapp.logica.Seguridad;

/**
 * Created by alvar on 01/08/2017.
 */

public class ws_login extends AsyncTask<String,Integer,Boolean> {
    //private Cliente[] listaClientes;

    protected Boolean doInBackground(String... params) {

        Log.e("SERVICIO", "Iniciando servicio");

        boolean resul = true;

        final String NAMESPACE = "http://WebService/";
        final String URL="http://192.168.0.100:8084/GestionAcademica/ws_login";
        final String METHOD_NAME = "Login";
        final String SOAP_ACTION = "http://WebService/ws_login/LoginRequest";

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        SoapSerializationEnvelope envelope =
                new SoapSerializationEnvelope(SoapEnvelope.VER11);
        //envelope.dotNet = true;

        String usuario = "";
        String password = "";

        try {
            usuario = Seguridad.GetInstancia().crypt("alumno1").toString();
            password = Seguridad.GetInstancia().crypt("alumno1").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.addProperty("mdltoken", "mdltoken");
        request.addProperty("pUser", usuario);
        request.addProperty("pPassword", password);

        envelope.setOutputSoapObject(request);

        HttpTransportSE transporte = new HttpTransportSE(URL);

        try
        {
            transporte.call(SOAP_ACTION, envelope);

            SoapPrimitive resSoap =(SoapPrimitive)envelope.getResponse();

          //  SoapObject resSoapObj =(SoapObject) envelope.getResponse();

            Log.e("SERVICIO", "d" + resSoap.toString());



            //listaClientes = new Cliente[resSoap.getPropertyCount()];

            for (int i = 0; i < resSoap.getAttributeCount(); i++)
            {
                SoapObject ic = (SoapObject)resSoap.getAttribute(i);

                Log.e("RESULTADO: ",  ic.getProperty(0).toString());

/*
                Cliente cli = new Cliente();
                cli.id = Integer.parseInt(ic.getProperty(0).toString());
                cli.nombre = ic.getProperty(1).toString();
                cli.telefono =
                        Integer.parseInt(ic.getProperty(2).toString());

                listaClientes[i] = cli;
                */
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            resul = false;
        }

        return resul;
    }

    protected void onPostExecute(Boolean result) {
/*
        if (result)
        {
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
