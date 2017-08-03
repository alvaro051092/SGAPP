package uy.edu.ctc.sgapp.persistencia;

import android.content.Context;
import android.provider.Settings;

import java.util.UUID;

import uy.edu.ctc.sgapp.entidad.Configuracion;
import uy.edu.ctc.sgapp.utiles.Utilidades;

/**
 * Created by alvar on 02/08/2017.
 */

public class perConfiguracion {

    private static perConfiguracion instancia;
    private AdminSQLite sql;
    private  Context contexto;

    private perConfiguracion(Context context) {
        this.contexto   = context;
        sql             = AdminSQLite.getInstancia(context);
    }

    public static final perConfiguracion getInstancia(Context context){
        if (instancia == null)
        {
            instancia = new perConfiguracion(context);
        }

        return  instancia;
    }

    public Configuracion Devolver(){
        Configuracion configuracion = sql.ConfiguracionDevolver(1);

        if(configuracion == null)
        {
            configuracion = new Configuracion();
            configuracion.setSessionId(UUID.randomUUID().toString());

            configuracion.set_ID(sql.ConfiguracionAgregar(configuracion));
        }
        else
        {
            if(configuracion.get_ID() == null)
            {
                configuracion = new Configuracion();
                configuracion.setSessionId(UUID.randomUUID().toString());

                configuracion.set_ID(sql.ConfiguracionAgregar(configuracion));
            }

        }

        return configuracion;

    }

    public void Modificar(Configuracion configuracion){
        sql.ConfiguracionModificar(configuracion);
    }

}
