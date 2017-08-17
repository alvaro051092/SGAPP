package uy.edu.ctc.sgapp.user_interface;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import uy.edu.ctc.sgapp.Adaptadores.EvaluacionesAdapter;
import uy.edu.ctc.sgapp.R;
import uy.edu.ctc.sgapp.entidad.Calendario;
import uy.edu.ctc.sgapp.entidad.CalendarioAlumno;
import uy.edu.ctc.sgapp.entidad.Persona;
import uy.edu.ctc.sgapp.enumerado.PersonaServicioMetodo;
import uy.edu.ctc.sgapp.enumerado.TipoMensaje;
import uy.edu.ctc.sgapp.logica.loEvaluacion;
import uy.edu.ctc.sgapp.utiles.Mensajes;
import uy.edu.ctc.sgapp.utiles.Retorno_MsgObj;
import uy.edu.ctc.sgapp.web_service.ws_evaluacionalumno;

public class lstEvaluacionInscripcionAlumno extends AppCompatActivity {
    private ListView listEvalParaInscribirse;
    private EvaluacionesAdapter evaAdapter;

    Persona per;
    Retorno_MsgObj parametro;
    CalendarioAlumno calAlumno;
    Calendario cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lstevaluacion_inscripcion_alumno);
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        cargarDatos();

        listEvalParaInscribirse = (ListView) findViewById(R.id.listEvalParaInscribirse);
//------------------------------------------------------------------
//        long AluPerCod = 3;
//------------------------------------------------------------------
//        per = new Persona();
//        per.setPerCod(AluPerCod);



//        if(this.SesionValida())
//        {
        parametro  = new Retorno_MsgObj();
        parametro.setObjeto(calAlumno);

        ws_evaluacionalumno ws = new ws_evaluacionalumno(this, PersonaServicioMetodo.GET_EVALUACIONESPARAINSCRIPCION, parametro);
        ws.execute();
//        }

        listEvalParaInscribirse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                dialog.setTitle("Inscripción");
                dialog.setMessage("¿Desea inscribirse?");
                dialog.setCancelable(false);
                dialog.setPositiveButton("Inscribirme", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        Aceptar();

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
    }

    public void Aceptar()
    {
        parametro = new Retorno_MsgObj();
        parametro.setObjeto(calAlumno);

        ws_evaluacionalumno ws = new ws_evaluacionalumno(this, PersonaServicioMetodo.INSCRIBIRALUMNO, parametro);
        ws.execute();
    }

    public void EvaluacionesParaInscripcion(Retorno_MsgObj retorno)
    {
        ArrayList<Object> lstObjeto = new ArrayList<>();
        lstObjeto.add(retorno.getLstObjetos());

        evaAdapter = new EvaluacionesAdapter(this, lstObjeto);

        listEvalParaInscribirse = (ListView) findViewById(R.id.listEvalParaInscribirse);
        listEvalParaInscribirse.setAdapter(evaAdapter);
    }

    private void InscribirAlumno(Retorno_MsgObj retorno)
    {
        if(retorno.getMensaje().getTipoMensaje() == TipoMensaje.MENSAJE)
        {
            Toast.makeText(getApplicationContext(), "Estas Inscripto", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Error, no has podido inscribirte", Toast.LENGTH_LONG).show();
        }
    }

    public void cargarDatos()
    {
        //Instancias
        calAlumno = new CalendarioAlumno();
        cal = new Calendario();
        per = new Persona();

        //Obtengo Datos
        long AluPerCod = 3;
        long CalCod = 7;

        //Cargo Datos
        per.setPerCod(AluPerCod);
        cal.setCalCod(CalCod);

        calAlumno.setAlumno(per);
        calAlumno.setCalendario(cal);
    }
}
