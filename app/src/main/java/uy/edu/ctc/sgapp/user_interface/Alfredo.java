package uy.edu.ctc.sgapp.user_interface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import uy.edu.ctc.sgapp.R;
import uy.edu.ctc.sgapp.entidad.Calendario;
import uy.edu.ctc.sgapp.entidad.CalendarioAlumno;
import uy.edu.ctc.sgapp.entidad.Persona;
import uy.edu.ctc.sgapp.logica.loEvaluacion;

public class Alfredo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alfredo);

        Button btnLstEvaluacionXAlumno = (Button) findViewById(R.id.btn_LstEvaluacionXAlumno);
        Button btnLstXAlumno = (Button) findViewById(R.id.btn_lstXAlumno);
        Button btnLstPendientes = (Button) findViewById(R.id.btn_LstPendientes);
        Button btnInscribir = (Button) findViewById(R.id.btn_Inscribir);
        Button btnDesinscribir = (Button) findViewById(R.id.btn_desinscribir);

        

        //----------------------------------------------------------------------------------------------
        // Evaluacion
        //----------------------------------------------------------------------------------------------
        btnLstEvaluacionXAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                long AluPerCod = 3;
//
//                Persona per = new Persona();
//                per.setPerCod(AluPerCod);
//
//                CalendarioAlumno calAlumno = new CalendarioAlumno();
//
//                calAlumno.setAlumno(per);
//
//                loEvaluacion.getInstancia(getApplicationContext()).EvaluacionesParaInscripcion(calAlumno);
                Intent intent = new Intent(Alfredo.this, lstEvaluacionInscripcionAlumnos.class);
                startActivity(intent);

            }
        });

        btnLstXAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                long AluPerCod = 3;
//
//                Persona per = new Persona();
//                per.setPerCod(AluPerCod);
//
//                CalendarioAlumno calAlumno = new CalendarioAlumno();
//
//                calAlumno.setAlumno(per);

//                loEvaluacion.getInstancia(getApplicationContext()).lstPorAlumno(calAlumno);

//                Intent intent = new Intent(Alfredo.this, lstEvaluacionInscripcionAlumno.class);
//                startActivity(intent);
            }
        });

        btnLstPendientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long AluPerCod = 3;

                Persona per = new Persona();
                per.setPerCod(AluPerCod);

                CalendarioAlumno calAlumno = new CalendarioAlumno();

                calAlumno.setAlumno(per);

                loEvaluacion.getInstancia(getApplicationContext()).lstPendiente(calAlumno);
            }
        });

//        btnInscribir.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                long AluPerCod  = 3;
//                long CalCod     = 5;
//
//                Persona per = new Persona();
//                per.setPerCod(AluPerCod);
//
//                Calendario cal = new Calendario();
//                cal.setCalCod(CalCod);
//
//                CalendarioAlumno calAlumno = new CalendarioAlumno();
//
//                calAlumno.setAlumno(per);
//                calAlumno.setCalendario(cal);

//                loEvaluacion.getInstancia(getApplicationContext()).InscribirAlumno(calAlumno);
//            }
//        });

        btnDesinscribir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long CalAlCod   = 31;
                long CalCod     = 5;

                Calendario cal = new Calendario();
                cal.setCalCod(CalCod);

                CalendarioAlumno calAlumno = new CalendarioAlumno();

                calAlumno.setCalendario(cal);
                calAlumno.setCalAlCod(CalAlCod);

                loEvaluacion.getInstancia(getApplicationContext()).DesinscribirAlumno(calAlumno);
            }
        });
        //----------------------------------------------------------------------------------------------

        //----------------------------------------------------------------------------------------------
        // Estudios
        //----------------------------------------------------------------------------------------------

    }
}
