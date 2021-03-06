package uy.edu.ctc.sgapp.user_interface;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import uy.edu.ctc.sgapp.R;
import uy.edu.ctc.sgapp.entidad.Persona;
import uy.edu.ctc.sgapp.enumerado.PersonaServicioMetodo;
import uy.edu.ctc.sgapp.logica.loPersona;
import uy.edu.ctc.sgapp.utiles.Retorno_MsgObj;
import uy.edu.ctc.sgapp.web_service.ws_persona;

public class login extends AppCompatActivity {

    private ProgressBar loading;
    private String usrTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loading = (ProgressBar) findViewById(R.id.lgn_load);
        final EditText usuario      = (EditText) findViewById(R.id.lgn_txt_user);
        final EditText password     = (EditText) findViewById(R.id.lgn_txt_psw);
        Button btnIniciar           = (Button) findViewById(R.id.lgn_btn_iniciar);

        if(loPersona.getInstancia(getApplicationContext()).SesionValida())
        {
            Intent intent = new Intent(this, menu_lateral.class);
            startActivity(intent);
        }

        loading.setVisibility(View.INVISIBLE);

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean error = false;
                String user = usuario.getText().toString();
                String psw  = password.getText().toString();

                if(user.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Debe ingresar su usuario", Toast.LENGTH_SHORT).show();
                    error = true;
                }
                if(psw.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Debe ingresar su contraseña", Toast.LENGTH_SHORT).show();
                    error = true;
                }

                if(!error)
                {
                    InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputMethodManager.hideSoftInputFromWindow(password.getWindowToken(), 0);

                    loading.setVisibility(View.VISIBLE);
                    IniciarSesion(user, psw);
                }

            }
        });
    }

    @Override
    public void onBackPressed ()
    {
        moveTaskToBack(true);
    }

    private void IniciarSesion(String user, String password){

        usrTexto = user;

        Persona persona             = new Persona();
        persona.setPerUsrMod(user);
        persona.setPerPass(password);

        Retorno_MsgObj parametro    = new Retorno_MsgObj();
        parametro.setObjeto(persona);

        ws_persona wsPersona = new ws_persona(this, PersonaServicioMetodo.LOGIN, parametro);
        wsPersona.execute();
    }


    private void RetornoLogin(Retorno_MsgObj retorno){
        if(!retorno.SurgioError()){

            Persona persona             = new Persona();
            persona.setPerUsrMod(usrTexto);


            Retorno_MsgObj parametro    = new Retorno_MsgObj();
            parametro.setObjeto(persona);

            ws_persona wsPersona = new ws_persona(this, PersonaServicioMetodo.GET_PERSONA_USR, parametro);
            wsPersona.execute();
        }
        else
        {
            loading.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(), retorno.getMensaje().getMensaje(), Toast.LENGTH_SHORT).show();
        }
    }

    private void RetornoGetPersona(Retorno_MsgObj retorno){
        if(!retorno.SurgioErrorObjetoRequerido()){

            Persona persona             = (Persona) retorno.getObjeto();

            loPersona.getInstancia(getApplicationContext()).GuardarCodigoPersona(persona.getPerCod());

            persona.setPerAppTkn(FirebaseInstanceId.getInstance().getToken());
            loPersona.getInstancia(getApplicationContext()).ActualizarToken(persona);

            Intent intent = new Intent(this, menu_lateral.class);
            startActivity(intent);

            loading.setVisibility(View.INVISIBLE);
        }
        else
        {
            loading.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(), retorno.getMensaje().getMensaje(), Toast.LENGTH_SHORT).show();
        }
    }


}
