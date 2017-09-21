package uy.edu.ctc.sgapp.user_interface;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import uy.edu.ctc.sgapp.Adaptadores.EvaluacionesAdapter;
import uy.edu.ctc.sgapp.R;
import uy.edu.ctc.sgapp.entidad.Calendario;
import uy.edu.ctc.sgapp.entidad.CalendarioAlumno;
import uy.edu.ctc.sgapp.entidad.Persona;
import uy.edu.ctc.sgapp.enumerado.Constantes;
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

    private ListView listEvalParaInscribirse;
    private EvaluacionesAdapter evaAdapter;
    private ProgressBar loading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_tab_inscribir, container, false);

        loading = (ProgressBar) rootView.findViewById(R.id.tabins_load);

        ActionBar acBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        acBar.setHomeButtonEnabled(true);
        acBar.setDisplayHomeAsUpEnabled(true);

        cargarDatos();

        listEvalParaInscribirse = (ListView) rootView.findViewById(R.id.listEvalParaInscribirse);

        cargarEvaluaciones();

        return rootView;
    }

    public void EvaluacionesParaInscripcion(Retorno_MsgObj retorno)
    {
        listEvalParaInscribirse.setVisibility(View.VISIBLE);
        loading.setVisibility(View.INVISIBLE);
        if(retorno.getMensaje().getTipoMensaje() == TipoMensaje.ERROR)
        {
            System.out.println("No esta inscripto a ninguna Evaluacion " + TipoMensaje.ERROR);
            evaAdapter = new EvaluacionesAdapter(getContext(), new ArrayList<>(), Constantes.INSCRIBIR.toString());
            if(listEvalParaInscribirse != null) listEvalParaInscribirse.setAdapter(evaAdapter);
        }
        else
        {
            evaAdapter = new EvaluacionesAdapter(getContext(), retorno.getLstObjetos(), Constantes.INSCRIBIR.toString());

            if(listEvalParaInscribirse != null) listEvalParaInscribirse.setAdapter(evaAdapter);
        }
    }

    public void cargarEvaluaciones()
    {
        listEvalParaInscribirse.setVisibility(View.INVISIBLE);
        loading.setVisibility(View.VISIBLE);
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
