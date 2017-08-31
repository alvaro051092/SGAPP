package uy.edu.ctc.sgapp.Adaptadores;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import uy.edu.ctc.sgapp.R;
import uy.edu.ctc.sgapp.entidad.Escolaridad;
import uy.edu.ctc.sgapp.entidad.Solicitud;

/**
 * Created by aa on 20-ago-17.
 */

public class solicitudAdapter extends BaseAdapter {

    private Context context;
    private List<Object> lstObjeto;

    public solicitudAdapter(Context context, List<Object> objeto)
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
            view = layoutInflater.inflate(R.layout.itemsolicitud, null);
        }

        TextView Solic      = (TextView) view.findViewById(R.id.txt_Solicitud);
        TextView Estado         = (TextView) view.findViewById(R.id.txt_Estado);
        TextView TipoSolicitud  = (TextView) view.findViewById(R.id.txt_TipoSolicitud);

        final Solicitud sol = (Solicitud) getItem(posicion);

        //cargar los textview respectivos
        Solic.setText("Solicitud");
        Estado.setText("Estado: " + sol.getSolEst().getNombre().toString());
        TipoSolicitud.setText("Tipo de Solicitud: " + sol.getSolTpo().getNombre().toString());

        return view;
    }
}
