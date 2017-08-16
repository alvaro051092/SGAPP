package uy.edu.ctc.sgapp.logica;

import android.content.Context;
import android.util.Log;

import uy.edu.ctc.sgapp.entidad.CalendarioAlumno;
import uy.edu.ctc.sgapp.enumerado.PersonaServicioMetodo;
import uy.edu.ctc.sgapp.persistencia.perConfiguracion;
import uy.edu.ctc.sgapp.utiles.Retorno_MsgObj;
import uy.edu.ctc.sgapp.web_service.ws_evaluacionalumno;

/**
 * Created by aa on 05-ago-17.
 */

public class loEvaluacion {
    private static loEvaluacion instancia;
    private perConfiguracion cfg;
    private Context contexto;

    private loEvaluacion(Context context) {
        this.contexto   = context;
        this.cfg        = perConfiguracion.getInstancia(context);
    }

    public static final loEvaluacion getInstancia(Context context){
        if (instancia == null)
        {
            instancia = new loEvaluacion(context);
        }
        return  instancia;
    }

    public boolean SesionValida()
    {
        if(cfg.Devolver().getPerCod() != null)
        {
            return true;
        }
        return false;
    }

//    public Retorno_MsgObj EvaluacionesParaInscripcion(CalendarioAlumno calAlumno){
//        Retorno_MsgObj retorno = new Retorno_MsgObj();
//
//        if(this.SesionValida())
//        {
//            Retorno_MsgObj parametro = new Retorno_MsgObj();
//            parametro.setObjeto(calAlumno);
//
//            ws_evaluacionalumno ws = new ws_evaluacionalumno(this, PersonaServicioMetodo.GET_EVALUACIONESPARAINSCRIPCION, parametro);
//            ws.execute();
//        }
//        return retorno;
//    }


    //------------------------------------------------------------------------------------
//    public void lstPorAlumno(CalendarioAlumno calAlumno){
//        if(this.SesionValida())
//        {
//        Retorno_MsgObj parametro = new Retorno_MsgObj();
//        parametro.setObjeto(calAlumno);
//
//        ws_evaluacionalumno ws = new ws_evaluacionalumno(this, PersonaServicioMetodo.GET_LISTAPORALUMNO, parametro);
//        ws.execute();
//        }
//    }

    public void lstPendiente(CalendarioAlumno calAlumno){
//        if(this.SesionValida())
//        {
        Retorno_MsgObj parametro = new Retorno_MsgObj();
        parametro.setObjeto(calAlumno);

        ws_evaluacionalumno ws = new ws_evaluacionalumno(this, PersonaServicioMetodo.GET_LISTAPENDIENTE, parametro);
        ws.execute();
//        }
    }

//    public void InscribirAlumno(CalendarioAlumno calAlumn){
//        if(this.SesionValida()){
//            Retorno_MsgObj parametro = new Retorno_MsgObj();
//            parametro.setObjeto(calAlumn);
//
//            ws_evaluacionalumno ws = new ws_evaluacionalumno(this, PersonaServicioMetodo.INSCRIBIRALUMNO, parametro);
//            ws.execute();
//        }
//    }

    public void DesinscribirAlumno(CalendarioAlumno calAlumn)
    {
//        if(this.SesionValida()){
        Retorno_MsgObj parametro = new Retorno_MsgObj();
        parametro.setObjeto(calAlumn);

        ws_evaluacionalumno ws = new ws_evaluacionalumno(this, PersonaServicioMetodo.DESINSCRIBIRALUMNO, parametro);
        ws.execute();
//        }
    }
}
