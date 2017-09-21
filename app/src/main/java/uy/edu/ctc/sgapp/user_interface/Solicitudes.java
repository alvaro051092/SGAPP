package uy.edu.ctc.sgapp.user_interface;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import uy.edu.ctc.sgapp.Adaptadores.EvaluacionesAdapter;
import uy.edu.ctc.sgapp.Adaptadores.solicitudAdapter;
import uy.edu.ctc.sgapp.R;
import uy.edu.ctc.sgapp.entidad.Persona;
import uy.edu.ctc.sgapp.entidad.Solicitud;
import uy.edu.ctc.sgapp.enumerado.PersonaServicioMetodo;
import uy.edu.ctc.sgapp.enumerado.TipoMensaje;
import uy.edu.ctc.sgapp.enumerado.TipoSolicitud;
import uy.edu.ctc.sgapp.logica.loPersona;
import uy.edu.ctc.sgapp.utiles.Retorno_MsgObj;
import uy.edu.ctc.sgapp.web_service.ws_estudios;
import uy.edu.ctc.sgapp.web_service.ws_evaluacionalumno;
import uy.edu.ctc.sgapp.web_service.ws_solicitudes;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static uy.edu.ctc.sgapp.R.id.listCarrera;
import static uy.edu.ctc.sgapp.R.id.listEvalParaBorrarse;
import static uy.edu.ctc.sgapp.enumerado.TipoSolicitud.ESCOLARIDAD;

public class Solicitudes extends AppCompatActivity {

    private ListView lstSolicitudes;
    private solicitudAdapter solAdapter;
    private Spinner spin_TpoSol;
    private AlertDialog.Builder dialog;
    private Retorno_MsgObj parametro;
    private ProgressBar loading;

    Persona per;
    Solicitud sol;
    TipoSolicitud TpoSolId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitudes);

        loading = (ProgressBar) findViewById(R.id.sol_load);

        ActionBar acBar = getSupportActionBar();
        acBar.setHomeButtonEnabled(true);
        acBar.setDisplayHomeAsUpEnabled(true);

        dialog = new AlertDialog.Builder(this);

        lstSolicitudes = (ListView) findViewById(R.id.lst_Solicitudes);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btn_add_solicitud);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Solicitudes.this);
                mBuilder.setTitle("Nueva Solicitud");
                View mView = getLayoutInflater().inflate(R.layout.add_solicitud, null);
                spin_TpoSol = (Spinner) mView.findViewById(R.id.spin_solicitudes);
                CargarSpinner();

                mBuilder.setPositiveButton("Solicitar", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        solicitar();
                    }
                });

                spin_TpoSol.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        Object obj = spin_TpoSol.getItemAtPosition(i);

                        String tpoSolNom = obj.toString();

                        switch(tpoSolNom)
                        {
                            case "Escolaridad":
                                TpoSolId = TipoSolicitud.ESCOLARIDAD;
                                break;
                            case "Constancia de estudio":
                                TpoSolId = TipoSolicitud.CONSTANCIA_ESTUDIO;
                                break;
                            case "Duplicado de factura":
                                TpoSolId = TipoSolicitud.DUPLICADO_FACTURA;
                                break;
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();

            }
        });

        CargarListview();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == android.R.id.home)
        {
            Intent intent = new Intent(Solicitudes.this, menu_lateral.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void CargarSpinner ()
    {
        ArrayList<String> lstTpoSol = new ArrayList<>();

        for(TipoSolicitud TpoSol : TipoSolicitud.values())
        {
            lstTpoSol.add(TpoSol.getNombre());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lstTpoSol);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_TpoSol.setAdapter(adapter);

    }

    public void solicitar()
    {
        parametro = new Retorno_MsgObj();
        per = new Persona();
        sol = new Solicitud();

        if(loPersona.getInstancia(getApplicationContext()).SesionValida())
        {
            //Persona
            Long PerCod = loPersona.getInstancia(getApplicationContext()).DevolverCodigoPersona();
            per.setPerCod(PerCod);

            //Solicitud
            sol.setSolTpo(TpoSolId);
            sol.setAlumno(per);

            parametro = new Retorno_MsgObj();
            parametro.setObjeto(sol);

            ws_solicitudes ws = new ws_solicitudes(this, PersonaServicioMetodo.ING_SOLICITUD, parametro);
            ws.execute();

            CargarListview();
        }
    }

    //metodo que recibe el ingreso True or False
    public void retornoSolicitud(Retorno_MsgObj retorno)
    {
        if(retorno.getMensaje().getTipoMensaje() == TipoMensaje.ERROR)
        {
            Toast.makeText(getApplicationContext(), retorno.getMensaje().toString(), Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "La solicitud fue enviada", Toast.LENGTH_LONG).show();
        }
    }


    public void CargarListview()
    {
        lstSolicitudes.setVisibility(View.INVISIBLE);
        loading.setVisibility(View.VISIBLE);
        per = new Persona();
        sol = new Solicitud();

        if(loPersona.getInstancia(getApplicationContext()).SesionValida()) {
            //Persona
            Long PerCod = loPersona.getInstancia(getApplicationContext()).DevolverCodigoPersona();
            per.setPerCod(PerCod);

            //Solicitud
            sol.setAlumno(per);

            parametro = new Retorno_MsgObj();
            parametro.setObjeto(sol);

            ws_solicitudes ws = new ws_solicitudes(this, PersonaServicioMetodo.GET_SOLICITUDESACTIVAS, parametro);
            ws.execute();
        }
    }

    //metodo que recibe las solicitudes activas
    public void retornoSolicitudesActivas(Retorno_MsgObj retorno)
    {
        lstSolicitudes.setVisibility(View.VISIBLE);
        loading.setVisibility(View.INVISIBLE);
        if(retorno.getMensaje().getTipoMensaje() == TipoMensaje.ERROR)
        {
            Toast.makeText(getApplicationContext(), "Aún no tiene Solicitudes", Toast.LENGTH_LONG).show();
            solAdapter = new solicitudAdapter(getApplicationContext(), new ArrayList<>());
            if(lstSolicitudes != null) lstSolicitudes.setAdapter(solAdapter);
        }
        else
        {
            solAdapter = new solicitudAdapter(getApplicationContext(), retorno.getLstObjetos());
            if(lstSolicitudes != null) lstSolicitudes.setAdapter(solAdapter);
        }
    }
}
