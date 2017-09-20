package uy.edu.ctc.sgapp.web_service;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.kobjects.util.Util;
import org.ksoap2.HeaderProperty;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import uy.edu.ctc.sgapp.entidad.Persona;
import uy.edu.ctc.sgapp.entidad.TipoEvaluacion;
import uy.edu.ctc.sgapp.enumerado.PersonaServicioMetodo;
import uy.edu.ctc.sgapp.enumerado.ServicioWeb;
import uy.edu.ctc.sgapp.enumerado.TipoMensaje;
import uy.edu.ctc.sgapp.logica.Seguridad;
import uy.edu.ctc.sgapp.utiles.HttpRequest;
import uy.edu.ctc.sgapp.utiles.Mensajes;
import uy.edu.ctc.sgapp.utiles.Retorno_MsgObj;
import uy.edu.ctc.sgapp.utiles.Utilidades;
import uy.edu.ctc.sgapp.web_service.Servicios.WS_Login;
import uy.edu.ctc.sgapp.web_service.Servicios.WS_Persona;

/**
 * Created by alvar on 01/08/2017.
 */

public class ws_persona_rest extends AsyncTask<String,Integer,Boolean> {
    private Retorno_MsgObj retorno;
    private Object clase;
    private PersonaServicioMetodo metodo;
    private Retorno_MsgObj parametro;
    private String metodoToCall;

    public ws_persona_rest(Object pClase, PersonaServicioMetodo pMetodo, Retorno_MsgObj pParametro) {
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
            //------------------------------------------------------------------------------------------

            List<HeaderProperty> headerList = new ArrayList<HeaderProperty>();
            headerList.add(new HeaderProperty("token", Seguridad.GetInstancia().getTokenWS(ServicioWeb.LOGIN)));

            //------------------------------------------------------------------------------------------

            transporte.call(WS_Persona.ObtenerPersonaByCod.SOAP_ACTION, envelope, headerList);

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

        request.addProperty("pUser", persona.getPerUsrMod());

        envelope.setOutputSoapObject(request);

        HttpTransportSE transporte = new HttpTransportSE(WS_Persona.servicio.URL);

        try
        {
            //------------------------------------------------------------------------------------------

            List<HeaderProperty> headerList = new ArrayList<HeaderProperty>();
            headerList.add(new HeaderProperty("token", Seguridad.GetInstancia().getTokenWS(ServicioWeb.LOGIN)));

            //------------------------------------------------------------------------------------------

            transporte.call(WS_Persona.ObtenerPersonaByUser.SOAP_ACTION, envelope, headerList);

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
            //------------------------------------------------------------------------------------------

            List<HeaderProperty> headerList = new ArrayList<HeaderProperty>();
            headerList.add(new HeaderProperty("token", Seguridad.GetInstancia().getTokenWS(ServicioWeb.LOGIN)));

            //------------------------------------------------------------------------------------------

            transporte.call(WS_Persona.PersonaActualizarToken.SOAP_ACTION, envelope, headerList);

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


        //SoapObject request = new SoapObject(WS_Login.servicio.NAMESPACE, WS_Login.Login.METHOD_NAME);

        //SoapSerializationEnvelope envelope =new SoapSerializationEnvelope(SoapEnvelope.VER11);

        String usuario = "";
        String password = "";

        try {
            usuario = Seguridad.GetInstancia().crypt(persona.getPerUsrMod()).toString();
            password = Seguridad.GetInstancia().crypt(persona.getPerPass()).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }


//        request.addProperty("pUser", usuario);
//       request.addProperty("pPassword", password);

//        envelope.setOutputSoapObject(request);

 //       HttpTransportSE transporte = new HttpTransportSE(WS_Login.servicio.URL);

        try
        {
            //------------------------------------------------------------------------------------------

  //          List<HeaderProperty> headerList = new ArrayList<HeaderProperty>();
   //         headerList.add(new HeaderProperty("token", Seguridad.GetInstancia().getTokenWS(ServicioWeb.LOGIN)));

            //------------------------------------------------------------------------------------------

//            transporte.call(WS_Login.Login.SOAP_ACTION, envelope, headerList);

  //          SoapPrimitive resSoap =(SoapPrimitive) envelope.getResponse();

            //String url "http://192.168.0.100:8084/GestionAcademica/rest/persona/login/"
            //        + URLEncoder.encode(Seguridad.GetInstancia().getTokenWS(ServicioWeb.LOGIN), HttpRequest.CHARSET_UTF8)+"/"
            //        +URLEncoder.encode(usuario, "utf-8")+"/"
            //        +URLEncoder.encode(password, "utf-8");


            String url = Uri.parse("http://192.168.0.100:8084/GestionAcademica/rest/persona/login")
                    .buildUpon()
                    .appendQueryParameter("tkn", Seguridad.GetInstancia().getTokenWS(ServicioWeb.LOGIN))
                    .appendQueryParameter("usr", usuario)
                    .appendQueryParameter("psw", password)
                    .build().toString();

            Log.e("RETORNO URL", HttpRequest.encode(url));
            Log.e("RETORNO URL", url);

            //String strJson = HttpRequest.get(url).accept("application/xml").body();
            String strJson = HttpRequest.get(url).accept("application/json").body();

            Log.e("RETORNO REST", strJson);


            /*xml
            Serializer serializer = new Persister();
            Retorno_MsgObj re  = serializer.read(Retorno_MsgObj.class, strJson);
            Log.e("RETORNO", re.toString());
            */


            /*json
            Gson gson = new Gson();
            Type type = new TypeToken<Retorno_MsgObj>(){}.getType();
            Retorno_MsgObj re = gson.fromJson(strJson, type);

            if(re.getObjeto() != null)
            {
                Log.e("RETORNO OBJ", re.getObjeto().toString());
                Type typeT = new TypeToken<TipoEvaluacion>(){}.getType();
                TipoEvaluacion tpoEvl = gson.fromJson(re.getObjeto().toString(), typeT);
                Log.e("RETORNO OBJ", tpoEvl.toString());
            }

            retorno.setMensaje(re.getMensaje());
            */

            /*json manual*/
            Retorno_MsgObj re = (Retorno_MsgObj) Utilidades.GetInstancia().JsonToObject(strJson, new Retorno_MsgObj());

            if(re.getObjeto() != null)
            {
                Log.e("RETORNO OBJ", re.getObjeto().toString());
                TipoEvaluacion tpoEvl = (TipoEvaluacion) re.getObjeto();
                Log.e("RETORNO OBJ", tpoEvl.toString());
            }

            retorno.setMensaje(re.getMensaje());
            /**/
        }
        catch (HttpRequest.HttpRequestException e)
        {
            e.printStackTrace();
            resul = false;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resul;
    }

    private Boolean Logout(){
        Log.e("SERVICIO", "Iniciando servicio");

        boolean resul   = true;
        retorno         = new Retorno_MsgObj(new Mensajes("Error", TipoMensaje.ERROR));

        Persona persona = (Persona) parametro.getObjeto();


        SoapObject request = new SoapObject(WS_Login.servicio.NAMESPACE, WS_Login.Logout.METHOD_NAME);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = false;

        String PerCod = "";

        try {
            PerCod = Seguridad.GetInstancia().crypt(persona.getPerCod().toString()).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //request.addProperty("token", Seguridad.GetInstancia().getTokenWS(ServicioWeb.LOGIN));
        request.addProperty("pPerCod", PerCod);

        envelope.setOutputSoapObject(request);

        //------------------------------------------------------------------------------------------

        List<HeaderProperty> headerList = new ArrayList<HeaderProperty>();
        headerList.add(new HeaderProperty("token", Seguridad.GetInstancia().getTokenWS(ServicioWeb.LOGIN)));

        //------------------------------------------------------------------------------------------

        HttpTransportSE transporte = new HttpTransportSE(WS_Login.servicio.URL);

        try
        {
            transporte.call(WS_Login.Logout.SOAP_ACTION, envelope, headerList);

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