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
import uy.edu.ctc.sgapp.entidad.CalendarioAlumno;
import uy.edu.ctc.sgapp.entidad.Carrera;
import uy.edu.ctc.sgapp.entidad.Curso;
import uy.edu.ctc.sgapp.entidad.Escolaridad;
import uy.edu.ctc.sgapp.entidad.Materia;
import uy.edu.ctc.sgapp.entidad.Modulo;
import uy.edu.ctc.sgapp.entidad.Persona;
import uy.edu.ctc.sgapp.entidad.PlanEstudio;
import uy.edu.ctc.sgapp.enumerado.PersonaServicioMetodo;
import uy.edu.ctc.sgapp.enumerado.TipoMensaje;
import uy.edu.ctc.sgapp.user_interface.escolaridad;
import uy.edu.ctc.sgapp.utiles.Mensajes;
import uy.edu.ctc.sgapp.utiles.Retorno_MsgObj;
import uy.edu.ctc.sgapp.web_service.Servicios.WS_Estudios;
import uy.edu.ctc.sgapp.web_service.Servicios.WS_EvaluacionAlumno;

/**
 * Created by aa on 20-ago-17.
 */

public class ws_estudios extends AsyncTask<String,Integer,Boolean> {
    private Retorno_MsgObj retorno;
    private Retorno_MsgObj parametro;
    private Object clase;
    private PersonaServicioMetodo metodo;
    private String metodoToCall;

    public ws_estudios(Object clase, PersonaServicioMetodo metodo, Retorno_MsgObj parametro) {
        this.clase = clase;
        this.metodo = metodo;
        this.parametro = parametro;
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        boolean resul = true;

        switch(this.metodo)
        {
            case GET_ESTUDIOS:
                resul = this.lstEstudiosPorAlumno();
                metodoToCall = "Estudios";
                break;
            case GET_ESTUDIOSPREVIOS:
                resul = this.lstEstudiosPreviosPorAlumno();
                metodoToCall = "EstudiosPrevios";
                break;
        }
        return resul;
    }

    protected void onPostExecute(Boolean result){
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

    public boolean lstEstudiosPorAlumno()
    {
        boolean resul                       = true;

        SDT_PersonaEstudio SDT_PE           = new SDT_PersonaEstudio();
        ArrayList<Escolaridad> lstEsc       = new ArrayList<>();
        Escolaridad escolaridad;
        Materia materia;
        PlanEstudio plan;
        Carrera carrera;
        Curso curso;
        Modulo modulo;
        List<Object> lstObj                 = new ArrayList<>();
        retorno = new Retorno_MsgObj(new Mensajes("Error", TipoMensaje.ERROR));

        SoapObject request = new SoapObject(WS_Estudios.servicio.NAMESPACE, WS_Estudios.Estudios.METHOD_NAME);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        Persona per = (Persona) parametro.getObjeto();

        long PerCod  = per.getPerCod();

        request.addProperty("token", "pedritoelescamoso");
        request.addProperty("PerCod", PerCod);

        envelope.setOutputSoapObject(request);

        HttpTransportSE transporte = new HttpTransportSE(WS_Estudios.servicio.URL);

        try
        {
            transporte.call(WS_Estudios.Estudios.SOAP_ACTION, envelope);

            SoapObject resSoapObj = (SoapObject) envelope.getResponse();

            for (int a = 0; a < resSoapObj.getPropertyCount(); a++)
            {
                SoapObject ic = (SoapObject)resSoapObj.getProperty(a);

                PropertyInfo pi_ = new PropertyInfo();
                resSoapObj.getPropertyInfo(a, pi_);

                String objetoPadre = pi_.name;

//                Log.e("Pi.NAME A: ", objetoPadre.toString());
//                Log.e("IC A: ", ic.getProperty(a).toString());

                for (int b = 0; b < ic.getPropertyCount(); b++){
                    PropertyInfo pi = new PropertyInfo();
                    ic.getPropertyInfo(b, pi);

                    String objetoPadre_esc = pi.name;

//                    Log.e("Pi.NAME B: ", pi.name.toString());
//                    Log.e("IC B: ", ic.getProperty(b).toString());

                    if(objetoPadre.equals("mensaje")) retorno.setField(pi.name, ic.getProperty(b).toString());

                    if(objetoPadre_esc.equals("escolaridad"))
                    {
                        escolaridad = new Escolaridad();
                        SoapObject ic_esc = (SoapObject) ic.getProperty(b);

                        for (int c = 0; c < ic_esc.getPropertyCount(); c++)
                        {
                            PropertyInfo pi_esc = new PropertyInfo();
                            ic_esc.getPropertyInfo(c, pi_esc);

                            String objetoPadre_mat = pi_esc.name;

//                            Log.e("Pi.NAME C: ", pi_esc.name.toString());
//                            Log.e("IC C: ", ic_esc.getProperty(c).toString());

                            escolaridad.setField(pi_esc.name, ic_esc.getProperty(c).toString());

                            if(objetoPadre_mat.equals("materia"))
                            {
                                materia = new Materia();
                                SoapObject ic_mat = (SoapObject) ic_esc.getProperty(c);

                                for(int d = 0; d < ic_mat.getPropertyCount(); d++)
                                {
                                    PropertyInfo pi_mat = new PropertyInfo();
                                    ic_mat.getPropertyInfo(d, pi_mat);

                                    String objetoPadre_plan = pi_mat.name;

//                                    Log.e("Pi.NAME D: ", pi_mat.name.toString());
//                                    Log.e("IC D: ", ic_mat.getProperty(d).toString());

                                    materia.setField(pi_mat.name, ic_mat.getProperty(d).toString());

                                    if(objetoPadre_plan.equals("plan"))
                                    {
                                        plan = new PlanEstudio();
                                        SoapObject ic_plan = (SoapObject) ic_mat.getProperty(d);

                                        for(int e = 0; e < ic_plan.getPropertyCount(); e++)
                                        {
                                            PropertyInfo pi_plan = new PropertyInfo();
                                            ic_plan.getPropertyInfo(e, pi_plan);

                                            String objetoPadre_car = pi_plan.name;

//                                            Log.e("Pi.NAME E: ", pi_plan.name.toString());
//                                            Log.e("IC E: ", ic_plan.getProperty(e).toString());

                                            plan.setField(pi_plan.name, ic_plan.getProperty(e).toString());

                                            if(objetoPadre_car.equals("carrera"))
                                            {
                                                carrera = new Carrera();
                                                SoapObject ic_car = (SoapObject) ic_plan.getProperty(e);

                                                for(int f = 0; f < ic_car.getPropertyCount(); f++)
                                                {
                                                    PropertyInfo pi_car = new PropertyInfo();
                                                    ic_car.getPropertyInfo(f, pi_car);

//                                                    String objetoPadre_ = pi_plan.name;

//                                                    Log.e("Pi.NAME F: ", pi_car.name.toString());
//                                                    Log.e("IC F: ", ic_car.getProperty(f).toString());

                                                    carrera.setField(pi_car.name, ic_car.getProperty(f).toString());
                                                }
                                                plan.setCarrera(carrera);
                                            }
                                        }
                                        materia.setPlan(plan);
                                    }
                                }
                                escolaridad.setMateria(materia);
                            }
                            //agregar if Modulo
                            if(objetoPadre_mat.equals("modulo"))
                            {
                                modulo = new Modulo();
                                SoapObject ic_mod = (SoapObject) ic_esc.getProperty(c);

                                for(int k = 0; k < ic_mod.getPropertyCount(); k++)
                                {
                                    PropertyInfo pi_mod = new PropertyInfo();
                                    ic_mod.getPropertyInfo(k, pi_mod);

                                    String objetoPadre_mod = pi_mod.name;
                                    Log.e("Pi.NAME K: ", objetoPadre_mod.toString());
                                    Log.e("IC K: ", ic_mod.getProperty(k).toString());

                                    modulo.setField(objetoPadre_mod, ic_mod.getProperty(k).toString());

                                    //Armo Curso del Modulo
                                    if(objetoPadre_mod.equals("curso"))
                                    {
                                        curso = new Curso();
                                        SoapObject ic_cur = (SoapObject) ic_mod.getProperty(k);

                                        for(int l = 0; l < ic_cur.getPropertyCount(); l++)
                                        {
                                            PropertyInfo pi_cur = new PropertyInfo();
                                            ic_cur.getPropertyInfo(l, pi_cur);

                                            String objetoPadre_cur = pi_cur.name;
                                        Log.e("Pi.NAME L: ", objetoPadre_cur.toString());
                                        Log.e("IC L: ", ic_cur.getProperty(l).toString());

                                            curso.setField(objetoPadre_cur, ic_cur.getProperty(l).toString());
                                        }
                                        modulo.setCurso(curso);
                                    }
                                }
                                escolaridad.setModulo(modulo);
                            }
                            //agregar if Curso
                            if(objetoPadre_mat.equals("curso"))
                            {
                                curso = new Curso();
                                SoapObject ic_cur = (SoapObject) ic_esc.getProperty(c);

                                for(int l = 0; l < ic_cur.getPropertyCount(); l++)
                                {
                                    PropertyInfo pi_cur = new PropertyInfo();
                                    ic_cur.getPropertyInfo(l, pi_cur);

                                    String objetoPadre_cur = pi_cur.name;
                                Log.e("Pi.NAME O: ", objetoPadre_cur.toString());
                                Log.e("IC O: ", ic_cur.getProperty(l).toString());

                                    curso.setField(objetoPadre_cur, ic_cur.getProperty(l).toString());
                                }
                                escolaridad.setCurso(curso);
                            }
                        }
                        lstObj.add(escolaridad);
                    }
                }
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

    public boolean lstEstudiosPreviosPorAlumno()
    {
        boolean resul                       = true;
        return resul;
    }
}
