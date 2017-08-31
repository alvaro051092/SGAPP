package uy.edu.ctc.sgapp.web_service;

import android.os.AsyncTask;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import uy.edu.ctc.sgapp.SDT.SDT_PersonaEstudio;
import uy.edu.ctc.sgapp.entidad.Escolaridad;
import uy.edu.ctc.sgapp.entidad.Persona;
import uy.edu.ctc.sgapp.entidad.Solicitud;
import uy.edu.ctc.sgapp.enumerado.PersonaServicioMetodo;
import uy.edu.ctc.sgapp.enumerado.TipoMensaje;
import uy.edu.ctc.sgapp.enumerado.TipoSolicitud;
import uy.edu.ctc.sgapp.utiles.Mensajes;
import uy.edu.ctc.sgapp.utiles.Retorno_MsgObj;
import uy.edu.ctc.sgapp.web_service.Servicios.WS_Solicitud;

/**
 * Created by aa on 20-ago-17.
 */

public class ws_solicitudes extends AsyncTask<String,Integer,Boolean> {
    private Retorno_MsgObj retorno;
    private Retorno_MsgObj parametro;
    private Object clase;
    private PersonaServicioMetodo metodo;
    private String metodoToCall;

    public ws_solicitudes(Object clase, PersonaServicioMetodo metodo, Retorno_MsgObj parametro) {
        this.clase = clase;
        this.metodo = metodo;
        this.parametro = parametro;
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        boolean resul = true;

        switch(this.metodo)
        {
            case ING_SOLICITUD:
                resul = this.ingresarSolicitud();
                metodoToCall = "retornoSolicitud";
                break;
            case GET_SOLICITUDESFINALIZADAS:
                resul = this.lstSolicitudesFinalizadas();
                metodoToCall = "retornoSolicitudesFinalizadas";
                break;
            case GET_SOLICITUDESACTIVAS:
                resul = this.lstSolicitudesActivas();
                metodoToCall = "retornoSolicitudesActivas";
                break;
        }
        return resul;
    }

    protected void onPostExecute(Boolean result)
    {
        if (result)
        {
            if(retorno.getMensaje().getTipoMensaje() != null) Log.e("RESULTADO: ", retorno.getMensaje().getMensaje());
            if(!retorno.SurgioErrorListaRequerida()) Log.e("RESULTADO: ", retorno.getLstObjetos().toString());

            if(clase != null && metodoToCall != null)
            {
                try
                {
                    Method metodo = clase.getClass().getDeclaredMethod(metodoToCall, Retorno_MsgObj.class);
                    metodo.setAccessible(true);
                    metodo.invoke(clase, retorno);
                }
                catch(NoSuchMethodException e)
                {
                    e.printStackTrace();
                }
                catch(InvocationTargetException e)
                {
                    e.printStackTrace();
                }
                catch(IllegalAccessException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean ingresarSolicitud()
        {
        boolean resul                       = true;

        retorno = new Retorno_MsgObj(new Mensajes("Error", TipoMensaje.ERROR));

        SoapObject request = new SoapObject(WS_Solicitud.servicio.NAMESPACE, WS_Solicitud.RealizarSolicitud.METHOD_NAME);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        Solicitud sol = (Solicitud) parametro.getObjeto();

        long PerCod  = sol.getAlumno().getPerCod();
        TipoSolicitud TpoSol = sol.getSolTpo();

        request.addProperty("token", "pedritoelescamoso");
        request.addProperty("AluPerCod", PerCod);
        request.addProperty("SolTpo", TpoSol.getTipoSolicitud());

        envelope.setOutputSoapObject(request);

        HttpTransportSE transporte = new HttpTransportSE(WS_Solicitud.servicio.URL);

        try {

            transporte.call(WS_Solicitud.RealizarSolicitud.SOAP_ACTION, envelope);

            SoapObject resSoapObj =(SoapObject) envelope.getResponse();

            for (int i = 0; i < resSoapObj.getPropertyCount(); i++) {
                SoapObject ic_ = (SoapObject) resSoapObj.getProperty(i);

                PropertyInfo pi_ = new PropertyInfo();
                resSoapObj.getPropertyInfo(i, pi_);

                String objetoPadre = pi_.name;

                for (int f = 0; f < ic_.getPropertyCount(); f++) {
                    PropertyInfo pi = new PropertyInfo();
                    ic_.getPropertyInfo(f, pi);

//                    Log.e("Pi.NAME F: ", objetoPadre_cal.toString());
//                    Log.e("IC F: ", ic_cal.getProperty(f).toString());

                    if (objetoPadre.equals("mensaje")) retorno.setField(pi.name, ic_.getProperty(f).toString());
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            resul = false;
        }

        return resul;
    }

    public boolean lstSolicitudesFinalizadas()
    {
        boolean resul   = true;
        return resul;
    }

    public boolean lstSolicitudesActivas()
    {
        boolean resul       = true;
        Solicitud solicitud;
        List<Object> lstObj = new ArrayList<>();

        retorno = new Retorno_MsgObj(new Mensajes("Error", TipoMensaje.ERROR));

        SoapObject request = new SoapObject(WS_Solicitud.servicio.NAMESPACE, WS_Solicitud.SolicitudesActivas.METHOD_NAME);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        Solicitud sol = (Solicitud) parametro.getObjeto();

        long PerCod  = sol.getAlumno().getPerCod();

//        System.out.println("PERCOD: " + PerCod);

        request.addProperty("token", "pedritoelescamoso");
        request.addProperty("PerCod", PerCod);

        envelope.setOutputSoapObject(request);

        HttpTransportSE transporte = new HttpTransportSE(WS_Solicitud.servicio.URL);

        try
        {
            transporte.call(WS_Solicitud.SolicitudesActivas.SOAP_ACTION, envelope);

            SoapObject resSoapObj =(SoapObject) envelope.getResponse();

            for (int i = 0; i < resSoapObj.getPropertyCount(); i++) {
                SoapObject ic_ = (SoapObject) resSoapObj.getProperty(i);

                PropertyInfo pi_ = new PropertyInfo();
                resSoapObj.getPropertyInfo(i, pi_);

                String objetoPadre = pi_.name;

                Log.e("PI>NAME I: ", pi_.name.toString());
//                Log.e("IC I: ", ic_.getProperty(i).toString());
                solicitud = new Solicitud();

                for (int f = 0; f < ic_.getPropertyCount(); f++) {
                    PropertyInfo pi = new PropertyInfo();
                    ic_.getPropertyInfo(f, pi);

                    Log.e("Pi.NAME F: ", pi.name.toString());
                    Log.e("IC F: ", ic_.getProperty(f).toString());

                    if (objetoPadre.equals("mensaje")) retorno.setField(pi.name, ic_.getProperty(f).toString());

                    if (objetoPadre.equals("lstObjetos")) solicitud.setField(pi.name, ic_.getProperty(f).toString());
                }
                if (solicitud.getSolCod() != null) lstObj.add(solicitud);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            resul = false;
        }
        retorno.setLstObjetos(lstObj);

        return resul;
    }
}
