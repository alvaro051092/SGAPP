package uy.edu.ctc.sgapp.Adaptadores;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import uy.edu.ctc.sgapp.R;
import uy.edu.ctc.sgapp.entidad.Calendario;
import uy.edu.ctc.sgapp.entidad.Escolaridad;
import uy.edu.ctc.sgapp.entidad.Inscripcion;
import uy.edu.ctc.sgapp.entidad.Materia;

/**
 * Created by aa on 20-ago-17.
 */

public class curso_carreraAdapter extends BaseAdapter {

    private Context context;
    private List<Object> lstObjeto;

    public curso_carreraAdapter(Context context, List<Object> objeto)
    {
        this.context = context;
        this.lstObjeto = objeto;
        Log.e("lstObjeto: ", lstObjeto.toString());
    }

    @Override
    public int getCount()
    {
        return lstObjeto.size();
    }

    @Override
    public Object getItem(int posicion)
    {
        return lstObjeto.get(posicion);
    }

    @Override
    public long getItemId(int posicion) {
        return posicion;
    }

    @Override
    public View getView(int posicion, View view, ViewGroup viewGroup) {
        if(view == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.itemescolaridad, null);
        }

        TextView CursoCarrera   = (TextView) view.findViewById(R.id.txt_CursoCarrera);
        TextView Calificacion   = (TextView) view.findViewById(R.id.txt_Calificacion);
        TextView Estado         = (TextView) view.findViewById(R.id.txt_Estado);
        TextView EstudioNombre  = (TextView) view.findViewById(R.id.txt_EstudioNombre);

        final Escolaridad esc = (Escolaridad) getItem(posicion);

        //cargar los textview respectivos
        EstudioNombre.setText("Curso/Carrera: " + esc.getNombreCarreraCurso());
        CursoCarrera.setText("Materia: " + esc.getEstudioNombre());
        Estado.setText("Estado: " + esc.getAprobacion());
        Calificacion.setText("Calificación: " + esc.getEscCalVal().toString());

        return view;
    }
}
