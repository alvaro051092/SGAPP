package uy.edu.ctc.sgapp.user_interface;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import uy.edu.ctc.sgapp.Adaptadores.EvaluacionesAdapter;
import uy.edu.ctc.sgapp.Adaptadores.menu_lateralAdapter;
import uy.edu.ctc.sgapp.R;
import uy.edu.ctc.sgapp.entidad.Calendario;
import uy.edu.ctc.sgapp.entidad.CalendarioAlumno;
import uy.edu.ctc.sgapp.entidad.Menu;
import uy.edu.ctc.sgapp.entidad.Persona;
import uy.edu.ctc.sgapp.entidad.Solicitud;
import uy.edu.ctc.sgapp.enumerado.Constantes;
import uy.edu.ctc.sgapp.enumerado.PersonaServicioMetodo;
import uy.edu.ctc.sgapp.enumerado.TipoMensaje;
import uy.edu.ctc.sgapp.logica.loPersona;
import uy.edu.ctc.sgapp.utiles.Opciones_Menu;
import uy.edu.ctc.sgapp.utiles.Retorno_MsgObj;
import uy.edu.ctc.sgapp.web_service.ws_evaluacionalumno;
import uy.edu.ctc.sgapp.web_service.ws_persona;

public class menu_lateral extends AppCompatActivity {
    private ListView listMenuLateral;
    private menu_lateralAdapter mlAdapter;
    private ListView listEvaluaciones;
    private EvaluacionesAdapter evaAdapter;

    Object obj;
    Menu m;
    List<Object> lstObj = new ArrayList<>();
    Retorno_MsgObj retObject;
    Retorno_MsgObj parametro;
    Persona per;
    CalendarioAlumno calAlumno;

    //-------------------------------------------------
    private DrawerLayout drawerLayout;
    private LinearLayout miLayout;
    //-------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_lateral);

        showActionBar();

        listEvaluaciones    = (ListView) findViewById(R.id.listEvaluaciones);
        listMenuLateral     = (ListView) findViewById(R.id.menuLateral);
        drawerLayout        = (DrawerLayout) findViewById(R.id.drawer_layout);
        miLayout            = (LinearLayout) findViewById(R.id.miLayout);

        cargarMenu();

        CargarEvaluaciones();
        CargarEvaluacionesPendientes();

        listMenuLateral.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                obj = listMenuLateral.getItemAtPosition(i);

                IntentMenu(obj);

            }
        });
    }

    private void showActionBar() {
        LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.action_bar, null);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled (false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setCustomView(v);

        ImageButton burger = (ImageButton) findViewById(R.id.imgbtn_burger);

        burger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(drawerLayout.isDrawerOpen(listMenuLateral))
                {
                    drawerLayout.closeDrawer(listMenuLateral);
                }
                else
                {
                    drawerLayout.openDrawer(listMenuLateral);
                }
            }
        });
    }

    @Override
    public void onBackPressed ()
    {
        moveTaskToBack(true);
    }

    //Evaluaciones Pendientes
    public void CargarEvaluacionesPendientes()
    {
        per = new Persona();
        calAlumno = new CalendarioAlumno();
        if(loPersona.getInstancia(getApplicationContext()).SesionValida())
        {
            Long PerCod = loPersona.getInstancia(getApplicationContext()).DevolverCodigoPersona();

            per.setPerCod(PerCod);
            calAlumno.setAlumno(per);

            parametro  = new Retorno_MsgObj();
            parametro.setObjeto(calAlumno);

            ws_evaluacionalumno ws = new ws_evaluacionalumno(this, PersonaServicioMetodo.GET_EVALUACIONESPENDIENTE, parametro);
            ws.execute();
        }
    }

    //Retorno Evaluaciones Pendientes
    public void EvaluacionesPendiente(Retorno_MsgObj retorno)
    {
        if(retorno.getMensaje().getTipoMensaje() == TipoMensaje.MENSAJE)
        {
            for (Object obj : retorno.getLstObjetos()) {
                Calendario cal = (Calendario) obj;

                lstObj.add(cal);
            }
        }
        else
        {
            System.out.println("No hay evaluaciones pendientes o surgió un problema");
        }
        // 2do paso fue cargar el retObject con las evaluaciones Pendientes
        // Sigue al 3er paso
    }

    //Evaluaciones para Inscripción
    public void CargarEvaluaciones()
    {
        per = new Persona();
        calAlumno = new CalendarioAlumno();
        if(loPersona.getInstancia(getApplicationContext()).SesionValida())
        {
            Long PerCod = loPersona.getInstancia(getApplicationContext()).DevolverCodigoPersona();

            per.setPerCod(PerCod);
            calAlumno.setAlumno(per);

            parametro  = new Retorno_MsgObj();
            parametro.setObjeto(calAlumno);

            ws_evaluacionalumno ws = new ws_evaluacionalumno(this, PersonaServicioMetodo.GET_EVALUACIONESPARAINSCRIPCION, parametro);
            ws.execute();
        }
    }

    //Retorno Evaluaciones para Inscribir
    public void EvaluacionesParaInscripcion(Retorno_MsgObj retorno)
    {
        if(retorno.getMensaje().getTipoMensaje() == TipoMensaje.MENSAJE)
        {
            for (Object obj : retorno.getLstObjetos()) {
                Calendario cal = (Calendario) obj;

                lstObj.add(cal);
            }
            llenarListView();
        }
        else
        {
            System.out.println("No hay evaluaciones para inscripción o surgió un problema");
        }
        // 1er paso fue cargr el retObject con las evaluaciones para inscripción
        // Sigue el 2do paso
    }

    public void llenarListView()
    {
        retObject = new Retorno_MsgObj();

        retObject.setLstObjetos(lstObj);

        evaAdapter = new EvaluacionesAdapter(getApplicationContext(), retObject.getLstObjetos(), Constantes.MENU_LATERAL.toString());
        if(listEvaluaciones != null) listEvaluaciones.setAdapter(evaAdapter);

        // 3er paso, se refresco la ListView con los datos cargados en el retObject paso 1 y 2
    }

    public void cargarMenu()
    {
        Opciones_Menu OM = new Opciones_Menu();
        ArrayList<Menu> lstMen = new ArrayList<>();

        lstMen = OM.CargarMenu();

        //Cargo adapter
        mlAdapter = new menu_lateralAdapter(getApplicationContext(), lstMen);

        //Cargo listView
        listMenuLateral.setAdapter(mlAdapter);
    }

    public void IntentMenu(Object obj)
    {
        m = (Menu) obj;
        switch (m.getOpcMenCod())
        {
            //Evaluaciones
            case 1:
                Intent a = new Intent(this, lstEvaluacionInscripcionAlumnos.class);
                startActivity(a);
                drawerLayout.closeDrawer(listMenuLateral);
                break;
            //Solicitudes
            case 2:
                Intent b = new Intent(this, Solicitudes.class);
                startActivity(b);
                drawerLayout.closeDrawer(listMenuLateral);
                break;
            //Escolaridad
            case 3:
                Intent c = new Intent(this, escolaridad.class);
                startActivity(c);
                drawerLayout.closeDrawer(listMenuLateral);
                break;
            //Cerrar Session
            case 4:
                CerrarSesion();
                break;
        }
    }

    private void CerrarSesion(){
        if(loPersona.getInstancia(getApplicationContext()).SesionValida()) {

            Persona persona = new Persona();
            persona.setPerCod(loPersona.getInstancia(getApplicationContext()).DevolverCodigoPersona());

            Retorno_MsgObj parametro = new Retorno_MsgObj();
            parametro.setObjeto(persona);

            ws_persona wsPersona = new ws_persona(this, PersonaServicioMetodo.LOGOUT, parametro);
            wsPersona.execute();
        }
    }

    private void RetornoLogout(Retorno_MsgObj retorno){
        if(!retorno.SurgioError()){

            loPersona.getInstancia(getApplicationContext()).QuitarCodigoPersona();
            Toast.makeText(getApplicationContext(), "Sesion cerrada correctamente", Toast.LENGTH_SHORT).show();

            Intent d = new Intent(this, login.class);
            startActivity(d);
        }
        else
        {
            Toast.makeText(getApplicationContext(), retorno.getMensaje().getMensaje(), Toast.LENGTH_SHORT).show();
        }
    }
}
