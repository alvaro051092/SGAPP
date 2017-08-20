package uy.edu.ctc.sgapp.user_interface;

import android.content.Intent;
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

        Button btnAlvaro = (Button) findViewById(R.id.btn_alvaro);
        Button btnAlfredo = (Button) findViewById(R.id.btn_alfredo);

        btnAlvaro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Alvaro.class);
                startActivity(intent);

            }
        });

        btnAlfredo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, menu_lateral.class);
                startActivity(intent);
            }
        });




    }

}
