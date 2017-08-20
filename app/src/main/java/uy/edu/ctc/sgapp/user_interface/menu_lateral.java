package uy.edu.ctc.sgapp.user_interface;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import uy.edu.ctc.sgapp.Adaptadores.EvaluacionesAdapter;
import uy.edu.ctc.sgapp.Adaptadores.menu_lateralAdapter;
import uy.edu.ctc.sgapp.R;
import uy.edu.ctc.sgapp.entidad.Menu;
import uy.edu.ctc.sgapp.utiles.Opciones_Menu;
import uy.edu.ctc.sgapp.utiles.Retorno_MsgObj;

public class menu_lateral extends AppCompatActivity {
    private ListView listMenuLateral;
    private menu_lateralAdapter mlAdapter;

    Object obj;
    Menu m;
    Retorno_MsgObj parametro;

    //-------------------------------------------------
    private DrawerLayout drawerLayout;
    private LinearLayout miLayout;
    //-------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_lateral);

        ActionBar acBar = getSupportActionBar();
        acBar.setHomeButtonEnabled(true);
        acBar.setDisplayHomeAsUpEnabled(true);

        listMenuLateral = (ListView) findViewById(R.id.menuLateral);
        //-------------------------------------------------
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        miLayout = (LinearLayout) findViewById(R.id.miLayout);
        //-------------------------------------------------

        cargarMenu();



        listMenuLateral.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                obj = listMenuLateral.getItemAtPosition(i);

                IntentMenu(obj);

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == android.R.id.home)
        {
            if(drawerLayout.isDrawerOpen(listMenuLateral))
            {
                drawerLayout.closeDrawer(listMenuLateral);
            }
            else
            {
                drawerLayout.openDrawer(listMenuLateral);
            }
        }
        return super.onOptionsItemSelected(item);
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
            case 1:
                Intent a = new Intent(this, lstEvaluacionInscripcionAlumnos.class);
                startActivity(a);
                break;
            case 2:
                Intent b = new Intent(this, lstEvaluacionInscripcionAlumnos.class);
                startActivity(b);
                break;
            case 3:
                Intent c = new Intent(this, lstEvaluacionInscripcionAlumnos.class);
                startActivity(c);
                break;
            case 4:
                Intent d = new Intent(this, lstEvaluacionInscripcionAlumnos.class);
                startActivity(d);
                break;
            case 5:
                Intent e = new Intent(this, lstEvaluacionInscripcionAlumnos.class);
                startActivity(e);
                break;
        }
    }
}
