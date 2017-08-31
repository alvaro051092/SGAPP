package uy.edu.ctc.sgapp.user_interface;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import uy.edu.ctc.sgapp.Adaptadores.EvaluacionesAdapter;
import uy.edu.ctc.sgapp.R;
import uy.edu.ctc.sgapp.entidad.Calendario;
import uy.edu.ctc.sgapp.entidad.CalendarioAlumno;
import uy.edu.ctc.sgapp.entidad.Persona;
import uy.edu.ctc.sgapp.enumerado.PersonaServicioMetodo;
import uy.edu.ctc.sgapp.enumerado.TipoMensaje;
import uy.edu.ctc.sgapp.logica.loPersona;
import uy.edu.ctc.sgapp.utiles.Retorno_MsgObj;
import uy.edu.ctc.sgapp.web_service.ws_evaluacionalumno;

/**
 * Created by aa on 17-ago-17.
 */

public class Tab_Inscribir extends Fragment {

    Persona per;
    Retorno_MsgObj parametro;
    CalendarioAlumno calAlumno;
    Calendario cal;
    Date fechaActual;
    SimpleDateFormat sdf;
    Object obj;

    private ListView listEvalParaInscribirse;
    private EvaluacionesAdapter evaAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_tab_inscribir, container, false);

        final AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());

        cargarDatos();

        listEvalParaInscribirse = (ListView) rootView.findViewById(R.id.listEvalParaInscribirse);

        cargarEvaluaciones();

        listEvalParaInscribirse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, int i, long l) {

                obj = listEvalParaInscribirse.getItemAtPosition(i);

                dialog.setMessage("¿Desea inscribirse?");
                dialog.setCancelable(false);
                dialog.setPositiveButton("Inscribirme", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                    Aceptar(obj);

                    }
                });
                dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i)
                    {
                    }
                });
                dialog.show();
            }
        });

        return rootView;
    }

    public void Aceptar(Object obj)
    {
        calAlumno = new CalendarioAlumno();
        cal = new Calendario();

        cal = (Calendario) obj;

//        long CalCod = cal.getCalCod();
//        cal.setCalCod(CalCod);

        calAlumno.setAlumno(per);
        calAlumno.setCalendario(cal);

//        System.out.println("CALENDARIO: " + calAlumno.getCalendario().getCalCod());
//        System.out.println("ALUMNOOOOO: " + calAlumno.getAlumno().getPerCod());

        parametro = new Retorno_MsgObj();
        parametro.setObjeto(calAlumno);

        ws_evaluacionalumno ws = new ws_evaluacionalumno(this, PersonaServicioMetodo.INSCRIBIRALUMNO, parametro);
        ws.execute();
    }

    public void EvaluacionesParaInscripcion(Retorno_MsgObj retorno)
    {
        if(retorno.getMensaje().getTipoMensaje() == TipoMensaje.ERROR)
        {
            System.out.println("No esta inscripto a ninguna Evaluacion " + TipoMensaje.ERROR);
            evaAdapter = new EvaluacionesAdapter(getContext(), new ArrayList<>());
            if(listEvalParaInscribirse != null) listEvalParaInscribirse.setAdapter(evaAdapter);
        }
        else
        {
            evaAdapter = new EvaluacionesAdapter(getContext(), retorno.getLstObjetos());

            if(listEvalParaInscribirse != null) listEvalParaInscribirse.setAdapter(evaAdapter);
        }
    }

    private void InscribirAlumno(Retorno_MsgObj retorno)
    {
        if(retorno.getMensaje().getTipoMensaje() == TipoMensaje.MENSAJE)
        {
            Toast.makeText(getContext(), "Estas Inscripto", Toast.LENGTH_LONG).show();

            cargarDatos();

            //Recargo la lista inscribir
            cargarEvaluaciones();

        }
        else
        {
            Toast.makeText(getContext(), "Error, no has podido inscribirte", Toast.LENGTH_LONG).show();
        }
    }

    public void cargarEvaluaciones()
    {
        if(loPersona.getInstancia(getContext()).SesionValida())
        {
            parametro  = new Retorno_MsgObj();
            parametro.setObjeto(calAlumno);

            ws_evaluacionalumno ws = new ws_evaluacionalumno(this, PersonaServicioMetodo.GET_EVALUACIONESPARAINSCRIPCION, parametro);
            ws.execute();
        }
    }

    public void cargarDatos()
    {
        //Persona
        Long PerCod = loPersona.getInstancia(getContext()).DevolverCodigoPersona();
        per = new Persona();
        per.setPerCod(PerCod);

        //Fecha actual
        fechaActual = new Date();
        sdf = new SimpleDateFormat("yyyyMMdd");

        //Calendario Alumno
        calAlumno = new CalendarioAlumno();
        calAlumno.setAlumno(per);
    }
}
