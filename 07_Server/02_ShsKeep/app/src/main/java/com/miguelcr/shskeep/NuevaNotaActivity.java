package com.miguelcr.shskeep;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class NuevaNotaActivity extends AppCompatActivity {
    EditText editTextTitulo, editTextDescripcion;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_nota);

        // Averiguamos si existen preferencias guardadas
        SharedPreferences settings = getSharedPreferences(Constantes.PREF_NAME, 0);
        userId = settings.getString(Constantes.PREF_EMAIL, null);

        editTextTitulo = (EditText) findViewById(R.id.edit_text_titulo);
        editTextDescripcion = (EditText) findViewById(R.id.edit_text_descripcion);
    }

    public void guardarNota(View view) {
        String titulo = editTextTitulo.getText().toString();
        String descripcion = editTextDescripcion.getText().toString();

        // Llamada al servidor
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://rest.miguelcr.com/keeper/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiKeepInterface service = retrofit.create(ApiKeepInterface.class);
        Call<RespuestaNotas> call = service.addNota(titulo,descripcion,userId);

        call.enqueue(new Callback<RespuestaNotas>() {
            @Override
            public void onResponse(Call<RespuestaNotas> call, Response<RespuestaNotas> response) {
                int codigoRespuesta = response.code();
                if(codigoRespuesta== HttpURLConnection.HTTP_OK) {
                    finish();
                } else {
                    Toast.makeText(NuevaNotaActivity.this, "No se ha guardado la nota", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RespuestaNotas> call, Throwable t) {
                // se lanza en caso de que no se produzca petición o respuesta
            }
        });
    }
}
