package com.miguelcr.shskeep;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Constructor;
import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    EditText editTextEmail, editTextPass;
    CheckBox checkBoxRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Averiguamos si existen preferencias guardadas
        SharedPreferences settings = getSharedPreferences(Constantes.PREF_NAME, 0);
        String emailPref = settings.getString(Constantes.PREF_EMAIL, null);
        String passPref = settings.getString(Constantes.PREF_PASSWORD, null);

        if(emailPref==null || passPref==null) {
            setContentView(R.layout.activity_login);
            editTextEmail = (EditText) findViewById(R.id.edit_text_email);
            editTextPass = (EditText) findViewById(R.id.edit_text_password);
            checkBoxRemember = (CheckBox) findViewById(R.id.checkbox_remember_me);
        } else {
            llamadaLoginServer(emailPref,passPref,false);

        }
    }

    public void hacerLogin(View view) {
        // Rescatamos los datos que el usuario ha escrito en el formulario
        String email = editTextEmail.getText().toString();
        String pass = editTextPass.getText().toString();

        if(email.isEmpty()) {
            editTextEmail.setError("Introduzca un email");
        } else if(pass.isEmpty()) {
            editTextEmail.setError("Introduzca su contraseña");

        } else {
            llamadaLoginServer(email,pass,checkBoxRemember.isChecked());
        }
    }

    private void llamadaLoginServer(final String email, final String pass, final boolean check) {
        // Llamada al servidor
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://rest.miguelcr.com/keeper/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiKeepInterface service = retrofit.create(ApiKeepInterface.class);
        Call<RespuestaRegistro> call = service.doLogin(email,pass);

        call.enqueue(new Callback<RespuestaRegistro>() {
            @Override
            public void onResponse(Call<RespuestaRegistro> call, Response<RespuestaRegistro> response) {
                int codigoRespuesta = response.code();
                if(codigoRespuesta== HttpURLConnection.HTTP_OK) {
                    // Guardar en una variable del dispositivo (preferencias)
                    // la información del login
                    if(check) {
                        SharedPreferences settings = getSharedPreferences(Constantes.PREF_NAME, 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString(Constantes.PREF_EMAIL, email);
                        editor.putString(Constantes.PREF_PASSWORD, pass);
                        editor.commit();

                    }

                    // Abro la pantalla donde se muestran las notas del usuario
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(LoginActivity.this, "El login no se ha realizado", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<RespuestaRegistro> call, Throwable t) {
                // se lanza en caso de que no se produzca petición o respuesta
            }
        });
    }

    public void irRegistro(View view) {
        Intent i = new Intent(LoginActivity.this, RegistroActivity.class);
        startActivity(i);
        finish();
    }
}
