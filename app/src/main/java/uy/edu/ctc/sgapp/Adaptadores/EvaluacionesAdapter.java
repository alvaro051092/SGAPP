package uy.edu.ctc.sgapp.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import uy.edu.ctc.sgapp.R;
import uy.edu.ctc.sgapp.entidad.Calendario;

/**
 * Created by aa on 14-ago-17.
 */

public class EvaluacionesAdapter extends BaseAdapter{

    private Context context;
    private List<Object> lstObjeto;

    public EvaluacionesAdapter(Context context, List<Object> objeto)
    {
        this.context = context;
        this.lstObjeto = objeto;
    }

    //Metodo que nos devuelve el tamaño de nuestro adapter
    @Override
    public int getCount() {
        return lstObjeto.size();
    }

    //Metodo del que obtenemos el item de nuestra lista
    @Override
    public Object getItem(int posicion) {
        return lstObjeto.get(posicion);
    }

    //En este metodo retornamos solo la posición
    @Override
    public long getItemId(int posicion) {
        return posicion;
    }

    //Retornamos la lista de cada item
    @Override
    public View getView(int posicion, View view, ViewGroup viewGroup) {
        if(view == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.itemevaluacion, null);
        }

        TextView evaluacion     = (TextView) view.findViewById(R.id.txt_evaluacion);
        TextView materia        = (TextView) view.findViewById(R.id.txt_materia);
        TextView Curso_Carrera  = (TextView) view.findViewById(R.id.txt_Curso_Carrera);
        TextView fechaD         = (TextView) view.findViewById(R.id.txt_fechaD);
        TextView fechaH         = (TextView) view.findViewById(R.id.txt_fechaH);
        TextView fechaE         = (TextView) view.findViewById(R.id.txt_fechaE);

        final Calendario cal     = (Calendario) getItem(posicion);

        Curso_Carrera.setText("Carrera / Curso: " + cal.getEvaluacion().getNombreCarreraCurso());
        materia.setText("Materia: " + String.valueOf(cal.getEvaluacion().getEstudioNombre()));
        evaluacion.setText("Evaluación: " + cal.getEvaluacion().getEvlNom());
        fechaD.setText("Fecha Desde: " + new SimpleDateFormat("dd/MM/yyyy").format(cal.getEvlInsFchDsd()));
        fechaH.setText("Fecha Hasta: " + new SimpleDateFormat("dd/MM/yyyy").format(cal.getEvlInsFchHst()));
        fechaE.setText("Fecha Evaluacion: " + new SimpleDateFormat("dd/MM/yyyy").format(cal.getCalFch()));

        return view;
    }
}
