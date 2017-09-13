package uy.edu.ctc.sgapp.user_interface;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import uy.edu.ctc.sgapp.Adaptadores.EvaluacionesAdapter;
import uy.edu.ctc.sgapp.Adaptadores.curso_carreraAdapter;
import uy.edu.ctc.sgapp.R;
import uy.edu.ctc.sgapp.SDT.SDT_PersonaEstudio;
import uy.edu.ctc.sgapp.entidad.Escolaridad;
import uy.edu.ctc.sgapp.entidad.Persona;
import uy.edu.ctc.sgapp.enumerado.PersonaServicioMetodo;
import uy.edu.ctc.sgapp.enumerado.TipoMensaje;
import uy.edu.ctc.sgapp.logica.loPersona;
import uy.edu.ctc.sgapp.utiles.Retorno_MsgObj;
import uy.edu.ctc.sgapp.web_service.ws_estudios;
import uy.edu.ctc.sgapp.web_service.ws_evaluacionalumno;

import static uy.edu.ctc.sgapp.R.id.listCurso;
import static uy.edu.ctc.sgapp.R.id.listEvalParaInscribirse;

public class Tab_Carrera extends Fragment {

    private ListView listCarrera;
    private curso_carreraAdapter cur_carAdapter;

    Retorno_MsgObj parametro;
    Persona per;
    Escolaridad esc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_tab_carrera, container, false);

        ActionBar acBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        acBar.setHomeButtonEnabled(true);
        acBar.setDisplayHomeAsUpEnabled(true);

        listCarrera = (ListView) rootView.findViewById(R.id.listCarrera);

        cargarEscolaridad();

        return rootView;
    }

    //Metodo que consulta el servicio escolaridad
    public void cargarEscolaridad()
    {
        per = new Persona();
        if(loPersona.getInstancia(getContext()).SesionValida())
        {
            Long PerCod = loPersona.getInstancia(getContext()).DevolverCodigoPersona();

            per.setPerCod(PerCod);

            parametro  = new Retorno_MsgObj();
            parametro.setObjeto(per);

            ws_estudios ws = new ws_estudios(this, PersonaServicioMetodo.GET_ESTUDIOS, parametro);
            ws.execute();
        }
    }

    //Metodo que recibe el retorno del servicio
    public void Estudios(Retorno_MsgObj retorno)
    {
        if(retorno.getMensaje().getTipoMensaje() == TipoMensaje.ERROR)
        {
            System.out.println("No posee ninguna escolaridad" + TipoMensaje.ERROR);
            cur_carAdapter = new curso_carreraAdapter(getContext(), new ArrayList<>());

            if(listCarrera != null) listCarrera.setAdapter(cur_carAdapter);
        }
        else
        {
            List<Object> lstObj = new ArrayList<>();
            for(Object obj : retorno.getLstObjetos())
            {
                Escolaridad esc = (Escolaridad) obj;
                if(esc.getMateria() != null)
                {
                    lstObj.add(esc);
                }
            }

            if(lstObj != null) {
                cur_carAdapter = new curso_carreraAdapter(getContext(), lstObj);

                if (listCarrera != null) listCarrera.setAdapter(cur_carAdapter);
            }
            else
            {
                Toast.makeText(getContext(), "No tiene Evaluaciones de la Carrera", Toast.LENGTH_LONG).show();
            }
        }
    }
}
