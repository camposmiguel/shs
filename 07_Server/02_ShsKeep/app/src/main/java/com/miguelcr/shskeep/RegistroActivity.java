package com.miguelcr.shskeep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistroActivity extends AppCompatActivity {
    EditText editTextNombre, editTextEmail, editTextPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        editTextNombre = (EditText) findViewById(R.id.edit_text_nombre);
        editTextEmail = (EditText) findViewById(R.id.edit_text_email);
        editTextPass = (EditText) findViewById(R.id.edit_text_password);
    }

    public void hacerRegistro(View view) {
        // Rescatamos los datos que el usuario ha escrito en el formulario
        String nombre = editTextNombre.getText().toString();
        String email = editTextEmail.getText().toString();
        String pass = editTextPass.getText().toString();

        if(nombre.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            editTextNombre.setError("Introduzca un nombre");


        } else {
            // Llamada al servidor
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://rest.miguelcr.com/keeper/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiKeepInterface service = retrofit.create(ApiKeepInterface.class);
            Call<RespuestaRegistro> call = service.doRegister(nombre,email,pass);

            call.enqueue(new Callback<RespuestaRegistro>() {
                @Override
                public void onResponse(Call<RespuestaRegistro> call, Response<RespuestaRegistro> response) {
                    int codigoRespuesta = response.code();
                    if(codigoRespuesta== HttpURLConnection.HTTP_OK) {
                        Intent i = new Intent(RegistroActivity.this, LoginActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(RegistroActivity.this, "El registro no se ha realizado", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RespuestaRegistro> call, Throwable t) {
                    // se lanza en caso de que no se produzca petición o respuesta
                }
            });
        }
    }

    public void irLogin(View view) {
        Intent i = new Intent(RegistroActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}
