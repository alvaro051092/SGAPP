package uy.edu.ctc.sgapp.Adaptadores;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import uy.edu.ctc.sgapp.R;
import uy.edu.ctc.sgapp.entidad.Calendario;
import uy.edu.ctc.sgapp.entidad.CalendarioAlumno;
import uy.edu.ctc.sgapp.entidad.Evaluacion;
import uy.edu.ctc.sgapp.entidad.Persona;
import uy.edu.ctc.sgapp.enumerado.Constantes;
import uy.edu.ctc.sgapp.enumerado.PersonaServicioMetodo;
import uy.edu.ctc.sgapp.enumerado.TipoMensaje;
import uy.edu.ctc.sgapp.logica.loPersona;
import uy.edu.ctc.sgapp.user_interface.Tab_Borrado;
import uy.edu.ctc.sgapp.user_interface.Tab_Inscribir;
import uy.edu.ctc.sgapp.utiles.Retorno_MsgObj;
import uy.edu.ctc.sgapp.web_service.ws_evaluacionalumno;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;
import static java.security.AccessController.getContext;
import static uy.edu.ctc.sgapp.R.id.listEvalParaBorrarse;
import static uy.edu.ctc.sgapp.R.id.listEvalParaInscribirse;

/**
 * Created by aa on 14-ago-17.
 */

public class EvaluacionesAdapter extends BaseAdapter{

    private Context context;
    private List<Object> lstObjeto;
    private String pant;
    AlertDialog.Builder dialog;
    Persona per;
    Retorno_MsgObj parametro;
    Calendario cal;
    CalendarioAlumno calAlumno;

    public EvaluacionesAdapter(Context context, List<Object> objeto, String pant)
    {
        dialog = new AlertDialog.Builder(context);
        this.context = context;
        this.lstObjeto = objeto;
        this.pant = pant;
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
    public View getView(final int posicion, View view, ViewGroup viewGroup) {
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
        Button btn_IngBor       = (Button) view.findViewById(R.id.btn_IngBor);

        final Calendario cal     = (Calendario) getItem(posicion);

        if(pant.equals(Constantes.MENU_LATERAL.toString())) btn_IngBor.setVisibility(View.GONE);
        if(pant.equals(Constantes.BORRAR.toString()))       btn_IngBor.setText("Borrar");
        if(pant.equals(Constantes.INSCRIBIR.toString()))    btn_IngBor.setText("Inscribir");
        Curso_Carrera.setText(cal.getEvaluacion().getNombreCarreraCurso());
        materia.setText("Materia: " + String.valueOf(cal.getEvaluacion().getEstudioNombre()));
        evaluacion.setText("Evaluación: " + cal.getEvaluacion().getEvlNom());
        fechaD.setText("Fecha Desde: " + new SimpleDateFormat("dd/MM/yyyy").format(cal.getEvlInsFchDsd()));
        fechaH.setText("Fecha Hasta: " + new SimpleDateFormat("dd/MM/yyyy").format(cal.getEvlInsFchHst()));
        fechaE.setText("Fecha Evaluacion: " + new SimpleDateFormat("dd/MM/yyyy").format(cal.getCalFch()));

        btn_IngBor.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                if(pant.equals(Constantes.BORRAR.toString())) {
                    dialog.setMessage("¿Desea Borrarse su inscripcion?");
                    dialog.setCancelable(true);
                    dialog.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i)
                        {
                            EliminarInscripcion(cal, posicion);
                        }
                    });
                }
                else
                {
                    dialog.setMessage("¿Desea inscribirse?");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("Inscribirme", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int i) {

                        Aceptar(cal, posicion);

                        }
                    });
                }
                dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                    }
                });
                dialog.show();
            }
        });

        return view;
    }

    //Borrarse de Evaluación
    public void EliminarInscripcion(Calendario cal, int pos)
    {
        parametro   = new Retorno_MsgObj();
        per         = new Persona();
        calAlumno   = new CalendarioAlumno();

        Long PerCod = loPersona.getInstancia(this.context).DevolverCodigoPersona();

        per.setPerCod(PerCod);

        calAlumno.setAlumno(per);
        calAlumno.setCalendario(cal);

        parametro.setObjeto(calAlumno);

        parametro.setLstObjetos(new ArrayList<>());
        parametro.getLstObjetos().add(pos);

        ws_evaluacionalumno ws = new ws_evaluacionalumno(this, PersonaServicioMetodo.DESINSCRIBIRALUMNO, parametro);
        ws.execute();
    }

//    Retorno del EliminarInscripcion
    public void DesinscribirAlumno(Retorno_MsgObj retorno)
    {
        if (retorno.getMensaje().getTipoMensaje() != TipoMensaje.ERROR) {
            lstObjeto.remove((int) retorno.getObjeto());
            notifyDataSetChanged();
        }
    }

    //Inscribirse a Evaluación
    public void Aceptar(Calendario cal, int pos)
    {
        parametro   = new Retorno_MsgObj();
        per         = new Persona();
        calAlumno   = new CalendarioAlumno();

        Long PerCod = loPersona.getInstancia(this.context).DevolverCodigoPersona();

        per.setPerCod(PerCod);

        calAlumno.setAlumno(per);
        calAlumno.setCalendario(cal);

        parametro.setObjeto(calAlumno);

        parametro.setLstObjetos(new ArrayList<>());
        parametro.getLstObjetos().add(pos);

        ws_evaluacionalumno ws = new ws_evaluacionalumno(this, PersonaServicioMetodo.INSCRIBIRALUMNO, parametro);
        ws.execute();
    }

//    Retorno del Inscribirse
    private void InscribirAlumno(Retorno_MsgObj retorno)
    {
        if (retorno.getMensaje().getTipoMensaje() != TipoMensaje.ERROR) {
            lstObjeto.remove((int) retorno.getObjeto());
            notifyDataSetChanged();
        }
    }
}
