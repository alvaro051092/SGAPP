package uy.edu.ctc.sgapp.web_service;

import android.os.AsyncTask;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import uy.edu.ctc.sgapp.entidad.Persona;
import uy.edu.ctc.sgapp.enumerado.PersonaServicioMetodo;
import uy.edu.ctc.sgapp.enumerado.ServicioWeb;
import uy.edu.ctc.sgapp.enumerado.TipoMensaje;
import uy.edu.ctc.sgapp.logica.Seguridad;
import uy.edu.ctc.sgapp.user_interface.login;
import uy.edu.ctc.sgapp.utiles.Mensajes;
import uy.edu.ctc.sgapp.utiles.Retorno_MsgObj;
import uy.edu.ctc.sgapp.web_service.Servicios.WS_Login;
import uy.edu.ctc.sgapp.web_service.Servicios.WS_Persona;

/**
 * Created by alvar on 01/08/2017.
 */

public class ws_persona extends AsyncTask<String,Integer,Boolean> {
    private Retorno_MsgObj retorno;
    private Object clase;
    private PersonaServicioMetodo metodo;
    private Retorno_MsgObj parametro;
    private String metodoToCall;

    public ws_persona(Object pClase, PersonaServicioMetodo pMetodo, Retorno_MsgObj pParametro) {
        this.clase = pClase;
        this.metodo = pMetodo;
        this.parametro = pParametro;
    }

    protected Boolean doInBackground(String... params) {

        boolean resul = true;

        switch(this.metodo)
        {
            case LOGIN: resul = this.Login();
                metodoToCall = "RetornoLogin";
                break;
            case LOGOUT: resul = this.Logout();
                metodoToCall = "RetornoLogout";
                break;
            case GET_PERSONA_USR: resul = this.ObtenerPersonaByUser();
                metodoToCall = "RetornoGetPersona";
                break;
            case GET_PERSONA_COD: resul = this.ObtenerPersonaByCod();
                metodoToCall = "RetornoGetPersona";
                break;
            case UPD_TOKEN: resul = this.ActualizarToken();
                metodoToCall = null;
                break;
        }

        return resul;
    }

    protected void onPostExecute(Boolean result) {

        if (result) {

            if(retorno.getMensaje() != null) Log.e("RESULTADO", retorno.getMensaje().getMensaje());
            if(!retorno.SurgioErrorObjetoRequerido()) Log.e("RESULTADO", retorno.getObjeto().toString());

            if(clase != null && metodoToCall != null){


                //login lg = (login) clase;

                //lg.RetornoLogin(retorno);

                //Log.e("WS_PERSONA", "Clase: " + clase.getClass().getSimpleName());

                try {
                    Method metodo = clase.getClass().getDeclaredMethod(metodoToCall, Retorno_MsgObj.class);
                    metodo.setAccessible(true);
                    metodo.invoke(clase, retorno);

                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                //   metodo.invoke(clase.getClass(), retorno);

            }
        }
    }

    private Boolean ObtenerPersonaByCod(){
        Log.e("SERVICIO", "Iniciando servicio");

        retorno = new Retorno_MsgObj(new Mensajes("Error", TipoMensaje.ERROR));

        Persona persona = (Persona) parametro.getObjeto();


        boolean resul = true;

        SoapObject request = new SoapObject(WS_Persona.servicio.NAMESPACE, WS_Persona.ObtenerPersonaByCod.METHOD_NAME);

        SoapSerializationEnvelope envelope =
                new SoapSerializationEnvelope(SoapEnvelope.VER11);
        //envelope.dotNet = true;

        request.addProperty("token", "token");
        request.addProperty("pPerCod", persona.getPerCod());

        envelope.setOutputSoapObject(request);

        HttpTransportSE transporte = new HttpTransportSE(WS_Persona.servicio.URL);

        try
        {
            transporte.call(WS_Persona.ObtenerPersonaByCod.SOAP_ACTION, envelope);

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

    private Boolean ObtenerPersonaByUser(){
        Log.e("SERVICIO", "Iniciando servicio");

        retorno = new Retorno_MsgObj(new Mensajes("Error", TipoMensaje.ERROR));
        Persona persona = (Persona) parametro.getObjeto();


        boolean resul = true;

        SoapObject request = new SoapObject(WS_Persona.servicio.NAMESPACE, WS_Persona.ObtenerPersonaByUser.METHOD_NAME);

        SoapSerializationEnvelope envelope =
                new SoapSerializationEnvelope(SoapEnvelope.VER11);
        //envelope.dotNet = true;

        Log.e("USUARIO", persona.getPerUsrMod());

        request.addProperty("token", "token");
        request.addProperty("pUser", persona.getPerUsrMod());

        envelope.setOutputSoapObject(request);

        HttpTransportSE transporte = new HttpTransportSE(WS_Persona.servicio.URL);

        try
        {
            transporte.call(WS_Persona.ObtenerPersonaByUser.SOAP_ACTION, envelope);

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

    private Boolean ActualizarToken(){
        Log.e("SERVICIO", "Iniciando servicio");

        retorno = new Retorno_MsgObj(new Mensajes("Error", TipoMensaje.ERROR));
        Persona persona = (Persona) parametro.getObjeto();


        boolean resul = true;

        SoapObject request = new SoapObject(WS_Persona.servicio.NAMESPACE, WS_Persona.PersonaActualizarToken.METHOD_NAME);

        SoapSerializationEnvelope envelope =
                new SoapSerializationEnvelope(SoapEnvelope.VER11);
        //envelope.dotNet = true;

        request.addProperty("token", "token");
        request.addProperty("pPerCod", persona.getPerCod());
        request.addProperty("pPerAppTkn", persona.getPerAppTkn());

        Log.e("TOKEN", persona.getPerAppTkn());

        envelope.setOutputSoapObject(request);

        HttpTransportSE transporte = new HttpTransportSE(WS_Persona.servicio.URL);

        try
        {
            transporte.call(WS_Persona.PersonaActualizarToken.SOAP_ACTION, envelope);

            SoapObject resSoapObj =(SoapObject) envelope.getResponse();


            for (int i = 0; i < resSoapObj.getPropertyCount(); i++)
            {
                SoapObject ic = (SoapObject) resSoapObj.getProperty(i);

                PropertyInfo pi_ = new PropertyInfo();
                resSoapObj.getPropertyInfo(i, pi_);

                String objetoPadre = pi_.name;

                for (int f = 0; f < ic.getPropertyCount(); f++) {

                    PropertyInfo pi = new PropertyInfo();
                    ic.getPropertyInfo(f, pi);

                    Log.e("RESULTADO", pi.name + " : " + ic.getProperty(f).toString());

                    if(objetoPadre.equals("mensaje")) retorno.setField(pi.name, ic.getProperty(f).toString());

                }
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            resul = false;
        }

        return resul;
    }

    private Boolean Login(){
        Log.e("SERVICIO", "Iniciando servicio");

        boolean resul   = true;
        retorno         = new Retorno_MsgObj(new Mensajes("Error", TipoMensaje.ERROR));

        Persona persona = (Persona) parametro.getObjeto();


        SoapObject request = new SoapObject(WS_Login.servicio.NAMESPACE, WS_Login.Login.METHOD_NAME);

        SoapSerializationEnvelope envelope =
                new SoapSerializationEnvelope(SoapEnvelope.VER11);

        String usuario = "";
        String password = "";

        try {
            usuario = Seguridad.GetInstancia().crypt(persona.getPerUsrMod()).toString();
            password = Seguridad.GetInstancia().crypt(persona.getPerPass()).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.e("Encriptado", usuario);

        Log.e("RESULTADO", persona.getPerUsrMod());
        Log.e("RESULTADO", persona.getPerPass());

        request.addProperty("token", Seguridad.GetInstancia().getTokenWS(ServicioWeb.LOGIN));
        request.addProperty("pUser", usuario);
        request.addProperty("pPassword", password);

        envelope.setOutputSoapObject(request);

        HttpTransportSE transporte = new HttpTransportSE(WS_Login.servicio.URL);

        try
        {
            transporte.call(WS_Login.Login.SOAP_ACTION, envelope);

            SoapPrimitive resSoap =(SoapPrimitive) envelope.getResponse();

            Log.e("RESULTADO", "Resultado: " + resSoap.toString());

            Boolean loginOk = Boolean.valueOf(resSoap.toString());

            if(loginOk)
            {
                retorno.setMensaje(new Mensajes("Resultado login: Ok", TipoMensaje.MENSAJE));
            }
            else
            {
                retorno.setMensaje(new Mensajes("Usuario o contraseña incorrectos", TipoMensaje.ERROR));
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            resul = false;
        }

        return resul;
    }

    private Boolean Logout(){
        Log.e("SERVICIO", "Iniciando servicio");

        boolean resul   = true;
        retorno         = new Retorno_MsgObj(new Mensajes("Error", TipoMensaje.ERROR));

        Persona persona = (Persona) parametro.getObjeto();


        SoapObject request = new SoapObject(WS_Login.servicio.NAMESPACE, WS_Login.Logout.METHOD_NAME);

        SoapSerializationEnvelope envelope =
                new SoapSerializationEnvelope(SoapEnvelope.VER11);

        String PerCod = "";

        try {
            PerCod = Seguridad.GetInstancia().crypt(persona.getPerCod().toString()).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.addProperty("token", Seguridad.GetInstancia().getTokenWS(ServicioWeb.LOGIN));
        request.addProperty("pPerCod", PerCod);

        envelope.setOutputSoapObject(request);

        HttpTransportSE transporte = new HttpTransportSE(WS_Login.servicio.URL);

        try
        {
            transporte.call(WS_Login.Logout.SOAP_ACTION, envelope);

            SoapObject resSoapObj =(SoapObject) envelope.getResponse();


            for (int i = 0; i < resSoapObj.getPropertyCount(); i++)
            {
                SoapObject ic = (SoapObject) resSoapObj.getProperty(i);

                PropertyInfo pi_ = new PropertyInfo();
                resSoapObj.getPropertyInfo(i, pi_);

                String objetoPadre = pi_.name;

                for (int f = 0; f < ic.getPropertyCount(); f++) {

                    PropertyInfo pi = new PropertyInfo();
                    ic.getPropertyInfo(f, pi);

                    Log.e("RESULTADO", pi.name + " : " + ic.getProperty(f).toString());

                    if(objetoPadre.equals("mensaje")) retorno.setField(pi.name, ic.getProperty(f).toString());

                }
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            resul = false;
        }

        return resul;
    }
}