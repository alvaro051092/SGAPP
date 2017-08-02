package uy.edu.ctc.sgapp.user_interface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import uy.edu.ctc.sgapp.R;
import uy.edu.ctc.sgapp.logica.Seguridad;
import uy.edu.ctc.sgapp.utiles.Retorno_MsgObj;
import uy.edu.ctc.sgapp.web_service.ws_login;
import uy.edu.ctc.sgapp.web_service.ws_persona;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnFirebase = (Button) findViewById(R.id.btn_getToken);
        Button btnServicio = (Button) findViewById(R.id.btn_ConsumirServicio);
        Button btnAccion   = (Button) findViewById(R.id.btn_Accion);

        btnFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "cargando");

                String refreshedToken = FirebaseInstanceId.getInstance().getToken();
                Log.e(TAG, "Refreshed token: " + refreshedToken);

                Toast.makeText(getApplicationContext(), "Refreshed token: " + refreshedToken, Toast.LENGTH_LONG).show();
            }
        });

        btnServicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ws_persona persona = new ws_persona(MainActivity.this);
                persona.execute();
            }
        });

        btnAccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = "alvaro";

                try {
                    String encriptado = Seguridad.GetInstancia().crypt(texto);

                    Toast.makeText(getApplicationContext(), "Encriptado: " + encriptado, Toast.LENGTH_SHORT).show();

                    String desencriptado = Seguridad.GetInstancia().decrypt(encriptado, "a#!?d./*@@^^''_a", "-KeY!!AD#AM!!KeY");

                    Toast.makeText(getApplicationContext(), "Desencriptado: " + desencriptado, Toast.LENGTH_SHORT).show();


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }

    public void MiFuncion(Retorno_MsgObj retorno)
    {
        Toast.makeText(getApplicationContext(), "Persona: " + retorno.getObjeto().toString(), Toast.LENGTH_SHORT).show();
    }
}
