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
import java.util.NoSuchElementException;

import uy.edu.ctc.sgapp.entidad.Calendario;
import uy.edu.ctc.sgapp.entidad.CalendarioAlumno;
import uy.edu.ctc.sgapp.entidad.Curso;
import uy.edu.ctc.sgapp.entidad.Evaluacion;
import uy.edu.ctc.sgapp.entidad.Materia;
import uy.edu.ctc.sgapp.entidad.Modulo;
import uy.edu.ctc.sgapp.entidad.Persona;
import uy.edu.ctc.sgapp.entidad.TipoEvaluacion;
import uy.edu.ctc.sgapp.enumerado.PersonaServicioMetodo;
import uy.edu.ctc.sgapp.enumerado.TipoMensaje;
import uy.edu.ctc.sgapp.utiles.Mensajes;
import uy.edu.ctc.sgapp.utiles.Retorno_MsgObj;
import uy.edu.ctc.sgapp.web_service.Servicios.WS_EvaluacionAlumno;

import static android.util.Log.e;

/**
 * Created by aa on 03-ago-17.
 */

public class ws_evaluacionalumno extends AsyncTask<String,Integer,Boolean>{
    private Retorno_MsgObj retorno;
    private Retorno_MsgObj parametro;
    private Object clase;
    private PersonaServicioMetodo metodo;
    private String metodoToCall;

    public ws_evaluacionalumno(Object pClase, PersonaServicioMetodo pMetodo, Retorno_MsgObj pParametro) {
        this.clase = pClase;
        this.metodo = pMetodo;
        this.parametro = pParametro;
    }


    protected Boolean doInBackground(String... strings) {

        boolean resul = true;

        switch(this.metodo)
        {
            case GET_EVALUACIONESPARAINSCRIPCION:
                resul = this.EvaluacionesParaInscripcion();
                metodoToCall = "EvaluacionesParaInscripcion";
                break;
            case GET_EVALUACIONESFINALIZADAS:
                resul = this.EvaluacionesFinalizadas();
                metodoToCall = "EvaluacionesFinalizadas";
                break;
            case GET_LISTAPENDIENTE:
                resul = this.ListaPendiente();
                metodoToCall = "RetornoListaPendiente";
                break;
            case INSCRIBIRALUMNO:
                resul = this.InscribirAlumno();
                metodoToCall = "InscribirAlumno";
                break;
            case DESINSCRIBIRALUMNO:
                resul = this.DesinscribirAlumno();
                metodoToCall = "RetornoDesinscribirAlumno";
                break;
        }
        return resul;
    }

    protected void onPostExecute(Boolean result){
        if (result)
        {
            if(retorno.getMensaje() != null) Log.e("RESULTADO: ", retorno.getMensaje().getMensaje());
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

    public boolean EvaluacionesParaInscripcion(){
        retorno                         = new Retorno_MsgObj();
        boolean resul                   = true;
        List<Object> lstObj             = new ArrayList<>();
        Calendario calendario           = new Calendario();
        Evaluacion evaluacion           = new Evaluacion();
        TipoEvaluacion tpoevaluacion    = new TipoEvaluacion();
        Materia materia                 = new Materia();
        Curso curso                     = new Curso();
        Modulo modulo                   = new Modulo();

        SoapObject request = new SoapObject(WS_EvaluacionAlumno.servicio.NAMESPACE, WS_EvaluacionAlumno.EvaluacionesPorAlumno.METHOD_NAME);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        CalendarioAlumno calAlumno = (CalendarioAlumno) parametro.getObjeto();

        long AluPerCod = calAlumno.getAlumno().getPerCod();

        request.addProperty("token", "pedritoelescamoso");
        request.addProperty("AluPerCod", AluPerCod);

        envelope.setOutputSoapObject(request);

        HttpTransportSE transporte = new HttpTransportSE(WS_EvaluacionAlumno.servicio.URL);

        try
        {
            transporte.call(WS_EvaluacionAlumno.EvaluacionesPorAlumno.SOAP_ACTION, envelope);

            SoapObject resSoapObj =(SoapObject) envelope.getResponse();

            for (int i = 0; i < resSoapObj.getPropertyCount(); i++)
            {
                SoapObject ic_cal = (SoapObject)resSoapObj.getProperty(i);

                PropertyInfo pi_ = new PropertyInfo();
                resSoapObj.getPropertyInfo(i, pi_);

                String objetoPadre = pi_.name;

                for (int f = 0; f < ic_cal.getPropertyCount(); f++)
                {
                    PropertyInfo pi_cal = new PropertyInfo();
                    ic_cal.getPropertyInfo(f, pi_cal);

                    String objetoPadre_cal = pi_cal.name;
//                    Log.e("Pi.NAME F: ", objetoPadre_cal.toString());
//                    Log.e("IC F: ", ic_cal.getProperty(f).toString());

                    if(objetoPadre.equals("mensaje")) retorno.setField(objetoPadre_cal, ic_cal.getProperty(f).toString());
                    if(objetoPadre.equals("lstObjetos")) calendario.setField(objetoPadre_cal, ic_cal.getProperty(f).toString());

                    if(objetoPadre_cal.equals("evaluacion"))
                    {
                        SoapObject ic_eva = (SoapObject) ic_cal.getProperty(f);

                        for (int g = 0; g < ic_eva.getPropertyCount(); g++)
                        {
                            PropertyInfo pi_eva = new PropertyInfo();
                            ic_eva.getPropertyInfo(g, pi_eva);

                            String objetoPadre_eva = pi_eva.name;

//                            Log.e("Pi.NAME G: ", objetoPadre_eva.toString());
//                            Log.e("IC G: ", ic_eva.getProperty(g).toString());

                            evaluacion.setField(objetoPadre_eva, ic_eva.getProperty(g).toString());

                            if(objetoPadre_eva.equals("tpoEvl"))
                            {
                                SoapObject ic_tpo = (SoapObject) ic_eva.getProperty(g);

                                for(int h = 0; h < ic_tpo.getPropertyCount(); h++)
                                {
                                    PropertyInfo pi_tpo = new PropertyInfo();
                                    ic_tpo.getPropertyInfo(h, pi_tpo);

                                    String objetoPadre_tpo = pi_tpo.name;
//                                    Log.e("Pi.NAME H: ", objetoPadre_tpo.toString());
//                                    Log.e("IC H: ", ic_tpo.getProperty(h).toString());

                                    tpoevaluacion.setField(objetoPadre_tpo, ic_tpo.getProperty(h).toString());
                                }
                            }
                            if(objetoPadre_eva.equals("matEvl"))
                            {
                                SoapObject ic_mat = (SoapObject) ic_eva.getProperty(g);

                                for(int j = 0; j < ic_mat.getPropertyCount(); j++)
                                {
                                    PropertyInfo pi_mat = new PropertyInfo();
                                    ic_mat.getPropertyInfo(j, pi_mat);

                                    String objetoPadre_mat = pi_mat.name;
//                                    Log.e("Pi.NAME J: ", objetoPadre_mat.toString());
//                                    Log.e("IC J: ", ic_mat.getProperty(j).toString());

                                    materia.setField(objetoPadre_mat, ic_mat.getProperty(j).toString());
                                }
                            }
//                            if(objetoPadre_eva.equals("modEvl"))
//                            {
//                                SoapObject ic_mod = (SoapObject) ic_eva.getProperty(g);
//
//                                for(int k = 0; k < ic_mod.getPropertyCount(); k++)
//                                {
//                                    PropertyInfo pi_mod = new PropertyInfo();
//                                    ic_mod.getPropertyInfo(k, pi_mod);
//
//                                    String objetoPadre_mod = pi_mod.name;
////                                    Log.e("Pi.NAME K: ", objetoPadre_mod.toString());
////                                    Log.e("IC K: ", ic_mod.getProperty(k).toString());
//
////                                    modulo.setField(objetoPadre_mod, ic_mod.getProperty(k).toString());
//                                }
//                            }
//                            if(objetoPadre_eva.equals("curEvl"))
//                            {
//                                SoapObject ic_cur = (SoapObject) ic_eva.getProperty(g);
//
//                                for(int l = 0; l < ic_cur.getPropertyCount(); l++)
//                                {
//                                    PropertyInfo pi_cur = new PropertyInfo();
//                                    ic_cur.getPropertyInfo(l, pi_cur);
//
//                                    String objetoPadre_cur = pi_cur.name;
////                                    Log.e("Pi.NAME L: ", objetoPadre_cur.toString());
////                                    Log.e("IC L: ", ic_cur.getProperty(l).toString());
//
////                                    curso.setField(objetoPadre_cur, ic_cur.getProperty(l).toString());
//                                }
//                            }
                        }
                    }
                }
            }
            calendario.setEvaluacion(evaluacion);
            calendario.getEvaluacion().setTipoEvaluacion(tpoevaluacion);
            calendario.getEvaluacion().setMatEvl(materia);
//            calendario.getEvaluacion().setCurEvl(curso);
//            calendario.getEvaluacion().setModEvl(modulo);
            lstObj.add(calendario);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            resul = false;
        }
        retorno.setLstObjetos(lstObj);

        return resul;
    }

    public boolean EvaluacionesFinalizadas(){
        retorno = new Retorno_MsgObj();
        boolean resul = true;

        SoapObject request = new SoapObject(WS_EvaluacionAlumno.servicio.NAMESPACE, WS_EvaluacionAlumno.ListaPorAlumno.METHOD_NAME);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        CalendarioAlumno calAlumno = (CalendarioAlumno) parametro.getObjeto();

        long AluPerCod = calAlumno.getAlumno().getPerCod();

        request.addProperty("token", "pedritoelescamoso");
        request.addProperty("UsuAlumno", AluPerCod);

        envelope.setOutputSoapObject(request);

        HttpTransportSE transporte = new HttpTransportSE(WS_EvaluacionAlumno.servicio.URL);

        try
        {
            transporte.call(WS_EvaluacionAlumno.ListaPorAlumno.SOAP_ACTION, envelope);

            SoapObject resSoapObj =(SoapObject) envelope.getResponse();

            for (int i = 0; i < resSoapObj.getPropertyCount(); i++)
            {
                SoapObject ic = (SoapObject)resSoapObj.getProperty(i);

                PropertyInfo pi_ = new PropertyInfo();
                resSoapObj.getPropertyInfo(i, pi_);

                String objetoPadre = pi_.name;

                for (int f = 0; f < ic.getPropertyCount(); f++){

                    PropertyInfo pi = new PropertyInfo();
                    ic.getPropertyInfo(f, pi);

                    if(objetoPadre.equals("objeto")) retorno.setField(pi.name, ic.getProperty(f).toString());
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

    public boolean ListaPendiente(){
        retorno = new Retorno_MsgObj();
        boolean resul = true;

        SoapObject request = new SoapObject(WS_EvaluacionAlumno.servicio.NAMESPACE, WS_EvaluacionAlumno.ListaPendiente.METHOD_NAME);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        CalendarioAlumno calAlumno = (CalendarioAlumno) parametro.getObjeto();

        long AluPerCod = calAlumno.getAlumno().getPerCod();

        request.addProperty("token", "pedritoelescamoso");
        request.addProperty("UsuAlumno", AluPerCod);

        envelope.setOutputSoapObject(request);

        HttpTransportSE transporte = new HttpTransportSE(WS_EvaluacionAlumno.servicio.URL);

        try
        {
            transporte.call(WS_EvaluacionAlumno.ListaPendiente.SOAP_ACTION, envelope);

            SoapObject resSoapObj =(SoapObject) envelope.getResponse();

            for (int i = 0; i < resSoapObj.getPropertyCount(); i++)
            {
                SoapObject ic = (SoapObject)resSoapObj.getProperty(i);

                PropertyInfo pi_ = new PropertyInfo();
                resSoapObj.getPropertyInfo(i, pi_);

                String objetoPadre = pi_.name;

                for (int f = 0; f < ic.getPropertyCount(); f++){

                    PropertyInfo pi = new PropertyInfo();
                    ic.getPropertyInfo(f, pi);

                    if(objetoPadre.equals("objeto")) retorno.setField(pi.name, ic.getProperty(f).toString());
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

    public boolean InscribirAlumno(){
        retorno = new Retorno_MsgObj(new Mensajes("Error", TipoMensaje.ERROR));
        boolean resul = true;

        SoapObject request = new SoapObject(WS_EvaluacionAlumno.servicio.NAMESPACE, WS_EvaluacionAlumno.InscribirAlumno.METHOD_NAME);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        CalendarioAlumno calAlumno = (CalendarioAlumno) parametro.getObjeto();

        long AluPerCod  = calAlumno.getAlumno().getPerCod();
        long CalCod     = calAlumno.getCalendario().getCalCod();

        request.addProperty("token", "pedritoelescamoso");
        request.addProperty("AluPerCod", AluPerCod);
        request.addProperty("CalCod", CalCod);

        envelope.setOutputSoapObject(request);

        HttpTransportSE transporte = new HttpTransportSE(WS_EvaluacionAlumno.servicio.URL);

        try
        {
            transporte.call(WS_EvaluacionAlumno.InscribirAlumno.SOAP_ACTION, envelope);

            SoapObject resSoapObj = (SoapObject) envelope.getResponse();

            for (int i = 0; i < resSoapObj.getPropertyCount(); i++)
            {
                SoapObject ic = (SoapObject)resSoapObj.getProperty(i);

                PropertyInfo pi_ = new PropertyInfo();
                resSoapObj.getPropertyInfo(i, pi_);

                String objetoPadre = pi_.name;

                for (int f = 0; f < ic.getPropertyCount(); f++){

                    PropertyInfo pi = new PropertyInfo();
                    ic.getPropertyInfo(f, pi);

                    if(objetoPadre.equals("mensaje")) retorno.setField(pi.name, ic.getProperty(f).toString());
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            resul = false;
        }
        return resul;
    } // OK

    public boolean DesinscribirAlumno(){
        retorno = new Retorno_MsgObj();
        boolean resul = true;

        SoapObject request = new SoapObject(WS_EvaluacionAlumno.servicio.NAMESPACE, WS_EvaluacionAlumno.DesinscribirAlumno.METHOD_NAME);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        CalendarioAlumno calAlumno = (CalendarioAlumno) parametro.getObjeto();

        long CalAlCod  = calAlumno.getCalAlCod();
        long CalCod     = calAlumno.getCalendario().getCalCod();

        request.addProperty("token", "pedritoelescamoso");
        request.addProperty("CalAlCod", CalAlCod);
        request.addProperty("CalCod", CalCod);

        envelope.setOutputSoapObject(request);

        HttpTransportSE transporte = new HttpTransportSE(WS_EvaluacionAlumno.servicio.URL);

        try
        {
            transporte.call(WS_EvaluacionAlumno.DesinscribirAlumno.SOAP_ACTION, envelope);

            SoapObject resSoapObj = (SoapObject) envelope.getResponse();

            for (int i = 0; i < resSoapObj.getPropertyCount(); i++)
            {
                SoapObject ic = (SoapObject)resSoapObj.getProperty(i);

                PropertyInfo pi_ = new PropertyInfo();
                resSoapObj.getPropertyInfo(i, pi_);

                String objetoPadre = pi_.name;

//                Log.e("RESULTADO 1", objetoPadre.toString());

                for (int f = 0; f < ic.getPropertyCount(); f++){

                    PropertyInfo pi = new PropertyInfo();
                    ic.getPropertyInfo(f, pi);

//                    Log.e("RESULTADO 2", pi.name + " : " + ic.getProperty(f).toString());

                    if(objetoPadre.equals("mensaje")) retorno.setField(pi.name, ic.getProperty(f).toString());
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
}
