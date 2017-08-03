package uy.edu.ctc.sgapp.logica;

import android.content.Context;
import android.provider.Settings;

import java.util.UUID;

import uy.edu.ctc.sgapp.entidad.Configuracion;
import uy.edu.ctc.sgapp.entidad.Persona;
import uy.edu.ctc.sgapp.enumerado.PersonaServicioMetodo;
import uy.edu.ctc.sgapp.persistencia.perConfiguracion;
import uy.edu.ctc.sgapp.utiles.Retorno_MsgObj;
import uy.edu.ctc.sgapp.web_service.ws_persona;

/**
 * Created by alvar on 02/08/2017.
 */

public class loPersona {
    private static loPersona instancia;
    private perConfiguracion cfg;
    private Context contexto;

    private loPersona(Context context) {
        this.contexto   = context;
        this.cfg        = perConfiguracion.getInstancia(context);
    }

    public static final loPersona getInstancia(Context context){
        if (instancia == null)
        {
            instancia = new loPersona(context);
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

    public Long DevolverCodigoPersona(){
        Long perCod         = null;
        String strperCod    = cfg.Devolver().getPerCod();

        if(strperCod != null) Long.parseLong(strperCod);

        return perCod;

    }

    public void GuardarCodigoPersona(Long PerCod){
        Configuracion configuracion = cfg.Devolver();
        configuracion.setPerCod(PerCod.toString());
        cfg.Modificar(configuracion);
    }

    public void ActualizarToken(Persona persona){
        if(this.SesionValida())
        {
            Retorno_MsgObj parametro = new Retorno_MsgObj();
            parametro.setObjeto(persona);

            ws_persona ws = new ws_persona(this, PersonaServicioMetodo.UPD_TOKEN, parametro);
            ws.execute();

        }
    }

}
