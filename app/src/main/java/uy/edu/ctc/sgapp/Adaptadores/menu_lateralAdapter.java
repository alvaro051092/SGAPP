package uy.edu.ctc.sgapp.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import uy.edu.ctc.sgapp.R;
import uy.edu.ctc.sgapp.entidad.Menu;

/**
 * Created by aa on 14-ago-17.
 */

public class menu_lateralAdapter extends BaseAdapter{

    private Context context;
    private List<Menu> lstMenu;

    public menu_lateralAdapter(Context context, List<Menu> lstMen)
    {
        this.context = context;
        this.lstMenu = lstMen;
    }

    //Metodo que nos devuelve el tamaño de nuestro adapter
    @Override
    public int getCount() {
        return lstMenu.size();
    }

    //Metodo del que obtenemos el item de nuestra lista
    @Override
    public Object getItem(int posicion) {
        return lstMenu.get(posicion);
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
            view = layoutInflater.inflate(R.layout.itemmenulateral, null);
        }

        TextView tituloMenu = (TextView) view.findViewById(R.id.txt_title);

        final Menu m = (Menu) getItem(posicion);

        tituloMenu.setText(String.valueOf(m.getOpcMenNom()));

        return view;
    }
}
