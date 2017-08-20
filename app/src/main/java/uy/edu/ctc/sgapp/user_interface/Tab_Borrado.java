package uy.edu.ctc.sgapp.user_interface;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

public class Tab_Borrado extends Fragment {

    private ListView listEvalParaBorrarse;
    private EvaluacionesAdapter evaAdapter;

    Persona per;
    Retorno_MsgObj parametro;
    CalendarioAlumno calAlumno;
    Calendario cal;
    Date fechaActual;
    SimpleDateFormat sdf;
    Object obj;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_tab_borrado, container, false);

        final AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());

        cargarDatos();

        listEvalParaBorrarse = (ListView) rootView.findViewById(R.id.listEvalParaBorrarse);

        cargarEvaluaciones();

        listEvalParaBorrarse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                obj = listEvalParaBorrarse.getItemAtPosition(i);

                dialog.setMessage("¿Desea Borrarse su inscripcion?");
                dialog.setCancelable(false);
                dialog.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                    EliminarInscripcion(obj);

                    }
                });
                dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                    }
                });
                dialog.show();
            }
        });

        return rootView;
    }

    public void EliminarInscripcion(Object obj)
    {
        parametro = new Retorno_MsgObj();
        cal = (Calendario) obj;

        long CalAlCod;

        for(CalendarioAlumno calAl : cal.getLstAlumnos())
        {
            CalAlCod = calAl.getCalAlCod();

            calAlumno.setCalAlCod(CalAlCod);
        }

        calAlumno.setCalendario(cal);

        parametro.setObjeto(calAlumno);

        ws_evaluacionalumno ws = new ws_evaluacionalumno(this, PersonaServicioMetodo.DESINSCRIBIRALUMNO, parametro);
        ws.execute();
    }

    public void EvaluacionesParaInscripcion(Retorno_MsgObj retorno)
    {
        if(retorno.getMensaje().getTipoMensaje() == TipoMensaje.ERROR)
        {
            System.out.println("No esta inscripto a ninguna Evaluacion" + TipoMensaje.ERROR);
            evaAdapter = new EvaluacionesAdapter(getContext(), new ArrayList<>());
            if(listEvalParaBorrarse != null) listEvalParaBorrarse.setAdapter(evaAdapter);
        }
        else
        {
            evaAdapter = new EvaluacionesAdapter(getContext(), retorno.getLstObjetos());
            if(listEvalParaBorrarse != null) listEvalParaBorrarse.setAdapter(evaAdapter);
        }
    }

    // Metodo que recibe el resultado del servicio desinscribir alumno de evaluación
    private void DesinscribirAlumno(Retorno_MsgObj retorno)
    {
        if(retorno.getMensaje().getTipoMensaje() == TipoMensaje.MENSAJE)
        {
            Toast.makeText(getContext(), "Se elimino su inscripcion", Toast.LENGTH_LONG).show();

            cargarDatos();

            //Recargo lista borrar
            cargarEvaluaciones();
        }
        else
        {
            Toast.makeText(getContext(), "No se pudo eliminar la inscripcion", Toast.LENGTH_LONG).show();
        }
    }

    public void cargarEvaluaciones()
    {
        per = new Persona();
        calAlumno = new CalendarioAlumno();
        if(loPersona.getInstancia(getContext()).SesionValida())
        {
            Long PerCod = loPersona.getInstancia(getContext()).DevolverCodigoPersona();

            per.setPerCod(PerCod);
            calAlumno.setAlumno(per);

            parametro  = new Retorno_MsgObj();
            parametro.setObjeto(calAlumno);

            ws_evaluacionalumno ws = new ws_evaluacionalumno(this, PersonaServicioMetodo.GET_EVALUACIONESPARABORRAR, parametro);
            ws.execute();
        }
    }

    public void cargarDatos()
    {
        fechaActual = new Date();
        sdf = new SimpleDateFormat("yyyyMMdd");
    }
}
